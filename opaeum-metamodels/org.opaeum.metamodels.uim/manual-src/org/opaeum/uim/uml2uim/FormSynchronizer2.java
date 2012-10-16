package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.ModelFactory;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.wizard.WizardFactory;

public class FormSynchronizer2 extends AbstractUimSynchronizer2{
	public FormSynchronizer2(){
	}
	public FormSynchronizer2(URI workspaceUri,ResourceSet resourceSet,boolean regenerate){
		super(workspaceUri, resourceSet, regenerate);
	}
	public EmbeddedTaskEditor beforeAction(Action a){
		if(EmfActionUtil.isEmbeddedTask(a)){
			String resourceUri = EmfWorkspace.getId(a);
			EmbeddedTaskEditor editor = (EmbeddedTaskEditor) getUserInteractionModel(a, ModelFactory.eINSTANCE.createEmbeddedTaskEditor());
			editor.setLinkedUmlResource(a.eResource().getURI().lastSegment());
			editor.setUmlElementUid(resourceUri);
			applyRegenerate(editor);
			if(!UserInterfaceUtil.isUnderUserControl(editor)){
				if(editor.getName() == null || editor.getName().length() == 0){
					// TODO internationalize - language strategy?, maybe only at runtime?
					editor.setName(NameConverter.separateWords(NameConverter.capitalize(a.getName())) + " Editor");
				}
				EditorCreator fc = new EditorCreator(this, editor);
				ArrayList<TypedElement> pins = new ArrayList<TypedElement>(a.getInputs());
				pins.addAll(a.getOutputs());
				fc.populateUserInterface(a, "Task: " + NameConverter.separateWords(a.getName()), pins);
				fc.addButtonBar(ActionKind.CLAIM_TASK, ActionKind.DELEGATE_TASK, ActionKind.FORWARD_TASK, ActionKind.SUSPEND, ActionKind.SKIP,
						ActionKind.ABORT, ActionKind.RESUME);
				return editor;
			}
		}
		return null;
	}
	private void applyRegenerate(UserInterfaceRoot editor){
		if(regenerate){
			editor.setUnderUserControl(false);
			editor.getPages().clear();
			editor.getPageOrdering().clear();
			if(editor instanceof AbstractEditor){
				((AbstractEditor) editor).setActionBar(EditorFactory.eINSTANCE.createActionBar());
			}
		}
	}
	public AbstractUserInteractionModel beforeClass(Classifier c){
		if(c instanceof Behavior){
			Behavior b = (Behavior) c;
			if(EmfBehaviorUtil.isStandaloneTask(b) || EmfBehaviorUtil.isProcess(b)){
				return populateBehaviorUserInteractionModel(b);
			}
		}else if(EmfClassifierUtil.isPersistentComplexStructure(c)){
			return populateClassUserInteractionModel(c, ActionKind.UPDATE, ActionKind.DELETE);
		}
		return null;
	}
	private AbstractUserInteractionModel populateBehaviorUserInteractionModel(Behavior o){
		String resourceUri = EmfWorkspace.getId(o);
		BehaviorUserInteractionModel model;
		model = getUserInteractionModel(o, ModelFactory.eINSTANCE.createBehaviorUserInteractionModel());
		model.setUmlElementUid(resourceUri);
		model.setLinkedUmlResource(o.eResource().getURI().lastSegment());
		if(!model.isUnderUserControl()){
			if(model.getName() == null || model.getName().length() == 0){
				model.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())) + " User Interface Model");
			}
			if(model.getEditor() == null){
				model.setEditor(EditorFactory.eINSTANCE.createBehaviorExecutionEditor());
			}
			if(!model.getEditor().isUnderUserControl()){
				model.getEditor().setName(o.getName());
				model.getEditor().setUmlElementUid(resourceUri);
				EditorCreator ec = new EditorCreator(this, model.getEditor());
				List<TypedElement> typedElements = new ArrayList<TypedElement>();
				typedElements.addAll(o.getOwnedParameters());
				typedElements.addAll(EmfPropertyUtil.getEffectiveProperties(o));
				List<TypedElement> tes = new ArrayList<TypedElement>();
				tes.addAll(o.getOwnedParameters());
				tes.addAll(EmfPropertyUtil.getEffectiveProperties(o));
				ec.populateUserInterface(o, "Task: " + NameConverter.separateWords(o.getName()), tes);
				if(EmfBehaviorUtil.isProcess(o)){
					ec.addButtonBar(o, ActionKind.ABORT, ActionKind.SUSPEND, ActionKind.RESUME);
				}else{
					ec.addButtonBar(o, ActionKind.CLAIM_TASK, ActionKind.DELEGATE_TASK, ActionKind.FORWARD_TASK, ActionKind.SUSPEND, ActionKind.SKIP,
							ActionKind.ABORT, ActionKind.RESUME);
				}
			}
			if(model.getInvocationWizard() == null){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createBehaviorInvocationWizard());
			}
			if(!model.getInvocationWizard().isUnderUserControl()){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createBehaviorInvocationWizard());
				model.getInvocationWizard().setName("Request" + o.getName());
				model.getInvocationWizard().setUmlElementUid(resourceUri);
				WizardCreator wc = new WizardCreator(this, model.getInvocationWizard());
				wc.populateUserInterface(o, NameConverter.separateWords(o.getName()), EmfBehaviorUtil.getArgumentParameters(o));
			}
		}
		return model;
	}
	private AbstractUserInteractionModel populateClassUserInteractionModel(Classifier c,ActionKind...actionKinds){
		String resourceUri = EmfWorkspace.getId(c);
		ClassUserInteractionModel model = getUserInteractionModel(c, ModelFactory.eINSTANCE.createClassUserInteractionModel());
		model.setUmlElementUid(resourceUri);
		model.setLinkedUmlResource(c.eResource().getURI().lastSegment());
		if(!model.isUnderUserControl()){
			if(model.getName() == null || model.getName().length() == 0){
				model.setName(NameConverter.separateWords(NameConverter.capitalize(c.getName())) + " User Interface Model");
			}
			populatePrimaryEditor(c, resourceUri, model, actionKinds);
			populateNewObjectWizar(c, resourceUri, model);
		}
		return model;
	}
	private void populateNewObjectWizar(Classifier c,String resourceUri,ClassUserInteractionModel model){
		if(model.getNewObjectWizard() == null){
			model.setNewObjectWizard(WizardFactory.eINSTANCE.createNewObjectWizard());
		}
		model.getNewObjectWizard().setUmlElementUid(resourceUri);
		applyRegenerate(model.getNewObjectWizard());
		for(Classifier general:c.getGenerals()){
			addSuperWizard(model, general);
		}
		if(c instanceof BehavioredClassifier){
			EList<Interface> implementedInterfaces = ((BehavioredClassifier) c).getImplementedInterfaces();
			for(Interface interface1:implementedInterfaces){
				addSuperWizard(model, interface1);
			}
		}
		if(!model.getNewObjectWizard().isUnderUserControl()){
			model.getNewObjectWizard().setName("Create " + c.getName());
			WizardCreator wc = new WizardCreator(this, model.getNewObjectWizard());
			Collection<Property> allAttributes = EmfPropertyUtil.getEffectiveProperties(c);
			wc.populateUserInterface(c, "Create" + NameConverter.separateWords(c.getName()), allAttributes);
		}
	}
	private void addSuperWizard(ClassUserInteractionModel model,Classifier general){
		String superId = EmfWorkspace.getId(general);
		ClassUserInteractionModel superModel = getUserInteractionModel(general, ModelFactory.eINSTANCE.createClassUserInteractionModel());
		if(superModel.eResource().getResourceSet() == null){
			populateNewObjectWizar(general, superId, superModel);
		}
		model.getNewObjectWizard().getSuperUserInterfaces().add(superModel.getNewObjectWizard());
	}
	private void populatePrimaryEditor(Classifier c,String resourceUri,ClassUserInteractionModel model,ActionKind...actionKinds){
		if(model.getPrimaryEditor() == null){
			model.setPrimaryEditor(EditorFactory.eINSTANCE.createObjectEditor());
		}
		model.getPrimaryEditor().setUmlElementUid(resourceUri);
		applyRegenerate(model.getPrimaryEditor());
		for(Classifier general:c.getGenerals()){
			addSuperEditor(model, general, actionKinds);
		}
		if(c instanceof BehavioredClassifier){
			EList<Interface> implementedInterfaces = ((BehavioredClassifier) c).getImplementedInterfaces();
			for(Interface interface1:implementedInterfaces){
				addSuperEditor(model, interface1, actionKinds);
			}
		}
		if(!model.getPrimaryEditor().isUnderUserControl()){
			model.getPrimaryEditor().setName("Edit " + c.getName());
			EditorCreator ec = new EditorCreator(this, model.getPrimaryEditor());
			Collection<Property> allAttributes = EmfPropertyUtil.getEffectiveProperties(c);
			ec.populateUserInterface(c, "Edit " + NameConverter.separateWords(c.getName()), allAttributes);
			ec.addButtonBar(c, actionKinds);
		}
	}
	private void addSuperEditor(ClassUserInteractionModel model,Classifier general,ActionKind...actionKinds){
		String superId = EmfWorkspace.getId(general);
		ClassUserInteractionModel superModel = getUserInteractionModel(general, ModelFactory.eINSTANCE.createClassUserInteractionModel());
		if(superModel.eResource().getResourceSet() == null){
			// is new
			populatePrimaryEditor(general, superId, superModel, actionKinds);
		}
		model.getPrimaryEditor().getSuperUserInterfaces().add(superModel.getPrimaryEditor());
	}
	public AbstractUserInteractionModel beforeOperation(Operation o){
		String resourceUri = EmfWorkspace.getId(o);
		if(EmfBehaviorUtil.isResponsibility(o)){
			return populateResponsibilityUserInteractionModel(o, resourceUri);
		}else if(o.isQuery() && o.getReturnResult() != null){
			return populateQueryInvoker(o, resourceUri);
		}else{
			return populateOperationInvocationWizard(o, resourceUri);
		}
	}
	private AbstractUserInteractionModel populateOperationInvocationWizard(Operation o,String resourceUri){
		OperationInvocationWizard wizard = (OperationInvocationWizard) getUserInteractionModel(o,
				ModelFactory.eINSTANCE.createOperationInvocationWizard());
		applyRegenerate(wizard);
		wizard.setUmlElementUid(resourceUri);
		wizard.setLinkedUmlResource(o.eResource().getURI().lastSegment());
		if(!wizard.isUnderUserControl()){
			wizard.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())));
			wizard.setName(o.getName());
			wizard.setUmlElementUid(resourceUri);
			WizardCreator ec = new WizardCreator(this, wizard);
			ec.populateUserInterface(o, "Operation: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
		}
		return wizard;
	}
	private QueryInvoker populateQueryInvoker(Operation o,String resourceUri){
		QueryInvoker editor = (QueryInvoker) getUserInteractionModel(o, ModelFactory.eINSTANCE.createQueryInvoker());
		applyRegenerate(editor);
		editor.setUmlElementUid(resourceUri);
		editor.setLinkedUmlResource(o.eResource().getURI().lastSegment());
		if(!editor.isUnderUserControl()){
			editor.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())) + " Query");
			editor.setName(o.getName());
			editor.setUmlElementUid(resourceUri);
			EditorCreator ec = new EditorCreator(this, editor);
			ec.populateUserInterface(o, "Query: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
			ec.addButtonBar(ActionKind.EXECUTE);
		}
		return editor;
	}
	private ResponsibilityUserInteractionModel populateResponsibilityUserInteractionModel(Operation o,String resourceUri){
		ResponsibilityUserInteractionModel model;
		model = getUserInteractionModel(o, ModelFactory.eINSTANCE.createResponsibilityUserInteractionModel());
		model.setUmlElementUid(resourceUri);
		model.setLinkedUmlResource(o.eResource().getURI().lastSegment());
		if(!model.isUnderUserControl()){
			if(model.getName() == null || model.getName().length() == 0){
				model.setName(NameConverter.separateWords(NameConverter.capitalize(o.getName())) + " User Interface Model");
			}
			if(model.getViewer() == null){
				model.setViewer(EditorFactory.eINSTANCE.createResponsibilityViewer());
			}
			model.getViewer().setUmlElementUid(resourceUri);
			applyRegenerate(model.getViewer());
			if(!model.getViewer().isUnderUserControl()){
				model.getViewer().setName(o.getName());
				EditorCreator ec = new EditorCreator(this, model.getViewer());
				ec.populateUserInterface(o, "Task: " + NameConverter.separateWords(o.getName()), o.getOwnedParameters());
				ec.addButtonBar(ActionKind.ABORT, ActionKind.SUSPEND, ActionKind.RESUME);
			}
			if(model.getInvocationWizard() == null){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createResponsibilityInvocationWizard());
			}
			model.getInvocationWizard().setUmlElementUid(resourceUri);
			applyRegenerate(model.getInvocationWizard());
			if(!model.getInvocationWizard().isUnderUserControl()){
				model.getInvocationWizard().setName("Request" + o.getName());
				WizardCreator wc = new WizardCreator(this, model.getInvocationWizard());
				wc.populateUserInterface(o, NameConverter.separateWords(o.getName()), EmfParameterUtil.getArguments(o));
			}
		}
		return model;
	}
	@SuppressWarnings("unchecked")
	public <T extends AbstractUserInteractionModel>T getUserInteractionModel(Element element,T newOne){
		Resource resource = getResource(element);
		if(resource.getContents().isEmpty()){
			resource.getContents().add(newOne);
		}else if(!newOne.getClass().isInstance(resource.getContents().get(0))){
			resource.getContents().set(0, newOne);
		}
		return (T) resource.getContents().get(0);
	}
}
