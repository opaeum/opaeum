package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.WizardPage;

public class CreateOperationPopupPageCommandHandler extends CreateCommandHandler{
	@Override
	protected EReference getFeature(){
		return WizardPackage.eINSTANCE.getAbstractWizard_Pages();
	}
	@Override
	protected EObject getNewObject(){
		WizardPage p = WizardFactory.eINSTANCE.createWizardPage();
		p.setName("NewPage");
		return p;
	}
}
