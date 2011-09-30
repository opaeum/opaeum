/*******************************************************************************
 * Copyright (c) 2008 Anyware Technologies. All rights reserved. This program
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
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.uml.internal.properties.sections.composites.BehaviorComposite;
import org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection;

/**
 * An abstract section used to create and edit a Behavior. Depending on the type of the current Behavior the widgets
 * used to edit it are changed.
 * 
 * Creation 05 feb. 08
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public abstract class AbstractBehaviorSection extends AbstractDetailedObjectPropertySection
{
    /**
     * @see org.topcased.tabbedproperties.sections.AbstractDetailedObjectPropertySection#getDetailsComposite()
     */
    protected Composite getDetailsComposite()
    {
        return new BehaviorCompositeSwitch().doSwitch(getRelatedEObject());
    }

    /**
     * An internal Switch used to get initialize widgets depending on the Constraint type.
     */
    private class BehaviorCompositeSwitch extends UMLSwitch<Composite>
    {
        /**
         * Constructor
         */
        public BehaviorCompositeSwitch()
        {
            super();
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseBehavior(org.eclipse.uml2.uml.Behavior)
         */
        public Composite caseBehavior(Behavior object)
        {
            return new BehaviorComposite(getWidgetFactory(), getGroupDetails(), object, SWT.NONE, getEditingDomain());
        }
    }
}
