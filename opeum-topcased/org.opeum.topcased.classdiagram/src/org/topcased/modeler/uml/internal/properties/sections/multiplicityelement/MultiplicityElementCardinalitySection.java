/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.multiplicityelement;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.properties.TextChangeHelper;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The section that manage the cardinality (lowerBound and UpperBound) of a MultiplicityElement.
 * 
 * Creation 16 févr. 07
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class MultiplicityElementCardinalitySection extends AbstractTabbedPropertySection
{
    /** The text control for the lower bound. */
    private Text lowerBoundText;

    /** The text control for the upper bound. */
    private Text upperBoundText;

    /**
     * A helper to listen for events that indicate that a text field has been changed.
     */
    private TextChangeHelper lowerBoundListener;

    /**
     * A helper to listen for events that indicate that a text field has been changed.
     */
    private TextChangeHelper upperBoundListener;

    private Group cardinalityGroup;

    private CLabel lowerBoundLabel;

    private CLabel upperBoundLabel;

    // /**
    // * @see
    // org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createControls(org.eclipse.swt.widgets.
    // Composite,
    // * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
    // */
    // public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    // {
    // final String lowerLbl = "Lower Bound:";
    // final String upperLbl = "Upper Bound:";
    //
    // super.createControls(parent, aTabbedPropertySheetPage);
    // Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    // FormData data;

    // Group cardinalityGroup = getWidgetFactory().createGroup(composite, "Cardinality");
    // CLabel lowerBoundLabel = getWidgetFactory().createCLabel(cardinalityGroup, lowerLbl);
    // lowerBoundText = getWidgetFactory().createText(cardinalityGroup, "", SWT.BORDER | SWT.FLAT);
    // getWidgetFactory().adapt(lowerBoundText, true, true);
    // CLabel upperBoundLabel = getWidgetFactory().createCLabel(cardinalityGroup, upperLbl);
    // upperBoundText = getWidgetFactory().createText(cardinalityGroup, "", SWT.BORDER | SWT.FLAT);
    // getWidgetFactory().adapt(upperBoundText, true, true);

    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
    // cardinalityGroup.setLayoutData(data);
    //
    // FormLayout layout = new FormLayout();
    // layout.marginWidth = ITabbedPropertyConstants.HSPACE;
    // layout.marginHeight = ITabbedPropertyConstants.VSPACE;
    // layout.spacing = ITabbedPropertyConstants.VMARGIN;
    // cardinalityGroup.setLayout(layout);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(lowerBoundText, -ITabbedPropertyConstants.HSPACE);
    // data.top = new FormAttachment(lowerBoundText, 0, SWT.CENTER);
    // lowerBoundLabel.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, getStandardLabelWidth(cardinalityGroup, new String[] {lowerLbl}));
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(lowerBoundLabel, 0, SWT.CENTER);
    // lowerBoundText.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(upperBoundText, -ITabbedPropertyConstants.HSPACE);
    // data.top = new FormAttachment(lowerBoundLabel, ITabbedPropertyConstants.VSPACE);
    // upperBoundLabel.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, getStandardLabelWidth(cardinalityGroup, new String[] {upperLbl}));
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(upperBoundLabel, 0, SWT.CENTER);
    // upperBoundText.setLayoutData(data);

    // lowerBoundListener = new TextChangeHelper()
    // {
    // public void textChanged(Control control)
    // {
    // handleLowerBoundModified();
    // }
    // };
    // lowerBoundListener.startListeningTo(lowerBoundText);
    // lowerBoundListener.startListeningForEnter(lowerBoundText);
    //
    // upperBoundListener = new TextChangeHelper()
    // {
    // public void textChanged(Control control)
    // {
    // handleUpperBoundModified();
    // }
    // };
    // upperBoundListener.startListeningTo(upperBoundText);
    // upperBoundListener.startListeningForEnter(upperBoundText);
    // }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        cardinalityGroup = getWidgetFactory().createGroup(composite, "Multiplicity");
        lowerBoundLabel = getWidgetFactory().createCLabel(cardinalityGroup, getLowerBoundLabel());
        lowerBoundText = getWidgetFactory().createText(cardinalityGroup, "", SWT.BORDER | SWT.FLAT);
        getWidgetFactory().adapt(lowerBoundText, true, true);
        upperBoundLabel = getWidgetFactory().createCLabel(cardinalityGroup, getUpperBoundLabel());
        upperBoundText = getWidgetFactory().createText(cardinalityGroup, "", SWT.BORDER | SWT.FLAT);
        getWidgetFactory().adapt(upperBoundText, true, true);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        cardinalityGroup.setLayoutData(data);

        FormLayout layout = new FormLayout();
        layout.marginWidth = ITabbedPropertyConstants.HSPACE;
        layout.marginHeight = ITabbedPropertyConstants.VSPACE;
        layout.spacing = ITabbedPropertyConstants.VMARGIN;
        cardinalityGroup.setLayout(layout);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(lowerBoundText, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(lowerBoundText, 0, SWT.CENTER);
        lowerBoundLabel.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, getStandardLabelWidth(cardinalityGroup, new String[] {getLowerBoundLabel()}));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(lowerBoundLabel, 0, SWT.CENTER);
        lowerBoundText.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(upperBoundText, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(lowerBoundLabel, ITabbedPropertyConstants.VSPACE);
        upperBoundLabel.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, getStandardLabelWidth(cardinalityGroup, new String[] {getUpperBoundLabel()}));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(upperBoundLabel, 0, SWT.CENTER);
        upperBoundText.setLayoutData(data);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#hookListeners()
     */
    protected void hookListeners()
    {
        lowerBoundListener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                handleLowerBoundModified();
            }
        };
        lowerBoundListener.startListeningTo(lowerBoundText);
        lowerBoundListener.startListeningForEnter(lowerBoundText);

        upperBoundListener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                handleUpperBoundModified();
            }
        };
        upperBoundListener.startListeningTo(upperBoundText);
        upperBoundListener.startListeningForEnter(upperBoundText);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    public void refresh()
    {
        super.refresh();
        if (!lowerBoundText.isDisposed() && !upperBoundText.isDisposed())
        {
            lowerBoundText.setText(StringConverter.asString(((MultiplicityElement) getEObject()).getLower()));
            int upper = ((MultiplicityElement) getEObject()).getUpper();
            if (upper == -1)
            {
                upperBoundText.setText("*");
            }
            else
            {
                upperBoundText.setText(StringConverter.asString(upper));
            }
        }
    }
    
    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (lowerBoundText != null)
        {
            lowerBoundText.setEnabled(enabled);
        }
        if (upperBoundText != null)
        {
            upperBoundText.setEnabled(enabled);
        }
    }

    /**
     * Handle the lower bound text modified event.
     */
    protected void handleLowerBoundModified()
    {
        String newText = lowerBoundText.getText();
        boolean equals = isEqual(newText, ((MultiplicityElement) getEObject()).getLower());
        if (!equals)
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                if (((MultiplicityElement) getEObject()).getLowerValue() == null)
                {
                    LiteralInteger newLowerValue = UMLFactory.eINSTANCE.createLiteralInteger();
                    compoundCommand.append(SetCommand.create(editingDomain, getEObject(), UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(), newLowerValue));
                    compoundCommand.append(SetCommand.create(editingDomain, newLowerValue, getLowerFeature(), value));
                }
                else
                {
                    compoundCommand.append(SetCommand.create(editingDomain, ((MultiplicityElement) getEObject()).getLowerValue(), getLowerFeature(), value));
                }
            }
            else
            {
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    if (((MultiplicityElement) nextObject).getLowerValue() == null)
                    {
                        LiteralInteger newLowerValue = UMLFactory.eINSTANCE.createLiteralInteger();
                        compoundCommand.append(SetCommand.create(editingDomain, nextObject, UMLPackage.eINSTANCE.getMultiplicityElement_LowerValue(), newLowerValue));
                        compoundCommand.append(SetCommand.create(editingDomain, newLowerValue, getLowerFeature(), value));
                    }
                    else
                    {
                        compoundCommand.append(SetCommand.create(editingDomain, ((MultiplicityElement) nextObject).getLowerValue(), getLowerFeature(), value));
                    }
                }
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }
    }

    /**
     * Handle the lower bound text modified event.
     */
    protected void handleUpperBoundModified()
    {
        String newText = upperBoundText.getText();
        if ("*".equals(newText))
        {
            newText = "-1";
        }
        boolean equals = isEqual(newText, ((MultiplicityElement) getEObject()).getUpper());
        if (!equals)
        {
            CompoundCommand compoundCommand = new CompoundCommand();
            EditingDomain editingDomain = (EditingDomain) getPart().getAdapter(EditingDomain.class);
            Object value = new Integer(Integer.parseInt(newText));
            if (getEObjectList().size() == 1)
            {
                /* apply the property change to single selected object */
                if (((MultiplicityElement) getEObject()).getUpperValue() == null)
                {
                    LiteralUnlimitedNatural newUpperValue = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
                    compoundCommand.append(SetCommand.create(editingDomain, getEObject(), UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(), newUpperValue));
                    compoundCommand.append(SetCommand.create(editingDomain, newUpperValue, getUpperFeature(), value));
                }
                else
                {
                    compoundCommand.append(SetCommand.create(editingDomain, ((MultiplicityElement) getEObject()).getUpperValue(), getUpperFeature(), value));
                }
            }
            else
            {
                /* apply the property change to all selected elements */
                for (EObject nextObject : getEObjectList())
                {
                    if (((MultiplicityElement) nextObject).getUpperValue() == null)
                    {
                        LiteralUnlimitedNatural newUpperValue = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
                        compoundCommand.append(SetCommand.create(editingDomain, nextObject, UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(), newUpperValue));
                        compoundCommand.append(SetCommand.create(editingDomain, newUpperValue, getUpperFeature(), value));
                    }
                    else
                    {
                        compoundCommand.append(SetCommand.create(editingDomain, ((MultiplicityElement) nextObject).getUpperValue(), getUpperFeature(), value));
                    }
                }
            }
            editingDomain.getCommandStack().execute(compoundCommand);
        }
    }

    /**
     * Determine if the provided string value is an equal representation of the current setting of the text property.
     * 
     * @param newText the new string value.
     * @param valueToCompare the int value to compare with the text value
     * @return <code>true</code> if the new string value is equal to the passed int value.
     */
    protected boolean isEqual(String newText, int valueToCompare)
    {
        try
        {
            Integer.parseInt(newText);
        }
        catch (NumberFormatException e)
        {
            refresh();
            return true;
        }
        Integer integer = new Integer(Integer.parseInt(newText));
        return new Integer(valueToCompare).equals(integer);
    }

    /**
     * Get the EAttribute for the lower bound object
     * 
     * @return the EAttribute
     */
    protected EAttribute getLowerFeature()
    {
        return UMLPackage.eINSTANCE.getLiteralInteger_Value();
    }

    /**
     * Get the EAttribute for the upper bound object
     * 
     * @return the EAttribute
     */
    protected EAttribute getUpperFeature()
    {
        return UMLPackage.eINSTANCE.getLiteralUnlimitedNatural_Value();
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

    /**
     * Get the label for the lower bound text field for the section.
     * 
     * @return the label for the lower bound text field.
     */
    protected String getLowerBoundLabel()
    {
        return "Lower Bound:";
    }

    /**
     * Get the label for the upper bound text field for the section.
     * 
     * @return the label for the upper bound text field.
     */
    protected String getUpperBoundLabel()
    {
        return "Upper Bound:";
    }

    /**
     * Return the Text used to edit the lowerBound value of the Multiplicity Element
     * 
     * @return Text
     */
    protected Text getLowerBoundText()
    {
        return lowerBoundText;
    }

    /**
     * Return the Text used to edit the upperBound value of the Multiplicity Element
     * 
     * @return Text
     */
    protected Text getUpperBoundText()
    {
        return upperBoundText;
    }

    /**
     * active or disable the cardinality group.
     * 
     */

    protected void setcardinalityGroupVisible(boolean val)
    {
        cardinalityGroup.setVisible(val);
    }
}