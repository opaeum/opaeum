/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.uml2.uml.Generalization;
import org.topcased.modeler.editor.EditPart2ModelAdapterFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.graphconf.exceptions.MissingGraphConfFileException;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.alldiagram.edit.NamedElementEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEditPart;
import org.topcased.modeler.uml.profilediagram.edit.ElementImportEditPart;
import org.topcased.modeler.uml.profilediagram.edit.ExtensionEditPart;
import org.topcased.modeler.uml.profilediagram.edit.StereotypeEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProfileConfiguration implements IConfiguration
{
    private ProfilePaletteManager paletteManager;

    private ProfileEditPartFactory editPartFactory;

    private ProfileCreationUtils creationUtils;

    /**
     * The DiagramGraphConf that contains graphical informations on the configuration
     */
    private DiagramGraphConf diagramGraphConf;

    /**
     * Constructor. Initialize Adapter factories.
     * 
     * @generated
     */
    public ProfileConfiguration()
    {
        registerAdapters();
    }

    /**
     * Registers the Adapter Factories for all the EditParts
     * 
     * @generated
     */
    private void registerAdapters()
    {
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(StereotypeEditPart.class, org.eclipse.uml2.uml.Stereotype.class), StereotypeEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PropertyEditPart.class, org.eclipse.uml2.uml.Property.class), PropertyEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(NamedElementEditPart.class, org.eclipse.uml2.uml.NamedElement.class), NamedElementEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DependencyEditPart.class, org.eclipse.uml2.uml.Dependency.class), DependencyEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(Generalization.class, org.eclipse.uml2.uml.Generalization.class), Generalization.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ElementImportEditPart.class, org.eclipse.uml2.uml.ElementImport.class), ElementImportEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ExtensionEditPart.class, org.eclipse.uml2.uml.Extension.class), ExtensionEditPart.class);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getId()
     * @generated
     */
    public String getId()
    {
        return new String("org.topcased.modeler.uml.profilediagram");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getName()
     * @generated
     */
    public String getName()
    {
        return new String("Profile Diagram");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
     * @generated
     */
    public EditPartFactory getEditPartFactory()
    {
        if (editPartFactory == null)
        {
            editPartFactory = new ProfileEditPartFactory();
        }

        return editPartFactory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
     * @generated
     */
    public IPaletteManager getPaletteManager()
    {
        if (paletteManager == null)
        {
            paletteManager = new ProfilePaletteManager(getCreationUtils());
        }

        return paletteManager;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
     * @generated
     */
    public ICreationUtils getCreationUtils()
    {
        if (creationUtils == null)
        {
            creationUtils = new ProfileCreationUtils(getDiagramGraphConf());
        }

        return creationUtils;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
     * @generated
     */
    public DiagramGraphConf getDiagramGraphConf()
    {
        if (diagramGraphConf == null)
        {
            URL url = UMLPlugin.getDefault().getBundle().getResource("org/topcased/modeler/uml/profilediagram/diagram.graphconf");
            if (url != null)
            {
                URI fileURI = URI.createURI(url.toString());
                ResourceSet resourceSet = new ResourceSetImpl();
                Resource resource = resourceSet.getResource(fileURI, true);
                if (resource != null && resource.getContents().get(0) instanceof DiagramGraphConf)
                {
                    diagramGraphConf = (DiagramGraphConf) resource.getContents().get(0);
                }
            }
            else
            {
                new MissingGraphConfFileException("The *.diagramgraphconf file can not be retrieved. Check if the path is correct in the Configuration class of your diagram.");
            }
        }

        return diagramGraphConf;
    }

}