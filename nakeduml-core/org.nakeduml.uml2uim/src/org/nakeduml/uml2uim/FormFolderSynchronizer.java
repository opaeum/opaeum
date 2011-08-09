package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.StepDependency;
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
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.folder.AbstractFolder;
import org.nakeduml.uim.folder.AbstractFormFolder;
import org.nakeduml.uim.folder.ActivityFolder;
import org.nakeduml.uim.folder.EntityFolder;
import org.nakeduml.uim.folder.FolderFactory;
import org.nakeduml.uim.folder.FolderPackage;
import org.nakeduml.uim.folder.PackageFolder;
import org.nakeduml.uim.folder.StateMachineFolder;
import org.nakeduml.uim.folder.UserInteractionModel;
import org.nakeduml.uim.folder.UserInteractionWorkspace;
import org.nakeduml.uim.util.UmlUimLinks;
@StepDependency(phase=UimSynchronizationPhase.class)
public class FormFolderSynchronizer extends AbstractUimSynchronizer{
	public FormFolderSynchronizer(){
	}
	public FormFolderSynchronizer(EmfWorkspace workspace,ResourceSet uimRst,boolean regenerate, UmlElementCache map){
		super(workspace, uimRst, regenerate,map);
	}
	@VisitBefore
	public void visitWorkspace(EmfWorkspace w){
		this.workspace = w;
		Resource resource = getFolderResource();
		if(regenerate){
			resource.getContents().clear();
			UserInteractionWorkspace ws = FolderFactory.eINSTANCE.createUserInteractionWorkspace();
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
			resource = uimRst.getResource(formUri, true);
		}catch(RuntimeException e){
			resource = uimRst.createResource(formUri);
		}
		return resource;
	}
	@VisitBefore
	public void beforeModel(Model m){
		UserInteractionModel pf = (UserInteractionModel) findFolder(m, FolderPackage.eINSTANCE.getUserInteractionModel());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforePackage(org.eclipse.uml2.uml.Package p){
		PackageFolder pf = (PackageFolder) findFolder(p, FolderPackage.eINSTANCE.getPackageFolder());
	}
	private AbstractFormFolder findFolder(Namespace p,EClass eClass){
		AbstractFormFolder pf = (AbstractFormFolder) getFolderFor(p);
		if(pf == null || regenerate){
			pf = (AbstractFormFolder) FolderPackage.eINSTANCE.getEFactoryInstance().create(eClass);
		}
		initFolder(p, pf);
		return pf;
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeClass(org.eclipse.uml2.uml.Class c){
		EntityFolder ef = (EntityFolder) findFolder(c, FolderPackage.eINSTANCE.getEntityFolder());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeActivity(Activity a){
		ActivityFolder pf = (ActivityFolder) findFolder(a, FolderPackage.eINSTANCE.getActivityFolder());
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeStateMachine(StateMachine sm){
		StateMachineFolder pf = (StateMachineFolder) findFolder(sm, FolderPackage.eINSTANCE.getStateMachineFolder());
	}
	private void initFolder(NamedElement p,AbstractFormFolder pf){
		AbstractFolder parentFolder = null;
		Namespace owner = (Namespace) p.getOwner();
		parentFolder = getFolderFor(owner);
		if(parentFolder != pf.getParent()){
			parentFolder.getChildren().add(pf);
		}
		pf.setUmlElementUid(workspace.getId(p));
		pf.setName(p.getName());
	}
}
