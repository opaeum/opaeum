/*****************************************************************************
 * 
 * StereotypeMetaclassSection.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Thibault Landré (Atos Origin) thibault.landre@atosorigin.com - Initial API and Implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.stereotype;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.uml.profilediagram.edit.StereotypeEditPart;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;

/**
 * The Class StereotypeMetaclassSection.
 */
public class StereotypeMetaclassSection extends AbstractTextPropertySection
{

    /** The stereo. */
    private Stereotype stereo;

    /**
     * Sets the input.
     * 
     * @param part the part
     * @param selection the selection
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        if (input instanceof StereotypeEditPart)
        {
            StereotypeEditPart editPart = (StereotypeEditPart) input;
            stereo = (Stereotype) editPart.getEObject();
        }
        // Fix #1930: Missing edition from the outline
        else if (input instanceof Stereotype)
        {
            stereo = (Stereotype) input;
        }
    }

    /**
     * Creates the controls.
     * 
     * @param parent the parent
     * @param tabbedPropertySheetPage the tabbed property sheet page
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage)
    {
        super.createControls(parent, tabbedPropertySheetPage);
        getText().setEnabled(false);
    }

    /**
     * Gets the metaclass text.
     * 
     * @return the metaclass text to display
     */
    private String getMetaclassText()
    {
        String metaclasses = "";
        boolean first = true;
        for (Property property : stereo.getOwnedAttributes())
        {

            if (property.getAssociation() instanceof Extension && property.getType() != null && property.getType() instanceof org.eclipse.uml2.uml.Class)
            {
                if (first)
                {
                    first = false;
                }
                else
                {
                    metaclasses += ", ";
                }
                metaclasses += ((org.eclipse.uml2.uml.Class) property.getType()).getName();
            }
        }
        return metaclasses;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeatureAsString()
     */
    @Override
    protected String getFeatureAsString()
    {
        return getMetaclassText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getNewFeatureValue(java.lang.String)
     */
    @Override
    protected Object getNewFeatureValue(String newText)
    {
        return newText;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getOldFeatureValue()
     */
    @Override
    protected Object getOldFeatureValue()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.tabbedproperties.sections.AbstractTextPropertySection#verifyField(org.eclipse.swt.widgets.Event)
     */
    @Override
    protected void verifyField(Event e)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    @Override
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getClassifier_Attribute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText()
    {
        return "Metaclass :";
    }

}
