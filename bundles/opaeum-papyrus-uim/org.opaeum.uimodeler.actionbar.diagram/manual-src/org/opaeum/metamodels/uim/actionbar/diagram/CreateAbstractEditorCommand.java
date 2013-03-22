package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.uml.diagram.common.commands.ModelCreationCommandBase;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.InstanceEditor;

public class CreateAbstractEditorCommand extends ModelCreationCommandBase{
	public static final String COMMAND_ID = "uml";
	@Override
	protected EObject createRootElement(){
		return EditorFactory.eINSTANCE.createObjectEditor();
	}
	@Override
	protected void initializeModel(EObject owner){
		super.initializeModel(owner);
		InstanceEditor classEditor = (InstanceEditor) owner;
		classEditor.setName(getModelName());
//		EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
//		page.setName("DefaultPage");
//		classEditor.getPages().add(page);
	}
	protected String getModelName(){
		return "InstanceEditor";
	}
}
