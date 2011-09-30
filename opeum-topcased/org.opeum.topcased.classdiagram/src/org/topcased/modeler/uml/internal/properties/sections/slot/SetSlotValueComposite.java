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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * A Text widget and a Button that allow you to set the Value of the Slot in an InstanceSpecification
 * 
 * Creation 12 sept. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class SetSlotValueComposite extends Composite
{
    // The SWT widgets
    private Text valueTxt;

    private Button setBt;

    // The Slot model object
    private Slot slot;

    private ValueSpecification valueSpecification;

    private TabbedPropertySheetWidgetFactory widgetFactory;

    /**
     * Constructor
     * 
     * @param parent the parent Composite
     * @param factory the factory necessary to create the widget
     * @param style
     */
    public SetSlotValueComposite(Composite parent, TabbedPropertySheetWidgetFactory factory, int style)
    {
        super(parent, style);

        this.widgetFactory = factory;
        createContents(this);
        widgetFactory.adapt(this);
        hookListeners();
    }

    /**
     * Creates the UI
     * 
     * @param parent this widget
     */
    protected void createContents(Composite parent)
    {
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        parent.setLayout(layout);

        valueTxt = widgetFactory.createText(parent, "", SWT.FLAT | SWT.BORDER);
        valueTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        valueTxt.setEnabled(false);

        setBt = widgetFactory.createButton(parent, "Set", SWT.PUSH);
    }

    /**
     * Adds the listeners on the widgets
     */
    protected void hookListeners()
    {
        setBt.addSelectionListener(new SelectionAdapter()
        {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e)
            {
                handleSetValue();
            }
        });
    }

    /**
     * Set the current active Slot
     * 
     * @param slot
     */
    public void setSlot(Slot slot)
    {
        this.slot = slot;
    }

    /**
     * Open the dialog to choose in the searchable list
     */
    private void handleSetValue()
    {
        if (slot.getDefiningFeature() != null)
        {
            SetSlotValueDialog dialog = new SetSlotValueDialog(getShell(), widgetFactory, slot);

            if (dialog.open() == Window.OK)
            {
                ValueSpecification valueSpec = getValueSpecification();
                setSelection(valueSpec);

                Event e = new Event();
                notifyListeners(SWT.Selection, e);
            }
        }
        else
        {
            UMLPlugin.log("You should first select a Feature before trying to set its value", IStatus.INFO);
        }
    }

    /**
     * Set the ValueSpecification
     * 
     * @param valueSpec the selected object
     */
    public void setSelection(ValueSpecification valueSpec)
    {
        valueSpecification = valueSpec;
        if (valueSpecification != null && valueSpecification.stringValue() != null)
        {
            valueTxt.setText(valueSpecification.stringValue());
        }
        else if (valueSpecification instanceof InstanceValue)
        {
            ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
            valueTxt.setText(labelProvider.getText(valueSpecification));
        }
        else
        {
            valueTxt.setText("");
        }
    }

    /**
     * Returns the new Requirement
     * 
     * @return the string representing the new requirement
     */
    public ValueSpecification getValueSpecification()
    {
        if (slot.getValues().size() > 0)
        {
            return slot.getValues().get(0);
        }
        return null;
    }

    /**
     * Add a SelectionListener on both the CCombo and the Button
     * 
     * @param listener
     */
    public void addSelectionListener(SelectionListener listener)
    {
        if (listener == null)
        {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        }
        TypedListener typedListener = new TypedListener(listener);
        addListener(SWT.Selection, typedListener);
    }

    /**
     * Remove the SelectionListener of the CCombo and the Button
     * 
     * @param listener
     */
    public void removeSelectionListener(SelectionListener listener)
    {
        if (listener == null)
        {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        }
        removeListener(SWT.Selection, listener);
    }
}
