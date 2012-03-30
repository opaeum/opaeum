package org.opaeum.uml2uim;

import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.NamedElement;

public class OpenLandingPageAction extends AbstractUimGenerationAction{
	public OpenLandingPageAction(){
		super("Open Landing Page Configuration");
	}

	@Override
	protected void runActionRecursively(NamedElement eObject){
		URI uri = getFileUri(eObject, "landingpage");
		if(!getFile(uri).exists()){
			SynchronizeAction.doSynchronize(eObject);
		}else{
			
		}
	}
}
