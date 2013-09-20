/**
 */
package org.eclipse.emf.cdo.expressions.impl;

import org.eclipse.emf.cdo.expressions.BooleanValue;
import org.eclipse.emf.cdo.expressions.ExpressionsPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.expressions.impl.BooleanValueImpl#isLiteral <em>Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanValueImpl extends ValueImpl implements BooleanValue
{
  /**
   * The default value of the '{@link #isLiteral() <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLiteral()
   * @generated
   * @ordered
   */
  protected static final boolean LITERAL_EDEFAULT = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanValueImpl()
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
    return ExpressionsPackage.Literals.BOOLEAN_VALUE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isLiteral()
  {
    return (Boolean)eDynamicGet(ExpressionsPackage.BOOLEAN_VALUE__LITERAL,
        ExpressionsPackage.Literals.BOOLEAN_VALUE__LITERAL, true, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLiteral(boolean newLiteral)
  {
    eDynamicSet(ExpressionsPackage.BOOLEAN_VALUE__LITERAL, ExpressionsPackage.Literals.BOOLEAN_VALUE__LITERAL,
        newLiteral);
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
    case ExpressionsPackage.BOOLEAN_VALUE__LITERAL:
      return isLiteral();
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
    case ExpressionsPackage.BOOLEAN_VALUE__LITERAL:
      setLiteral((Boolean)newValue);
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
    case ExpressionsPackage.BOOLEAN_VALUE__LITERAL:
      setLiteral(LITERAL_EDEFAULT);
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
    case ExpressionsPackage.BOOLEAN_VALUE__LITERAL:
      return isLiteral() != LITERAL_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public Object getLiteral()
  {
    return isLiteral();
  }

} // BooleanValueImpl