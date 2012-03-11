package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorFactory;

public class CreateSecondaryEditorCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return UimPackage.eINSTANCE.getClassUserInteractionModel_NewObjectWizard();
	}

	@Override
	protected EObject getNewObject(){
		AbstractEditor p = EditorFactory.eINSTANCE.createClassEditor();
		p.setName("SecondaryEditor");
		return p;
	}
}
