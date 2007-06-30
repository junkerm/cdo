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
package org.eclipse.emf.internal.cdo;

import org.eclipse.net4j.IConnector;
import org.eclipse.net4j.util.container.IElementProcessor;
import org.eclipse.net4j.util.container.IManagedContainer;

import org.eclipse.emf.common.util.URI;

import org.eclipse.internal.net4j.ConnectorFactory;

/**
 * @author Eike Stepper
 */
public class ChannelInjector implements IElementProcessor
{
  public ChannelInjector()
  {
  }

  public Object process(IManagedContainer container, String productGroup, String factoryType, String description,
      Object element)
  {
    if (element instanceof CDOSessionImpl)
    {
      CDOSessionImpl session = (CDOSessionImpl)element;
      if (session.getConnector() == null)
      {
        session.setConnector(getConnector(container, description));
      }
    }

    return element;
  }

  protected IConnector getConnector(IManagedContainer container, String description)
  {
    URI uri = URI.createURI(description);
    String factoryType = uri.scheme();

    String connectorDescription = uri.authority();
    return (IConnector)container.getElement(ConnectorFactory.CONNECTOR_GROUP, factoryType, connectorDescription);
  }
}
