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
package org.eclipse.internal.net4j.util.registry;

import org.eclipse.net4j.util.registry.IRegistry;
import org.eclipse.net4j.util.registry.IRegistryEvent;

import org.eclipse.internal.net4j.util.container.ContainerEvent;

import java.util.Map;

/**
 * @author Eike Stepper
 */
public class RegistryEvent<K, V> extends ContainerEvent<Map.Entry<K, V>> implements IRegistryEvent<K, V>
{
  private static final long serialVersionUID = 1L;

  public RegistryEvent(IRegistry registry)
  {
    super(registry);
  }

  public IRegistry getRegistry()
  {
    return (IRegistry)getContainer();
  }
}