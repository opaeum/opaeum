/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin  Cedric.dumoulin@lifl.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.opaeum.uimodeler;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.editor.CoreMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.IModel;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.ExtendedUmlModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.util.UmlUimLinks;


/**
 * Papyrus main MultiEditor.
 * This class add GMF adaptation dedicated to Papyrus.
 * TODO : move GMF dependencies into this plugin.
 * 
 * @author dumoulin
 * 
 */
public class UimMultiDiagramEditor extends CoreMultiDiagramEditor {
	@Override
	public void init(IEditorSite site,IEditorInput input) throws PartInitException{
		super.init(site, input);
		ExtendedUmlModel resource = (ExtendedUmlModel) resourceSet.getModel("org.eclipse.papyrus.infra.core.resource.uml.UmlModel");
//		UserInteractionElement uie= (UserInteractionElement) resource.getContents().get(0);
		UmlUimLinks umlUimLinks = new UmlUimLinks(resource.getResource(), OpaeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace());
		try{
			NamedElement ne= (NamedElement) umlUimLinks.getUmlElement((UmlReference) resource.lookupRoot());
			setPartName(ne.getName() + " User Interface");
		}catch(NotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
