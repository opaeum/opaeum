/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UseCaseConfiguration implements IConfiguration
{
    private UseCasePaletteManager paletteManager;

    private UseCaseEditPartFactory editPartFactory;

    private UseCaseCreationUtils creationUtils;

    /**
     * The DiagramGraphConf that contains graphical informations on the configuration
     */
    private DiagramGraphConf diagramGraphConf;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getId()
     * @generated
     */
    public String getId()
    {
        return new String("org.topcased.modeler.uml.usecasediagram");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getName()
     * @generated
     */
    public String getName()
    {
        return new String("Use Case Diagram");
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
            editPartFactory = new UseCaseEditPartFactory();
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
            paletteManager = new UseCasePaletteManager(getCreationUtils());
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
            creationUtils = new UseCaseCreationUtils(getDiagramGraphConf());
        }

        return creationUtils;
    }

    /**
     * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
     */
    public DiagramGraphConf getDiagramGraphConf()
    {
        if (diagramGraphConf == null)
        {
            URI fileURI = URI.createURI(UMLPlugin.getDefault().getBundle().getResource("org/topcased/modeler/uml/usecasediagram/diagram.graphconf").toString());
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.getResource(fileURI, true);
            if (resource != null && resource.getContents().get(0) instanceof DiagramGraphConf)
            {
                diagramGraphConf = (DiagramGraphConf) resource.getContents().get(0);
            }
        }

        return diagramGraphConf;
    }

}