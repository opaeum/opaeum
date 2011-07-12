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

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection;

/**
 * The section for the visibility property of a Property associated with an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public abstract class AbstractVisibilitySection extends AbstractEnumerationPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Property Visibility:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getNamedElement_Visibility();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getEnumerationFeatureValues()
     */
    protected String[] getEnumerationFeatureValues()
    {
        List<VisibilityKind> values = VisibilityKind.VALUES;
        String[] ret = new String[values.size()];
        for (int i = 0; i < values.size(); i++)
        {
            ret[i] = values.get(i).getName();
        }
        return ret;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText()
    {
        return getProperty((Association) getEObject()).getVisibility().getName();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index)
    {
        return VisibilityKind.get(index);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createCommand(java.lang.Object,
     *      java.lang.Object)
     */
    protected void createCommand(Object oldValue, Object newValue)
    {
        boolean equals = oldValue == null ? false : oldValue.equals(newValue);
        if (!equals)
        {
            EditingDomain editingDomain = getEditingDomain();
            Object value = newValue;
            if (getEObjectList().size() == 1)
            {
                // apply the property change to single selected object
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getProperty((Association) getEObject()), getFeature(), value));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                // apply the property change to all selected elements
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, getProperty((Association) nextObject), getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
     */
    protected Object getOldFeatureValue()
    {
        return getProperty((Association) getEObject()).getVisibility();
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);

}