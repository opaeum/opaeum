package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.WizardFactory;

public class CreateNewObjectWizardCommandHandler extends CreateCommandHandler{

	@Override
	protected EReference getFeature(){
		return UimPackage.eINSTANCE.getClassUserInteractionModel_SecondaryEditors();
	}

	@Override
	protected EObject getNewObject(){
		NewObjectWizard p = WizardFactory.eINSTANCE.createNewObjectWizard();
		p.setName("SecondaryEditor");
		return p;
	}
}
