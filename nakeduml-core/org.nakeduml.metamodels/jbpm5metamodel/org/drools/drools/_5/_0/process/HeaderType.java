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
 * A representation of the model object '<em><b>Header Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getImports <em>Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getFunctionImports <em>Function Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getGlobals <em>Globals</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getSwimlanes <em>Swimlanes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.HeaderType#getExceptionHandlers <em>Exception Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType()
 * @model extendedMetaData="name='header_._type' kind='elementOnly'"
 * @generated
 */
public interface HeaderType extends EObject {
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ImportsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_Imports()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='imports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ImportsType> getImports();

	/**
	 * Returns the value of the '<em><b>Function Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.FunctionImportsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Imports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_FunctionImports()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='functionImports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<FunctionImportsType> getFunctionImports();

	/**
	 * Returns the value of the '<em><b>Globals</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.GlobalsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Globals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Globals</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_Globals()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globals' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<GlobalsType> getGlobals();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.VariablesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_Variables()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variables' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<VariablesType> getVariables();

	/**
	 * Returns the value of the '<em><b>Swimlanes</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.SwimlanesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlanes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlanes</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_Swimlanes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='swimlanes' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SwimlanesType> getSwimlanes();

	/**
	 * Returns the value of the '<em><b>Exception Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ExceptionHandlersType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handlers</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getHeaderType_ExceptionHandlers()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exceptionHandlers' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlersType> getExceptionHandlers();

} // HeaderType
