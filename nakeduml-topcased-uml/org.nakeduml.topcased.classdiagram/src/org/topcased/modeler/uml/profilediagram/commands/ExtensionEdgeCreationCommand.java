/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.profilediagram.commands;

import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

/**
 * Extension edge creation command
 * 
 * @generated
 */
public class ExtensionEdgeCreationCommand extends CreateTypedEdgeCommand
{
    private Extension extension;

    private Stereotype sourceStereotype;

    private ElementImport targetElementImport;

    private Property stereotypeProperty;

    private ExtensionEnd extensionEnd;

    /**
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @generated
     */
    public ExtensionEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src)
    {
        this(domain, newObj, src, true);
    }

    /**
     * @param domain the edit domain
     * @param newObj the graph edge of the new connection
     * @param src the graph element of the source
     * @param needModelUpdate set it to true if the model need to be updated
     * @generated
     */
    public ExtensionEdgeCreationCommand(EditDomain domain, GraphEdge newObj, GraphElement src, boolean needModelUpdate)
    {
        super(domain, newObj, src, needModelUpdate);
    }

    /**
     * Create the model objects that will be associated with the Association
     * 
     * @see org.topcased.modeler.commands.CreateGraphEdgeCommand#execute()
     */
    public void execute()
    {
        // Only create necessary UML elements when the model needs to be updated : this can not be done at the creation
        // tool level, as we do not know yet the Stereotype and ElementImport that will be connected
        if (isUpdateModel())
        {
            // Retrieve Extension edge, source Stereotype and target ElementImport
            extension = (Extension) Utils.getElement(getEdge());
            sourceStereotype = (Stereotype) Utils.getElement(getSource());
            targetElementImport = (ElementImport) Utils.getElement(getTarget());

            // Create a new Property to be added in the Stereotype attributes
            stereotypeProperty = UMLFactory.eINSTANCE.createProperty();
            stereotypeProperty.setType((Type) targetElementImport.getImportedElement());
            stereotypeProperty.setName("base_" + ((Type) targetElementImport.getImportedElement()).getName());

            // create and initialize the ExtensionEnd associated with the Extension and linked with the Stereotype
            extensionEnd = UMLFactory.eINSTANCE.createExtensionEnd();
            extensionEnd.setName("extension_" + sourceStereotype.getName());
            extensionEnd.setType(sourceStereotype);
            extensionEnd.setAggregation(AggregationKind.COMPOSITE_LITERAL);

            // init the name of the Extension contained by the profile
            extension.setName(targetElementImport.getImportedElement().getName() + "_" + sourceStereotype.getName());
            // update Extension ends
            extension.getOwnedEnds().add(extensionEnd);
            extension.getMemberEnds().add(stereotypeProperty);
        }

        super.execute();
    }

    /**
     * @generated NOT
     */
    protected void redoModel()
    {
        // Add the Extension to the current Profile
        ((org.eclipse.uml2.uml.Profile) Utils.getElement(getContainer())).getPackagedElements().add(extension);

        // Add a Property to the related Stereotype
        sourceStereotype.getOwnedAttributes().add(stereotypeProperty);
    }

    /**
     * @generated NOT
     */
    protected void undoModel()
    {
        // Remove the association from the package
        ((org.eclipse.uml2.uml.Profile) Utils.getElement(getContainer())).getPackagedElements().remove(extension);

        // Remove the Property added to the related Stereotype
        sourceStereotype.getOwnedAttributes().remove(stereotypeProperty);
    }

}