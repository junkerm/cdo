/***************************************************************************
 * Copyright (c) 2004-2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.internal.net4j.util.container.delegate;

import org.eclipse.net4j.util.container.IContainer;
import org.eclipse.net4j.util.container.IContainerDelta;
import org.eclipse.net4j.util.container.IContainerDelta.Kind;

import org.eclipse.internal.net4j.util.container.ContainerEvent;
import org.eclipse.internal.net4j.util.container.SingleDeltaContainerEvent;
import org.eclipse.internal.net4j.util.event.Notifier;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Eike Stepper
 */
public abstract class AbstractDelegator<E> extends Notifier implements IContainer<E>
{
  public AbstractDelegator()
  {
  }

  protected void fireAddedEvent(E o)
  {
    fireEvent(new SingleDeltaContainerEvent(this, o, IContainerDelta.Kind.ADDED));
  }

  protected void fireRemovedEvent(Object o)
  {
    fireEvent(new SingleDeltaContainerEvent(this, o, IContainerDelta.Kind.REMOVED));
  }

  protected ContainerEvent createEvent(Collection c, Kind kind)
  {
    ContainerEvent event = new ContainerEvent(this);
    for (Object o : c)
    {
      event.addDelta(o, kind);
    }

    return event;
  }

  protected boolean dispatchEvent(ContainerEvent event)
  {
    if (event.isEmpty())
    {
      return false;
    }

    fireEvent(event);
    return true;
  }

  /**
   * @author Eike Stepper
   */
  public class DelegatingIterator implements Iterator<E>
  {
    private Iterator<E> delegate;

    protected E last;

    public DelegatingIterator(Iterator<E> delegate)
    {
      this.delegate = delegate;
    }

    public Iterator<E> getDelegate()
    {
      return delegate;
    }

    /**
     * @category READ
     */
    public boolean hasNext()
    {
      return getDelegate().hasNext();
    }

    /**
     * @category READ
     */
    public E next()
    {
      return last = getDelegate().next();
    }

    /**
     * @category WRITE
     */
    public void remove()
    {
      getDelegate().remove();
      fireRemovedEvent(last);
      last = null;
    }
  }
}
