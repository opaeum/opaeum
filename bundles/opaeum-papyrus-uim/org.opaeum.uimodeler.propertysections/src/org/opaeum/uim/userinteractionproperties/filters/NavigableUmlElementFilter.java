package org.opaeum.uim.userinteractionproperties.filters;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;
import org.opaeum.uimodeler.common.IExplorerMap;

public class NavigableUmlElementFilter extends AbstractEObjectFilter<Element>{
	@Override
	public boolean select(Element e){
		return e.eResource().getResourceSet() instanceof IExplorerMap && ((IExplorerMap)e.eResource().getResourceSet()).getConstraintFor(e)!=null;
	}
}
