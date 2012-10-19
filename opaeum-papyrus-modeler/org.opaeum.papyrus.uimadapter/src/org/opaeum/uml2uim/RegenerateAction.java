package org.opaeum.uml2uim;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class RegenerateAction extends AbstractUimGenerationAction{
	public RegenerateAction(){
		super("Regenerate User Interfaces for this element");
	}
	protected void runActionRecursively(Element modelElement,EmfWorkspace w){
		doGenerate(modelElement, w);
	}
	public static void doGenerate(Element modelElement,EmfWorkspace workspace){
		try{
			FormSynchronizer2 fs = new FormSynchronizer2(workspace.getDirectoryUri(), modelElement.eResource().getResourceSet(), true);
			fs.visitOnly(modelElement);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
