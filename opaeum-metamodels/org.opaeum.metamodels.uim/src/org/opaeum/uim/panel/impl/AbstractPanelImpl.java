/**
 */
package org.opaeum.uim.panel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.Labels;
import org.opaeum.uim.component.impl.UimContainerImpl;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.impl.AbstractPanelImpl#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractPanelImpl extends UimContainerImpl implements AbstractPanel {
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
	protected AbstractPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PanelPackage.Literals.ABSTRACT_PANEL;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PanelPackage.ABSTRACT_PANEL__LABELS, oldLabels, newLabels);
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
				msgs = ((InternalEObject)labels).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PanelPackage.ABSTRACT_PANEL__LABELS, null, msgs);
			if (newLabels != null)
				msgs = ((InternalEObject)newLabels).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PanelPackage.ABSTRACT_PANEL__LABELS, null, msgs);
			msgs = basicSetLabels(newLabels, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.ABSTRACT_PANEL__LABELS, newLabels, newLabels));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PanelPackage.ABSTRACT_PANEL__LABELS:
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
			case PanelPackage.ABSTRACT_PANEL__LABELS:
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
			case PanelPackage.ABSTRACT_PANEL__LABELS:
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
			case PanelPackage.ABSTRACT_PANEL__LABELS:
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
			case PanelPackage.ABSTRACT_PANEL__LABELS:
				return labels != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractPanelImpl
