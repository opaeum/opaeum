/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (2009) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/

package org.topcased.modeler.uml.alldiagram.edit;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.commands.GEFtoEMFCommandWrapper;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.OffscreenEditPartFactory.CustomModeler;

public class AbstractStereotypableEdgeEditPart extends EMFGraphEdgeEditPart
{

    public AbstractStereotypableEdgeEditPart(GraphEdge conn)
    {
        super(conn);
    }

    @Override
    public void refresh()
    {
        super.refresh();
        boolean exist = false;
        EObject eObject = getEObject();
        if (eObject instanceof Element && isPreferenceForStereotypeAsANoteSet())
        {
            List<EObject> stereotypes = getAllAppliedStereotypes((Element) eObject);
            if (stereotypes != null && stereotypes.size() > 0 && getModel() instanceof GraphEdge)
            {
                for (EObject child : ((GraphEdge) getModel()).eContainer().eContents())
                {
                    if (child instanceof GraphNode)
                    {
                        GraphNode graphNode = (GraphNode) child;
                        SemanticModelBridge semanticModel = graphNode.getSemanticModel();
                        if (semanticModel instanceof EMFSemanticModelBridge)
                        {
                            // Look if the stereotype note has already been created
                            if (stereotypes.contains(((EMFSemanticModelBridge) semanticModel).getElement()))
                            {
                                exist = true;
                            }
                        }
                    }
                }
                // If the note hasn't been created yet
                if (!exist)
                {
                    createStereotypeNote();
                }
            }
        }

    }

    /**
     * Create a note that display all the attributes of all the element's pplied stereotypes
     * 
     * @param graphElement
     */
    private void createStereotypeNote()
    {
        EObject eObject = getEObject();
        if (eObject instanceof Element)
        {
            Element umlElement = (Element) eObject;
            EList<Stereotype> appliedStereotypes = umlElement.getAppliedStereotypes();
            // Check if the element has applied stereotypes
            if (appliedStereotypes != null && appliedStereotypes.size() > 0)
            {
                boolean hasAttribute = false;
                for (Stereotype s : appliedStereotypes)
                {
                    EObject stereoAppl = umlElement.getStereotypeApplication(s);
                    EList<EStructuralFeature> eAllAttributes = stereoAppl.eClass().getEAllStructuralFeatures();
                    if (eAllAttributes != null && eAllAttributes.size() - 1 > 0) // Always have one structural feature
                    {
                        hasAttribute = true;
                    }
                }
                // The note is displayed only if one for all the applied stereotypes that have at least one attribute
                if (hasAttribute)
                {
                    Modeler modelerEditor = ((ModelerGraphicalViewer) getViewer()).getModelerEditor();
                    if (!(modelerEditor instanceof CustomModeler))
                    {
                    	// umlElem.getStereotypeApplication(s)
                    	modelerEditor.getEditingDomain().getCommandStack().execute(
                    			createGraphNode(modelerEditor, umlElement.getStereotypeApplication(appliedStereotypes.get(0)), (GraphNode) ((GraphEdge) getModel()).eContainer()));
                    }
                }
            }
        }
    }

    /**
	 * Check in preference if stereotype must be display as a note
	 */
	protected boolean isPreferenceForStereotypeAsANoteSet() {
		return getPreferenceStore().getBoolean(
				AllDiagramPreferenceConstants.STEREOTYPE_AS_A_NOTE);
	}
    
    /**
     * Return all the applied stereotypes that have at least on attribute
     * 
     * @return
     */
    private List<EObject> getAllAppliedStereotypes(Element elem)
    {
        List<EObject> stereotypes = new LinkedList<EObject>();
        EList<Stereotype> appliedStereotypes = elem.getAppliedStereotypes();
        // Check if the element has applied stereotypes
        if (appliedStereotypes != null && appliedStereotypes.size() > 0)
        {
            for (Stereotype s : appliedStereotypes)
            {
                EObject stereoAppl = elem.getStereotypeApplication(s);
                EList<EStructuralFeature> eAllAttributes = stereoAppl.eClass().getEAllStructuralFeatures();
                if (eAllAttributes != null && eAllAttributes.size() - 1 > 0) // Always have one structural feature
                {
                    stereotypes.add(stereoAppl);
                }
            }
        }

        return stereotypes;
    }

    private org.eclipse.emf.common.command.Command createGraphNode(Modeler modeler, EObject newElement, GraphNode parentGraphNode)
    {
        GraphNode attributeGraphNode = DiagramInterchangeFactory.eINSTANCE.createGraphNode();
        EMFSemanticModelBridge element = DiagramInterchangeFactory.eINSTANCE.createEMFSemanticModelBridge();
        element.setElement(newElement);
        element.setPresentation("default");
        attributeGraphNode.setSemanticModel(element);
        return new GEFtoEMFCommandWrapper(new CreateGraphNodeCommand(modeler.getRootEditPart().getViewer().getEditDomain(), attributeGraphNode, parentGraphNode, new Point(0, 0),
                null, 0));
    }

}
