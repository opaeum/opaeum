package org.opaeum.uimodeler.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.ModelCreationCommandBase;
import org.opaeum.uim.editor.EditorFactory;

public class CreateEditorPageCommand extends ModelCreationCommandBase{
	public static final String COMMAND_ID = "uml";
	@Override
	protected EObject createRootElement(){
		return EditorFactory.eINSTANCE.createEditorPage();
	}
	@Override
	protected void initializeModel(EObject owner){
		super.initializeModel(owner);
	}
	protected String getModelName(){
		return "model";
	}
}
