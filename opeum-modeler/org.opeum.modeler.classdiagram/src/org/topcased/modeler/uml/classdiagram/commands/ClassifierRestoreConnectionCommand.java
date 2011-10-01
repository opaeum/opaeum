/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * contributors:
 * eperico (emilien.perico@atosorigin.com) - add restore connection for templateBinding
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateableElement;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * Classifier restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ClassifierRestoreConnectionCommand extends TypeRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public ClassifierRestoreConnectionCommand(EditPart part)
    {
        super(part);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     * @generated NOT
     */
    protected void initializeCommands()
    {

        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof Classifier)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		EObject eObjectTgt = Utils.getElement(graphElementTgt);

        		if (eObjectTgt instanceof Classifier)
        		{
        			// if the elt is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createGeneralizationFromClassifierToClassifier(graphElementSrc, graphElementTgt);
        			// if elt is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createGeneralizationFromClassifierToClassifier(graphElementTgt, graphElementSrc);
        		
        			// classifier is a subtype of TemplateableElement
                    createTemplateBindingFromTemplateableElementToTemplateSignature(graphElementSrc, graphElementTgt);
                    createTemplateBindingFromTemplateableElementToTemplateSignature(graphElementTgt, graphElementSrc);
        		}


        	}
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated NOT
     */
    private void createGeneralizationFromClassifierToClassifier(GraphElement srcNode, GraphElement targetNode)
    {
        Classifier sourceObject = (Classifier) Utils.getElement(srcNode);
        Classifier targetObject = (Classifier) Utils.getElement(targetNode);

        for (Generalization edgeObject : sourceObject.getGeneralizations())
        {
            if (targetObject.equals(edgeObject.getGeneral()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Generalization.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        GeneralizationEdgeCreationCommand cmd = new GeneralizationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }
    
    /**
     * Creates the template binding edge from templateable element to template signature.
     * 
     * @param srcNode the source node (templateableElement)
     * @param targetNode the target node (templateSignature)
     */
    private void createTemplateBindingFromTemplateableElementToTemplateSignature(GraphElement srcNode, GraphElement targetNode)
    {
        TemplateableElement sourceObject = (TemplateableElement) Utils.getElement(srcNode);
        TemplateableElement targetObject = (TemplateableElement) Utils.getElement(targetNode);

        for (TemplateBinding edgeObject : sourceObject.getTemplateBindings())
        {
            if (edgeObject.getSignature().equals(targetObject.getOwnedTemplateSignature()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, TemplateBinding.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        TemplateBindingEdgeCreationCommand cmd = new TemplateBindingEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }
}