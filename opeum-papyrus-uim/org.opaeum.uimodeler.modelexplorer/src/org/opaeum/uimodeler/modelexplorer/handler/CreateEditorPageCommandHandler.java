package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.OperationPopupPage;

public class CreateEditorPageCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ActionPackage.eINSTANCE.getOperationPopup_Pages();
	}

	@Override
	protected EObject getNewObject(){
		OperationPopupPage p = ActionFactory.eINSTANCE.createOperationPopupPage();
		p.setName("NewPage");
		return p;
	}
}
