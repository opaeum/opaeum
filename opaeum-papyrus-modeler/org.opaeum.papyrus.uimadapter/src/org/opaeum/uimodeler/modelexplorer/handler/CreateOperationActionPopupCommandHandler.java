package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.action.ActionPackage;

public class CreateOperationActionPopupCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return ActionPackage.eINSTANCE.getInvocationButton_Popup();
	}

	@Override
	protected EObject getNewObject(){
		throw new RuntimeException();
	}

}
