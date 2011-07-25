/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.Parent#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.Parent#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.Parent#getVersion <em>Version</em>}</li>
 *   <li>{@link org.apache.maven.pom.Parent#getRelativePath <em>Relative Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getParent()
 * @model extendedMetaData="name='Parent' kind='elementOnly'"
 * @generated
 */
public interface Parent extends EObject {
	/**
	 * Returns the value of the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * The artifact id of the parent project to inherit from.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifact Id</em>' attribute.
	 * @see #setArtifactId(String)
	 * @see org.apache.maven.pom.POMPackage#getParent_ArtifactId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='artifactId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getArtifactId();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Parent#getArtifactId <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Id</em>' attribute.
	 * @see #getArtifactId()
	 * @generated
	 */
	void setArtifactId(String value);

	/**
	 * Returns the value of the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * The group id of the parent project to inherit from.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Group Id</em>' attribute.
	 * @see #setGroupId(String)
	 * @see org.apache.maven.pom.POMPackage#getParent_GroupId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='groupId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getGroupId();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Parent#getGroupId <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Id</em>' attribute.
	 * @see #getGroupId()
	 * @generated
	 */
	void setGroupId(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * The version of the parent project to inherit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.apache.maven.pom.POMPackage#getParent_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Parent#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Relative Path</b></em>' attribute.
	 * The default value is <code>"../pom.xml"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The relative path of the parent <code>pom.xml</code> file within the check out.
	 *             The default value is <code>../pom.xml</code>.
	 *             Maven looks for the parent pom first in the reactor of currently building projects, then in this location on
	 *             the filesystem, then the local repository, and lastly in the remote repo.
	 *             <code>relativePath</code> allows you to select a different location,
	 *             for example when your structure is flat, or deeper without an intermediate parent pom.
	 *             However, the group ID, artifact ID and version are still required,
	 *             and must match the file in the location given or it will revert to the repository for the POM.
	 *             This feature is only for enhancing the development in a local checkout of that project.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Relative Path</em>' attribute.
	 * @see #isSetRelativePath()
	 * @see #unsetRelativePath()
	 * @see #setRelativePath(String)
	 * @see org.apache.maven.pom.POMPackage#getParent_RelativePath()
	 * @model default="../pom.xml" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='relativePath' namespace='##targetNamespace'"
	 * @generated
	 */
	String getRelativePath();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Parent#getRelativePath <em>Relative Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Path</em>' attribute.
	 * @see #isSetRelativePath()
	 * @see #unsetRelativePath()
	 * @see #getRelativePath()
	 * @generated
	 */
	void setRelativePath(String value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Parent#getRelativePath <em>Relative Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRelativePath()
	 * @see #getRelativePath()
	 * @see #setRelativePath(String)
	 * @generated
	 */
	void unsetRelativePath();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Parent#getRelativePath <em>Relative Path</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Relative Path</em>' attribute is set.
	 * @see #unsetRelativePath()
	 * @see #getRelativePath()
	 * @see #setRelativePath(String)
	 * @generated
	 */
	boolean isSetRelativePath();

} // Parent
