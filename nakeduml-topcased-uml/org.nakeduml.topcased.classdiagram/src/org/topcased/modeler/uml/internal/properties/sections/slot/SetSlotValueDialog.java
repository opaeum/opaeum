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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.uml.classdiagram.util.InstanceSpecificationHelper;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;

/**
 * A Dialog used to Edit the value of a Slot depending on the type of its Classifier
 * 
 * Creation 12 sept. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class SetSlotValueDialog extends Dialog
{
    /** The Slot that is valued */
    private Slot slot;

    // SWT Objects
    private Composite dialogComposite;

    private Text valueTxt;

    private Button valueBooleanTrue;

    private Button valueBooleanFalse;

    private CSingleObjectChooser valueObject;

    private TabbedPropertySheetWidgetFactory widgetFactory;

    /**
     * Constructor
     * 
     * @param parentShell the paren shell
     * @param factory the factory used to create the widgets
     * @param slot The Slot model object
     */
    public SetSlotValueDialog(Shell parentShell, TabbedPropertySheetWidgetFactory factory, Slot slot)
    {
        super(parentShell);
        this.slot = slot;
        this.widgetFactory = factory;

        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell)
    {
        newShell.setText("Set the ValueSpecification value");

        super.configureShell(newShell);
    }

    /**
     * Create the Dialog area
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        // Dialog
        dialogComposite = (Composite) super.createDialogArea(parent);
        GridData gdComp = new GridData(GridData.FILL_BOTH);
        dialogComposite.setLayoutData(gdComp);

        // A generic label
        Label lbl = new Label(dialogComposite, SWT.NONE);
        lbl.setText("Set the instance value of the ValueSpecification :");

        Type type = slot.getDefiningFeature().getType();

        if (type instanceof Class)
        {
            valueObject = new CSingleObjectChooser(dialogComposite, widgetFactory, SWT.NONE);
            valueObject.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            valueObject.setChoices(InstanceSpecificationHelper.getAvailableInstances(slot.getModel(), type).toArray());
            valueObject.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));
            if (slot.getValues().size() > 0 && slot.getValues().get(0) != null)
            {
                valueObject.setSelection(slot.getValues().get(0));
            }
        }
        else if (type instanceof PrimitiveType)
        {
            if ("boolean".equals(type.getName().toLowerCase()) || "bool".equals(type.getName().toLowerCase()))
            {
                valueBooleanTrue = new Button(dialogComposite, SWT.RADIO);
                valueBooleanTrue.setText("True");
                valueBooleanFalse = new Button(dialogComposite, SWT.RADIO);
                valueBooleanFalse.setText("False");

                if (slot.getValues().size() > 0 && slot.getValues().get(0).stringValue() != null)
                {
                    valueBooleanTrue.setSelection(Boolean.valueOf(slot.getValues().get(0).stringValue()).booleanValue());
                    valueBooleanFalse.setSelection(!Boolean.valueOf(slot.getValues().get(0).stringValue()).booleanValue());
                }
            }
            else if ("integer".equals(type.getName().toLowerCase()) || "int".equals(type.getName().toLowerCase()) || "string".equals(type.getName().toLowerCase()))
            {
                valueTxt = new Text(dialogComposite, SWT.BORDER);
                valueTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                if (slot.getValues().size() > 0 && slot.getValues().get(0).stringValue() != null)
                {
                    valueTxt.setText(slot.getValues().get(0).stringValue());
                }
            }
            else
            {
                Label infoLbl = new Label(dialogComposite, SWT.NONE);
                infoLbl.setText("This type has not been implemented yet.\nYou should use the Outline and the Advanced tab to set its value.");
                infoLbl.setForeground(ModelerColorConstants.red);
            }
        }
        else
        {
            // TODO Handle other types
            Label infoLbl = new Label(dialogComposite, SWT.NONE);
            infoLbl.setText("This type has not been implemented yet. You should use the Outline and the Advanced tab to set its value.");
            infoLbl.setForeground(ModelerColorConstants.red);
        }

        return dialogComposite;
    }

    /**
     * Update the ValueSpecification depending on the type of the Feature associated with the Slot. When the
     * ValueSpecification does not exist, a new one is created.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed()
    {
        Type type = slot.getDefiningFeature().getType();

        if (type instanceof Class)
        {
            if (slot.getValues().size() > 0 && slot.getValues().get(0) instanceof InstanceValue)
            {
                ((InstanceValue) slot.getValues().get(0)).setInstance((InstanceSpecification) valueObject.getSelection());
            }
            else
            {
                InstanceValue instValue = UMLFactory.eINSTANCE.createInstanceValue();
                instValue.setInstance((InstanceSpecification) valueObject.getSelection());
                slot.getValues().add(instValue);
            }
        }
        else if (type instanceof PrimitiveType)
        {
            if ("boolean".equals(type.getName().toLowerCase()) || "bool".equals(type.getName().toLowerCase()))
            {
                if (slot.getValues().size() > 0 && slot.getValues().get(0) instanceof LiteralBoolean)
                {
                    ((LiteralBoolean) slot.getValues().get(0)).setValue(valueBooleanTrue.getSelection());
                }
                else
                {
                    LiteralBoolean bool = UMLFactory.eINSTANCE.createLiteralBoolean();
                    bool.setValue(valueBooleanTrue.getSelection());
                    slot.getValues().add(0, bool);
                }
            }
            else if ("integer".equals(type.getName().toLowerCase()) || "int".equals(type.getName().toLowerCase()))
            {
                if (slot.getValues().size() > 0 && slot.getValues().get(0) instanceof LiteralInteger)
                {
                    ((LiteralInteger) slot.getValues().get(0)).setValue(Integer.parseInt(valueTxt.getText()));
                }
                else
                {
                    LiteralInteger intg = UMLFactory.eINSTANCE.createLiteralInteger();
                    intg.setValue(Integer.parseInt(valueTxt.getText()));
                    slot.getValues().add(0, intg);
                }
            }
            else if ("string".equals(type.getName().toLowerCase()))
            {
                if (slot.getValues().size() > 0 && slot.getValues().get(0) instanceof LiteralString)
                {
                    ((LiteralString) slot.getValues().get(0)).setValue(valueTxt.getText());
                }
                else
                {
                    LiteralString str = UMLFactory.eINSTANCE.createLiteralString();
                    str.setValue(valueTxt.getText());
                    slot.getValues().add(0, str);
                }
            }
        }
        super.okPressed();
    }

}
