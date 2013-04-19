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
 * A representation of the model object '<em><b>Dependency Management</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * 
 *         Section for management of default dependency information for use in a group of POMs.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.DependencyManagement#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getDependencyManagement()
 * @model extendedMetaData="name='DependencyManagement' kind='elementOnly'"
 * @generated
 */
public interface DependencyManagement extends EObject {
	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             The dependencies specified here are not used until they
	 *             are referenced in a POM within the group. This allows the
	 *             specification of a "standard" version for a particular
	 *             dependency.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(DependenciesType1)
	 * @see org.apache.maven.pom.POMPackage#getDependencyManagement_Dependencies()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dependencies' namespace='##targetNamespace'"
	 * @generated
	 */
	DependenciesType1 getDependencies();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.DependencyManagement#getDependencies <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(DependenciesType1 value);

} // DependencyManagement
