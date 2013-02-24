package org.opaeum.uim.userinteractionproperties.filters.uml;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class DisplayableUmlElementFilter extends AbstractEObjectFilter<Element>{
	@Override
	public boolean select(Element e){
		return e.eResource().getResourceSet() instanceof IUIElementMapMap && ((IUIElementMapMap) e.eResource().getResourceSet()).getElementFor(e) != null;
	}
}
