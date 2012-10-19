package org.opaeum.uim.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistryExtended;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractGetEditorIconQuery;

@SuppressWarnings({"restriction","deprecation"})
@Deprecated
public class GetUimDiagramIcon extends AbstractGetEditorIconQuery implements IJavaModelQuery<Diagram,String>{
	public String evaluate(final Diagram context,final ParameterValueList parameterValues) throws ModelQueryExecutionException{
		return "/" + ((IPageIconsRegistryExtended) getEditorRegistry()).getEditorURLIcon(context); //$NON-NLS-1$
	}
}
