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
 *  Benoit MAGGI (Atos Origin) benoit.maggi@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.IAnnotationConstants;
import org.topcased.modeler.commands.RecordingChangeCommand;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Class ApplyProfileCommand.
 */
public class ApplyProfileCommand extends RecordingChangeCommand
{

    /** The e object. */
    private EObject eObject;

    /** The stereotype list. */
    private List<Stereotype> stereotypeList;

    /** The edit part. */
    private EditPart editPart;

    /**
     * Instantiates a new apply profile command.
     * 
     * @param pEObject the e object
     * @param pStereotypeList the stereotype list
     * @param pEditPart the edit part
     */
    public ApplyProfileCommand(EObject pEObject, List<Stereotype> pStereotypeList, EditPart pEditPart)
    {
        super(pEObject.eResource());
        eObject = pEObject;
        stereotypeList = pStereotypeList;
        editPart = pEditPart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
     */
    @Override
    protected void doExecute()
    {
        for (Iterator<Stereotype> itNewElements = stereotypeList.iterator(); itNewElements.hasNext();)
        {
            Stereotype stereotype = itNewElements.next();
            ((Element) eObject).applyStereotype(stereotype);
            manageExtensionAuthorEannotation((Element) eObject, stereotype);
        }
        refreshEditPart();
    }

    /**
     * 
     * Create eannotation extension author or eannotation extension author depending on existing eannotation
     * 
     * @param eObject2
     * @param next
     */
    @SuppressWarnings("restriction")
    private void manageExtensionAuthorEannotation(Element eObject2, Stereotype next)
    {
        String author = ModelerPlugin.getDefault().getPreferenceStore().getString(ModelerPreferenceConstants.P_DEFAULT_AUTHOR);
        boolean booleanAuthor = ModelerPlugin.getDefault().getPreferenceStore().getBoolean(ModelerPreferenceConstants.P_CREATE_AUTHOR_ANNOTATION);

        boolean createEannotation = true;
        boolean createNewEannotationOcurrence = false;

        EAnnotation eannotationFromSpecifiedStereotype = null;

        if (author != null && author.length() > 0 && eObject2 instanceof EModelElement && booleanAuthor)
        {

            List<EAnnotation> listEannotation = eObject2.getEAnnotations();

            for (EAnnotation eannotation : listEannotation)
            {
                if (eannotation.getSource() != null && eannotation.getSource().equals(IAnnotationConstants.EXTENSION_AUTHOR_SOURCE))
                {
                    eannotationFromSpecifiedStereotype = eannotation;
                    break;
                }
            }

            if (!listEannotation.isEmpty() && eannotationFromSpecifiedStereotype != null)
            {
                createEannotation = false;
                createNewEannotationOcurrence = true;
            }
        }
        else
        {
            createEannotation = false;
        }
        if (createNewEannotationOcurrence)
        {
            String authorName = next.getName();
            eannotationFromSpecifiedStereotype.getDetails().put(authorName, author);
        }
        else if (createEannotation)
        {
            EModelElement modelElement = (EModelElement) eObject2;
            String authorName = next.getName();
            Utils.createExtensionAuthorEAnnotation(modelElement, authorName, author);
        }
    }

    /**
     * Refresh the editPart if it is not null
     */
    private void refreshEditPart()
    {
        if (editPart != null)
        {
            editPart.refresh();
        }
    }

    /**
     * This command is not Undoable. The Unapply Stereotype action should be used instead.
     */
    @Override
    public boolean canUndo()
    {
        return false;
    }
}
