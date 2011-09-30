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
package org.topcased.modeler.uml.profilediagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Extension;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.uml.profilediagram.commands.ExtensionEdgeCreationCommand;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * Extension edge creation
 * 
 * @generated
 */
public class ExtensionEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy
{
    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain,
     *      org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source)
    {
        return new ExtensionEdgeCreationCommand(domain, edge, source);
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
     * @generated
     */
    protected boolean checkEdge(GraphEdge edge)
    {
        return Utils.getElement(edge) instanceof Extension;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected boolean checkSource(GraphElement source)
    {
        EObject object = Utils.getElement(source);
        if (object instanceof org.eclipse.uml2.uml.Stereotype)
        {
            return true;
        }
        return false;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated NOT
     */
    protected boolean checkTargetForSource(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.Stereotype && targetObject instanceof org.eclipse.uml2.uml.ElementImport)
        {
            // Check whether the ElementImport is linked with a Metaclass
            if (!sourceObject.equals(targetObject) && ((ElementImport) targetObject).getImportedElement() != null)
            {
                return true;
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
        return command instanceof ExtensionEdgeCreationCommand;
    }

    /**
     * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement,
     *      org.topcased.modeler.di.model.GraphElement)
     * @generated
     */
    protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target)
    {
        EObject sourceObject = Utils.getElement(source);
        EObject targetObject = Utils.getElement(target);

        if (sourceObject instanceof org.eclipse.uml2.uml.Stereotype && targetObject instanceof org.eclipse.uml2.uml.ElementImport)
        {
            return new SourceTargetData(false, false, SourceTargetData.DIAGRAM, "org.eclipse.uml2.uml.Profile", "packagedElement", null, null, null, null, null, null);
        }
        return null;
    }

}