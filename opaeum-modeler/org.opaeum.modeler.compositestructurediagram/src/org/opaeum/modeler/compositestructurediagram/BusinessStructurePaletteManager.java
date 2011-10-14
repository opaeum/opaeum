package org.opaeum.modeler.compositestructurediagram;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class BusinessStructurePaletteManager extends ModelerPaletteManager{
	private PaletteDrawer objectsDrawer;
	private PaletteDrawer connectionsDrawer;
	private PaletteDrawer commentDrawer;
	private ICreationUtils creationUtils;
	public BusinessStructurePaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	protected void createCategories(){
		createObjectsDrawer();
		createConnectionsDrawer();
		createCommentDrawer();
	}
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated categories
		getRoot().remove(objectsDrawer);
		createObjectsDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
		getRoot().remove(commentDrawer);
		createCommentDrawer();
	}
	private void createObjectsDrawer(){
		objectsDrawer = new PaletteDrawer("Objects", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.compositestructuresdiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getProperty(), "default"){
			public EObject getNewModelObject(){
				Property element = (Property) super.getNewModelObject();
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.BUSINESS_ROLE_CONTAINMENT, "");
				element.setAggregation(AggregationKind.COMPOSITE_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Business Role Containment", "Business Role Containment", factory, CompositeStructuresImageRegistry.getImageDescriptor("PART"),
				CompositeStructuresImageRegistry.getImageDescriptor("PART_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getProperty(), "default"){
			public EObject getNewModelObject(){
				Property element = (Property) super.getNewModelObject();
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.PARTICIPANT_REFERENCE, "");
				element.setAggregation(AggregationKind.NONE_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Participant reference", "Participant reference", factory, CompositeStructuresImageRegistry.getImageDescriptor("ROLE"),
				CompositeStructuresImageRegistry.getImageDescriptor("ROLE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterface(), "default"){

		};
		entries.add(new ModelerCreationToolEntry("Interface", "Interface", factory, CompositeStructuresImageRegistry.getImageDescriptor("INTERFACE"),
				CompositeStructuresImageRegistry.getImageDescriptor("INTERFACE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPort(), "default"){
			public EObject getNewModelObject(){
				Port element = (Port) super.getNewModelObject();
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.BUSINESS_GATEWAY, "");
				element.setAggregation(AggregationKind.COMPOSITE_LITERAL);
				return element;
			}
		};
		entries.add(new ModelerCreationToolEntry("Business Gateway", "Business Gateway", factory, CompositeStructuresImageRegistry.getImageDescriptor("PORT"), CompositeStructuresImageRegistry
				.getImageDescriptor("PORT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getCollaborationUse(), "default");
		entries.add(new ModelerCreationToolEntry("CollaborationUse", "CollaborationUse", factory,
				CompositeStructuresImageRegistry.getImageDescriptor("COLLABORATIONUSE"), CompositeStructuresImageRegistry.getImageDescriptor("COLLABORATIONUSE_LARGE")));
		objectsDrawer.addAll(entries);
		if(objectsDrawer.getChildren().size() > 0){
			getRoot().add(objectsDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 * 
	 * @generated
	 */
	private void createConnectionsDrawer(){
		connectionsDrawer = new PaletteDrawer("Connections", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.compositestructuresdiagram");
		GraphElementCreationFactory delegationFactory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConnector(), "default"){
			public EObject getNewModelObject(){
				Connector element = (Connector) super.getNewModelObject();
				ConnectorEnd sourceEnd = UMLFactory.eINSTANCE.createConnectorEnd();
				sourceEnd.setUpper(1);
				sourceEnd.setLower(1);
				ConnectorEnd targetEnd = UMLFactory.eINSTANCE.createConnectorEnd();
				targetEnd.setUpper(1);
				targetEnd.setLower(1);
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.DELEGATION, "");
				element.setKind(ConnectorKind.DELEGATION_LITERAL);
				element.getEnds().add(sourceEnd);
				element.getEnds().add(targetEnd);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Delegation", "Delegation", delegationFactory, CompositeStructuresImageRegistry.getImageDescriptor("CONNECTOR"),
				CompositeStructuresImageRegistry.getImageDescriptor("CONNECTOR_LARGE")));
		GraphElementCreationFactory channelFactory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConnector(), "default"){
			public EObject getNewModelObject(){
				// Creates Connector and ConnectorEnd
				Connector element = (Connector) super.getNewModelObject();
				ConnectorEnd sourceEnd = UMLFactory.eINSTANCE.createConnectorEnd();
				sourceEnd.setUpper(1);
				sourceEnd.setLower(1);
				ConnectorEnd targetEnd = UMLFactory.eINSTANCE.createConnectorEnd();
				targetEnd.setUpper(1);
				targetEnd.setLower(1);
				// Sets connection kind
				/** NOTE : the connector kind should be ASSEMBLY_LITERAL otherwise it generates a warning during validation step */
				element.setKind(ConnectorKind.ASSEMBLY_LITERAL);
				StereotypesHelper.getNumlAnnotation(element).getDetails().put(StereotypeNames.BUSINESS_CHANNEL, "");
				// Adds ConnectorEnds objects
				element.getEnds().add(sourceEnd);
				element.getEnds().add(targetEnd);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Business Channel", "Business Channel", channelFactory, CompositeStructuresImageRegistry.getImageDescriptor("CONNECTOR"),
				CompositeStructuresImageRegistry.getImageDescriptor("CONNECTOR_LARGE")));
		GraphElementCreationFactory factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterfaceRealization(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("InterfaceRealization", "InterfaceRealization", factory, CompositeStructuresImageRegistry
				.getImageDescriptor("INTERFACEREALIZATION"), CompositeStructuresImageRegistry.getImageDescriptor("INTERFACEREALIZATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getUsage(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Usage", "Usage", factory, CompositeStructuresImageRegistry.getImageDescriptor("USAGE"),
				CompositeStructuresImageRegistry.getImageDescriptor("USAGE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDependency(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Role Binding", "Role Binding", factory, CompositeStructuresImageRegistry.getImageDescriptor("DEPENDENCY"),
				CompositeStructuresImageRegistry.getImageDescriptor("DEPENDENCY_LARGE")));
		connectionsDrawer.addAll(entries);
		if(connectionsDrawer.getChildren().size() > 0){
			getRoot().add(connectionsDrawer);
		}
	}
	/**
	 * Creates the Palette container containing all the Palette entries for each figure.
	 * 
	 * @generated
	 */
	private void createCommentDrawer(){
		commentDrawer = new PaletteDrawer("Comment", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.compositestructuresdiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComment(), "default");
		entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory, CompositeStructuresImageRegistry.getImageDescriptor("COMMENT"),
				CompositeStructuresImageRegistry.getImageDescriptor("COMMENT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, AllSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("CommentLink", "CommentLink", factory, CompositeStructuresImageRegistry.getImageDescriptor("COMMENTLINK"),
				CompositeStructuresImageRegistry.getImageDescriptor("COMMENTLINK_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConstraint(), "default");
		entries.add(new ModelerCreationToolEntry("Constraint", "Constraint", factory, CompositeStructuresImageRegistry.getImageDescriptor("CONSTRAINT"),
				CompositeStructuresImageRegistry.getImageDescriptor("CONSTRAINT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Constraint Link", "Constraint Link", factory, CompositeStructuresImageRegistry
				.getImageDescriptor("CONSTRAINTLINK"), CompositeStructuresImageRegistry.getImageDescriptor("CONSTRAINTLINK_LARGE")));
		commentDrawer.addAll(entries);
		if(commentDrawer.getChildren().size() > 0){
			getRoot().add(commentDrawer);
		}
	}
}
