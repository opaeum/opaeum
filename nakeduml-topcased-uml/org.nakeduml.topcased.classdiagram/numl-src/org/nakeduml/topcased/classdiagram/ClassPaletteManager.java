package org.nakeduml.topcased.classdiagram;

import java.util.List;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.classdiagram.ClassImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class ClassPaletteManager extends ModelerPaletteManager{
	// declare all the palette categories of the diagram
	private PaletteDrawer classesDrawer;
	private PaletteDrawer featuresDrawer;
	private PaletteDrawer connectionsDrawer;
	private PaletteDrawer organizationDrawer;
	// private PaletteDrawer commentDrawer;
	private ICreationUtils creationUtils;
	public ClassPaletteManager(ICreationUtils utils){
		super();
		this.creationUtils = utils;
	}
	@Override
	protected void createCategories(){
		createClassesDrawer();
		createFeaturesDrawer();
		createConnectionsDrawer();
		createOrganizationDrawer();
		// createCommentDrawer();
	}
	@Override
	protected void updateCategories(){
		// deletion of the existing categories and creation of the updated
		// categories
		getRoot().remove(classesDrawer);
		createClassesDrawer();
		getRoot().remove(featuresDrawer);
		createFeaturesDrawer();
		getRoot().remove(connectionsDrawer);
		createConnectionsDrawer();
		getRoot().remove(organizationDrawer);
		createOrganizationDrawer();
		// getRoot().remove(commentDrawer);
		// createCommentDrawer();
	}
	private void createFeaturesDrawer(){
		featuresDrawer = new PaletteDrawer("Features", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.classdiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getOperation(), "default");
		entries.add(new ModelerCreationToolEntry("Operation", "Operation", factory, ClassImageRegistry.getImageDescriptor("OPERATION"), ClassImageRegistry
				.getImageDescriptor("OPERATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getProperty(), "default");
		entries.add(new ModelerCreationToolEntry("Property", "Property", factory, ClassImageRegistry.getImageDescriptor("PROPERTY"), ClassImageRegistry
				.getImageDescriptor("PROPERTY_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getEnumerationLiteral(), "default");
		entries.add(new ModelerCreationToolEntry("Enumeration Literal", "Enumeration Literal", factory, ClassImageRegistry.getImageDescriptor("ENUMERATIONLITERAL"),
				ClassImageRegistry.getImageDescriptor("ENUMERATIONLITERAL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getReception(), "default");
		entries.add(new ModelerCreationToolEntry("Reception", "Reception", factory, ClassImageRegistry.getImageDescriptor("RECEPTION"), ClassImageRegistry
				.getImageDescriptor("RECEPTION_LARGE")));
		featuresDrawer.addAll(entries);
		if(featuresDrawer.getChildren().size() > 0){
			getRoot().add(featuresDrawer);
		}

		
	}
	private void createOrganizationDrawer(){
		organizationDrawer = new PaletteDrawer("BPM", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.classdiagram");
		CreationFactory businessServiceFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterface(), StereotypeNames.BUSINESS_SERVICE);
		entries.add(new ModelerCreationToolEntry("BusinessService", "BusinessService", businessServiceFactory, ClassImageRegistry.getImageDescriptor("INTERFACE"),
				ClassImageRegistry.getImageDescriptor("INTERFACE")));
		CreationFactory businessRoleFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClass_(), StereotypeNames.BUSINESS_ROLE);
		entries.add(new ModelerCreationToolEntry("BusinessRole", "BusinessRole", businessRoleFactory, ClassImageRegistry.getImageDescriptor("CLASS"), ClassImageRegistry
				.getImageDescriptor("INTERFACE")));
		CreationFactory businessComponentFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComponent(), StereotypeNames.BUSINESS_COMPONENT);
		entries.add(new ModelerCreationToolEntry("BusinessComponent", "BusinessComponent", businessComponentFactory, ClassImageRegistry.getImageDescriptor("CLASS"),
				ClassImageRegistry.getImageDescriptor("CLASS")));
		CreationFactory notificationFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSignal(), StereotypeNames.NOTIFICATION);
		entries.add(new ModelerCreationToolEntry("Notification", "Notification", notificationFactory , ClassImageRegistry.getImageDescriptor("SIGNAL"),
				ClassImageRegistry.getImageDescriptor("SIGNAL")));
		organizationDrawer.addAll(entries);
		if(organizationDrawer.getChildren().size() > 0){
			getRoot().add(organizationDrawer);
		}
	}
	private void createClassesDrawer(){
		classesDrawer = new PaletteDrawer("Classes", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.classdiagram");
		CreationFactory factory;
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackage(), "default");
		entries.add(new ModelerCreationToolEntry("Package", "Package", factory, ClassImageRegistry.getImageDescriptor("PACKAGE"), ClassImageRegistry
				.getImageDescriptor("PACKAGE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClass_(), "default");
		entries.add(new ModelerCreationToolEntry("Class", "Class", factory, ClassImageRegistry.getImageDescriptor("CLASS"), ClassImageRegistry
				.getImageDescriptor("CLASS_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDataType(), "default");
		entries.add(new ModelerCreationToolEntry("Data Type", "Data Type", factory, ClassImageRegistry.getImageDescriptor("DATATYPE"), ClassImageRegistry
				.getImageDescriptor("DATATYPE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterface(), "default");
		entries.add(new ModelerCreationToolEntry("Interface", "Interface", factory, ClassImageRegistry.getImageDescriptor("INTERFACE"), ClassImageRegistry
				.getImageDescriptor("INTERFACE_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInstanceSpecification(), "default");
		// entries.add(new ModelerCreationToolEntry("Instance Specification", "Instance Specification", factory, ClassImageRegistry
		// .getImageDescriptor("INSTANCESPECIFICATION"), ClassImageRegistry.getImageDescriptor("INSTANCESPECIFICATION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSlot(), "default");
		// entries.add(new ModelerCreationToolEntry("Slot", "Slot", factory, ClassImageRegistry.getImageDescriptor("SLOT"),
		// ClassImageRegistry
		// .getImageDescriptor("SLOT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getEnumeration(), "default");
		entries.add(new ModelerCreationToolEntry("Enumeration", "Enumeration", factory, ClassImageRegistry.getImageDescriptor("ENUMERATION"), ClassImageRegistry
				.getImageDescriptor("ENUMERATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComponent(), "default");
		entries.add(new ModelerCreationToolEntry("Component", "Component", factory, ClassImageRegistry.getImageDescriptor("ENUMERATION"), ClassImageRegistry
				.getImageDescriptor("ENUMERATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPrimitiveType(), "default");
		entries.add(new ModelerCreationToolEntry("Primitive Type", "Primitive Type", factory, ClassImageRegistry.getImageDescriptor("PRIMITIVETYPE"), ClassImageRegistry
				.getImageDescriptor("PRIMITIVETYPE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getSignal(), "default");
		entries.add(new ModelerCreationToolEntry("Signal", "Signal", factory, ClassImageRegistry.getImageDescriptor("SIGNAL"), ClassImageRegistry
				.getImageDescriptor("SIGNAL_LARGE")));
		CreationFactory notificationFactory = new NakedElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterface(), StereotypeNames.HELPER);
		entries.add(new ModelerCreationToolEntry("Helper", "Helper", notificationFactory , ClassImageRegistry.getImageDescriptor("INTERFACE"),
				ClassImageRegistry.getImageDescriptor("INTERFACE")));
		classesDrawer.addAll(entries);
		if(classesDrawer.getChildren().size() > 0){
			getRoot().add(classesDrawer);
		}
	}
	private void createConnectionsDrawer(){
		connectionsDrawer = new PaletteDrawer("Connections", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.classdiagram");
		CreationFactory factory;
		PaletteStack associationStack = new PaletteStack("Association", "Association", ClassImageRegistry.getImageDescriptor("ASSOCIATION"));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation(), "default"){
			@Override
			public EObject getNewModelObject(){
				Association element = (Association) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(1);
				property1.setUpper(1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(1);
				property2.setUpper(1);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				return element;
			}
		};
		associationStack.add(new ModelerConnectionCreationToolEntry("Association", "Association", factory, ClassImageRegistry.getImageDescriptor("ASSOCIATION"),
				ClassImageRegistry.getImageDescriptor("ASSOCIATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation(), "default"){
			@Override
			public EObject getNewModelObject(){
				Association element = (Association) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(1);
				property1.setUpper(1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(1);
				property2.setUpper(1);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				// The isNavigable property should be set after the property is attached to the Association.
				property2.setIsNavigable(true);
				return element;
			}
		};
		associationStack.add(new ModelerConnectionCreationToolEntry("Association (UniDir.)", "Association (UniDir.)", factory, ClassImageRegistry
				.getImageDescriptor("ASSOCIATION_UNIDIRECTIONAL"), ClassImageRegistry.getImageDescriptor("ASSOCIATION_UNIDIRECTIONAL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation(), "default"){
			@Override
			public EObject getNewModelObject(){
				Association element = (Association) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(1);
				property1.setUpper(1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(1);
				property2.setUpper(1);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				// The isNavigable property of two association ends should be set after the property is attached to the Association.
				property1.setIsNavigable(true);
				property2.setIsNavigable(true);
				return element;
			}
		};
		associationStack.add(new ModelerConnectionCreationToolEntry("Association (DoubleDir.)", "Association (DoubleDir.)", factory, ClassImageRegistry
				.getImageDescriptor("ASSOCIATION_DOUBLEDIRECTIONAL"), ClassImageRegistry.getImageDescriptor("ASSOCIATION_DOUBLEDIRECTIONAL_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation(), "default"){
			@Override
			public EObject getNewModelObject(){
				Association element = (Association) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(1);
				property1.setUpper(1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(1);
				property2.setUpper(1);
				property2.setAggregation(AggregationKind.COMPOSITE_LITERAL);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				return element;
			}
		};
		associationStack.add(new ModelerConnectionCreationToolEntry("Association (Composite)", "Association (Composite)", factory, ClassImageRegistry
				.getImageDescriptor("ASSOCIATION_COMPOSITE"), ClassImageRegistry.getImageDescriptor("ASSOCIATION_COMPOSITE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation(), "default"){
			@Override
			public EObject getNewModelObject(){
				Association element = (Association) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(1);
				property1.setUpper(1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(1);
				property2.setUpper(1);
				property2.setAggregation(AggregationKind.SHARED_LITERAL);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				return element;
			}
		};
		associationStack.add(new ModelerConnectionCreationToolEntry("Association (Shared)", "Association (Shared)", factory, ClassImageRegistry
				.getImageDescriptor("ASSOCIATION_SHARED"), ClassImageRegistry.getImageDescriptor("ASSOCIATION_SHARED_LARGE")));
		entries.add(associationStack);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociationClass(), "default"){
			@Override
			public EObject getNewModelObject(){
				AssociationClass element = (AssociationClass) super.getNewModelObject();
				Property property1 = UMLFactory.eINSTANCE.createProperty();
				property1.setName("source");
				property1.setLower(0);
				property1.setUpper(-1);
				Property property2 = UMLFactory.eINSTANCE.createProperty();
				property2.setName("target");
				property2.setLower(0);
				property2.setUpper(-1);
				element.getOwnedEnds().add(property1);
				element.getOwnedEnds().add(property2);
				return element;
			}
		};
		entries.add(new ModelerConnectionCreationToolEntry("Association Class", "Association Class", factory, ClassImageRegistry.getImageDescriptor("ASSOCIATIONCLASS"),
				ClassImageRegistry.getImageDescriptor("ASSOCIATIONCLASS_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInstanceSpecification(), "link");
		// entries.add(new ModelerConnectionCreationToolEntry("Instance Specification link", "Instance Specification link", factory,
		// ClassImageRegistry
		// .getImageDescriptor("ASSOCIATION"), ClassImageRegistry.getImageDescriptor("ASSOCIATION")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getGeneralization(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Generalization", "Generalization", factory, ClassImageRegistry.getImageDescriptor("GENERALIZATION"),
				ClassImageRegistry.getImageDescriptor("GENERALIZATION_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInterfaceRealization(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Interface Realization", "Interface Realization", factory, ClassImageRegistry
				.getImageDescriptor("INTERFACEREALIZATION"), ClassImageRegistry.getImageDescriptor("INTERFACEREALIZATION_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getTemplateBinding(), "default");
		// entries.add(new ModelerConnectionCreationToolEntry("Template Binding", "Template Binding", factory,
		// ClassImageRegistry.getImageDescriptor("TEMPLATEBINDING"),
		// ClassImageRegistry.getImageDescriptor("TEMPLATEBINDING_LARGE")));
		PaletteStack dependencyStack = new PaletteStack("Dependency", "Dependency", ClassImageRegistry.getImageDescriptor("DEPENDENCY"));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDependency(), "default");
		dependencyStack.add(new ModelerConnectionCreationToolEntry("Dependency", "Dependency", factory, ClassImageRegistry.getImageDescriptor("DEPENDENCY"),
				ClassImageRegistry.getImageDescriptor("DEPENDENCY_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getUsage(), "default");
		dependencyStack.add(new ModelerConnectionCreationToolEntry("Usage", "Usage", factory, ClassImageRegistry.getImageDescriptor("USAGE"), ClassImageRegistry
				.getImageDescriptor("USAGE_LARGE")));
		entries.add(dependencyStack);
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackageImport(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Package Import", "Package Import", factory, ClassImageRegistry.getImageDescriptor("PACKAGEIMPORT"),
				ClassImageRegistry.getImageDescriptor("PACKAGEIMPORT_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackageMerge(), "default");
		entries.add(new ModelerConnectionCreationToolEntry("Package Merge", "Package Merge", factory, ClassImageRegistry.getImageDescriptor("PACKAGEMERGE"),
				ClassImageRegistry.getImageDescriptor("PACKAGEMERGE_LARGE")));
		connectionsDrawer.addAll(entries);
		if(connectionsDrawer.getChildren().size() > 0){
			getRoot().add(connectionsDrawer);
		}
	}
	private void createCommentDrawer(){
		// commentDrawer = new PaletteDrawer("Comment", null);
		// List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.classdiagram");
		// CreationFactory factory;
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComment(), "default");
		// entries.add(new ModelerCreationToolEntry("Comment", "Comment", factory, ClassImageRegistry.getImageDescriptor("COMMENT"),
		// ClassImageRegistry
		// .getImageDescriptor("COMMENT_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, ClassSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK, "default", false);
		// entries.add(new ModelerConnectionCreationToolEntry("Comment link", "Comment link", factory,
		// ClassImageRegistry.getImageDescriptor("COMMENTLINK"),
		// ClassImageRegistry.getImageDescriptor("COMMENTLINK_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConstraint(), "default");
		// entries.add(new ModelerCreationToolEntry("Constraint", "Constraint", factory,
		// ClassImageRegistry.getImageDescriptor("CONSTRAINT"), ClassImageRegistry
		// .getImageDescriptor("CONSTRAINT_LARGE")));
		// factory = new GraphElementCreationFactory(creationUtils, AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK, "default",
		// false);
		// entries.add(new ModelerConnectionCreationToolEntry("Constraint Link", "Constraint Link", factory,
		// ClassImageRegistry.getImageDescriptor("CONSTRAINTLINK"),
		// ClassImageRegistry.getImageDescriptor("CONSTRAINTLINK_LARGE")));
		// commentDrawer.addAll(entries);
		// if(commentDrawer.getChildren().size() > 0){
		// getRoot().add(commentDrawer);
		// }
	}
}
