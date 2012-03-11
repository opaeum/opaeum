package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.ModelCreationCommandBase;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.EditorFactory;

public class CreateAbstractEditorCommand extends ModelCreationCommandBase{
	public static final String COMMAND_ID = "uml";
	@Override
	protected EObject createRootElement(){
		return EditorFactory.eINSTANCE.createClassEditor();
	}
	@Override
	protected void initializeModel(EObject owner){
		super.initializeModel(owner);
		ClassEditor classEditor = (ClassEditor) owner;
		classEditor.setName(getModelName());
//		EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
//		page.setName("DefaultPage");
//		classEditor.getPages().add(page);
	}
	protected String getModelName(){
		return "ClassEditor";
	}
}
