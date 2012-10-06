/**
 */
package org.opaeum.uim.model;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.wizard.NewObjectWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.model.ClassUserInteractionModel#getSecondaryEditors <em>Secondary Editors</em>}</li>
 *   <li>{@link org.opaeum.uim.model.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.model.ClassUserInteractionModel#getCubeQueryEditor <em>Cube Query Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getClassUserInteractionModel()
 * @model
 * @generated
 */
public interface ClassUserInteractionModel extends AbstractUserInteractionModel {
	/**
	 * Returns the value of the '<em><b>Primary Editor</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.ObjectEditor#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Editor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Editor</em>' containment reference.
	 * @see #setPrimaryEditor(ObjectEditor)
	 * @see org.opaeum.uim.model.ModelPackage#getClassUserInteractionModel_PrimaryEditor()
	 * @see org.opaeum.uim.editor.ObjectEditor#getModel
	 * @model opposite="model" containment="true" required="true"
	 * @generated
	 */
	ObjectEditor getPrimaryEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Editor</em>' containment reference.
	 * @see #getPrimaryEditor()
	 * @generated
	 */
	void setPrimaryEditor(ObjectEditor value);

	/**
	 * Returns the value of the '<em><b>Secondary Editors</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.editor.ObjectEditor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secondary Editors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secondary Editors</em>' containment reference list.
	 * @see org.opaeum.uim.model.ModelPackage#getClassUserInteractionModel_SecondaryEditors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ObjectEditor> getSecondaryEditors();

	/**
	 * Returns the value of the '<em><b>New Object Wizard</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.NewObjectWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Object Wizard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Object Wizard</em>' containment reference.
	 * @see #setNewObjectWizard(NewObjectWizard)
	 * @see org.opaeum.uim.model.ModelPackage#getClassUserInteractionModel_NewObjectWizard()
	 * @see org.opaeum.uim.wizard.NewObjectWizard#getModel
	 * @model opposite="model" containment="true" required="true"
	 * @generated
	 */
	NewObjectWizard getNewObjectWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Object Wizard</em>' containment reference.
	 * @see #getNewObjectWizard()
	 * @generated
	 */
	void setNewObjectWizard(NewObjectWizard value);

	/**
	 * Returns the value of the '<em><b>Cube Query Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cube Query Editor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cube Query Editor</em>' containment reference.
	 * @see #setCubeQueryEditor(CubeQueryEditor)
	 * @see org.opaeum.uim.model.ModelPackage#getClassUserInteractionModel_CubeQueryEditor()
	 * @model containment="true"
	 * @generated
	 */
	CubeQueryEditor getCubeQueryEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.ClassUserInteractionModel#getCubeQueryEditor <em>Cube Query Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube Query Editor</em>' containment reference.
	 * @see #getCubeQueryEditor()
	 * @generated
	 */
	void setCubeQueryEditor(CubeQueryEditor value);

} // ClassUserInteractionModel
