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

package org.topcased.modeler.uml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.validation.extend.IValidatorElector;
import org.topcased.validation.core.IValidator;

/**
 * TODO comment this class
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 * @author <a href="maxime.leray@atosorigin.com">Maxime Leray</a>
 */
public class UMLValidator implements IValidator
{
    private Map<IValidator, IValidatorElector> validators;

    public UMLValidator()
    {
        super();
        readExtensionPoint();
    }

    /**
     * @see org.topcased.validation.core.IValidator#validate(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.common.util.DiagnosticChain, org.eclipse.core.runtime.IProgressMonitor)
     */
    public boolean validate(EObject eObject, DiagnosticChain diagnostics, final IProgressMonitor monitor) throws CoreException
    {
        int count = 0;
        for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext(); i.next())
        {
            ++count;
        }
        IFile currentFile = Modeler.getCurrentIFile();
        for (IValidator validator : validators.keySet())
        {
            if (validators.get(validator).isValidatorEligible(currentFile))
            {
                return validator.validate(eObject, diagnostics, monitor);
            }
        }

        monitor.beginTask("UML Validation", count);

        Diagnostician diagnostician = new Diagnostician()
        {

            public boolean validate(EClass eClass, EObject eObj, DiagnosticChain diag, Map<Object, Object> context)
            {
                monitor.worked(1);

                boolean result = org.eclipse.uml2.uml.util.UMLValidator.INSTANCE.validate(eClass, eObj, diag, context);
                if (result || diag != null)
                {
                    result &= doValidateContents(eObj, diag, context);
                }
                return result;
            }
        };

        monitor.setTaskName("Validation of " + EcoreUtil.getURI(eObject));

        Diagnostic result = diagnostician.validate(eObject);
        diagnostics.merge(result);

        return true;
    }

    private void readExtensionPoint()
    {
        final String extensionId = "extendedValidator";
        final String validatorAttribute = "validator";
        final String conditionAttribute = "condition";
        validators = new HashMap<IValidator, IValidatorElector>();

        IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(UMLPlugin.getId(), extensionId);
        for (IConfigurationElement configElement : configElements)
        {
            try
            {
                IValidator validator = (IValidator) configElement.createExecutableExtension(validatorAttribute);
                IValidatorElector elector = (IValidatorElector) configElement.createExecutableExtension(conditionAttribute);
                validators.put(validator, elector);
            }
            catch (CoreException e)
            {
                UMLPlugin.log(e);
            }
        }

    }

}
