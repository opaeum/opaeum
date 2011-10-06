package org.opaeum.uml2uim;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.State;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.folder.EntityFolder;
import org.opaeum.uim.form.FormPanel;
import org.opaeum.uim.form.UimForm;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;

@StepDependency(phase = UimSynchronizationPhase.class,requires = FormFolderSynchronizer.class,after = FormFolderSynchronizer.class)
public class DiagramSynchronizer extends AbstractUimSynchronizer{
	public DiagramSynchronizer(){
	}
	public DiagramSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		super(workspace, resourceSet, regenerate);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeAction(OpaqueAction a){
		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(a);
		UimForm form = getFormFor(resourceUri, "uim");
		if(form != null){
			Diagram diagrams = recreateDiagrams(resourceUri, form.getPanel());
			// TODO make input entities editable through inputs tab per entity
			DiagramCreator fc = new DiagramCreator(form.getPanel(), diagrams);
			fc.createDiagram();
		}
	}
	private Diagram recreateDiagrams(String resourceUri,FormPanel form){
		Resource diagramsResource = getResource(resourceUri, "uimdi");
		diagramsResource.getContents().clear();
		Diagrams diagrams = DiagramsFactory.eINSTANCE.createDiagrams();
		diagrams.setModel(form);
		diagramsResource.getContents().add(diagrams);
		Diagram diag = DiagramInterchangeFactory.eINSTANCE.createDiagram();
		EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE.createEMFSemanticModelBridge();
		bridge.setPresentation("org.opaeum.uim.classform");
		bridge.setElement(form);
		diag.setSemanticModel(bridge);
		diag.setName(form.getName());
		diag.setPosition(new Point());
		diag.setSize(new Dimension(1000, 1000));
		diag.setViewport(new Point());
		diagrams.getDiagrams().add(diag);
		// Diagrams subDiagrams = DiagramsFactory.eINSTANCE.createDiagrams();
		// diagrams.getSubdiagrams().add(subDiagrams);
		// subDiagrams.getDiagrams().add(diag);
		return diag;
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeClass(Class c){
		EntityFolder folder = (EntityFolder) getFolderFor(c.getNamespace());
		createClassForm(c, folder, ActionKind.UPDATE, ActionKind.DELETE, ActionKind.BACK);
		createClassForm(c, folder, ActionKind.CREATE, ActionKind.BACK);
	}
	private void createClassForm(Class c,EntityFolder folder,ActionKind...actionKinds){
		String suffix = actionKinds[0] == ActionKind.UPDATE ? "Editor" : "Creator";
		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(c) + suffix;
		UimForm form = getFormFor(resourceUri, "uim");
		if(form != null){
			Diagram diagrams = recreateDiagrams(resourceUri, form.getPanel());
			// TODO make input entities editable through inputs tab per entity
			DiagramCreator fc = new DiagramCreator(form.getPanel(), diagrams);
			fc.createDiagram();
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		if(EmfBehaviorUtil.isTask(o)){
			String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(o) + "Task";
			UimForm form = getFormFor(resourceUri, "uim");
			if(form != null){
				Diagram diagrams = recreateDiagrams(resourceUri, form.getPanel());
				// TODO make input entities editable through inputs tab per entity
				DiagramCreator fc = new DiagramCreator(form.getPanel(), diagrams);
				fc.createDiagram();
			}
		}
		// TODO generate table Panels for multi output parameters and detail panels for single output parameters
		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(o) + "Invoker";
		UimForm form = getFormFor(resourceUri, "uim");
		if(form != null){
			Diagram diagrams = recreateDiagrams(resourceUri, form.getPanel());
			// TODO make input entities editable through inputs tab per entity
			DiagramCreator fc = new DiagramCreator(form.getPanel(), diagrams);
			fc.createDiagram();
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeState(State s){
		String resourceUri = OpaeumEclipseContext.getCurrentContext().getId(s);
		UimForm form = getFormFor(resourceUri, "uim");
		if(form != null){
			Diagram diagrams = recreateDiagrams(resourceUri, form.getPanel());
			// TODO make input entities editable through inputs tab per entity
			DiagramCreator fc = new DiagramCreator(form.getPanel(), diagrams);
			fc.createDiagram();
		}
	}
	private UimForm getFormFor(String id,String extenstion){
		Resource resource = getResource(id, extenstion);
		return (UimForm) resource.getContents().get(0);
	}
	private void setActiveDiagram(Diagrams diagrams){
		diagrams.setActiveDiagram(diagrams.getDiagrams().isEmpty() ? null : diagrams.getDiagrams().get(0));
		for(Diagrams child:diagrams.getSubdiagrams()){
			setActiveDiagram(child);
		}
	}
}
