/***********************************************************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.uml.properties.sections.AbstractValueSpecificationSection;

/**
 * The Section used to edit the 'Specification' associated with a {@link InstanceSpecification}.<br>
 * 
 * Creation 09 november 2010<br>
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since Topcased 4.2.0
 */
public class InstanceSpecificationSpecificationSection extends AbstractValueSpecificationSection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    @Override
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getInstanceSpecification_Specification();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText()
    {
        return "Specification:";
    }

    /**
     * @see org.topcased.modeler.uml.properties.sections.AbstractValueSpecificationSection#getFeatureAsText()
     */
    @Override
    protected String getFeatureAsText()
    {
        if (((InstanceSpecification) getEObject()).getSpecification() != null)
        {
            String featureTextValue = ((InstanceSpecification) getEObject()).getSpecification().stringValue();
            return featureTextValue == null ? "" : featureTextValue;
        }
        return "";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection#getRelatedEObject()
     */
    @Override
    protected EObject getRelatedEObject()
    {
        return ((InstanceSpecification) getEObject()).getSpecification();
    }
}
