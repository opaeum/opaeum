/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.uml.alldiagram.commands.NamedElementRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.commands.GeneralizationEdgeCreationCommand;
import org.topcased.modeler.utils.Utils;

/**
 * Stereotype restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class StereotypeRestoreConnectionCommand extends NamedElementRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public StereotypeRestoreConnectionCommand(EditPart part)
    {
        super(part);
    }

    /**
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     * @generated NOT
     */
    protected void initializeCommands()
    {

        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof Stereotype)
        {
          	for(GraphElement graphElementTgt : getAllGraphElements())
          	{       
          		boolean autoRef = graphElementTgt.equals(graphElementSrc);

          		EObject eObjectTgt = Utils.getElement(graphElementTgt);

          		if (eObjectTgt instanceof Stereotype)
          		{
          			if (!autoRef)
          			{
          				// if the elt is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
          				createGeneralizationFromStereotypeToStereotypeGeneral(graphElementSrc, graphElementTgt);
          				// if elt is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
          				createGeneralizationFromStereotypeToStereotypeGeneral(graphElementTgt, graphElementSrc);
          			}
          		}
          		if (eObjectTgt instanceof ElementImport)
          		{
          			// if the elt is the source of the edge or if it is the target and that the
          			// SourceTargetCouple is reversible
          			createExtensionFromStereotypeToElementImportElementImport(graphElementSrc, graphElementTgt);
          		}

          	}
        }
    }

    /**
     * @param srcElt the source element
     * @param targetElt the target element
     * @generated
     */
    private void createGeneralizationFromStereotypeToStereotypeGeneral(GraphElement srcElt, GraphElement targetElt)
    {
        Stereotype sourceObject = (Stereotype) Utils.getElement(srcElt);
        Stereotype targetObject = (Stereotype) Utils.getElement(targetElt);

        for (Generalization edgeObject : sourceObject.getGeneralizations())
        {
            if (targetObject.equals(edgeObject.getGeneral()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, Generalization.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        GeneralizationEdgeCreationCommand cmd = new GeneralizationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
                        cmd.setTarget(targetElt);
                        add(cmd);
                    }
                }
            }
        }
    }

    /**
     * @param srcElt the source element
     * @param targetElt the target element
     * @generated NOT
     */
    private void createExtensionFromStereotypeToElementImportElementImport(GraphElement srcElt, GraphElement targetElt)
    {
        Stereotype sourceObject = (Stereotype) Utils.getElement(srcElt);
        ElementImport targetObject = (ElementImport) Utils.getElement(targetElt);

        EList<Element> edgeObjectList = ((org.eclipse.uml2.uml.Profile) Utils.getDiagramModelObject(srcElt)).getOwnedElements();
        for (Iterator<Element> it = edgeObjectList.iterator(); it.hasNext();)
        {
            Object obj = it.next();
            if (obj instanceof Extension)
            {
                Extension edgeObject = (Extension) obj;

                if (edgeObject.getMemberEnds().size() == 2 && sourceObject.equals(edgeObject.getMemberEnds().get(0).getType()) && targetObject.getImportedElement() != null && targetObject.getImportedElement().equals(edgeObject.getMemberEnds().get(1).getType()))
                {
                    // check if the relation does not exists yet
                    List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, Extension.class);
                    if (!isAlreadyPresent(existing, edgeObject))
                    {
                        ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                        // restore the link with its default presentation
                        GraphElement edge = factory.createGraphElement(edgeObject);
                        if (edge instanceof GraphEdge)
                        {
                            ExtensionEdgeCreationCommand cmd = new ExtensionEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
                            cmd.setTarget(targetElt);
                            add(cmd);
                        }
                    }
                }
            }
        }
    }

}