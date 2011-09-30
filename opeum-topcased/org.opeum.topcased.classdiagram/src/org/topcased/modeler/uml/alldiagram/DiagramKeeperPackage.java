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
 *  Alexia ALLANIC (Atos Origin) alexia.allanic@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram;

import org.eclipse.uml2.uml.Package;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.tools.DiagramKeeperImpl;

public class DiagramKeeperPackage extends DiagramKeeperImpl
{
    /**
     * {@inheritDoc}
     */
    public boolean keep(GraphElement movedElement)
    {
        return super.keep(movedElement) && getEObject(movedElement).eContainer() instanceof Package;
    }
}
