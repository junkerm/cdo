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
package org.eclipse.internal.net4j.transport;

import org.eclipse.net4j.transport.IConnector;

import org.eclipse.internal.net4j.bundle.Net4j;
import org.eclipse.internal.net4j.util.factory.Factory;

/**
 * @author Eike Stepper
 */
public abstract class ConnectorFactory<PRODUCT extends IConnector> extends Factory<PRODUCT>
{
  public static final String CONNECTOR_GROUP = Net4j.BUNDLE_ID + ".connectors";

  public ConnectorFactory(String type)
  {
    super(CONNECTOR_GROUP, type);
  }
}
