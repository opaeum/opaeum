package org.opaeum.uim.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.InstanceEditor;

public class EditorCreator extends AbstractUserInterfaceCreator{
	private AbstractEditor editor;
	public EditorCreator(UserInterfaceResourceFactory rf,AbstractEditor cf){
		super(rf);
		this.editor = cf;
	}
	protected Page addPage(UserInterfaceRoot pc){
		EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
		((AbstractEditor) pc).getPages().add(page);
		return page;
	}
	public void addButtonBar(Collection<Operation> operations,ActionKind...updateCurrentEntity){
		if(editor instanceof InstanceEditor &&  ((InstanceEditor)editor).getActionBar() == null || !((InstanceEditor)editor).getActionBar().isUnderUserControl()){
			ActionBar panel = EditorFactory.eINSTANCE.createActionBar();
			((InstanceEditor)editor).setActionBar(panel);
			panel.setName("ActionBar");
			for(ActionKind actionKind:updateCurrentEntity){
				BuiltInActionButton bia = ActionFactory.eINSTANCE.createBuiltInActionButton();
				bia.setKind(actionKind);
				bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
				panel.getChildren().add(bia);
			}
			for(Operation operation:operations){
				if(!operation.isQuery()){
					InvocationButton button = ActionFactory.eINSTANCE.createInvocationButton();
					button.setUmlElementUid(EmfWorkspace.getId(operation));
					button.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
					panel.getChildren().add(button);
					button.setPopup(getInvocationWizard(operation));
				}
			}
		}
	}
	@Override
	protected UserInterfaceRoot getUserInterfaceRoot(){
		return editor;
	}
}