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
 *  Caroline Bourdeu d'Aguerre (2009) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/

package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.ISpecificCase;

public class StereotypeApplicationSpecificCase implements ISpecificCase {


	public EditPart defaultCase(GraphElement element, Class<?> diagramType) {
		return new StereotypeAttributsEditPart((GraphNode) element);
	}

	public boolean isDropable(EObject child, GraphNode parent) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled(Object object, EditPart editPart) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExternalObjectAllowed(GraphNode child, GraphNode parent) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValid(EObject child, EObject parent) {
		// TODO Auto-generated method stub
		return false;
	}

	public GraphElement setPropertyGraphElement(GraphElement graphElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
