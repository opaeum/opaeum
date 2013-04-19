package org.opaeum.uimodeler.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.uml.diagram.common.commands.ModelCreationCommandBase;
import org.opaeum.uim.model.ModelFactory;

@Deprecated
public class CreateInstanceEditorCommand extends ModelCreationCommandBase{
	public static final String COMMAND_ID = "uml";
	@Override
	protected EObject createRootElement(){
		return ModelFactory.eINSTANCE.createClassUserInteractionModel();
	}
	@Override
	protected void initializeModel(EObject owner){
		super.initializeModel(owner);
//		EditorPage page = EditorFactory.eINSTANCE.createEditorPage();
//		page.setName("DefaultPage");
//		classEditor.getPages().add(page);
	}
	protected String getModelName(){
		return "InstanceEditor";
	}
}
