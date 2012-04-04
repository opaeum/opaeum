package org.opaeum.uml2uim;

import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;

public class OpenUserInterfaceAction extends AbstractUimGenerationAction{
	public OpenUserInterfaceAction(){
		super("Open User Interface");
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void runActionRecursively(Element eObject,EmfWorkspace w){
		URI uri = getFileUri(eObject, w);
		if(!getFile(uri).exists()){
			SynchronizeAction.doSynchronize(eObject, w);
		}else{
		}
	}
}
