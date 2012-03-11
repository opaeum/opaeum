package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.EditorFactory;

public class CreatePrimaryEditorCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return UimPackage.eINSTANCE.getClassUserInteractionModel_PrimaryEditor();
	}

	@Override
	protected EObject getNewObject(){
		ClassEditor p = EditorFactory.eINSTANCE.createClassEditor();
		p.setName("PrimaryEditor");
		return p;
	}
}
