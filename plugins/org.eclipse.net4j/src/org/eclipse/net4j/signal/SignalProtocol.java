/***************************************************************************
 * Copyright (c) 2004, 2005, 2006 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.net4j.signal;

import org.eclipse.net4j.stream.BufferInputStream;
import org.eclipse.net4j.stream.ChannelOutputStream;
import org.eclipse.net4j.transport.Buffer;
import org.eclipse.net4j.transport.BufferProvider;
import org.eclipse.net4j.transport.Channel;
import org.eclipse.net4j.util.om.trace.ContextTracer;

import org.eclipse.internal.net4j.bundle.Net4j;
import org.eclipse.internal.net4j.transport.AbstractProtocol;
import org.eclipse.internal.net4j.transport.BufferUtil;
import org.eclipse.internal.net4j.transport.ChannelImpl;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author Eike Stepper
 */
public abstract class SignalProtocol extends AbstractProtocol
{
  public static final long NO_TIMEOUT = BufferInputStream.NO_TIMEOUT;

  private static final int MIN_CORRELATION_ID = 1;

  private static final int MAX_CORRELATION_ID = Integer.MAX_VALUE;

  private static final ContextTracer TRACER = new ContextTracer(Net4j.DEBUG_SIGNAL,
      SignalProtocol.class);

  private static final ContextTracer STREAM_TRACER = new ContextTracer(Net4j.DEBUG_BUFFER_STREAM,
      SignalOutputStream.class);

  private ExecutorService executorService;

  private Map<Integer, Signal> signals = new ConcurrentHashMap(0);

  private int nextCorrelationID = MIN_CORRELATION_ID;

  protected SignalProtocol(Channel channel, ExecutorService executorService)
  {
    super(channel);

    if (executorService == null)
    {
      throw new IllegalArgumentException("executorService == null"); //$NON-NLS-1$
    }

    this.executorService = executorService;
  }

  protected SignalProtocol(Channel channel)
  {
    this(channel, ((ChannelImpl)channel).getReceiveExecutor());
  }

  public boolean waitForSignals(long timeout)
  {
    synchronized (signals)
    {
      while (!signals.isEmpty())
      {
        try
        {
          signals.wait(timeout);
        }
        catch (InterruptedException ex)
        {
          return false;
        }
      }
    }

    return true;
  }

  public void handleBuffer(Buffer buffer)
  {
    ByteBuffer byteBuffer = buffer.getByteBuffer();
    int correlationID = byteBuffer.getInt();
    if (TRACER.isEnabled())
    {
      TRACER.trace("Received buffer for correlation " + correlationID); //$NON-NLS-1$
    }

    Signal signal;
    if (correlationID > 0)
    {
      // Incoming indication
      signal = signals.get(-correlationID);
      if (signal == null)
      {
        short signalID = byteBuffer.getShort();
        if (TRACER.isEnabled())
        {
          TRACER.trace("Got signal id " + signalID); //$NON-NLS-1$
        }

        signal = createSignalReactor(signalID);
        signal.setProtocol(this);
        signal.setCorrelationID(-correlationID);
        signal.setInputStream(new SignalInputStream(getInputStreamTimeout()));
        signal.setOutputStream(new SignalOutputStream(-correlationID, signalID, false));
        signals.put(-correlationID, signal);
        executorService.execute(signal);
      }
    }
    else
    {
      // Incoming confirmation
      signal = signals.get(-correlationID);
      if (signal == null)
      {
        if (TRACER.isEnabled())
        {
          TRACER.trace("Discarding buffer"); //$NON-NLS-1$
        }

        buffer.release();
      }
    }

    signal.getInputStream().handleBuffer(buffer);
  }

  public long getInputStreamTimeout()
  {
    return NO_TIMEOUT;
  }

  @Override
  public String toString()
  {
    return "SignalProtocol[" + getProtocolID() + ", " + getChannel() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  protected abstract SignalReactor createSignalReactor(short signalID);

  void startSignal(SignalActor signalActor, long timeout)
  {
    if (signalActor.getProtocol() != this)
    {
      throw new IllegalArgumentException("signalActor.getProtocol() != this"); //$NON-NLS-1$
    }

    short signalID = signalActor.getSignalID();
    int correlationID = signalActor.getCorrelationID();
    signalActor.setInputStream(new SignalInputStream(timeout));
    signalActor.setOutputStream(new SignalOutputStream(correlationID, signalID, true));
    signals.put(correlationID, signalActor);

    signalActor.run();
  }

  void stopSignal(Signal signal)
  {
    int correlationID = signal.getCorrelationID();
    signals.remove(correlationID);

    synchronized (signals)
    {
      signals.notifyAll();
    }
  }

  int getNextCorrelationID()
  {
    int correlationID = nextCorrelationID;
    if (nextCorrelationID == MAX_CORRELATION_ID)
    {
      if (TRACER.isEnabled())
      {
        TRACER.trace("Correlation wrap around"); //$NON-NLS-1$
      }

      nextCorrelationID = MIN_CORRELATION_ID;
    }
    else
    {
      ++nextCorrelationID;
    }

    return correlationID;
  }

  /**
   * @author Eike Stepper
   */
  class SignalInputStream extends BufferInputStream
  {
    private long timeout;

    public SignalInputStream(long timeout)
    {
      this.timeout = timeout;
    }

    @Override
    public long getMillisBeforeTimeout()
    {
      return timeout;
    }
  }

  /**
   * @author Eike Stepper
   */
  class SignalOutputStream extends ChannelOutputStream
  {
    public SignalOutputStream(final int correlationID, final short signalID,
        final boolean addSignalID)
    {
      super(getChannel(), new BufferProvider()
      {
        private BufferProvider delegate = BufferUtil.getBufferProvider(getChannel());

        private boolean firstBuffer = addSignalID;

        public short getBufferCapacity()
        {
          return delegate.getBufferCapacity();
        }

        public Buffer provideBuffer()
        {
          Buffer buffer = delegate.provideBuffer();
          ByteBuffer byteBuffer = buffer.startPutting(getChannel().getChannelIndex());
          if (STREAM_TRACER.isEnabled())
          {
            STREAM_TRACER.trace("Providing buffer for correlation " + correlationID); //$NON-NLS-1$
          }

          byteBuffer.putInt(correlationID);
          if (firstBuffer)
          {
            if (SignalProtocol.TRACER.isEnabled())
            {
              STREAM_TRACER.trace("Put signal id " + signalID); //$NON-NLS-1$
            }

            byteBuffer.putShort(signalID);
          }

          firstBuffer = false;
          return buffer;
        }

        public void retainBuffer(Buffer buffer)
        {
          delegate.retainBuffer(buffer);
        }
      });
    }
  }
}
