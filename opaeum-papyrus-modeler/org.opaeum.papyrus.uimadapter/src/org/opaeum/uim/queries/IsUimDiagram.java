package org.opaeum.uim.queries;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.gmf.runtime.notation.Diagram;

@SuppressWarnings({"restriction","deprecation"})
@Deprecated
public class IsUimDiagram implements IJavaModelQuery<EObject, Boolean> {

	public Boolean evaluate(final EObject context, final ParameterValueList parameterValues) throws ModelQueryExecutionException {

		return context instanceof Diagram;
	}
}
