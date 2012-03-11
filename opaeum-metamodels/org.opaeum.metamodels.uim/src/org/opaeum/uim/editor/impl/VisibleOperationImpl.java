/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.VisibleOperation;
import org.opaeum.uim.impl.UmlReferenceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Visible Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.VisibleOperationImpl#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VisibleOperationImpl extends UmlReferenceImpl implements VisibleOperation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VisibleOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.VISIBLE_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuConfiguration getMenuConfiguration() {
		if (eContainerFeatureID() != EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION) return null;
		return (MenuConfiguration)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMenuConfiguration(MenuConfiguration newMenuConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMenuConfiguration, EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMenuConfiguration(MenuConfiguration newMenuConfiguration) {
		if (newMenuConfiguration != eInternalContainer() || (eContainerFeatureID() != EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION && newMenuConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newMenuConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMenuConfiguration != null)
				msgs = ((InternalEObject)newMenuConfiguration).eInverseAdd(this, EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS, MenuConfiguration.class, msgs);
			msgs = basicSetMenuConfiguration(newMenuConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION, newMenuConfiguration, newMenuConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMenuConfiguration((MenuConfiguration)otherEnd, msgs);
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				return basicSetMenuConfiguration(null, msgs);
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS, MenuConfiguration.class, msgs);
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				return getMenuConfiguration();
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				setMenuConfiguration((MenuConfiguration)newValue);
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				setMenuConfiguration((MenuConfiguration)null);
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
			case EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION:
				return getMenuConfiguration() != null;
		}
		return super.eIsSet(featureID);
	}

} //VisibleOperationImpl
