package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Element;

public class BehavioredClassifierNotBehaviorFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof BehavioredClassifier){
			return !(e instanceof Behavior);
		}
		return false;
	}
}
