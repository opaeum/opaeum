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
package org.topcased.modeler.uml.validation.extend;

import org.eclipse.core.resources.IFile;
import org.topcased.modeler.editor.Modeler;

/**
 * The validator classes that implement this interface shall define the case(s) when they shall be called to validate a
 * model.
 * 
 * @author <a href="mailto:maxime.leray@atosorigin.com">Maxime Leray</a>
 * 
 */
public interface IValidatorElector
{
    /**
     * Evaluates if the validator is consistent with the given modeler.
     * 
     * @param modeler the modeler
     * @return true, if successful
     */
    boolean isValidatorEligible(Modeler modeler);

    /**
     * Evaluates if the validator is consitent with the given file.
     * 
     * @param file the file
     * @return true, if successful
     */
    boolean isValidatorEligible(IFile file);
}
