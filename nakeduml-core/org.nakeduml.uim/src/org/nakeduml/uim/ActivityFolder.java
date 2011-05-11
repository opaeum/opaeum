/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.Activity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.ActivityFolder#getActionTaskForms <em>Action Task Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.ActivityFolder#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getActivityFolder()
 * @model
 * @generated
 */
public interface ActivityFolder extends AbstractFormFolder, UmlReference {
	/**
	 * Returns the value of the '<em><b>Action Task Forms</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.ActionTaskForm}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.ActionTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Task Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Task Forms</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getActivityFolder_ActionTaskForms()
	 * @see org.nakeduml.uim.ActionTaskForm#getFolder
	 * @model opposite="folder" containment="true"
	 * @generated
	 */
	EList<ActionTaskForm> getActionTaskForms();

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see org.nakeduml.uim.UIMPackage#getActivityFolder_Activity()
	 * @model
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ActivityFolder#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

} // ActivityFolder
