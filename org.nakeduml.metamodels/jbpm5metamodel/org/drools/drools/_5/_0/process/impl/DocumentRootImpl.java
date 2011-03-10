/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ConstraintsType;
import org.drools.drools._5._0.process.DocumentRoot;
import org.drools.drools._5._0.process.DynamicType;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.EventFilterType;
import org.drools.drools._5._0.process.EventFiltersType;
import org.drools.drools._5._0.process.EventNodeType;
import org.drools.drools._5._0.process.ExceptionHandlerType;
import org.drools.drools._5._0.process.ExceptionHandlersType;
import org.drools.drools._5._0.process.FaultType;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.FunctionImportType;
import org.drools.drools._5._0.process.FunctionImportsType;
import org.drools.drools._5._0.process.GlobalType;
import org.drools.drools._5._0.process.GlobalsType;
import org.drools.drools._5._0.process.HeaderType;
import org.drools.drools._5._0.process.HumanTaskType;
import org.drools.drools._5._0.process.ImportType;
import org.drools.drools._5._0.process.ImportsType;
import org.drools.drools._5._0.process.InPortType;
import org.drools.drools._5._0.process.InPortsType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.MappingType;
import org.drools.drools._5._0.process.MetaDataType;
import org.drools.drools._5._0.process.MilestoneType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.OutPortType;
import org.drools.drools._5._0.process.OutPortsType;
import org.drools.drools._5._0.process.ParameterType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.ProcessType;
import org.drools.drools._5._0.process.RuleSetType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.drools.drools._5._0.process.SubProcessType;
import org.drools.drools._5._0.process.SwimlaneType;
import org.drools.drools._5._0.process.SwimlanesType;
import org.drools.drools._5._0.process.TimerNodeType;
import org.drools.drools._5._0.process.TimerType;
import org.drools.drools._5._0.process.TimersType;
import org.drools.drools._5._0.process.TriggerType;
import org.drools.drools._5._0.process.TriggersType;
import org.drools.drools._5._0.process.TypeType;
import org.drools.drools._5._0.process.ValueType;
import org.drools.drools._5._0.process.VariableType;
import org.drools.drools._5._0.process.VariablesType;
import org.drools.drools._5._0.process.WorkItemType;
import org.drools.drools._5._0.process.WorkType;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getActionNode <em>Action Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getComposite <em>Composite</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getEventFilter <em>Event Filter</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getEventFilters <em>Event Filters</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getEventNode <em>Event Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getExceptionHandlers <em>Exception Handlers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getFault <em>Fault</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getForEach <em>For Each</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getFunctionImport <em>Function Import</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getFunctionImports <em>Function Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getGlobal <em>Global</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getGlobals <em>Globals</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getHumanTask <em>Human Task</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getImport <em>Import</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getInPort <em>In Port</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getInPorts <em>In Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getOnEntry <em>On Entry</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getOnExit <em>On Exit</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getOutPort <em>Out Port</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getOutPorts <em>Out Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getSplit <em>Split</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getState <em>State</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getSwimlanes <em>Swimlanes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getTimerNode <em>Timer Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getTimers <em>Timers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getWork <em>Work</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.DocumentRootImpl#getWorkItem <em>Work Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, ProcessPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType getAction() {
		return (ActionType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(ActionType newAction, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION, newAction, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(ActionType newAction) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION, newAction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionNodeType getActionNode() {
		return (ActionNodeType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionNode(ActionNodeType newActionNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION_NODE, newActionNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionNode(ActionNodeType newActionNode) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__ACTION_NODE, newActionNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeType getComposite() {
		return (CompositeType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__COMPOSITE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComposite(CompositeType newComposite, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__COMPOSITE, newComposite, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComposite(CompositeType newComposite) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__COMPOSITE, newComposite);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionType getConnection() {
		return (ConnectionType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnection(ConnectionType newConnection, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTION, newConnection, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnection(ConnectionType newConnection) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTION, newConnection);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionsType getConnections() {
		return (ConnectionsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnections(ConnectionsType newConnections, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, newConnections, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnections(ConnectionsType newConnections) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, newConnections);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintType getConstraint() {
		return (ConstraintType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstraint(ConstraintType newConstraint, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINT, newConstraint, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraint(ConstraintType newConstraint) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINT, newConstraint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintsType getConstraints() {
		return (ConstraintsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstraints(ConstraintsType newConstraints, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, newConstraints, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraints(ConstraintsType newConstraints) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, newConstraints);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicType getDynamic() {
		return (DynamicType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__DYNAMIC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDynamic(DynamicType newDynamic, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__DYNAMIC, newDynamic, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamic(DynamicType newDynamic) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__DYNAMIC, newDynamic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndType getEnd() {
		return (EndType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__END, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnd(EndType newEnd, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__END, newEnd, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(EndType newEnd) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__END, newEnd);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventFilterType getEventFilter() {
		return (EventFilterType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventFilter(EventFilterType newEventFilter, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTER, newEventFilter, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventFilter(EventFilterType newEventFilter) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTER, newEventFilter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventFiltersType getEventFilters() {
		return (EventFiltersType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventFilters(EventFiltersType newEventFilters, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTERS, newEventFilters, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventFilters(EventFiltersType newEventFilters) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_FILTERS, newEventFilters);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventNodeType getEventNode() {
		return (EventNodeType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventNode(EventNodeType newEventNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_NODE, newEventNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventNode(EventNodeType newEventNode) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__EVENT_NODE, newEventNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlerType getExceptionHandler() {
		return (ExceptionHandlerType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExceptionHandler(ExceptionHandlerType newExceptionHandler, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, newExceptionHandler, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionHandler(ExceptionHandlerType newExceptionHandler) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLER, newExceptionHandler);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlersType getExceptionHandlers() {
		return (ExceptionHandlersType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExceptionHandlers(ExceptionHandlersType newExceptionHandlers, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLERS, newExceptionHandlers, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionHandlers(ExceptionHandlersType newExceptionHandlers) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__EXCEPTION_HANDLERS, newExceptionHandlers);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FaultType getFault() {
		return (FaultType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__FAULT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFault(FaultType newFault, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__FAULT, newFault, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFault(FaultType newFault) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__FAULT, newFault);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForEachType getForEach() {
		return (ForEachType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__FOR_EACH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetForEach(ForEachType newForEach, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__FOR_EACH, newForEach, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForEach(ForEachType newForEach) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__FOR_EACH, newForEach);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionImportType getFunctionImport() {
		return (FunctionImportType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunctionImport(FunctionImportType newFunctionImport, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORT, newFunctionImport, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionImport(FunctionImportType newFunctionImport) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORT, newFunctionImport);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionImportsType getFunctionImports() {
		return (FunctionImportsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunctionImports(FunctionImportsType newFunctionImports, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORTS, newFunctionImports, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionImports(FunctionImportsType newFunctionImports) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__FUNCTION_IMPORTS, newFunctionImports);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalType getGlobal() {
		return (GlobalType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBAL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobal(GlobalType newGlobal, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBAL, newGlobal, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobal(GlobalType newGlobal) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBAL, newGlobal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalsType getGlobals() {
		return (GlobalsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBALS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobals(GlobalsType newGlobals, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBALS, newGlobals, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobals(GlobalsType newGlobals) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__GLOBALS, newGlobals);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeaderType getHeader() {
		return (HeaderType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__HEADER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(HeaderType newHeader, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__HEADER, newHeader, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeader(HeaderType newHeader) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HumanTaskType getHumanTask() {
		return (HumanTaskType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__HUMAN_TASK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHumanTask(HumanTaskType newHumanTask, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__HUMAN_TASK, newHumanTask, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHumanTask(HumanTaskType newHumanTask) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__HUMAN_TASK, newHumanTask);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportType getImport() {
		return (ImportType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImport(ImportType newImport, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORT, newImport, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImport(ImportType newImport) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORT, newImport);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportsType getImports() {
		return (ImportsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImports(ImportsType newImports, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORTS, newImports, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImports(ImportsType newImports) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__IMPORTS, newImports);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPortType getInPort() {
		return (InPortType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInPort(InPortType newInPort, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORT, newInPort, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInPort(InPortType newInPort) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORT, newInPort);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPortsType getInPorts() {
		return (InPortsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInPorts(InPortsType newInPorts, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORTS, newInPorts, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInPorts(InPortsType newInPorts) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__IN_PORTS, newInPorts);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType getJoin() {
		return (JoinType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__JOIN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJoin(JoinType newJoin, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__JOIN, newJoin, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJoin(JoinType newJoin) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__JOIN, newJoin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingType getMapping() {
		return (MappingType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__MAPPING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapping(MappingType newMapping, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__MAPPING, newMapping, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapping(MappingType newMapping) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__MAPPING, newMapping);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaDataType getMetaData() {
		return (MetaDataType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__META_DATA, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMetaData(MetaDataType newMetaData, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__META_DATA, newMetaData, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaData(MetaDataType newMetaData) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__META_DATA, newMetaData);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MilestoneType getMilestone() {
		return (MilestoneType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__MILESTONE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMilestone(MilestoneType newMilestone, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__MILESTONE, newMilestone, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMilestone(MilestoneType newMilestone) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__MILESTONE, newMilestone);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodesType getNodes() {
		return (NodesType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__NODES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodes(NodesType newNodes, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__NODES, newNodes, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodes(NodesType newNodes) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__NODES, newNodes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OnEntryType getOnEntry() {
		return (OnEntryType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__ON_ENTRY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnEntry(OnEntryType newOnEntry, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__ON_ENTRY, newOnEntry, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnEntry(OnEntryType newOnEntry) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__ON_ENTRY, newOnEntry);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OnExitType getOnExit() {
		return (OnExitType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__ON_EXIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnExit(OnExitType newOnExit, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__ON_EXIT, newOnExit, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnExit(OnExitType newOnExit) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__ON_EXIT, newOnExit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPortType getOutPort() {
		return (OutPortType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutPort(OutPortType newOutPort, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORT, newOutPort, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutPort(OutPortType newOutPort) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORT, newOutPort);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPortsType getOutPorts() {
		return (OutPortsType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutPorts(OutPortsType newOutPorts, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORTS, newOutPorts, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutPorts(OutPortsType newOutPorts) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__OUT_PORTS, newOutPorts);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterType getParameter() {
		return (ParameterType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__PARAMETER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParameter(ParameterType newParameter, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__PARAMETER, newParameter, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(ParameterType newParameter) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__PARAMETER, newParameter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessType getProcess() {
		return (ProcessType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(ProcessType newProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__PROCESS, newProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcess(ProcessType newProcess) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__PROCESS, newProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleSetType getRuleSet() {
		return (RuleSetType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__RULE_SET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRuleSet(RuleSetType newRuleSet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__RULE_SET, newRuleSet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuleSet(RuleSetType newRuleSet) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__RULE_SET, newRuleSet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SplitType getSplit() {
		return (SplitType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__SPLIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSplit(SplitType newSplit, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__SPLIT, newSplit, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplit(SplitType newSplit) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__SPLIT, newSplit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartType getStart() {
		return (StartType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__START, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStart(StartType newStart, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__START, newStart, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(StartType newStart) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__START, newStart);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateType getState() {
		return (StateType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__STATE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetState(StateType newState, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__STATE, newState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(StateType newState) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__STATE, newState);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessType getSubProcess() {
		return (SubProcessType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubProcess(SubProcessType newSubProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, newSubProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubProcess(SubProcessType newSubProcess) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS, newSubProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlaneType getSwimlane() {
		return (SwimlaneType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwimlane(SwimlaneType newSwimlane, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANE, newSwimlane, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwimlane(SwimlaneType newSwimlane) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANE, newSwimlane);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlanesType getSwimlanes() {
		return (SwimlanesType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwimlanes(SwimlanesType newSwimlanes, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANES, newSwimlanes, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwimlanes(SwimlanesType newSwimlanes) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__SWIMLANES, newSwimlanes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerType getTimer() {
		return (TimerType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimer(TimerType newTimer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER, newTimer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimer(TimerType newTimer) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER, newTimer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerNodeType getTimerNode() {
		return (TimerNodeType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER_NODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimerNode(TimerNodeType newTimerNode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER_NODE, newTimerNode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimerNode(TimerNodeType newTimerNode) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TIMER_NODE, newTimerNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimersType getTimers() {
		return (TimersType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TIMERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimers(TimersType newTimers, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TIMERS, newTimers, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimers(TimersType newTimers) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TIMERS, newTimers);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerType getTrigger() {
		return (TriggerType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(TriggerType newTrigger, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGER, newTrigger, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrigger(TriggerType newTrigger) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGER, newTrigger);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggersType getTriggers() {
		return (TriggersType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTriggers(TriggersType newTriggers, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGERS, newTriggers, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTriggers(TriggersType newTriggers) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TRIGGERS, newTriggers);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType getType() {
		return (TypeType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(TypeType newType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__TYPE, newType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeType newType) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType getValue() {
		return (ValueType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(ValueType newValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__VALUE, newValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(ValueType newValue) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType getVariable() {
		return (VariableType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariable(VariableType newVariable, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLE, newVariable, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(VariableType newVariable) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLE, newVariable);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariablesType getVariables() {
		return (VariablesType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariables(VariablesType newVariables, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLES, newVariables, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariables(VariablesType newVariables) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__VARIABLES, newVariables);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkType getWork() {
		return (WorkType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__WORK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWork(WorkType newWork, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__WORK, newWork, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWork(WorkType newWork) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__WORK, newWork);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkItemType getWorkItem() {
		return (WorkItemType)getMixed().get(ProcessPackage.Literals.DOCUMENT_ROOT__WORK_ITEM, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkItem(WorkItemType newWorkItem, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ProcessPackage.Literals.DOCUMENT_ROOT__WORK_ITEM, newWorkItem, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkItem(WorkItemType newWorkItem) {
		((FeatureMap.Internal)getMixed()).set(ProcessPackage.Literals.DOCUMENT_ROOT__WORK_ITEM, newWorkItem);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case ProcessPackage.DOCUMENT_ROOT__ACTION:
				return basicSetAction(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__ACTION_NODE:
				return basicSetActionNode(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__COMPOSITE:
				return basicSetComposite(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__CONNECTION:
				return basicSetConnection(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__CONNECTIONS:
				return basicSetConnections(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINT:
				return basicSetConstraint(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINTS:
				return basicSetConstraints(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__DYNAMIC:
				return basicSetDynamic(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__END:
				return basicSetEnd(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTER:
				return basicSetEventFilter(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTERS:
				return basicSetEventFilters(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__EVENT_NODE:
				return basicSetEventNode(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return basicSetExceptionHandler(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLERS:
				return basicSetExceptionHandlers(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__FAULT:
				return basicSetFault(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__FOR_EACH:
				return basicSetForEach(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORT:
				return basicSetFunctionImport(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORTS:
				return basicSetFunctionImports(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__GLOBAL:
				return basicSetGlobal(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__GLOBALS:
				return basicSetGlobals(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__HEADER:
				return basicSetHeader(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__HUMAN_TASK:
				return basicSetHumanTask(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__IMPORT:
				return basicSetImport(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__IMPORTS:
				return basicSetImports(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__IN_PORT:
				return basicSetInPort(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__IN_PORTS:
				return basicSetInPorts(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__JOIN:
				return basicSetJoin(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__MAPPING:
				return basicSetMapping(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__META_DATA:
				return basicSetMetaData(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__MILESTONE:
				return basicSetMilestone(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__NODES:
				return basicSetNodes(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__ON_ENTRY:
				return basicSetOnEntry(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__ON_EXIT:
				return basicSetOnExit(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORT:
				return basicSetOutPort(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORTS:
				return basicSetOutPorts(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__PARAMETER:
				return basicSetParameter(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__PROCESS:
				return basicSetProcess(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__RULE_SET:
				return basicSetRuleSet(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__SPLIT:
				return basicSetSplit(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__START:
				return basicSetStart(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__STATE:
				return basicSetState(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return basicSetSubProcess(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANE:
				return basicSetSwimlane(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANES:
				return basicSetSwimlanes(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TIMER:
				return basicSetTimer(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TIMER_NODE:
				return basicSetTimerNode(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TIMERS:
				return basicSetTimers(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TRIGGER:
				return basicSetTrigger(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TRIGGERS:
				return basicSetTriggers(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__TYPE:
				return basicSetType(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__VALUE:
				return basicSetValue(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__VARIABLE:
				return basicSetVariable(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__VARIABLES:
				return basicSetVariables(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__WORK:
				return basicSetWork(null, msgs);
			case ProcessPackage.DOCUMENT_ROOT__WORK_ITEM:
				return basicSetWorkItem(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case ProcessPackage.DOCUMENT_ROOT__ACTION:
				return getAction();
			case ProcessPackage.DOCUMENT_ROOT__ACTION_NODE:
				return getActionNode();
			case ProcessPackage.DOCUMENT_ROOT__COMPOSITE:
				return getComposite();
			case ProcessPackage.DOCUMENT_ROOT__CONNECTION:
				return getConnection();
			case ProcessPackage.DOCUMENT_ROOT__CONNECTIONS:
				return getConnections();
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINT:
				return getConstraint();
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINTS:
				return getConstraints();
			case ProcessPackage.DOCUMENT_ROOT__DYNAMIC:
				return getDynamic();
			case ProcessPackage.DOCUMENT_ROOT__END:
				return getEnd();
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTER:
				return getEventFilter();
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTERS:
				return getEventFilters();
			case ProcessPackage.DOCUMENT_ROOT__EVENT_NODE:
				return getEventNode();
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return getExceptionHandler();
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLERS:
				return getExceptionHandlers();
			case ProcessPackage.DOCUMENT_ROOT__FAULT:
				return getFault();
			case ProcessPackage.DOCUMENT_ROOT__FOR_EACH:
				return getForEach();
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORT:
				return getFunctionImport();
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORTS:
				return getFunctionImports();
			case ProcessPackage.DOCUMENT_ROOT__GLOBAL:
				return getGlobal();
			case ProcessPackage.DOCUMENT_ROOT__GLOBALS:
				return getGlobals();
			case ProcessPackage.DOCUMENT_ROOT__HEADER:
				return getHeader();
			case ProcessPackage.DOCUMENT_ROOT__HUMAN_TASK:
				return getHumanTask();
			case ProcessPackage.DOCUMENT_ROOT__IMPORT:
				return getImport();
			case ProcessPackage.DOCUMENT_ROOT__IMPORTS:
				return getImports();
			case ProcessPackage.DOCUMENT_ROOT__IN_PORT:
				return getInPort();
			case ProcessPackage.DOCUMENT_ROOT__IN_PORTS:
				return getInPorts();
			case ProcessPackage.DOCUMENT_ROOT__JOIN:
				return getJoin();
			case ProcessPackage.DOCUMENT_ROOT__MAPPING:
				return getMapping();
			case ProcessPackage.DOCUMENT_ROOT__META_DATA:
				return getMetaData();
			case ProcessPackage.DOCUMENT_ROOT__MILESTONE:
				return getMilestone();
			case ProcessPackage.DOCUMENT_ROOT__NODES:
				return getNodes();
			case ProcessPackage.DOCUMENT_ROOT__ON_ENTRY:
				return getOnEntry();
			case ProcessPackage.DOCUMENT_ROOT__ON_EXIT:
				return getOnExit();
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORT:
				return getOutPort();
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORTS:
				return getOutPorts();
			case ProcessPackage.DOCUMENT_ROOT__PARAMETER:
				return getParameter();
			case ProcessPackage.DOCUMENT_ROOT__PROCESS:
				return getProcess();
			case ProcessPackage.DOCUMENT_ROOT__RULE_SET:
				return getRuleSet();
			case ProcessPackage.DOCUMENT_ROOT__SPLIT:
				return getSplit();
			case ProcessPackage.DOCUMENT_ROOT__START:
				return getStart();
			case ProcessPackage.DOCUMENT_ROOT__STATE:
				return getState();
			case ProcessPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return getSubProcess();
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANE:
				return getSwimlane();
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANES:
				return getSwimlanes();
			case ProcessPackage.DOCUMENT_ROOT__TIMER:
				return getTimer();
			case ProcessPackage.DOCUMENT_ROOT__TIMER_NODE:
				return getTimerNode();
			case ProcessPackage.DOCUMENT_ROOT__TIMERS:
				return getTimers();
			case ProcessPackage.DOCUMENT_ROOT__TRIGGER:
				return getTrigger();
			case ProcessPackage.DOCUMENT_ROOT__TRIGGERS:
				return getTriggers();
			case ProcessPackage.DOCUMENT_ROOT__TYPE:
				return getType();
			case ProcessPackage.DOCUMENT_ROOT__VALUE:
				return getValue();
			case ProcessPackage.DOCUMENT_ROOT__VARIABLE:
				return getVariable();
			case ProcessPackage.DOCUMENT_ROOT__VARIABLES:
				return getVariables();
			case ProcessPackage.DOCUMENT_ROOT__WORK:
				return getWork();
			case ProcessPackage.DOCUMENT_ROOT__WORK_ITEM:
				return getWorkItem();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProcessPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ACTION:
				setAction((ActionType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ACTION_NODE:
				setActionNode((ActionNodeType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__COMPOSITE:
				setComposite((CompositeType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTION:
				setConnection((ConnectionType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTIONS:
				setConnections((ConnectionsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINT:
				setConstraint((ConstraintType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINTS:
				setConstraints((ConstraintsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__DYNAMIC:
				setDynamic((DynamicType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__END:
				setEnd((EndType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTER:
				setEventFilter((EventFilterType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTERS:
				setEventFilters((EventFiltersType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_NODE:
				setEventNode((EventNodeType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				setExceptionHandler((ExceptionHandlerType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLERS:
				setExceptionHandlers((ExceptionHandlersType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FAULT:
				setFault((FaultType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FOR_EACH:
				setForEach((ForEachType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORT:
				setFunctionImport((FunctionImportType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORTS:
				setFunctionImports((FunctionImportsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__GLOBAL:
				setGlobal((GlobalType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__GLOBALS:
				setGlobals((GlobalsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__HEADER:
				setHeader((HeaderType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__HUMAN_TASK:
				setHumanTask((HumanTaskType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IMPORT:
				setImport((ImportType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IMPORTS:
				setImports((ImportsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORT:
				setInPort((InPortType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORTS:
				setInPorts((InPortsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__MAPPING:
				setMapping((MappingType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__META_DATA:
				setMetaData((MetaDataType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__MILESTONE:
				setMilestone((MilestoneType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__NODES:
				setNodes((NodesType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ON_ENTRY:
				setOnEntry((OnEntryType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ON_EXIT:
				setOnExit((OnExitType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORT:
				setOutPort((OutPortType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORTS:
				setOutPorts((OutPortsType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__PARAMETER:
				setParameter((ParameterType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__PROCESS:
				setProcess((ProcessType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__RULE_SET:
				setRuleSet((RuleSetType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SPLIT:
				setSplit((SplitType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__START:
				setStart((StartType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__STATE:
				setState((StateType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SUB_PROCESS:
				setSubProcess((SubProcessType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANE:
				setSwimlane((SwimlaneType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANES:
				setSwimlanes((SwimlanesType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMER:
				setTimer((TimerType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMER_NODE:
				setTimerNode((TimerNodeType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMERS:
				setTimers((TimersType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGER:
				setTrigger((TriggerType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGERS:
				setTriggers((TriggersType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TYPE:
				setType((TypeType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VALUE:
				setValue((ValueType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLE:
				setVariable((VariableType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLES:
				setVariables((VariablesType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__WORK:
				setWork((WorkType)newValue);
				return;
			case ProcessPackage.DOCUMENT_ROOT__WORK_ITEM:
				setWorkItem((WorkItemType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ProcessPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case ProcessPackage.DOCUMENT_ROOT__ACTION:
				setAction((ActionType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ACTION_NODE:
				setActionNode((ActionNodeType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__COMPOSITE:
				setComposite((CompositeType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTION:
				setConnection((ConnectionType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTIONS:
				setConnections((ConnectionsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINT:
				setConstraint((ConstraintType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINTS:
				setConstraints((ConstraintsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__DYNAMIC:
				setDynamic((DynamicType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__END:
				setEnd((EndType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTER:
				setEventFilter((EventFilterType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTERS:
				setEventFilters((EventFiltersType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_NODE:
				setEventNode((EventNodeType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				setExceptionHandler((ExceptionHandlerType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLERS:
				setExceptionHandlers((ExceptionHandlersType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FAULT:
				setFault((FaultType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FOR_EACH:
				setForEach((ForEachType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORT:
				setFunctionImport((FunctionImportType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORTS:
				setFunctionImports((FunctionImportsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__GLOBAL:
				setGlobal((GlobalType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__GLOBALS:
				setGlobals((GlobalsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__HEADER:
				setHeader((HeaderType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__HUMAN_TASK:
				setHumanTask((HumanTaskType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IMPORT:
				setImport((ImportType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IMPORTS:
				setImports((ImportsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORT:
				setInPort((InPortType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORTS:
				setInPorts((InPortsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__MAPPING:
				setMapping((MappingType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__META_DATA:
				setMetaData((MetaDataType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__MILESTONE:
				setMilestone((MilestoneType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__NODES:
				setNodes((NodesType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ON_ENTRY:
				setOnEntry((OnEntryType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__ON_EXIT:
				setOnExit((OnExitType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORT:
				setOutPort((OutPortType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORTS:
				setOutPorts((OutPortsType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__PARAMETER:
				setParameter((ParameterType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__PROCESS:
				setProcess((ProcessType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__RULE_SET:
				setRuleSet((RuleSetType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SPLIT:
				setSplit((SplitType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__START:
				setStart((StartType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__STATE:
				setState((StateType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SUB_PROCESS:
				setSubProcess((SubProcessType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANE:
				setSwimlane((SwimlaneType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANES:
				setSwimlanes((SwimlanesType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMER:
				setTimer((TimerType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMER_NODE:
				setTimerNode((TimerNodeType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TIMERS:
				setTimers((TimersType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGER:
				setTrigger((TriggerType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGERS:
				setTriggers((TriggersType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__TYPE:
				setType((TypeType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VALUE:
				setValue((ValueType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLE:
				setVariable((VariableType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLES:
				setVariables((VariablesType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__WORK:
				setWork((WorkType)null);
				return;
			case ProcessPackage.DOCUMENT_ROOT__WORK_ITEM:
				setWorkItem((WorkItemType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ProcessPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case ProcessPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case ProcessPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case ProcessPackage.DOCUMENT_ROOT__ACTION:
				return getAction() != null;
			case ProcessPackage.DOCUMENT_ROOT__ACTION_NODE:
				return getActionNode() != null;
			case ProcessPackage.DOCUMENT_ROOT__COMPOSITE:
				return getComposite() != null;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTION:
				return getConnection() != null;
			case ProcessPackage.DOCUMENT_ROOT__CONNECTIONS:
				return getConnections() != null;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINT:
				return getConstraint() != null;
			case ProcessPackage.DOCUMENT_ROOT__CONSTRAINTS:
				return getConstraints() != null;
			case ProcessPackage.DOCUMENT_ROOT__DYNAMIC:
				return getDynamic() != null;
			case ProcessPackage.DOCUMENT_ROOT__END:
				return getEnd() != null;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTER:
				return getEventFilter() != null;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_FILTERS:
				return getEventFilters() != null;
			case ProcessPackage.DOCUMENT_ROOT__EVENT_NODE:
				return getEventNode() != null;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLER:
				return getExceptionHandler() != null;
			case ProcessPackage.DOCUMENT_ROOT__EXCEPTION_HANDLERS:
				return getExceptionHandlers() != null;
			case ProcessPackage.DOCUMENT_ROOT__FAULT:
				return getFault() != null;
			case ProcessPackage.DOCUMENT_ROOT__FOR_EACH:
				return getForEach() != null;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORT:
				return getFunctionImport() != null;
			case ProcessPackage.DOCUMENT_ROOT__FUNCTION_IMPORTS:
				return getFunctionImports() != null;
			case ProcessPackage.DOCUMENT_ROOT__GLOBAL:
				return getGlobal() != null;
			case ProcessPackage.DOCUMENT_ROOT__GLOBALS:
				return getGlobals() != null;
			case ProcessPackage.DOCUMENT_ROOT__HEADER:
				return getHeader() != null;
			case ProcessPackage.DOCUMENT_ROOT__HUMAN_TASK:
				return getHumanTask() != null;
			case ProcessPackage.DOCUMENT_ROOT__IMPORT:
				return getImport() != null;
			case ProcessPackage.DOCUMENT_ROOT__IMPORTS:
				return getImports() != null;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORT:
				return getInPort() != null;
			case ProcessPackage.DOCUMENT_ROOT__IN_PORTS:
				return getInPorts() != null;
			case ProcessPackage.DOCUMENT_ROOT__JOIN:
				return getJoin() != null;
			case ProcessPackage.DOCUMENT_ROOT__MAPPING:
				return getMapping() != null;
			case ProcessPackage.DOCUMENT_ROOT__META_DATA:
				return getMetaData() != null;
			case ProcessPackage.DOCUMENT_ROOT__MILESTONE:
				return getMilestone() != null;
			case ProcessPackage.DOCUMENT_ROOT__NODES:
				return getNodes() != null;
			case ProcessPackage.DOCUMENT_ROOT__ON_ENTRY:
				return getOnEntry() != null;
			case ProcessPackage.DOCUMENT_ROOT__ON_EXIT:
				return getOnExit() != null;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORT:
				return getOutPort() != null;
			case ProcessPackage.DOCUMENT_ROOT__OUT_PORTS:
				return getOutPorts() != null;
			case ProcessPackage.DOCUMENT_ROOT__PARAMETER:
				return getParameter() != null;
			case ProcessPackage.DOCUMENT_ROOT__PROCESS:
				return getProcess() != null;
			case ProcessPackage.DOCUMENT_ROOT__RULE_SET:
				return getRuleSet() != null;
			case ProcessPackage.DOCUMENT_ROOT__SPLIT:
				return getSplit() != null;
			case ProcessPackage.DOCUMENT_ROOT__START:
				return getStart() != null;
			case ProcessPackage.DOCUMENT_ROOT__STATE:
				return getState() != null;
			case ProcessPackage.DOCUMENT_ROOT__SUB_PROCESS:
				return getSubProcess() != null;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANE:
				return getSwimlane() != null;
			case ProcessPackage.DOCUMENT_ROOT__SWIMLANES:
				return getSwimlanes() != null;
			case ProcessPackage.DOCUMENT_ROOT__TIMER:
				return getTimer() != null;
			case ProcessPackage.DOCUMENT_ROOT__TIMER_NODE:
				return getTimerNode() != null;
			case ProcessPackage.DOCUMENT_ROOT__TIMERS:
				return getTimers() != null;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGER:
				return getTrigger() != null;
			case ProcessPackage.DOCUMENT_ROOT__TRIGGERS:
				return getTriggers() != null;
			case ProcessPackage.DOCUMENT_ROOT__TYPE:
				return getType() != null;
			case ProcessPackage.DOCUMENT_ROOT__VALUE:
				return getValue() != null;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLE:
				return getVariable() != null;
			case ProcessPackage.DOCUMENT_ROOT__VARIABLES:
				return getVariables() != null;
			case ProcessPackage.DOCUMENT_ROOT__WORK:
				return getWork() != null;
			case ProcessPackage.DOCUMENT_ROOT__WORK_ITEM:
				return getWorkItem() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
