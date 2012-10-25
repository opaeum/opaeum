package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;

public class GetResponsibilityInvocationWizard extends LazyInitializeUimQuery <Operation,ResponsibilityUserInteractionModel, ResponsibilityInvocationWizard>{

	@Override
	protected boolean generateModel(Operation context,FormSynchronizer2 fs2){
		return fs2.beforeOperation(context) instanceof ResponsibilityUserInteractionModel;
	}

	@Override
	protected ResponsibilityInvocationWizard getResult(ResponsibilityUserInteractionModel eObject){
		return eObject.getInvocationWizard();
	}

}
