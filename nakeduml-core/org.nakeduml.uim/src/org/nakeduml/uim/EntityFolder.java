/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Could be any type of entity - OrgUnit, Role, Entity, etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.EntityFolder#getOperationTaskForms <em>Operation Task Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.EntityFolder#getEntity <em>Entity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getEntityFolder()
 * @model
 * @generated
 */
public interface EntityFolder extends OperationContainingFolder, UmlReference {
	/**
	 * Returns the value of the '<em><b>Operation Task Forms</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.OperationTaskForm}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.OperationTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Task Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Task Forms</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getEntityFolder_OperationTaskForms()
	 * @see org.nakeduml.uim.OperationTaskForm#getFolder
	 * @model opposite="folder" containment="true"
	 * @generated
	 */
	EList<OperationTaskForm> getOperationTaskForms();

	/**
	 * Returns the value of the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity</em>' reference.
	 * @see #setEntity(org.eclipse.uml2.uml.Class)
	 * @see org.nakeduml.uim.UIMPackage#getEntityFolder_Entity()
	 * @model
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getEntity();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.EntityFolder#getEntity <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(org.eclipse.uml2.uml.Class value);

} // EntityFolder
