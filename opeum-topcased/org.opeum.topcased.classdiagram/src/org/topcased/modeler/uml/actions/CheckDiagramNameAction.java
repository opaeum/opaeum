package org.topcased.modeler.uml.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.DiagnosticComposite;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.ide.ResourceUtil;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.uml.UMLPlugin;

public class CheckDiagramNameAction implements IObjectActionDelegate
{
    private IStructuredSelection selection;

    private BasicDiagnostic diagnostic;

    private List<String> diagramNames = new ArrayList<String>();
    
    private List<String> diagramToDisplayInErrorLog = new ArrayList<String>();
    
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
        boolean errorOccurs = false;
        if (selection.getFirstElement() instanceof IFile)
        {
            IFile currentFile = (IFile) selection.getFirstElement();
            diagramToDisplayInErrorLog.clear();
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
                List<Diagrams> listDiagrams = getDiagramsAdditionalResource(selectedFileURI);

                // init diagnostician
                diagnostic = new BasicDiagnostic(currentFile.getName(), Status.ERROR, "The following diagrams have an invalid or a duplicate name:", new Object[] {currentFile});

                // check diagram name
                for (Diagrams rootDiagrams : listDiagrams)
                {
                    DiagramNameUtil.renameDiagrams(rootDiagrams, diagnostic, diagramNames, diagramToDisplayInErrorLog, corruptedDiagramToDisplayInErrorLog, false);
                }

                // display diagnostician if needed
                if (diagnostic.getChildren() != null && !diagnostic.getChildren().isEmpty())
                {
                    DiagnosticDialog dialog = new DiagnosticDialog(Display.getDefault().getActiveShell(), "UMLDI validation error", "The umldi file contains invalid diagram name", diagnostic,
                            DiagnosticComposite.ERROR_WARNING_MASK);
                    dialog.open();
                }
                else
                {
                    DiagramNameUtil.displayMessageBox("Diagrams' name are valid in the UMLDI file", SWT.ICON_INFORMATION);
                }

            }
        }
        if (errorOccurs)
        {
            DiagramNameUtil.displayMessageBox("Error occurs during action", SWT.ICON_ERROR);
        }
    }

    /**
     * Gets the diagrams resource for a file path
     * 
     * @param filePath the specified file path
     * 
     * @return the diagrams resource
     */
    public static List<Diagrams> getDiagramsAdditionalResource(String filePath)
    {
        List<Diagrams> diagrams = new ArrayList<Diagrams>();

        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.getResource(URI.createURI(filePath), true);
        EcoreUtil.resolveAll(set);
        for (Resource res : set.getResources())
        {
            if (res != null && res.getContents().get(0) instanceof Diagrams)
            {
                diagrams.add((Diagrams) resource.getContents().get(0));
            }
        }
        return diagrams;
    }

}
