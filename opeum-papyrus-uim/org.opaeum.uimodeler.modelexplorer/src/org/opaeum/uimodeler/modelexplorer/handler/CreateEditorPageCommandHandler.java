package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;

public class CreateEditorPageCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		// TODO Auto-generated method stub
		return EditorPackage.eINSTANCE.getAbstractEditor_Pages();
	}

	@Override
	protected EObject getNewObject(){
		EditorPage p = EditorFactory.eINSTANCE.createEditorPage();
		p.setName("NewPage");
		return p;
	}
}
