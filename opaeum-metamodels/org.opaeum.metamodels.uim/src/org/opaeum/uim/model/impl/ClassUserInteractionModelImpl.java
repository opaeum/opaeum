/**
 */
package org.opaeum.uim.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.impl.UmlReferenceImpl;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.WizardPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#isUnderUserControl <em>Under User Control</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getPrimaryEditor <em>Primary Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getSecondaryEditors <em>Secondary Editors</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getNewObjectWizard <em>New Object Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl#getCubeQueryEditor <em>Cube Query Editor</em>}</li>
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
	 * The default value of the '{@link #getLinkedUmlResource() <em>Linked Uml Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedUmlResource()
	 * @generated
	 * @ordered
	 */
	protected static final String LINKED_UML_RESOURCE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLinkedUmlResource() <em>Linked Uml Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedUmlResource()
	 * @generated
	 * @ordered
	 */
	protected String linkedUmlResource = LINKED_UML_RESOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrimaryEditor() <em>Primary Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimaryEditor()
	 * @generated
	 * @ordered
	 */
	protected ObjectEditor primaryEditor;

	/**
	 * The cached value of the '{@link #getSecondaryEditors() <em>Secondary Editors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondaryEditors()
	 * @generated
	 * @ordered
	 */
	protected EList<ObjectEditor> secondaryEditors;

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
	 * The cached value of the '{@link #getCubeQueryEditor() <em>Cube Query Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCubeQueryEditor()
	 * @generated
	 * @ordered
	 */
	protected CubeQueryEditor cubeQueryEditor;

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
		return ModelPackage.Literals.CLASS_USER_INTERACTION_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL, oldUnderUserControl, underUserControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLinkedUmlResource() {
		return linkedUmlResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedUmlResource(String newLinkedUmlResource) {
		String oldLinkedUmlResource = linkedUmlResource;
		linkedUmlResource = newLinkedUmlResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE, oldLinkedUmlResource, linkedUmlResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectEditor getPrimaryEditor() {
		return primaryEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrimaryEditor(ObjectEditor newPrimaryEditor, NotificationChain msgs) {
		ObjectEditor oldPrimaryEditor = primaryEditor;
		primaryEditor = newPrimaryEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, oldPrimaryEditor, newPrimaryEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryEditor(ObjectEditor newPrimaryEditor) {
		if (newPrimaryEditor != primaryEditor) {
			NotificationChain msgs = null;
			if (primaryEditor != null)
				msgs = ((InternalEObject)primaryEditor).eInverseRemove(this, EditorPackage.OBJECT_EDITOR__MODEL, ObjectEditor.class, msgs);
			if (newPrimaryEditor != null)
				msgs = ((InternalEObject)newPrimaryEditor).eInverseAdd(this, EditorPackage.OBJECT_EDITOR__MODEL, ObjectEditor.class, msgs);
			msgs = basicSetPrimaryEditor(newPrimaryEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, newPrimaryEditor, newPrimaryEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectEditor> getSecondaryEditors() {
		if (secondaryEditors == null) {
			secondaryEditors = new EObjectContainmentEList<ObjectEditor>(ObjectEditor.class, this, ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, oldNewObjectWizard, newNewObjectWizard);
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
				msgs = ((InternalEObject)newObjectWizard).eInverseRemove(this, WizardPackage.NEW_OBJECT_WIZARD__MODEL, NewObjectWizard.class, msgs);
			if (newNewObjectWizard != null)
				msgs = ((InternalEObject)newNewObjectWizard).eInverseAdd(this, WizardPackage.NEW_OBJECT_WIZARD__MODEL, NewObjectWizard.class, msgs);
			msgs = basicSetNewObjectWizard(newNewObjectWizard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, newNewObjectWizard, newNewObjectWizard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeQueryEditor getCubeQueryEditor() {
		return cubeQueryEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCubeQueryEditor(CubeQueryEditor newCubeQueryEditor, NotificationChain msgs) {
		CubeQueryEditor oldCubeQueryEditor = cubeQueryEditor;
		cubeQueryEditor = newCubeQueryEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR, oldCubeQueryEditor, newCubeQueryEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCubeQueryEditor(CubeQueryEditor newCubeQueryEditor) {
		if (newCubeQueryEditor != cubeQueryEditor) {
			NotificationChain msgs = null;
			if (cubeQueryEditor != null)
				msgs = ((InternalEObject)cubeQueryEditor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR, null, msgs);
			if (newCubeQueryEditor != null)
				msgs = ((InternalEObject)newCubeQueryEditor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR, null, msgs);
			msgs = basicSetCubeQueryEditor(newCubeQueryEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR, newCubeQueryEditor, newCubeQueryEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				if (primaryEditor != null)
					msgs = ((InternalEObject)primaryEditor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR, null, msgs);
				return basicSetPrimaryEditor((ObjectEditor)otherEnd, msgs);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				if (newObjectWizard != null)
					msgs = ((InternalEObject)newObjectWizard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, null, msgs);
				return basicSetNewObjectWizard((NewObjectWizard)otherEnd, msgs);
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return basicSetPrimaryEditor(null, msgs);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return ((InternalEList<?>)getSecondaryEditors()).basicRemove(otherEnd, msgs);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return basicSetNewObjectWizard(null, msgs);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR:
				return basicSetCubeQueryEditor(null, msgs);
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				return getName();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL:
				return isUnderUserControl();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return getLinkedUmlResource();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return getPrimaryEditor();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return getSecondaryEditors();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return getNewObjectWizard();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR:
				return getCubeQueryEditor();
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL:
				setUnderUserControl((Boolean)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource((String)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				setPrimaryEditor((ObjectEditor)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				getSecondaryEditors().clear();
				getSecondaryEditors().addAll((Collection<? extends ObjectEditor>)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				setNewObjectWizard((NewObjectWizard)newValue);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR:
				setCubeQueryEditor((CubeQueryEditor)newValue);
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL:
				setUnderUserControl(UNDER_USER_CONTROL_EDEFAULT);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource(LINKED_UML_RESOURCE_EDEFAULT);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				setPrimaryEditor((ObjectEditor)null);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				getSecondaryEditors().clear();
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				setNewObjectWizard((NewObjectWizard)null);
				return;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR:
				setCubeQueryEditor((CubeQueryEditor)null);
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
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL:
				return underUserControl != UNDER_USER_CONTROL_EDEFAULT;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return LINKED_UML_RESOURCE_EDEFAULT == null ? linkedUmlResource != null : !LINKED_UML_RESOURCE_EDEFAULT.equals(linkedUmlResource);
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR:
				return primaryEditor != null;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS:
				return secondaryEditors != null && !secondaryEditors.isEmpty();
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD:
				return newObjectWizard != null;
			case ModelPackage.CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR:
				return cubeQueryEditor != null;
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
				case ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				case ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL: return UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return ModelPackage.CLASS_USER_INTERACTION_MODEL__NAME;
				case UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL: return ModelPackage.CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL;
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
		result.append(", underUserControl: ");
		result.append(underUserControl);
		result.append(", linkedUmlResource: ");
		result.append(linkedUmlResource);
		result.append(')');
		return result.toString();
	}

} //ClassUserInteractionModelImpl
