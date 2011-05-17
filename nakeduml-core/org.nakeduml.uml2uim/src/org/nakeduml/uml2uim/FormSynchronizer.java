package org.nakeduml.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.name.NameConverter;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimForm;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.modeleditor.SafeUmlUimLinks;
import org.nakeduml.uim.util.StateMachineUtil;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;

@StepDependency(phase = UserInteractionSynchronizationPhase.class,requires = FormFolderSynchronizer.class,after = FormFolderSynchronizer.class)
public class FormSynchronizer extends AbstractUimSynchronizer{
	public FormSynchronizer(){
	}
	public FormSynchronizer(EmfWorkspace workspace, ResourceSet resourceSet,boolean regenerate){
		super(workspace,resourceSet, regenerate);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeAction(OpaqueAction a){
		String resourceUri = UmlUimLinks.getInstance(a).getId(a);
		UimForm form = getFormFor(resourceUri, "uim");
		ActionTaskForm atf = null;
		if(regenerate || form.getPanel() == null){
			atf = UimFactory.eINSTANCE.createActionTaskForm();
			Diagram diagrams = recreateDiagrams(resourceUri, atf);
			initForm(form, a, atf);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(atf, diagrams);
			ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a.getInputs());
			pins.addAll(a.getOutputs());
			fc.prepareFormPanel("Task: " + NameConverter.separateWords(a.getName()), pins);
			fc.addButtonBar(ActionKind.COMPLETE_TASK, ActionKind.POSTPONE_TASK, ActionKind.RETURN_TASK);
		}else{
			atf = (ActionTaskForm) form.getPanel();
		}
		putFormElements(atf);
		atf.setFolder((ActivityFolder) UmlUimLinks.getInstance(a).getFolderFor(a.getActivity()));
	}
	private Diagram recreateDiagrams(String resourceUri,FormPanel form){
		Resource diagramsResource = getResource(resourceUri, "uimdi");
		diagramsResource.getContents().clear();
		Diagrams diagrams = DiagramsFactory.eINSTANCE.createDiagrams();
		diagrams.setModel(form);
		diagramsResource.getContents().add(diagrams);
		Diagram diag = DiagramInterchangeFactory.eINSTANCE.createDiagram();
		EMFSemanticModelBridge bridge = DiagramInterchangeFactory.eINSTANCE.createEMFSemanticModelBridge();
		bridge.setPresentation("org.nakeduml.uim.classform");
		bridge.setElement(form);
		diag.setSemanticModel(bridge);
		diag.setName(form.getName());
		diag.setPosition(new Point());
		diag.setSize(new Dimension(1000, 1000));
		diag.setViewport(new Point());
		diagrams.getDiagrams().add(diag);
//		Diagrams subDiagrams = DiagramsFactory.eINSTANCE.createDiagrams();
//		diagrams.getSubdiagrams().add(subDiagrams);
//		subDiagrams.getDiagrams().add(diag);
		return diag;
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeClass(Class c){
		EntityFolder folder = (EntityFolder) UmlUimLinks.getInstance(c).getFolderFor(c.getNamespace());
		createClassForm(c, folder, ActionKind.UPDATE, ActionKind.DELETE, ActionKind.BACK);
		createClassForm(c, folder, ActionKind.CREATE, ActionKind.BACK);
	}
	private void createClassForm(Class c,EntityFolder folder,ActionKind...actionKinds){
		String suffix = actionKinds[0] == ActionKind.UPDATE ? "Editor" : "Creator";
		String resourceUri = UmlUimLinks.getInstance(c).getId(c) + suffix;
		UimForm form = getFormFor(resourceUri, "uim");
		ClassForm panel;
		if(regenerate || form.getPanel() == null){
			panel = UimFactory.eINSTANCE.createClassForm();
			Diagram diagrams = recreateDiagrams(resourceUri,panel);
			initForm(form, c, panel);
			panel.setName(c.getName() + suffix);
			FormCreator fc = new FormCreator(panel, diagrams);
			Collection<Property> allAttributes = SafeUmlUimLinks.getInstance(c).getOwnedAttributes(c);
			fc.prepareFormPanel((actionKinds[0] == ActionKind.UPDATE ? "Edit " : "Create") + NameConverter.separateWords(c.getName()), allAttributes);
			fc.addButtonBar(actionKinds);
		}else{
			panel= (ClassForm) form.getPanel();
		}
		putFormElements(panel);
		panel.setFolder(folder);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		EntityFolder ef = (EntityFolder) UmlUimLinks.getInstance(o).getFolderFor(o.getClass_());
		if(UimUtil.isTask(o)){
			String resourceUri = UmlUimLinks.getInstance(o).getId(o) + "Task";
			UimForm form = getFormFor(resourceUri, "uim");
			OperationTaskForm otf;
			if(regenerate || form.getPanel() == null){
				otf = UimFactory.eINSTANCE.createOperationTaskForm();
				Diagram diagrams = recreateDiagrams(resourceUri,otf);
				initForm(form, o, otf);
				// TODO make input entities editable through inputs tab per entity
				FormCreator fc = new FormCreator(otf, diagrams);
				fc.prepareFormPanel("Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				fc.addButtonBar(ActionKind.COMPLETE_TASK, ActionKind.POSTPONE_TASK, ActionKind.RETURN_TASK);
				otf.setFolder(ef);
			}else{
				otf = (OperationTaskForm) form.getPanel();
				otf.setFolder(ef);
			}
			putFormElements(otf);
		}
		// TODO generate table Panels for multi output parameters and detail panels for single output parameters
		String resourceUri = UmlUimLinks.getInstance(o).getId(o) + "Invoker";
		UimForm form = getFormFor(resourceUri, "uim");
		OperationInvocationForm oif;
		if(regenerate || form.getPanel() == null){
			oif = UimFactory.eINSTANCE.createOperationInvocationForm();
			Diagram diagrams = recreateDiagrams(resourceUri,oif);
			initForm(form, o, oif);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(oif, diagrams);
			fc.prepareFormPanel("Invoke: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
			fc.addButtonBar(ActionKind.EXECUTE_OPERATION, ActionKind.BACK);
			oif.setFolder(ef);
		}else{
			oif = (OperationInvocationForm) form.getPanel();
			oif.setFolder(ef);
		}
		putFormElements(oif);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeState(State s){
		String resourceUri = UmlUimLinks.getId(s);
		StateMachine sm = StateMachineUtil.getStateMachine(s);
		StateMachineFolder smf = (StateMachineFolder) UmlUimLinks.getInstance(s).getFolderFor(sm);
		UimForm form = getFormFor(resourceUri, "uim");
		StateForm sf;
		if(regenerate || form.getPanel() == null){
			sf = UimFactory.eINSTANCE.createStateForm();
			Diagram diagrams = recreateDiagrams(resourceUri, sf);
			initForm(form, s, sf);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(sf, diagrams);
			EList<Property> allAttributes;
			if(sm.getContext() != null && sm.getContext().getClassifierBehavior() == sm){
				allAttributes = sm.getContext().getAllAttributes();
			}else{
				allAttributes = sm.getAllAttributes();
			}
			fc.prepareFormPanel(NameConverter.separateWords(s.getName()), allAttributes);
			fc.addButtonBar(ActionKind.UPDATE, ActionKind.DELETE, ActionKind.BACK);
			sf.setFolder(smf);
		}else{
			sf = (StateForm) form.getPanel();
			sf.setFolder(smf);
		}
		putFormElements(sf);
	}
	private void initForm(UimForm form,NamedElement a,FormPanel sf){
		form.setPanel(sf);
		sf.setUmlElementUid(UmlUimLinks.getId(a));
		sf.setName(a.getName());
	}
	private UimForm getFormFor(String id,String extenstion){
		Resource resource = getResource(id, extenstion);
		if(resource.getContents().isEmpty()){
			resource.getContents().add(UimFactory.eINSTANCE.createUimForm());
		}
		return (UimForm) resource.getContents().get(0);
	}
	private Resource getResource(String id,String extenstion){
		URI formUri = workspace.getDirectoryUri().appendSegment("forms");
		String formId = id;
		formUri = formUri.appendSegment(formId);
		formUri = formUri.appendFileExtension(extenstion);
		Resource resource = null;
		try{
			resource = resourceSet.getResource(formUri, true);
		}catch(RuntimeException e){
			resource = resourceSet.createResource(formUri);
		}
		return resource;
	}
	private void setActiveDiagram(Diagrams diagrams){
		diagrams.setActiveDiagram(diagrams.getDiagrams().isEmpty() ? null : diagrams.getDiagrams().get(0));
		for(Diagrams child:diagrams.getSubdiagrams()){
			setActiveDiagram(child);
		}
	}
	private void putFormElements(FormPanel form){
		UmlUimLinks.getInstance(form).link(form);
		// Optimization
		TreeIterator<EObject> eAllContents = form.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof UmlReference){
				UmlUimLinks.getInstance(form).link((UmlReference) eObject);
			}
		}
	}
}
