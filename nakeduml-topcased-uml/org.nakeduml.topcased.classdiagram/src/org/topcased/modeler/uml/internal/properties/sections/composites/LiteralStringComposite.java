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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.properties.TextChangeHelper;

/**
 * Creation 26 oct. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class LiteralStringComposite extends Composite
{
    private LiteralString literalString;

    private Text text;

    /** A helper to listen for events that indicate that a text field has been changed. */
    private TextChangeHelper listener;

    /**
     * Constructor
     * 
     * @param widgetFactory
     * @param parent The parent Composite
     * @param literalStr The model object
     * @param style the style of the composite
     * @param editingDomain
     */
    public LiteralStringComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, final LiteralString literalStr, int style, final EditingDomain editingDomain)
    {
        super(parent, style);
        this.literalString = literalStr;

        setLayout(new GridLayout(2, false));
        setLayoutData(new GridData(GridData.FILL_BOTH));

        widgetFactory.createLabel(this, "Value:");
        text = widgetFactory.createText(this, literalString.stringValue(), SWT.FLAT | SWT.BORDER);
        text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        listener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, literalStr, UMLPackage.eINSTANCE.getLiteralString_Value(), text.getText()));
            }
        };
        listener.startListeningTo(text);
        listener.startListeningForEnter(text);
    }

}
