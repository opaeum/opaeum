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
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.properties.TextChangeHelper;

/**
 * Creation 10 nov. 06
 * 
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class DurationConstraintComposite extends Composite
{
    private DurationConstraint modelObject;

    private Text text;

    /** A helper to listen for events that indicate that a text field has been changed. */
    private TextChangeHelper listener;

    /**
     * Constructor
     * 
     * @param widgetFactory
     * @param parent The parent Composite
     * @param modelObj The model object
     * @param style the style of the composite
     * @param editingDomain
     */
    public DurationConstraintComposite(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, final DurationConstraint modelObj, int style, final EditingDomain editingDomain)
    {
        super(parent, style);
        this.modelObject = modelObj;

        setLayout(new GridLayout(2, false));
        setLayoutData(new GridData(GridData.FILL_BOTH));

        widgetFactory.createLabel(this, "Name:");
        text = widgetFactory.createText(this, modelObject.getName(), SWT.FLAT | SWT.BORDER);
        text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        listener = new TextChangeHelper()
        {
            public void textChanged(Control control)
            {
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, modelObj, UMLPackage.eINSTANCE.getNamedElement_Name(), text.getText()));
            }
        };
        listener.startListeningTo(text);
        listener.startListeningForEnter(text);
    }

}
