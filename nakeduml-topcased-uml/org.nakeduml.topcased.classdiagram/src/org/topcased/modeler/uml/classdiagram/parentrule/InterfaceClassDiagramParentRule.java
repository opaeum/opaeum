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
 *  Benoit MAGGI (Atos Origin) benoit.maggi@atosorigin.com - Initial API and implementation : Feature #2208
 *
  *****************************************************************************/


package org.topcased.modeler.uml.classdiagram.parentrule;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.edit.GraphNodeEditPart;
import org.topcased.modeler.uml.parentrule.PartWithHeaderLabelSuffixParentRule;



/**
 * The Class InterfaceClassDiagramParentRule.
 */
public class InterfaceClassDiagramParentRule extends PartWithHeaderLabelSuffixParentRule
{

    /* (non-Javadoc)
     * @see org.topcased.modeler.uml.parentrule.PartWithHeaderLabelSuffixParentRule#hasRightGraphicParent(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    @Override
    public boolean hasRightGraphicParent(IDecoratorTarget pTarget)
    {
        GraphNodeEditPart lEditPart = (GraphNodeEditPart) pTarget.getAdapter(GraphicalEditPart.class);

        EObject lModelObject = (EObject) pTarget.getAdapter(EObject.class);

        if (lEditPart != null && lModelObject != null)
        {
            EObject lEObjectParent = lModelObject.eContainer();
            if (lEObjectParent!= null && lEObjectParent instanceof Element) {
    			Element lElementModelParent = (Element) lEObjectParent;
            	if (lElementModelParent instanceof Component){
            		return true;
            	}   
            }
        }
        return super.hasRightGraphicParent(pTarget);
    }

}
