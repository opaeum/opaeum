package org.nakeduml.uml2uim;

import java.util.Collection;
import java.util.HashMap;

import net.sf.nakeduml.emf.extraction.EmfElementVisitor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementMap;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.nakeduml.uim.folder.AbstractFolder;
import org.nakeduml.uim.util.UmlUimLinks;

public class AbstractUimSynchronizer extends EmfElementVisitor implements TransformationStep{
	ResourceSet uimRst;
	protected boolean regenerate;
	protected UmlUimLinks links;
	public AbstractUimSynchronizer(){
	}
	public AbstractUimSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate,UmlElementMap map){
		super.workspace = workspace;
		init(workspace,resourceSet,  regenerate,map);
	}
	public void init(EmfWorkspace workspace,ResourceSet uimRst, boolean b, UmlElementMap map){
		super.workspace=workspace;
		this.regenerate = b;
		this.uimRst = uimRst;
		links=new UmlUimLinks(map);
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
			return ((EmfWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return super.getChildren(root);
		}
	}
	protected final Resource getResource(String id,String extenstion){
		URI formUri = workspace.getDirectoryUri().appendSegment("forms");
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
		return resource;
	}
	protected AbstractFolder getFolderFor(Namespace class_){
		return null;
	}
	
}
