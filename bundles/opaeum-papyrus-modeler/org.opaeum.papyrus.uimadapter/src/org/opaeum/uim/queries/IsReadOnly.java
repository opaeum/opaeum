package org.opaeum.uim.queries;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;

@SuppressWarnings({"restriction","deprecation"})
@Deprecated
public class IsReadOnly implements IJavaModelQuery<EObject,Boolean>{
	public Boolean evaluate(EObject context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(context);
		if(editingDomain != null){
			if(editingDomain.isReadOnly(context.eResource())){
				return true;
			}
		}
		return false;
	}
}
