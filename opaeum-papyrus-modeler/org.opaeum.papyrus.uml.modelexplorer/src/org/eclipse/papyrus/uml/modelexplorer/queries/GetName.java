/*
 * 
 */
package org.eclipse.papyrus.uml.modelexplorer.queries;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

/** get the name + the list of stereotypes from a named Element */
public class GetName implements IJavaModelQuery<NamedElement,String>{
	public String evaluate(final NamedElement context,final ParameterValueList parameterValues) throws ModelQueryExecutionException{
		
		for(Stereotype stereotype:context.getAppliedStereotypes()){
			if(stereotype.getQualifiedName().toLowerCase().contains("opaeumbpm")){
				return "<" + stereotype.getName() + "> " + context.getName();
			}
		}
		if(context instanceof Component || context instanceof Behavior){
		}else if(context instanceof org.eclipse.uml2.uml.Class){
			return "<Business Entity> " + context.getName();
		}
		return context.getName();
	}
}
