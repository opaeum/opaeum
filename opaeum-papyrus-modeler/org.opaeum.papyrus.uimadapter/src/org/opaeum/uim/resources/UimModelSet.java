package org.opaeum.uim.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.infra.core.resource.ModelIdentifiers;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.resource.sasheditor.SashModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageList;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.services.resourceloading.OnDemandLoadingModelSet;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.uimodeler.util.UimContentAdapter;

public class UimModelSet extends OnDemandLoadingModelSet implements IOpaeumResourceSet{
	private InMemoryNotationResource inMemoryNotationModel;
	private IFile primaryFile;
	private Map<Element,Resource> uiResourceMap = new HashMap<Element,Resource>();
	private OpenUmlFile openUmlFile;
	private DiagramSynchronizingListener diagramSynchronizer;
	public OpenUmlFile getOpenUmlFile(){
		if(openUmlFile == null && getRootObject() != null){
			openUmlFile = OpaeumEclipseContext.findOpenUmlFileFor(getRootObject());
			openUmlFile.setAdditionalContentAdapter(new UimContentAdapter(this));
		}
		return openUmlFile;
	}
	public SashWindowsMngr getWindowsManager(){
		SashModel model = (SashModel) super.getModel(DiModel.MODEL_ID);
		return (SashWindowsMngr) model.getResource().getContents().get(0);
	}
	public Package getRootObject(){
		UmlModel um = (UmlModel) getModel(UmlModel.MODEL_ID);
		if(um != null && um.getResource().getContents().size() > 0){
			return (Package) um.getResource().getContents().get(0);
		}else{
			return null;
		}
	}
	public void registerUimResource(Element e, Resource r){
		uiResourceMap.put(e, r);
		if(r.getResourceSet()==null){
			try{
				getResources().add(r);
			}catch(Exception ex){
				EditingDomain ed = openUmlFile.getEditingDomain();
				ed.getCommandStack().execute(new AddCommand(ed, getResources(), r));
			}
		}
		r.eAdapters().add(getDiagramSynchronizer());
	}
	protected DiagramSynchronizingListener getDiagramSynchronizer(){
		if(diagramSynchronizer == null){
			diagramSynchronizer = new DiagramSynchronizingListener(inMemoryNotationModel);
		}
		return diagramSynchronizer;
	}
	@Override
	public void createsModels(ModelIdentifiers modelIdentifiers){
		super.createsModels(modelIdentifiers);
		createInMemoryModel();
		EcoreUtil.resolveAll(this);
	}
	@Override
	public void createsModels(IFile newFile){
		super.createsModels(newFile);
		createInMemoryModel();
		updatePrimaryFile(newFile);
		EcoreUtil.resolveAll(this);
	}
	@Override
	public void loadModels(IFile file) throws ModelMultiException{
		updatePrimaryFile(file);
		super.loadModels(file);
		TreeIterator<EObject> eAllContents = getWindowsManager().eAllContents();
		Set<PageRef> remove = new HashSet<PageRef>();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof PageRef){
				PageRef pr = (PageRef) eObject;
				if(pr.getEmfPageIdentifier() == null || pr.getEmfPageIdentifier().eIsProxy()){
					remove.add(pr);
				}
			}
		}
		for(PageRef pageRef:remove){
			if(pageRef.getParent() != null){
				pageRef.getParent().getChildren().remove(pageRef);
			}else if(pageRef.eContainer() instanceof PageList){
				((PageList) pageRef.eContainer()).getAvailablePage().remove(pageRef);
			}
		}
		createInMemoryModel();
		EcoreUtil.resolveAll(this);
	}
	protected void updatePrimaryFile(IFile file){
		String name = file.getName().replaceAll("\\.di", ".uml");
		this.primaryFile = (IFile) file.getParent().findMember(name);
	}
	private void createInMemoryModel(){
		URI uri = getModelResource().getURI().trimSegments(1).appendSegment("ui").appendSegment("tmp.notation");
		Resource tmp = super.getResource(uri, false);
		if(tmp == null){
			tmp = super.createResource(uri);
		}
		tmp.unload();
		getResources().remove(tmp);
		inMemoryNotationModel = new InMemoryNotationResource(this, uri);
		super.uriResourceMap.put(uri, inMemoryNotationModel);
		getResources().add(inMemoryNotationModel);
	}
	public InMemoryNotationResource getInMemoryNotationResource(){
		return inMemoryNotationModel;
	}
	@Override
	public IContainer getModelDirectory(){
		return this.getPrimaryFile().getParent();
	}
	@Override
	public IFile getPrimaryFile(){
		return primaryFile;
	}
}
