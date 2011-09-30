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

import java.util.regex.Pattern;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.editor.properties.TextChangeHelper;

/**
 * Creation 9 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class LiteralIntegerComposite extends Composite
{
    /** Predefined string pattern value for numerics and absloute with '-' : -25 */
    public static final String ABS_NUMERICS_PATTERN = "^[-\\d][\\d]*"; //$NON-NLS-1$   

    /** The Pattern used to check an Integer value */
    public static final Pattern INTEGER_PATTERN = Pattern.compile(ABS_NUMERICS_PATTERN);

    private LiteralInteger literalInteger;

    private Text text;

    /** A helper to listen for events that indicate that a text field has been changed. */
    private TextChangeHelper listener;

    /**
     * Constructor
     * 
     * @param widgetFactory
     * @param parent The parent Composite
     * @param literalInt The model object
     * @param style the style of the composite
     * @param editingDomain
     */
    public LiteralIntegerComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, final LiteralInteger literalInt, int style, final EditingDomain editingDomain)
    {
        super(parent, style);
        this.literalInteger = literalInt;

        setLayout(new GridLayout(2, false));
        setLayoutData(new GridData(GridData.FILL_BOTH));

        widgetFactory.createLabel(this, "Value:");
        text = widgetFactory.createText(this, literalInteger.stringValue(), SWT.FLAT | SWT.BORDER);
        text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        listener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                if (INTEGER_PATTERN.matcher(text.getText()).matches())
                {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, literalInt, UMLPackage.eINSTANCE.getLiteralInteger_Value(), new Integer(Integer.parseInt(text.getText()))));
                }
            }
        };
        listener.startListeningTo(text);
        listener.startListeningForEnter(text);

        text.addListener(SWT.Modify, new Listener()
        {
            public void handleEvent(Event e)
            {
                String value = text.getText();
                if (value == null || value.equals("") || INTEGER_PATTERN.matcher(value).matches())
                {
                    text.setBackground(null);
                    e.doit = true;
                }
                else
                {
                    text.setBackground(ModelerColorConstants.COLOR_ERROR);
                    e.doit = false;
                }
            }
        });
    }

}
