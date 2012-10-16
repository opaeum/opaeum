package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetOperationInvocationWizard extends LazyInitializeUimQuery<Operation,OperationInvocationWizard,OperationInvocationWizard>{
	@Override
	protected boolean generateModel(Operation context,FormSynchronizer2 fs2){
		return fs2.beforeOperation(context) instanceof OperationInvocationWizard;
	}
	@Override
	protected OperationInvocationWizard getResult(OperationInvocationWizard eObject){
		return eObject;
	}
}
