package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractEditorContainerQuery;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;

/**
 * FIXME : delete this class when the bug EMF-Facet 365744 will be corrected!
 * 
 */
public class IsUimDiagramContainer extends AbstractEditorContainerQuery implements IJavaModelQuery<UserInteractionElement, Boolean> {


	public Boolean evaluate(UserInteractionElement context, ParameterValueList parameterValues) throws ModelQueryExecutionException {
		return context instanceof UserInterfaceRoot;
	}
}
