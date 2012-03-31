package org.opaeum.uml2uim;

import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.uml2uim.FormSynchronizer;

public class RegenerateAction extends AbstractUimGenerationAction{
	public RegenerateAction(){
		super("Regenerate User Interfaces for this element");
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
			fs.visitOnly(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false);
			ds.visitOnly(modelElement);
			EList<Resource> resources = uimResourceSet.getResources();
			for(Resource resource:resources){
					resource.save(Collections.emptyMap());
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
