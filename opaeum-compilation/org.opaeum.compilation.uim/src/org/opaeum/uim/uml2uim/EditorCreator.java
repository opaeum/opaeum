package org.opaeum.uim.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.action.OperationPopup;
import org.opaeum.uim.action.OperationPopupPage;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;

public class EditorCreator extends AbstractUserInterfaceCreator{
	private AbstractEditor formPanel;
	public EditorCreator(EmfWorkspace w,AbstractEditor cf){
		super(w);
		this.formPanel = cf;
	}
	protected Page addPage(PageContainer pc){
		if(pc instanceof AbstractEditor){
			EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
			((AbstractEditor)pc).getPages().add(page);
			return page;
		}else{
			OperationPopup popup=(OperationPopup) pc;
			OperationPopupPage page = ActionFactory.eINSTANCE.createOperationPopupPage();
			popup.getPages().add(page);
			return page;
		}
	}
	public void addButtonBar(Collection<Operation> operations, ActionKind...updateCurrentEntity){
		EditorActionBar panel = EditorFactory.eINSTANCE.createEditorActionBar();
		formPanel.setActionBar(panel);
		panel.setName("ActionBar");
		for(ActionKind actionKind:updateCurrentEntity){
			BuiltInActionButton bia = ActionFactory.eINSTANCE.createBuiltInActionButton();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			panel.getChildren().add(bia);
		}
		for(Operation operation:operations){
			if(!operation.isQuery() && operation.getReturnResult()==null){
				OperationButton button = ActionFactory.eINSTANCE.createOperationButton();
				button.setUmlElementUid(EmfWorkspace.getId(operation));
				button.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				OperationPopup popup = ActionFactory.eINSTANCE.createOperationPopup();
				button.setPopup(popup);
				prepareFormPanel(button.getPopup(), button.getName(), operation.getOwnedParameters());
				
			}
		}
	}
	@Override
	protected UserInterfaceEntryPoint getUserInterfaceEntryPoint(){
		return formPanel;
	}
}