package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.OperationActionPopup;

public class CreateOperationActionPopupCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ActionPackage.eINSTANCE.getOperationAction_Popup();
	}

	@Override
	protected EObject getNewObject(){
		OperationActionPopup p = org.opaeum.uim.action.ActionFactory.eINSTANCE.createOperationActionPopup();
		p.setName("Popup");
		return p;
	}

}
