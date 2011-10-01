/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 * 
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Vincent Hemery (Atos Origin) vincent.hemery@atosorigin.com -
 * Initial API and implementation 
 *****************************************************************************/

package org.topcased.modeler.uml.parentrule;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.GraphNodeEditPart;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.modeler.utils.Utils;

/**
 * The Class PartWithHeaderLabelSuffixParentRule provides a specific behavior
 * for determining whether the parent is correct. For EditParts which already
 * display a suffix in the header label, telling where the class comes from, the
 * decorator warning that the parent is not the same is redundant and useless.
 * If the Suffix contains information, the rule will return that the parent is
 * correct. Otherwise, the behavior not be changed.
 */
public class PartWithHeaderLabelSuffixParentRule extends NamedElementSynchronizedModelDiagramRule
{

    /**
     * If a "From ..." message will be displayed as a label suffix, return true.
     * Otherwise, return the supertype answer.
     * 
     * @param pTarget the target
     * 
     * @return true, if checks for right graphic parent
     * 
     * @see org.topcased.modeler.extensions.SynchronizedModelDiagramRule#hasRightGraphicParent(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    @Override
    public boolean hasRightGraphicParent(IDecoratorTarget pTarget)
    {
        GraphNodeEditPart lEditPart = (GraphNodeEditPart) pTarget.getAdapter(GraphicalEditPart.class);

        EObject lModelObject = (EObject) pTarget.getAdapter(EObject.class);

        if (lEditPart != null && lModelObject != null)
        {
            Object lParentPartModel = lEditPart.getParent().getModel();
            if (lParentPartModel instanceof GraphElement)
            {
                EObject lParentModel = Utils.getElement((GraphElement) lParentPartModel);
                if (lModelObject instanceof Element && lParentModel instanceof Element)
                {
                    String lAddedSuffix = UMLTools.getFromPackageNotation((Element) lModelObject,
                            (Element) lParentModel);
                    if (!"".equals(lAddedSuffix))
                    {
                        // information will already be displayed in the header
                        // label.
                        return true;
                    }
                }
            }
        }
        return super.hasRightGraphicParent(pTarget);
    }

}
