package org.opaeum.uim.uml2uim;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPage;

public class EditorCreator extends AbstractUserInterfaceCreator{
	private AbstractEditor formPanel;
	public EditorCreator(EmfWorkspace w, AbstractEditor cf){
		super(w);
		this.formPanel = cf;
	}
	protected Page addPage(){
		EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
		formPanel.getPages().add(page);
		return page;
	}
	public void addButtonBar(ActionKind...updateCurrentEntity){
		EditorActionBar panel = EditorFactory.eINSTANCE.createEditorActionBar();
		formPanel.setActionBar(panel);
		for(ActionKind actionKind:updateCurrentEntity){
			BuiltInAction bia = ActionFactory.eINSTANCE.createBuiltInAction();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			panel.getChildren().add(bia);
		}
	}
	@Override
	protected UserInterfaceEntryPoint getUserInterfaceEntryPoint(){
		return formPanel;
	}
}