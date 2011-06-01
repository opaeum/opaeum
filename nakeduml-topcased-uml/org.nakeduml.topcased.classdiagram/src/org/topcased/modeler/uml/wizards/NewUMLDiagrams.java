/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.IAnnotationConstants;
import org.topcased.modeler.extensions.Template;
import org.topcased.modeler.extensions.TemplatesManager;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.tools.DiagramFileInitializer;
import org.topcased.modeler.uml.UMLImageRegistry;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.utils.Utils;
import org.topcased.modeler.wizards.DiagramsPage;

/**
 * Generated wizard that offers the model creation facilities. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class NewUMLDiagrams extends Wizard implements INewWizard
{
    private IStructuredSelection selection;

    private DiagramsPage diagPage;

    private IFile createdFile;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     * @generated
     */
    public void init(IWorkbench workbench, IStructuredSelection sel)
    {
        createdFile = null;
        selection = sel;

        // TODO put the Wizard image
        setDefaultPageImageDescriptor(UMLImageRegistry.getImageDescriptor("NEW_PAGE_WZD"));
        setDialogSettings(UMLPlugin.getDefault().getDialogSettings());
        setWindowTitle("New UML model with TOPCASED");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     * @generated
     */
    public boolean performFinish()
    {
        boolean result = true;
        if (diagPage.isPageComplete())
        {
            if (diagPage.isNewModel())
            {
                result = result & createModelFile();
                result = result & createDiagramFile();
                if (createdFile != null && result)
                {
                    // Open the newly created model
                    try
                    {
                        IDE.openEditor(UMLPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), createdFile, true);
                    }
                    catch (PartInitException pie)
                    {
                        UMLPlugin.log(pie);
                    }
                }
            }
            else
            {
                createDiagramFromExistingModel();
            }
        }
        return result;
    }

    private boolean createDiagramFromExistingModel()
    {
        WorkspaceModifyOperation op = new WorkspaceModifyOperation()
        {
            /**
             * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
             */
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
            {
                DiagramFileInitializer initializer = new DiagramFileInitializer();
                try
                {
                    initializer.createDiagram(diagPage.getDiagramEObject(), diagPage.getDiagramId(), diagPage.isInitialized(), monitor);
                }
                catch (IOException ioe)
                {
                    throw new InvocationTargetException(ioe);
                }
            }
        };

        try
        {
            getContainer().run(false, true, op);
            return true;
        }
        catch (InvocationTargetException ite)
        {
            UMLPlugin.log(ite);
        }
        catch (InterruptedException ie)
        {
            UMLPlugin.log(ie);
        }
        return false;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     * @generated
     */
    public void addPages()
    {
        diagPage = new UMLDiagramsPage("New UML model with TOPCASED", selection);
        diagPage.setTitle("UML Model with TOPCASED");
        diagPage.setDescription("Define the model diagram informations. ");
        addPage(diagPage);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.jface.wizard.IWizard#canFinish()
     * @generated
     */
    public boolean canFinish()
    {
       return diagPage.isPageComplete();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return true if the model file was successfully created
     * @generated
     */
    private boolean createModelFile()
    {
        try
        {
            if(diagPage.isNewTemplate())
            {
                Template template = TemplatesManager.getInstance().find(diagPage.getTemplateId()).getTemplateModel();
                template.setDestination(diagPage.getSelectedIContainer());
                template.addVariable("name", diagPage.getModelName());
                // Add charset variable (Cf. C sync template, very very optional)
                template.addVariable("charset", diagPage.getSelectedIContainer().getProject().getDefaultCharset(true));
                template.generate(new NullProgressMonitor());
            }
            else if(diagPage.isNewModelDiagram())
            {
                Template template = TemplatesManager.getInstance().find(diagPage.getNewDiagramId()).getTemplateModel();
                template.setDestination(diagPage.getSelectedIContainer());
                template.addVariable("name", diagPage.getModelName());
                IResource res = template.generate(new NullProgressMonitor());
                boolean createAuthorAnnotation = ModelerPlugin.getDefault().getPreferenceStore().getBoolean(ModelerPreferenceConstants.P_CREATE_AUTHOR_ANNOTATION);
                if (createAuthorAnnotation)
                {
                    createModelWithAuthor(res);                    
                }
            }
        }
        catch (CoreException ce)
        {
            UMLPlugin.log(ce);
            UMLPlugin.displayDialog("Template creation", "An error occured during the template generation.", IStatus.ERROR);
            return false;
        }
        return true;
    }
    
    private void createModelWithAuthor(IResource iResource)
    {
        // create the resource
        ResourceSet resourceSet = new ResourceSetImpl();
        String resourcePath = iResource.getFullPath().toOSString();
        URI fileURI = null;
        if (resourcePath.startsWith("/"))
        {
            fileURI = URI.createPlatformResourceURI(resourcePath, true);
        }
        else
        {
            fileURI = URI.createFileURI(resourcePath);
        }
        Resource resource = resourceSet.getResource(fileURI, true);
        
        // get the model and add author if needed
        Element model = (Element) resource.getContents().get(0);
        createAuthorEannotation(model);
        for (EObject obj: model.allOwnedElements())
        {
            createAuthorEannotation(obj);                    
        }
        // save the rerource
        try
        {
            resource.save(Collections.emptyMap());
        }
        catch (IOException e)
        {
            UMLPlugin.log(e);
        }
    }
    
    /**
     * Creates the author eannotation from preferences if needed.
     * 
     * @param object the object
     */
    private void createAuthorEannotation(EObject object)
    {        
        if (object instanceof EModelElement)
        {
            EModelElement modelObject = (EModelElement) object;
            EAnnotation authorAnnotation = modelObject.getEAnnotation(IAnnotationConstants.AUTHOR_SOURCE);
            String author = ModelerPlugin.getDefault().getPreferenceStore().getString(ModelerPreferenceConstants.P_DEFAULT_AUTHOR);
            
            // creates EAnnotation if needed
            if (authorAnnotation == null && !"".equals(author))
            {
                Utils.createAuthorEAnnotation(modelObject, author);              
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return true if the diagram file was successfully created
     * @generated
     */
    private boolean createDiagramFile()
    {
       boolean result = true;
       if(diagPage.isNewTemplate())
       {
           Template template = TemplatesManager.getInstance().find(diagPage.getTemplateId()).getTemplateDI();
           result = updateTemplate(template);
       }
       else if(diagPage.isNewModelDiagram())
       {
           Template template = TemplatesManager.getInstance().find(diagPage.getNewDiagramId()).getTemplateDI();
           result = updateTemplate(template);
       }

        return result;
    }
    
    /**
     * Update the template 
     * @param template the template to update
     * @return true if the template was successfully created
     */
    private boolean updateTemplate(Template template)
    {
        try
        {
            template.setDestination(diagPage.getSelectedIContainer());
            template.addVariable("name", diagPage.getModelName());
            // Bug #1395 : Add an additional variable used to encode the model file name
            template.addVariable("escapedName", URI.encodeFragment(diagPage.getModelName(), false));

            createdFile = (IFile) template.generate(new NullProgressMonitor());
        }
        catch (CoreException ce)
        {
            UMLPlugin.log(ce);

            UMLPlugin.displayDialog("Template creation", "An error occured during the template generation.", IStatus.ERROR);
            return false;
        }
        return true;
    }
}
