/*****************************************************************************
 * Copyright (c) 2005-2008 TOPCASED consortium.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.DuplicationAdapter;

/**
 * This class implements the extension point <code>org.topcased.modeler.duplicateAdapter</code> to customize the
 * "Duplicate action" for UML models. More specifically:
 * <ul>
 * <li>it makes sure the stereotypes applied to the original elements being copied are also copied (and applied to the
 * elements' copies);</li>
 * <li>it renames the top-level element being duplicated (if it is a {@link NamedElement}) by prefixing
 * <code>Copy of</code>.</li>
 * </ul>
 * 
 */
public class UMLDuplicationAdapter implements DuplicationAdapter
{
//    private static final String NAME_PREFIX = "Copy of ";
    
    /**
     * This implementation of {@link DuplicationAdapter#getAdditionalObjects(EObject)} makes sure the stereotypes
     * applied to the original elements are included in the duplication.
     */
    public Collection<EObject> getAdditionalObjects(EObject original)
    {
        if (!(original instanceof Element))
        {
            throw new IllegalArgumentException("Expected a UML model element.");
        }
        Element elt = (Element) original;
        Collection<EObject> additionalElements = new HashSet<EObject>();
        addStereotypes(elt, additionalElements);
        addDependencies(elt, additionalElements);
        // addAssociations(elt, additionalElements); // Disabled until the expected behavior is more precisely defined.
        return additionalElements;
    }

    private void addStereotypes(Element elt, Collection<EObject> additionalElements)
    {
        addStereotypesOf(elt, additionalElements);
        TreeIterator<EObject> iter = elt.eAllContents();
        while (iter.hasNext())
        {
        	EObject childEObject = iter.next();
        	if(childEObject instanceof Element)
        	{
        		Element child = (Element) childEObject;
        		addStereotypesOf(child, additionalElements);
        	}
        }
    }

    private void addStereotypesOf(Element elt, Collection<EObject> allAppliedStereotypes)
    {
        allAppliedStereotypes.addAll(elt.getStereotypeApplications());
    }

    private void addDependencies(Element elt, Collection<EObject> additionalElements)
    {
        if (elt instanceof NamedElement)
        {
            EList<Dependency> dependencies = ((NamedElement) elt).getClientDependencies();
            additionalElements.addAll(dependencies);
        }
    }

    @SuppressWarnings("unused")
    private void addAssociations(Element elt, Collection<EObject> additionalElements)
    {
        if (elt instanceof Type)
        {
            EList<Association> associations = ((Type) elt).getAssociations();
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            if (!associations.isEmpty() && MessageDialog.openQuestion(shell, "Association duplications", "Also duplicate associations involving the target?"))
            {
                additionalElements.addAll(associations);
            }
        }
    }

    /**
     * @see org.topcased.modeler.DuplicationAdapter#getPostProcessingCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, java.util.Map, java.util.Map)
     */
    public Command getPostProcessingCommand(EditingDomain editingDomain, EObject original, Map<EObject, EObject> mainMapping, Map<EObject, EObject> additionalMapping)
    {
        CompoundCommand cmd = new CompoundCommand();
//        if (original instanceof NamedElement)
//        {
//            String name = ((NamedElement) original).getName();
//            NamedElement copy = (NamedElement) mainMapping.get(original);
//            cmd.append(new SetCommand(editingDomain, copy, UMLPackage.eINSTANCE.getNamedElement_Name(), NAME_PREFIX + name));
//        }
        for (Map.Entry<EObject, EObject> entry : additionalMapping.entrySet())
        {
            if (entry.getKey() instanceof Dependency)
            {
                Dependency orig = (Dependency) entry.getKey();
                Dependency copy = (Dependency) entry.getValue();
                for (NamedElement origClient : orig.getClients())
                {
                    NamedElement newClient = origClient;
                    if (mainMapping.containsKey(origClient))
                    {
                        newClient = (NamedElement) mainMapping.get(origClient);
                    }
                    else if (additionalMapping.containsKey(origClient))
                    {
                        newClient = (NamedElement) additionalMapping.get(origClient);
                    }
                    cmd.append(new AddCommand(editingDomain, copy, UMLPackage.eINSTANCE.getDependency_Client(), newClient));
                }
            }
        }
        return cmd.unwrap();
    }
}
