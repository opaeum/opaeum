/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.properties.sections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.uml.internal.properties.sections.composites.LiteralBooleanComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.LiteralIntegerComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.LiteralStringComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.LiteralUnlimitedNaturalComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.OpaqueExpressionComposite;
import org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection;

/**
 * An abstract section used to create and edit a ValueSpecification. Depending on the type of the current
 * ValueSpecification, the composite used to edit its contents is changed.
 * 
 * Creation 26 oct. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public abstract class AbstractValueSpecificationSection extends AbstractDetailedObjectPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection#getDetailsComposite()
     */
    protected Composite getDetailsComposite()
    {
        return new ValueSpecificationCompositeSwitch().doSwitch(getRelatedEObject());
    }

    /**
     * An internal Switch used to get initialize widgets depending on the ValueSpecification type.
     */
    private class ValueSpecificationCompositeSwitch extends UMLSwitch<Composite>
    {
        /**
         * Constructor
         */
        public ValueSpecificationCompositeSwitch()
        {
            super();
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseLiteralBoolean(org.eclipse.uml2.uml.LiteralBoolean)
         */
        public Composite caseLiteralBoolean(LiteralBoolean object)
        {
            return new LiteralBooleanComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseLiteralInteger(org.eclipse.uml2.uml.LiteralInteger)
         */
        public Composite caseLiteralInteger(LiteralInteger object)
        {
            return new LiteralIntegerComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseLiteralString(org.eclipse.uml2.uml.LiteralString)
         */
        public Composite caseLiteralString(LiteralString object)
        {
            return new LiteralStringComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseLiteralUnlimitedNatural(org.eclipse.uml2.uml.LiteralUnlimitedNatural)
         */
        public Composite caseLiteralUnlimitedNatural(LiteralUnlimitedNatural object)
        {
            return new LiteralUnlimitedNaturalComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseOpaqueExpression(org.eclipse.uml2.uml.OpaqueExpression)
         */
        public Composite caseOpaqueExpression(OpaqueExpression object)
        {
            return new OpaqueExpressionComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }
    }
}
