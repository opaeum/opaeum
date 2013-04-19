
package org.opaeum.uimodeler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.ExtendedUmlModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.util.UmlUimLinks;


public class UimMultiDiagramEditor extends PapyrusMultiDiagramEditor{
	@Override
	public void init(IEditorSite site,IEditorInput input) throws PartInitException{
		super.init(site, input);
		ExtendedUmlModel resource = (ExtendedUmlModel) resourceSet.getModel("org.eclipse.papyrus.infra.core.resource.uml.UmlModel");
		// UserInteractionElement uie= (UserInteractionElement) resource.getContents().get(0);
		UmlUimLinks umlUimLinks = new UmlUimLinks(resource.getResource(), OpaeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace());
		try{
			EObject lookupRoot = resource.lookupRoot();
			if(lookupRoot instanceof UmlReference){
				NamedElement ne = (NamedElement) umlUimLinks.getUmlElement((UmlReference) lookupRoot);
				setPartName(ne.getName() + " User Interface");
			}
		}catch(NotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
