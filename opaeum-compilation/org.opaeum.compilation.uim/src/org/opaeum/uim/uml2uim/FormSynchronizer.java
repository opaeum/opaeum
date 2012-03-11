package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.ResponsibilityUserInteractionModel;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.wizard.WizardFactory;

@StepDependency(phase = UimSynchronizationPhase.class)
public class FormSynchronizer extends AbstractUimSynchronizer{
	public FormSynchronizer(){
	}
	public FormSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		super(workspace, resourceSet, regenerate);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeAction(OpaqueAction a){
		String resourceUri = workspace.getId(a);
		ActionTaskEditor editor = (ActionTaskEditor) getResourceRoot(resourceUri, "uml", EditorFactory.eINSTANCE.createActionTaskEditor());
		if(regenerate || editor.getPages().isEmpty()){
			editor.setUmlElementUid(workspace.getId(a));
			editor.setName(a.getName());
			// TODO make input entities editable through inputs tab per entity
			EditorCreator fc = new EditorCreator(workspace, editor);
			ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a.getInputs());
			pins.addAll(a.getOutputs());
			fc.prepareFormPanel("Task: " + NameConverter.separateWords(a.getName()), pins);
			fc.addButtonBar(ActionKind.CLAIM_TASK, ActionKind.DELEGATE_TASK, ActionKind.FORWARD_TASK, ActionKind.SUSPEND_TASK);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeClass(Class c){
		createClassForm(c, ActionKind.UPDATE, ActionKind.DELETE);
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeComponent(Component c){
		// TODO create external view;
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	private void createClassForm(Class c,ActionKind...actionKinds){
		String resourceUri = workspace.getId(c);
		ClassUserInteractionModel model = (ClassUserInteractionModel) getResourceRoot(resourceUri, "uml",
				UimFactory.eINSTANCE.createClassUserInteractionModel());
		model.setUmlElementUid(resourceUri);
		if(model.getPrimaryEditor() == null || regenerate){
			model.setPrimaryEditor(EditorFactory.eINSTANCE.createClassEditor());
			model.getPrimaryEditor().setName("Edit" + c.getName());
			model.getPrimaryEditor().setUmlElementUid(resourceUri);
			EditorCreator ec = new EditorCreator(workspace, model.getPrimaryEditor());
			Collection<Property> allAttributes = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c);
			ec.prepareFormPanel("Edit " + NameConverter.separateWords(c.getName()), allAttributes);
			ec.addButtonBar(actionKinds);
		}
		if(model.getNewObjectWizard() == null || regenerate){
			model.setNewObjectWizard(WizardFactory.eINSTANCE.createNewObjectWizard());
			model.getNewObjectWizard().setName("Create" + c.getName());
			model.getNewObjectWizard().setUmlElementUid(resourceUri);
			WizardCreator wc = new WizardCreator(workspace, model.getNewObjectWizard());
			Collection<Property> allAttributes = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c);
			wc.prepareFormPanel("Create" + NameConverter.separateWords(c.getName()), allAttributes);
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		String resourceUri = workspace.getId(o);
		if(EmfBehaviorUtil.isTask(o)){
			ResponsibilityUserInteractionModel model = (ResponsibilityUserInteractionModel) getResourceRoot(resourceUri, "uml",
					UimPackage.eINSTANCE.getResponsibilityUserInteractionModel());
			if(regenerate || model.getTaskEditor() == null){
				model.setTaskEditor(EditorFactory.eINSTANCE.createResponsibilityTaskEditor());
				model.getTaskEditor().setName(o.getName());
				model.getTaskEditor().setUmlElementUid(resourceUri);
				EditorCreator ec = new EditorCreator(workspace, model.getTaskEditor());
				ec.prepareFormPanel("Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				ec.addButtonBar(ActionKind.COMPLETE_TASK, ActionKind.SUSPEND_TASK);
			}
			if(regenerate || model.getInvocationWizard() == null){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createInvokeResponsibilityWizard());
				model.getInvocationWizard().setName("Request" + o.getName());
				model.getInvocationWizard().setUmlElementUid(resourceUri);
				WizardCreator wc = new WizardCreator(workspace, model.getInvocationWizard());
				wc.prepareFormPanel(NameConverter.separateWords(o.getName()), o.getOwnedParameters());
			}
		}else if(o.isQuery() && o.getReturnResult() != null){
			QueryInvocationEditor editor = (QueryInvocationEditor) getResourceRoot(resourceUri, "uml",
					EditorFactory.eINSTANCE.createQueryInvocationEditor());
			if(regenerate || editor.getPages().isEmpty()){
				editor.getPages().clear();
				editor.setName(o.getName());
				editor.setUmlElementUid(resourceUri);
				EditorCreator ec = new EditorCreator(workspace, editor);
				ec.prepareFormPanel("Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				ec.addButtonBar(ActionKind.EXECUTE_OPERATION);
			}
		}
	}
	private UserInteractionElement getResourceRoot(String id,String extenstion,EObject newOne){
		Resource resource = getResource(id, extenstion);
		if(resource.getContents().isEmpty()){
			resource.getContents().add(newOne);
		}
		return (UserInteractionElement) resource.getContents().get(0);
	}
}
