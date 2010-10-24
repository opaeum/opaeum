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
 * A representation of the model object '<em><b>Composite Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getTimers <em>Timers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getOnEntry <em>On Entry</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getOnExit <em>On Exit</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getExceptionHandlers <em>Exception Handlers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getInPorts <em>In Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getOutPorts <em>Out Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.CompositeType#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType()
 * @model extendedMetaData="name='composite_._type' kind='elementOnly'"
 * @generated
 */
public interface CompositeType extends EObject {
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Meta Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.MetaDataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Data</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_MetaData()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='metaData' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MetaDataType> getMetaData();

	/**
	 * Returns the value of the '<em><b>Timers</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.TimersType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timers</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Timers()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timers' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TimersType> getTimers();

	/**
	 * Returns the value of the '<em><b>On Entry</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.OnEntryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Entry</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Entry</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_OnEntry()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='onEntry' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<OnEntryType> getOnEntry();

	/**
	 * Returns the value of the '<em><b>On Exit</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.OnExitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Exit</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Exit</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_OnExit()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='onExit' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<OnExitType> getOnExit();

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Variables()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variables' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<VariablesType> getVariables();

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_ExceptionHandlers()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exceptionHandlers' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlersType> getExceptionHandlers();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.NodesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Nodes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='nodes' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<NodesType> getNodes();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ConnectionsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Connections()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connections' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ConnectionsType> getConnections();

	/**
	 * Returns the value of the '<em><b>In Ports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.InPortsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Ports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_InPorts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='in-ports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<InPortsType> getInPorts();

	/**
	 * Returns the value of the '<em><b>Out Ports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.OutPortsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Ports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_OutPorts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='out-ports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<OutPortsType> getOutPorts();

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Height()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='height'"
	 * @generated
	 */
	String getHeight();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Width()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='width'"
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_X()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='x'"
	 * @generated
	 */
	String getX();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(String value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getCompositeType_Y()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='y'"
	 * @generated
	 */
	String getY();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.CompositeType#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(String value);

} // CompositeType
