/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.folder;

import org.eclipse.emf.common.util.EList;
import org.opeum.uim.UmlReference;
import org.opeum.uim.UserInteractionElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.folder.AbstractFolder#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.folder.FolderPackage#getAbstractFolder()
 * @model abstract="true"
 * @generated
 */
public interface AbstractFolder extends UserInteractionElement, UmlReference {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.opeum.uim.folder.AbstractFormFolder}.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.folder.AbstractFormFolder#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.opeum.uim.folder.FolderPackage#getAbstractFolder_Children()
	 * @see org.opeum.uim.folder.AbstractFormFolder#getParent
	 * @model opposite="parent" containment="true"
	 *        extendedMetaData="wildcards='' name=''"
	 * @generated
	 */
	EList<AbstractFormFolder> getChildren();

} // AbstractFolder