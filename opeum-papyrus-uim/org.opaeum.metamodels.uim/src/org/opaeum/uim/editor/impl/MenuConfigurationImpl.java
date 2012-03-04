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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.VisibleOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl#getEditor <em>Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl#getVisibleOperations <em>Visible Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuConfigurationImpl extends EObjectImpl implements MenuConfiguration {
	/**
	 * The cached value of the '{@link #getVisibleOperations() <em>Visible Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibleOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<VisibleOperation> visibleOperations;

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
	public AbstractEditor getEditor() {
		if (eContainerFeatureID() != EditorPackage.MENU_CONFIGURATION__EDITOR) return null;
		return (AbstractEditor)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditor(AbstractEditor newEditor, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEditor, EditorPackage.MENU_CONFIGURATION__EDITOR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditor(AbstractEditor newEditor) {
		if (newEditor != eInternalContainer() || (eContainerFeatureID() != EditorPackage.MENU_CONFIGURATION__EDITOR && newEditor != null)) {
			if (EcoreUtil.isAncestor(this, newEditor))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEditor != null)
				msgs = ((InternalEObject)newEditor).eInverseAdd(this, EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION, AbstractEditor.class, msgs);
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
	public EList<VisibleOperation> getVisibleOperations() {
		if (visibleOperations == null) {
			visibleOperations = new EObjectContainmentWithInverseEList<VisibleOperation>(VisibleOperation.class, this, EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS, EditorPackage.VISIBLE_OPERATION__MENU_CONFIGURATION);
		}
		return visibleOperations;
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
				return basicSetEditor((AbstractEditor)otherEnd, msgs);
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVisibleOperations()).basicAdd(otherEnd, msgs);
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
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				return ((InternalEList<?>)getVisibleOperations()).basicRemove(otherEnd, msgs);
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
				return eInternalContainer().eInverseRemove(this, EditorPackage.ABSTRACT_EDITOR__MENU_CONFIGURATION, AbstractEditor.class, msgs);
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
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				return getVisibleOperations();
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
				setEditor((AbstractEditor)newValue);
				return;
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				getVisibleOperations().clear();
				getVisibleOperations().addAll((Collection<? extends VisibleOperation>)newValue);
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
				setEditor((AbstractEditor)null);
				return;
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				getVisibleOperations().clear();
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
			case EditorPackage.MENU_CONFIGURATION__VISIBLE_OPERATIONS:
				return visibleOperations != null && !visibleOperations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MenuConfigurationImpl
