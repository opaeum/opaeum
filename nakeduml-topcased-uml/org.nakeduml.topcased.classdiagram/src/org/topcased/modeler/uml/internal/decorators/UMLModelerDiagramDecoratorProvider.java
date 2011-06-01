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
 *  Vincent Hemery (Atos Origin) vincent.hemery@atosorigin.com - Initial API and implementation
 *  Caroline Bourdeu d'Aguerre (ATOS ORIGIN INTEGRATION) caroline.bourdeudaguerre@atosorigin.com
 *****************************************************************************/

package org.topcased.modeler.uml.internal.decorators;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.topcased.modeler.edit.EListEditPart;

/**
 * This class provides a decorator for informing that Diagrams are linked to an UML element.
 * 
 */
public class UMLModelerDiagramDecoratorProvider extends AbstractProvider implements IDecoratorProvider
{

    /** The key used for the mood decoration. */
    public static final String DIAGRAM = "UML_Diagram_Decorator"; //$NON-NLS-1$

    /**
     * Creates the decorators.
     * 
     * @param decoratorTarget the decorator target
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider#createDecorators(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    public void createDecorators(IDecoratorTarget decoratorTarget)
    {
        if (UseCaseLinkedDiagramDecorator.getUseCaseDecoratorTarget(decoratorTarget) != null)
        {
            decoratorTarget.installDecorator(DIAGRAM, new UseCaseLinkedDiagramDecorator(decoratorTarget));
        }
        if ( ClassLinkedDiagramDecorator.getClassDecoratorTarget(decoratorTarget) != null)
        {
        	decoratorTarget.installDecorator(DIAGRAM, new ClassLinkedDiagramDecorator(decoratorTarget));
        }
    }

    /**
     * Provides.
     * 
     * @param operation the operation
     * 
     * @return true, if provides
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(IOperation operation)
    {
        if (operation != null && operation instanceof CreateDecoratorsOperation)
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
                    return UseCaseLinkedDiagramDecorator.getUseCaseDecoratorTarget(decoratorTarget) != null
                    	|| ClassLinkedDiagramDecorator.getClassDecoratorTarget(decoratorTarget) != null;
                }
            }
        }
        return false;
    }
}
