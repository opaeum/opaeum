/**
 */
package org.opaeum.uim.editor.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.OperationMenuItem;
import org.opaeum.uim.impl.UserInteractionElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuConfigurationImpl extends UserInteractionElementImpl implements MenuConfiguration {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationMenuItem> operations;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MenuConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.MENU_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceEditor getEditor() {
		if (eContainerFeatureID() != EditorPackage.MENU_CONFIGURATION__EDITOR) return null;
		return (InstanceEditor)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditor(InstanceEditor newEditor, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEditor, EditorPackage.MENU_CONFIGURATION__EDITOR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditor(InstanceEditor newEditor) {
		if (newEditor != eInternalContainer() || (eContainerFeatureID() != EditorPackage.MENU_CONFIGURATION__EDITOR && newEditor != null)) {
			if (EcoreUtil.isAncestor(this, newEditor))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEditor != null)
				msgs = ((InternalEObject)newEditor).eInverseAdd(this, EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION, InstanceEditor.class, msgs);
			msgs = basicSetEditor(newEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.MENU_CONFIGURATION__EDITOR, newEditor, newEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationMenuItem> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentWithInverseEList<OperationMenuItem>(OperationMenuItem.class, this, EditorPackage.MENU_CONFIGURATION__OPERATIONS, EditorPackage.OPERATION_MENU_ITEM__MENU_CONFIGURATION);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEditor((InstanceEditor)otherEnd, msgs);
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperations()).basicAdd(otherEnd, msgs);
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
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				return basicSetEditor(null, msgs);
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
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
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				return eInternalContainer().eInverseRemove(this, EditorPackage.INSTANCE_EDITOR__MENU_CONFIGURATION, InstanceEditor.class, msgs);
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
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				return getEditor();
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				return getOperations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				setEditor((InstanceEditor)newValue);
				return;
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends OperationMenuItem>)newValue);
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
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				setEditor((InstanceEditor)null);
				return;
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				getOperations().clear();
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
			case EditorPackage.MENU_CONFIGURATION__EDITOR:
				return getEditor() != null;
			case EditorPackage.MENU_CONFIGURATION__OPERATIONS:
				return operations != null && !operations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MenuConfigurationImpl
