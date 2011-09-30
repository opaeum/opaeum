/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.uml.alldiagram.commands.ElementRestoreConnectionCommand;
import org.topcased.modeler.utils.Utils;

/**
 * TemplateSignature restore connection command
 * 
 * @generated
 */
public class TemplateSignatureRestoreConnectionCommand extends ElementRestoreConnectionCommand
{
    /**
     * @param part the EditPart that is restored
     * @generated
     */
    public TemplateSignatureRestoreConnectionCommand(EditPart part)
    {
        super(part);
    }

    /**
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     * @generated
     */
    protected void initializeCommands()
    {

        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof TemplateSignature)
        {
            for (GraphElement graphElementTgt : getAllGraphElements())
            {
                boolean autoRef = graphElementTgt.equals(graphElementSrc);

                EObject eObjectTgt = Utils.getElement(graphElementTgt);

                if (eObjectTgt instanceof TemplateableElement)
                {
                    if (autoRef)
                    {
                        // autoRef not allowed
                    }
                    else
                    {
                        // if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
                        createTemplateBindingFromTemplateableElementToTemplateSignature_Template(graphElementTgt,
                                graphElementSrc);
                    }
                }

            }
        }
    }

    /**
     * @param srcElt the source element
     * @param targetElt the target element
     * @generated
     */
    private void createTemplateBindingFromTemplateableElementToTemplateSignature_Template(GraphElement srcElt,
            GraphElement targetElt)
    {
        TemplateableElement sourceObject = (TemplateableElement) Utils.getElement(srcElt);
        TemplateSignature targetObject = (TemplateSignature) Utils.getElement(targetElt);

        EList edgeObjectList = sourceObject.getTemplateBindings();
        for (Iterator it = edgeObjectList.iterator(); it.hasNext();)
        {
            Object obj = it.next();
            if (obj instanceof TemplateBinding)
            {
                TemplateBinding edgeObject = (TemplateBinding) obj;
                if (targetObject.equals(edgeObject.getSignature()) && sourceObject.equals(edgeObject.getBoundElement())
                        && sourceObject.getTemplateBindings().contains(edgeObject)
                        && targetObject.equals(sourceObject.getOwnedTemplateSignature())
                        && sourceObject.equals(targetObject.getTemplate()))
                {
                    // check if the relation does not exists yet
                    List < GraphEdge > existing = getExistingEdges(srcElt, targetElt, TemplateBinding.class);
                    if (!isAlreadyPresent(existing, edgeObject))
                    {
                        ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                        // restore the link with its default presentation
                        GraphElement edge = factory.createGraphElement(edgeObject);
                        if (edge instanceof GraphEdge)
                        {
                            TemplateBindingEdgeCreationCommand cmd = new TemplateBindingEdgeCreationCommand(
                                    getEditDomain(), (GraphEdge) edge, srcElt, false);
                            cmd.setTarget(targetElt);
                            add(cmd);
                        }
                    }
                }
            }
        }
    }

}