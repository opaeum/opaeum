package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.OperationPopup;

public class CreateOperationActionPopupCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ActionPackage.eINSTANCE.getOperationButton_Popup();
	}

	@Override
	protected EObject getNewObject(){
		OperationPopup p = org.opaeum.uim.action.ActionFactory.eINSTANCE.createOperationPopup();
//		p.setName("Popup");
		return p;
	}

}
