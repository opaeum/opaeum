/**
 */
package org.opaeum.uim.component.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.Labels;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.impl.UserInteractionElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.impl.UimComponentImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimComponentImpl#getLabelOverride <em>Label Override</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class UimComponentImpl extends UserInteractionElementImpl implements UimComponent {
	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint visibility;

	/**
	 * The cached value of the '{@link #getLabelOverride() <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelOverride()
	 * @generated
	 * @ordered
	 */
	protected Labels labelOverride;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.UIM_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(UserInteractionConstraint newVisibility, NotificationChain msgs) {
		UserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_COMPONENT__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(UserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_COMPONENT__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_COMPONENT__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_COMPONENT__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabelOverride() {
		return labelOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelOverride(Labels newLabelOverride, NotificationChain msgs) {
		Labels oldLabelOverride = labelOverride;
		labelOverride = newLabelOverride;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE, oldLabelOverride, newLabelOverride);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelOverride(Labels newLabelOverride) {
		if (newLabelOverride != labelOverride) {
			NotificationChain msgs = null;
			if (labelOverride != null)
				msgs = ((InternalEObject)labelOverride).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE, null, msgs);
			if (newLabelOverride != null)
				msgs = ((InternalEObject)newLabelOverride).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE, null, msgs);
			msgs = basicSetLabelOverride(newLabelOverride, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE, newLabelOverride, newLabelOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer getParent() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.UIM_COMPONENT__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE:
				return basicSetLabelOverride(null, msgs);
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
			case ComponentPackage.UIM_COMPONENT__VISIBILITY:
				return getVisibility();
			case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE:
				return getLabelOverride();
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
			case ComponentPackage.UIM_COMPONENT__VISIBILITY:
				setVisibility((UserInteractionConstraint)newValue);
				return;
			case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
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
			case ComponentPackage.UIM_COMPONENT__VISIBILITY:
				setVisibility((UserInteractionConstraint)null);
				return;
			case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
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
			case ComponentPackage.UIM_COMPONENT__VISIBILITY:
				return visibility != null;
			case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE:
				return labelOverride != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ConstrainedObject.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.UIM_COMPONENT__VISIBILITY: return ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE: return UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY: return ComponentPackage.UIM_COMPONENT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (baseFeatureID) {
				case UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE: return ComponentPackage.UIM_COMPONENT__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //UimComponentImpl
