/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (ATOS ORIGIN INTEGRATION) caroline.bourdeudaguerre@atosorigin.com
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.decorators;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.uml2.uml.Behavior;
import org.topcased.modeler.diagrams.model.util.DiagramsUtils;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.extensions.AbstractLinkedDiagramDecorator;

public class ClassLinkedDiagramDecorator extends AbstractLinkedDiagramDecorator {

	 /**
     * Instantiates a new use case linked diagram decorator.
     * 
     * @param pTarget the target
     */
	public ClassLinkedDiagramDecorator(IDecoratorTarget pTarget) {
		super(pTarget);
	}

    /**
     * Method to determine if the decoratorTarget is a supported type for this decorator and return the associated
     * Classifier element.
     * 
     * @param pDecoratorTarget IDecoratorTarget to check and return valid Classifier target.
     * 
     * @return node Node if IDecoratorTarget can be supported, null otherwise.
     */
    public static org.eclipse.uml2.uml.Class getClassDecoratorTarget(IDecoratorTarget pDecoratorTarget)
    {
        return (org.eclipse.uml2.uml.Class) pDecoratorTarget.getAdapter(org.eclipse.uml2.uml.Class.class);
    }

    /**
     * Checks for diagram.
     * 
     * @param pTarget the target
     * 
     * @return true, if checks for diagram
     * 
     * @see org.topcased.modeler.extensions.AbstractLinkedDiagramDecorator#hasDiagram(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    protected boolean hasDiagram(IDecoratorTarget pTarget)
    {

    	org.eclipse.uml2.uml.Class lModelObject = getClassDecoratorTarget(pTarget);
    	EditPart lEp = (EditPart) pTarget.getAdapter(EditPart.class);
        if (lModelObject != null && lEp != null)
        {
            Behavior lClassClassifierBehavior = lModelObject.getClassifierBehavior();
            if (lClassClassifierBehavior != null)
            {
                if (lEp.getViewer() instanceof ModelerGraphicalViewer)
                {
                    return !(DiagramsUtils.findAllExistingDiagram(((ModelerGraphicalViewer) lEp.getViewer()).getModelerEditor().getDiagrams(), lClassClassifierBehavior).isEmpty());
                }
            }
        }
        return false;
    }

}
