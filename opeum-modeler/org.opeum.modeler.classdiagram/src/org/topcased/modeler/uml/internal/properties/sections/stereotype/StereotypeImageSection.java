/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Thomas Szadel (Atos Origin) - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections.stereotype;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.profilediagram.edit.StereotypeEditPart;
import org.topcased.tabbedproperties.sections.AbstractWorkspaceFileChooserPropertySection;

/**
 * The Class StereotypeImageSection.
 */
public class StereotypeImageSection extends AbstractWorkspaceFileChooserPropertySection
{

    /** The allowed extensions. */
    private static final String[] EXTENSIONS = {"jpg", "gif", "bmp", "png"};

    /** The stereo. */
    private Stereotype stereotype;

    /**
     * Sets the input.
     * 
     * @param part the part
     * @param selection the selection
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        if (input instanceof StereotypeEditPart)
        {
            StereotypeEditPart editPart = (StereotypeEditPart) input;
            stereotype = (Stereotype) editPart.getEObject();
        }
        // Fix #1930: Missing edition from the outline
        else if (input instanceof Stereotype)
        {
            stereotype = (Stereotype) input;
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
     */
    @Override
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getStereotype_Icon();
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractFileChooserPropertySection#createWidgets(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createWidgets(Composite composite)
    {
        super.createWidgets(composite);
        getWorkSpaceFileChooser().setViewerFilter(new ViewerFilter()
        {

            /**
             * Allow to select specific elements.
             * 
             * @param viewer The viewer.
             * @param parentElement The parent.
             * @param element The element.
             * @return True if the element can be selected, false if filtered.
             */
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element)
            {
                if (element instanceof IContainer)
                {
                    return true;
                }
                else if (element instanceof IFile)
                {
                    IFile file = (IFile) element;
                    String extension = file.getFileExtension();
                    for (String ext : EXTENSIONS)
                    {
                        if (ext.equalsIgnoreCase(extension))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractWorkspaceFileChooserPropertySection#isFieldEditable()
     */
    @Override
    public boolean isFieldEditable()
    {
        return true;
    }

    /**
     * Handle the combo modified event.
     */
    @Override
    protected void handleTextModified()
    {
        if (!isRefreshing() && getFeatureValue() != getWorkSpaceFileChooser().getSelection())
        {
            List<IStatus> status = verifyFile();
            getWorkSpaceFileChooser().setStatus(status);
            if (status.isEmpty())
            {
                EditingDomain editingDomain = getEditingDomain();
                Image image = UMLFactory.eINSTANCE.createImage();
                image.setLocation(getWorkSpaceFileChooser().getSelection());

                // Remove oldest images
                if (!stereotype.getIcons().isEmpty())
                {
                    CompoundCommand delCommand = new CompoundCommand();
                    for (Image oldImage : stereotype.getIcons())
                    {
                        delCommand.append(RemoveCommand.create(editingDomain, getEObject(), getFeature(), oldImage));
                    }
                    editingDomain.getCommandStack().execute(delCommand);
                }

                // Insert at the first position
                // Do not append empty string!
                if (!"".equals(image.getLocation().trim()))
                {
                    editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, getEObject(), getFeature(), image, 0));
                }
            }
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractFileChooserPropertySection#verifyFile()
     */
    @Override
    protected List<IStatus> verifyFile()
    {
        List<IStatus> statusList = new ArrayList<IStatus>(super.verifyFile());
        // If parent has found error, not usefull to continue
        if (!statusList.isEmpty())
        {
            return statusList;
        }
        String selection = getWorkSpaceFileChooser().getSelection();
        // If the selection can be blank and is blank, we can stop here
        if (selection != null && selection.trim().equals("") && !cannotBeBlank())
        {
            return statusList;
        }

        // Check image loading
        File selectedFile = new File(toAbsolutePath(selection));

        // File existence already checked by super.
        if (selectedFile.exists())
        {
            try
            {
                // The image is dispose as soon as it is loaded to avoid memory leak
                new org.eclipse.swt.graphics.Image(Display.getCurrent(), selectedFile.getAbsolutePath()).dispose();
            }
            catch (SWTException e)
            {
                String lMsg;
                switch (e.code)
                {
                    case SWT.ERROR_IO:
                        lMsg = "An IO error occurs while reading from the file";
                        break;
                    case SWT.ERROR_INVALID_IMAGE:
                        lMsg = "The image file contains invalid data";
                        break;
                    case SWT.ERROR_UNSUPPORTED_DEPTH:
                        lMsg = "The image file describes an image with an unsupported depth";
                        break;
                    case SWT.ERROR_UNSUPPORTED_FORMAT:
                        lMsg = "The image file contains an unrecognized format";
                        break;
                    default:
                        lMsg = "Unexpected error while loading the image";
                        break;
                }
                IStatus status = new Status(IStatus.ERROR, UMLPlugin.getId(), lMsg);
                ResourcesPlugin.getPlugin().getLog().log(status);
                statusList.add(status);
            }
        }
        else
        {
            statusList.add(new Status(IStatus.ERROR, UMLPlugin.getId(), "The image doesn't exist"));
        }

        return statusList;
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText()
    {
        return "Image :";
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractWorkspaceFileChooserPropertySection#getFeatureValue()
     */
    @Override
    protected String getFeatureValue()
    {
        if (stereotype != null && stereotype.getIcons().size() >= 1)
        {
            return stereotype.getIcons().get(0).getLocation();
        }
        else
        {
            return "";
        }
    }

    /**
     * @see org.topcased.tabbedproperties.sections.AbstractFileChooserPropertySection#isCheckFileExistence()
     */
    @Override
    public boolean isCheckFileExistence()
    {
        return true;
    }

}
