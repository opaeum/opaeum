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
import org.topcased.modeler.uml.statemachinediagram.STMSimpleObjectConstants;
import org.topcased.modeler.utils.CustomPaletteArrayList;

/**
 * Generated Palette Manager <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ScreenFlowPaletteManager extends ModelerPaletteManager{
	// declare all the palette categories of the diagram
	private PaletteDrawer objectsDrawer;
	private PaletteDrawer pseudostatesDrawer;
	private PaletteDrawer connectionsDrawer;
	private PaletteDrawer commentDrawer;
	private ICreationUtils creationUtils;
	/**
	 * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param utils
	 *            the creation utils for the tools of the palette
	 * @generated
	 */
	public ScreenFlowPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	/**
	 * Creates the main categories of the palette <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createCategories(){
		createObjectsDrawer();
		createPseudostatesDrawer();
		createConnectionsDrawer();
		createCommentDrawer();
	}
	/**
	 * Updates the main categories of the palette <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated
		// categories
		getRoot().remove(objectsDrawer);
		createObjectsDrawer();
		getRoot().remove(pseudostatesDrawer);
		createPseudostatesDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
		getRoot().remove(commentDrawer);
		createCommentDrawer();
	}
	private void createObjectsDrawer(){
		objectsDrawer = new PaletteDrawer("Objects", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.nakeduml.topcased.statemachinediagram.statemachinediagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getRegion(), "default");
		entries.add(new ModelerCreationToolEntry("Region", "Region", factory, STMImageRegistry.getImageDescriptor("REGION"), STMImageRegistry
				.getImageDescriptor("REGION_LARGE")));
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
		entries.add(new ModelerCreationToolEntry("Submachine State", "Submachine State", factory, STMImageRegistry.getImageDescriptor("SUBMACHINESTATE"),
				STMImageRegistry.getImageDescriptor("SUBMACHINESTATE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConnectionPointReference(), "default");
		entries.add(new ModelerCreationToolEntry("ConnectionPointReference", "ConnectionPointReference", factory, STMImageRegistry
				.getImageDescriptor("CONNECTIONPOINTREFERENCE"), STMImageRegistry.getImageDescriptor("CONNECTIONPOINTREFERENCE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getFinalState(), "default");
		entries.add(new ModelerCreationToolEntry("FinalState", "FinalState", factory, STMImageRegistry.getImageDescriptor("FINALSTATE"), STMImageRegistry
				.getImageDescriptor("FINALSTATE_LARGE")));
		objectsDrawer.addAll(entries);
		if(objectsDrawer.getChildren().size() > 0){
			getRoot().add(objectsDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
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
				element.setKind(PseudostateKind.JOIN_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Join", "Join", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEJOIN"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEJOIN_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.FORK_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Fork", "Fork", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEFORK"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEFORK_LARGE")));
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
				element.setKind(PseudostateKind.ENTRY_POINT_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Entry Point", "Entry Point", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEENTRYPOINT"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEENTRYPOINT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPseudostate(), "default"){
			public EObject getNewModelObject(){
				Pseudostate element = (Pseudostate) super.getNewModelObject();
				element.setKind(PseudostateKind.EXIT_POINT_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Exit Point", "Exit Point", factory, STMImageRegistry.getImageDescriptor("PSEUDOSTATEEXITPOINT"), STMImageRegistry
				.getImageDescriptor("PSEUDOSTATEEXITPOINT_LARGE")));
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
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
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
	/**
	 * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void createCommentDrawer(){
		commentDrawer = new PaletteDrawer("Comment", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.nakeduml.topcased.statemachinediagram.statemachinediagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComment(), "default");
		entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory, STMImageRegistry.getImageDescriptor("COMMENT"), STMImageRegistry
				.getImageDescriptor("COMMENT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, STMSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Comment Link", "Comment Link", factory, STMImageRegistry.getImageDescriptor("COMMENTLINK"), STMImageRegistry
				.getImageDescriptor("COMMENTLINK_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConstraint(), "default");
		entries.add(new ModelerCreationToolEntry("Constraint", "Constraint", factory, STMImageRegistry.getImageDescriptor("CONSTRAINT"), STMImageRegistry
				.getImageDescriptor("CONSTRAINT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, STMSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Constraint Link", "Constraint Link", factory, STMImageRegistry.getImageDescriptor("CONSTRAINTLINK"),
				STMImageRegistry.getImageDescriptor("CONSTRAINTLINK_LARGE")));
		commentDrawer.addAll(entries);
		if(commentDrawer.getChildren().size() > 0){
			getRoot().add(commentDrawer);
		}
	}
}
