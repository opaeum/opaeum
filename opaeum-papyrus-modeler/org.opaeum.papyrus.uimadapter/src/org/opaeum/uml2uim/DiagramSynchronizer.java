package org.opaeum.uml2uim;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.uml2uim.AbstractUimSynchronizer;
import org.opaeum.uim.uml2uim.UimSynchronizationPhase;

@StepDependency(phase = UimSynchronizationPhase.class)
public class DiagramSynchronizer extends AbstractUimSynchronizer{
	public DiagramSynchronizer(){
	}
	public DiagramSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		super(workspace, resourceSet, regenerate);
	}
//	@VisitBefore(matchSubclasses = false)
//	public void beforeAction(OpaqueAction a){
//		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(a);
//		EmbeddedTaskEditor form = (EmbeddedTaskEditor) getResourceRoot(resourceUri, "uml");
//		if(form != null){
//			Resource resource = recreateDiagrams(resourceUri);
//			SashWindowsMngr sashWindowsManager = recreateWindowsManager(resourceUri);
//			// TODO make input entities editable through inputs tab per entity
//			DiagramCreator fc = new DiagramCreator(form, resource, sashWindowsManager);
//			fc.createDiagrams();
//		}
//	}
//	public SashWindowsMngr findOrCreateWindowsManager(String resourceUri){
//		Resource resource = getResource(resourceUri, "di");
//		for(EObject eObject:resource.getContents()){
//			if(eObject instanceof SashWindowsMngr){
//				return (SashWindowsMngr) eObject;
//			}
//		}
//		return recreateWindowsManager(resourceUri);
//	}
//	private SashWindowsMngr recreateWindowsManager(String resourceUri){
//		Resource diagramsResource = getResource(resourceUri, "di");
//		diagramsResource.getContents().clear();
//		SashWindowsMngr m = DiFactory.eINSTANCE.createSashWindowsMngr();
//		m.setPageList(DiFactory.eINSTANCE.createPageList());
//		m.setSashModel(DiFactory.eINSTANCE.createSashModel());
//		diagramsResource.getContents().add(m);
//		return m;
//	}
//	private Resource recreateDiagrams(String resourceUri){
//		Resource diagramsResource = getResource(resourceUri, "notation");
//		diagramsResource.getContents().clear();
//		return diagramsResource;
//	}
//	@VisitBefore(matchSubclasses = true)
//	public void beforeClass(Class c){
//		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(c);
//		ClassUserInteractionModel form = (ClassUserInteractionModel) getResourceRoot(resourceUri, "uml");
//		if(form != null){
//			Resource resource = recreateDiagrams(resourceUri);
//			SashWindowsMngr sashWindowsManager = recreateWindowsManager(resourceUri);
//			DiagramCreator fc = new DiagramCreator(form.getPrimaryEditor(), resource, sashWindowsManager);
//			fc.createDiagrams();
//			fc = new DiagramCreator(form.getNewObjectWizard(), resource, sashWindowsManager);
//			fc.createDiagrams();
//		}
//	}
//	@VisitBefore(matchSubclasses = false)
//	public void beforeOperation(Operation o){
//		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(o);
//		if(EmfBehaviorUtil.isTask(o)){
//			ResponsibilityUserInteractionModel model = (ResponsibilityUserInteractionModel) getResourceRoot(resourceUri, "uml");
//			if(model != null){
//				Resource resource = recreateDiagrams(resourceUri);
//				SashWindowsMngr sashWindowsManager = recreateWindowsManager(resourceUri);
//				DiagramCreator fc = new DiagramCreator(model.getInvocationWizard(), resource, sashWindowsManager);
//				fc.createDiagrams();
//				fc = new DiagramCreator(model.getViewer(), resource, sashWindowsManager);
//				fc.createDiagrams();
//			}
//		}else{
//			QueryInvoker form = (QueryInvoker) getResourceRoot(resourceUri, "uml");
//			if(form != null){
//				Resource resource = recreateDiagrams(resourceUri);
//				SashWindowsMngr sashWindowsManager = recreateWindowsManager(resourceUri);
//				DiagramCreator fc = new DiagramCreator(form, resource, sashWindowsManager);
//				fc.createDiagrams();
//			}
//		}
//	}
//	private UserInteractionElement getResourceRoot(String id,String extenstion){
//		Resource resource = getResource(id, extenstion);
//		if(resource.getContents().isEmpty()){
//			return null;
//		}
//		return (UserInteractionElement) resource.getContents().get(0);
//	}
}
