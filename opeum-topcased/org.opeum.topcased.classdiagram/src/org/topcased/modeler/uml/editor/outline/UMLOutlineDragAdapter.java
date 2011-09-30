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
package org.topcased.modeler.uml.editor.outline;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Package;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.editor.outline.OutlineDragAdapter;

/**
 * The Class UMLOutlineDragAdapter.
 */
public class UMLOutlineDragAdapter extends OutlineDragAdapter
{

    public UMLOutlineDragAdapter(Viewer pViewer)
    {
        super(pViewer);
    }

    @Override
    protected boolean checkGraphElement(Object sel)
    {
        if (sel instanceof Diagram)
        {
            Diagram di = (Diagram) sel ; 
            SemanticModelBridge bridge = di.getSemanticModel();
            if (bridge instanceof EMFSemanticModelBridge)
            {
                EMFSemanticModelBridge emfBridge = (EMFSemanticModelBridge) bridge;
                if (emfBridge.getElement() instanceof Package)
                {
                    return true ;
                }
            }
            else
            {
                return false ;
            }
        }
        return false ;
    }
    
    
    

}
