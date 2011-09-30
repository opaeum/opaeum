/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Maxime Leray (Atos Origin) maxime.leray@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram;

import org.eclipse.uml2.uml.Package;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.tools.DiagramKeeperImpl;

/**
 * The Class UMLDiagramKeeper.
 */
public class UMLDiagramKeeper extends DiagramKeeperImpl
{

    public boolean keep(GraphElement movedElement)
    {
        return super.keep(movedElement) && getEObject(movedElement).eContainer() instanceof Package;
    }

}
