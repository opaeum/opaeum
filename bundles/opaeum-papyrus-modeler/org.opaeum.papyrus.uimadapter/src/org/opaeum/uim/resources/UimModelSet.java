package org.opaeum.uim.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.resource.ModelIdentifiers;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.resource.sasheditor.SashModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.core.sashwindows.di.TabFolder;
import org.eclipse.papyrus.infra.services.resourceloading.OnDemandLoadingModelSet;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumScheduler;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.papyrus.PapyrusEObjectSelectorUI;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.uml2uim.UimResourceUtil;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uimodeler.util.InMemoryNotationCommandQueue;
import org.opaeum.uimodeler.util.UimContentAdapter;

public class UimModelSet extends OnDemandLoadingModelSet implements IOpaeumResourceSet{
	private InMemoryNotationResource inMemoryNotationModel;
	private IFile primaryFile;
	private Map<Element,Resource> uiResourceMap = new HashMap<Element,Resource>();
	private OpenUmlFile openUmlFile;
	private DiagramSynchronizingListener diagramSynchronizer;
	public OpenUmlFile getOpenUmlFile(){
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
	public void registerUimResources(Map<Element,Resource> map){
		Set<Entry<Element,Resource>> entrySet = map.entrySet();
		InternalTransaction activeTransaction = ((InternalTransactionalEditingDomain) openUmlFile.getEditingDomain()).getActiveTransaction();
		boolean hasTx = activeTransaction != null && activeTransaction.isActive();
		for(final Entry<Element,Resource> entry:entrySet){
			if(entry.getValue().getResourceSet() == null){
				if(hasTx){
					if(activeTransaction.isReadOnly()){
						final EditingDomain ed = openUmlFile.getEditingDomain();
						Display.getDefault().asyncExec(new Runnable(){
							@Override
							public void run(){
								ed.getCommandStack().execute(new AddCommand(ed, getResources(), entry.getValue()));
							}
						});
					}else{
						getResources().add(entry.getValue());
					}
				}else{
					EditingDomain ed = openUmlFile.getEditingDomain();
					ed.getCommandStack().execute(new AddCommand(ed, getResources(), entry.getValue()));
				}
			}
			new UmlUimLinks(entry.getValue(), getOpenUmlFile().getEmfWorkspace());
			EList<Adapter> ad = entry.getValue().eAdapters();
			if(!ad.contains(getDiagramSynchronizer())){
				ad.add(getDiagramSynchronizer());
			}
			uiResourceMap.put(entry.getKey(), entry.getValue());
		}
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
		doOpaeumInitialization();
	}
	@Override
	public void createsModels(IFile newFile){
		super.createsModels(newFile);
		doOpaeumInitialization();
	}
	private void doOpaeumInitialization(){
		this.primaryFile = ResourcesPlugin.getWorkspace().getRoot().getFile(getFilenameWithoutExtension().addFileExtension("uml"));
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(getPrimaryFile().getParent());
		final PapyrusEObjectSelectorUI selector = new PapyrusEObjectSelectorUI(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		final Runnable runnable = new Runnable(){
			@Override
			public void run(){
				EcoreUtil.resolveAll(UimModelSet.this);
				ctx.startSynch(getTransactionalEditingDomain(), getPrimaryFile(), selector);
				openUmlFile = OpaeumEclipseContext.findOpenUmlFileFor(getRootObject());
				HashSet<EClassifier> set = new HashSet<EClassifier>();
				set.add(UMLPackage.eINSTANCE.getType());
				set.add(UMLPackage.eINSTANCE.getClassifier());
				set.add(UMLPackage.eINSTANCE.getInterface());
				set.add(UMLPackage.eINSTANCE.getSignal());
				set.add(UMLPackage.eINSTANCE.getOperation());
				openUmlFile.getTypeCacheAdapter().loadRelevantElements(getRootObject(), set);
				if(openUmlFile.getConfig().isUiModelerActive()){
					doOpaeumUimInitialization();
				}
			}
		};
		runnable.run();
		// runAsyncExclusive(runnable);
	}
	private void runAsyncExclusive(final Runnable runnable){
		OpaeumScheduler.schedule(new Runnable(){
			@Override
			public void run(){
				try{
					getTransactionalEditingDomain().runExclusive(runnable);
				}catch(InterruptedException e){
					OpaeumEclipsePlugin.logError(e.getMessage(), e);
				}
			}
		}, 3000);
	}
	@Override
	public void loadModels(IFile file) throws ModelMultiException{
		super.loadModels(file);
		removeDanglingDiagrams();
		createInMemoryModel();
		doOpaeumInitialization();
	}
	private void removeDanglingDiagrams(){
		SashModel model = (SashModel) super.getModel(DiModel.MODEL_ID);
		model.getResource().eAdapters().add(new EContentAdapter(){
			@Override
			public void notifyChanged(Notification notification){
				super.notifyChanged(notification);
				if(notification.getNotifier() instanceof TabFolder && notification.getNewValue() instanceof PageRef){
					System.out.println();
				}
			}
		});
		TreeIterator<EObject> eAllContents = model.getResource().getAllContents();
		Set<PageRef> remove = new HashSet<PageRef>();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof PageRef){
				PageRef pr = (PageRef) eObject;
				if(pr.getEmfPageIdentifier() == null || pr.getEmfPageIdentifier().eIsProxy()){
					remove.add(pr);
				}else{
					System.out.println();
				}
			}
		}
		for(PageRef pageRef:remove){
			EStructuralFeature f = pageRef.eContainmentFeature();
			if(f.isMany()){
				Collection eGet = (Collection) pageRef.eContainer().eGet(f);
				eGet.remove(pageRef);
			}else{
				pageRef.eContainer().eSet(f, null);
			}
		}
		eAllContents = model.getResource().getAllContents();
		remove = new HashSet<PageRef>();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof PageRef){
				PageRef pr = (PageRef) eObject;
				if(pr.getEmfPageIdentifier() == null || pr.getEmfPageIdentifier().eIsProxy()){
					remove.add(pr);
				}
			}
		}
	}
	private void createInMemoryModel(){
		UmlModel umlModel = UmlUtils.getUmlModel(this);
		URI uri = umlModel.getResource().getURI().trimSegments(1).appendSegment("ui").appendSegment("tmp.notation");
		Resource tmp = super.getResource(uri, false);
		if(tmp == null){
			tmp = super.createResource(uri);
		}
		tmp.unload();
		getResources().remove(tmp);
		inMemoryNotationModel = new InMemoryNotationResource(this, uri);
		super.uriResourceMap.put(uri, inMemoryNotationModel);
		getResources().add(inMemoryNotationModel);
		inMemoryNotationModel.eAdapters().add(InMemoryNotationCommandQueue.getInstance(getFilenameWithoutExtension()));
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
	private void doOpaeumUimInitialization(){
		runAsyncExclusive(new Runnable(){
			@Override
			public void run(){
				try{
					getOpenUmlFile().setAdditionalContentAdapter(new UimContentAdapter(UimModelSet.this));
					URI dirUri = getOpenUmlFile().getEmfWorkspace().getDirectoryUri();
					FormSynchronizer2 fs = new FormSynchronizer2(dirUri, UimModelSet.this, false);
					Collection<EObject> clsses = getOpenUmlFile().getTypeCacheAdapter().getReachableObjectsOfType(getRootObject(),
							UMLPackage.eINSTANCE.getClassifier());
					for(EObject cls:clsses){
						if(!UimResourceUtil.hasUiResource((Classifier) cls, UimModelSet.this, dirUri)){
							fs.beforeClass((Classifier) cls);
						}
					}
					Collection<EObject> ops = getOpenUmlFile().getTypeCacheAdapter().getReachableObjectsOfType(getRootObject(),
							UMLPackage.eINSTANCE.getOperation());
					for(EObject op:ops){
						if(!UimResourceUtil.hasUiResource((Operation) op, UimModelSet.this, dirUri)){
							fs.beforeOperation((Operation) op);
						}
					}
					Collection<EObject> acts = getOpenUmlFile().getTypeCacheAdapter().getReachableObjectsOfType(getRootObject(),
							UMLPackage.eINSTANCE.getAction());
					for(EObject ac:acts){
						if(!UimResourceUtil.hasUiResource((Action) ac, UimModelSet.this, dirUri)){
							fs.beforeAction((Action) ac);
						}
					}
					// NB!! all the work up to this point was done on "unattached" resources. Now we attach them
					registerUimResources(fs.getNewResources());
				}catch(Exception e){
					OpaeumEclipsePlugin.logError(e.getMessage(), e);
				}
			}
		});
	}
}
