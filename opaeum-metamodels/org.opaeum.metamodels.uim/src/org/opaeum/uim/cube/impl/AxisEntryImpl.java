/**
 */
package org.opaeum.uim.cube.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.LevelProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Axis Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.impl.AxisEntryImpl#getDimensionBinding <em>Dimension Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.AxisEntryImpl#getLevelProperty <em>Level Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AxisEntryImpl extends EObjectImpl implements AxisEntry {
	/**
	 * The cached value of the '{@link #getDimensionBinding() <em>Dimension Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionBinding()
	 * @generated
	 * @ordered
	 */
	protected DimensionBinding dimensionBinding;

	/**
	 * The cached value of the '{@link #getLevelProperty() <em>Level Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelProperty()
	 * @generated
	 * @ordered
	 */
	protected LevelProperty levelProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AxisEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.AXIS_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionBinding getDimensionBinding() {
		return dimensionBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimensionBinding(DimensionBinding newDimensionBinding, NotificationChain msgs) {
		DimensionBinding oldDimensionBinding = dimensionBinding;
		dimensionBinding = newDimensionBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.AXIS_ENTRY__DIMENSION_BINDING, oldDimensionBinding, newDimensionBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensionBinding(DimensionBinding newDimensionBinding) {
		if (newDimensionBinding != dimensionBinding) {
			NotificationChain msgs = null;
			if (dimensionBinding != null)
				msgs = ((InternalEObject)dimensionBinding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.AXIS_ENTRY__DIMENSION_BINDING, null, msgs);
			if (newDimensionBinding != null)
				msgs = ((InternalEObject)newDimensionBinding).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.AXIS_ENTRY__DIMENSION_BINDING, null, msgs);
			msgs = basicSetDimensionBinding(newDimensionBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.AXIS_ENTRY__DIMENSION_BINDING, newDimensionBinding, newDimensionBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LevelProperty getLevelProperty() {
		return levelProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLevelProperty(LevelProperty newLevelProperty, NotificationChain msgs) {
		LevelProperty oldLevelProperty = levelProperty;
		levelProperty = newLevelProperty;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.AXIS_ENTRY__LEVEL_PROPERTY, oldLevelProperty, newLevelProperty);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelProperty(LevelProperty newLevelProperty) {
		if (newLevelProperty != levelProperty) {
			NotificationChain msgs = null;
			if (levelProperty != null)
				msgs = ((InternalEObject)levelProperty).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.AXIS_ENTRY__LEVEL_PROPERTY, null, msgs);
			if (newLevelProperty != null)
				msgs = ((InternalEObject)newLevelProperty).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.AXIS_ENTRY__LEVEL_PROPERTY, null, msgs);
			msgs = basicSetLevelProperty(newLevelProperty, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.AXIS_ENTRY__LEVEL_PROPERTY, newLevelProperty, newLevelProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				return basicSetDimensionBinding(null, msgs);
			case CubePackage.AXIS_ENTRY__LEVEL_PROPERTY:
				return basicSetLevelProperty(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				return getDimensionBinding();
			case CubePackage.AXIS_ENTRY__LEVEL_PROPERTY:
				return getLevelProperty();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				setDimensionBinding((DimensionBinding)newValue);
				return;
			case CubePackage.AXIS_ENTRY__LEVEL_PROPERTY:
				setLevelProperty((LevelProperty)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				setDimensionBinding((DimensionBinding)null);
				return;
			case CubePackage.AXIS_ENTRY__LEVEL_PROPERTY:
				setLevelProperty((LevelProperty)null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				return dimensionBinding != null;
			case CubePackage.AXIS_ENTRY__LEVEL_PROPERTY:
				return levelProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //AxisEntryImpl
