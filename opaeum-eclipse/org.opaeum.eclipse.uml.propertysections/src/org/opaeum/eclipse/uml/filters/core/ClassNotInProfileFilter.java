package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfBehaviorUtil;

public class ClassNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof org.eclipse.uml2.uml.Class &&  e.getModel()!=null && !(e instanceof Behavior && !EmfBehaviorUtil.hasExecutionInstance((Behavior) e));
	}
}
