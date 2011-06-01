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
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.uml.internal.properties.sections.structuralfeature.StructuralFeatureIsReadOnlySection;

/**
 * The section for the isReadOnly property of a Property associated with an Association Object.
 * 
 * Creation 26 févr. 07
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public abstract class AbstractIsReadOnlySection extends StructuralFeatureIsReadOnlySection
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
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getProperty((Association) getEObject()), getFeature(), value));
        }
        else
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            /* apply the property change to all selected elements */
            for (EObject nextObject : getEObjectList())
            {
                compoundCommand.append(SetCommand.create(editingDomain, getProperty((Association) nextObject), getFeature(), value));
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getFeatureValue()
     */
    protected boolean getFeatureValue()
    {
        return getProperty((Association) getEObject()).isReadOnly();
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);

}