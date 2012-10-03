package org.eclipse.papyrus.uml.modelexplorer.queries;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractEditorContainerQuery;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class IsRegenerating extends AbstractEditorContainerQuery implements IJavaModelQuery<EObject,Boolean>{
	public Boolean evaluate(EObject context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		Model model = (Model) context;
		Stereotype st = StereotypesHelper.getStereotype(model, StereotypeNames.MODEL_LIBRARY);
		return Boolean.TRUE.equals(model.getValue(st, "regenerate"));
	}
}