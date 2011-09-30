/*****************************************************************************
 * Copyright (c) 2008; TOPCASED consortium.
 *
 * Contributors:
 *  Barraille Frederic; [(Atos Origin)] [frederic.barraille@atosorigin.com]
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Constraint restore connection command
 * 
 * @generated NOT
 */
public class ConstraintRestoreConnectionCommand extends NamedElementRestoreConnectionCommand
{
    /**
     * @param part the EditPart that is restored
     * @generated
     */
    public ConstraintRestoreConnectionCommand(EditPart part)
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

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof Constraint)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		boolean autoRef = graphElementTgt.equals(graphElementSrc);
        		EObject eltObject2 = Utils.getElement(graphElementTgt);
        		if (eltObject2 instanceof Element)
        		{
        			if (!autoRef)
        			{
        				// if elt is the target of the edge or if it is the source and that the SourceTargetCouple
        				// is reversible
        				createConstraintLinkFromElementToConstraint(graphElementSrc, graphElementTgt);
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
    private void createConstraintLinkFromElementToConstraint(GraphElement srcElt, GraphElement targetElt)
    {
        Constraint sourceObject = (Constraint) Utils.getElement(srcElt);
        Element targetObject = (Element) Utils.getElement(targetElt);
        if (((Constraint) sourceObject).getConstrainedElements().contains(targetObject))
        {
            // check if the relation does not exists yet
            if (getExistingEdges(srcElt, targetElt, AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK).size() == 0)
            {
                GraphEdge edge = Utils.createGraphEdge(AllSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK);
                ConstraintLinkEdgeCreationCommand cmd = new ConstraintLinkEdgeCreationCommand(null, edge, srcElt, false);
                cmd.setTarget(targetElt);
                add(cmd);
            }
        }
    }
}
