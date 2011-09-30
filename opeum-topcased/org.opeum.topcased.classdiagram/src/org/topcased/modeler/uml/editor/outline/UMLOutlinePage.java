/*******************************************************************************
 * Copyright (c) 2005 Anyware Technologies
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 *******************************************************************************/

package org.topcased.modeler.uml.editor.outline;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.DiagramsOutlinePage;
import org.topcased.modeler.editor.outline.ModelNavigator;

/**
 * The UML Outline is customized. It changes the context menu in the model navigator.
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class UMLOutlinePage extends DiagramsOutlinePage
{

    /**
     * Constructor
     * 
     * @param modeler the editor
     */
    public UMLOutlinePage(Modeler modeler)
    {
        super(modeler);
    }

    /**
     * @see org.topcased.modeler.editor.outline.DiagramsOutlinePage#createNavigator(org.eclipse.swt.widgets.Composite,
     *      org.topcased.modeler.editor.Modeler, org.eclipse.ui.part.IPageSite)
     */
    protected ModelNavigator createNavigator(Composite parent, Modeler editor, IPageSite pageSite)
    {
        return new UMLNavigator(parent, editor, pageSite);
    }
}
