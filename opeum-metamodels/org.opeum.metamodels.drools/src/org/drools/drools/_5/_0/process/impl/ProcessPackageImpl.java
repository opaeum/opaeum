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
import org.drools.drools._5._0.process.ProcessFactory;
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessPackageImpl extends EPackageImpl implements ProcessPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventFiltersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventFilterTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionHandlersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionHandlerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass faultTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forEachTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionImportsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionImportTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass globalsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass globalTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass headerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass humanTaskTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inPortsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inPortTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass milestoneTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass onEntryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass onExitTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outPortsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outPortTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleSetTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass splitTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subProcessTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swimlanesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swimlaneTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variablesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workItemTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.drools.drools._5._0.process.ProcessPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ProcessPackageImpl() {
		super(eNS_URI, ProcessFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ProcessPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ProcessPackage init() {
		if (isInited) return (ProcessPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);

		// Obtain or create and register package
		ProcessPackageImpl theProcessPackage = (ProcessPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProcessPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProcessPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theProcessPackage.createPackageContents();

		// Initialize created meta-data
		theProcessPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theProcessPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ProcessPackage.eNS_URI, theProcessPackage);
		return theProcessPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionNodeType() {
		return actionNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Group() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionNodeType_MetaData() {
		return (EReference)actionNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionNodeType_Action() {
		return (EReference)actionNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Height() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Id() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Name() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Width() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_X() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionNodeType_Y() {
		return (EAttribute)actionNodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionType() {
		return actionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Value() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Dialect() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Name() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Type() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeType() {
		return compositeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Group() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_MetaData() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_Timers() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_OnEntry() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_OnExit() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_Variables() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_ExceptionHandlers() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_Nodes() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_Connections() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_InPorts() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeType_OutPorts() {
		return (EReference)compositeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Height() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Id() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Name() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Width() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_X() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeType_Y() {
		return (EAttribute)compositeTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionsType() {
		return connectionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionsType_Group() {
		return (EAttribute)connectionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionsType_Connection() {
		return (EReference)connectionsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionType() {
		return connectionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionType_Bendpoints() {
		return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionType_From() {
		return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionType_FromType() {
		return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionType_To() {
		return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionType_ToType() {
		return (EAttribute)connectionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintsType() {
		return constraintsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintsType_Group() {
		return (EAttribute)constraintsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintsType_Constraint() {
		return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintType() {
		return constraintTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_Value() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_Dialect() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_Name() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_Priority() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_ToNodeId() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_ToType() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintType_Type() {
		return (EAttribute)constraintTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Action() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ActionNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Composite() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Connection() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Connections() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Constraint() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Constraints() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Dynamic() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_End() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EventFilter() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EventFilters() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EventNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExceptionHandler() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExceptionHandlers() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Fault() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ForEach() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FunctionImport() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FunctionImports() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Global() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Globals() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Header() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_HumanTask() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Import() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Imports() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_InPort() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_InPorts() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Join() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Mapping() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MetaData() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Milestone() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Nodes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OnEntry() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OnExit() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OutPort() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OutPorts() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Parameter() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Process() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_RuleSet() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Split() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Start() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_State() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SubProcess() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Swimlane() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Swimlanes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Timer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TimerNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Timers() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Trigger() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Triggers() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Type() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Value() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Variable() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Variables() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Work() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_WorkItem() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicType() {
		return dynamicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Group() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_MetaData() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_Variables() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_ExceptionHandlers() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_Nodes() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_Connections() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_InPorts() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicType_OutPorts() {
		return (EReference)dynamicTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Height() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Id() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Name() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Width() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_X() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicType_Y() {
		return (EAttribute)dynamicTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEndType() {
		return endTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Group() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEndType_MetaData() {
		return (EReference)endTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Height() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Id() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Name() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Terminate() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Width() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_X() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndType_Y() {
		return (EAttribute)endTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventFiltersType() {
		return eventFiltersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventFiltersType_Group() {
		return (EAttribute)eventFiltersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventFiltersType_EventFilter() {
		return (EReference)eventFiltersTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventFilterType() {
		return eventFilterTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventFilterType_EventType() {
		return (EAttribute)eventFilterTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventFilterType_Type() {
		return (EAttribute)eventFilterTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventNodeType() {
		return eventNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Group() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventNodeType_MetaData() {
		return (EReference)eventNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventNodeType_EventFilters() {
		return (EReference)eventNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Height() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Id() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Name() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Scope() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_VariableName() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Width() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_X() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventNodeType_Y() {
		return (EAttribute)eventNodeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExceptionHandlersType() {
		return exceptionHandlersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlersType_Group() {
		return (EAttribute)exceptionHandlersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionHandlersType_ExceptionHandler() {
		return (EReference)exceptionHandlersTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExceptionHandlerType() {
		return exceptionHandlerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_Group() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionHandlerType_Action() {
		return (EReference)exceptionHandlerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_FaultName() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_FaultVariable() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_Type() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFaultType() {
		return faultTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Group() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFaultType_MetaData() {
		return (EReference)faultTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_FaultName() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_FaultVariable() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Height() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Id() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Name() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Width() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_X() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaultType_Y() {
		return (EAttribute)faultTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForEachType() {
		return forEachTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Group() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachType_MetaData() {
		return (EReference)forEachTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachType_Nodes() {
		return (EReference)forEachTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachType_Connections() {
		return (EReference)forEachTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachType_InPorts() {
		return (EReference)forEachTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForEachType_OutPorts() {
		return (EReference)forEachTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_CollectionExpression() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Height() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Id() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Name() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_VariableName() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_WaitForCompletion() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Width() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_X() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForEachType_Y() {
		return (EAttribute)forEachTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionImportsType() {
		return functionImportsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionImportsType_Group() {
		return (EAttribute)functionImportsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionImportsType_FunctionImport() {
		return (EReference)functionImportsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionImportType() {
		return functionImportTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionImportType_Name() {
		return (EAttribute)functionImportTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlobalsType() {
		return globalsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalsType_Group() {
		return (EAttribute)globalsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGlobalsType_Global() {
		return (EReference)globalsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlobalType() {
		return globalTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalType_Identifier() {
		return (EAttribute)globalTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobalType_Type() {
		return (EAttribute)globalTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHeaderType() {
		return headerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHeaderType_Group() {
		return (EAttribute)headerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_Imports() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_FunctionImports() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_Globals() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_Variables() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_Swimlanes() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHeaderType_ExceptionHandlers() {
		return (EReference)headerTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHumanTaskType() {
		return humanTaskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Group() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_MetaData() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_Work() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_Mapping() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_Timers() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_OnEntry() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanTaskType_OnExit() {
		return (EReference)humanTaskTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Height() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Id() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Name() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Swimlane() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_WaitForCompletion() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Width() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_X() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanTaskType_Y() {
		return (EAttribute)humanTaskTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportsType() {
		return importsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportsType_Group() {
		return (EAttribute)importsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportsType_Import() {
		return (EReference)importsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportType() {
		return importTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportType_Name() {
		return (EAttribute)importTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInPortsType() {
		return inPortsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInPortsType_Group() {
		return (EAttribute)inPortsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInPortsType_InPort() {
		return (EReference)inPortsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInPortType() {
		return inPortTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInPortType_NodeId() {
		return (EAttribute)inPortTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInPortType_NodeInType() {
		return (EAttribute)inPortTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInPortType_Type() {
		return (EAttribute)inPortTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoinType() {
		return joinTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Group() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJoinType_MetaData() {
		return (EReference)joinTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Height() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Id() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_N() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Name() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Type() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Width() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_X() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Y() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMappingType() {
		return mappingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingType_From() {
		return (EAttribute)mappingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingType_To() {
		return (EAttribute)mappingTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingType_Type() {
		return (EAttribute)mappingTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaDataType() {
		return metaDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaDataType_Value() {
		return (EReference)metaDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaDataType_Name() {
		return (EAttribute)metaDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMilestoneType() {
		return milestoneTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Group() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestoneType_MetaData() {
		return (EReference)milestoneTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestoneType_Constraint() {
		return (EReference)milestoneTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestoneType_Timers() {
		return (EReference)milestoneTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestoneType_OnEntry() {
		return (EReference)milestoneTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMilestoneType_OnExit() {
		return (EReference)milestoneTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Height() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Id() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Name() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Width() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_X() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMilestoneType_Y() {
		return (EAttribute)milestoneTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodesType() {
		return nodesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodesType_Group() {
		return (EAttribute)nodesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Start() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_End() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_ActionNode() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_RuleSet() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Split() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Join() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Milestone() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_SubProcess() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_WorkItem() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_TimerNode() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_HumanTask() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Composite() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_ForEach() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_EventNode() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Fault() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_State() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodesType_Dynamic() {
		return (EReference)nodesTypeEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOnEntryType() {
		return onEntryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOnEntryType_Group() {
		return (EAttribute)onEntryTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOnEntryType_Action() {
		return (EReference)onEntryTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOnExitType() {
		return onExitTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOnExitType_Group() {
		return (EAttribute)onExitTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOnExitType_Action() {
		return (EReference)onExitTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutPortsType() {
		return outPortsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutPortsType_Group() {
		return (EAttribute)outPortsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutPortsType_OutPort() {
		return (EReference)outPortsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutPortType() {
		return outPortTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutPortType_NodeId() {
		return (EAttribute)outPortTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutPortType_NodeOutType() {
		return (EAttribute)outPortTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutPortType_Type() {
		return (EAttribute)outPortTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterType() {
		return parameterTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterType_Group() {
		return (EAttribute)parameterTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterType_Type() {
		return (EReference)parameterTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterType_Value() {
		return (EReference)parameterTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterType_Name() {
		return (EAttribute)parameterTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessType() {
		return processTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_Group() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessType_Header() {
		return (EReference)processTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessType_Nodes() {
		return (EReference)processTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessType_Connections() {
		return (EReference)processTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_Id() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_Name() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_PackageName() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_RouterLayout() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_Type() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessType_Version() {
		return (EAttribute)processTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuleSetType() {
		return ruleSetTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Group() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleSetType_MetaData() {
		return (EReference)ruleSetTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuleSetType_Timers() {
		return (EReference)ruleSetTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Height() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Id() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Name() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_RuleFlowGroup() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Width() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_X() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuleSetType_Y() {
		return (EAttribute)ruleSetTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSplitType() {
		return splitTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Group() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSplitType_MetaData() {
		return (EReference)splitTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSplitType_Constraints() {
		return (EReference)splitTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Height() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Id() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Name() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Type() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Width() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_X() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSplitType_Y() {
		return (EAttribute)splitTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartType() {
		return startTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Group() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartType_MetaData() {
		return (EReference)startTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartType_Triggers() {
		return (EReference)startTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Height() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Id() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Name() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Width() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_X() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartType_Y() {
		return (EAttribute)startTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateType() {
		return stateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Group() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_MetaData() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_Timers() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_OnEntry() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_OnExit() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_Constraints() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Height() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Id() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Name() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Width() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_X() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Y() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubProcessType() {
		return subProcessTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Group() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubProcessType_MetaData() {
		return (EReference)subProcessTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubProcessType_Mapping() {
		return (EReference)subProcessTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubProcessType_Timers() {
		return (EReference)subProcessTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubProcessType_OnEntry() {
		return (EReference)subProcessTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubProcessType_OnExit() {
		return (EReference)subProcessTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Height() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Id() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Independent() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Name() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_ProcessId() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_WaitForCompletion() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Width() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_X() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Y() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwimlanesType() {
		return swimlanesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlanesType_Group() {
		return (EAttribute)swimlanesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwimlanesType_Swimlane() {
		return (EReference)swimlanesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwimlaneType() {
		return swimlaneTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlaneType_Name() {
		return (EAttribute)swimlaneTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerNodeType() {
		return timerNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Group() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerNodeType_MetaData() {
		return (EReference)timerNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Delay() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Height() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Id() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Name() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Period() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Width() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_X() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerNodeType_Y() {
		return (EAttribute)timerNodeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimersType() {
		return timersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimersType_Group() {
		return (EAttribute)timersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimersType_Timer() {
		return (EReference)timersTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerType() {
		return timerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Action() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Delay() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Id() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Period() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTriggersType() {
		return triggersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggersType_Group() {
		return (EAttribute)triggersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTriggersType_Trigger() {
		return (EReference)triggersTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTriggerType() {
		return triggerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerType_Group() {
		return (EAttribute)triggerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTriggerType_Constraint() {
		return (EReference)triggerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTriggerType_EventFilters() {
		return (EReference)triggerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTriggerType_Mapping() {
		return (EReference)triggerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerType_Type() {
		return (EAttribute)triggerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeType() {
		return typeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeType_ClassName() {
		return (EAttribute)typeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeType_Name() {
		return (EAttribute)typeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueType() {
		return valueTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueType_Value() {
		return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariablesType() {
		return variablesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariablesType_Group() {
		return (EAttribute)variablesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariablesType_Variable() {
		return (EReference)variablesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableType() {
		return variableTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_Group() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableType_Type() {
		return (EReference)variableTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableType_Value() {
		return (EReference)variableTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_Name() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkItemType() {
		return workItemTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Group() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_MetaData() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_Timers() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_Work() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_Mapping() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_OnEntry() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkItemType_OnExit() {
		return (EReference)workItemTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Height() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Id() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Name() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_WaitForCompletion() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Width() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_X() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkItemType_Y() {
		return (EAttribute)workItemTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkType() {
		return workTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkType_Group() {
		return (EAttribute)workTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkType_Parameter() {
		return (EReference)workTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkType_Name() {
		return (EAttribute)workTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessFactory getProcessFactory() {
		return (ProcessFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		actionNodeTypeEClass = createEClass(ACTION_NODE_TYPE);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__GROUP);
		createEReference(actionNodeTypeEClass, ACTION_NODE_TYPE__META_DATA);
		createEReference(actionNodeTypeEClass, ACTION_NODE_TYPE__ACTION);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__HEIGHT);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__ID);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__NAME);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__WIDTH);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__X);
		createEAttribute(actionNodeTypeEClass, ACTION_NODE_TYPE__Y);

		actionTypeEClass = createEClass(ACTION_TYPE);
		createEAttribute(actionTypeEClass, ACTION_TYPE__VALUE);
		createEAttribute(actionTypeEClass, ACTION_TYPE__DIALECT);
		createEAttribute(actionTypeEClass, ACTION_TYPE__NAME);
		createEAttribute(actionTypeEClass, ACTION_TYPE__TYPE);

		compositeTypeEClass = createEClass(COMPOSITE_TYPE);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__GROUP);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__META_DATA);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__TIMERS);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__ON_ENTRY);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__ON_EXIT);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__VARIABLES);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__EXCEPTION_HANDLERS);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__NODES);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__CONNECTIONS);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__IN_PORTS);
		createEReference(compositeTypeEClass, COMPOSITE_TYPE__OUT_PORTS);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__HEIGHT);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__ID);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__NAME);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__WIDTH);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__X);
		createEAttribute(compositeTypeEClass, COMPOSITE_TYPE__Y);

		connectionsTypeEClass = createEClass(CONNECTIONS_TYPE);
		createEAttribute(connectionsTypeEClass, CONNECTIONS_TYPE__GROUP);
		createEReference(connectionsTypeEClass, CONNECTIONS_TYPE__CONNECTION);

		connectionTypeEClass = createEClass(CONNECTION_TYPE);
		createEAttribute(connectionTypeEClass, CONNECTION_TYPE__BENDPOINTS);
		createEAttribute(connectionTypeEClass, CONNECTION_TYPE__FROM);
		createEAttribute(connectionTypeEClass, CONNECTION_TYPE__FROM_TYPE);
		createEAttribute(connectionTypeEClass, CONNECTION_TYPE__TO);
		createEAttribute(connectionTypeEClass, CONNECTION_TYPE__TO_TYPE);

		constraintsTypeEClass = createEClass(CONSTRAINTS_TYPE);
		createEAttribute(constraintsTypeEClass, CONSTRAINTS_TYPE__GROUP);
		createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__CONSTRAINT);

		constraintTypeEClass = createEClass(CONSTRAINT_TYPE);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__VALUE);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__DIALECT);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__NAME);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__PRIORITY);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__TO_NODE_ID);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__TO_TYPE);
		createEAttribute(constraintTypeEClass, CONSTRAINT_TYPE__TYPE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTION_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COMPOSITE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRAINT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRAINTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DYNAMIC);
		createEReference(documentRootEClass, DOCUMENT_ROOT__END);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EVENT_FILTER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EVENT_FILTERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EVENT_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXCEPTION_HANDLER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXCEPTION_HANDLERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FAULT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FOR_EACH);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FUNCTION_IMPORT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FUNCTION_IMPORTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GLOBAL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GLOBALS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HEADER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HUMAN_TASK);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IMPORT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IMPORTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IN_PORT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IN_PORTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__JOIN);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MAPPING);
		createEReference(documentRootEClass, DOCUMENT_ROOT__META_DATA);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MILESTONE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NODES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ON_ENTRY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ON_EXIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OUT_PORT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OUT_PORTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARAMETER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROCESS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RULE_SET);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPLIT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__START);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STATE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SUB_PROCESS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SWIMLANE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SWIMLANES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIMER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIMER_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIMERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRIGGER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRIGGERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TYPE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VALUE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABLE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABLES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WORK);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WORK_ITEM);

		dynamicTypeEClass = createEClass(DYNAMIC_TYPE);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__GROUP);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__META_DATA);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__VARIABLES);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__EXCEPTION_HANDLERS);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__NODES);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__CONNECTIONS);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__IN_PORTS);
		createEReference(dynamicTypeEClass, DYNAMIC_TYPE__OUT_PORTS);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__HEIGHT);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__ID);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__NAME);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__WIDTH);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__X);
		createEAttribute(dynamicTypeEClass, DYNAMIC_TYPE__Y);

		endTypeEClass = createEClass(END_TYPE);
		createEAttribute(endTypeEClass, END_TYPE__GROUP);
		createEReference(endTypeEClass, END_TYPE__META_DATA);
		createEAttribute(endTypeEClass, END_TYPE__HEIGHT);
		createEAttribute(endTypeEClass, END_TYPE__ID);
		createEAttribute(endTypeEClass, END_TYPE__NAME);
		createEAttribute(endTypeEClass, END_TYPE__TERMINATE);
		createEAttribute(endTypeEClass, END_TYPE__WIDTH);
		createEAttribute(endTypeEClass, END_TYPE__X);
		createEAttribute(endTypeEClass, END_TYPE__Y);

		eventFiltersTypeEClass = createEClass(EVENT_FILTERS_TYPE);
		createEAttribute(eventFiltersTypeEClass, EVENT_FILTERS_TYPE__GROUP);
		createEReference(eventFiltersTypeEClass, EVENT_FILTERS_TYPE__EVENT_FILTER);

		eventFilterTypeEClass = createEClass(EVENT_FILTER_TYPE);
		createEAttribute(eventFilterTypeEClass, EVENT_FILTER_TYPE__EVENT_TYPE);
		createEAttribute(eventFilterTypeEClass, EVENT_FILTER_TYPE__TYPE);

		eventNodeTypeEClass = createEClass(EVENT_NODE_TYPE);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__GROUP);
		createEReference(eventNodeTypeEClass, EVENT_NODE_TYPE__META_DATA);
		createEReference(eventNodeTypeEClass, EVENT_NODE_TYPE__EVENT_FILTERS);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__HEIGHT);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__ID);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__NAME);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__SCOPE);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__VARIABLE_NAME);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__WIDTH);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__X);
		createEAttribute(eventNodeTypeEClass, EVENT_NODE_TYPE__Y);

		exceptionHandlersTypeEClass = createEClass(EXCEPTION_HANDLERS_TYPE);
		createEAttribute(exceptionHandlersTypeEClass, EXCEPTION_HANDLERS_TYPE__GROUP);
		createEReference(exceptionHandlersTypeEClass, EXCEPTION_HANDLERS_TYPE__EXCEPTION_HANDLER);

		exceptionHandlerTypeEClass = createEClass(EXCEPTION_HANDLER_TYPE);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__GROUP);
		createEReference(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__ACTION);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__FAULT_NAME);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__TYPE);

		faultTypeEClass = createEClass(FAULT_TYPE);
		createEAttribute(faultTypeEClass, FAULT_TYPE__GROUP);
		createEReference(faultTypeEClass, FAULT_TYPE__META_DATA);
		createEAttribute(faultTypeEClass, FAULT_TYPE__FAULT_NAME);
		createEAttribute(faultTypeEClass, FAULT_TYPE__FAULT_VARIABLE);
		createEAttribute(faultTypeEClass, FAULT_TYPE__HEIGHT);
		createEAttribute(faultTypeEClass, FAULT_TYPE__ID);
		createEAttribute(faultTypeEClass, FAULT_TYPE__NAME);
		createEAttribute(faultTypeEClass, FAULT_TYPE__WIDTH);
		createEAttribute(faultTypeEClass, FAULT_TYPE__X);
		createEAttribute(faultTypeEClass, FAULT_TYPE__Y);

		forEachTypeEClass = createEClass(FOR_EACH_TYPE);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__GROUP);
		createEReference(forEachTypeEClass, FOR_EACH_TYPE__META_DATA);
		createEReference(forEachTypeEClass, FOR_EACH_TYPE__NODES);
		createEReference(forEachTypeEClass, FOR_EACH_TYPE__CONNECTIONS);
		createEReference(forEachTypeEClass, FOR_EACH_TYPE__IN_PORTS);
		createEReference(forEachTypeEClass, FOR_EACH_TYPE__OUT_PORTS);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__COLLECTION_EXPRESSION);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__HEIGHT);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__ID);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__NAME);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__VARIABLE_NAME);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__WAIT_FOR_COMPLETION);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__WIDTH);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__X);
		createEAttribute(forEachTypeEClass, FOR_EACH_TYPE__Y);

		functionImportsTypeEClass = createEClass(FUNCTION_IMPORTS_TYPE);
		createEAttribute(functionImportsTypeEClass, FUNCTION_IMPORTS_TYPE__GROUP);
		createEReference(functionImportsTypeEClass, FUNCTION_IMPORTS_TYPE__FUNCTION_IMPORT);

		functionImportTypeEClass = createEClass(FUNCTION_IMPORT_TYPE);
		createEAttribute(functionImportTypeEClass, FUNCTION_IMPORT_TYPE__NAME);

		globalsTypeEClass = createEClass(GLOBALS_TYPE);
		createEAttribute(globalsTypeEClass, GLOBALS_TYPE__GROUP);
		createEReference(globalsTypeEClass, GLOBALS_TYPE__GLOBAL);

		globalTypeEClass = createEClass(GLOBAL_TYPE);
		createEAttribute(globalTypeEClass, GLOBAL_TYPE__IDENTIFIER);
		createEAttribute(globalTypeEClass, GLOBAL_TYPE__TYPE);

		headerTypeEClass = createEClass(HEADER_TYPE);
		createEAttribute(headerTypeEClass, HEADER_TYPE__GROUP);
		createEReference(headerTypeEClass, HEADER_TYPE__IMPORTS);
		createEReference(headerTypeEClass, HEADER_TYPE__FUNCTION_IMPORTS);
		createEReference(headerTypeEClass, HEADER_TYPE__GLOBALS);
		createEReference(headerTypeEClass, HEADER_TYPE__VARIABLES);
		createEReference(headerTypeEClass, HEADER_TYPE__SWIMLANES);
		createEReference(headerTypeEClass, HEADER_TYPE__EXCEPTION_HANDLERS);

		humanTaskTypeEClass = createEClass(HUMAN_TASK_TYPE);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__GROUP);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__META_DATA);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__WORK);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__MAPPING);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__TIMERS);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__ON_ENTRY);
		createEReference(humanTaskTypeEClass, HUMAN_TASK_TYPE__ON_EXIT);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__HEIGHT);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__ID);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__NAME);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__SWIMLANE);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__WAIT_FOR_COMPLETION);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__WIDTH);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__X);
		createEAttribute(humanTaskTypeEClass, HUMAN_TASK_TYPE__Y);

		importsTypeEClass = createEClass(IMPORTS_TYPE);
		createEAttribute(importsTypeEClass, IMPORTS_TYPE__GROUP);
		createEReference(importsTypeEClass, IMPORTS_TYPE__IMPORT);

		importTypeEClass = createEClass(IMPORT_TYPE);
		createEAttribute(importTypeEClass, IMPORT_TYPE__NAME);

		inPortsTypeEClass = createEClass(IN_PORTS_TYPE);
		createEAttribute(inPortsTypeEClass, IN_PORTS_TYPE__GROUP);
		createEReference(inPortsTypeEClass, IN_PORTS_TYPE__IN_PORT);

		inPortTypeEClass = createEClass(IN_PORT_TYPE);
		createEAttribute(inPortTypeEClass, IN_PORT_TYPE__NODE_ID);
		createEAttribute(inPortTypeEClass, IN_PORT_TYPE__NODE_IN_TYPE);
		createEAttribute(inPortTypeEClass, IN_PORT_TYPE__TYPE);

		joinTypeEClass = createEClass(JOIN_TYPE);
		createEAttribute(joinTypeEClass, JOIN_TYPE__GROUP);
		createEReference(joinTypeEClass, JOIN_TYPE__META_DATA);
		createEAttribute(joinTypeEClass, JOIN_TYPE__HEIGHT);
		createEAttribute(joinTypeEClass, JOIN_TYPE__ID);
		createEAttribute(joinTypeEClass, JOIN_TYPE__N);
		createEAttribute(joinTypeEClass, JOIN_TYPE__NAME);
		createEAttribute(joinTypeEClass, JOIN_TYPE__TYPE);
		createEAttribute(joinTypeEClass, JOIN_TYPE__WIDTH);
		createEAttribute(joinTypeEClass, JOIN_TYPE__X);
		createEAttribute(joinTypeEClass, JOIN_TYPE__Y);

		mappingTypeEClass = createEClass(MAPPING_TYPE);
		createEAttribute(mappingTypeEClass, MAPPING_TYPE__FROM);
		createEAttribute(mappingTypeEClass, MAPPING_TYPE__TO);
		createEAttribute(mappingTypeEClass, MAPPING_TYPE__TYPE);

		metaDataTypeEClass = createEClass(META_DATA_TYPE);
		createEReference(metaDataTypeEClass, META_DATA_TYPE__VALUE);
		createEAttribute(metaDataTypeEClass, META_DATA_TYPE__NAME);

		milestoneTypeEClass = createEClass(MILESTONE_TYPE);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__GROUP);
		createEReference(milestoneTypeEClass, MILESTONE_TYPE__META_DATA);
		createEReference(milestoneTypeEClass, MILESTONE_TYPE__CONSTRAINT);
		createEReference(milestoneTypeEClass, MILESTONE_TYPE__TIMERS);
		createEReference(milestoneTypeEClass, MILESTONE_TYPE__ON_ENTRY);
		createEReference(milestoneTypeEClass, MILESTONE_TYPE__ON_EXIT);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__HEIGHT);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__ID);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__NAME);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__WIDTH);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__X);
		createEAttribute(milestoneTypeEClass, MILESTONE_TYPE__Y);

		nodesTypeEClass = createEClass(NODES_TYPE);
		createEAttribute(nodesTypeEClass, NODES_TYPE__GROUP);
		createEReference(nodesTypeEClass, NODES_TYPE__START);
		createEReference(nodesTypeEClass, NODES_TYPE__END);
		createEReference(nodesTypeEClass, NODES_TYPE__ACTION_NODE);
		createEReference(nodesTypeEClass, NODES_TYPE__RULE_SET);
		createEReference(nodesTypeEClass, NODES_TYPE__SPLIT);
		createEReference(nodesTypeEClass, NODES_TYPE__JOIN);
		createEReference(nodesTypeEClass, NODES_TYPE__MILESTONE);
		createEReference(nodesTypeEClass, NODES_TYPE__SUB_PROCESS);
		createEReference(nodesTypeEClass, NODES_TYPE__WORK_ITEM);
		createEReference(nodesTypeEClass, NODES_TYPE__TIMER_NODE);
		createEReference(nodesTypeEClass, NODES_TYPE__HUMAN_TASK);
		createEReference(nodesTypeEClass, NODES_TYPE__COMPOSITE);
		createEReference(nodesTypeEClass, NODES_TYPE__FOR_EACH);
		createEReference(nodesTypeEClass, NODES_TYPE__EVENT_NODE);
		createEReference(nodesTypeEClass, NODES_TYPE__FAULT);
		createEReference(nodesTypeEClass, NODES_TYPE__STATE);
		createEReference(nodesTypeEClass, NODES_TYPE__DYNAMIC);

		onEntryTypeEClass = createEClass(ON_ENTRY_TYPE);
		createEAttribute(onEntryTypeEClass, ON_ENTRY_TYPE__GROUP);
		createEReference(onEntryTypeEClass, ON_ENTRY_TYPE__ACTION);

		onExitTypeEClass = createEClass(ON_EXIT_TYPE);
		createEAttribute(onExitTypeEClass, ON_EXIT_TYPE__GROUP);
		createEReference(onExitTypeEClass, ON_EXIT_TYPE__ACTION);

		outPortsTypeEClass = createEClass(OUT_PORTS_TYPE);
		createEAttribute(outPortsTypeEClass, OUT_PORTS_TYPE__GROUP);
		createEReference(outPortsTypeEClass, OUT_PORTS_TYPE__OUT_PORT);

		outPortTypeEClass = createEClass(OUT_PORT_TYPE);
		createEAttribute(outPortTypeEClass, OUT_PORT_TYPE__NODE_ID);
		createEAttribute(outPortTypeEClass, OUT_PORT_TYPE__NODE_OUT_TYPE);
		createEAttribute(outPortTypeEClass, OUT_PORT_TYPE__TYPE);

		parameterTypeEClass = createEClass(PARAMETER_TYPE);
		createEAttribute(parameterTypeEClass, PARAMETER_TYPE__GROUP);
		createEReference(parameterTypeEClass, PARAMETER_TYPE__TYPE);
		createEReference(parameterTypeEClass, PARAMETER_TYPE__VALUE);
		createEAttribute(parameterTypeEClass, PARAMETER_TYPE__NAME);

		processTypeEClass = createEClass(PROCESS_TYPE);
		createEAttribute(processTypeEClass, PROCESS_TYPE__GROUP);
		createEReference(processTypeEClass, PROCESS_TYPE__HEADER);
		createEReference(processTypeEClass, PROCESS_TYPE__NODES);
		createEReference(processTypeEClass, PROCESS_TYPE__CONNECTIONS);
		createEAttribute(processTypeEClass, PROCESS_TYPE__ID);
		createEAttribute(processTypeEClass, PROCESS_TYPE__NAME);
		createEAttribute(processTypeEClass, PROCESS_TYPE__PACKAGE_NAME);
		createEAttribute(processTypeEClass, PROCESS_TYPE__ROUTER_LAYOUT);
		createEAttribute(processTypeEClass, PROCESS_TYPE__TYPE);
		createEAttribute(processTypeEClass, PROCESS_TYPE__VERSION);

		ruleSetTypeEClass = createEClass(RULE_SET_TYPE);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__GROUP);
		createEReference(ruleSetTypeEClass, RULE_SET_TYPE__META_DATA);
		createEReference(ruleSetTypeEClass, RULE_SET_TYPE__TIMERS);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__HEIGHT);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__ID);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__NAME);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__RULE_FLOW_GROUP);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__WIDTH);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__X);
		createEAttribute(ruleSetTypeEClass, RULE_SET_TYPE__Y);

		splitTypeEClass = createEClass(SPLIT_TYPE);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__GROUP);
		createEReference(splitTypeEClass, SPLIT_TYPE__META_DATA);
		createEReference(splitTypeEClass, SPLIT_TYPE__CONSTRAINTS);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__HEIGHT);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__ID);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__NAME);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__TYPE);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__WIDTH);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__X);
		createEAttribute(splitTypeEClass, SPLIT_TYPE__Y);

		startTypeEClass = createEClass(START_TYPE);
		createEAttribute(startTypeEClass, START_TYPE__GROUP);
		createEReference(startTypeEClass, START_TYPE__META_DATA);
		createEReference(startTypeEClass, START_TYPE__TRIGGERS);
		createEAttribute(startTypeEClass, START_TYPE__HEIGHT);
		createEAttribute(startTypeEClass, START_TYPE__ID);
		createEAttribute(startTypeEClass, START_TYPE__NAME);
		createEAttribute(startTypeEClass, START_TYPE__WIDTH);
		createEAttribute(startTypeEClass, START_TYPE__X);
		createEAttribute(startTypeEClass, START_TYPE__Y);

		stateTypeEClass = createEClass(STATE_TYPE);
		createEAttribute(stateTypeEClass, STATE_TYPE__GROUP);
		createEReference(stateTypeEClass, STATE_TYPE__META_DATA);
		createEReference(stateTypeEClass, STATE_TYPE__TIMERS);
		createEReference(stateTypeEClass, STATE_TYPE__ON_ENTRY);
		createEReference(stateTypeEClass, STATE_TYPE__ON_EXIT);
		createEReference(stateTypeEClass, STATE_TYPE__CONSTRAINTS);
		createEAttribute(stateTypeEClass, STATE_TYPE__HEIGHT);
		createEAttribute(stateTypeEClass, STATE_TYPE__ID);
		createEAttribute(stateTypeEClass, STATE_TYPE__NAME);
		createEAttribute(stateTypeEClass, STATE_TYPE__WIDTH);
		createEAttribute(stateTypeEClass, STATE_TYPE__X);
		createEAttribute(stateTypeEClass, STATE_TYPE__Y);

		subProcessTypeEClass = createEClass(SUB_PROCESS_TYPE);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__GROUP);
		createEReference(subProcessTypeEClass, SUB_PROCESS_TYPE__META_DATA);
		createEReference(subProcessTypeEClass, SUB_PROCESS_TYPE__MAPPING);
		createEReference(subProcessTypeEClass, SUB_PROCESS_TYPE__TIMERS);
		createEReference(subProcessTypeEClass, SUB_PROCESS_TYPE__ON_ENTRY);
		createEReference(subProcessTypeEClass, SUB_PROCESS_TYPE__ON_EXIT);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__HEIGHT);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__ID);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__INDEPENDENT);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__NAME);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__PROCESS_ID);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__WIDTH);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__X);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__Y);

		swimlanesTypeEClass = createEClass(SWIMLANES_TYPE);
		createEAttribute(swimlanesTypeEClass, SWIMLANES_TYPE__GROUP);
		createEReference(swimlanesTypeEClass, SWIMLANES_TYPE__SWIMLANE);

		swimlaneTypeEClass = createEClass(SWIMLANE_TYPE);
		createEAttribute(swimlaneTypeEClass, SWIMLANE_TYPE__NAME);

		timerNodeTypeEClass = createEClass(TIMER_NODE_TYPE);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__GROUP);
		createEReference(timerNodeTypeEClass, TIMER_NODE_TYPE__META_DATA);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__DELAY);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__HEIGHT);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__ID);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__NAME);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__PERIOD);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__WIDTH);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__X);
		createEAttribute(timerNodeTypeEClass, TIMER_NODE_TYPE__Y);

		timersTypeEClass = createEClass(TIMERS_TYPE);
		createEAttribute(timersTypeEClass, TIMERS_TYPE__GROUP);
		createEReference(timersTypeEClass, TIMERS_TYPE__TIMER);

		timerTypeEClass = createEClass(TIMER_TYPE);
		createEReference(timerTypeEClass, TIMER_TYPE__ACTION);
		createEAttribute(timerTypeEClass, TIMER_TYPE__DELAY);
		createEAttribute(timerTypeEClass, TIMER_TYPE__ID);
		createEAttribute(timerTypeEClass, TIMER_TYPE__PERIOD);

		triggersTypeEClass = createEClass(TRIGGERS_TYPE);
		createEAttribute(triggersTypeEClass, TRIGGERS_TYPE__GROUP);
		createEReference(triggersTypeEClass, TRIGGERS_TYPE__TRIGGER);

		triggerTypeEClass = createEClass(TRIGGER_TYPE);
		createEAttribute(triggerTypeEClass, TRIGGER_TYPE__GROUP);
		createEReference(triggerTypeEClass, TRIGGER_TYPE__CONSTRAINT);
		createEReference(triggerTypeEClass, TRIGGER_TYPE__EVENT_FILTERS);
		createEReference(triggerTypeEClass, TRIGGER_TYPE__MAPPING);
		createEAttribute(triggerTypeEClass, TRIGGER_TYPE__TYPE);

		typeTypeEClass = createEClass(TYPE_TYPE);
		createEAttribute(typeTypeEClass, TYPE_TYPE__CLASS_NAME);
		createEAttribute(typeTypeEClass, TYPE_TYPE__NAME);

		valueTypeEClass = createEClass(VALUE_TYPE);
		createEAttribute(valueTypeEClass, VALUE_TYPE__VALUE);

		variablesTypeEClass = createEClass(VARIABLES_TYPE);
		createEAttribute(variablesTypeEClass, VARIABLES_TYPE__GROUP);
		createEReference(variablesTypeEClass, VARIABLES_TYPE__VARIABLE);

		variableTypeEClass = createEClass(VARIABLE_TYPE);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__GROUP);
		createEReference(variableTypeEClass, VARIABLE_TYPE__TYPE);
		createEReference(variableTypeEClass, VARIABLE_TYPE__VALUE);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__NAME);

		workItemTypeEClass = createEClass(WORK_ITEM_TYPE);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__GROUP);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__META_DATA);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__TIMERS);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__WORK);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__MAPPING);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__ON_ENTRY);
		createEReference(workItemTypeEClass, WORK_ITEM_TYPE__ON_EXIT);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__HEIGHT);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__ID);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__NAME);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__WAIT_FOR_COMPLETION);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__WIDTH);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__X);
		createEAttribute(workItemTypeEClass, WORK_ITEM_TYPE__Y);

		workTypeEClass = createEClass(WORK_TYPE);
		createEAttribute(workTypeEClass, WORK_TYPE__GROUP);
		createEReference(workTypeEClass, WORK_TYPE__PARAMETER);
		createEAttribute(workTypeEClass, WORK_TYPE__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(actionNodeTypeEClass, ActionNodeType.class, "ActionNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionNodeType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, ActionNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActionNodeType_Action(), this.getActionType(), null, "action", null, 0, -1, ActionNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionNodeType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, ActionNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTypeEClass, ActionType.class, "ActionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Dialect(), theXMLTypePackage.getString(), "dialect", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeTypeEClass, CompositeType.class, "CompositeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompositeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_Variables(), this.getVariablesType(), null, "variables", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_ExceptionHandlers(), this.getExceptionHandlersType(), null, "exceptionHandlers", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_Nodes(), this.getNodesType(), null, "nodes", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_Connections(), this.getConnectionsType(), null, "connections", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_InPorts(), this.getInPortsType(), null, "inPorts", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCompositeType_OutPorts(), this.getOutPortsType(), null, "outPorts", null, 0, -1, CompositeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, CompositeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionsTypeEClass, ConnectionsType.class, "ConnectionsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectionsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ConnectionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectionsType_Connection(), this.getConnectionType(), null, "connection", null, 0, -1, ConnectionsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(connectionTypeEClass, ConnectionType.class, "ConnectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectionType_Bendpoints(), theXMLTypePackage.getString(), "bendpoints", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionType_From(), theXMLTypePackage.getString(), "from", null, 1, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionType_FromType(), theXMLTypePackage.getString(), "fromType", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionType_To(), theXMLTypePackage.getString(), "to", null, 1, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionType_ToType(), theXMLTypePackage.getString(), "toType", null, 0, 1, ConnectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintsTypeEClass, ConstraintsType.class, "ConstraintsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ConstraintsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintsType_Constraint(), this.getConstraintType(), null, "constraint", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(constraintTypeEClass, ConstraintType.class, "ConstraintType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_Dialect(), theXMLTypePackage.getString(), "dialect", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_Priority(), theXMLTypePackage.getString(), "priority", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_ToNodeId(), theXMLTypePackage.getString(), "toNodeId", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_ToType(), theXMLTypePackage.getString(), "toType", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Action(), this.getActionType(), null, "action", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ActionNode(), this.getActionNodeType(), null, "actionNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Composite(), this.getCompositeType(), null, "composite", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Connection(), this.getConnectionType(), null, "connection", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Connections(), this.getConnectionsType(), null, "connections", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Constraint(), this.getConstraintType(), null, "constraint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Constraints(), this.getConstraintsType(), null, "constraints", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Dynamic(), this.getDynamicType(), null, "dynamic", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_End(), this.getEndType(), null, "end", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EventFilter(), this.getEventFilterType(), null, "eventFilter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EventFilters(), this.getEventFiltersType(), null, "eventFilters", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EventNode(), this.getEventNodeType(), null, "eventNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExceptionHandlers(), this.getExceptionHandlersType(), null, "exceptionHandlers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Fault(), this.getFaultType(), null, "fault", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ForEach(), this.getForEachType(), null, "forEach", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FunctionImport(), this.getFunctionImportType(), null, "functionImport", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FunctionImports(), this.getFunctionImportsType(), null, "functionImports", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Global(), this.getGlobalType(), null, "global", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Globals(), this.getGlobalsType(), null, "globals", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Header(), this.getHeaderType(), null, "header", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_HumanTask(), this.getHumanTaskType(), null, "humanTask", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Import(), this.getImportType(), null, "import", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Imports(), this.getImportsType(), null, "imports", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_InPort(), this.getInPortType(), null, "inPort", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_InPorts(), this.getInPortsType(), null, "inPorts", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Join(), this.getJoinType(), null, "join", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Mapping(), this.getMappingType(), null, "mapping", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Milestone(), this.getMilestoneType(), null, "milestone", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Nodes(), this.getNodesType(), null, "nodes", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OutPort(), this.getOutPortType(), null, "outPort", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_OutPorts(), this.getOutPortsType(), null, "outPorts", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Parameter(), this.getParameterType(), null, "parameter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Process(), this.getProcessType(), null, "process", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_RuleSet(), this.getRuleSetType(), null, "ruleSet", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Split(), this.getSplitType(), null, "split", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Start(), this.getStartType(), null, "start", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_State(), this.getStateType(), null, "state", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SubProcess(), this.getSubProcessType(), null, "subProcess", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Swimlane(), this.getSwimlaneType(), null, "swimlane", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Swimlanes(), this.getSwimlanesType(), null, "swimlanes", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Timer(), this.getTimerType(), null, "timer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TimerNode(), this.getTimerNodeType(), null, "timerNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Timers(), this.getTimersType(), null, "timers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Trigger(), this.getTriggerType(), null, "trigger", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Triggers(), this.getTriggersType(), null, "triggers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Type(), this.getTypeType(), null, "type", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Value(), this.getValueType(), null, "value", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Variable(), this.getVariableType(), null, "variable", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Variables(), this.getVariablesType(), null, "variables", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Work(), this.getWorkType(), null, "work", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_WorkItem(), this.getWorkItemType(), null, "workItem", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(dynamicTypeEClass, DynamicType.class, "DynamicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDynamicType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_Variables(), this.getVariablesType(), null, "variables", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_ExceptionHandlers(), this.getExceptionHandlersType(), null, "exceptionHandlers", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_Nodes(), this.getNodesType(), null, "nodes", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_Connections(), this.getConnectionsType(), null, "connections", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_InPorts(), this.getInPortsType(), null, "inPorts", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicType_OutPorts(), this.getOutPortsType(), null, "outPorts", null, 0, -1, DynamicType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, DynamicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(endTypeEClass, EndType.class, "EndType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEndType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEndType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, EndType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Terminate(), theXMLTypePackage.getString(), "terminate", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, EndType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventFiltersTypeEClass, EventFiltersType.class, "EventFiltersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventFiltersType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EventFiltersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventFiltersType_EventFilter(), this.getEventFilterType(), null, "eventFilter", null, 0, -1, EventFiltersType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(eventFilterTypeEClass, EventFilterType.class, "EventFilterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventFilterType_EventType(), theXMLTypePackage.getString(), "eventType", null, 1, 1, EventFilterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventFilterType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, EventFilterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventNodeTypeEClass, EventNodeType.class, "EventNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventNodeType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, EventNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventNodeType_EventFilters(), this.getEventFiltersType(), null, "eventFilters", null, 0, -1, EventNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Scope(), theXMLTypePackage.getString(), "scope", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_VariableName(), theXMLTypePackage.getString(), "variableName", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventNodeType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, EventNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exceptionHandlersTypeEClass, ExceptionHandlersType.class, "ExceptionHandlersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExceptionHandlersType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExceptionHandlersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionHandlersType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, ExceptionHandlersType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(exceptionHandlerTypeEClass, ExceptionHandlerType.class, "ExceptionHandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExceptionHandlerType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionHandlerType_Action(), this.getActionType(), null, "action", null, 0, -1, ExceptionHandlerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionHandlerType_FaultName(), theXMLTypePackage.getString(), "faultName", null, 1, 1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionHandlerType_FaultVariable(), theXMLTypePackage.getString(), "faultVariable", null, 0, 1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionHandlerType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(faultTypeEClass, FaultType.class, "FaultType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFaultType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFaultType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, FaultType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_FaultName(), theXMLTypePackage.getString(), "faultName", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_FaultVariable(), theXMLTypePackage.getString(), "faultVariable", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFaultType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, FaultType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forEachTypeEClass, ForEachType.class, "ForEachType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getForEachType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getForEachType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, ForEachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForEachType_Nodes(), this.getNodesType(), null, "nodes", null, 0, -1, ForEachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForEachType_Connections(), this.getConnectionsType(), null, "connections", null, 0, -1, ForEachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForEachType_InPorts(), this.getInPortsType(), null, "inPorts", null, 0, -1, ForEachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForEachType_OutPorts(), this.getOutPortsType(), null, "outPorts", null, 0, -1, ForEachType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_CollectionExpression(), theXMLTypePackage.getString(), "collectionExpression", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_VariableName(), theXMLTypePackage.getString(), "variableName", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_WaitForCompletion(), theXMLTypePackage.getString(), "waitForCompletion", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForEachType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, ForEachType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionImportsTypeEClass, FunctionImportsType.class, "FunctionImportsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionImportsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, FunctionImportsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionImportsType_FunctionImport(), this.getFunctionImportType(), null, "functionImport", null, 0, -1, FunctionImportsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(functionImportTypeEClass, FunctionImportType.class, "FunctionImportType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionImportType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, FunctionImportType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(globalsTypeEClass, GlobalsType.class, "GlobalsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGlobalsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, GlobalsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGlobalsType_Global(), this.getGlobalType(), null, "global", null, 0, -1, GlobalsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(globalTypeEClass, GlobalType.class, "GlobalType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGlobalType_Identifier(), theXMLTypePackage.getString(), "identifier", null, 1, 1, GlobalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGlobalType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, GlobalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(headerTypeEClass, HeaderType.class, "HeaderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHeaderType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, HeaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_Imports(), this.getImportsType(), null, "imports", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_FunctionImports(), this.getFunctionImportsType(), null, "functionImports", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_Globals(), this.getGlobalsType(), null, "globals", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_Variables(), this.getVariablesType(), null, "variables", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_Swimlanes(), this.getSwimlanesType(), null, "swimlanes", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHeaderType_ExceptionHandlers(), this.getExceptionHandlersType(), null, "exceptionHandlers", null, 0, -1, HeaderType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(humanTaskTypeEClass, HumanTaskType.class, "HumanTaskType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHumanTaskType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_Work(), this.getWorkType(), null, "work", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_Mapping(), this.getMappingType(), null, "mapping", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHumanTaskType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, HumanTaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Swimlane(), theXMLTypePackage.getString(), "swimlane", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_WaitForCompletion(), theXMLTypePackage.getString(), "waitForCompletion", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanTaskType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, HumanTaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importsTypeEClass, ImportsType.class, "ImportsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ImportsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImportsType_Import(), this.getImportType(), null, "import", null, 0, -1, ImportsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(importTypeEClass, ImportType.class, "ImportType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ImportType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inPortsTypeEClass, InPortsType.class, "InPortsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInPortsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, InPortsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInPortsType_InPort(), this.getInPortType(), null, "inPort", null, 0, -1, InPortsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(inPortTypeEClass, InPortType.class, "InPortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInPortType_NodeId(), theXMLTypePackage.getString(), "nodeId", null, 1, 1, InPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInPortType_NodeInType(), theXMLTypePackage.getString(), "nodeInType", null, 1, 1, InPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInPortType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, InPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinTypeEClass, JoinType.class, "JoinType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJoinType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJoinType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_N(), theXMLTypePackage.getString(), "n", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingTypeEClass, MappingType.class, "MappingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMappingType_From(), theXMLTypePackage.getString(), "from", null, 1, 1, MappingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMappingType_To(), theXMLTypePackage.getString(), "to", null, 1, 1, MappingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMappingType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, MappingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaDataTypeEClass, MetaDataType.class, "MetaDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetaDataType_Value(), this.getValueType(), null, "value", null, 0, 1, MetaDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaDataType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MetaDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(milestoneTypeEClass, MilestoneType.class, "MilestoneType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMilestoneType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMilestoneType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, MilestoneType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMilestoneType_Constraint(), this.getConstraintType(), null, "constraint", null, 0, -1, MilestoneType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMilestoneType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, MilestoneType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMilestoneType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, MilestoneType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMilestoneType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, MilestoneType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMilestoneType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, MilestoneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodesTypeEClass, NodesType.class, "NodesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNodesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, NodesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Start(), this.getStartType(), null, "start", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_End(), this.getEndType(), null, "end", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_ActionNode(), this.getActionNodeType(), null, "actionNode", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_RuleSet(), this.getRuleSetType(), null, "ruleSet", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Split(), this.getSplitType(), null, "split", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Join(), this.getJoinType(), null, "join", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Milestone(), this.getMilestoneType(), null, "milestone", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_SubProcess(), this.getSubProcessType(), null, "subProcess", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_WorkItem(), this.getWorkItemType(), null, "workItem", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_TimerNode(), this.getTimerNodeType(), null, "timerNode", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_HumanTask(), this.getHumanTaskType(), null, "humanTask", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Composite(), this.getCompositeType(), null, "composite", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_ForEach(), this.getForEachType(), null, "forEach", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_EventNode(), this.getEventNodeType(), null, "eventNode", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Fault(), this.getFaultType(), null, "fault", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_State(), this.getStateType(), null, "state", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodesType_Dynamic(), this.getDynamicType(), null, "dynamic", null, 0, -1, NodesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(onEntryTypeEClass, OnEntryType.class, "OnEntryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOnEntryType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, OnEntryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOnEntryType_Action(), this.getActionType(), null, "action", null, 0, -1, OnEntryType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(onExitTypeEClass, OnExitType.class, "OnExitType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOnExitType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, OnExitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOnExitType_Action(), this.getActionType(), null, "action", null, 0, -1, OnExitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(outPortsTypeEClass, OutPortsType.class, "OutPortsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutPortsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, OutPortsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutPortsType_OutPort(), this.getOutPortType(), null, "outPort", null, 0, -1, OutPortsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(outPortTypeEClass, OutPortType.class, "OutPortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutPortType_NodeId(), theXMLTypePackage.getString(), "nodeId", null, 1, 1, OutPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutPortType_NodeOutType(), theXMLTypePackage.getString(), "nodeOutType", null, 1, 1, OutPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutPortType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, OutPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterTypeEClass, ParameterType.class, "ParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterType_Type(), this.getTypeType(), null, "type", null, 0, -1, ParameterType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getParameterType_Value(), this.getValueType(), null, "value", null, 0, -1, ParameterType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processTypeEClass, ProcessType.class, "ProcessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessType_Header(), this.getHeaderType(), null, "header", null, 0, -1, ProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessType_Nodes(), this.getNodesType(), null, "nodes", null, 0, -1, ProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessType_Connections(), this.getConnectionsType(), null, "connections", null, 0, -1, ProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_PackageName(), theXMLTypePackage.getString(), "packageName", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_RouterLayout(), theXMLTypePackage.getString(), "routerLayout", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessType_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, ProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleSetTypeEClass, RuleSetType.class, "RuleSetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRuleSetType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRuleSetType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, RuleSetType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getRuleSetType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, RuleSetType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_RuleFlowGroup(), theXMLTypePackage.getString(), "ruleFlowGroup", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuleSetType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, RuleSetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(splitTypeEClass, SplitType.class, "SplitType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSplitType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSplitType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, SplitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSplitType_Constraints(), this.getConstraintsType(), null, "constraints", null, 0, -1, SplitType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplitType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, SplitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startTypeEClass, StartType.class, "StartType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStartType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, StartType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStartType_Triggers(), this.getTriggersType(), null, "triggers", null, 0, -1, StartType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, StartType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateTypeEClass, StateType.class, "StateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStateType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_Constraints(), this.getConstraintsType(), null, "constraints", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subProcessTypeEClass, SubProcessType.class, "SubProcessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubProcessType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubProcessType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, SubProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSubProcessType_Mapping(), this.getMappingType(), null, "mapping", null, 0, -1, SubProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSubProcessType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, SubProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSubProcessType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, SubProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSubProcessType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, SubProcessType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Independent(), theXMLTypePackage.getString(), "independent", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_ProcessId(), theXMLTypePackage.getString(), "processId", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_WaitForCompletion(), theXMLTypePackage.getString(), "waitForCompletion", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swimlanesTypeEClass, SwimlanesType.class, "SwimlanesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwimlanesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, SwimlanesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwimlanesType_Swimlane(), this.getSwimlaneType(), null, "swimlane", null, 0, -1, SwimlanesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(swimlaneTypeEClass, SwimlaneType.class, "SwimlaneType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwimlaneType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, SwimlaneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timerNodeTypeEClass, TimerNodeType.class, "TimerNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimerNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimerNodeType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, TimerNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Delay(), theXMLTypePackage.getString(), "delay", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Period(), theXMLTypePackage.getString(), "period", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerNodeType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, TimerNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timersTypeEClass, TimersType.class, "TimersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimersType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TimersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimersType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, TimersType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(timerTypeEClass, TimerType.class, "TimerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimerType_Action(), this.getActionType(), null, "action", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Delay(), theXMLTypePackage.getString(), "delay", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Period(), theXMLTypePackage.getString(), "period", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggersTypeEClass, TriggersType.class, "TriggersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTriggersType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TriggersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTriggersType_Trigger(), this.getTriggerType(), null, "trigger", null, 0, -1, TriggersType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(triggerTypeEClass, TriggerType.class, "TriggerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTriggerType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TriggerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTriggerType_Constraint(), this.getConstraintType(), null, "constraint", null, 0, -1, TriggerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTriggerType_EventFilters(), this.getEventFiltersType(), null, "eventFilters", null, 0, -1, TriggerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTriggerType_Mapping(), this.getMappingType(), null, "mapping", null, 0, -1, TriggerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTriggerType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, TriggerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeTypeEClass, TypeType.class, "TypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeType_ClassName(), theXMLTypePackage.getString(), "className", null, 0, 1, TypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, TypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueTypeEClass, ValueType.class, "ValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variablesTypeEClass, VariablesType.class, "VariablesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariablesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, VariablesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariablesType_Variable(), this.getVariableType(), null, "variable", null, 0, -1, VariablesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(variableTypeEClass, VariableType.class, "VariableType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariableType_Type(), this.getTypeType(), null, "type", null, 0, -1, VariableType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getVariableType_Value(), this.getValueType(), null, "value", null, 0, -1, VariableType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workItemTypeEClass, WorkItemType.class, "WorkItemType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkItemType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_MetaData(), this.getMetaDataType(), null, "metaData", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_Timers(), this.getTimersType(), null, "timers", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_Work(), this.getWorkType(), null, "work", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_Mapping(), this.getMappingType(), null, "mapping", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_OnEntry(), this.getOnEntryType(), null, "onEntry", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getWorkItemType_OnExit(), this.getOnExitType(), null, "onExit", null, 0, -1, WorkItemType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_Height(), theXMLTypePackage.getString(), "height", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_WaitForCompletion(), theXMLTypePackage.getString(), "waitForCompletion", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_X(), theXMLTypePackage.getString(), "x", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkItemType_Y(), theXMLTypePackage.getString(), "y", null, 0, 1, WorkItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workTypeEClass, WorkType.class, "WorkType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, WorkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkType_Parameter(), this.getParameterType(), null, "parameter", null, 0, -1, WorkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, WorkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (actionNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "actionNode_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getActionNodeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getActionNodeType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getActionNodeType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getActionNodeType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getActionNodeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getActionNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getActionNodeType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getActionNodeType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getActionNodeType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (actionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "action_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getActionType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getActionType_Dialect(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "dialect"
		   });		
		addAnnotation
		  (getActionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getActionType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (compositeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "composite_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getCompositeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getCompositeType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variables",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_ExceptionHandlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandlers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_Nodes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "nodes",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_InPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_OutPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCompositeType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getCompositeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getCompositeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getCompositeType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getCompositeType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getCompositeType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (connectionsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "connections_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConnectionsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getConnectionsType_Connection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connection",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (connectionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "connection_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getConnectionType_Bendpoints(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "bendpoints"
		   });		
		addAnnotation
		  (getConnectionType_From(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "from"
		   });		
		addAnnotation
		  (getConnectionType_FromType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fromType"
		   });		
		addAnnotation
		  (getConnectionType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (getConnectionType_ToType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "toType"
		   });		
		addAnnotation
		  (constraintsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "constraints_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConstraintsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getConstraintsType_Constraint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraint",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (constraintTypeEClass, 
		   source, 
		   new String[] {
			 "name", "constraint_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getConstraintType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getConstraintType_Dialect(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "dialect"
		   });		
		addAnnotation
		  (getConstraintType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getConstraintType_Priority(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "priority"
		   });		
		addAnnotation
		  (getConstraintType_ToNodeId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "toNodeId"
		   });		
		addAnnotation
		  (getConstraintType_ToType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "toType"
		   });		
		addAnnotation
		  (getConstraintType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ActionNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "actionNode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Composite(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "composite",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Connection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connection",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Constraint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraint",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Constraints(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraints",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Dynamic(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "dynamic",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_End(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "end",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EventFilter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventFilter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EventFilters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventFilters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EventNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventNode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExceptionHandlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandlers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Fault(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fault",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ForEach(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "forEach",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_FunctionImport(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "functionImport",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_FunctionImports(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "functionImports",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Global(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "global",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Globals(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "globals",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Header(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "header",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_HumanTask(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "humanTask",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Import(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "import",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Imports(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "imports",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_InPort(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-port",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_InPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-ports",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "join",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mapping",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Milestone(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "milestone",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Nodes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "nodes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OutPort(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-port",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_OutPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-ports",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Parameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "parameter",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Process(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "process",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_RuleSet(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ruleSet",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Split(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "split",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Start(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "start",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_State(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SubProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "subProcess",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlane",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Swimlanes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlanes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TimerNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timerNode",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Trigger(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "trigger",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Triggers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "triggers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Type(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "value",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Variable(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variable",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variables",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Work(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "work",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_WorkItem(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "workItem",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (dynamicTypeEClass, 
		   source, 
		   new String[] {
			 "name", "dynamic_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDynamicType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getDynamicType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variables",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_ExceptionHandlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandlers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_Nodes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "nodes",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_InPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_OutPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDynamicType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getDynamicType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getDynamicType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getDynamicType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getDynamicType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getDynamicType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (endTypeEClass, 
		   source, 
		   new String[] {
			 "name", "end_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEndType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getEndType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEndType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getEndType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getEndType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getEndType_Terminate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "terminate"
		   });		
		addAnnotation
		  (getEndType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getEndType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getEndType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (eventFiltersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "eventFilters_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEventFiltersType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getEventFiltersType_EventFilter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventFilter",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (eventFilterTypeEClass, 
		   source, 
		   new String[] {
			 "name", "eventFilter_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getEventFilterType_EventType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "eventType"
		   });		
		addAnnotation
		  (getEventFilterType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (eventNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "eventNode_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEventNodeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getEventNodeType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEventNodeType_EventFilters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventFilters",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEventNodeType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getEventNodeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getEventNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getEventNodeType_Scope(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "scope"
		   });		
		addAnnotation
		  (getEventNodeType_VariableName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variableName"
		   });		
		addAnnotation
		  (getEventNodeType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getEventNodeType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getEventNodeType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (exceptionHandlersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "exceptionHandlers_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExceptionHandlersType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getExceptionHandlersType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (exceptionHandlerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "exceptionHandler_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExceptionHandlerType_FaultName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "faultName"
		   });		
		addAnnotation
		  (getExceptionHandlerType_FaultVariable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "faultVariable"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (faultTypeEClass, 
		   source, 
		   new String[] {
			 "name", "fault_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFaultType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getFaultType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFaultType_FaultName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "faultName"
		   });		
		addAnnotation
		  (getFaultType_FaultVariable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "faultVariable"
		   });		
		addAnnotation
		  (getFaultType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getFaultType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getFaultType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getFaultType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getFaultType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getFaultType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (forEachTypeEClass, 
		   source, 
		   new String[] {
			 "name", "forEach_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getForEachType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getForEachType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForEachType_Nodes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "nodes",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForEachType_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForEachType_InPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForEachType_OutPorts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-ports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForEachType_CollectionExpression(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "collectionExpression"
		   });		
		addAnnotation
		  (getForEachType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getForEachType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getForEachType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getForEachType_VariableName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "variableName"
		   });		
		addAnnotation
		  (getForEachType_WaitForCompletion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "waitForCompletion"
		   });		
		addAnnotation
		  (getForEachType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getForEachType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getForEachType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (functionImportsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "functionImports_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFunctionImportsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getFunctionImportsType_FunctionImport(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "functionImport",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (functionImportTypeEClass, 
		   source, 
		   new String[] {
			 "name", "functionImport_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getFunctionImportType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (globalsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "globals_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getGlobalsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getGlobalsType_Global(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "global",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (globalTypeEClass, 
		   source, 
		   new String[] {
			 "name", "global_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getGlobalType_Identifier(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "identifier"
		   });		
		addAnnotation
		  (getGlobalType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (headerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "header_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getHeaderType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getHeaderType_Imports(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "imports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHeaderType_FunctionImports(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "functionImports",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHeaderType_Globals(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "globals",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHeaderType_Variables(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variables",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHeaderType_Swimlanes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlanes",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHeaderType_ExceptionHandlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exceptionHandlers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (humanTaskTypeEClass, 
		   source, 
		   new String[] {
			 "name", "humanTask_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getHumanTaskType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_Work(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "work",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mapping",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getHumanTaskType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getHumanTaskType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getHumanTaskType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getHumanTaskType_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "swimlane"
		   });		
		addAnnotation
		  (getHumanTaskType_WaitForCompletion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "waitForCompletion"
		   });		
		addAnnotation
		  (getHumanTaskType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getHumanTaskType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getHumanTaskType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (importsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "imports_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getImportsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getImportsType_Import(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "import",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (importTypeEClass, 
		   source, 
		   new String[] {
			 "name", "import_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getImportType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (inPortsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "in-ports_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getInPortsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getInPortsType_InPort(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "in-port",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (inPortTypeEClass, 
		   source, 
		   new String[] {
			 "name", "in-port_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getInPortType_NodeId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "nodeId"
		   });		
		addAnnotation
		  (getInPortType_NodeInType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "nodeInType"
		   });		
		addAnnotation
		  (getInPortType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (joinTypeEClass, 
		   source, 
		   new String[] {
			 "name", "join_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getJoinType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getJoinType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJoinType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getJoinType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getJoinType_N(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "n"
		   });		
		addAnnotation
		  (getJoinType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getJoinType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (getJoinType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getJoinType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getJoinType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (mappingTypeEClass, 
		   source, 
		   new String[] {
			 "name", "mapping_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getMappingType_From(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "from"
		   });		
		addAnnotation
		  (getMappingType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (getMappingType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (metaDataTypeEClass, 
		   source, 
		   new String[] {
			 "name", "metaData_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMetaDataType_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "value",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getMetaDataType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (milestoneTypeEClass, 
		   source, 
		   new String[] {
			 "name", "milestone_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMilestoneType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getMilestoneType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMilestoneType_Constraint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraint",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMilestoneType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMilestoneType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMilestoneType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMilestoneType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getMilestoneType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getMilestoneType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getMilestoneType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getMilestoneType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getMilestoneType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (nodesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "nodes_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getNodesType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getNodesType_Start(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "start",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_End(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "end",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_ActionNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "actionNode",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_RuleSet(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ruleSet",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Split(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "split",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "join",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Milestone(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "milestone",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_SubProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "subProcess",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_WorkItem(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "workItem",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_TimerNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timerNode",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_HumanTask(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "humanTask",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Composite(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "composite",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_ForEach(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "forEach",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_EventNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventNode",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Fault(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fault",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_State(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getNodesType_Dynamic(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "dynamic",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (onEntryTypeEClass, 
		   source, 
		   new String[] {
			 "name", "onEntry_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getOnEntryType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getOnEntryType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (onExitTypeEClass, 
		   source, 
		   new String[] {
			 "name", "onExit_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getOnExitType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getOnExitType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (outPortsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "out-ports_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getOutPortsType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getOutPortsType_OutPort(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "out-port",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (outPortTypeEClass, 
		   source, 
		   new String[] {
			 "name", "out-port_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getOutPortType_NodeId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "nodeId"
		   });		
		addAnnotation
		  (getOutPortType_NodeOutType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "nodeOutType"
		   });		
		addAnnotation
		  (getOutPortType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (parameterTypeEClass, 
		   source, 
		   new String[] {
			 "name", "parameter_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getParameterType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getParameterType_Type(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "type",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getParameterType_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "value",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getParameterType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (processTypeEClass, 
		   source, 
		   new String[] {
			 "name", "process_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getProcessType_Header(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "header",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessType_Nodes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "nodes",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessType_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getProcessType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getProcessType_PackageName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "package-name"
		   });		
		addAnnotation
		  (getProcessType_RouterLayout(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "routerLayout"
		   });		
		addAnnotation
		  (getProcessType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (getProcessType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });		
		addAnnotation
		  (ruleSetTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ruleSet_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRuleSetType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getRuleSetType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getRuleSetType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getRuleSetType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getRuleSetType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getRuleSetType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getRuleSetType_RuleFlowGroup(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "ruleFlowGroup"
		   });		
		addAnnotation
		  (getRuleSetType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getRuleSetType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getRuleSetType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (splitTypeEClass, 
		   source, 
		   new String[] {
			 "name", "split_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSplitType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getSplitType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSplitType_Constraints(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraints",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSplitType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getSplitType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getSplitType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getSplitType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (getSplitType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getSplitType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getSplitType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (startTypeEClass, 
		   source, 
		   new String[] {
			 "name", "start_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStartType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getStartType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartType_Triggers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "triggers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getStartType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getStartType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getStartType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getStartType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getStartType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (stateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStateType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getStateType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStateType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStateType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStateType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStateType_Constraints(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraints",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStateType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getStateType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getStateType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getStateType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getStateType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (subProcessTypeEClass, 
		   source, 
		   new String[] {
			 "name", "subProcess_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSubProcessType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getSubProcessType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSubProcessType_Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mapping",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSubProcessType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSubProcessType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSubProcessType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSubProcessType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getSubProcessType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getSubProcessType_Independent(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "independent"
		   });		
		addAnnotation
		  (getSubProcessType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getSubProcessType_ProcessId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "processId"
		   });		
		addAnnotation
		  (getSubProcessType_WaitForCompletion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "waitForCompletion"
		   });		
		addAnnotation
		  (getSubProcessType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getSubProcessType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getSubProcessType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (swimlanesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "swimlanes_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSwimlanesType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getSwimlanesType_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlane",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (swimlaneTypeEClass, 
		   source, 
		   new String[] {
			 "name", "swimlane_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getSwimlaneType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (timerNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timerNode_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTimerNodeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTimerNodeType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTimerNodeType_Delay(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "delay"
		   });		
		addAnnotation
		  (getTimerNodeType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getTimerNodeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getTimerNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTimerNodeType_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period"
		   });		
		addAnnotation
		  (getTimerNodeType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getTimerNodeType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getTimerNodeType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (timersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timers_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTimersType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTimersType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (timerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timer_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTimerType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_Delay(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "delay"
		   });		
		addAnnotation
		  (getTimerType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getTimerType_Period(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "period"
		   });		
		addAnnotation
		  (triggersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "triggers_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTriggersType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTriggersType_Trigger(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "trigger",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (triggerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "trigger_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTriggerType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTriggerType_Constraint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constraint",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTriggerType_EventFilters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "eventFilters",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTriggerType_Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mapping",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTriggerType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (typeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "type_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTypeType_ClassName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "className"
		   });		
		addAnnotation
		  (getTypeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (valueTypeEClass, 
		   source, 
		   new String[] {
			 "name", "value_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getValueType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (variablesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "variables_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getVariablesType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getVariablesType_Variable(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variable",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (variableTypeEClass, 
		   source, 
		   new String[] {
			 "name", "variable_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getVariableType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getVariableType_Type(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "type",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getVariableType_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "value",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getVariableType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (workItemTypeEClass, 
		   source, 
		   new String[] {
			 "name", "workItem_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkItemType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getWorkItemType_MetaData(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "metaData",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_Timers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timers",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_Work(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "work",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_Mapping(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mapping",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_OnEntry(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onEntry",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_OnExit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "onExit",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkItemType_Height(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "height"
		   });		
		addAnnotation
		  (getWorkItemType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getWorkItemType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getWorkItemType_WaitForCompletion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "waitForCompletion"
		   });		
		addAnnotation
		  (getWorkItemType_Width(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "width"
		   });		
		addAnnotation
		  (getWorkItemType_X(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "x"
		   });		
		addAnnotation
		  (getWorkItemType_Y(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "y"
		   });		
		addAnnotation
		  (workTypeEClass, 
		   source, 
		   new String[] {
			 "name", "work_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getWorkType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getWorkType_Parameter(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "parameter",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getWorkType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });
	}

} //ProcessPackageImpl
