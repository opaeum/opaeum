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

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The section for the type property of the Property associated with an Association Object.
 * 
 * Creation 10 avr. 2006
 * 
 * @author jlescot
 */
public abstract class AbstractClassifierSection extends AbstractTabbedPropertySection
{
    private Text classifierText;

    private CLabel classifierLabel;

    // /**
    // * @see
    // org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
    // org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
    // */
    // public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage)
    // {
    // super.createControls(parent, tabbedPropertySheetPage);
    // Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    // FormData data;
    //
    // CLabel classifierLabel = getWidgetFactory().createCLabel(composite, getLabelText());
    //
    // FontData fontData = classifierLabel.getFont().getFontData()[0];
    // if (fontData != null)
    // {
    // fontData.setStyle(SWT.BOLD);
    // FontData[] fontDataList = new FontData[1];
    // fontDataList[0] = fontData;
    // JFaceResources.getFontRegistry().put(StringConverter.asString(fontData), fontDataList);
    // // the get on a font always return a font even if this font is not
    // // registered
    // Font font = JFaceResources.getFontRegistry().get(StringConverter.asString(fontData));
    // classifierLabel.setFont(font);
    // }
    // classifierText = getWidgetFactory().createText(composite, "", SWT.READ_ONLY);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(classifierText, -ITabbedPropertyConstants.HSPACE);
    // data.top = new FormAttachment(classifierText, 0, SWT.CENTER);
    // classifierLabel.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[] {getLabelText()}));
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
    // classifierText.setLayoutData(data);
    // }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        classifierLabel = getWidgetFactory().createCLabel(composite, getLabelText());

        FontData fontData = classifierLabel.getFont().getFontData()[0];
        if (fontData != null)
        {
            fontData.setStyle(SWT.BOLD);
            FontData[] fontDataList = new FontData[1];
            fontDataList[0] = fontData;
            JFaceResources.getFontRegistry().put(StringConverter.asString(fontData), fontDataList);
            // the get on a font always return a font even if this font is not
            // registered
            Font font = JFaceResources.getFontRegistry().get(StringConverter.asString(fontData));
            classifierLabel.setFont(font);
        }
        classifierText = getWidgetFactory().createText(composite, "", SWT.READ_ONLY);
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(classifierText, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(classifierText, 0, SWT.CENTER);
        classifierLabel.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[] {getLabelText()}));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(classifierLabel, 0, SWT.CENTER);
        classifierText.setLayoutData(data);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    public void refresh()
    {
        super.refresh();
        classifierText.setText(getClassifierText());
    }
    
    

    @Override
    protected void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (classifierText != null)
        {
            classifierText.setEnabled(enabled);
        }
    }

    /**
     * Get the text of the Classifier associated with the Property
     * 
     * @return String
     */
    protected abstract String getClassifierText();

}