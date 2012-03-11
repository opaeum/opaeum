/**
 */
package org.opaeum.uim.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.wizard.NewObjectWizard;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl#getPrimaryEditor <em>Primary Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl#getSecondaryEditors <em>Secondary Editors</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl#getNewObjectWizard <em>New Object Wizard</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassUserInteractionModelImpl extends UmlReferenceImpl implements ClassUserInteractionModel {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The cached value of the '{@link #getPrimaryEditor() <em>Primary Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryEditor()
	 * @generated
	 * @ordered
	 */
	protected ClassEditor primaryEditor;

	/**
	 * The cached value of the '{@link #getSecondaryEditors() <em>Secondary Editors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondaryEditors()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassEditor> secondaryEditors;

	/**
	 * The cached value of the '{@link #getNewObjectWizard() <em>New Object Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewObjectWizard()
	 * @generated
	 * @ordered
	 */
	protected NewObjectWizard newObjectWizard;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassUserInteractionModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.CLASS_USER_INTERACTION_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.CLASS_USER_INTERACTION_MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassEditor getPrimaryEditor() {
		return primaryEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrimaryEditor(ClassEditor newPrimaryEditor, NotificationChain msgs) {
		ClassEditor oldPrimaryEditor = primaryEditor;
		primaryEditor = newPrimaryEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, oldPrimaryEditor, newPrimaryEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryEditor(ClassEditor newPrimaryEditor) {
		if (newPrimaryEditor != primaryEditor) {
			NotificationChain msgs = null;
			if (primaryEditor != null)
				msgs = ((InternalEObject)primaryEditor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, null, msgs);
			if (newPrimaryEditor != null)
				msgs = ((InternalEObject)newPrimaryEditor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, null, msgs);
			msgs = basicSetPrimaryEditor(newPrimaryEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, newPrimaryEditor, newPrimaryEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassEditor> getSecondaryEditors() {
		if (secondaryEditors == null) {
			secondaryEditors = new EObjectContainmentEList<ClassEditor>(ClassEditor.class, this, UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS);
		}
		return secondaryEditors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewObjectWizard getNewObjectWizard() {
		return newObjectWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewObjectWizard(NewObjectWizard newNewObjectWizard, NotificationChain msgs) {
		NewObjectWizard oldNewObjectWizard = newObjectWizard;
		newObjectWizard = newNewObjectWizard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, oldNewObjectWizard, newNewObjectWizard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewObjectWizard(NewObjectWizard newNewObjectWizard) {
		if (newNewObjectWizard != newObjectWizard) {
			NotificationChain msgs = null;
			if (newObjectWizard != null)
				msgs = ((InternalEObject)newObjectWizard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, null, msgs);
			if (newNewObjectWizard != null)
				msgs = ((InternalEObject)newNewObjectWizard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, null, msgs);
			msgs = basicSetNewObjectWizard(newNewObjectWizard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, newNewObjectWizard, newNewObjectWizard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return basicSetPrimaryEditor(null, msgs);
			case UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return ((InternalEList<?>)getSecondaryEditors()).basicRemove(otherEnd, msgs);
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return basicSetNewObjectWizard(null, msgs);
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
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				return getName();
			case UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return getPrimaryEditor();
			case UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return getSecondaryEditors();
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return getNewObjectWizard();
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
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				setName((String)newValue);
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				setPrimaryEditor((ClassEditor)newValue);
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				getSecondaryEditors().clear();
				getSecondaryEditors().addAll((Collection<? extends ClassEditor>)newValue);
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				setNewObjectWizard((NewObjectWizard)newValue);
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
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				setPrimaryEditor((ClassEditor)null);
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				getSecondaryEditors().clear();
				return;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				setNewObjectWizard((NewObjectWizard)null);
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
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return primaryEditor != null;
			case UimPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return secondaryEditors != null && !secondaryEditors.isEmpty();
			case UimPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return newObjectWizard != null;
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
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case UimPackage.CLASS_USER_INTERACTION_MODEL__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
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
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.CLASS_USER_INTERACTION_MODEL__NAME;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ClassUserInteractionModelImpl
