/*****************************************************************************
 * 
 * AbstractConnectorCardinalitySection.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Guojun SONG (Atos Origin)  guojun.song@atosorigin.com - initial API and implementation
 *  Answer FR #964 The multiplicity of connector ends.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.uml.internal.properties.sections.multiplicityelement.MultiplicityElementCardinalitySection;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;

/**
 * 
 *
 */
public abstract class AbstractConnectorCardinalitySection extends MultiplicityElementCardinalitySection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.multiplicityelement.MultiplicityElementCardinalitySection#refresh()
     */
    public void refresh()
    {
        if (getEnd((Connector) getEObject()) == null)
        {
        	setcardinalityGroupVisible(false);
        }
        else
        { 
            setcardinalityGroupVisible(true);
            if (!getLowerBoundText().isDisposed() && !getUpperBoundText().isDisposed())
            {
                getLowerBoundText().setText(StringConverter.asString(getEnd((Connector) getEObject()).getLower()));
                int upper = getEnd((Connector) getEObject()).getUpper();
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

    }

    /**
     * Handle the lower bound text modified event.
     */
    protected void handleLowerBoundModified()
    {
        String newText = getLowerBoundText().getText();

        boolean equals = isEqual(newText, getEnd((Connector) getEObject()).getLower());
        if (!equals)
        {
            getEnd((Connector) getEObject()).setLower(1);
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getEnd((Connector) getEObject()).getLowerValue(), getLowerFeature(), value));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, getEnd((Connector) nextObject).getLowerValue(), getLowerFeature(), value));
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
        boolean equals = isEqual(newText, getEnd((Connector) getEObject()).getUpper());
        if (!equals)
        {
            getEnd((Connector) getEObject()).setUpper(1);
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getEnd((Connector) getEObject()).getUpperValue(), getUpperFeature(), value));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, getEnd((Connector) nextObject).getUpperValue(), getUpperFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Get the property connector end with the section
     * 
     * @param connector the Connector model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract ConnectorEnd getEnd(Connector con);

}
