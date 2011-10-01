package org.topcased.modeler.uml.actions;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.uml2.uml.NamedElement;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.util.DiagramsUtils;

public class DiagramNameUtil
{

    /**
     * Rename unamed diagrams with its container name.
     * 
     * @param diagrams the diagrams
     * @param hasToBeRename use an index to rename diagrams with duplicated name
     */
    public static void renameDiagrams(Diagrams diagrams, BasicDiagnostic diagnostic, List<String> diagramNames, List<String> diagramToDisplayInErrorLog,
            List<String> corruptedDiagramToDisplayInErrorLog, boolean hasToBeRename)
    {
        if (diagrams != null)
        {
            for (Diagram diagram : DiagramsUtils.findAllDiagrams(diagrams))
            {
                // check invalid name
                if (diagram.getName() != null && (diagram.getName().contains("/") || diagram.getName().contains(File.separator)))
                {
                    if (hasToBeRename)
                    {
                        // Replace '/' by '_'
                        diagram.setName((diagram.getName()).replaceAll("/", "_"));
                    }
                    else
                    {
                        // inform about invalid name
                        if (!corruptedDiagramToDisplayInErrorLog.contains(diagram.getName()))
                        {
                            diagnostic.add(new BasicDiagnostic(Status.ERROR, null, Status.ERROR, "Diagram " + diagram.getName() + " contains invalid character", null));
                            corruptedDiagramToDisplayInErrorLog.add(diagram.getName());
                        }
                    }
                }

                // rename unamed diagrams and diagnose about duplicated diagram names
                if ("unnamed".equals(diagram.getName()))
                {
                    SemanticModelBridge semanticModel = diagram.getSemanticModel();
                    if (semanticModel instanceof EMFSemanticModelBridge)
                    {
                        EObject eObject = ((EMFSemanticModelBridge) semanticModel).getElement();
                        if (eObject instanceof NamedElement)
                        {
                            String containerName = ((NamedElement) eObject).getName();
                            if (containerName == null || containerName.length() == 0)
                            {
                                containerName = getFirstNamedOwner(eObject);
                                diagnostic.add(new BasicDiagnostic(Status.WARNING, null, Status.WARNING, "Element " + containerName + " contains unnamed children", null));
                            }
                            diagram.setName(containerName);
                        }
                    }
                }

                String toLowerName = diagram.getName().toLowerCase();

                if (diagramNames.contains(toLowerName))
                {
                    if (hasToBeRename)
                    {
                        // add an index to the duplicated diagram name
                        diagram.setName(getFirstAvailableName(diagram.getName(), diagramNames, 1));
                    }
                    else
                    {
                        // inform about duplicate diagram name
                        if (!diagramToDisplayInErrorLog.contains(diagram.getName()))
                        {
                            diagnostic.add(new BasicDiagnostic(Status.WARNING, null, Status.WARNING, "Diagram " + diagram.getName() + " already exists", null));
                            diagramToDisplayInErrorLog.add(diagram.getName());
                        }

                    }
                }

                diagramNames.add(diagram.getName().toLowerCase());
            }
        }
        diagramNames.clear();
    }

    /**
     * Gets the first named owner of the specified eObject
     * 
     * @param eObject the eObject
     * 
     * @return the name of first owner
     */
    private static String getFirstNamedOwner(EObject eObject)
    {
        EObject container = eObject.eContainer();
        if (container != null && container instanceof NamedElement)
        {
            String name = ((NamedElement) container).getName();
            if (name != null)
            {
                return name;
            }
            else
            {
                return getFirstNamedOwner(container);
            }
        }
        return "";
    }

    private static String getFirstAvailableName(String commonBasis, List<String> existingNames, int cpt)
    {
        if (existingNames.contains((commonBasis + cpt).toLowerCase()))
        {
            return getFirstAvailableName(commonBasis, existingNames, cpt + 1);
        }
        return commonBasis + cpt;
    }

    /**
     * Display message box.
     * 
     * @param message the message
     */
    public static void displayMessageBox(String message, int severity)
    {
        MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(), SWT.OK | severity);
        messageBox.setMessage(message);
        messageBox.setText("Information");
        messageBox.open();
    }

    /**
     * Gets the diagrams resource for a file path
     * 
     * @param filePath the specified file path
     * 
     * @return the diagrams resource
     */
    public static Diagrams getDiagramsResource(String filePath)
    {
        Diagrams diagrams = null;

        ResourceSet set = new ResourceSetImpl();
        Resource resource = set.getResource(URI.createURI(filePath), true);
        if (resource != null && resource.getContents().get(0) instanceof Diagrams)
        {
            diagrams = (Diagrams) resource.getContents().get(0);
        }
        return diagrams;
    }
}
