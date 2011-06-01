/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram;

import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerPaletteManager;
import org.topcased.modeler.utils.CustomPaletteArrayList;

/**
 * Generated Palette Manager <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProfilePaletteManager extends ModelerPaletteManager
{
    // declare all the palette categories of the diagram
    private PaletteDrawer objectsDrawer;

    /**
     * @generated
     */
    private PaletteDrawer connectionsDrawer;

    private ICreationUtils creationUtils;

    /**
     * The Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param utils the creation utils for the tools of the palette
     * @generated
     */
    public ProfilePaletteManager(ICreationUtils utils)
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
    }

    /**
     * Updates the main categories of the palette <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void updateCategories()
    {
        // deletion of the existing categories and creation of the updated categories

        getRoot().remove(objectsDrawer);
        createObjectsDrawer();

        getRoot().remove(connectionsDrawer);
        createConnectionsDrawer();
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
        List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.profilediagram");

        CreationFactory factory;

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getStereotype(), "default");
        entries.add(new ModelerCreationToolEntry("Stereotype", "Stereotype", factory, ProfileImageRegistry.getImageDescriptor("STEREOTYPE"),
                ProfileImageRegistry.getImageDescriptor("STEREOTYPE_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getProperty(), "default");
        entries.add(new ModelerCreationToolEntry("Property", "Property", factory, ProfileImageRegistry.getImageDescriptor("PROPERTY"), ProfileImageRegistry.getImageDescriptor("PROPERTY_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getElementImport(), "default");
        entries.add(new ModelerCreationToolEntry("Metaclass", "Metaclass", factory, ProfileImageRegistry.getImageDescriptor("ELEMENTIMPORT"),
                ProfileImageRegistry.getImageDescriptor("ELEMENTIMPORT_LARGE")));

        objectsDrawer.addAll(entries);
        if (objectsDrawer.getChildren().size() > 0)
        {
        	getRoot().add(objectsDrawer);
        }
    }

    /**
     * Creates the Palette container containing all the Palette entries for each figure.
     * 
     * @generated NOT
     */
    private void createConnectionsDrawer()
    {
        connectionsDrawer = new PaletteDrawer("Connections", null);
        List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.profilediagram");

        CreationFactory factory;

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getDependency(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Dependency", "Dependency", factory, ProfileImageRegistry.getImageDescriptor("DEPENDENCY"),
                ProfileImageRegistry.getImageDescriptor("DEPENDENCY_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getGeneralization(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Generalization", "Generalization", factory, ProfileImageRegistry.getImageDescriptor("GENERALIZATION"),
                ProfileImageRegistry.getImageDescriptor("GENERALIZATION_LARGE")));

        factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getExtension(), "default");
        entries.add(new ModelerConnectionCreationToolEntry("Extension", "Extension", factory, ProfileImageRegistry.getImageDescriptor("EXTENSION"),
                ProfileImageRegistry.getImageDescriptor("EXTENSION_LARGE")));

        connectionsDrawer.addAll(entries);
        if (connectionsDrawer.getChildren().size() > 0)
        {
        	getRoot().add(connectionsDrawer);
        }
    }

}
