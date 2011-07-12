/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.uml.internal.properties.sections.multiplicityelement.MultiplicityElementCardinalitySection;

/**
 * The section that manage the cardinality of an Association End element.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public abstract class AbstractCardinalitySection extends MultiplicityElementCardinalitySection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.multiplicityelement.MultiplicityElementCardinalitySection#refresh()
     */
    public void refresh()
    {
        if (!getLowerBoundText().isDisposed() && !getUpperBoundText().isDisposed())
        {
            getLowerBoundText().setText(StringConverter.asString(getProperty((Association) getEObject()).getLower()));
            int upper = getProperty((Association) getEObject()).getUpper();
            if (upper == -1)
            {
                getUpperBoundText().setText("*");
            }
            else
            {
                getUpperBoundText().setText(StringConverter.asString(upper));
            }
        }
    }

    /**
     * Handle the lower bound text modified event.
     */
    protected void handleLowerBoundModified()
    {
        String newText = getLowerBoundText().getText();
        boolean equals = isEqual(newText, getProperty((Association) getEObject()).getLower());
        if (!equals)
        {
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getProperty((Association) getEObject()).getLowerValue(), getLowerFeature(), value));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, getProperty((Association) nextObject).getLowerValue(), getLowerFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Handle the lower bound text modified event.
     */
    protected void handleUpperBoundModified()
    {
        String newText = getUpperBoundText().getText();
        if ("*".equals(newText))
        {
            newText = "-1";
        }
        boolean equals = isEqual(newText, getProperty((Association) getEObject()).getUpper());
        if (!equals)
        {
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getProperty((Association) getEObject()).getUpperValue(), getUpperFeature(), value));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, getProperty((Association) nextObject).getUpperValue(), getUpperFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);

}