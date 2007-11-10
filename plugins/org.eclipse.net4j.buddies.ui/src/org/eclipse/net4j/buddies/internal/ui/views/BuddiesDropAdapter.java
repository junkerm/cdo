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
package org.eclipse.net4j.buddies.internal.ui.views;

import org.eclipse.net4j.buddies.IBuddyCollaboration;
import org.eclipse.net4j.buddies.internal.ui.dnd.BuddiesTransfer;
import org.eclipse.net4j.buddies.protocol.IBuddy;
import org.eclipse.net4j.util.ui.dnd.DNDDropAdapter;

import org.eclipse.jface.viewers.StructuredViewer;

/**
 * @author Eike Stepper
 */
public class BuddiesDropAdapter extends DNDDropAdapter<IBuddy[]>
{
  public BuddiesDropAdapter(StructuredViewer viewer)
  {
    super(BuddiesTransfer.INSTANCE, viewer);
  }

  @Override
  protected boolean performDrop(IBuddy[] buddies, Object target)
  {
    IBuddyCollaboration collaboration = (IBuddyCollaboration)target;
    collaboration.invite(buddies);
    return true;
  }

  @Override
  protected boolean validateTarget(Object target, int operation)
  {
    return true;// target instanceof IBuddyCollaboration;
  }
}