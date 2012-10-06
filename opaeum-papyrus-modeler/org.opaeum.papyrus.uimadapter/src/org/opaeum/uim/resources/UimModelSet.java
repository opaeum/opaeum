package org.opaeum.uim.resources;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.resource.ModelIdentifiers;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.resource.sasheditor.SashModel;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageList;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.services.resourceloading.OnDemandLoadingModelSet;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uimodeler.util.UimContentAdapter;

public class UimModelSet extends OnDemandLoadingModelSet implements IOpaeumResourceSet{
	private InMemoryNotationResource inMemoryNotationModel;
	private IFile primaryFile;
	public SashWindowsMngr getWindowsManager(){
		SashModel model = (SashModel) super.getModel(DiModel.MODEL_ID);
		return (SashWindowsMngr) model.getResource().getContents().get(0);
	}
	private void addContentAdapter(){
		if(!UimContentAdapter.isListeningTo(this)){
			super.eAdapters().add(new UimContentAdapter());
		}
	}
	@Override
	public void createsModels(ModelIdentifiers modelIdentifiers){
		super.createsModels(modelIdentifiers);
		createInMemoryModel();
	}
	@Override
	public void createsModels(IFile newFile){
		super.createsModels(newFile);
		createInMemoryModel();
		this.primaryFile = newFile;
	}
	@Override
	public void loadModels(IFile file) throws ModelMultiException{
		this.primaryFile = file;
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
		addContentAdapter();
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
		return this.primaryFile;
	}
}
