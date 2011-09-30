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
 * A representation of the model object '<em><b>Nodes Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getStart <em>Start</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getEnd <em>End</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getActionNode <em>Action Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getSplit <em>Split</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getJoin <em>Join</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getWorkItem <em>Work Item</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getTimerNode <em>Timer Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getHumanTask <em>Human Task</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getComposite <em>Composite</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getForEach <em>For Each</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getEventNode <em>Event Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getFault <em>Fault</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getState <em>State</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.NodesType#getDynamic <em>Dynamic</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType()
 * @model extendedMetaData="name='nodes_._type' kind='elementOnly'"
 * @generated
 */
public interface NodesType extends EObject {
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Start</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.StartType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Start()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='start' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<StartType> getStart();

	/**
	 * Returns the value of the '<em><b>End</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.EndType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_End()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='end' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EndType> getEnd();

	/**
	 * Returns the value of the '<em><b>Action Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ActionNodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Node</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_ActionNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='actionNode' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ActionNodeType> getActionNode();

	/**
	 * Returns the value of the '<em><b>Rule Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.RuleSetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Set</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_RuleSet()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ruleSet' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<RuleSetType> getRuleSet();

	/**
	 * Returns the value of the '<em><b>Split</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.SplitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Split()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='split' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SplitType> getSplit();

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.JoinType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Join()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='join' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<JoinType> getJoin();

	/**
	 * Returns the value of the '<em><b>Milestone</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.MilestoneType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Milestone</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Milestone</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Milestone()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='milestone' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MilestoneType> getMilestone();

	/**
	 * Returns the value of the '<em><b>Sub Process</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.SubProcessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Process</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Process</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_SubProcess()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subProcess' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SubProcessType> getSubProcess();

	/**
	 * Returns the value of the '<em><b>Work Item</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.WorkItemType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Item</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Item</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_WorkItem()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='workItem' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<WorkItemType> getWorkItem();

	/**
	 * Returns the value of the '<em><b>Timer Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.TimerNodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer Node</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_TimerNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timerNode' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TimerNodeType> getTimerNode();

	/**
	 * Returns the value of the '<em><b>Human Task</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.HumanTaskType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human Task</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Human Task</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_HumanTask()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='humanTask' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<HumanTaskType> getHumanTask();

	/**
	 * Returns the value of the '<em><b>Composite</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.CompositeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Composite()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='composite' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<CompositeType> getComposite();

	/**
	 * Returns the value of the '<em><b>For Each</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ForEachType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Each</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For Each</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_ForEach()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='forEach' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ForEachType> getForEach();

	/**
	 * Returns the value of the '<em><b>Event Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.EventNodeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Node</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_EventNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventNode' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EventNodeType> getEventNode();

	/**
	 * Returns the value of the '<em><b>Fault</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.FaultType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fault</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fault</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Fault()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='fault' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<FaultType> getFault();

	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.StateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_State()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='state' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<StateType> getState();

	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.DynamicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getNodesType_Dynamic()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dynamic' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<DynamicType> getDynamic();

} // NodesType
