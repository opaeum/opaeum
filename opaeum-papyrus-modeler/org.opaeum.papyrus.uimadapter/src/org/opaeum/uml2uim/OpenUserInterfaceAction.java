package org.opaeum.uml2uim;

import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.NamedElement;

public class OpenUserInterfaceAction extends AbstractUimGenerationAction{
	public OpenUserInterfaceAction(){
		super("Open User Interface");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void runActionRecursively(NamedElement eObject){
		URI uri = getFileUri(eObject, getFileName(eObject));
		if(!getFile(uri).exists()){
			SynchronizeAction.doSynchronize(eObject);
		}else{
			
		}
	}
}
