/*****************************************************************************
 * 
 * AbstractConnectorIsUniqueSection.java
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
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.topcased.modeler.uml.internal.properties.sections.multiplicityelement.MultiplicityElementIsUniqueSection;

/**
 * The section for the isUnique property of a Property associated with a Connector Object.
 */
public abstract class AbstractConnectorIsUniqueSection extends MultiplicityElementIsUniqueSection
{
    /**
     * Handle the checkbutton modified event.
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#handleCheckButtonModified()
     */
    protected void handleCheckButtonModified()
    {
        EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
        Object value = new Boolean(getCheckButton().getSelection());
        if (getEObjectList().size() == 1)
        {
            /* apply the property change to single selected object */
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getEnd((Connector) getEObject()), getFeature(), value));
        }
        else
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            /* apply the property change to all selected elements */
            for (EObject nextObject : getEObjectList())
            {
                compoundCommand.append(SetCommand.create(editingDomain, getEnd((Connector) nextObject), getFeature(), value));
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getFeatureValue()
     */
    protected boolean getFeatureValue()
    {
        if (getEnd((Connector) getEObject()) != null)
        {
        	getCheckButton().setVisible(true);
        	return getEnd((Connector) getEObject()).isUnique();
        }
        else
        {
            getCheckButton().setVisible(false);
            return false;
        }

    }

    /**
     * Get the property connector with the section
     * 
     * @param connector the Connector model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract ConnectorEnd getEnd(Connector con);

}
