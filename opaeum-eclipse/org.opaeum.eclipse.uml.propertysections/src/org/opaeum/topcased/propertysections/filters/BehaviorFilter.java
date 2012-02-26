package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;

public class BehaviorFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Behavior;
	}
}
