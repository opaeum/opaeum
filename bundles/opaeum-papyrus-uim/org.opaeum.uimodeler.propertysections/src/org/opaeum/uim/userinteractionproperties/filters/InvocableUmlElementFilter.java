package org.opaeum.uim.userinteractionproperties.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class InvocableUmlElementFilter extends AbstractEObjectFilter<Element>{
	@Override
	public boolean select(Element e){
		boolean isMapped = e.eResource().getResourceSet() instanceof IUIElementMapMap && ((IUIElementMapMap) e.eResource().getResourceSet()).getElementFor(e) != null;
		if(isMapped){
			if(e instanceof Operation){
				return !((Operation) e).isQuery();
			}
		}
		return false;
	}
}
