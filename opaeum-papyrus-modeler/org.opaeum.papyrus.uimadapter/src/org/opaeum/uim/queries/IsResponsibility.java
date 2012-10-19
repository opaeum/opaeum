package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

@SuppressWarnings({"deprecation","restriction"})
public class IsResponsibility implements IJavaModelQuery<Operation,Boolean>{
	public Boolean evaluate(Operation context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return EmfOperationUtil.isResponsibility(context) && OpaeumEclipseContext.getContextFor(context).getConfig().isUiModelerActive();
	}
}
