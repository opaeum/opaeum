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
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection;

/**
 * A section for the direction property of a Parameter object.
 * 
 * Creation 5 avr. 2006
 * 
 * @author jlescot
 */
public class ParameterDirectionSection extends AbstractEnumerationPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getParameter_Direction();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText()
    {
        return ((Parameter) getEObject()).getDirection().getName();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getFeatureValue(int)
     */
    protected Object getFeatureValue(int index)
    {
        return ParameterDirectionKind.get(index);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Direction:";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getEnumerationFeatureValues()
     */
    protected String[] getEnumerationFeatureValues()
    {
        List<ParameterDirectionKind> values = ParameterDirectionKind.VALUES;
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
        return ((Parameter) getEObject()).getDirection();
    }
}