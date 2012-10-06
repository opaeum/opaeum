package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Behavior;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;

public class GetBehaviorInvocationWizard extends LazyInitializeUimQuery <Behavior,BehaviorUserInteractionModel, BehaviorInvocationWizard>{

	@Override
	protected boolean generateModel(Behavior context,FormSynchronizer2 fs2){
		return fs2.beforeClass(context)  instanceof BehaviorUserInteractionModel;
	}

	@Override
	protected BehaviorInvocationWizard getResult(BehaviorUserInteractionModel eObject){
		return eObject.getInvocationWizard();
	}

}
