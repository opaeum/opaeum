/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 * Thibault Landré (Atos Origin) - adding command to make the diagram dirty
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

/**
 * The section for the isNavigable property of a Property associated with an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public abstract class AbstractIsNavigableSection extends AbstractBooleanPropertySection
{
    /**
     * Handle the checkbutton modified event.
     */
    protected void handleCheckButtonModified()
    {
        EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
        if (getEObjectList().size() == 1)
        {
            /* apply the property change to single selected object */
            editingDomain.getCommandStack().execute(getNavigationCommand(editingDomain, (Association) getEObject()));

        }
        else
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            /* apply the property change to all selected elements */
            for (EObject nextObject : getEObjectList())
            {
                compoundCommand.append(getNavigationCommand(editingDomain, (Association) nextObject));
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }

    }

    /**
     * Get the command which modify the navigableOwnedEnd of an association
     * 
     * @param editingDomain the editingDomain
     * @param association the association to modify
     * @return the command to execute for the given association
     */
    private Command getNavigationCommand(EditingDomain editingDomain, Association association)
    {
        Command command = null;
        EList<Property> navList = new BasicEList<Property>();
        navList.addAll(association.getNavigableOwnedEnds());

        if (getCheckButton().getSelection())
        {
            if(association.getOwnedEnds().contains(getProperty(association)))
            {
                navList.add(getProperty(association));
                command = SetCommand.create(editingDomain, association, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), navList);
           }
        }
        else
        {
            if(association.getOwnedEnds().contains(getProperty(association)))
            {
                navList.remove(getProperty(association));
                command = SetCommand.create(editingDomain, association, UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), navList);
            }
        }
        return command;
    }
    
    /**
     * Override refresh to disable the isNavigable button when the association ends is contained by the classifier (always navigable in that case).
     */
    @Override
    public void refresh()
    {
        super.refresh();
        Association association = (Association)getEObject();
        if(association.getOwnedEnds().contains(getProperty(association)))
        {
            getCheckButton().setEnabled(true);
        }
        else 
        {
            getCheckButton().setEnabled(false);
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return null;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getFeatureValue()
     */
    protected boolean getFeatureValue()
    {
        return getProperty((Association) getEObject()).isNavigable();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "isNavigable";
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);

}