package org.opaeum.uml2uim;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.uml2uim.FormSynchronizer;

public class RegenerateRecursivelyAction extends AbstractUimGenerationAction{
	public RegenerateRecursivelyAction(){
		super("Regenerate User Interfaces Recursively");
	}
	protected void runActionRecursively(NamedElement modelElement){
		doGenerate(modelElement);
	}
	public static void doGenerate(NamedElement modelElement){
		try{
			OpaeumEclipseContext e = OpaeumEclipseContext.getCurrentContext();
			EmfWorkspace workspace = e.getCurrentEmfWorkspace();
			ResourceSet uimResourceSet = new ResourceSetImpl();
			FormSynchronizer fs = new FormSynchronizer(workspace, uimResourceSet, true);
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false);
			ds.visitRecursively(modelElement);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
