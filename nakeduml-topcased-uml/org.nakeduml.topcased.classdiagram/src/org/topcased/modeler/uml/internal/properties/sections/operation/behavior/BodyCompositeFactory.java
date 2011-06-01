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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Operation;

/**
 * A factory for creating BodyComposite objects.
 */
public interface BodyCompositeFactory
{

    /**
     * Creates a new BodyComposite object.
     * 
     * @param parent the parent
     * @param widgetFactory the widget factory
     * 
     * @return the composite owning the text area for the language
     */
    Composite createComposite(Composite parent, FormToolkit widgetFactory);

    /**
     * Sets the operation.
     * 
     * @param operation the operation for text area which needs contextual information
     */
    void setOperation(Operation operation);

    /**
     * Gets the composite value. ie. the text contained in the composite
     * 
     * @return the composite value
     */
    String getCompositeValue();

    /**
     * Sets the composite value. ie. the text contained in the composite
     * 
     * @param text the new composite value
     */
    void setCompositeValue(String text);

    /**
     * Register call back for modification. The class implemented this method must create a listener on modification of
     * text contained in the composite and call the hookListeners method of the callBackElement
     * 
     * @param callBack the call back
     */
    void registerCallBackForModification(CallBackElement callBack);
}
