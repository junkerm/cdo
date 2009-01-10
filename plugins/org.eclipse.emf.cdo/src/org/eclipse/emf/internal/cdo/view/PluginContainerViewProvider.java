/***************************************************************************
 * Copyright (c) 2004 - 2008 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Victor Roldan Betancort - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.internal.cdo.view;

import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.util.CDOURIUtil;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.cdo.view.CDOViewProvider;
import org.eclipse.emf.cdo.view.CDOViewSet;
import org.eclipse.emf.cdo.view.ManagedContainerViewProvider;

import org.eclipse.emf.internal.cdo.session.CDOSessionFactory;

import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.container.IPluginContainer;

import org.eclipse.emf.common.util.URI;

/**
 * Provides <code>CDOView</code> from <code>CDOSession</code> registered in IPluginContainer
 * 
 * @author Victor Roldan Betancort
 */
public class PluginContainerViewProvider extends ManagedContainerViewProvider implements CDOViewProvider
{
  private final static String REGEX = "cdo:.*";

  private final static int PRIORITY = 400;

  public PluginContainerViewProvider()
  {
    super(IPluginContainer.INSTANCE, REGEX, PRIORITY);
  }

  public CDOView getView(URI uri, CDOViewSet viewSet)
  {
    IManagedContainer container = getContainer();
    if (container == null)
    {
      return null;
    }

    String repoUUID = CDOURIUtil.extractRepositoryUUID(uri);
    for (Object element : container.getElements(CDOSessionFactory.PRODUCT_GROUP))
    {
      CDOSession session = (CDOSession)element;
      String uuid = session.repository().getUUID();
      if (repoUUID.equals(uuid))
      {
        CDOView view = openView(session, uri);
        if (view != null)
        {
          return view;
        }
      }
    }

    return null;
  }

  @Override
  protected IManagedContainer getContainer()
  {
    return IPluginContainer.INSTANCE;
  }

  protected CDOView openView(CDOSession session, URI uri)
  {
    return session.openTransaction();
  }
}
