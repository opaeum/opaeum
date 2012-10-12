package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.model.ModelPackage;

public class CreateSecondaryEditorCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ModelPackage.eINSTANCE.getClassUserInteractionModel_NewObjectWizard();
	}

	@Override
	protected EObject getNewObject(){
		AbstractEditor p = EditorFactory.eINSTANCE.createObjectEditor();
		p.setName("SecondaryEditor");
		return p;
	}
}
