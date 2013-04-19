package org.opaeum.topcased.activitydiagram.bpm;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.activitydiagram.OpaeumActivityPaletteManager;
import org.opaeum.topcased.classdiagram.NakedElementCreationFactory;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class BusinessProcessPaletteManager extends OpaeumActivityPaletteManager{
	protected PaletteDrawer bpmDrawer;
	public BusinessProcessPaletteManager(ICreationUtils utils){
		super(utils);
	}

	protected void createCategories(){
		super.createCategories();
		createBpmDrawer();
	}

	protected void updateCategories(){
		super.updateCategories();
		getRoot().remove(bpmDrawer);
		createBpmDrawer();
	}

	protected void createBpmDrawer(){
		bpmDrawer = new PaletteDrawer("BPM", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.activitydiagram");
		PaletteStack eventsStack = new PaletteStack("Wait for Event", "Wait for Event", ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION"));
		GraphElementCreationFactory factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(), "default");
		ModelerCreationToolEntry defaultPaletteEntry = new ModelerCreationToolEntry("Wait for Event", "Wait for Event", factory,
				ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTEVENTACTION_LARGE"));
		eventsStack.add(defaultPaletteEntry);
		eventsStack.setActiveEntry(defaultPaletteEntry);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(), "default"){
			public EObject getNewModelObject(){
				AcceptEventAction element = (AcceptEventAction) super.getNewModelObject();
				element.createTrigger("Trigger1");
				return element;
			}
		};
		eventsStack.add(new ModelerCreationToolEntry("Accept Time Event Action", "Accept Time Event Action", factory, ActivityImageRegistry
				.getImageDescriptor("ACCEPTTIMEEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTTIMEEVENTACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptCallAction(), "default");
		eventsStack.add(new ModelerCreationToolEntry("Receive Call", "Receive Call", factory, ActivityImageRegistry
				.getImageDescriptor("ACCEPTCALLACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReplyAction(), "default");
		eventsStack.add(new ModelerCreationToolEntry("Reply Action", "Reply Action", factory, ActivityImageRegistry.getImageDescriptor("REPLYACTION"),
				ActivityImageRegistry.getImageDescriptor("REPLYACTION_LARGE")));
		CreationFactory acceptDeadline = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(),
				StereotypeNames.ACCEPT_DEADLINE_ACTION);
		eventsStack.add(new ModelerCreationToolEntry("On Deadline Reached", "On Deadline Reached", acceptDeadline, ActivityImageRegistry
				.getImageDescriptor("ACCEPTTIMEEVENTACTION"), ActivityImageRegistry.getImageDescriptor("ACCEPTTIMEEVENTACTION_LARGE")));
		CreationFactory acceptTaskEvent = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAcceptEventAction(),
				StereotypeNames.ACCEPT_TASK_EVENT_ACTION);
		eventsStack.add(new ModelerCreationToolEntry("On Task Event", "On Task Event", acceptTaskEvent, ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION"),
				ActivityImageRegistry.getImageDescriptor("ACCEPTCALLACTION_LARGE")));
		
		entries.add(eventsStack);
		CreationFactory esctfactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOpaqueAction(), StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		entries.add(new ModelerCreationToolEntry("Simple Task", "Simple Task", esctfactory, ActivityImageRegistry.getImageDescriptor("OPAQUEACTION"),
				ActivityImageRegistry.getImageDescriptor("OPAQUEACTION_LARGE")));
		CreationFactory escftfactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(),
				StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		entries.add(new ModelerCreationToolEntry("Screen Flow Task", "Screen Flow Task", escftfactory, ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION"),
				ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE")));
		CreationFactory callBusinessProcess = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCallBehaviorAction(),
				StereotypeNames.CALL_BUSINES_PROCESS_ACTION);
		entries.add(new ModelerCreationToolEntry("Call Business Process", "Call Business Process", callBusinessProcess, ActivityImageRegistry
				.getImageDescriptor("CALLBEHAVIORACTION"), ActivityImageRegistry.getImageDescriptor("CALLBEHAVIORACTION_LARGE")));
		CreationFactory sendNotification = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSendSignalAction(),
				StereotypeNames.SEND_NOTIFICATION_ACTION);
		entries.add(new ModelerCreationToolEntry("Send Notification", "Send Notification", sendNotification,
				ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION"), ActivityImageRegistry.getImageDescriptor("SENDSIGNALACTION_LARGE")));
		bpmDrawer.addAll(entries);
		if(bpmDrawer.getChildren().size() > 0){
			getRoot().add(bpmDrawer);
		}
	}
}
