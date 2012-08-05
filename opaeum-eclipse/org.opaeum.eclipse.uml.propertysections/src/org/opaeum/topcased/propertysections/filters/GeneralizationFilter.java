package org.opaeum.topcased.propertysections.filters;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;

public class GeneralizationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Generalization;
	}
}
