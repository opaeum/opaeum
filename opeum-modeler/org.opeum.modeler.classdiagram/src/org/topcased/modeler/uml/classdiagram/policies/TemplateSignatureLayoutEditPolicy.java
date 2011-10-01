/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 * 
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: eperico (atos origin) emilien.perico@atosorigin.com - Initial
 * API and implementation
 * 
 *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.TemplateParameter;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;
import org.topcased.modeler.uml.classdiagram.commands.ClassifierTemplateParameterNodeCreationCommand;
import org.topcased.modeler.utils.Utils;

/**
 * The Class TemplateSignatureLayoutEditPolicy.
 * 
 * @author eperico
 */
public class TemplateSignatureLayoutEditPolicy extends ModelerLayoutEditPolicy
{

    /**
     * Instantiates a new redefinable template signature layout edit policy.
     */
    public TemplateSignatureLayoutEditPolicy()
    {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValid(EObject child, EObject parent)
    {
        if (child instanceof TemplateParameter)
        {
            return true;
        }
        return super.isValid(child, parent);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getXYLayout()
     */
    @Override
    protected XYLayout getXYLayout()
    {
        IFigure hostFigure = getHostFigure();
        LayoutManager layoutManager = hostFigure.getParent().getLayoutManager();
        return (XYLayout) layoutManager;      
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getCreateCommand(EditDomain domain, GraphNode newObject, GraphNode newParent,
            EObject newContainerParent, Point location, Dimension dimension, int attach, List featuresList,
            boolean needModelUpdate)
    {
        if (Utils.getElement(newObject) instanceof ClassifierTemplateParameter)
        {
            return new ClassifierTemplateParameterNodeCreationCommand(domain, newObject, newParent, newContainerParent,
                    location, dimension, attach, featuresList, needModelUpdate);
        }
        else
        {
            return super.getCreateCommand(domain, newObject, newParent, newContainerParent, location, dimension,
                    attach, featuresList, needModelUpdate);
        }
    }

}
