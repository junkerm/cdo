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
package org.eclipse.emf.cdo.spi.common.revision;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.branch.CDOBranchPoint;
import org.eclipse.emf.cdo.common.branch.CDOBranchVersion;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.io.CDODataInput;
import org.eclipse.emf.cdo.common.io.CDODataOutput;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.revision.CDORevisionFactory;
import org.eclipse.emf.cdo.common.revision.CDORevisionManager;
import org.eclipse.emf.cdo.common.revision.cache.CDORevisionCache;

import org.eclipse.net4j.util.lifecycle.ILifecycle;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Eike Stepper
 * @since 3.0
 */
public interface InternalCDORevisionManager extends CDORevisionManager, ILifecycle
{
  public boolean isSupportingBranches();

  public void setSupportingBranches(boolean on);

  public RevisionLoader getRevisionLoader();

  public void setRevisionLoader(RevisionLoader revisionLoader);

  public RevisionLocker getRevisionLocker();

  public void setRevisionLocker(RevisionLocker revisionLocker);

  public CDORevisionFactory getFactory();

  public void setFactory(CDORevisionFactory factory);

  public CDORevisionCache getCache();

  public void setCache(CDORevisionCache cache);

  public void reviseLatest(CDOID id, CDOBranch branch);

  public void reviseVersion(CDOID id, CDOBranchVersion branchVersion, long timeStamp);

  /**
   * @author Eike Stepper
   * @since 3.0
   */
  public interface RevisionLoader
  {
    public List<InternalCDORevision> loadRevisions(Collection<MissingRevisionInfo> infos, CDOBranchPoint branchPoint,
        int referenceChunk, int prefetchDepth);

    public InternalCDORevision loadRevisionByVersion(CDOID id, CDOBranchVersion branchVersion, int referenceChunk);

    /**
     * @author Eike Stepper
     * @since 3.0
     */
    public interface MissingRevisionInfo
    {
      public Type getType();

      public CDOID getID();

      public void write(CDODataOutput out) throws IOException;

      public InternalCDORevision execute(CDORevisionManager revisionManager, CDOBranchPoint branchPoint,
          int referenceChunk);

      public void writeResult(CDODataOutput out, CDORevision revision, int referenceChunk) throws IOException;

      public InternalCDORevision readResult(CDODataInput in) throws IOException;

      /**
       * @author Eike Stepper
       * @since 3.0
       */
      public static enum Type
      {
        /**
         * Indicates that the revision must be normally loaded by {@link MissingRevisionInfo#getID() ID} starting from
         * the branch point of the request/view, possibly recursing up to the main branch. If the requested revision
         * exists, it will be loaded.
         */
        MISSING,

        /**
         * Indicates that the revision must be normally loaded by {@link MissingRevisionInfo#getID() ID} starting from
         * the <b>base</b> of the branch point of the request/view, possibly recursing up to the given
         * {@link MissingRevisionInfo#getBranchVersion() branch version} which is already available to the requestor.If
         * the requested revision exists and is <b>different</b> from the one that is possibly available, it will be
         * loaded.
         */
        POSSIBLY_AVAILABLE,

        /**
         * Indicates that the requestor already knows the exact {@link MissingRevisionInfo#getBranchVersion() branch
         * version}. If the requested revision exists, it will be loaded.
         */
        EXACTLY_KNOWN
      }

      /**
       * @author Eike Stepper
       * @since 3.0
       */
      public static class Missing implements MissingRevisionInfo
      {
        private CDOID id;

        public Missing(CDOID id)
        {
          this.id = id;
        }

        Missing(CDODataInput in) throws IOException
        {
          id = in.readCDOID();
        }

        public Type getType()
        {
          return Type.MISSING;
        }

        public CDOID getID()
        {
          return id;
        }

        public void write(CDODataOutput out) throws IOException
        {
          out.writeByte(getType().ordinal());
          out.writeCDOID(id);
        }

        public InternalCDORevision execute(CDORevisionManager revisionManager, CDOBranchPoint branchPoint,
            int referenceChunk)
        {
          return (InternalCDORevision)revisionManager.getRevision(id, branchPoint, referenceChunk,
              CDORevision.DEPTH_NONE, true);
        }

        public void writeResult(CDODataOutput out, CDORevision revision, int referenceChunk) throws IOException
        {
          out.writeCDORevision(revision, referenceChunk);
        }

        public InternalCDORevision readResult(CDODataInput in) throws IOException
        {
          return (InternalCDORevision)in.readCDORevision();
        }

        @Override
        public String toString()
        {
          return id.toString();
        }

        public static MissingRevisionInfo read(CDODataInput in) throws IOException
        {
          byte ordinal = in.readByte();
          switch (Type.values()[ordinal])
          {
          case MISSING:
            return new Missing(in);
          case POSSIBLY_AVAILABLE:
            return new PossiblyAvailable(in);
          case EXACTLY_KNOWN:
            return new ExactlyKnown(in);
          default:
            throw new IOException(); // Can not happen
          }
        }
      }

      /**
       * @author Eike Stepper
       * @since 3.0
       */
      public static class PossiblyAvailable extends Missing
      {
        private CDOBranchVersion available;

        public PossiblyAvailable(CDOID id, InternalCDORevision available)
        {
          super(id);
          this.available = available;
        }

        PossiblyAvailable(CDODataInput in) throws IOException
        {
          super(in);
          available = in.readCDOBranchVersion();
        }

        @Override
        public Type getType()
        {
          return Type.POSSIBLY_AVAILABLE;
        }

        @Override
        public void write(CDODataOutput out) throws IOException
        {
          super.write(out);
          out.writeCDOBranchVersion(available);
        }

        @Override
        public InternalCDORevision readResult(CDODataInput in) throws IOException
        {
          boolean useAvailable = in.readBoolean();
          if (useAvailable)
          {
            return (InternalCDORevision)available;
          }

          return super.readResult(in);
        }
      }

      /**
       * @author Eike Stepper
       * @since 3.0
       */
      public static class ExactlyKnown extends Missing
      {
        private CDOBranchVersion branchVersion;

        public ExactlyKnown(CDOID id, CDOBranchVersion branchVersion)
        {
          super(id);
          this.branchVersion = branchVersion;
        }

        ExactlyKnown(CDODataInput in) throws IOException
        {
          super(in);
          branchVersion = in.readCDOBranchVersion();
        }

        @Override
        public Type getType()
        {
          return Type.EXACTLY_KNOWN;
        }

        @Override
        public void write(CDODataOutput out) throws IOException
        {
          super.write(out);
          out.writeCDOBranchVersion(branchVersion);
        }
      }
    }
  }

  /**
   * @author Eike Stepper
   * @since 3.0
   */
  public interface RevisionLocker
  {
    public void acquireAtomicRequestLock(Object key);

    public void releaseAtomicRequestLock(Object key);
  }
}
