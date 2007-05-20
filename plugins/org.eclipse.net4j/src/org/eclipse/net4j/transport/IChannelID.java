/***************************************************************************
 * Copyright (c) 2004 - 2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.net4j.transport;

/**
 * An identifier of an {@link IChannel} that is unique among all channels of all
 * {@link IConnector}s.
 * 
 * @author Eike Stepper
 * @since 0.8.0
 */
public interface IChannelID
{
  /**
   * Returns the connector of the associated {@link IChannel}.
   */
  public IConnector getConnector();

  /**
   * Returns the channel index of the associated {@link IChannel} within its
   * connector.
   */
  public short getChannelIndex();
}
