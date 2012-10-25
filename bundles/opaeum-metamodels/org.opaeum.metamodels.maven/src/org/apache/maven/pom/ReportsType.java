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
 * A representation of the model object '<em><b>Reports Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.ReportsType#getReport <em>Report</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getReportsType()
 * @model extendedMetaData="name='reports_._type' kind='elementOnly'"
 * @generated
 */
public interface ReportsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Report</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Report</em>' attribute list.
	 * @see org.apache.maven.pom.POMPackage#getReportsType_Report()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='report' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getReport();

} // ReportsType
