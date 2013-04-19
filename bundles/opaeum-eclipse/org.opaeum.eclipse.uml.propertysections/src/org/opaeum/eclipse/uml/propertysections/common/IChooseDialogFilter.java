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
package org.opaeum.eclipse.uml.propertysections.common;

/**
 * This interface determines if an element has to be filtered in a choose dialog
 * @author tfaure
 *
 */
public interface IChooseDialogFilter
{
    
    /**
     * Filter.
     * 
     * @return true, if the element is filtered
     */
    public boolean filter (Object o) ;
}
