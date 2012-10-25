package org.opaeum.uim.queries;

import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.uml2uim.FormSynchronizer2;

public class GetQueryInvoker extends LazyInitializeUimQuery<Operation,QueryInvoker,QueryInvoker>{

	@Override
	protected boolean generateModel(Operation context,FormSynchronizer2 fs2){
		return fs2.beforeOperation(context) instanceof QueryInvoker;
	}
	@Override
	protected QueryInvoker getResult(QueryInvoker eObject){
		return eObject;
	}
}
