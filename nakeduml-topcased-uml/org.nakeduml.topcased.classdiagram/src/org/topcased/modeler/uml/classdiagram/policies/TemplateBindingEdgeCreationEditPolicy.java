/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateableElement;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.commands.TemplateBindingEdgeCreationCommand;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * TemplateBinding edge creation
 * 
 * @generated
 */
public class TemplateBindingEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy
{
    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain, org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source)
    {
        return new TemplateBindingEdgeCreationCommand(domain, edge, source);
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
     * @generated
     */
    protected boolean checkEdge(GraphEdge edge)
    {
        return Utils.getElement(edge) instanceof TemplateBinding;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkSource(GraphElement source)
    {
        EObject object = Utils.getElement(source);
        if (object instanceof org.eclipse.uml2.uml.TemplateableElement)
        {
            return true;
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
     * @generated NOT
     */
    protected boolean checkTargetForSource(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);
        
        if (sourceObject instanceof org.eclipse.uml2.uml.TemplateableElement
                && targetObject instanceof org.eclipse.uml2.uml.TemplateableElement)
        {
            if (!sourceObject.equals(targetObject))
            {
                TemplateableElement element = (TemplateableElement) targetObject;
                if (element.getOwnedTemplateSignature() != null && 
                        element.getOwnedTemplateSignature().getOwnedParameters().size() != 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
     * @generated
     */
    protected boolean checkCommand(Command command)
    {
        return command instanceof TemplateBindingEdgeCreationCommand;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.TemplateableElement
                && targetObject instanceof org.eclipse.uml2.uml.TemplateableElement)
        {
            SourceTargetData srcTargetData = new SourceTargetData(false, false, SourceTargetData.SOURCE,
                    "org.eclipse.uml2.uml.TemplateableElement", "templateBinding", "boundElement", "signature",
                    "templateBinding", "ownedTemplateSignature", null, "template");
            return srcTargetData;
        }
        return null;
    }
    
    

}