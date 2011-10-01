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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.IAnnotationConstants;
import org.topcased.modeler.commands.RecordingChangeCommand;

/**
 * The Class UnapplyProfileCommand.
 */
public class UnapplyProfileCommand extends RecordingChangeCommand {

	
	/** The e object. */
	private EObject eObject;
	
	/** The stereotype list. */
	private List<Stereotype> stereotypeList;	
	
	/** The edit part. */
	private EditPart editPart;
	
	/**
	 * Instantiates a new unapply profile command.
	 * 
	 * @param pEObject the e object
	 * @param pStereotypeList the stereotype list
	 * @param pEditPart the edit part
	 */
	public UnapplyProfileCommand(EObject pEObject, List<Stereotype> pStereotypeList, EditPart pEditPart) {
		super(pEObject.eResource());
		eObject = pEObject;
		stereotypeList = pStereotypeList;
		editPart = pEditPart;
	}

	/* (non-Javadoc)
	 * @see org.topcased.modeler.commands.RecordingChangeCommand#doExecute()
	 */
	@Override
    protected void doExecute()
    {
        for (Iterator<Stereotype> itOldElements = stereotypeList.iterator(); itOldElements.hasNext();)
        {
            Stereotype stereotype = itOldElements.next();
            ((Element) eObject).unapplyStereotype(stereotype);
            manageExtensionAuthorEannotation((Element) eObject, stereotype);
        }
        refreshEditPart();
    }

    private void manageExtensionAuthorEannotation(Element eObject2, Stereotype stereotype)
    {
        List<EAnnotation> listEannotation = eObject2.getEAnnotations();

        for (Iterator<EAnnotation> it = listEannotation.iterator(); it.hasNext();)
        {
            EAnnotation eannotation = it.next();

            if (eannotation.getSource() != null && eannotation.getSource().equals(IAnnotationConstants.EXTENSION_AUTHOR_SOURCE))
            {
                EMap<String, String> map = eannotation.getDetails();
                String value = map.get(stereotype.getName());

                if (value != null && !value.equals(""))
                {
                    map.removeKey(stereotype.getName());
                    
                    if(map.isEmpty()){
                        it.remove();  
                    }
                }               
            }
        }
    }

    /**
	 * Refresh the editPart if it is not null
	 */
    private void refreshEditPart()
    {
        if(editPart!=null)
        {
            editPart.refresh();
        }
    }

    /**
     * This command is not Undoable. The Unapply Stereotype action should be used instead.
     * 
     */
	@Override
	public boolean canUndo()
	{
	    return false;
	}

}
