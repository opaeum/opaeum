package org.opaeum.uml2uim;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;

public class OpenUserInterfaceAction extends AbstractUimGenerationAction{
	public OpenUserInterfaceAction(){
		super("Open User Interface");
	}
	@Override
	protected void runActionRecursively(Element eObject,EmfWorkspace w){
	}
}
