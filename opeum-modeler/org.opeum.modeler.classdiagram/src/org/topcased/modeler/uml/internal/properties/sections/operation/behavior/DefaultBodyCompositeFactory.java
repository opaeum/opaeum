/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tristan FAURE (ATOS ORIGIN INTEGRATION) tristan.faure@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.operation.behavior;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Operation;

/**
 * A factory for creating DefaultBodyComposite objects. for user custom languages no auto completion, no coloration
 */
public class DefaultBodyCompositeFactory implements BodyCompositeFactory
{

    /** The default body composite. */
    private Composite defaultBodyComposite;

    /** The current call back. */
    private CallBackElement currentCallBack;

    /** The code area. */
    private Text codeArea;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.modeler.uml.internal.properties.sections.operation.behavior.BodyCompositeFactory#createComposite
     * (org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
     */
    public Composite createComposite(Composite parent, FormToolkit factory)
    {
        defaultBodyComposite = factory.createComposite(parent, SWT.NONE);
        defaultBodyComposite.setLayout(new GridLayout(1, false));
        codeArea = factory.createText(defaultBodyComposite, "", SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        codeArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return defaultBodyComposite;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.modeler.uml.internal.properties.sections.operation.behavior.BodyCompositeFactory#getCompositeValue()
     */
    public String getCompositeValue()
    {

        return codeArea.getText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.modeler.uml.internal.properties.sections.operation.behavior.BodyCompositeFactory#setCompositeValue
     * (java.lang.String)
     */
    public void setCompositeValue(String text)
    {
        codeArea.setText(text);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.topcased.modeler.uml.internal.properties.sections.operation.behavior.BodyCompositeFactory#setOperation(org
     * .eclipse.uml2.uml.Operation)
     */
    public void setOperation(Operation operation)
    {
        // no contextual information required
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.topcased.modeler.uml.internal.properties.sections.operation.behavior.BodyCompositeFactory#
     * registerCallBackForModification
     * (org.topcased.modeler.uml.internal.properties.sections.operation.behavior.CallBackElement)
     */
    public void registerCallBackForModification(CallBackElement callBack)
    {
        currentCallBack = callBack;
        codeArea.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                currentCallBack.hookListeners();
            }
        });

    }

}
