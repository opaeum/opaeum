package org.opeum.uml2uim;

import java.util.ArrayList;
import java.util.Collection;

import org.opeum.emf.workspace.EmfWorkspace;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TypedElement;
import org.opeum.eclipse.EmfBehaviorUtil;
import org.opeum.eclipse.EmfElementFinder;
import org.opeum.eclipse.EmfStateMachineUtil;
import org.opeum.name.NameConverter;
import org.opeum.uim.action.ActionKind;
import org.opeum.uim.folder.ActivityFolder;
import org.opeum.uim.folder.EntityFolder;
import org.opeum.uim.folder.StateMachineFolder;
import org.opeum.uim.form.ActionTaskForm;
import org.opeum.uim.form.ClassForm;
import org.opeum.uim.form.FormFactory;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.form.OperationInvocationForm;
import org.opeum.uim.form.OperationTaskForm;
import org.opeum.uim.form.StateForm;
import org.opeum.uim.form.UimForm;
import org.opeum.uim.util.UmlUimLinks;

@StepDependency(phase = UimSynchronizationPhase.class,requires = FormFolderSynchronizer.class,after = FormFolderSynchronizer.class)
public class FormSynchronizer extends AbstractUimSynchronizer{
	public FormSynchronizer(){
	}
	public FormSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		super(workspace, resourceSet, regenerate);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeAction(OpaqueAction a){
		String resourceUri = workspace.getId(a);
		UimForm form = getFormFor(resourceUri, "uim");
		ActionTaskForm atf = null;
		if(regenerate || form.getPanel() == null){
			atf = FormFactory.eINSTANCE.createActionTaskForm();
			initForm(form, a, atf);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(workspace, atf);
			ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a.getInputs());
			pins.addAll(a.getOutputs());
			fc.prepareFormPanel("Task: " + NameConverter.separateWords(a.getName()), pins);
			fc.addButtonBar(ActionKind.CLAIM_TASK, ActionKind.DELEGATE_TASK, ActionKind.FORWARD_TASK, ActionKind.SUSPEND_TASK);
		}else{
			atf = (ActionTaskForm) form.getPanel();
		}
		atf.setFolder((ActivityFolder) getFolderFor(a.getActivity()));
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeClass(Class c){
		EntityFolder folder = (EntityFolder) getFolderFor(c.getNamespace());
		createClassForm(c, folder, ActionKind.UPDATE, ActionKind.DELETE, ActionKind.BACK);
		createClassForm(c, folder, ActionKind.CREATE, ActionKind.BACK);
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeComponent(Component c){
		// TODO create external view;
	}
	private void createClassForm(Class c,EntityFolder folder,ActionKind...actionKinds){
		String suffix = actionKinds[0] == ActionKind.UPDATE ? "Editor" : "Creator";
		String resourceUri = workspace.getId(c) + suffix;
		UimForm form = getFormFor(resourceUri, "uim");
		ClassForm panel;
		if(regenerate || form.getPanel() == null){
			panel = FormFactory.eINSTANCE.createClassForm();
			initForm(form, c, panel);
			panel.setName(c.getName() + suffix);
			FormCreator fc = new FormCreator(workspace, panel);
			UmlUimLinks r = this.links;
			Collection<Property> allAttributes = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c);
			fc.prepareFormPanel((actionKinds[0] == ActionKind.UPDATE ? "Edit " : "Create") + NameConverter.separateWords(c.getName()), allAttributes);
			fc.addButtonBar(actionKinds);
		}else{
			panel = (ClassForm) form.getPanel();
		}
		panel.setFolder(folder);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		EntityFolder ef = (EntityFolder) getFolderFor(o.getClass_());
		if(EmfBehaviorUtil.isTask(o)){
			String resourceUri = workspace.getId(o) + "Task";
			UimForm form = getFormFor(resourceUri, "uim");
			OperationTaskForm otf;
			if(regenerate || form.getPanel() == null){
				otf = FormFactory.eINSTANCE.createOperationTaskForm();
				initForm(form, o, otf);
				// TODO make input entities editable through inputs tab per entity
				FormCreator fc = new FormCreator(workspace, otf);
				fc.prepareFormPanel("Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				fc.addButtonBar(ActionKind.COMPLETE_TASK, ActionKind.SUSPEND_TASK);
				otf.setFolder(ef);
			}else{
				otf = (OperationTaskForm) form.getPanel();
				otf.setFolder(ef);
			}
		}
		// TODO generate table Panels for multi output parameters and detail panels for single output parameters
		String resourceUri = workspace.getId(o) + "Invoker";
		UimForm form = getFormFor(resourceUri, "uim");
		OperationInvocationForm oif;
		if(regenerate || form.getPanel() == null){
			oif = FormFactory.eINSTANCE.createOperationInvocationForm();
			initForm(form, o, oif);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(workspace, oif);
			fc.prepareFormPanel("Invoke: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
			fc.addButtonBar(ActionKind.EXECUTE_OPERATION, ActionKind.BACK);
			oif.setFolder(ef);
		}else{
			oif = (OperationInvocationForm) form.getPanel();
			oif.setFolder(ef);
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeState(State s){
		String resourceUri = workspace.getId(s);
		StateMachine sm = EmfStateMachineUtil.getStateMachine(s);
		StateMachineFolder smf = (StateMachineFolder) getFolderFor(sm);
		UimForm form = getFormFor(resourceUri, "uim");
		StateForm sf;
		if(regenerate || form.getPanel() == null){
			sf = FormFactory.eINSTANCE.createStateForm();
			initForm(form, s, sf);
			// TODO make input entities editable through inputs tab per entity
			FormCreator fc = new FormCreator(workspace, sf);
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
	}
	private void initForm(UimForm form,NamedElement a,FormPanel sf){
		form.setPanel(sf);
		sf.setUmlElementUid(workspace.getId(a));
		sf.setName(a.getName());
	}
	private UimForm getFormFor(String id,String extenstion){
		Resource resource = getResource(id, extenstion);
		if(resource.getContents().isEmpty()){
			resource.getContents().add(FormFactory.eINSTANCE.createUimForm());
		}
		return (UimForm) resource.getContents().get(0);
	}
}
