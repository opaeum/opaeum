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

package org.topcased.modeler.uml.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.editor.Modeler;

/**
 * Action to create an import of Primitive types in the selected package
 * 
 * @author <a href="david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class ImportPrimitiveTypesAction extends UMLEObjectAction
{

    private org.eclipse.uml2.uml.Package thePackage;

    /**
     * Constructor
     * 
     * @param ed the Modeler object
     * @param p the UML package where the import is created
     */
    public ImportPrimitiveTypesAction(Modeler ed, org.eclipse.uml2.uml.Package p)
    {
        super("Import Primitive Types", ed);
        this.thePackage = p;
    }

    
    
    @Override
    public EObject getEObject()
    {
        return thePackage;
    }



    /**
     * Execute the Action
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run()
    {
        ((CommandStack) getEditor().getAdapter(CommandStack.class)).execute(createActionCommand(thePackage));
    }

    /**
     * Create the generation command
     * 
     * @param p the UML package
     * @return the command that creates the primitive types
     */
    protected Command createActionCommand(final org.eclipse.uml2.uml.Package p)
    {

        List<PrimitiveType> choiceOfValues = new ArrayList<PrimitiveType>();

        Resource eResource = p.eResource();
        ResourceSet resourceSet = eResource == null ? null : eResource.getResourceSet();

        if (resourceSet != null)
        {

            try
            {
                Resource resource = resourceSet.getResource(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI), true);

                for (Iterator<EObject> contents = resource.getAllContents(); contents.hasNext();)
                {

                    EObject object = contents.next();

                    if (object instanceof PrimitiveType && !p.getImportedElements().contains(object))
                    {

                        choiceOfValues.add((PrimitiveType) object);
                    }
                }
            }
            catch (Exception e)
            {
                // ignore
            }

            try
            {
                Resource resource = resourceSet.getResource(URI.createURI(UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI), true);

                for (Iterator<EObject> contents = resource.getAllContents(); contents.hasNext();)
                {

                    EObject object = contents.next();

                    if (object instanceof PrimitiveType && !p.getImportedElements().contains(object))
                    {

                        choiceOfValues.add((PrimitiveType) object);
                    }
                }
            }
            catch (Exception e)
            {
                // ignore
            }

            try
            {
                Resource resource = resourceSet.getResource(URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI), true);

                for (Iterator<EObject> contents = resource.getAllContents(); contents.hasNext();)
                {

                    EObject object = contents.next();

                    if (object instanceof PrimitiveType && !p.getImportedElements().contains(object))
                    {

                        choiceOfValues.add((PrimitiveType) object);
                    }
                }
            }
            catch (Exception e)
            {
                // ignore
            }
        }

        Collections.sort(choiceOfValues, new TextComparator<PrimitiveType>());

        String label = "Choose the primitive types to import";

        final FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), getLabelProvider(), p, UMLPackage.Literals.PACKAGEABLE_ELEMENT, Collections.EMPTY_LIST,
                label, choiceOfValues);
        dialog.open();

        if (dialog.getReturnCode() == Window.OK)
        {

            return new RecordingChangeCommand(p.eResource().getResourceSet())
            {
                /**
                 * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
                 */
                protected void doExecute()
                {
                    for (Iterator<?> primitiveTypes = dialog.getResult().iterator(); primitiveTypes.hasNext();)
                    {

                        p.createElementImport((PrimitiveType) primitiveTypes.next(), VisibilityKind.PUBLIC_LITERAL);
                    }
                }
            };
        }

        return UnexecutableCommand.INSTANCE;
    }

    
    
}
