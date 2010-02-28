/***************************************************************************
 * Copyright (c) 2004 - 2010 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Simon McDuff - bug 230832
 **************************************************************************/
package org.eclipse.emf.cdo.internal.net4j.protocol;

import org.eclipse.emf.cdo.common.CDOCommonRepository;
import org.eclipse.emf.cdo.common.CDOCommonSession.Options.PassiveUpdateMode;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.io.CDODataInput;
import org.eclipse.emf.cdo.common.io.CDODataOutput;
import org.eclipse.emf.cdo.common.model.CDOPackageUnit;
import org.eclipse.emf.cdo.common.protocol.CDOProtocolConstants;
import org.eclipse.emf.cdo.common.util.CDOCommonUtil;
import org.eclipse.emf.cdo.internal.net4j.bundle.OM;
import org.eclipse.emf.cdo.internal.net4j.messages.Messages;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageUnit;
import org.eclipse.emf.cdo.util.ServerException;

import org.eclipse.net4j.util.om.trace.ContextTracer;

import org.eclipse.emf.spi.cdo.CDOSessionProtocol.OpenSessionResult;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author Eike Stepper
 */
public class OpenSessionRequest extends CDOTimeRequest<OpenSessionResult>
{
  private static final ContextTracer TRACER = new ContextTracer(OM.DEBUG_PROTOCOL, OpenSessionRequest.class);

  private String repositoryName;

  private boolean passiveUpdateEnabled;

  private PassiveUpdateMode passiveUpdateMode;

  private OpenSessionResult result;

  public OpenSessionRequest(CDOClientProtocol protocol, String repositoryName, boolean passiveUpdateEnabled,
      PassiveUpdateMode passiveUpdateMode)
  {
    super(protocol, CDOProtocolConstants.SIGNAL_OPEN_SESSION);
    this.repositoryName = repositoryName;
    this.passiveUpdateEnabled = passiveUpdateEnabled;
    this.passiveUpdateMode = passiveUpdateMode;
  }

  @Override
  protected void requesting(CDODataOutput out) throws IOException
  {
    super.requesting(out);
    if (TRACER.isEnabled())
    {
      TRACER.format("Writing repositoryName: {0}", repositoryName); //$NON-NLS-1$
    }

    out.writeString(repositoryName);

    if (TRACER.isEnabled())
    {
      TRACER.format("Writing passiveUpdateEnabled: {0}", passiveUpdateEnabled); //$NON-NLS-1$
    }

    out.writeBoolean(passiveUpdateEnabled);

    if (TRACER.isEnabled())
    {
      TRACER.format("Writing passiveUpdateMode: {0}", passiveUpdateMode); //$NON-NLS-1$
    }

    out.writeEnum(passiveUpdateMode);
  }

  @Override
  protected OpenSessionResult confirming(CDODataInput in) throws IOException
  {
    int sessionID = in.readInt();
    if (sessionID == CDOProtocolConstants.ERROR_REPOSITORY_NOT_FOUND)
    {
      String msg = MessageFormat.format(Messages.getString("OpenSessionRequest.0"), repositoryName); //$NON-NLS-1$
      throw new ServerException(msg);
    }

    if (sessionID == CDOProtocolConstants.ERROR_NO_SESSION)
    {
      String msg = MessageFormat.format(Messages.getString("OpenSessionRequest.3"), repositoryName); //$NON-NLS-1$
      throw new ServerException(msg);
    }

    if (TRACER.isEnabled())
    {
      TRACER.format("Read sessionID: {0}", sessionID); //$NON-NLS-1$
    }

    String repositoryUUID = in.readString();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositoryUUID: {0}", repositoryUUID); //$NON-NLS-1$
    }

    CDOCommonRepository.Type repositoryType = in.readEnum(CDOCommonRepository.Type.class);
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositoryType: {0}", repositoryType); //$NON-NLS-1$
    }

    CDOCommonRepository.State repositoryState = in.readEnum(CDOCommonRepository.State.class);
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositoryState: {0}", repositoryState); //$NON-NLS-1$
    }

    long repositoryCreationTime = in.readLong();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositoryCreationTime: {0}", CDOCommonUtil.formatTimeStamp(repositoryCreationTime)); //$NON-NLS-1$
    }

    long lastUpdateTime = in.readLong();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read lastUpdateTime: {0}", CDOCommonUtil.formatTimeStamp(lastUpdateTime)); //$NON-NLS-1$
    }

    CDOID rootResourceID = in.readCDOID();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read rootResourceID: {0}", rootResourceID); //$NON-NLS-1$
    }

    boolean repositorySupportingAudits = in.readBoolean();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositorySupportingAudits: {0}", repositorySupportingAudits); //$NON-NLS-1$
    }

    boolean repositorySupportingBranches = in.readBoolean();
    if (TRACER.isEnabled())
    {
      TRACER.format("Read repositorySupportingBranches: {0}", repositorySupportingBranches); //$NON-NLS-1$
    }

    result = new OpenSessionResult(sessionID, repositoryUUID, repositoryType, repositoryState, repositoryCreationTime,
        lastUpdateTime, rootResourceID, repositorySupportingAudits, repositorySupportingBranches);

    CDOPackageUnit[] packageUnits = in.readCDOPackageUnits(null);
    for (int i = 0; i < packageUnits.length; i++)
    {
      result.getPackageUnits().add((InternalCDOPackageUnit)packageUnits[i]);
    }

    super.confirming(in);
    result.setRepositoryTimeResult(getRepositoryTimeResult());
    return result;
  }
}
