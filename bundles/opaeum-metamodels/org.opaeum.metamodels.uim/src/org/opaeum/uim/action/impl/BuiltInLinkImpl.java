/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.Labels;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Built In Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.impl.BuiltInLinkImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.opaeum.uim.action.impl.BuiltInLinkImpl#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuiltInLinkImpl extends AbstractLinkImpl implements BuiltInLink {
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final BuiltInLinkKind KIND_EDEFAULT = BuiltInLinkKind.AUDIT_TRAIL;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected BuiltInLinkKind kind = KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLabels() <em>Labels</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabels()
	 * @generated
	 * @ordered
	 */
	protected Labels labels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionPackage.Literals.BUILT_IN_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInLinkKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(BuiltInLinkKind newKind) {
		BuiltInLinkKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.BUILT_IN_LINK__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabels(Labels newLabels, NotificationChain msgs) {
		Labels oldLabels = labels;
		labels = newLabels;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActionPackage.BUILT_IN_LINK__LABELS, oldLabels, newLabels);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabels(Labels newLabels) {
		if (newLabels != labels) {
			NotificationChain msgs = null;
			if (labels != null)
				msgs = ((InternalEObject)labels).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActionPackage.BUILT_IN_LINK__LABELS, null, msgs);
			if (newLabels != null)
				msgs = ((InternalEObject)newLabels).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActionPackage.BUILT_IN_LINK__LABELS, null, msgs);
			msgs = basicSetLabels(newLabels, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.BUILT_IN_LINK__LABELS, newLabels, newLabels));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ActionPackage.BUILT_IN_LINK__LABELS:
				return basicSetLabels(null, msgs);
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
			case ActionPackage.BUILT_IN_LINK__KIND:
				return getKind();
			case ActionPackage.BUILT_IN_LINK__LABELS:
				return getLabels();
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
			case ActionPackage.BUILT_IN_LINK__KIND:
				setKind((BuiltInLinkKind)newValue);
				return;
			case ActionPackage.BUILT_IN_LINK__LABELS:
				setLabels((Labels)newValue);
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
			case ActionPackage.BUILT_IN_LINK__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case ActionPackage.BUILT_IN_LINK__LABELS:
				setLabels((Labels)null);
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
			case ActionPackage.BUILT_IN_LINK__KIND:
				return kind != KIND_EDEFAULT;
			case ActionPackage.BUILT_IN_LINK__LABELS:
				return labels != null;
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(')');
		return result.toString();
	}

} //BuiltInLinkImpl
