/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection;

/**
 * A section for the visibility property of a NamedElement object.
 * 
 * Creation 5 avr. 2006
 * 
 * @author jlescot
 */
public class NamedElementVisibilitySection extends AbstractEnumerationPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getNamedElement_Visibility();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText()
    {
        if (getEObject() != null)
        {
            return ((NamedElement) getEObject()).getVisibility().getName();
        }
        return "";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index)
    {
        return VisibilityKind.get(index);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Visibility:";
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
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
     */
    protected Object getOldFeatureValue()
    {
        return ((NamedElement) getEObject()).getVisibility();
    }
}