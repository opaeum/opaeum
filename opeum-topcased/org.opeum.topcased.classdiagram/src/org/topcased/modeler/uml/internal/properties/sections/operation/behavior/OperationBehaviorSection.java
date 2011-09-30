/******************************************************************************************
 * Copyright (c) 2007 Communication & System (C-S).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Sebastien Gabel (CS) 
 *    
 *******************************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation.behavior;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Operation;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The section assumes the creation of a Behavior related to an Operation Object.
 * 
 * Creation 10 april 2007
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * 
 */
public class OperationBehaviorSection extends AbstractTabbedPropertySection
{
    private OperationBehaviorComposite operationBehaviorComposite;

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        operationBehaviorComposite = new OperationBehaviorComposite(composite, SWT.NONE, getWidgetFactory());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setEnabled(boolean)
     */
    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (operationBehaviorComposite != null)
        {
            operationBehaviorComposite.setEnabled(enabled);
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        // FIXME : we placed this test to avoid the two time refresh implied by the framework.....weird !
        // earlier in the stack trace, I noticed two recorded listeners having the same attributes. So, they are clearly
        // both notified implying this strange behavior.
        if (operationBehaviorComposite.getOperation() != getEObject())
        {
            MixedEditDomain mixedEditDomain = (MixedEditDomain) getPart().getAdapter(MixedEditDomain.class);
            operationBehaviorComposite.setMixedEditDomain(mixedEditDomain);
            operationBehaviorComposite.setOperation((Operation) getEObject());
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData(SWT.DEFAULT, SWT.DEFAULT);
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        data.bottom = new FormAttachment(100, 0);
        operationBehaviorComposite.setLayoutData(data);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        operationBehaviorComposite.setLayout(layout);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    public boolean shouldUseExtraSpace()
    {
        return true;
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
        return null;
    }

}
