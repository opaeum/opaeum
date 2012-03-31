package org.opaeum.uml2uim;

import java.util.Collections;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
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
			EList<Resource> resources = uimResourceSet.getResources();
			for(Resource resource:resources){
					resource.save(Collections.emptyMap());
					ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resource.getURI().toPlatformString(true)));
			}

		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
