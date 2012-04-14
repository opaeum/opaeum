/**
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.wizard.NewObjectWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}</li>
 *   <li>{@link org.opaeum.uim.ClassUserInteractionModel#getSecondaryEditors <em>Secondary Editors</em>}</li>
 *   <li>{@link org.opaeum.uim.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.ClassUserInteractionModel#getCubeQueries <em>Cube Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getClassUserInteractionModel()
 * @model
 * @generated
 */
public interface ClassUserInteractionModel extends UmlReference, UserInteractionElement, UimRootElement {
	/**
	 * Returns the value of the '<em><b>Primary Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Editor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Editor</em>' containment reference.
	 * @see #setPrimaryEditor(ClassEditor)
	 * @see org.opaeum.uim.UimPackage#getClassUserInteractionModel_PrimaryEditor()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ClassEditor getPrimaryEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Editor</em>' containment reference.
	 * @see #getPrimaryEditor()
	 * @generated
	 */
	void setPrimaryEditor(ClassEditor value);

	/**
	 * Returns the value of the '<em><b>Secondary Editors</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.editor.ClassEditor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secondary Editors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secondary Editors</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getClassUserInteractionModel_SecondaryEditors()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassEditor> getSecondaryEditors();

	/**
	 * Returns the value of the '<em><b>New Object Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Object Wizard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Object Wizard</em>' containment reference.
	 * @see #setNewObjectWizard(NewObjectWizard)
	 * @see org.opaeum.uim.UimPackage#getClassUserInteractionModel_NewObjectWizard()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NewObjectWizard getNewObjectWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Object Wizard</em>' containment reference.
	 * @see #getNewObjectWizard()
	 * @generated
	 */
	void setNewObjectWizard(NewObjectWizard value);

	/**
	 * Returns the value of the '<em><b>Cube Queries</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.CubeQuery}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cube Queries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cube Queries</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getClassUserInteractionModel_CubeQueries()
	 * @model containment="true"
	 * @generated
	 */
	EList<CubeQuery> getCubeQueries();

} // ClassUserInteractionModel
