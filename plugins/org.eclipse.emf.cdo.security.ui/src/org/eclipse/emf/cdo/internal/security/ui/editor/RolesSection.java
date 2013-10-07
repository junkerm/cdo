/*
 * Copyright (c) 2004-2013 Eike Stepper (Berlin, Germany), CEA LIST, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Christian W. Damus (CEA LIST) - initial API and implementation
 */
package org.eclipse.emf.cdo.internal.security.ui.editor;

import org.eclipse.emf.cdo.internal.security.ui.messages.Messages;
import org.eclipse.emf.cdo.security.Role;
import org.eclipse.emf.cdo.security.SecurityPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * 
 */
public class RolesSection extends TableSection<Role>
{

  public RolesSection(EditingDomain domain, AdapterFactory adapterFactory)
  {
    super(Role.class, SecurityPackage.Literals.ROLE, domain, adapterFactory);
  }

  @Override
  protected String getTitle()
  {
    return Messages.RolesSection_0;
  }
}
