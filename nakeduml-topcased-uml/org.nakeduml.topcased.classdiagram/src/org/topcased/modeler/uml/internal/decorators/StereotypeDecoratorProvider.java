/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Thomas Szadel (Atos Origin)
 ******************************************************************************/
package org.topcased.modeler.uml.internal.decorators;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.topcased.modeler.edit.EListEditPart;

/**
 * This class provides a decorator for stereotypes.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public class StereotypeDecoratorProvider extends AbstractProvider implements IDecoratorProvider
{

    /** The key used for the mood decoration */
    public static final String STEREOTYPE = "Stereotype_Decorator"; //$NON-NLS-1$

    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider#createDecorators(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    public void createDecorators(IDecoratorTarget decoratorTarget)
    {
        EObject object = StereotypeDecorator.getEObjectDecoratorTarget(decoratorTarget);
        if (object != null)
        {
            decoratorTarget.installDecorator(STEREOTYPE, new StereotypeDecorator(decoratorTarget));
        }
    }

    /**
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(IOperation operation)
    {
        if (operation instanceof CreateDecoratorsOperation)
        {
            IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
            GraphicalEditPart editPart = (GraphicalEditPart) decoratorTarget.getAdapter(GraphicalEditPart.class);
            if (editPart != null)
            {
                if (editPart instanceof EListEditPart)
                {
                    return false;
                }
                else
                {
                    return StereotypeDecorator.getEObjectDecoratorTarget(decoratorTarget) != null;
                }
            }
        }
        return false;
    }

}
