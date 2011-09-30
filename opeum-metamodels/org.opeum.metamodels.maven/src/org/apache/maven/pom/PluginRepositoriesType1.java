/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Repositories Type1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.PluginRepositoriesType1#getPluginRepository <em>Plugin Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getPluginRepositoriesType1()
 * @model extendedMetaData="name='pluginRepositories_._1_._type' kind='elementOnly'"
 * @generated
 */
public interface PluginRepositoriesType1 extends EObject {
	/**
	 * Returns the value of the '<em><b>Plugin Repository</b></em>' containment reference list.
	 * The list contents are of type {@link org.apache.maven.pom.Repository}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Repository</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Repository</em>' containment reference list.
	 * @see org.apache.maven.pom.POMPackage#getPluginRepositoriesType1_PluginRepository()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='pluginRepository' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Repository> getPluginRepository();

} // PluginRepositoriesType1
