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

import org.eclipse.internal.net4j.bundle.PluginTransportContainer;

/**
 * @author Eike Stepper
 * @since 0.8.0
 */
public interface IPluginTransportContainer extends ITransportContainer
{
  public static final ITransportContainer INSTANCE = PluginTransportContainer.getInstance();
}
