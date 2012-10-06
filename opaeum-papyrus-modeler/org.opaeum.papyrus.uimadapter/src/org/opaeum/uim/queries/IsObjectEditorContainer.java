package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.opaeum.eclipse.EmfClassifierUtil;

public class IsObjectEditorContainer implements IJavaModelQuery<Classifier,Boolean>{
	public Boolean evaluate(Classifier context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		if(context instanceof Behavior || context instanceof DataType){
			return false;
		}else{
			return EmfClassifierUtil.isPersistentComplexStructure(context);
		}
	}
}
