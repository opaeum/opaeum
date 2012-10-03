package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.ModelFactory;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
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
		if(EmfActionUtil.isEmbeddedTask(a)){
			String resourceUri = EmfWorkspace.getId(a);
			EmbeddedTaskEditor editor = (EmbeddedTaskEditor) getResourceRoot(resourceUri, "uml", ModelFactory.eINSTANCE.createEmbeddedTaskEditor());
			editor.setLinkedUmlResource(a.eResource().getURI().lastSegment());
			editor.setUmlElementUid(resourceUri);
			if(editor.getName() == null || editor.getName().length() == 0){
				editor.setName(NameConverter.separateWords(NameConverter.capitalize(a.getName())) + " Editor");
			}
			if((regenerate || editor.getPages().isEmpty()) && !editor.isUnderUserControl()){
				editor.getPages().clear();
				editor.setUmlElementUid(EmfWorkspace.getId(a));
				editor.setName(a.getName());
				EditorCreator fc = new EditorCreator(this, editor);
				ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a.getInputs());
				pins.addAll(a.getOutputs());
				fc.populateUserInterface(a, fc.getUserInterfaceRoot(), "Task: " + NameConverter.separateWords(a.getName()), pins);
				fc.addButtonBar(Collections.<Operation>emptySet(), ActionKind.CLAIM_TASK, ActionKind.DELEGATE_TASK, ActionKind.FORWARD_TASK, ActionKind.SUSPEND,
						ActionKind.SKIP, ActionKind.ABORT);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeClass(Class c){
		populateClassUserInteractionModel(c, ActionKind.UPDATE, ActionKind.DELETE);
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeComponent(Component c){
		// TODO create external view;
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	private void populateClassUserInteractionModel(Class c,ActionKind...actionKinds){
		String resourceUri = EmfWorkspace.getId(c);
		ClassUserInteractionModel model = getResourceRoot(resourceUri, "uml", ModelFactory.eINSTANCE.createClassUserInteractionModel());
		model.setUmlElementUid(resourceUri);
		model.setLinkedUmlResource(c.eResource().getURI().lastSegment());
		if(model.getName() == null || model.getName().length() == 0){
			model.setName(NameConverter.separateWords(NameConverter.capitalize(c.getName())) + " User Interface Model");
		}
		if((model.getPrimaryEditor() == null || regenerate) && (!model.isUnderUserControl())){
			model.setPrimaryEditor(EditorFactory.eINSTANCE.createObjectEditor());
			model.getPrimaryEditor().setName("Edit " + c.getName());
			model.getPrimaryEditor().setUmlElementUid(resourceUri);
		}
		if(model.getPrimaryEditor() != null && !model.isUnderUserControl()){
			EditorCreator ec = new EditorCreator(this, model.getPrimaryEditor());
			Collection<Property> allAttributes = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c);
			ec.populateUserInterface(c, ec.getUserInterfaceRoot(), "Edit " + NameConverter.separateWords(c.getName()), allAttributes);
			ec.addButtonBar(c.getAllOperations(), actionKinds);
		}
		if((model.getNewObjectWizard() == null || regenerate) && (!model.isUnderUserControl())){
			model.setNewObjectWizard(WizardFactory.eINSTANCE.createNewObjectWizard());
			model.getNewObjectWizard().setName("Create " + c.getName());
			model.getNewObjectWizard().setUmlElementUid(resourceUri);
		}
		if(model.getNewObjectWizard() != null && !model.isUnderUserControl()){
			WizardCreator wc = new WizardCreator(this, model.getNewObjectWizard());
			Collection<Property> allAttributes = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c);
			wc.populateUserInterface(c, wc.getUserInterfaceRoot(), "Create" + NameConverter.separateWords(c.getName()), allAttributes);
		}
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		String resourceUri = EmfWorkspace.getId(o);
		if(EmfBehaviorUtil.isResponsibility(o)){
			ResponsibilityUserInteractionModel model;
			model = getResourceRoot(resourceUri, "uml", ModelFactory.eINSTANCE.createResponsibilityUserInteractionModel());
			model.setUmlElementUid(resourceUri);
			model.setLinkedUmlResource(o.eResource().getURI().lastSegment());
			if(model.getName() == null || model.getName().length() == 0){
				model.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())) + " User Interface Model");
			}
			if((regenerate || model.getViewer() == null) && !model.getViewer().isUnderUserControl()){
				model.setViewer(EditorFactory.eINSTANCE.createResponsibilityViewer());
				model.getViewer().setName(o.getName());
				model.getViewer().setUmlElementUid(resourceUri);
				EditorCreator ec = new EditorCreator(this, model.getViewer());
				ec.populateUserInterface(o, ec.getUserInterfaceRoot(), "Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				ec.addButtonBar(Collections.<Operation>emptySet(), ActionKind.COMPLETE_TASK, ActionKind.SUSPEND);
			}
			if((regenerate || model.getInvocationWizard() == null) && !model.getInvocationWizard().isUnderUserControl()){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createResponsibilityInvocationWizard());
				model.getInvocationWizard().setName("Request" + o.getName());
				model.getInvocationWizard().setUmlElementUid(resourceUri);
				WizardCreator wc = new WizardCreator(this, model.getInvocationWizard());
				wc.populateUserInterface(o, wc.getUserInterfaceRoot(), NameConverter.separateWords(o.getName()), o.getOwnedParameters());
			}
		}else if(o.isQuery() && o.getReturnResult() != null){
			QueryInvoker editor = (QueryInvoker) getResourceRoot(resourceUri, "uml", ModelFactory.eINSTANCE.createQueryInvoker());
			editor.setUmlElementUid(resourceUri);
			editor.setLinkedUmlResource(o.eResource().getURI().lastSegment());
			if(editor.getName() == null || editor.getName().length() == 0){
				editor.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())) + " Query");
			}
			if((regenerate || editor.getPages().isEmpty()) && !editor.isUnderUserControl()){
				editor.getPages().clear();
				editor.setName(o.getName());
				editor.setUmlElementUid(resourceUri);
				EditorCreator ec = new EditorCreator(this, editor);
				ec.populateUserInterface(o, ec.getUserInterfaceRoot(), "Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				ec.addButtonBar(Collections.<Operation>emptySet(), ActionKind.EXECUTE);
			}
		}
	}
	private <T extends AbstractUserInteractionModel>T getResourceRoot(String id,String extenstion,T newOne){
		Resource resource = getResource(id, extenstion);
		if(resource.getContents().isEmpty()){
			resource.getContents().add(newOne);
		}
		return (T) resource.getContents().get(0);
	}
}
