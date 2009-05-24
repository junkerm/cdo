/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.spi.cdo;

import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDAndVersion;
import org.eclipse.emf.cdo.common.id.CDOIDObjectFactory;
import org.eclipse.emf.cdo.common.model.CDOPackageUnit;
import org.eclipse.emf.cdo.common.protocol.CDOAuthenticator;
import org.eclipse.emf.cdo.common.revision.delta.CDORevisionDelta;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageRegistry;

import org.eclipse.net4j.util.lifecycle.ILifecycle;

import java.util.Collection;
import java.util.Set;

/**
 * @author Eike Stepper
 * @since 2.0
 */
public interface InternalCDOSession extends CDOSession, CDOIDObjectFactory,
    InternalCDOPackageRegistry.PackageProcessor, InternalCDOPackageRegistry.PackageLoader, ILifecycle.Introspection
{
  public CDOSessionProtocol getSessionProtocol();

  public InternalCDOPackageRegistry getPackageRegistry();

  public void setPackageRegistry(InternalCDOPackageRegistry packageRegistry);

  public void setRepositoryName(String repositoryName);

  public CDOAuthenticator getAuthenticator();

  public void setAuthenticator(CDOAuthenticator authenticator);

  public void viewDetached(InternalCDOView view);

  public void handleCommitNotification(long timeStamp, Collection<CDOPackageUnit> newPackageUnits,
      Set<CDOIDAndVersion> dirtyOIDs, Collection<CDOID> detachedObjects, Collection<CDORevisionDelta> deltas,
      InternalCDOView excludedView);

  public void handleSyncResponse(long timestamp, Collection<CDOPackageUnit> newPackageUnits,
      Set<CDOIDAndVersion> dirtyOIDs, Collection<CDOID> detachedObjects);

  /**
   * In some cases we need to sync without propagating event. Lock is a good example.
   */
  public void handleUpdateRevision(final long timeStamp, Set<CDOIDAndVersion> dirtyOIDs,
      Collection<CDOID> detachedObjects);
}
