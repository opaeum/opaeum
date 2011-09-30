/*******************************************************************************
 * Copyright (c) 2006 Anyware Technologies. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jacques Lescot (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.composites;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Creation 26 oct. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class LiteralBooleanComposite extends Composite
{
    private LiteralBoolean literalBoolean;

    private Button checkButton;

    /**
     * Constructor
     * 
     * @param widgetFactory The WidgetFactory that should be used to create widgets of that Composite
     * @param parent The parent Composite
     * @param literalBool The model object
     * @param style the style of the composite
     * @param editingDomain
     */
    public LiteralBooleanComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, final LiteralBoolean literalBool, int style, final EditingDomain editingDomain)
    {
        super(parent, style);
        this.literalBoolean = literalBool;

        setLayout(new GridLayout());
        setLayoutData(new GridData(GridData.FILL_BOTH));

        checkButton = widgetFactory.createButton(this, "isActive", SWT.CHECK);
        checkButton.setSelection(literalBoolean.booleanValue());
        checkButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Object value = Boolean.valueOf(checkButton.getSelection());
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, literalBool, UMLPackage.eINSTANCE.getLiteralBoolean_Value(), value));
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }

        });
    }

}
