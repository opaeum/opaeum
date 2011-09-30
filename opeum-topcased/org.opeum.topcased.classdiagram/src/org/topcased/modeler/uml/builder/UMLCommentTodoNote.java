/*******************************************************************************
 * Copyright (c) 2008 TOPCASED. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Topcased contributors and others - initial API and implementation
 *******************************************************************************/
package org.topcased.modeler.uml.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.topcased.modeler.builders.ITodoNoteExtension;

public class UMLCommentTodoNote implements ITodoNoteExtension
{
    public boolean accept(IFile file)
    {
        return file != null && file.getFileExtension() != null && file.getFileExtension().toLowerCase().equals(UMLResource.FILE_EXTENSION);
    }

    public String analyse(EObject eobject)
    {
        if (eobject instanceof Comment)
        {
            Comment comment = (Comment) eobject;
            if (comment.getBody() != null)
            {
                return comment.getBody();
            }
        }
        return null;
    }

}
