/*
 * 
 */
package org.opaeum.uimodeler.modelexplorer.queries;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.opaeum.uim.UserInteractionElement;

/** get the name + the list of stereotypes from a named Element */
public class GetName implements IJavaModelQuery<UserInteractionElement, String> {
	public String evaluate(final UserInteractionElement context, final ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		// TODO Auto-generated method stub
		return context.getName();
	}
}
