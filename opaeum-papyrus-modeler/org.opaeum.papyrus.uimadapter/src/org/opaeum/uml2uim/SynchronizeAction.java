package org.opaeum.uml2uim;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.uml2uim.FormSynchronizer;
import org.opaeum.uim.uml2uim.UimSynchronizationPhase;

public class SynchronizeAction extends AbstractUimGenerationAction{
	public SynchronizeAction(){
		super("Synchronize User Interfaces");
	}
	protected void runActionRecursively(Element modelElement,EmfWorkspace workspace){
		doSynchronize(modelElement, workspace);
	}
	public static void doSynchronize(Element modelElement,EmfWorkspace workspace){
		try{
			ResourceSetImpl uimResourceSet = new ResourceSetImpl();
			FormSynchronizer fs = new FormSynchronizer(workspace, uimResourceSet, false);
			fs.visitRecursively(modelElement);
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), uimResourceSet);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false);
			ds.visitRecursively(modelElement);
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), uimResourceSet);
		}catch(IOException e2){
			throw new RuntimeException(e2);
		}
	}
}
