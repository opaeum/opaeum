package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfOperationUtil;

public class IsEmbeddedTask implements IJavaModelQuery<Action,Boolean>{
	public Boolean evaluate(Action context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return EmfActionUtil.isEmbeddedTask(context);
	}
}
