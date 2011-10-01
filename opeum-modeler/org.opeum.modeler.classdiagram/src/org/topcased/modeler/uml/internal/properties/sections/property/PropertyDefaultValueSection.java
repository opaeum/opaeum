/*****************************************************************************
 * Copyright (c) 2008; Atos Origin
 *
 * Contributors:
 *  Barraille Frederic; Atos Origin; frederic.barraille@atosorigin.com
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.uml.properties.sections.AbstractValueSpecificationSection;

/**
 * 
 * @author fbarrail
 * 
 */
public class PropertyDefaultValueSection extends AbstractValueSpecificationSection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getProperty_DefaultValue();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    protected String getLabelText()
    {
        return "Default Value:";
    }

    /**
     * @see org.topcased.modeler.uml.properties.sections.AbstractValueSpecificationSection#getFeatureAsText()
     */
    protected String getFeatureAsText()
    {
        if (((Property) getEObject()).getDefaultValue() != null)
        {
            String featureTextValue = ((Property) getEObject()).getDefaultValue().stringValue();
            return featureTextValue == null ? "" : featureTextValue;
        }
        return "";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection#getRelatedEObject()
     */
    protected EObject getRelatedEObject()
    {
        return ((Property) getEObject()).getDefaultValue();
    }
}
