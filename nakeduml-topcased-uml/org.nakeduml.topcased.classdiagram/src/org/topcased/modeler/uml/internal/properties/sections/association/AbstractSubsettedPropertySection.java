/*******************************************************************************
 * Copyright (c) 2007 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 * Thibault Landré (Atos Origin) - add check property existence on removeListener() 
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.uml.internal.properties.sections.property.PropertySubsettedPropertySection;

/**
 * Creation 26 fÃ©vr. 07
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public abstract class AbstractSubsettedPropertySection extends PropertySubsettedPropertySection
{
    /**
     * The real input model object is the property
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractListPropertySection#refresh()
     */
    public void refresh()
    {
        getTable().setInput(getProperty((Association) getEObject()), getFeature());
        getTable().setEditingDomain(getEditingDomain());
        getTable().refresh();
    }

    /**
     * Removes the model listener from the Property
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#removeListener()
     */
    protected void removeListener()
    {
        if (getEObject() == null)
        {
            return;
        }
        
        if (getProperty((Association)getEObject())!= null)
        {
            if (getProperty((Association) getEObject()).eAdapters().contains(getModelListener()))
            {
                getProperty((Association) getEObject()).eAdapters().remove(getModelListener());
            }
        }
    }

    /**
     * Adds the model listener to the Property
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#addListener()
     */
    protected void addListener()
    {
        if (getEObject() == null)
        {
            return;
        }
        if (!getProperty((Association) getEObject()).eAdapters().contains(getModelListener()))
        {
            getProperty((Association) getEObject()).eAdapters().add(getModelListener());
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#handleModelChanged(org.eclipse.emf.common.notify.Notification)
     */
    protected void handleModelChanged(Notification msg)
    {
        Object notifier = msg.getNotifier();
        if (notifier.equals(getProperty((Association) getEObject())) && getFeature() != null)
        {
            if (msg.getFeatureID(getProperty((Association) getEObject()).getClass()) == getFeature().getFeatureID())
            {
                refresh();
            }
        }
    }

    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.property.PropertySubsettedPropertySection#getListValues()
     */
    protected Object getListValues()
    {
        return getProperty((Association) getEObject()).getSubsettedProperties();
    }

    /**
     * Get the property associated with the section
     * 
     * @param association the Association model object
     * @return the Property (first end or second end) or null if not found
     */
    protected abstract Property getProperty(Association association);
}
