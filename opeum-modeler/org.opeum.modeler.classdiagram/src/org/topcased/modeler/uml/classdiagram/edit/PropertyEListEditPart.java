/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 *******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EListEditPart;

/**
 * A customized EListEditPart used to filter the Property that are associated with an Association
 * 
 * Creation : 1 mars 2006
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class PropertyEListEditPart extends EListEditPart
{

    /**
     * Constructor
     * 
     * @param obj the GraphNode
     * @param feature the EStructuralFeature
     */
    public PropertyEListEditPart(GraphNode obj, EStructuralFeature feature)
    {
        super(obj, feature);
    }

    /**
     * Override the method to filter Properties associated with an Association
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List<GraphNode> getModelChildren()
    {
        List<GraphNode> graphNodeChildren = new ArrayList<GraphNode>();
        for (DiagramElement elt : getGraphNode().getContained())
        {
            if (elt instanceof GraphNode)
            {
                EMFSemanticModelBridge m = (EMFSemanticModelBridge) ((GraphNode) elt).getSemanticModel();
                if (m.getElement() instanceof Property && ((Property) m.getElement()).getAssociation() == null)
                {
                    graphNodeChildren.add((GraphNode) elt);
                }
            }
        }
        return graphNodeChildren;
    }

}
