/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

/**
 * The section for the creation of Parameters of an Operation Object.
 * 
 * Creation 23 may 2006
 * 
 * @author jlescot
 */
public class OperationParametersSection extends AbstractTabbedPropertySection
{
    /** The widgets */
    private ParametersTableComposite table;

    private ParameterComposite details;

    private Group groupDetails;

    // /**
    // * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
    // * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
    // */
    // public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    // {
    // super.createControls(parent, aTabbedPropertySheetPage);
    // Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    // FormData data;
    //
    // table = new ParametersTableComposite(composite, SWT.NONE, getWidgetFactory())
    // {
    // public void updateSelectedParameter(Parameter newParameter)
    // {
    // details.setParameter(newParameter);
    // refresh();
    // }
    // };
    //
    // Group groupDetails = getWidgetFactory().createGroup(composite, "Details of the Parameter");
    // groupDetails.setLayout(new GridLayout());
    //
    // details = new ParameterComposite(groupDetails, SWT.NONE, getWidgetFactory());
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(0, 0);
    // table.setLayoutData(data);
    //
    // data = new FormData();
    // data.left = new FormAttachment(0, 0);
    // data.right = new FormAttachment(100, 0);
    // data.top = new FormAttachment(table, ITabbedPropertyConstants.VSPACE);
    // groupDetails.setLayoutData(data);
    //
    // }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    protected void createWidgets(Composite composite)
    {
        table = new ParametersTableComposite(composite, SWT.NONE, getWidgetFactory())
        {
            public void updateSelectedParameter(Parameter newParameter)
            {
                details.setParameter(newParameter);
                refresh();
            }
        };

        groupDetails = getWidgetFactory().createGroup(composite, "Details of the Parameter");
        groupDetails.setLayout(new GridLayout());

        details = new ParameterComposite(groupDetails, SWT.NONE, getWidgetFactory());
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#setSectionData(org.eclipse.swt.widgets.Composite)
     */
    protected void setSectionData(Composite composite)
    {
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        table.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(table, ITabbedPropertyConstants.VSPACE);
        groupDetails.setLayoutData(data);
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
     */
    public void refresh()
    {
        super.refresh();
        MixedEditDomain mixedEditDomain = (MixedEditDomain) getPart().getAdapter(MixedEditDomain.class);
        table.setMixedEditDomain(mixedEditDomain);
        table.setOperation((Operation) getEObject());
        details.setMixedEditDomain(mixedEditDomain);
        details.setParameter(null);
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