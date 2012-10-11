/*
 * 
 */
package org.opaeum.papyrus.uml.modelexplorer.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.papyrus.uml.modelexplorer.OpaeumLabelProvider;

/** get the name + the list of stereotypes from a named Element */
public class GetName implements IJavaModelQuery<NamedElement,String>{
	OpaeumLabelProvider labelProvider = new OpaeumLabelProvider();
	public String evaluate(final NamedElement context,final ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return labelProvider.getText(context);
	}
}
