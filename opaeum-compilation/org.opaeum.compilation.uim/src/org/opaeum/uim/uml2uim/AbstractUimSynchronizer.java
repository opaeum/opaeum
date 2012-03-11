package org.opaeum.uim.uml2uim;

import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.EmfElementVisitor;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.uim.util.UmlUimLinks;

public class AbstractUimSynchronizer extends EmfElementVisitor implements ITransformationStep{
	protected ResourceSet uimRst;
	protected boolean regenerate;
	protected UmlUimLinks links;
	protected EmfWorkspace workspace;

	public AbstractUimSynchronizer(){
	}
	public AbstractUimSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		this.workspace = workspace;
		init(workspace,resourceSet,  regenerate);
	}
	public void init(EmfWorkspace workspace,ResourceSet uimRst, boolean b){
		this.workspace=workspace;
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
	public Collection<? extends Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return ((EmfWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return super.getChildren(root);
		}
	}
	protected final Resource getResource(String id,String extenstion){
		URI formUri = workspace.getDirectoryUri().appendSegment("ui");
		String formId = id;
		formUri = formUri.appendSegment(formId);
		formUri = formUri.appendFileExtension(extenstion);
		Resource resource = null;
		try{
			resource = uimRst.getResource(formUri,false);
			resource.load(new HashMap<Object,Object>());
		}catch(Exception e){
			try{
				resource.delete(new HashMap<Object,Object>());
			}catch(Exception e2){
			}
			resource = uimRst.createResource(formUri);
		}
		links=new UmlUimLinks(resource, workspace);

		return resource;
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void release(){
		this.uimRst=null;
		this.workspace=null;
		
	}
	
}
