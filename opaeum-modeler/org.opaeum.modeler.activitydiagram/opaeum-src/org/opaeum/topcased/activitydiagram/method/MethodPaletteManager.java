package org.opaeum.topcased.activitydiagram.method;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
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
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class MethodPaletteManager extends ModelerPaletteManager{
	private PaletteDrawer controlDrawer;
	private PaletteDrawer actionsDrawer;
	private PaletteDrawer objectDrawer;
	private PaletteDrawer connectionsDrawer;
	private ICreationUtils creationUtils;
	public MethodPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	protected void createCategories(){
		createControlDrawer();
		createActionsDrawer();
		createObjectDrawer();
		createConnectionsDrawer();
	}
	protected void updateCategories(){
		getRoot().remove(controlDrawer);
		createControlDrawer();
		getRoot().remove(actionsDrawer);
		createActionsDrawer();
		getRoot().remove(objectDrawer);
		createObjectDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
	}
	private void createControlDrawer(){
		controlDrawer = new PaletteDrawer("Control", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram.method");
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
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getActivityFinalNode(), "default");
		entries.add(new ModelerCreationToolEntry("Activity Final", "Activity Final", factory, ActivityImageRegistry.getImageDescriptor("ACTIVITYFINALNODE"),
				ActivityImageRegistry.getImageDescriptor("ACTIVITYFINALNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getFlowFinalNode(), "default");
		entries.add(new ModelerCreationToolEntry("Flow Final", "Flow Final", factory, ActivityImageRegistry.getImageDescriptor("FLOWFINALNODE"), ActivityImageRegistry
				.getImageDescriptor("FLOWFINALNODE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExpansionRegion(), "default");
		PaletteEntry defaultPaletteEntry = new ModelerCreationToolEntry("ForEach Loop", "ForEach Loop", factory,
				ActivityImageRegistry.getImageDescriptor("EXPANSIONREGION"), ActivityImageRegistry.getImageDescriptor("EXPANSIONREGION_LARGE"));
		structuredActivitiesStack.add(defaultPaletteEntry);
		structuredActivitiesStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterruptibleActivityRegion(), "default");
		structuredActivitiesStack.add(new ModelerCreationToolEntry("Interruptible Container", "Interruptible Container", factory, ActivityImageRegistry
				.getImageDescriptor("INTERRUPTIBLEACTIVITYREGION"), ActivityImageRegistry.getImageDescriptor("INTERRUPTIBLEACTIVITYREGION_LARGE")));
		entries.add(structuredActivitiesStack);
		controlDrawer.addAll(entries);
		if(controlDrawer.getChildren().size() > 0){
			getRoot().add(controlDrawer);
		}
	}
	private void createActionsDrawer(){
		actionsDrawer = new PaletteDrawer("Actions", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram.method");
		CreationFactory factory;
		PaletteEntry defaultPaletteEntry;
		PaletteStack basicActionsStack = new PaletteStack("Call Behavior Action", "Call Behavior Action", ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"));
		// PaletteStack intermediateActionsStack = new PaletteStack("Read Link Action", "Read Link Action",
		// ActivityImageRegistry.getImageDescriptor("READLINKACTION"));
		PaletteStack structuralFeatureActionsStack = new PaletteStack("Read Structural Feature Action", "Read Structural Feature Action",
				ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION"));
		PaletteStack structuredActionsStack = new PaletteStack("Read Variable Action", "Read Variable Action",
				ActivityImageRegistry.getImageDescriptor("READVARIABLEACTION"));
		PaletteStack completeActionsStack = new PaletteStack("Accept Event Action", "Accept Event Action", ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION"));
		// =================================== BasicActions ====================================
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(), "default");
		defaultPaletteEntry = new ModelerCreationToolEntry("Call Behavior Action", "Call Behavior Action", factory,
				ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE"));
		basicActionsStack.add(defaultPaletteEntry);
		basicActionsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallOperationAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Call Operation Action", "Call Operation Action", factory, ActivityImageRegistry
				.getImageDescriptor("CALLOPERATIONACTION"), ActivityImageRegistry.getImageDescriptor("CALLOPERATIONACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendObjectAction(), "default");
		// basicActionsStack.add(new ModelerCreationToolEntry("Send Object Action", "Send Object Action", factory,
		// ActivityImageRegistry.getImageDescriptor("SENDOBJECTACTION"),
		// ActivityImageRegistry.getImageDescriptor("SENDOBJECTACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendSignalAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Send Signal Action", "Send Signal Action", factory, ActivityImageRegistry
				.getImageDescriptor("SENDSIGNALACTION"), ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOpaqueAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Opaque Action", "Opaque Action", factory, ActivityImageRegistry.getImageDescriptor("OPAQUEACTION"),
				ActivityImageRegistry.getImageDescriptor("OPAQUEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCreateObjectAction(), "default");
		basicActionsStack.add(new ModelerCreationToolEntry("Create Object Action", "Create Object Action", factory, ActivityImageRegistry
				.getImageDescriptor("CREATEOBJECTACTION"), ActivityImageRegistry.getImageDescriptor("CREATEOBJECTACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getStartClassifierBehaviorAction(), "default");
		completeActionsStack.add(new ModelerCreationToolEntry("Start Classifier Behavior Action", "Start Classifier Behavior Action", factory, ActivityImageRegistry
				.getImageDescriptor("STARTCLASSIFIERBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("STARTCLASSIFIERBEHAVIORACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClearStructuralFeatureAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Clear Property Values Action", "Clear Property Values Action", factory, ActivityImageRegistry
				.getImageDescriptor("CLEARSTRUCTURALFEATUREACTION"), ActivityImageRegistry.getImageDescriptor("CLEARSTRUCTURALFEATUREACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReadStructuralFeatureAction(), "default");
		defaultPaletteEntry = new ModelerCreationToolEntry("Read Property Action", "Read Property Action", factory,
				ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION"), ActivityImageRegistry.getImageDescriptor("READSTRUCTURALFEATUREACTION_LARGE"));
		structuralFeatureActionsStack.add(defaultPaletteEntry);
		structuralFeatureActionsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAddStructuralFeatureValueAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Add Property Value Action", "Add Property Value Action", factory, ActivityImageRegistry
				.getImageDescriptor("ADDSTRUCTURALFEATUREVALUEACTION"), ActivityImageRegistry.getImageDescriptor("ADDSTRUCTURALFEATUREVALUEACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getRemoveStructuralFeatureValueAction(), "default");
		structuralFeatureActionsStack.add(new ModelerCreationToolEntry("Remove Property Value Action", "Remove Property Value Action", factory, ActivityImageRegistry
				.getImageDescriptor("REMOVESTRUCTURALFEATUREVALUEACTION"), ActivityImageRegistry.getImageDescriptor("REMOVESTRUCTURALFEATUREVALUEACTION_LARGE")));
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
		actionsDrawer.addAll(entries);
		if(actionsDrawer.getChildren().size() > 0){
			getRoot().add(actionsDrawer);
		}
	}
	private void createObjectDrawer(){
		objectDrawer = new PaletteDrawer("Object", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram.method");
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
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Ocl Input", "Ocl Input", vpFactory, ActivityImageRegistry.getImageDescriptor("INPUTPIN"), ActivityImageRegistry
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
		entries.add(new ModelerCreationToolEntry("Object Input", "Object Input ", ipFactory, ActivityImageRegistry.getImageDescriptor("INPUTPIN"), ActivityImageRegistry
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
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram.method");
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
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExceptionHandler(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Exception Handler", "Exception Handler", factory, ActivityImageRegistry
				.getImageDescriptor("EXCEPTIONHANDLER"), ActivityImageRegistry.getImageDescriptor("EXCEPTIONHANDLER_LARGE")));
		connectionsDrawer.addAll(entries);
		if(connectionsDrawer.getChildren().size() > 0){
			getRoot().add(connectionsDrawer);
		}
	}
}
