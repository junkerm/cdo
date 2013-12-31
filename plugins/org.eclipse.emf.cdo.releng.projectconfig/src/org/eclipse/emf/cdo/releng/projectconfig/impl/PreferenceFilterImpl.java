/*
 * Copyright (c) 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.projectconfig.impl;

import org.eclipse.emf.cdo.releng.preferences.PreferenceNode;
import org.eclipse.emf.cdo.releng.preferences.Property;
import org.eclipse.emf.cdo.releng.projectconfig.PreferenceFilter;
import org.eclipse.emf.cdo.releng.projectconfig.PreferenceProfile;
import org.eclipse.emf.cdo.releng.projectconfig.ProjectConfigFactory;
import org.eclipse.emf.cdo.releng.projectconfig.ProjectConfigPackage;
import org.eclipse.emf.cdo.releng.projectconfig.WorkspaceConfiguration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Preference Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.releng.projectconfig.impl.PreferenceFilterImpl#getPreferenceNode <em>Preference Node</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.projectconfig.impl.PreferenceFilterImpl#getPreferenceProfile <em>Preference Profile</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.projectconfig.impl.PreferenceFilterImpl#getInclusions <em>Inclusions</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.projectconfig.impl.PreferenceFilterImpl#getExclusions <em>Exclusions</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.releng.projectconfig.impl.PreferenceFilterImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PreferenceFilterImpl extends MinimalEObjectImpl.Container implements PreferenceFilter
{
  /**
   * The cached value of the '{@link #getPreferenceNode() <em>Preference Node</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPreferenceNode()
   * @generated
   * @ordered
   */
  protected PreferenceNode preferenceNode;

  /**
   * The default value of the '{@link #getInclusions() <em>Inclusions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInclusions()
   * @generated
   * @ordered
   */
  protected static final Pattern INCLUSIONS_EDEFAULT = (Pattern)ProjectConfigFactory.eINSTANCE.createFromString(
      ProjectConfigPackage.eINSTANCE.getPattern(), ".*");

  /**
   * The cached value of the '{@link #getInclusions() <em>Inclusions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInclusions()
   * @generated
   * @ordered
   */
  protected Pattern inclusions = INCLUSIONS_EDEFAULT;

  /**
   * The default value of the '{@link #getExclusions() <em>Exclusions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExclusions()
   * @generated
   * @ordered
   */
  protected static final Pattern EXCLUSIONS_EDEFAULT = (Pattern)ProjectConfigFactory.eINSTANCE.createFromString(
      ProjectConfigPackage.eINSTANCE.getPattern(), "");

  /**
   * The cached value of the '{@link #getExclusions() <em>Exclusions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExclusions()
   * @generated
   * @ordered
   */
  protected Pattern exclusions = EXCLUSIONS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PreferenceFilterImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ProjectConfigPackage.Literals.PREFERENCE_FILTER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreferenceNode getPreferenceNode()
  {
    if (preferenceNode != null && preferenceNode.eIsProxy())
    {
      InternalEObject oldPreferenceNode = (InternalEObject)preferenceNode;
      preferenceNode = (PreferenceNode)eResolveProxy(oldPreferenceNode);
      if (preferenceNode != oldPreferenceNode)
      {
        if (eNotificationRequired())
        {
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE, oldPreferenceNode, preferenceNode));
        }
      }
    }
    return preferenceNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreferenceNode basicGetPreferenceNode()
  {
    return preferenceNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreferenceNode(PreferenceNode newPreferenceNode)
  {
    PreferenceNode oldPreferenceNode = preferenceNode;
    preferenceNode = newPreferenceNode;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE,
          oldPreferenceNode, preferenceNode));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreferenceProfile getPreferenceProfile()
  {
    if (eContainerFeatureID() != ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE)
    {
      return null;
    }
    return (PreferenceProfile)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPreferenceProfile(PreferenceProfile newPreferenceProfile, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newPreferenceProfile,
        ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreferenceProfile(PreferenceProfile newPreferenceProfile)
  {
    if (newPreferenceProfile != eInternalContainer()
        || eContainerFeatureID() != ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE
        && newPreferenceProfile != null)
    {
      if (EcoreUtil.isAncestor(this, newPreferenceProfile))
      {
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      }
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
      {
        msgs = eBasicRemoveFromContainer(msgs);
      }
      if (newPreferenceProfile != null)
      {
        msgs = ((InternalEObject)newPreferenceProfile).eInverseAdd(this,
            ProjectConfigPackage.PREFERENCE_PROFILE__PREFERENCE_FILTERS, PreferenceProfile.class, msgs);
      }
      msgs = basicSetPreferenceProfile(newPreferenceProfile, msgs);
      if (msgs != null)
      {
        msgs.dispatch();
      }
    }
    else if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE,
          newPreferenceProfile, newPreferenceProfile));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Pattern getInclusions()
  {
    return inclusions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInclusions(Pattern newInclusions)
  {
    Pattern oldInclusions = inclusions;
    inclusions = newInclusions;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectConfigPackage.PREFERENCE_FILTER__INCLUSIONS,
          oldInclusions, inclusions));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Pattern getExclusions()
  {
    return exclusions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExclusions(Pattern newExclusions)
  {
    Pattern oldExclusions = exclusions;
    exclusions = newExclusions;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectConfigPackage.PREFERENCE_FILTER__EXCLUSIONS,
          oldExclusions, exclusions));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<Property> getProperties()
  {
    WorkspaceConfiguration workspaceConfiguration = getPreferenceProfile().getProject().getConfiguration();
    List<Property> properties = new ArrayList<Property>();
    for (Property property : getPreferenceNode().getProperties())
    {
      if (matches(property.getName()) && !workspaceConfiguration.isOmitted(property))
      {
        properties.add(property);
      }
    }

    int size = properties.size();
    return new EcoreEList.UnmodifiableEList<Property>(this,
        ProjectConfigPackage.Literals.PREFERENCE_FILTER__PROPERTIES, size, properties.toArray(new Property[size]));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean matches(String value)
  {
    return getInclusions().matcher(value).matches() && !getExclusions().matcher(value).matches();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      if (eInternalContainer() != null)
      {
        msgs = eBasicRemoveFromContainer(msgs);
      }
      return basicSetPreferenceProfile((PreferenceProfile)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      return basicSetPreferenceProfile(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      return eInternalContainer().eInverseRemove(this, ProjectConfigPackage.PREFERENCE_PROFILE__PREFERENCE_FILTERS,
          PreferenceProfile.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE:
      if (resolve)
      {
        return getPreferenceNode();
      }
      return basicGetPreferenceNode();
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      return getPreferenceProfile();
    case ProjectConfigPackage.PREFERENCE_FILTER__INCLUSIONS:
      return getInclusions();
    case ProjectConfigPackage.PREFERENCE_FILTER__EXCLUSIONS:
      return getExclusions();
    case ProjectConfigPackage.PREFERENCE_FILTER__PROPERTIES:
      return getProperties();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE:
      setPreferenceNode((PreferenceNode)newValue);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      setPreferenceProfile((PreferenceProfile)newValue);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__INCLUSIONS:
      setInclusions((Pattern)newValue);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__EXCLUSIONS:
      setExclusions((Pattern)newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE:
      setPreferenceNode((PreferenceNode)null);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      setPreferenceProfile((PreferenceProfile)null);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__INCLUSIONS:
      setInclusions(INCLUSIONS_EDEFAULT);
      return;
    case ProjectConfigPackage.PREFERENCE_FILTER__EXCLUSIONS:
      setExclusions(EXCLUSIONS_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_NODE:
      return preferenceNode != null;
    case ProjectConfigPackage.PREFERENCE_FILTER__PREFERENCE_PROFILE:
      return getPreferenceProfile() != null;
    case ProjectConfigPackage.PREFERENCE_FILTER__INCLUSIONS:
      return INCLUSIONS_EDEFAULT == null ? inclusions != null : !INCLUSIONS_EDEFAULT.equals(inclusions);
    case ProjectConfigPackage.PREFERENCE_FILTER__EXCLUSIONS:
      return EXCLUSIONS_EDEFAULT == null ? exclusions != null : !EXCLUSIONS_EDEFAULT.equals(exclusions);
    case ProjectConfigPackage.PREFERENCE_FILTER__PROPERTIES:
      return !getProperties().isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
  {
    switch (operationID)
    {
    case ProjectConfigPackage.PREFERENCE_FILTER___MATCHES__STRING:
      return matches((String)arguments.get(0));
    }
    return super.eInvoke(operationID, arguments);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy())
    {
      return super.toString();
    }

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (inclusions: ");
    result.append(inclusions);
    result.append(", exclusions: ");
    result.append(exclusions);
    result.append(')');
    return result.toString();
  }

} // PreferenceFilterImpl
