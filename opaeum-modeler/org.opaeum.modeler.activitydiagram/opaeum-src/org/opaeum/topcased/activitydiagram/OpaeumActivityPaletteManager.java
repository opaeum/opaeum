package org.opaeum.topcased.activitydiagram;

import java.util.List;

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
import org.eclipse.uml2.uml.ObjectNodeOrderingKind;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.classdiagram.NakedElementCreationFactory;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public abstract class OpaeumActivityPaletteManager extends ModelerPaletteManager{
	protected PaletteDrawer commonDrawer;
	protected PaletteDrawer controlDrawer;
	protected PaletteDrawer actionsDrawer;
	protected PaletteDrawer objectDrawer;
	protected PaletteDrawer connectionsDrawer;
	protected ICreationUtils creationUtils;

	public OpaeumActivityPaletteManager(ICreationUtils s){
		super();
		this.creationUtils=s;
	}

	protected void createCategories(){
		createCommonDrawer();
		createControlDrawer();
		createActionsDrawer();
		createObjectDrawer();
		createConnectionsDrawer();
		createCommentDrawer();
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
		// getRoot().remove(commentDrawer);
		// createCommentDrawer();
	}

	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void createCommonDrawer(){
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
	protected void createControlDrawer(){
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

	protected void createActionsDrawer(){
		actionsDrawer = new PaletteDrawer("Actions", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		CreationFactory factory;
		PaletteEntry defaultPaletteEntry;
		PaletteStack basicActionsStack = new PaletteStack("Call Method", "Call Method", ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"));
		PaletteStack structuralFeatureActionsStack = new PaletteStack("Read Property", "Read Property",
				ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION"));
		PaletteStack structuredActionsStack = new PaletteStack("Read Variable", "Read Variable",
				ActivityImageRegistry.getImageDescriptor("READVARIABLEACTION"));
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
		// Fix #807
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(), "default"){
			public EObject getNewModelObject(){
				AcceptEventAction element = (AcceptEventAction) super.getNewModelObject();
				element.createTrigger("Trigger1");
				return element;
			}
		};
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getStartClassifierBehaviorAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Start Classifier Behavior Action", "Start Classifier Behavior Action", factory, ActivityImageRegistry
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
		actionsDrawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		actionsDrawer.addAll(entries);
		if(actionsDrawer.getChildren().size() > 0){
			getRoot().add(actionsDrawer);
		}
	}

	protected void createObjectDrawer(){
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
		GraphElementCreationFactory loopInput = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExpansionNode(), "default"){
			public EObject getNewModelObject(){
				ExpansionNode element = (ExpansionNode) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.LOOP_INPUT_COLLECTION, "");
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Loop Input Collection", "Loop Input Collection", loopInput, ActivityImageRegistry.getImageDescriptor("EXPANSIONNODE"),
				ActivityImageRegistry.getImageDescriptor("EXPANSIONNODE_LARGE")));
		GraphElementCreationFactory loopOutput = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExpansionNode(), "default"){
			public EObject getNewModelObject(){
				ExpansionNode element = (ExpansionNode) super.getNewModelObject();
				LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.LOOP_OUTPUT_COLLECTION, "");
				upperBound.setValue(-1);
				element.setUpperBound(upperBound);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Loop Output Collection", "Loop Output Collection", loopOutput, ActivityImageRegistry.getImageDescriptor("EXPANSIONNODE"),
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
	protected void createConnectionsDrawer(){
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
	protected void createCommentDrawer(){
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