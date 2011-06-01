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

import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;

/**
 * The section for the aggregation property of the first end Property of an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public class FirstEndAggregationSection extends AbstractAggregationSection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractAggregationSection#getProperty(org.eclipse.uml2.uml.Association)
     */
    protected Property getProperty(Association association)
    {
        if (association.getMemberEnds() != null && association.getMemberEnds().size() > 0)
        {
            return association.getMemberEnds().get(0);
        }
        return null;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractEnumerationPropertySection#getOldFeatureValue()
     */
    protected Object getOldFeatureValue()
    {
        return null;
    }
    
    
    /** 
     * Check if the other end have an aggregation kind define. If there is one then disable the combo.
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractAggregationSection#refresh()
     */
    @Override
    public void refresh() 
    {
    	super.refresh();
    	//FIX #735
    	AssociationHelper associationHelper = new AssociationHelper((Association)getEObject());
    	AggregationKind aggregationKind = associationHelper.getAssociationSecondEndAggregationKind();
    	
    	if(aggregationKind.NONE_LITERAL.equals(aggregationKind))
    	{
    		getCombo().setEnabled(true);
    	}
    	else
    	{
    		getCombo().setEnabled(false);
    	}
    	//ENDFIX #735
    }
    
}