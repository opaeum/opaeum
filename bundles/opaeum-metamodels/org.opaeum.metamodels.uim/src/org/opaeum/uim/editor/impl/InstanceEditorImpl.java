/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.editor.MenuConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.InstanceEditorImpl#getMenuConfiguration <em>Menu Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class InstanceEditorImpl extends AbstractEditorImpl implements InstanceEditor {
	/**
	 * The cached value of the '{@link #getMenuConfiguration() <em>Menu Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMenuConfiguration()
	 * @generated
	 * @ordered
	 */
	protected MenuConfiguration menuConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceEditorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.INSTANCE_EDITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuConfiguration getMenuConfiguration() {
		return menuConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMenuConfiguration(MenuConfiguration newMenuConfiguration, NotificationChain msgs) {
		MenuConfiguration oldMenuConfiguration = menuConfiguration;
		menuConfiguration = newMenuConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION, oldMenuConfiguration, newMenuConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMenuConfiguration(MenuConfiguration newMenuConfiguration) {
		if (newMenuConfiguration != menuConfiguration) {
			NotificationChain msgs = null;
			if (menuConfiguration != null)
				msgs = ((InternalEObject)menuConfiguration).eInverseRemove(this, EditorPackage.MENU_CONFIGURATION__EDITOR, MenuConfiguration.class, msgs);
			if (newMenuConfiguration != null)
				msgs = ((InternalEObject)newMenuConfiguration).eInverseAdd(this, EditorPackage.MENU_CONFIGURATION__EDITOR, MenuConfiguration.class, msgs);
			msgs = basicSetMenuConfiguration(newMenuConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION, newMenuConfiguration, newMenuConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
				if (menuConfiguration != null)
					msgs = ((InternalEObject)menuConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION, null, msgs);
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
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
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
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
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
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
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
			case EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION:
				return menuConfiguration != null;
		}
		return super.eIsSet(featureID);
	}

} //InstanceEditorImpl
