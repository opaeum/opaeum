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
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.IntervalConstraint;
import org.eclipse.uml2.uml.TimeConstraint;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.uml.internal.properties.sections.composites.ConstraintComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.DurationConstraintComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.InteractionConstraintComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.IntervalConstraintComposite;
import org.topcased.modeler.uml.internal.properties.sections.composites.TimeConstraintComposite;
import org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection;

/**
 * An abstract section used to create and edit a Constraint. Depending on the type of the current Constraint the widgets
 * used to edit it are changed.
 * 
 * Creation 10 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public abstract class AbstractConstraintSection extends AbstractDetailedObjectPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection#getDetailsComposite()
     */
    protected Composite getDetailsComposite()
    {
        return new ConstraintCompositeSwitch().doSwitch(getRelatedEObject());
    }

    /**
     * An internal Switch used to get initialize widgets depending on the Constraint type.
     */
    private class ConstraintCompositeSwitch extends UMLSwitch<Composite>
    {
        /**
         * Constructor
         */
        public ConstraintCompositeSwitch()
        {
            super();
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseConstraint(org.eclipse.uml2.uml.Constraint)
         */
        public Composite caseConstraint(Constraint object)
        {
            return new ConstraintComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInteractionConstraint(org.eclipse.uml2.uml.InteractionConstraint)
         */
        public Composite caseInteractionConstraint(InteractionConstraint object)
        {
            return new InteractionConstraintComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseIntervalConstraint(org.eclipse.uml2.uml.IntervalConstraint)
         */
        public Composite caseIntervalConstraint(IntervalConstraint object)
        {
            return new IntervalConstraintComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDurationConstraint(org.eclipse.uml2.uml.DurationConstraint)
         */
        public Composite caseDurationConstraint(DurationConstraint object)
        {
            return new DurationConstraintComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseTimeConstraint(org.eclipse.uml2.uml.TimeConstraint)
         */
        public Composite caseTimeConstraint(TimeConstraint object)
        {
            return new TimeConstraintComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }
    }
}
