/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;

/**
 * Generated Palette Manager
 *
 * @generated
 */
public class BPMN2PaletteManager extends ModelerPaletteManager{
	// declare all the palette categories of the diagram
	/**
	 * @generated
	 */
	private PaletteDrawer tasksDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer flowsDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer eventsDrawer;
	/**
	 * @generated
	 */
	private PaletteDrawer eventdefinitionsDrawer;
	/**
	 * @generated
	 */
	private ICreationUtils creationUtils;
	/**
	 * The Constructor
	 *
	 * @param utils the creation utils for the tools of the palette 
	 * @generated
	 */
	public BPMN2PaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	/**
	 * Creates the main categories of the palette
	 *
	 * @generated
	 */
	protected void createCategories(){
		createTasksDrawer();
		createFlowsDrawer();
		createEventsDrawer();
		createEventDefinitionsDrawer();
	}
	/**
	 * Updates the main categories of the palette
	 *
	 * @generated
	 */
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated categories
		getRoot().remove(tasksDrawer);
		createTasksDrawer();
		getRoot().remove(flowsDrawer);
		createFlowsDrawer();
		getRoot().remove(eventsDrawer);
		createEventsDrawer();
		getRoot().remove(eventdefinitionsDrawer);
		createEventDefinitionsDrawer();
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createTasksDrawer(){
		tasksDrawer = new PaletteDrawer("Tasks", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getUserTask(), "default");
		entries.add(new ModelerCreationToolEntry("UserTask", "UserTask", factory, BPMN2ImageRegistry.getImageDescriptor("USERTASK"), BPMN2ImageRegistry
				.getImageDescriptor("USERTASK_LARGE")));
		tasksDrawer.addAll(entries);
		getRoot().add(tasksDrawer);
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createFlowsDrawer(){
		flowsDrawer = new PaletteDrawer("Flows", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getSequenceFlow(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("SequenceFlow", "SequenceFlow", factory, BPMN2ImageRegistry.getImageDescriptor("SEQUENCEFLOW"),
				BPMN2ImageRegistry.getImageDescriptor("SEQUENCEFLOW_LARGE")));
		flowsDrawer.addAll(entries);
		getRoot().add(flowsDrawer);
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createEventsDrawer(){
		eventsDrawer = new PaletteDrawer("Events", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getBoundaryEvent(), "default");
		entries.add(new ModelerCreationToolEntry("BoundaryEvent", "BoundaryEvent", factory, BPMN2ImageRegistry.getImageDescriptor("BOUNDARYEVENT"), BPMN2ImageRegistry
				.getImageDescriptor("BOUNDARYEVENT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getIntermediateCatchEvent(), "default");
		entries.add(new ModelerCreationToolEntry("IntermediateCatchEvent", "IntermediateCatchEvent", factory, BPMN2ImageRegistry
				.getImageDescriptor("INTERMEDIATECATCHEVENT"), BPMN2ImageRegistry.getImageDescriptor("INTERMEDIATECATCHEVENT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getStartEvent(), "default");
		entries.add(new ModelerCreationToolEntry("Start Event", "Start Event", factory, BPMN2ImageRegistry.getImageDescriptor("STARTEVENT"), BPMN2ImageRegistry
				.getImageDescriptor("STARTEVENT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getEndEvent(), "default");
		entries.add(new ModelerCreationToolEntry("End Event", "End Event", factory, BPMN2ImageRegistry.getImageDescriptor("ENDEVENT"), BPMN2ImageRegistry
				.getImageDescriptor("ENDEVENT_LARGE")));
		eventsDrawer.addAll(entries);
		getRoot().add(eventsDrawer);
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 *
	 * @generated
	 */
	private void createEventDefinitionsDrawer(){
		eventdefinitionsDrawer = new PaletteDrawer("EventDefinitions", null);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getMessageEventDefinition(), "default");
		entries.add(new ModelerCreationToolEntry("Message Event", "Message Event", factory, BPMN2ImageRegistry.getImageDescriptor("MESSAGEEVENTDEFINITION"),
				BPMN2ImageRegistry.getImageDescriptor("MESSAGEEVENTDEFINITION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, Bpmn2Package.eINSTANCE.getSignalEventDefinition(), "default");
		entries.add(new ModelerCreationToolEntry("SignalEvent", "SignalEvent", factory, BPMN2ImageRegistry.getImageDescriptor("SIGNALEVENTDEFINITION"),
				BPMN2ImageRegistry.getImageDescriptor("SIGNALEVENTDEFINITION_LARGE")));
		eventdefinitionsDrawer.addAll(entries);
		getRoot().add(eventdefinitionsDrawer);
	}
}
