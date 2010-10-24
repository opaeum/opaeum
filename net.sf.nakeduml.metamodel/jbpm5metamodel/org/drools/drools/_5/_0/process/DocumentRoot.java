/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getAction <em>Action</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getActionNode <em>Action Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getComposite <em>Composite</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getEnd <em>End</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilter <em>Event Filter</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilters <em>Event Filters</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getEventNode <em>Event Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandlers <em>Exception Handlers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getFault <em>Fault</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getForEach <em>For Each</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImport <em>Function Import</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImports <em>Function Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getGlobal <em>Global</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getGlobals <em>Globals</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getHeader <em>Header</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getHumanTask <em>Human Task</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getImport <em>Import</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getImports <em>Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getInPort <em>In Port</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getInPorts <em>In Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getJoin <em>Join</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getOnEntry <em>On Entry</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getOnExit <em>On Exit</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getOutPort <em>Out Port</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getOutPorts <em>Out Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getProcess <em>Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getSplit <em>Split</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getStart <em>Start</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getState <em>State</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlanes <em>Swimlanes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getTimerNode <em>Timer Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getTimers <em>Timers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getType <em>Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getValue <em>Value</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getWork <em>Work</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.DocumentRoot#getWorkItem <em>Work Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(ActionType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Action()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace'"
	 * @generated
	 */
	ActionType getAction();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(ActionType value);

	/**
	 * Returns the value of the '<em><b>Action Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Node</em>' containment reference.
	 * @see #setActionNode(ActionNodeType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_ActionNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='actionNode' namespace='##targetNamespace'"
	 * @generated
	 */
	ActionNodeType getActionNode();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getActionNode <em>Action Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Node</em>' containment reference.
	 * @see #getActionNode()
	 * @generated
	 */
	void setActionNode(ActionNodeType value);

	/**
	 * Returns the value of the '<em><b>Composite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite</em>' containment reference.
	 * @see #setComposite(CompositeType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Composite()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='composite' namespace='##targetNamespace'"
	 * @generated
	 */
	CompositeType getComposite();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getComposite <em>Composite</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite</em>' containment reference.
	 * @see #getComposite()
	 * @generated
	 */
	void setComposite(CompositeType value);

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' containment reference.
	 * @see #setConnection(ConnectionType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Connection()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connection' namespace='##targetNamespace'"
	 * @generated
	 */
	ConnectionType getConnection();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getConnection <em>Connection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' containment reference.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(ConnectionType value);

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference.
	 * @see #setConnections(ConnectionsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Connections()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connections' namespace='##targetNamespace'"
	 * @generated
	 */
	ConnectionsType getConnections();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getConnections <em>Connections</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connections</em>' containment reference.
	 * @see #getConnections()
	 * @generated
	 */
	void setConnections(ConnectionsType value);

	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint</em>' containment reference.
	 * @see #setConstraint(ConstraintType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Constraint()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='constraint' namespace='##targetNamespace'"
	 * @generated
	 */
	ConstraintType getConstraint();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getConstraint <em>Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint</em>' containment reference.
	 * @see #getConstraint()
	 * @generated
	 */
	void setConstraint(ConstraintType value);

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' containment reference.
	 * @see #setConstraints(ConstraintsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Constraints()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='constraints' namespace='##targetNamespace'"
	 * @generated
	 */
	ConstraintsType getConstraints();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getConstraints <em>Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraints</em>' containment reference.
	 * @see #getConstraints()
	 * @generated
	 */
	void setConstraints(ConstraintsType value);

	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic</em>' containment reference.
	 * @see #setDynamic(DynamicType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Dynamic()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dynamic' namespace='##targetNamespace'"
	 * @generated
	 */
	DynamicType getDynamic();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getDynamic <em>Dynamic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' containment reference.
	 * @see #getDynamic()
	 * @generated
	 */
	void setDynamic(DynamicType value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' containment reference.
	 * @see #setEnd(EndType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_End()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='end' namespace='##targetNamespace'"
	 * @generated
	 */
	EndType getEnd();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getEnd <em>End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' containment reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(EndType value);

	/**
	 * Returns the value of the '<em><b>Event Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Filter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Filter</em>' containment reference.
	 * @see #setEventFilter(EventFilterType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_EventFilter()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventFilter' namespace='##targetNamespace'"
	 * @generated
	 */
	EventFilterType getEventFilter();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilter <em>Event Filter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Filter</em>' containment reference.
	 * @see #getEventFilter()
	 * @generated
	 */
	void setEventFilter(EventFilterType value);

	/**
	 * Returns the value of the '<em><b>Event Filters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Filters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Filters</em>' containment reference.
	 * @see #setEventFilters(EventFiltersType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_EventFilters()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventFilters' namespace='##targetNamespace'"
	 * @generated
	 */
	EventFiltersType getEventFilters();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilters <em>Event Filters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Filters</em>' containment reference.
	 * @see #getEventFilters()
	 * @generated
	 */
	void setEventFilters(EventFiltersType value);

	/**
	 * Returns the value of the '<em><b>Event Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Node</em>' containment reference.
	 * @see #setEventNode(EventNodeType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_EventNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventNode' namespace='##targetNamespace'"
	 * @generated
	 */
	EventNodeType getEventNode();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getEventNode <em>Event Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Node</em>' containment reference.
	 * @see #getEventNode()
	 * @generated
	 */
	void setEventNode(EventNodeType value);

	/**
	 * Returns the value of the '<em><b>Exception Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler</em>' containment reference.
	 * @see #setExceptionHandler(ExceptionHandlerType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_ExceptionHandler()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exceptionHandler' namespace='##targetNamespace'"
	 * @generated
	 */
	ExceptionHandlerType getExceptionHandler();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Handler</em>' containment reference.
	 * @see #getExceptionHandler()
	 * @generated
	 */
	void setExceptionHandler(ExceptionHandlerType value);

	/**
	 * Returns the value of the '<em><b>Exception Handlers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handlers</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handlers</em>' containment reference.
	 * @see #setExceptionHandlers(ExceptionHandlersType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_ExceptionHandlers()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exceptionHandlers' namespace='##targetNamespace'"
	 * @generated
	 */
	ExceptionHandlersType getExceptionHandlers();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandlers <em>Exception Handlers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Handlers</em>' containment reference.
	 * @see #getExceptionHandlers()
	 * @generated
	 */
	void setExceptionHandlers(ExceptionHandlersType value);

	/**
	 * Returns the value of the '<em><b>Fault</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fault</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fault</em>' containment reference.
	 * @see #setFault(FaultType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Fault()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='fault' namespace='##targetNamespace'"
	 * @generated
	 */
	FaultType getFault();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getFault <em>Fault</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fault</em>' containment reference.
	 * @see #getFault()
	 * @generated
	 */
	void setFault(FaultType value);

	/**
	 * Returns the value of the '<em><b>For Each</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Each</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For Each</em>' containment reference.
	 * @see #setForEach(ForEachType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_ForEach()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='forEach' namespace='##targetNamespace'"
	 * @generated
	 */
	ForEachType getForEach();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getForEach <em>For Each</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For Each</em>' containment reference.
	 * @see #getForEach()
	 * @generated
	 */
	void setForEach(ForEachType value);

	/**
	 * Returns the value of the '<em><b>Function Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Import</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Import</em>' containment reference.
	 * @see #setFunctionImport(FunctionImportType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_FunctionImport()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='functionImport' namespace='##targetNamespace'"
	 * @generated
	 */
	FunctionImportType getFunctionImport();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImport <em>Function Import</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Import</em>' containment reference.
	 * @see #getFunctionImport()
	 * @generated
	 */
	void setFunctionImport(FunctionImportType value);

	/**
	 * Returns the value of the '<em><b>Function Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Imports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Imports</em>' containment reference.
	 * @see #setFunctionImports(FunctionImportsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_FunctionImports()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='functionImports' namespace='##targetNamespace'"
	 * @generated
	 */
	FunctionImportsType getFunctionImports();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImports <em>Function Imports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Imports</em>' containment reference.
	 * @see #getFunctionImports()
	 * @generated
	 */
	void setFunctionImports(FunctionImportsType value);

	/**
	 * Returns the value of the '<em><b>Global</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global</em>' containment reference.
	 * @see #setGlobal(GlobalType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Global()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='global' namespace='##targetNamespace'"
	 * @generated
	 */
	GlobalType getGlobal();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getGlobal <em>Global</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global</em>' containment reference.
	 * @see #getGlobal()
	 * @generated
	 */
	void setGlobal(GlobalType value);

	/**
	 * Returns the value of the '<em><b>Globals</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Globals</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Globals</em>' containment reference.
	 * @see #setGlobals(GlobalsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Globals()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globals' namespace='##targetNamespace'"
	 * @generated
	 */
	GlobalsType getGlobals();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getGlobals <em>Globals</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Globals</em>' containment reference.
	 * @see #getGlobals()
	 * @generated
	 */
	void setGlobals(GlobalsType value);

	/**
	 * Returns the value of the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header</em>' containment reference.
	 * @see #setHeader(HeaderType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Header()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='header' namespace='##targetNamespace'"
	 * @generated
	 */
	HeaderType getHeader();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getHeader <em>Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' containment reference.
	 * @see #getHeader()
	 * @generated
	 */
	void setHeader(HeaderType value);

	/**
	 * Returns the value of the '<em><b>Human Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Human Task</em>' containment reference.
	 * @see #setHumanTask(HumanTaskType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_HumanTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='humanTask' namespace='##targetNamespace'"
	 * @generated
	 */
	HumanTaskType getHumanTask();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getHumanTask <em>Human Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Human Task</em>' containment reference.
	 * @see #getHumanTask()
	 * @generated
	 */
	void setHumanTask(HumanTaskType value);

	/**
	 * Returns the value of the '<em><b>Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import</em>' containment reference.
	 * @see #setImport(ImportType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Import()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='import' namespace='##targetNamespace'"
	 * @generated
	 */
	ImportType getImport();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getImport <em>Import</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import</em>' containment reference.
	 * @see #getImport()
	 * @generated
	 */
	void setImport(ImportType value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference.
	 * @see #setImports(ImportsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Imports()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='imports' namespace='##targetNamespace'"
	 * @generated
	 */
	ImportsType getImports();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getImports <em>Imports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imports</em>' containment reference.
	 * @see #getImports()
	 * @generated
	 */
	void setImports(ImportsType value);

	/**
	 * Returns the value of the '<em><b>In Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Port</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Port</em>' containment reference.
	 * @see #setInPort(InPortType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_InPort()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='in-port' namespace='##targetNamespace'"
	 * @generated
	 */
	InPortType getInPort();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getInPort <em>In Port</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Port</em>' containment reference.
	 * @see #getInPort()
	 * @generated
	 */
	void setInPort(InPortType value);

	/**
	 * Returns the value of the '<em><b>In Ports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Ports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Ports</em>' containment reference.
	 * @see #setInPorts(InPortsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_InPorts()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='in-ports' namespace='##targetNamespace'"
	 * @generated
	 */
	InPortsType getInPorts();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getInPorts <em>In Ports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Ports</em>' containment reference.
	 * @see #getInPorts()
	 * @generated
	 */
	void setInPorts(InPortsType value);

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference.
	 * @see #setJoin(JoinType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Join()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='join' namespace='##targetNamespace'"
	 * @generated
	 */
	JoinType getJoin();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getJoin <em>Join</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' containment reference.
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinType value);

	/**
	 * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapping</em>' containment reference.
	 * @see #setMapping(MappingType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Mapping()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mapping' namespace='##targetNamespace'"
	 * @generated
	 */
	MappingType getMapping();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getMapping <em>Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapping</em>' containment reference.
	 * @see #getMapping()
	 * @generated
	 */
	void setMapping(MappingType value);

	/**
	 * Returns the value of the '<em><b>Meta Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Data</em>' containment reference.
	 * @see #setMetaData(MetaDataType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_MetaData()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='metaData' namespace='##targetNamespace'"
	 * @generated
	 */
	MetaDataType getMetaData();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getMetaData <em>Meta Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Meta Data</em>' containment reference.
	 * @see #getMetaData()
	 * @generated
	 */
	void setMetaData(MetaDataType value);

	/**
	 * Returns the value of the '<em><b>Milestone</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Milestone</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Milestone</em>' containment reference.
	 * @see #setMilestone(MilestoneType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Milestone()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='milestone' namespace='##targetNamespace'"
	 * @generated
	 */
	MilestoneType getMilestone();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getMilestone <em>Milestone</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Milestone</em>' containment reference.
	 * @see #getMilestone()
	 * @generated
	 */
	void setMilestone(MilestoneType value);

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference.
	 * @see #setNodes(NodesType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Nodes()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='nodes' namespace='##targetNamespace'"
	 * @generated
	 */
	NodesType getNodes();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getNodes <em>Nodes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nodes</em>' containment reference.
	 * @see #getNodes()
	 * @generated
	 */
	void setNodes(NodesType value);

	/**
	 * Returns the value of the '<em><b>On Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Entry</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Entry</em>' containment reference.
	 * @see #setOnEntry(OnEntryType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_OnEntry()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='onEntry' namespace='##targetNamespace'"
	 * @generated
	 */
	OnEntryType getOnEntry();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getOnEntry <em>On Entry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Entry</em>' containment reference.
	 * @see #getOnEntry()
	 * @generated
	 */
	void setOnEntry(OnEntryType value);

	/**
	 * Returns the value of the '<em><b>On Exit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Exit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Exit</em>' containment reference.
	 * @see #setOnExit(OnExitType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_OnExit()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='onExit' namespace='##targetNamespace'"
	 * @generated
	 */
	OnExitType getOnExit();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getOnExit <em>On Exit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Exit</em>' containment reference.
	 * @see #getOnExit()
	 * @generated
	 */
	void setOnExit(OnExitType value);

	/**
	 * Returns the value of the '<em><b>Out Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Port</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Port</em>' containment reference.
	 * @see #setOutPort(OutPortType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_OutPort()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='out-port' namespace='##targetNamespace'"
	 * @generated
	 */
	OutPortType getOutPort();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getOutPort <em>Out Port</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Port</em>' containment reference.
	 * @see #getOutPort()
	 * @generated
	 */
	void setOutPort(OutPortType value);

	/**
	 * Returns the value of the '<em><b>Out Ports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Ports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Ports</em>' containment reference.
	 * @see #setOutPorts(OutPortsType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_OutPorts()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='out-ports' namespace='##targetNamespace'"
	 * @generated
	 */
	OutPortsType getOutPorts();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getOutPorts <em>Out Ports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Ports</em>' containment reference.
	 * @see #getOutPorts()
	 * @generated
	 */
	void setOutPorts(OutPortsType value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference.
	 * @see #setParameter(ParameterType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Parameter()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='parameter' namespace='##targetNamespace'"
	 * @generated
	 */
	ParameterType getParameter();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getParameter <em>Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' containment reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(ParameterType value);

	/**
	 * Returns the value of the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' containment reference.
	 * @see #setProcess(ProcessType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Process()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='process' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessType getProcess();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getProcess <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' containment reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(ProcessType value);

	/**
	 * Returns the value of the '<em><b>Rule Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Set</em>' containment reference.
	 * @see #setRuleSet(RuleSetType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_RuleSet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ruleSet' namespace='##targetNamespace'"
	 * @generated
	 */
	RuleSetType getRuleSet();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getRuleSet <em>Rule Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule Set</em>' containment reference.
	 * @see #getRuleSet()
	 * @generated
	 */
	void setRuleSet(RuleSetType value);

	/**
	 * Returns the value of the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split</em>' containment reference.
	 * @see #setSplit(SplitType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Split()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='split' namespace='##targetNamespace'"
	 * @generated
	 */
	SplitType getSplit();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getSplit <em>Split</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split</em>' containment reference.
	 * @see #getSplit()
	 * @generated
	 */
	void setSplit(SplitType value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' containment reference.
	 * @see #setStart(StartType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Start()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='start' namespace='##targetNamespace'"
	 * @generated
	 */
	StartType getStart();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getStart <em>Start</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' containment reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(StartType value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' containment reference.
	 * @see #setState(StateType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_State()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='state' namespace='##targetNamespace'"
	 * @generated
	 */
	StateType getState();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getState <em>State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' containment reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(StateType value);

	/**
	 * Returns the value of the '<em><b>Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Process</em>' containment reference.
	 * @see #setSubProcess(SubProcessType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_SubProcess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subProcess' namespace='##targetNamespace'"
	 * @generated
	 */
	SubProcessType getSubProcess();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getSubProcess <em>Sub Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Process</em>' containment reference.
	 * @see #getSubProcess()
	 * @generated
	 */
	void setSubProcess(SubProcessType value);

	/**
	 * Returns the value of the '<em><b>Swimlane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlane</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlane</em>' containment reference.
	 * @see #setSwimlane(SwimlaneType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Swimlane()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='swimlane' namespace='##targetNamespace'"
	 * @generated
	 */
	SwimlaneType getSwimlane();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlane <em>Swimlane</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swimlane</em>' containment reference.
	 * @see #getSwimlane()
	 * @generated
	 */
	void setSwimlane(SwimlaneType value);

	/**
	 * Returns the value of the '<em><b>Swimlanes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlanes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlanes</em>' containment reference.
	 * @see #setSwimlanes(SwimlanesType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Swimlanes()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='swimlanes' namespace='##targetNamespace'"
	 * @generated
	 */
	SwimlanesType getSwimlanes();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlanes <em>Swimlanes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swimlanes</em>' containment reference.
	 * @see #getSwimlanes()
	 * @generated
	 */
	void setSwimlanes(SwimlanesType value);

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference.
	 * @see #setTimer(TimerType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Timer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timer' namespace='##targetNamespace'"
	 * @generated
	 */
	TimerType getTimer();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getTimer <em>Timer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timer</em>' containment reference.
	 * @see #getTimer()
	 * @generated
	 */
	void setTimer(TimerType value);

	/**
	 * Returns the value of the '<em><b>Timer Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer Node</em>' containment reference.
	 * @see #setTimerNode(TimerNodeType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_TimerNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timerNode' namespace='##targetNamespace'"
	 * @generated
	 */
	TimerNodeType getTimerNode();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getTimerNode <em>Timer Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timer Node</em>' containment reference.
	 * @see #getTimerNode()
	 * @generated
	 */
	void setTimerNode(TimerNodeType value);

	/**
	 * Returns the value of the '<em><b>Timers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timers</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timers</em>' containment reference.
	 * @see #setTimers(TimersType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Timers()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timers' namespace='##targetNamespace'"
	 * @generated
	 */
	TimersType getTimers();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getTimers <em>Timers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timers</em>' containment reference.
	 * @see #getTimers()
	 * @generated
	 */
	void setTimers(TimersType value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(TriggerType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Trigger()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='trigger' namespace='##targetNamespace'"
	 * @generated
	 */
	TriggerType getTrigger();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(TriggerType value);

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggers</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference.
	 * @see #setTriggers(TriggersType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Triggers()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='triggers' namespace='##targetNamespace'"
	 * @generated
	 */
	TriggersType getTriggers();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getTriggers <em>Triggers</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Triggers</em>' containment reference.
	 * @see #getTriggers()
	 * @generated
	 */
	void setTriggers(TriggersType value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(TypeType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Type()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	TypeType getType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(ValueType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Value()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueType getValue();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(ValueType value);

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' containment reference.
	 * @see #setVariable(VariableType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Variable()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variable' namespace='##targetNamespace'"
	 * @generated
	 */
	VariableType getVariable();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getVariable <em>Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' containment reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(VariableType value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference.
	 * @see #setVariables(VariablesType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Variables()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variables' namespace='##targetNamespace'"
	 * @generated
	 */
	VariablesType getVariables();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(VariablesType value);

	/**
	 * Returns the value of the '<em><b>Work</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work</em>' containment reference.
	 * @see #setWork(WorkType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_Work()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='work' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkType getWork();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getWork <em>Work</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' containment reference.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(WorkType value);

	/**
	 * Returns the value of the '<em><b>Work Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Item</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Item</em>' containment reference.
	 * @see #setWorkItem(WorkItemType)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getDocumentRoot_WorkItem()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='workItem' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkItemType getWorkItem();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.DocumentRoot#getWorkItem <em>Work Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Item</em>' containment reference.
	 * @see #getWorkItem()
	 * @generated
	 */
	void setWorkItem(WorkItemType value);

} // DocumentRoot
