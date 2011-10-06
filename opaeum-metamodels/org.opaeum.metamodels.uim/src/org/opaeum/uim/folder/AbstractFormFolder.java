/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.folder;

import org.opaeum.uim.security.EditableSecureObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Form Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.folder.AbstractFormFolder#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.folder.FolderPackage#getAbstractFormFolder()
 * @model abstract="true"
 * @generated
 */
public interface AbstractFormFolder extends AbstractFolder, EditableSecureObject {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.folder.AbstractFolder#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(AbstractFolder)
	 * @see org.opaeum.uim.folder.FolderPackage#getAbstractFormFolder_Parent()
	 * @see org.opaeum.uim.folder.AbstractFolder#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	AbstractFolder getParent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.folder.AbstractFormFolder#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(AbstractFolder value);

} // AbstractFormFolder
