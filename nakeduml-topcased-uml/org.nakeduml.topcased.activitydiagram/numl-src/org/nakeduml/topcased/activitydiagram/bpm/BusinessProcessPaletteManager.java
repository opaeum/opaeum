package org.nakeduml.topcased.activitydiagram.bpm;

import java.util.List;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.nakeduml.topcased.classdiagram.NakedElementCreationFactory;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

/**
 * Generated Palette Manager <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class BusinessProcessPaletteManager extends ModelerPaletteManager{
	// declare all the palette categories of the diagram
	private PaletteDrawer commonDrawer;
	private PaletteDrawer controlDrawer;
	private PaletteDrawer actionsDrawer;
	private PaletteDrawer objectDrawer;
	private PaletteDrawer connectionsDrawer;
	private ICreationUtils creationUtils;
	private PaletteDrawer bpmDrawer;
	public BusinessProcessPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	protected void createCategories(){
		createCommonDrawer();
		createControlDrawer();
		createActionsDrawer();
		createObjectDrawer();
		createConnectionsDrawer();
		createCommentDrawer();
		createBpmDrawer();
	}
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated categories
		getRoot().remove(commonDrawer);
		createCommonDrawer();
		getRoot().remove(controlDrawer);
		createControlDrawer();
		getRoot().remove(actionsDrawer);
		createActionsDrawer();
		getRoot().remove(objectDrawer);
		createObjectDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
		getRoot().remove(bpmDrawer);
		createBpmDrawer();
		// getRoot().remove(commentDrawer);
		// createCommentDrawer();
	}
	private void createBpmDrawer(){
		bpmDrawer = new PaletteDrawer("BPM", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory esctfactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOpaqueAction(), StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		entries.add(new ModelerCreationToolEntry("Simple Task", "Simple Task", esctfactory, ActivityImageRegistry.getImageDescriptor("OPAQUEACTION"),
				ActivityImageRegistry.getImageDescriptor("OPAQUEACTION_LARGE")));
		CreationFactory escftfactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(), StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		entries.add(new ModelerCreationToolEntry("Screen Flow Task", "Screen Flow Task", escftfactory, ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"),
				ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE")));
		CreationFactory acceptDeadline = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(),
				StereotypeNames.ACCEPT_DEADLINE_ACTION);
		entries.add(new ModelerCreationToolEntry("On Deadline Reached", "On Deadline Reached", acceptDeadline, ActivityImageRegistry
				.getImageDescriptor("ACCEPTTIMEEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTTIMEEVENTACTION_LARGE")));
		CreationFactory acceptTaskEvent = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(),
				StereotypeNames.ACCEPT_TASK_EVENT_ACTION);
		entries.add(new ModelerCreationToolEntry("On Task Event", "On Task Event", acceptTaskEvent, ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION"),
				ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION_LARGE")));
		CreationFactory callBusinessProcess = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(),
				StereotypeNames.CALL_BUSINES_PROCESS_ACTION);
		entries.add(new ModelerCreationToolEntry("Call Business Process", "Call Business Process", callBusinessProcess,
				ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE")));
		CreationFactory sendNotification = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendSignalAction(),
				StereotypeNames.SEND_NOTIFICATION_ACTION);
		entries.add(new ModelerCreationToolEntry("Send Notification", "Send Notification", sendNotification,
				ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION"), ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION_LARGE")));
		bpmDrawer.addAll(entries);
		if(bpmDrawer.getChildren().size() > 0){
			getRoot().add(bpmDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createCommonDrawer(){
		commonDrawer = new PaletteDrawer("Common", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getActivityPartition(), "default");
		entries.add(new ModelerCreationToolEntry("Activity Partition", "Activity Partition", factory, ActivityImageRegistry.getImageDescriptor("ACTIVITYPARTITION"),
				ActivityImageRegistry.getImageDescriptor("ACTIVITYPARTITION_LARGE")));
		commonDrawer.addAll(entries);
		commonDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		if(commonDrawer.getChildren().size() > 0){
			getRoot().add(commonDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createControlDrawer(){
		controlDrawer = new PaletteDrawer("Control", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		PaletteStack structuredActivitiesStack = new PaletteStack("ForEach Loop", "ForEach Loop", ActivityImageRegistry.getImageDescriptor("EXPANSIONREGION"));
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInitialNode(), "default");
		entries.add(new ModelerCreationToolEntry("Initial Node", "Initial Node", factory, ActivityImageRegistry.getImageDescriptor("INITIALNODE"), ActivityImageRegistry
				.getImageDescriptor("INITIALNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDecisionNode(), "default");
		entries.add(new ModelerCreationToolEntry("Decision Node", "Decision Node", factory, ActivityImageRegistry.getImageDescriptor("DECISIONNODE"),
				ActivityImageRegistry.getImageDescriptor("DECISIONNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getMergeNode(), "default");
		entries.add(new ModelerCreationToolEntry("Merge Node", "Merge Node", factory, ActivityImageRegistry.getImageDescriptor("MERGENODE"), ActivityImageRegistry
				.getImageDescriptor("MERGENODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getForkNode(), "default");
		entries.add(new ModelerCreationToolEntry("Fork Node", "Fork Node", factory, ActivityImageRegistry.getImageDescriptor("FORKNODE"), ActivityImageRegistry
				.getImageDescriptor("FORKNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getJoinNode(), "default");
		entries.add(new ModelerCreationToolEntry("Join Node", "Join Node", factory, ActivityImageRegistry.getImageDescriptor("JOINNODE"), ActivityImageRegistry
				.getImageDescriptor("JOINNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getActivityFinalNode(), "default");
		entries.add(new ModelerCreationToolEntry("Activity Final", "Activity Final", factory, ActivityImageRegistry.getImageDescriptor("ACTIVITYFINALNODE"),
				ActivityImageRegistry.getImageDescriptor("ACTIVITYFINALNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getFlowFinalNode(), "default");
		entries.add(new ModelerCreationToolEntry("Flow Final", "Flow Final", factory, ActivityImageRegistry.getImageDescriptor("FLOWFINALNODE"), ActivityImageRegistry
				.getImageDescriptor("FLOWFINALNODE_LARGE")));
		// =================================== StructuredActivities ====================================
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getStructuredActivityNode(), "default");
		// structuredActivitiesStack.add(new ModelerCreationToolEntry("Structured Activity Node", "Structured Activity Node", factory,
		// ActivityImageRegistry.getImageDescriptor("STRUCTUREDACTIVITY"),
		// ActivityImageRegistry.getImageDescriptor("STRUCTUREDACTIVITY_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConditionalNode(), "default");
		// structuredActivitiesStack.add(new ModelerCreationToolEntry("Conditional Node", "Conditional Node", factory,
		// ActivityImageRegistry.getImageDescriptor("CONDITIONALNODE"),
		// ActivityImageRegistry.getImageDescriptor("CONDITIONALNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExpansionRegion(), "default");
		PaletteEntry defaultPaletteEntry = new ModelerCreationToolEntry("ForEach Loop", "ForEach Loop", factory,
				ActivityImageRegistry.getImageDescriptor("EXPANSIONREGION"), ActivityImageRegistry.getImageDescriptor("EXPANSIONREGION_LARGE"));
		structuredActivitiesStack.add(defaultPaletteEntry);
		structuredActivitiesStack.setActiveEntry(defaultPaletteEntry);
		// Fix #809
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterruptibleActivityRegion(), "default");
		structuredActivitiesStack.add(new ModelerCreationToolEntry("Interruptible Container", "Interruptible Container", factory, ActivityImageRegistry
				.getImageDescriptor("INTERRUPTIBLEACTIVITYREGION"), ActivityImageRegistry.getImageDescriptor("INTERRUPTIBLEACTIVITYREGION_LARGE")));
		// EndFix #809
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getLoopNode(), "default");
		// structuredActivitiesStack.add(new ModelerCreationToolEntry("Loop Node", "Loop Node", factory,
		// ActivityImageRegistry.getImageDescriptor("LOOPNODE"),
		// ActivityImageRegistry.getImageDescriptor("LOOPNODE_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSequenceNode(), "default");
		// structuredActivitiesStack.add(new ModelerCreationToolEntry("Sequence Node", "Sequence Node", factory,
		// ActivityImageRegistry.getImageDescriptor("SEQUENCENODE"),
		// ActivityImageRegistry.getImageDescriptor("SEQUENCENODE_LARGE")));
		entries.add(structuredActivitiesStack);
		controlDrawer.addAll(entries);
		if(controlDrawer.getChildren().size() > 0){
			getRoot().add(controlDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createActionsDrawer(){
		actionsDrawer = new PaletteDrawer("Actions", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory factory;
		PaletteEntry defaultPaletteEntry;
		PaletteStack basicActionsStack = new PaletteStack("Call Method", "Call Method", ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"));
		PaletteStack structuralFeatureActionsStack = new PaletteStack("Read Property", "Read Property",
				ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION"));
		PaletteStack structuredActionsStack = new PaletteStack("Read Variable", "Read Variable",
				ActivityImageRegistry.getImageDescriptor("READVARIABLEACTION"));
		PaletteStack completeActionsStack = new PaletteStack("Wait for Event", "Wait for Event", ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION"));
		// =================================== BasicActions ====================================
		NakedElementCreationFactory cmaFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(), StereotypeNames.CALL_METHOD_ACTION);
		defaultPaletteEntry = new ModelerCreationToolEntry("Call Method", "Call Method", cmaFactory,
				ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE"));
		basicActionsStack.add(defaultPaletteEntry);
		basicActionsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallOperationAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Call Operation", "Call Operation", factory, ActivityImageRegistry
				.getImageDescriptor("CALLOPERATIONACTION"), ActivityImageRegistry.getImageDescriptor("CALLOPERATIONACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendObjectAction(), "default");
		// basicActionsStack.add(new ModelerCreationToolEntry("Send Object Action", "Send Object Action", factory,
		// ActivityImageRegistry.getImageDescriptor("SENDOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("SENDOBJECTACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendSignalAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Send Signal", "Send Signal", factory, ActivityImageRegistry
				.getImageDescriptor("SENDSIGNALACTION"), ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOpaqueAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Opaque Action", "Opaque Action", factory, ActivityImageRegistry.getImageDescriptor("OPAQUEACTION"),
				ActivityImageRegistry.getImageDescriptor("OPAQUEACTION_LARGE")));
		// =================================== IntermediateActions ====================================
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadSelfAction(), "default");
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCreateObjectAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Create Object", "Create Object", factory, ActivityImageRegistry
				.getImageDescriptor("CREATEOBJECTACTION"), ActivityImageRegistry.getImageDescriptor("CREATEOBJECTACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getBroadcastSignalAction(), "default");
		// intermediateActionsStack.add(new ModelerCreationToolEntry("Broadcast Signal Action", "Broadcast Signal Action", factory,
		// ActivityImageRegistry.getImageDescriptor("BROADCASTSIGNALACTION"),
		// ActivityImageRegistry.getImageDescriptor("BROADCASTSIGNALACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getTestIdentityAction(), "default");
		// intermediateActionsStack.add(new ModelerCreationToolEntry("Test Identity Action", "Test Identity Action", factory,
		// ActivityImageRegistry.getImageDescriptor("TESTIDENTITYACTION"),
		// ActivityImageRegistry.getImageDescriptor("TESTIDENTITYACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getValueSpecificationAction(), "default");
		// intermediateActionsStack.add(new ModelerCreationToolEntry("Value Specification Action", "Value Specification Action", factory,
		// ActivityImageRegistry.getImageDescriptor("VALUESPECIFICATIONACTION"),
		// ActivityImageRegistry.getImageDescriptor("VALUESPECIFICATIONACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClearAssociationAction(), "default");
		// intermediateActionsStack.add(new ModelerCreationToolEntry("Clear Association Action", "Clear Association Action", factory,
		// ActivityImageRegistry.getImageDescriptor("CLEARASSOCIATIONACTION"),
		// ActivityImageRegistry.getImageDescriptor("CLEARASSOCIATIONACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDestroyObjectAction(), "default");
		// intermediateActionsStack.add(new ModelerCreationToolEntry("Destroy Object Action", "Destroy Object Action", factory,
		// ActivityImageRegistry.getImageDescriptor("DESTROYOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("DESTROYOBJECTACTION_LARGE")));
		// =================================== CompleteActions ====================================
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(), "default");
		defaultPaletteEntry = new ModelerCreationToolEntry("Wait for Event", "Wait for Event", factory,
				ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION_LARGE"));
		completeActionsStack.add(defaultPaletteEntry);
		completeActionsStack.setActiveEntry(defaultPaletteEntry);
		// Fix #807
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(), "default"){
			public EObject getNewModelObject(){
				AcceptEventAction element = (AcceptEventAction) super.getNewModelObject();
				element.createTrigger("Trigger1");
				return element;
			}
		};
		completeActionsStack.add(new ModelerCreationToolEntry("Accept Time Event Action", "Accept Time Event Action", factory, ActivityImageRegistry
				.getImageDescriptor("ACCEPTTIMEEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTTIMEEVENTACTION_LARGE")));
		// EndFix #807
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptCallAction(), "default");
		completeActionsStack.add(new ModelerCreationToolEntry("Receive Call", "Receive Call", factory, ActivityImageRegistry
				.getImageDescriptor("ACCEPTCALLACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCreateLinkObjectAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Create Link Object Action", "Create Link Object Action", factory,
		// ActivityImageRegistry.getImageDescriptor("CREATELINKOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("CREATELINKOBJECTACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadExtentAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Read Extent Action", "Read Extent Action", factory,
		// ActivityImageRegistry.getImageDescriptor("READEXTENTACTION"),
		// ActivityImageRegistry.getImageDescriptor("READEXTENTACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadIsClassifiedObjectAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Read Is Classified Object Action", "Read Is Classified Object Action",
		// factory,
		// ActivityImageRegistry.getImageDescriptor("READISCLASSIFIEDOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("READISCLASSIFIEDOBJECTACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadLinkObjectEndAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Read Link Object End Action", "Read Link Object End Action", factory,
		// ActivityImageRegistry.getImageDescriptor("READLINKOBJECTENDACTION"),
		// ActivityImageRegistry.getImageDescriptor("READLINKOBJECTENDACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadLinkObjectEndQualifierAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Read Link Object End Qualifier Action",
		// "Read Link Object End Qualifier Action", factory,
		// ActivityImageRegistry.getImageDescriptor("READLINKOBJECTENDQUALIFIERACTION"),
		// ActivityImageRegistry.getImageDescriptor("READLINKOBJECTENDQUALIFIERACTION_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReclassifyObjectAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Reclassify Object Action", "Reclassify Object Action", factory,
		// ActivityImageRegistry.getImageDescriptor("RECLASSIFYOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("RECLASSIFYOBJECTACTION_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReduceAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Reduce Action", "Reduce Action", factory,
		// ActivityImageRegistry.getImageDescriptor("REDUCEACTION"),
		// ActivityImageRegistry.getImageDescriptor("REDUCEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReplyAction(), "default");
		completeActionsStack.add(new ModelerCreationToolEntry("Reply Action", "Reply Action", factory, ActivityImageRegistry.getImageDescriptor("REPLYACTION"),
				ActivityImageRegistry.getImageDescriptor("REPLYACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getStartClassifierBehaviorAction(), "default");
		completeActionsStack.add(new ModelerCreationToolEntry("Start Classifier Behavior Action", "Start Classifier Behavior Action", factory, ActivityImageRegistry
				.getImageDescriptor("STARTCLASSIFIERBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("STARTCLASSIFIERBEHAVIORACTION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getUnmarshallAction(), "default");
		// completeActionsStack.add(new ModelerCreationToolEntry("Unmarshall Action", "Unmarshall Action", factory,
		// ActivityImageRegistry.getImageDescriptor("UNMARSHALLACTION"),
		// ActivityImageRegistry.getImageDescriptor("UNMARSHALLACTION_LARGE")));
		// =================================== StructuralFeatureActions ====================================
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClearStructuralFeatureAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Clear Property Values", "Clear Property Values", factory, ActivityImageRegistry
				.getImageDescriptor("CLEARSTRUCTURALFEATUREACTION"), ActivityImageRegistry.getImageDescriptor("CLEARSTRUCTURALFEATUREACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadStructuralFeatureAction(), "default");
		defaultPaletteEntry = new ModelerCreationToolEntry("Read Property", "Read Property", factory,
				ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION"), ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION_LARGE"));
		structuralFeatureActionsStack.add(defaultPaletteEntry);
		structuralFeatureActionsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAddStructuralFeatureValueAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Add Property Value", "Add Property Value", factory, ActivityImageRegistry
				.getImageDescriptor("ADDSTRUCTURALFEATUREVALUEACTION"), ActivityImageRegistry.getImageDescriptor("ADDSTRUCTURALFEATUREVALUEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getRemoveStructuralFeatureValueAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Remove Property Value Action", "Remove Property Value Action", factory, ActivityImageRegistry
				.getImageDescriptor("REMOVESTRUCTURALFEATUREVALUEACTION"), ActivityImageRegistry.getImageDescriptor("REMOVESTRUCTURALFEATUREVALUEACTION_LARGE")));
		// =================================== StructuredActions ====================================
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadVariableAction(), "default");
		defaultPaletteEntry = new ModelerCreationToolEntry("Read Variable Action", "Read Variable Action", factory,
				ActivityImageRegistry.getImageDescriptor("READVARIABLEACTION"), ActivityImageRegistry.getImageDescriptor("READVARIABLEACTION_LARGE"));
		structuredActionsStack.add(defaultPaletteEntry);
		structuredActionsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClearVariableAction(), "default");
		structuredActionsStack.add(new ModelerCreationToolEntry("Clear Variable Action", "Clear Variable Action", factory, ActivityImageRegistry
				.getImageDescriptor("CLEARVARIABLEACTION"), ActivityImageRegistry.getImageDescriptor("CLEARVARIABLEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAddVariableValueAction(), "default");
		structuredActionsStack.add(new ModelerCreationToolEntry("Add Variable Value Action", "Add Variable Value Action", factory, ActivityImageRegistry
				.getImageDescriptor("ADDVARIABLEVALUEACTION"), ActivityImageRegistry.getImageDescriptor("ADDVARIABLEVALUEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getRemoveVariableValueAction(), "default");
		structuredActionsStack.add(new ModelerCreationToolEntry("Remove Variable Value Action", "Remove Variable Value Action", factory, ActivityImageRegistry
				.getImageDescriptor("REMOVEVARIABLEVALUEACTION"), ActivityImageRegistry.getImageDescriptor("REMOVEVARIABLEVALUEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getRaiseExceptionAction(), "default");
		structuredActionsStack.add(new ModelerCreationToolEntry("Raise Exception Action", "Raise Exception Action", factory, ActivityImageRegistry
				.getImageDescriptor("RAISEEXCEPTIONACTION"), ActivityImageRegistry.getImageDescriptor("RAISEEXCEPTIONACTION_LARGE")));
		entries.add(basicActionsStack);
		entries.add(structuralFeatureActionsStack);
		entries.add(structuredActionsStack);
		entries.add(completeActionsStack);
		actionsDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		actionsDrawer.addAll(entries);
		if(actionsDrawer.getChildren().size() > 0){
			getRoot().add(actionsDrawer);
		}
	}
	private void createObjectDrawer(){
		objectDrawer = new PaletteDrawer("Object", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getActivityParameterNode(), "default"){
			public EObject getNewModelObject(){
				ActivityParameterNode element = (ActivityParameterNode) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Activity Parameter", "Activity Parameter", factory, ActivityImageRegistry.getImageDescriptor("ACTIVITYPARAMETERNODE"),
				ActivityImageRegistry.getImageDescriptor("ACTIVITYPARAMETERNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExpansionNode(), "default"){
			public EObject getNewModelObject(){
				ExpansionNode element = (ExpansionNode) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Loop Collection", "Loop Collection", factory, ActivityImageRegistry.getImageDescriptor("EXPANSIONNODE"),
				ActivityImageRegistry.getImageDescriptor("EXPANSIONNODE_LARGE")));
		GraphElementCreationFactory vpFactory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getValuePin(), "default"){
			public EObject getNewModelObject(){
				ValuePin element = (ValuePin) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.OCL_INPUT, "");
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Ocl Input", "Ocl Input", vpFactory, ActivityImageRegistry.getImageDescriptor("INPUTPIN"), ActivityImageRegistry
				.getImageDescriptor("INPUTPIN_LARGE")));
		GraphElementCreationFactory vpFactory2 = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getValuePin(), "default"){
			public EObject getNewModelObject(){
				ValuePin element = (ValuePin) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
				ann.getDetails().put(StereotypeNames.NEW_OBJECT_INPUT, "");
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("New Object Instance", "New Object Instance", vpFactory2, ActivityImageRegistry.getImageDescriptor("INPUTPIN"), ActivityImageRegistry
				.getImageDescriptor("INPUTPIN_LARGE")));
		GraphElementCreationFactory ipFactory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInputPin(), "default"){
			public EObject getNewModelObject(){
				InputPin element = (InputPin) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Object Flow Input", "Object Flow Input ", ipFactory, ActivityImageRegistry.getImageDescriptor("INPUTPIN"), ActivityImageRegistry
				.getImageDescriptor("INPUTPIN_LARGE")));
		GraphElementCreationFactory opFactory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOutputPin(), "default"){
			public EObject getNewModelObject(){
				OutputPin element = (OutputPin) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		objectDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		entries.add(new ModelerCreationToolEntry("Object Output", "Object Output", opFactory, ActivityImageRegistry.getImageDescriptor("OUTPUTPIN"), ActivityImageRegistry
				.getImageDescriptor("OUTPUTPIN_LARGE")));
		objectDrawer.addAll(entries);
		if(objectDrawer.getChildren().size() > 0){
			getRoot().add(objectDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createConnectionsDrawer(){
		connectionsDrawer = new PaletteDrawer("Connections", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getControlFlow(), "default"){
			public EObject getNewModelObject(){
				ControlFlow element = (ControlFlow) super.getNewModelObject();
				LiteralBoolean guard = UMLFactory.eINSTANCE.createLiteralBoolean();
				LiteralInteger weight = UMLFactory.eINSTANCE.createLiteralInteger();
				guard.setValue(true);
				weight.setValue(1);
				element.setGuard(guard);
				element.setWeight(weight);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Control Flow", "Control Flow", factory, ActivityImageRegistry.getImageDescriptor("CONTROLFLOW"),
				ActivityImageRegistry.getImageDescriptor("CONTROLFLOW_LARGE")));
		// Fix #809
		// Create a new originalElement <=> ControlFlow with his "Interrupts" property set with a default value
		// And constraint on his creation <=> his source must be in a InterruptibleActivityREgion, but not his his
		// target
		/*
		 * factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getControlFlow(), "default") { public EObject
		 * getNewModelObject() { ControlFlow originalElement = (ControlFlow) super.getNewModelObject(); LiteralBoolean guard =
		 * UMLFactory.eINSTANCE.createLiteralBoolean(); LiteralInteger weight = UMLFactory.eINSTANCE.createLiteralInteger();
		 * InterruptibleActivityRegion interrupts = UMLFactory.eINSTANCE.createInterruptibleActivityRegion(); guard.setValue(false);
		 * weight.setValue(1); originalElement.setGuard(guard); originalElement.setWeight(weight); originalElement.setInterrupts(interrupts); return originalElement; } };
		 * entries.add(new ModelerConnectionCreationToolEntry("Interruptible Control Flow", "Interruptible Control Flow", factory,
		 * ActivityImageRegistry.getImageDescriptor("CONTROLFLOW"), ActivityImageRegistry.getImageDescriptor("CONTROLFLOW_LARGE")));
		 */
		// EndFix #809
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getObjectFlow(), "default"){
			public EObject getNewModelObject(){
				ObjectFlow element = (ObjectFlow) super.getNewModelObject();
				LiteralBoolean guard = UMLFactory.eINSTANCE.createLiteralBoolean();
				LiteralInteger weight = UMLFactory.eINSTANCE.createLiteralInteger();
				guard.setValue(true);
				weight.setValue(1);
				element.setGuard(guard);
				element.setWeight(weight);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Object Flow", "Object Flow", factory, ActivityImageRegistry.getImageDescriptor("OBJECTFLOW"),
				ActivityImageRegistry.getImageDescriptor("OBJECTFLOW_LARGE")));
		// Fix #809
		// Create a new originalElement <=> ObjectFlow with his "Interrupts" property set with a default value
		// And constraint on his creation <=> his source must be in a InterruptibleActivityREgion, but not his his
		// target
		/*
		 * factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getObjectFlow(), "default") { public EObject
		 * getNewModelObject() { ObjectFlow originalElement = (ObjectFlow) super.getNewModelObject(); LiteralBoolean guard =
		 * UMLFactory.eINSTANCE.createLiteralBoolean(); LiteralInteger weight = UMLFactory.eINSTANCE.createLiteralInteger();
		 * InterruptibleActivityRegion interrupts = UMLFactory.eINSTANCE.createInterruptibleActivityRegion(); guard.setValue(false);
		 * weight.setValue(1); originalElement.setGuard(guard); originalElement.setWeight(weight); originalElement.setInterrupts(interrupts); return originalElement; } };
		 * entries.add(new ModelerConnectionCreationToolEntry("Interruptible Object Flow", "Interruptible Object Flow", factory,
		 * ActivityImageRegistry.getImageDescriptor("OBJECTFLOW"), ActivityImageRegistry.getImageDescriptor("OBJECTFLOW_LARGE")));
		 */
		// EndFix #809
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExceptionHandler(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Exception Handler", "Exception Handler", factory, ActivityImageRegistry
				.getImageDescriptor("EXCEPTIONHANDLER"), ActivityImageRegistry.getImageDescriptor("EXCEPTIONHANDLER_LARGE")));
		connectionsDrawer.addAll(entries);
		if(connectionsDrawer.getChildren().size() > 0){
			getRoot().add(connectionsDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createCommentDrawer(){
		// commentDrawer = new PaletteDrawer("Comment", null);
		// List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		//
		// CreationFactory factory;
		//
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComment(), "default");
		// entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory, ActivityImageRegistry.getImageDescriptor("COMMENT"),
		// ActivityImageRegistry.getImageDescriptor("COMMENT_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, ActivitySimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK, "default",
		// false);
		// entries.add(new ModelerConnectionCreationToolEntry("Comment Link", "Comment Link", factory,
		// ActivityImageRegistry.getImageDescriptor("COMMENTLINK"),
		// ActivityImageRegistry.getImageDescriptor("COMMENTLINK_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConstraint(), "default");
		// entries.add(new ModelerCreationToolEntry("Constraint", "Constraint", factory,
		// ActivityImageRegistry.getImageDescriptor("CONSTRAINT"), ActivityImageRegistry.getImageDescriptor("CONSTRAINT_LARGE")));
		//
		// factory = new GraphElementCreationFactory(creationUtils, ActivitySimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK, "default",
		// false);
		// entries.add(new ModelerConnectionCreationToolEntry("Constraint Link", "Constraint Link", factory,
		// ActivityImageRegistry.getImageDescriptor("CONSTRAINTLINK"),
		// ActivityImageRegistry.getImageDescriptor("CONSTRAINTLINK_LARGE")));
		//
		// commentDrawer.addAll(entries);
		// if (commentDrawer.getChildren().size() > 0)
		// {
		// getRoot().add(commentDrawer);
		// }
	}
}
