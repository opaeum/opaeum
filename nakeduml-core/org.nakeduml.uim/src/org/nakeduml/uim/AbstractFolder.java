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
 * A representation of the model object '<em><b>Abstract Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.AbstractFolder#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getAbstractFolder()
 * @model abstract="true"
 * @generated
 */
public interface AbstractFolder extends UserInteractionElement, UmlReference {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.AbstractFormFolder}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.AbstractFormFolder#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nakeduml.uim.UimPackage#getAbstractFolder_Children()
	 * @see org.nakeduml.uim.AbstractFormFolder#getParent
	 * @model opposite="parent" containment="true"
	 *        extendedMetaData="wildcards='' name=''"
	 * @generated
	 */
	EList<AbstractFormFolder> getChildren();

} // AbstractFolder
