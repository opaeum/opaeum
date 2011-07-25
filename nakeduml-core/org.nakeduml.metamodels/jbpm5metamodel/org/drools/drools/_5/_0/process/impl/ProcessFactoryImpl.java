/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import org.drools.drools._5._0.process.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessFactoryImpl extends EFactoryImpl implements ProcessFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ProcessFactory init() {
		try {
			ProcessFactory theProcessFactory = (ProcessFactory)EPackage.Registry.INSTANCE.getEFactory("http://drools.org/drools-5.0/process"); 
			if (theProcessFactory != null) {
				return theProcessFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ProcessFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ProcessPackage.ACTION_NODE_TYPE: return createActionNodeType();
			case ProcessPackage.ACTION_TYPE: return createActionType();
			case ProcessPackage.COMPOSITE_TYPE: return createCompositeType();
			case ProcessPackage.CONNECTIONS_TYPE: return createConnectionsType();
			case ProcessPackage.CONNECTION_TYPE: return createConnectionType();
			case ProcessPackage.CONSTRAINTS_TYPE: return createConstraintsType();
			case ProcessPackage.CONSTRAINT_TYPE: return createConstraintType();
			case ProcessPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ProcessPackage.DYNAMIC_TYPE: return createDynamicType();
			case ProcessPackage.END_TYPE: return createEndType();
			case ProcessPackage.EVENT_FILTERS_TYPE: return createEventFiltersType();
			case ProcessPackage.EVENT_FILTER_TYPE: return createEventFilterType();
			case ProcessPackage.EVENT_NODE_TYPE: return createEventNodeType();
			case ProcessPackage.EXCEPTION_HANDLERS_TYPE: return createExceptionHandlersType();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE: return createExceptionHandlerType();
			case ProcessPackage.FAULT_TYPE: return createFaultType();
			case ProcessPackage.FOR_EACH_TYPE: return createForEachType();
			case ProcessPackage.FUNCTION_IMPORTS_TYPE: return createFunctionImportsType();
			case ProcessPackage.FUNCTION_IMPORT_TYPE: return createFunctionImportType();
			case ProcessPackage.GLOBALS_TYPE: return createGlobalsType();
			case ProcessPackage.GLOBAL_TYPE: return createGlobalType();
			case ProcessPackage.HEADER_TYPE: return createHeaderType();
			case ProcessPackage.HUMAN_TASK_TYPE: return createHumanTaskType();
			case ProcessPackage.IMPORTS_TYPE: return createImportsType();
			case ProcessPackage.IMPORT_TYPE: return createImportType();
			case ProcessPackage.IN_PORTS_TYPE: return createInPortsType();
			case ProcessPackage.IN_PORT_TYPE: return createInPortType();
			case ProcessPackage.JOIN_TYPE: return createJoinType();
			case ProcessPackage.MAPPING_TYPE: return createMappingType();
			case ProcessPackage.META_DATA_TYPE: return createMetaDataType();
			case ProcessPackage.MILESTONE_TYPE: return createMilestoneType();
			case ProcessPackage.NODES_TYPE: return createNodesType();
			case ProcessPackage.ON_ENTRY_TYPE: return createOnEntryType();
			case ProcessPackage.ON_EXIT_TYPE: return createOnExitType();
			case ProcessPackage.OUT_PORTS_TYPE: return createOutPortsType();
			case ProcessPackage.OUT_PORT_TYPE: return createOutPortType();
			case ProcessPackage.PARAMETER_TYPE: return createParameterType();
			case ProcessPackage.PROCESS_TYPE: return createProcessType();
			case ProcessPackage.RULE_SET_TYPE: return createRuleSetType();
			case ProcessPackage.SPLIT_TYPE: return createSplitType();
			case ProcessPackage.START_TYPE: return createStartType();
			case ProcessPackage.STATE_TYPE: return createStateType();
			case ProcessPackage.SUB_PROCESS_TYPE: return createSubProcessType();
			case ProcessPackage.SWIMLANES_TYPE: return createSwimlanesType();
			case ProcessPackage.SWIMLANE_TYPE: return createSwimlaneType();
			case ProcessPackage.TIMER_NODE_TYPE: return createTimerNodeType();
			case ProcessPackage.TIMERS_TYPE: return createTimersType();
			case ProcessPackage.TIMER_TYPE: return createTimerType();
			case ProcessPackage.TRIGGERS_TYPE: return createTriggersType();
			case ProcessPackage.TRIGGER_TYPE: return createTriggerType();
			case ProcessPackage.TYPE_TYPE: return createTypeType();
			case ProcessPackage.VALUE_TYPE: return createValueType();
			case ProcessPackage.VARIABLES_TYPE: return createVariablesType();
			case ProcessPackage.VARIABLE_TYPE: return createVariableType();
			case ProcessPackage.WORK_ITEM_TYPE: return createWorkItemType();
			case ProcessPackage.WORK_TYPE: return createWorkType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionNodeType createActionNodeType() {
		ActionNodeTypeImpl actionNodeType = new ActionNodeTypeImpl();
		return actionNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType createActionType() {
		ActionTypeImpl actionType = new ActionTypeImpl();
		return actionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeType createCompositeType() {
		CompositeTypeImpl compositeType = new CompositeTypeImpl();
		return compositeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionsType createConnectionsType() {
		ConnectionsTypeImpl connectionsType = new ConnectionsTypeImpl();
		return connectionsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionType createConnectionType() {
		ConnectionTypeImpl connectionType = new ConnectionTypeImpl();
		return connectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintsType createConstraintsType() {
		ConstraintsTypeImpl constraintsType = new ConstraintsTypeImpl();
		return constraintsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintType createConstraintType() {
		ConstraintTypeImpl constraintType = new ConstraintTypeImpl();
		return constraintType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicType createDynamicType() {
		DynamicTypeImpl dynamicType = new DynamicTypeImpl();
		return dynamicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndType createEndType() {
		EndTypeImpl endType = new EndTypeImpl();
		return endType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventFiltersType createEventFiltersType() {
		EventFiltersTypeImpl eventFiltersType = new EventFiltersTypeImpl();
		return eventFiltersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventFilterType createEventFilterType() {
		EventFilterTypeImpl eventFilterType = new EventFilterTypeImpl();
		return eventFilterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventNodeType createEventNodeType() {
		EventNodeTypeImpl eventNodeType = new EventNodeTypeImpl();
		return eventNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlersType createExceptionHandlersType() {
		ExceptionHandlersTypeImpl exceptionHandlersType = new ExceptionHandlersTypeImpl();
		return exceptionHandlersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlerType createExceptionHandlerType() {
		ExceptionHandlerTypeImpl exceptionHandlerType = new ExceptionHandlerTypeImpl();
		return exceptionHandlerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FaultType createFaultType() {
		FaultTypeImpl faultType = new FaultTypeImpl();
		return faultType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForEachType createForEachType() {
		ForEachTypeImpl forEachType = new ForEachTypeImpl();
		return forEachType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionImportsType createFunctionImportsType() {
		FunctionImportsTypeImpl functionImportsType = new FunctionImportsTypeImpl();
		return functionImportsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionImportType createFunctionImportType() {
		FunctionImportTypeImpl functionImportType = new FunctionImportTypeImpl();
		return functionImportType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalsType createGlobalsType() {
		GlobalsTypeImpl globalsType = new GlobalsTypeImpl();
		return globalsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlobalType createGlobalType() {
		GlobalTypeImpl globalType = new GlobalTypeImpl();
		return globalType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeaderType createHeaderType() {
		HeaderTypeImpl headerType = new HeaderTypeImpl();
		return headerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HumanTaskType createHumanTaskType() {
		HumanTaskTypeImpl humanTaskType = new HumanTaskTypeImpl();
		return humanTaskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportsType createImportsType() {
		ImportsTypeImpl importsType = new ImportsTypeImpl();
		return importsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportType createImportType() {
		ImportTypeImpl importType = new ImportTypeImpl();
		return importType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPortsType createInPortsType() {
		InPortsTypeImpl inPortsType = new InPortsTypeImpl();
		return inPortsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InPortType createInPortType() {
		InPortTypeImpl inPortType = new InPortTypeImpl();
		return inPortType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType createJoinType() {
		JoinTypeImpl joinType = new JoinTypeImpl();
		return joinType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingType createMappingType() {
		MappingTypeImpl mappingType = new MappingTypeImpl();
		return mappingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaDataType createMetaDataType() {
		MetaDataTypeImpl metaDataType = new MetaDataTypeImpl();
		return metaDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MilestoneType createMilestoneType() {
		MilestoneTypeImpl milestoneType = new MilestoneTypeImpl();
		return milestoneType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodesType createNodesType() {
		NodesTypeImpl nodesType = new NodesTypeImpl();
		return nodesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OnEntryType createOnEntryType() {
		OnEntryTypeImpl onEntryType = new OnEntryTypeImpl();
		return onEntryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OnExitType createOnExitType() {
		OnExitTypeImpl onExitType = new OnExitTypeImpl();
		return onExitType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPortsType createOutPortsType() {
		OutPortsTypeImpl outPortsType = new OutPortsTypeImpl();
		return outPortsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPortType createOutPortType() {
		OutPortTypeImpl outPortType = new OutPortTypeImpl();
		return outPortType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterType createParameterType() {
		ParameterTypeImpl parameterType = new ParameterTypeImpl();
		return parameterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessType createProcessType() {
		ProcessTypeImpl processType = new ProcessTypeImpl();
		return processType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleSetType createRuleSetType() {
		RuleSetTypeImpl ruleSetType = new RuleSetTypeImpl();
		return ruleSetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SplitType createSplitType() {
		SplitTypeImpl splitType = new SplitTypeImpl();
		return splitType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartType createStartType() {
		StartTypeImpl startType = new StartTypeImpl();
		return startType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateType createStateType() {
		StateTypeImpl stateType = new StateTypeImpl();
		return stateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessType createSubProcessType() {
		SubProcessTypeImpl subProcessType = new SubProcessTypeImpl();
		return subProcessType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlanesType createSwimlanesType() {
		SwimlanesTypeImpl swimlanesType = new SwimlanesTypeImpl();
		return swimlanesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlaneType createSwimlaneType() {
		SwimlaneTypeImpl swimlaneType = new SwimlaneTypeImpl();
		return swimlaneType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerNodeType createTimerNodeType() {
		TimerNodeTypeImpl timerNodeType = new TimerNodeTypeImpl();
		return timerNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimersType createTimersType() {
		TimersTypeImpl timersType = new TimersTypeImpl();
		return timersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerType createTimerType() {
		TimerTypeImpl timerType = new TimerTypeImpl();
		return timerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggersType createTriggersType() {
		TriggersTypeImpl triggersType = new TriggersTypeImpl();
		return triggersType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerType createTriggerType() {
		TriggerTypeImpl triggerType = new TriggerTypeImpl();
		return triggerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType createTypeType() {
		TypeTypeImpl typeType = new TypeTypeImpl();
		return typeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType createValueType() {
		ValueTypeImpl valueType = new ValueTypeImpl();
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariablesType createVariablesType() {
		VariablesTypeImpl variablesType = new VariablesTypeImpl();
		return variablesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType createVariableType() {
		VariableTypeImpl variableType = new VariableTypeImpl();
		return variableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkItemType createWorkItemType() {
		WorkItemTypeImpl workItemType = new WorkItemTypeImpl();
		return workItemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkType createWorkType() {
		WorkTypeImpl workType = new WorkTypeImpl();
		return workType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPackage getProcessPackage() {
		return (ProcessPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ProcessPackage getPackage() {
		return ProcessPackage.eINSTANCE;
	}

} //ProcessFactoryImpl
