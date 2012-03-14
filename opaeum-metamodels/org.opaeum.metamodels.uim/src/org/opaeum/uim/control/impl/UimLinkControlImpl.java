/**
 */
package org.opaeum.uim.control.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.editor.ClassEditor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Link Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.impl.UimLinkControlImpl#getEditorToOpen <em>Editor To Open</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimLinkControlImpl extends UimControlImpl implements UimLinkControl {
	/**
	 * The cached value of the '{@link #getEditorToOpen() <em>Editor To Open</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorToOpen()
	 * @generated
	 * @ordered
	 */
	protected ClassEditor editorToOpen;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimLinkControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.UIM_LINK_CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassEditor getEditorToOpen() {
		if (editorToOpen != null && editorToOpen.eIsProxy()) {
			InternalEObject oldEditorToOpen = (InternalEObject)editorToOpen;
			editorToOpen = (ClassEditor)eResolveProxy(oldEditorToOpen);
			if (editorToOpen != oldEditorToOpen) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN, oldEditorToOpen, editorToOpen));
			}
		}
		return editorToOpen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassEditor basicGetEditorToOpen() {
		return editorToOpen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorToOpen(ClassEditor newEditorToOpen) {
		ClassEditor oldEditorToOpen = editorToOpen;
		editorToOpen = newEditorToOpen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN, oldEditorToOpen, editorToOpen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN:
				if (resolve) return getEditorToOpen();
				return basicGetEditorToOpen();
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
			case ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN:
				setEditorToOpen((ClassEditor)newValue);
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
			case ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN:
				setEditorToOpen((ClassEditor)null);
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
			case ControlPackage.UIM_LINK_CONTROL__EDITOR_TO_OPEN:
				return editorToOpen != null;
		}
		return super.eIsSet(featureID);
	}

} //UimLinkControlImpl
