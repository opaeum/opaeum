/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml;

import org.eclipse.gef.RequestConstants;

/**
 * The request Ids
 * 
 * @author <a href="mailto:nicolas.lalevee@anyware-tech.com">Nicolas Lalevée</a>
 * 
 */
public interface UMLRequestConstants extends RequestConstants
{

    /** Id for the graph edge move request */
    String REQ_MOVE_GRAPHEDGE = "moveGraphEdge";

}
