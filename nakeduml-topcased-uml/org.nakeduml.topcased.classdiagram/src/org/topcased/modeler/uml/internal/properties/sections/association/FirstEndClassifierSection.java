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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.TypedElement;

/**
 * The section for the type property of the first end Property associated with an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public class FirstEndClassifierSection extends AbstractClassifierSection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractClassifierSection#getClassifierText()
     */
    protected String getClassifierText()
    {
        if (((TypedElement) ((Association) getEObject()).getMemberEnds().get(0)).getType() != null)
        {
            return ((TypedElement) ((Association) getEObject()).getMemberEnds().get(0)).getType().getName();
        }

        return "";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return null;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Classifier:";
    }

}