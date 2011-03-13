/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.PackageFolder#getUmlPackage <em>Uml Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getPackageFolder()
 * @model
 * @generated
 */
public interface PackageFolder extends AbstractFormFolder {
	/**
	 * Returns the value of the '<em><b>Uml Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Package</em>' reference.
	 * @see #setUmlPackage(org.eclipse.uml2.uml.Package)
	 * @see org.nakeduml.uim.UIMPackage#getPackageFolder_UmlPackage()
	 * @model
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getUmlPackage();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.PackageFolder#getUmlPackage <em>Uml Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Package</em>' reference.
	 * @see #getUmlPackage()
	 * @generated
	 */
	void setUmlPackage(org.eclipse.uml2.uml.Package value);

} // PackageFolder
