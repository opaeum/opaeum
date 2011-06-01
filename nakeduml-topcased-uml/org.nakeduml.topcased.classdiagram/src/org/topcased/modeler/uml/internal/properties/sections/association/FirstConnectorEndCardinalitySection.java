/*****************************************************************************
 * 
 * FirstConnectorEndCardinalitySection.java
 * 
 * Copyright (c) 2008 Atos Origin.
 *
 * Contributors:
 *  Guojun SONG (Atos Origin)  guojun.song@atosorigin.com - initial API and implementation
 *  Answer FR #964 The multiplicity of connector ends.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.association;

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;

/**
 * 
 */
public class FirstConnectorEndCardinalitySection extends AbstractConnectorCardinalitySection
{
    /**
     * @see org.topcased.modeler.uml.internal.properties.sections.association.AbstractConnectorCardinalitySection#getEnd(org.eclipse.uml2.uml.Connector)
     */
    protected ConnectorEnd getEnd(Connector con)
    {
        if (con.getEnds() != null && con.getEnds().size() > 0)
        {
            return (ConnectorEnd) con.getEnds().get(0);
        }
        return null;
    }

}
