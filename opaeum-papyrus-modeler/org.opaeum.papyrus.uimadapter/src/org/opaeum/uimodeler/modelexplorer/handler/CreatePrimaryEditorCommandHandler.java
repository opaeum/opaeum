package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.model.ModelPackage;

public class CreatePrimaryEditorCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ModelPackage.eINSTANCE.getClassUserInteractionModel_PrimaryEditor();
	}

	@Override
	protected EObject getNewObject(){
		InstanceEditor p = EditorFactory.eINSTANCE.createObjectEditor();
		p.setName("PrimaryEditor");
		return p;
	}
}
