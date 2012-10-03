package org.opaeum.uim.uml2uim;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.opaeum.EmfElementVisitor;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.visit.VisitSpec;

public class AbstractUimSynchronizer extends EmfElementVisitor implements ITransformationStep,UserInterfaceResourceFactory{
	protected ResourceSet uimRst;
	protected boolean regenerate;
	protected EmfWorkspace workspace;
	public AbstractUimSynchronizer(){
	}
	@Override
	public void visitOnly(Element o){
		super.visitOnly(o);
	}
	public AbstractUimSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		this.workspace = workspace;
		init(workspace, resourceSet, regenerate);
	}
	public void init(EmfWorkspace workspace,ResourceSet uimRst,boolean b){
		this.workspace = workspace;
		this.regenerate = b;
		this.uimRst = uimRst;
	}
	protected void visitParentsRecursively(Element parent){
		if(parent != null){
			visitParentsRecursively(parent.getOwner());
			for(VisitSpec v:methodInvokers.beforeMethods){
				maybeVisit(parent, v);
			}
		}
	}
	public void visitUpThenDown(Element element){
		visitParentsRecursively(element.getOwner());
		visitRecursively(element);
	}
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return new HashSet<Element>(((EmfWorkspace) root).getOwnedModels());
		}else{
			return super.getChildren(root);
		}
	}
	@Override
	public final Resource getResource(String id,String extenstion){
		URI formUri = workspace.getDirectoryUri().appendSegment("ui");
		formUri = formUri.appendSegment(id);
		formUri = formUri.appendFileExtension(extenstion);
		Resource resource = null;
		resource = uimRst.getResource(formUri, false);
		if(resource == null){
			resource = uimRst.createResource(formUri);
		}
		return resource;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void release(){
		this.uimRst = null;
		this.workspace = null;
	}
}
