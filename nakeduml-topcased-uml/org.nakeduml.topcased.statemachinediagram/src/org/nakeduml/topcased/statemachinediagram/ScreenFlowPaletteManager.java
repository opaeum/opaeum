package org.nakeduml.topcased.statemachinediagram;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.statemachinediagram.STMImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;


public class ScreenFlowPaletteManager extends ModelerPaletteManager{
	private PaletteDrawer objectsDrawer;
	private PaletteDrawer pseudostatesDrawer;
	private PaletteDrawer connectionsDrawer;
	private ICreationUtils creationUtils;
	public ScreenFlowPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	protected void createCategories(){
		createObjectsDrawer();
		createPseudostatesDrawer();
		createConnectionsDrawer();
	}
	protected void updateCategories(){
		getRoot().remove(objectsDrawer);
		createObjectsDrawer();
		getRoot().remove(pseudostatesDrawer);
		createPseudostatesDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
	}
	private void createObjectsDrawer(){
		objectsDrawer = new PaletteDrawer("Objects", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.nakeduml.topcased.statemachinediagram.statemachinediagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getState(), "default");
		entries.add(new ModelerCreationToolEntry("Screen", "Screen", factory, STMImageRegistry.getImageDescriptor("STATE"), STMImageRegistry
				.getImageDescriptor("STATE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getState(), "default"){
			public EObject getNewModelObject(){
				State element = (State) super.getNewModelObject();
				element.createRegion("Region1");
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Composite State", "Composite State", factory, STMImageRegistry.getImageDescriptor("COMPOSITESTATE"), STMImageRegistry
				.getImageDescriptor("COMPOSITESTATE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getState(), "default"){
			public EObject getNewModelObject(){
				State element = (State) super.getNewModelObject();
				// Set a temporary StateMachine so that the isSubmachineState
				// returns true.
				element.setSubmachine(UMLFactory.eINSTANCE.createStateMachine());
				return element;
			}
		};
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getFinalState(), "default");
		entries.add(new ModelerCreationToolEntry("FinalState", "FinalState", factory, STMImageRegistry.getImageDescriptor("FINALSTATE"), STMImageRegistry
				.getImageDescriptor("FINALSTATE_LARGE")));
		objectsDrawer.addAll(entries);
		if(objectsDrawer.getChildren().size() > 0){
			getRoot().add(objectsDrawer);
		}
	}
	private void createPseudostatesDrawer(){
		pseudostatesDrawer = new PaletteDrawer("Pseudostates", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.nakeduml.topcased.statemachinediagram.statemachinediagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.INITIAL_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Initial", "Initial", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEINITIAL"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEINITIAL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.DEEP_HISTORY_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Deep History", "Deep History", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEDEEPHISTORY"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEDEEPHISTORY_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.SHALLOW_HISTORY_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Shallow History", "Shallow History", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATESHALLOWHISTORY"),
				STMImageRegistry.getImageDescriptor("PSEUDOSTATESHALLOWHISTORY_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.JUNCTION_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Junction", "Junction", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEJUNCTION"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEJUNCTION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.CHOICE_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Choice", "Choice", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATECHOICE"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATECHOICE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.TERMINATE_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Terminate", "Terminate", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATETERMINATE"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATETERMINATE_LARGE")));
		pseudostatesDrawer.addAll(entries);
		if(pseudostatesDrawer.getChildren().size() > 0){
			getRoot().add(pseudostatesDrawer);
		}
	}
	private void createConnectionsDrawer(){
		connectionsDrawer = new PaletteDrawer("Connections", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.nakeduml.topcased.statemachinediagram.statemachinediagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getTransition(), "default"){
			public EObject getNewModelObject(){
				Transition element = (Transition) super.getNewModelObject();
				element.setKind(TransitionKind.EXTERNAL_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("External Transition", "External Transition", factory, STMImageRegistry
				.getImageDescriptor("TRANSITIONEXTERNAL"), STMImageRegistry.getImageDescriptor("TRANSITIONEXTERNAL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getTransition(), "default"){
			public EObject getNewModelObject(){
				Transition element = (Transition) super.getNewModelObject();
				element.setKind(TransitionKind.LOCAL_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Local Transition", "Local Transition", factory, STMImageRegistry.getImageDescriptor("TRANSITIONLOCAL"),
				STMImageRegistry.getImageDescriptor("TRANSITIONLOCAL_LARGE")));
		connectionsDrawer.addAll(entries);
		if(connectionsDrawer.getChildren().size() > 0){
			getRoot().add(connectionsDrawer);
		}
	}
}
