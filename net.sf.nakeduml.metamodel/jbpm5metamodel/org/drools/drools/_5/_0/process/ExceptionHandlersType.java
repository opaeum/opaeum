/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exception Handlers Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ExceptionHandlersType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ExceptionHandlersType#getExceptionHandler <em>Exception Handler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getExceptionHandlersType()
 * @model extendedMetaData="name='exceptionHandlers_._type' kind='elementOnly'"
 * @generated
 */
public interface ExceptionHandlersType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getExceptionHandlersType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Exception Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ExceptionHandlerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getExceptionHandlersType_ExceptionHandler()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exceptionHandler' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlerType> getExceptionHandler();

} // ExceptionHandlersType
