/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl;
import org.opaeum.uim.perspective.ExplorerConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Explorer Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl#isUnderUserControl <em>Under User Control</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl#getLabelOverride <em>Label Override</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExplorerConstraintImpl extends UserInteractionConstraintImpl implements ExplorerConstraint {
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNDER_USER_CONTROL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected boolean underUserControl = UNDER_USER_CONTROL_EDEFAULT;

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
	 * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.EXPLORER_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid) {
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnderUserControl() {
		return underUserControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderUserControl(boolean newUnderUserControl) {
		boolean oldUnderUserControl = underUserControl;
		underUserControl = newUnderUserControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL, oldUnderUserControl, underUserControl));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE, oldLabelOverride, newLabelOverride);
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
				msgs = ((InternalEObject)labelOverride).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE, null, msgs);
			if (newLabelOverride != null)
				msgs = ((InternalEObject)newLabelOverride).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE, null, msgs);
			msgs = basicSetLabelOverride(newLabelOverride, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE, newLabelOverride, newLabelOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.EXPLORER_CONSTRAINT__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE:
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
			case PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID:
				return getUmlElementUid();
			case PerspectivePackage.EXPLORER_CONSTRAINT__NAME:
				return getName();
			case PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL:
				return isUnderUserControl();
			case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE:
				return getLabelOverride();
			case PerspectivePackage.EXPLORER_CONSTRAINT__HIDDEN:
				return isHidden();
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
			case PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL:
				setUnderUserControl((Boolean)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__HIDDEN:
				setHidden((Boolean)newValue);
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
			case PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL:
				setUnderUserControl(UNDER_USER_CONTROL_EDEFAULT);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
				return;
			case PerspectivePackage.EXPLORER_CONSTRAINT__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
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
			case PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case PerspectivePackage.EXPLORER_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL:
				return underUserControl != UNDER_USER_CONTROL_EDEFAULT;
			case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE:
				return labelOverride != null;
			case PerspectivePackage.EXPLORER_CONSTRAINT__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
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
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case PerspectivePackage.EXPLORER_CONSTRAINT__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				case PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL: return UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (derivedFeatureID) {
				case PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE: return UimPackage.LABELED_ELEMENT__LABEL_OVERRIDE;
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
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return PerspectivePackage.EXPLORER_CONSTRAINT__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return PerspectivePackage.EXPLORER_CONSTRAINT__NAME;
				case UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL: return PerspectivePackage.EXPLORER_CONSTRAINT__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (baseFeatureID) {
				case UimPackage.LABELED_ELEMENT__LABEL_OVERRIDE: return PerspectivePackage.EXPLORER_CONSTRAINT__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(", name: ");
		result.append(name);
		result.append(", underUserControl: ");
		result.append(underUserControl);
		result.append(", hidden: ");
		result.append(hidden);
		result.append(')');
		return result.toString();
	}

} //ExplorerConstraintImpl
