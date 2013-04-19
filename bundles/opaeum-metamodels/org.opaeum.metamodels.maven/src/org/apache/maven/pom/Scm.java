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
 * A representation of the model object '<em><b>Scm</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.Scm#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.apache.maven.pom.Scm#getDeveloperConnection <em>Developer Connection</em>}</li>
 *   <li>{@link org.apache.maven.pom.Scm#getTag <em>Tag</em>}</li>
 *   <li>{@link org.apache.maven.pom.Scm#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getScm()
 * @model extendedMetaData="name='Scm' kind='elementOnly'"
 * @generated
 */
public interface Scm extends EObject {
	/**
	 * Returns the value of the '<em><b>Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *               The source control management system URL
	 *               that describes the repository and how to connect to the
	 *               repository. For more information, see the
	 *               <a href="http://maven.apache.org/scm/scm-url-format.html">URL format</a>
	 *               and <a href="http://maven.apache.org/scm/scms-overview.html">list of supported SCMs</a>.
	 *               This connection is read-only.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection</em>' attribute.
	 * @see #setConnection(String)
	 * @see org.apache.maven.pom.POMPackage#getScm_Connection()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='connection' namespace='##targetNamespace'"
	 * @generated
	 */
	String getConnection();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Scm#getConnection <em>Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' attribute.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(String value);

	/**
	 * Returns the value of the '<em><b>Developer Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             Just like <code>connection</code>, but for developers, i.e. this scm connection
	 *             will not be read only.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Developer Connection</em>' attribute.
	 * @see #setDeveloperConnection(String)
	 * @see org.apache.maven.pom.POMPackage#getScm_DeveloperConnection()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='developerConnection' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDeveloperConnection();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Scm#getDeveloperConnection <em>Developer Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Developer Connection</em>' attribute.
	 * @see #getDeveloperConnection()
	 * @generated
	 */
	void setDeveloperConnection(String value);

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute.
	 * The default value is <code>"HEAD"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The tag of current code. By default, it's set to HEAD during development.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #isSetTag()
	 * @see #unsetTag()
	 * @see #setTag(String)
	 * @see org.apache.maven.pom.POMPackage#getScm_Tag()
	 * @model default="HEAD" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='tag' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Scm#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #isSetTag()
	 * @see #unsetTag()
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Scm#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTag()
	 * @see #getTag()
	 * @see #setTag(String)
	 * @generated
	 */
	void unsetTag();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Scm#getTag <em>Tag</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Tag</em>' attribute is set.
	 * @see #unsetTag()
	 * @see #getTag()
	 * @see #setTag(String)
	 * @generated
	 */
	boolean isSetTag();

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The URL to the project's browsable SCM repository, such as ViewVC or Fisheye.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.apache.maven.pom.POMPackage#getScm_Url()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='url' namespace='##targetNamespace'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Scm#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

} // Scm
