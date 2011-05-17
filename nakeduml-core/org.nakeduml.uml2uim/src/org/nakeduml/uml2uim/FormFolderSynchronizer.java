package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.StateMachine;
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.AbstractFormFolder;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionModel;
import org.nakeduml.uim.UserInteractionWorkspace;
import org.nakeduml.uim.util.UmlUimLinks;

public class FormFolderSynchronizer extends AbstractUimSynchronizer{
	public FormFolderSynchronizer(){
	}
	public FormFolderSynchronizer(EmfWorkspace workspace, ResourceSet resourceSet,boolean regenerate){
		super(workspace,resourceSet, regenerate);
	}
	@VisitBefore
	public void visitWorkspace(EmfWorkspace w){
		this.workspace = w;
		Resource resource = getFolderResource();
		if(regenerate){
			resource.getContents().clear();
			UserInteractionWorkspace ws = UimFactory.eINSTANCE.createUserInteractionWorkspace();
			ws.setName(w.getName());
			resource.getContents().add(ws);
		}
	}
	private Resource getFolderResource(){
		URI formUri = workspace.getDirectoryUri().appendSegment("forms");
		formUri = formUri.appendSegment("folders");
		formUri = formUri.appendFileExtension("uim");
		Resource resource = null;
		try{
			resource = resourceSet.getResource(formUri, true);
		}catch(RuntimeException e){
			resource = resourceSet.createResource(formUri);
		}
		return resource;
	}
	@VisitBefore
	public void beforeModel(Model m){
		UserInteractionModel pf = (UserInteractionModel) findFolder(m, UimPackage.eINSTANCE.getUserInteractionModel());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforePackage(org.eclipse.uml2.uml.Package p){
		PackageFolder pf = (PackageFolder) findFolder(p, UimPackage.eINSTANCE.getPackageFolder());
	}
	private AbstractFormFolder findFolder(Namespace p,EClass eClass){
		AbstractFormFolder pf = (AbstractFormFolder) UmlUimLinks.getInstance(p).getFolderFor(p);
		if(pf == null || regenerate){
			pf = (AbstractFormFolder) UimPackage.eINSTANCE.getEFactoryInstance().create(eClass);
		}
		initFolder(p, pf);
		return pf;
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeClass(org.eclipse.uml2.uml.Class c){
		EntityFolder ef = (EntityFolder) findFolder(c, UimPackage.eINSTANCE.getEntityFolder());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeActivity(Activity a){
		ActivityFolder pf = (ActivityFolder) findFolder(a, UimPackage.eINSTANCE.getActivityFolder());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeStateMachine(StateMachine sm){
		StateMachineFolder pf = (StateMachineFolder) findFolder(sm, UimPackage.eINSTANCE.getStateMachineFolder());
	}
	private void initFolder(NamedElement p,AbstractFormFolder pf){
		UmlUimLinks.getInstance(p).link((UmlReference) pf);
		AbstractFolder parentFolder=null;
		if(p instanceof Model){
			Collection<UmlReference> ue = UmlUimLinks.getInstance(p).getUimElements(workspace);
			if(ue.size() > 0){
				parentFolder = (AbstractFolder) ue.iterator().next();
			}
		}else{
			Namespace owner = (Namespace) p.getOwner();
			parentFolder = UmlUimLinks.getInstance(p).getFolderFor(owner);
		}
		if(parentFolder != pf.getParent()){
			parentFolder.getChildren().add(pf);
		}
		pf.setUmlElementUid(UmlUimLinks.getId(p));
		pf.setName(p.getName());
	}
}
