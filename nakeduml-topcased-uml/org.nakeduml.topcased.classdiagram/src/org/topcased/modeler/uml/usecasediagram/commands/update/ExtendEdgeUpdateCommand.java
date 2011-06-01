/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.commands.update;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Class that create a command in order to update operation parameters <br>
 * creation : 12 mai 2005
 * 
 * @author <a href="mailto:mathieu.garcia@anyware-tech.com">Mathieu Garcia</a>
 */
public class ExtendEdgeUpdateCommand extends Command
{
    // new values
    private Extend extendedObject;

    private ExtensionPoint extensionPoint;

    private String condition;

    private Object extensionPointData;

    // old values
    private Extend oldExtendedObject;

    private ExtensionPoint oldExtensionPoint;

    private String oldCondition;

    private Object oldExtensionPointData;

    /**
     * Create a command for updating parameters on a given extend edge
     * 
     * @param extended the extend to update
     * @param extPoint sourceClass the new extension point
     * @param cond targetClass the new condition
     */
    public ExtendEdgeUpdateCommand(Extend extended, Object extPoint, String cond)
    {
        extendedObject = extended;
        extensionPointData = extPoint;
        condition = cond;
    }

    /**
     * Set the values on the ExtendEdge
     */
    protected void setValues()
    {
        // REV only update the relevant properties and do not automatically
        // recreate the Constraint and the OpaqueExpression ... the command
        // should be marked as @deprecated and the properties should be managed
        // through the Tabbed Properties View.

        if (extensionPointData instanceof ExtensionPoint)
        {
            extensionPoint = (ExtensionPoint) extensionPointData;
        }
        else
        {
            extensionPoint = UMLFactory.eINSTANCE.createExtensionPoint();
            extensionPoint.setName((String) extensionPointData);
            extendedObject.getExtendedCase().getExtensionPoints().add(extensionPoint);
        }

        // Indicate to the extend object the extended use case and the extension
        // point to used
        while (extendedObject.getExtensionLocations().size() != 0)
        {
            extendedObject.getExtensionLocations().remove(0);
        }
        extendedObject.getExtensionLocations().add(extensionPoint);

        // Remove old condition
        extendedObject.setCondition(null);
        Constraint constraint = UMLFactory.eINSTANCE.createConstraint();
        if (condition != null && condition.length() != 0)
        {
            OpaqueExpression opaqueExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
            opaqueExpression.getBodies().add(condition);
            constraint.setSpecification(opaqueExpression);
            constraint.getConstrainedElements().add(extendedObject);
            extendedObject.setCondition(constraint);
        }
    }

    /**
     * Switch the old and new values
     */
    protected void switchValues()
    {
        String tempContition = condition;
        condition = oldCondition;
        oldCondition = tempContition;

        Extend tempExtendedObject = extendedObject;
        extendedObject = oldExtendedObject;
        oldExtendedObject = tempExtendedObject;

        ExtensionPoint tempExtensionPoint = extensionPoint;
        extensionPoint = oldExtensionPoint;
        oldExtensionPoint = tempExtensionPoint;

        Object tempExtensionPointData = extensionPointData;
        extensionPointData = oldExtensionPointData;
        oldExtensionPointData = tempExtensionPointData;

    }

    /**
     * Set the new values
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute()
    {
        setValues();
    }

    /**
     * Set the new values
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo()
    {
        switchValues();
        setValues();
    }

    /**
     * Set the old values
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo()
    {
        switchValues();
        setValues();
    }

}
