package org.opaeum.uim.uml2uim;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.editor.QueryResultPage;
import org.opaeum.uim.model.QueryInvoker;

public class EditorCreator extends AbstractUserInterfaceCreator{
	private AbstractEditor editor;
	public EditorCreator(UserInterfaceResourceFactory rf,AbstractEditor cf){
		super(rf);
		this.editor = cf;
	}
	@Override
	protected void removePage(UserInterfaceRoot container,Page p){
		((AbstractEditor)container).getPages().remove(p);
	}
	protected Page addPage(UserInterfaceRoot pc,NamedElement represented){
		if(pc instanceof QueryInvoker && represented instanceof Parameter && EmfParameterUtil.isResult(((Parameter) represented).getDirection())){
			QueryResultPage page = EditorFactory.eINSTANCE.createQueryResultPage();
			((QueryInvoker) pc).setResultPage(page);
			return page;
		}else{
			EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
			((AbstractEditor) pc).getPages().add(page);
			return page;
		}
	}
	public void addButtonBar(Classifier owner,ActionKind...builtInActionKinds){
		if(editor.getActionBar() == null || !((InstanceEditor) editor).getActionBar().isUnderUserControl()){
			Set<UserInteractionElement> controlledElements = new HashSet<UserInteractionElement>();
			ActionBar panel = findOrCreateActionBar();
			addBuiltInActions(controlledElements, panel, builtInActionKinds);
			for(Operation operation:EmfOperationUtil.getEffectiveOperations(owner)){
				if(!operation.isQuery()){
					findOrCreateOperationButton(controlledElements, panel, operation);
				}
			}
			if(owner instanceof BehavioredClassifier){
				for(Behavior behavior:EmfBehaviorUtil.getEffectiveBehaviors((BehavioredClassifier) owner)){
					findOrCreateOperationButton(controlledElements, panel, behavior);
				}
			}
			UserInterfaceUtil.removeObsoleteElements(controlledElements, panel);
		}
	}
	private ActionBar findOrCreateActionBar(){
		AbstractEditor instanceEditor = (AbstractEditor) editor;
		ActionBar panel;
		if(instanceEditor.getActionBar() == null){
			panel = EditorFactory.eINSTANCE.createActionBar();
			instanceEditor.setActionBar(panel);
			panel.setName("ActionBar");
		}else{
			panel = instanceEditor.getActionBar();
		}
		return panel;
	}
	public void findOrCreateOperationButton(Set<UserInteractionElement> controlledElements,ActionBar panel,Namespace operation){
		EList<UimComponent> children = panel.getChildren();
		InvocationButton button = UserInterfaceUtil.findOrCreateInvocationButton(operation, children);
		if(!button.isUnderUserControl()){
			button.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
			button.setPopup(getInvocationWizard(operation));
		}
		controlledElements.add(button);
	}
	private void addBuiltInActions(Set<UserInteractionElement> controlledElements,ActionBar panel,ActionKind...builtInActionKinds){
		for(ActionKind actionKind:builtInActionKinds){
			EList<UimComponent> children = panel.getChildren();
			BuiltInActionButton bia = UserInterfaceUtil.findOrCreateBuiltInActionButton(actionKind, children);
			controlledElements.add(bia);
			if(!UserInterfaceUtil.isUnderUserControl(bia)){
				bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			}
		}
	}
	@Override
	protected UserInterfaceRoot getUserInterfaceRoot(){
		return editor;
	}
	public void addButtonBar(ActionKind...builtInActionKinds){
		Set<UserInteractionElement> controlledElements = new HashSet<UserInteractionElement>();
		ActionBar panel = findOrCreateActionBar();
		addBuiltInActions(controlledElements, panel, builtInActionKinds);
	}
}