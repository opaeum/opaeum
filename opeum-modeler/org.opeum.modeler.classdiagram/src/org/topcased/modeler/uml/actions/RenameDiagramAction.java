/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  eperico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *  caroline Bourdeu d'Aguerre (Atos Origin) caroline.bourdeudaguerre@atosorigin.com - Add the menu to have the possibility to rename the diagrams
 *
 *****************************************************************************/
package org.topcased.modeler.uml.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.ResourceUtil;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.uml.UMLPlugin;

/**
 * Rename diagrams of an umldi file with the diagram container name
 * 
 * @author eperico
 */
public class RenameDiagramAction implements IObjectActionDelegate
{
    private IStructuredSelection selection;

    private BasicDiagnostic diagnostic;

    private List<String> diagramNames = new ArrayList<String>();

    private List<String> duplicatesDiagramToDisplayInErrorLog = new ArrayList<String>();
    
    private List<String> corruptedDiagramToDisplayInErrorLog = new ArrayList<String>();

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart)
    {
        // do nothing
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection)
    {
        this.selection = (IStructuredSelection) selection;
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action)
    {
        boolean renameDiagram = false;
        if ("RenameDiagramAction".equals(action.getId()))
        {
            renameDiagram = true;
        }

        boolean errorOccurs = false;
        try
        {
            if (selection.getFirstElement() instanceof IFile)
            {
                IFile currentFile = (IFile) selection.getFirstElement();
                duplicatesDiagramToDisplayInErrorLog.clear();
                corruptedDiagramToDisplayInErrorLog.clear();
                boolean editorClosed = true;

                // close the active editor if needed
                IEditorPart activeEditor = ResourceUtil.findEditor(UMLPlugin.getActivePage(), currentFile);
                if (activeEditor != null)
                {
                    editorClosed = activeEditor.getSite().getPage().closeEditor(activeEditor, true);
                }

                // operation cancelled if false
                if (editorClosed)
                {
                    String selectedFileURI = currentFile.getLocationURI().toString();
                    Diagrams rootDiagrams = DiagramNameUtil.getDiagramsResource(selectedFileURI);

                    // init diagnostician
                    diagnostic = new BasicDiagnostic(currentFile.getName(), Status.ERROR, "The following diagrams have an invalid or a duplicate name:", new Object[] {currentFile});

                    // rename unamed diagrams
                    DiagramNameUtil.renameDiagrams(rootDiagrams, diagnostic, diagramNames, duplicatesDiagramToDisplayInErrorLog, corruptedDiagramToDisplayInErrorLog, true);

                    // save resources
                    for (Resource res : rootDiagrams.eResource().getResourceSet().getResources())
                    {
                        URI uri = res.getURI();
                        if (uri != null && ("umldi".equals(uri.fileExtension()) || "sysml".equals(uri.fileExtension())))
                        {
                            res.save(null);
                        }
                    }

                    // refresh file
                    currentFile.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());

                    IDE.openEditor(UMLPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(), currentFile, true);

                    // display diagnostician if needed
                    DiagramNameUtil.displayMessageBox("Diagrams' name has been renamed in the UMLDI file", SWT.ICON_INFORMATION);
                }
            }
        }
        catch (PartInitException e)
        {
            errorOccurs = true;
            e.printStackTrace();
        }
        catch (IOException e)
        {
            errorOccurs = true;
            e.printStackTrace();
        }
        catch (CoreException e)
        {
            errorOccurs = true;
            e.printStackTrace();
        }
        if (errorOccurs)
        {
            DiagramNameUtil.displayMessageBox("Error occurs during action", SWT.ICON_ERROR);
        }
    }

}
