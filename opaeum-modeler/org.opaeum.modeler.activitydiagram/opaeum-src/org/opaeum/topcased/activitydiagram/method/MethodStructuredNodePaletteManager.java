package org.opaeum.topcased.activitydiagram.method;

import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.activitydiagram.OpaeumActivityPaletteManager;
import org.opaeum.topcased.activitydiagram.OpaeumStructuredNodePalletteManager;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class MethodStructuredNodePaletteManager extends OpaeumStructuredNodePalletteManager{
	public MethodStructuredNodePaletteManager(ICreationUtils utils){
		super(utils);
	}
	protected void createCategories(){
		createClauseDrawer();
		createControlDrawer();
		createActionsDrawer();
		createObjectDrawer();
		createConnectionsDrawer();
		
	}
	protected void updateCategories(){
		getRoot().remove(clauseDrawer);
		createClauseDrawer();
		getRoot().remove(controlDrawer);
		createControlDrawer();
		getRoot().remove(actionsDrawer);
		createActionsDrawer();
		getRoot().remove(objectDrawer);
		createObjectDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
	}
	protected void createControlDrawer(){
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
}
