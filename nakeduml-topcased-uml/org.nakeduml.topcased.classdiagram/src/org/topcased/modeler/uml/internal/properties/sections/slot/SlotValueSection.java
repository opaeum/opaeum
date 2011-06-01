/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.slot;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The section for the value property of a Slot model object.
 * 
 * Creation 12 sept. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class SlotValueSection extends AbstractTabbedPropertySection
{
    /**
     * A boolean that store if refreshing is happening and no model modifications should be performed
     */
    private boolean isRefreshing = false;

    /**
     * The composite composed of a Text and a button used to set the Slot value
     */
    private SetSlotValueComposite setSlotValueComposite;

    /**
     * The section label;
     */
    private CLabel valueLabel;

    // /**
    // * @see
    // org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
    // * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
    // */
    // public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage)
    // {
    // super.createControls(parent, tabbedPropertySheetPage);
    // Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    // FormData data;
    //
    // CLabel valueLabel = getWidgetFactory().createCLabel(composite, getLabelText());
    //
    // setSlotValueComposite = new SetSlotValueComposite(composite, getWidgetFactory(), SWT.NONE);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(setSlotValueComposite, -ITabbedPropertyConstants.HSPACE);
    // data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
    // valueLabel.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(valueLabel, ITabbedPropertyConstants.HSPACE);
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(valueLabel, 0, SWT.CENTER);
    // setSlotValueComposite.setLayoutData(data);
    //
    // hookListeners();
    // }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        valueLabel = getWidgetFactory().createCLabel(composite, getLabelText());

        setSlotValueComposite = new SetSlotValueComposite(composite, getWidgetFactory(), SWT.NONE);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(setSlotValueComposite, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        valueLabel.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(valueLabel, ITabbedPropertyConstants.HSPACE);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(valueLabel, 0, SWT.CENTER);
        setSlotValueComposite.setLayoutData(data);
    }

    /**
     * Adds the listeners on the widgets
     */
    protected void hookListeners()
    {
        setSlotValueComposite.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e)
            {
                handleValueChanged();
            }
        });
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    public void refresh()
    {
        super.refresh();
        isRefreshing = true;

        setSlotValueComposite.setSlot((Slot) getEObject());
        ValueSpecification valueSpec = null;
        if (((Slot) getEObject()).getValues().size() > 0)
        {
            valueSpec = ((Slot) getEObject()).getValues().get(0);
        }
        setSlotValueComposite.setSelection(valueSpec);

        isRefreshing = false;
    }

    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (setSlotValueComposite != null)
        {
            setSlotValueComposite.setEnabled(enabled);
        }
    }

    /**
     * Manage the change of the Value.
     */
    protected void handleValueChanged()
    {
        if (!isRefreshing)
        {
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);

            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, getEObject(), ((Slot) getEObject()).getValues(), getValueSpecification(), 0));
            }
            else
            {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, ((Slot) getEObject()).getValues(), getValueSpecification(), 0));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * Get the first ValueSpecification that is contained by the Slot.
     * 
     * @return the first ValueSpecification
     */
    protected ValueSpecification getValueSpecification()
    {
        if (((Slot) getEObject()).getValues() != null && ((Slot) getEObject()).getValues().size() > 0)
        {
            return ((Slot) getEObject()).getValues().get(0);
        }
        return null;
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
        return "Value:";
    }

}