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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;

/**
 * The section for the name property of a Property Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public abstract class AbstractRoleSection extends AbstractStringPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Role:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getNamedElement_Name();
    }

    /**
     * Handle the text modified event.
     */
    protected void handleTextModified()
    {
        String newText = getText().getText();
        if (!getFeatureAsString().equals(newText))
        {
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = getNewFeatureValue(newText);
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
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractStringPropertySection#getFeatureAsString()
     */
    protected String getFeatureAsString()
    {
        String string = getProperty((Association) getEObject()).getName();
        if (string == null)
        {
            return "";
        }
        return string;
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);

}