/**
 */
package org.opaeum.uim.control.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimControl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.impl.UimControlImpl#getMimumWidth <em>Mimum Width</em>}</li>
 *   <li>{@link org.opaeum.uim.control.impl.UimControlImpl#getField <em>Field</em>}</li>
 *   <li>{@link org.opaeum.uim.control.impl.UimControlImpl#getMinimumHeight <em>Minimum Height</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimControlImpl extends EObjectImpl implements UimControl {
	/**
	 * The default value of the '{@link #getMimumWidth() <em>Mimum Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMimumWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String MIMUM_WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMimumWidth() <em>Mimum Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMimumWidth()
	 * @generated
	 * @ordered
	 */
	protected String mimumWidth = MIMUM_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumHeight() <em>Minimum Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MINIMUM_HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinimumHeight() <em>Minimum Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumHeight()
	 * @generated
	 * @ordered
	 */
	protected Integer minimumHeight = MINIMUM_HEIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.UIM_CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMimumWidth() {
		return mimumWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMimumWidth(String newMimumWidth) {
		String oldMimumWidth = mimumWidth;
		mimumWidth = newMimumWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_CONTROL__MIMUM_WIDTH, oldMimumWidth, mimumWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimField getField() {
		if (eContainerFeatureID() != ControlPackage.UIM_CONTROL__FIELD) return null;
		return (UimField)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetField(UimField newField, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newField, ControlPackage.UIM_CONTROL__FIELD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(UimField newField) {
		if (newField != eInternalContainer() || (eContainerFeatureID() != ControlPackage.UIM_CONTROL__FIELD && newField != null)) {
			if (EcoreUtil.isAncestor(this, newField))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newField != null)
				msgs = ((InternalEObject)newField).eInverseAdd(this, UimPackage.UIM_FIELD__CONTROL, UimField.class, msgs);
			msgs = basicSetField(newField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_CONTROL__FIELD, newField, newField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getMinimumHeight() {
		return minimumHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumHeight(Integer newMinimumHeight) {
		Integer oldMinimumHeight = minimumHeight;
		minimumHeight = newMinimumHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_CONTROL__MINIMUM_HEIGHT, oldMinimumHeight, minimumHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ControlPackage.UIM_CONTROL__FIELD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetField((UimField)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ControlPackage.UIM_CONTROL__FIELD:
				return basicSetField(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ControlPackage.UIM_CONTROL__FIELD:
				return eInternalContainer().eInverseRemove(this, UimPackage.UIM_FIELD__CONTROL, UimField.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.UIM_CONTROL__MIMUM_WIDTH:
				return getMimumWidth();
			case ControlPackage.UIM_CONTROL__FIELD:
				return getField();
			case ControlPackage.UIM_CONTROL__MINIMUM_HEIGHT:
				return getMinimumHeight();
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
			case ControlPackage.UIM_CONTROL__MIMUM_WIDTH:
				setMimumWidth((String)newValue);
				return;
			case ControlPackage.UIM_CONTROL__FIELD:
				setField((UimField)newValue);
				return;
			case ControlPackage.UIM_CONTROL__MINIMUM_HEIGHT:
				setMinimumHeight((Integer)newValue);
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
			case ControlPackage.UIM_CONTROL__MIMUM_WIDTH:
				setMimumWidth(MIMUM_WIDTH_EDEFAULT);
				return;
			case ControlPackage.UIM_CONTROL__FIELD:
				setField((UimField)null);
				return;
			case ControlPackage.UIM_CONTROL__MINIMUM_HEIGHT:
				setMinimumHeight(MINIMUM_HEIGHT_EDEFAULT);
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
			case ControlPackage.UIM_CONTROL__MIMUM_WIDTH:
				return MIMUM_WIDTH_EDEFAULT == null ? mimumWidth != null : !MIMUM_WIDTH_EDEFAULT.equals(mimumWidth);
			case ControlPackage.UIM_CONTROL__FIELD:
				return getField() != null;
			case ControlPackage.UIM_CONTROL__MINIMUM_HEIGHT:
				return MINIMUM_HEIGHT_EDEFAULT == null ? minimumHeight != null : !MINIMUM_HEIGHT_EDEFAULT.equals(minimumHeight);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mimumWidth: ");
		result.append(mimumWidth);
		result.append(", minimumHeight: ");
		result.append(minimumHeight);
		result.append(')');
		return result.toString();
	}

} //UimControlImpl
