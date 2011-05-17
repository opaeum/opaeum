package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.emf.extraction.EmfElementVisitor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.uim.util.UmlUimLinks;

public class AbstractUimSynchronizer extends EmfElementVisitor implements TransformationStep{
	ResourceSet resourceSet;
	protected boolean regenerate;
	public AbstractUimSynchronizer(){
	}
	public AbstractUimSynchronizer(EmfWorkspace workspace, ResourceSet resourceSet,boolean regenerate){
		super.workspace=workspace;
		init(resourceSet, regenerate);
	}
	public void init(ResourceSet resourceSet,boolean regenerate){
		this.regenerate = regenerate;
		this.resourceSet = resourceSet;
	}
	protected void visitParentsRecursively(Element parent){
		if(parent != null){
			visitParentsRecursively(parent.getOwner());
			for(VisitSpec v:beforeMethods){
				maybeVisit(parent, v);
			}
		}
	}
	public void visitUpThenDown(Element element){
		visitParentsRecursively(element.getOwner());
		visitRecursively(element);
	}
	@Override
	public Collection<? extends Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return ((EmfWorkspace) root).getOwnedModels();
		}else{
			return super.getChildren(root);
		}
	}
}
