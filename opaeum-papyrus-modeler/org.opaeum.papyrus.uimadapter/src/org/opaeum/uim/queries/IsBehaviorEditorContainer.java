package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Behavior;
import org.opaeum.eclipse.EmfBehaviorUtil;

public class IsBehaviorEditorContainer implements IJavaModelQuery<Behavior,Boolean>{
	public Boolean evaluate(Behavior context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return EmfBehaviorUtil.isProcess(context) || EmfBehaviorUtil.isStandaloneTask(context);
	}
}
