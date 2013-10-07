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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;

import java.util.Map;

/**
 * 
 */
public class EClassDetailsPageProvider implements IDetailsPageProvider
{

  private final Map<EClass, IDetailsPage> pages = new java.util.HashMap<EClass, IDetailsPage>();

  private EClassDetailsPageProvider()
  {
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public Object getPageKey(Object object)
  {
    return object instanceof EObject ? ((EObject)object).eClass() : null;
  }

  public IDetailsPage getPage(Object key)
  {
    return pages.get(key);
  }

  //
  // Nested types
  //

  public static class Builder
  {
    private final Map<EClass, IDetailsPage> pages = new java.util.HashMap<EClass, IDetailsPage>();

    private Builder()
    {
    }

    public Builder page(EClass eclass, IDetailsPage page)
    {
      pages.put(eclass, page);
      return this;
    }

    public EClassDetailsPageProvider build()
    {
      EClassDetailsPageProvider result = new EClassDetailsPageProvider();
      result.pages.putAll(pages);
      return result;
    }
  }
}
