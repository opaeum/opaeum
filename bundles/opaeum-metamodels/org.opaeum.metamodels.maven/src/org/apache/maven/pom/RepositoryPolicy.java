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
 * A representation of the model object '<em><b>Repository Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * Download policy
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.RepositoryPolicy#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.apache.maven.pom.RepositoryPolicy#getUpdatePolicy <em>Update Policy</em>}</li>
 *   <li>{@link org.apache.maven.pom.RepositoryPolicy#getChecksumPolicy <em>Checksum Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getRepositoryPolicy()
 * @model extendedMetaData="name='RepositoryPolicy' kind='elementOnly'"
 * @generated
 */
public interface RepositoryPolicy extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * Whether to use this repository for downloading this type of artifact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.apache.maven.pom.POMPackage#getRepositoryPolicy_Enabled()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='enabled' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.RepositoryPolicy#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.RepositoryPolicy#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.RepositoryPolicy#isEnabled <em>Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Update Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The frequency for downloading updates - can be
	 *             <code>always,</code>
	 *             <code>daily</code>
	 *             (default),
	 *             <code>interval:XXX</code>
	 *             (in minutes) or
	 *             <code>never</code>
	 *             (only if it doesn't exist locally).
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Update Policy</em>' attribute.
	 * @see #setUpdatePolicy(String)
	 * @see org.apache.maven.pom.POMPackage#getRepositoryPolicy_UpdatePolicy()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='updatePolicy' namespace='##targetNamespace'"
	 * @generated
	 */
	String getUpdatePolicy();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.RepositoryPolicy#getUpdatePolicy <em>Update Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Policy</em>' attribute.
	 * @see #getUpdatePolicy()
	 * @generated
	 */
	void setUpdatePolicy(String value);

	/**
	 * Returns the value of the '<em><b>Checksum Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             What to do when verification of an artifact checksum fails. Valid values are
	 *             <code>ignore</code>
	 *             ,
	 *             <code>fail</code>
	 *             or
	 *             <code>warn</code>
	 *             (the default).
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Checksum Policy</em>' attribute.
	 * @see #setChecksumPolicy(String)
	 * @see org.apache.maven.pom.POMPackage#getRepositoryPolicy_ChecksumPolicy()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='checksumPolicy' namespace='##targetNamespace'"
	 * @generated
	 */
	String getChecksumPolicy();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.RepositoryPolicy#getChecksumPolicy <em>Checksum Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checksum Policy</em>' attribute.
	 * @see #getChecksumPolicy()
	 * @generated
	 */
	void setChecksumPolicy(String value);

} // RepositoryPolicy
