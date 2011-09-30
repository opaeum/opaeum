/*******************************************************************************
 * Copyright (c) 2005, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalev�e (Anyware Technologies) - initial API and
 * implementation
 * Maxime Nauleau (Atos Origin) - Fix #792
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.uml.UMLImageRegistry;
import org.topcased.modeler.utils.CustomPaletteArrayList;

/**
 * Generated Palette Manager <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCasePaletteManager extends ModelerPaletteManager
{
    // declare all the palette categories of the diagram
    private PaletteDrawer objectsDrawer;

    private PaletteDrawer connectionsDrawer;

    private PaletteDrawer commentDrawer;

    private ICreationUtils creationUtils;

    /**
     * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param utils the creation utils for the tools of the palette
     * @generated
     */
    public UseCasePaletteManager(ICreationUtils utils)
    {
        super();
        this.creationUtils = utils;
    }

    /**
     * Creates the main categories of the palette <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createCategories()
    {
        createObjectsDrawer();
        createConnectionsDrawer();
        createCommentDrawer();
    }

    /**
     * Updates the main categories of the palette <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void updateCategories()
    {
        // deletion of the existing categories and creation of the updated
        // categories

        getRoot().remove(objectsDrawer);
        createObjectsDrawer();

        getRoot().remove(connectionsDrawer);
        createConnectionsDrawer();

        getRoot().remove(commentDrawer);
        createCommentDrawer();
    }

    /**
     * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated NOT
     */
    private void createObjectsDrawer()
    {
        objectsDrawer = new PaletteDrawer("Objects", null);
        List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.usecasediagram");

        CreationFactory factory;
        String newObjTxt;

        ModelerCreationToolEntry objectTool;

        newObjTxt = "Package";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackage());
        objectTool = new ModelerCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("PACKAGE"), UMLImageRegistry.getImageDescriptor("PACKAGE_LARGE"));
        entries.add(objectTool);
        newObjTxt = "Actor";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getActor());
        objectTool = new ModelerCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("ACTOR"), UMLImageRegistry.getImageDescriptor("ACTOR_LARGE"));
        entries.add(objectTool);
        newObjTxt = "Use Case";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getUseCase());
        objectTool = new ModelerCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("USECASE"), UMLImageRegistry.getImageDescriptor("USECASE_LARGE"));
        entries.add(objectTool);

        //Fix #792
        newObjTxt = "Boundary Box";
        factory = new GraphElementCreationFactory(creationUtils, UseCaseSimpleObjectConstants.SIMPLE_OBJECT_BOUNDARYBOX, true);
        objectTool = new ModelerCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("BOUNDARYBOX"), UMLImageRegistry.getImageDescriptor("BOUNDARYBOX_LARGE"));
        entries.add(objectTool);
        //EndFix #792
        
        objectsDrawer.addAll(entries);
        if (objectsDrawer.getChildren().size() > 0)
        {
        	getRoot().add(objectsDrawer);
        }
    }

    /**
     * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated NOT
     */
    private void createConnectionsDrawer()
    {
        connectionsDrawer = new PaletteDrawer("Connections", null);
        List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.usecasediagram");

        CreationFactory factory;
        String newObjTxt;

        ModelerConnectionCreationToolEntry connectionTool;

        newObjTxt = "Association";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getAssociation())
        {
            public EObject getNewModelObject()
            {
                Association association = (Association) super.getNewModelObject();

                Property property1 = UMLFactory.eINSTANCE.createProperty();
                property1.setName("source");
                property1.setLower(1);
                property1.setUpper(1);

                Property property2 = UMLFactory.eINSTANCE.createProperty();
                property2.setName("target");
                property2.setLower(1);
                property2.setUpper(1);

                association.getOwnedEnds().add(property1);
                association.getOwnedEnds().add(property2);

                return association;
            }
        };
        connectionTool = new ModelerConnectionCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("ASSOCIATION"),
                UMLImageRegistry.getImageDescriptor("ASSOCIATION_LARGE"));
        entries.add(connectionTool);
        newObjTxt = "Generalization";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getGeneralization());
        connectionTool = new ModelerConnectionCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("GENERALIZATION"),
                UMLImageRegistry.getImageDescriptor("GENERALIZATION_LARGE"));
        entries.add(connectionTool);
        newObjTxt = "Include";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getInclude());
        connectionTool = new ModelerConnectionCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("INCLUDE"), UMLImageRegistry.getImageDescriptor("INCLUDE_LARGE"));
        entries.add(connectionTool);
        newObjTxt = "Extend";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExtend());
        connectionTool = new ModelerConnectionCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("EXTEND"), UMLImageRegistry.getImageDescriptor("EXTEND_LARGE"));
        entries.add(connectionTool);
        
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackageImport(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Package Import", "Package Import", factory, UMLImageRegistry.getImageDescriptor("PACKAGEIMPORT"),
        		UMLImageRegistry.getImageDescriptor("PACKAGEIMPORT_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getPackageMerge(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Package Merge", "Package Merge", factory, UMLImageRegistry.getImageDescriptor("PACKAGEMERGE"),
        		UMLImageRegistry.getImageDescriptor("PACKAGEMERGE_LARGE")));
        
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDependency(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Dependency", "Dependency", factory, UMLImageRegistry.getImageDescriptor("DEPENDENCY"),
        		UMLImageRegistry.getImageDescriptor("DEPENDENCY_LARGE")));

        connectionsDrawer.addAll(entries);
        if (connectionsDrawer.getChildren().size() > 0)
        {
        	getRoot().add(connectionsDrawer);
        }
    }

    /**
     * Creates the Palette container containing all the Palette entries for each figure. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated NOT
     */
    private void createCommentDrawer()
    {
        commentDrawer = new PaletteDrawer("Comment", null);
        List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.usecasediagram");

        CreationFactory factory;
        String newObjTxt;

        ModelerCreationToolEntry objectTool;
        ModelerConnectionCreationToolEntry connectionTool;

        newObjTxt = "Comment";
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getComment());
        objectTool = new ModelerCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("COMMENT"), UMLImageRegistry.getImageDescriptor("COMMENT_LARGE"));
        entries.add(objectTool);

        newObjTxt = "Comment Link";
        factory = new GraphElementCreationFactory(creationUtils, UseCaseSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK, false);
        connectionTool = new ModelerConnectionCreationToolEntry(newObjTxt, newObjTxt, factory, UMLImageRegistry.getImageDescriptor("COMMENTLINK"),
                UMLImageRegistry.getImageDescriptor("COMMENTLINK_LARGE"));
        entries.add(connectionTool);
        
        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getConstraint(), "default");
        entries.add(new ModelerCreationToolEntry("Constraint", "Constraint", factory, UMLImageRegistry.getImageDescriptor("CONSTRAINT"), UMLImageRegistry.getImageDescriptor("CONSTRAINT_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UseCaseSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK, "default", false);
        entries.add(new ModelerConnectionCreationToolEntry("Constraint Link", "Constraint Link", factory, UMLImageRegistry.getImageDescriptor("CONSTRAINTLINK"),
        		UMLImageRegistry.getImageDescriptor("CONSTRAINTLINK_LARGE")));


        commentDrawer.addAll(entries);
        if (commentDrawer.getChildren().size() > 0)
        {
        	getRoot().add(commentDrawer);
        }
    }

}
