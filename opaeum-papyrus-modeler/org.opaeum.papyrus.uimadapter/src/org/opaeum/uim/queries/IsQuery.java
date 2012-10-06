package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfOperationUtil;

public class IsQuery implements IJavaModelQuery<Operation,Boolean>{
	public Boolean evaluate(Operation context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return context.isQuery();
	}
}
