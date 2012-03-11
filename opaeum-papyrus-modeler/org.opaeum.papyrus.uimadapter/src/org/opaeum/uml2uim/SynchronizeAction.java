package org.opaeum.uml2uim;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.uml2uim.FormSynchronizer;
import org.opaeum.uim.uml2uim.UimSynchronizationPhase;

public class SynchronizeAction extends AbstractUimGenerationAction {
	public SynchronizeAction(){
		super("Synchronize User Interfaces");
	}

	protected void runActionRecursively(NamedElement modelElement){
		doSynchronize(modelElement);
	}

	public static void doSynchronize(NamedElement modelElement){
		try{
			EmfWorkspace workspace = OpaeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace();
			ResourceSet uimResourceSet = new ResourceSetImpl();
			FormSynchronizer fs = new FormSynchronizer(workspace, uimResourceSet, false);
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false);
			ds.visitRecursively(modelElement);
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), uimResourceSet);
		}catch(IOException e2){
			throw new RuntimeException(e2);
		}
	}
}
