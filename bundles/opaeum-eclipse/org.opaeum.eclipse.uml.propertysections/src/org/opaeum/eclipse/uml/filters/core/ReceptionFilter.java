package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Reception;

public class ReceptionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Reception;
	}
}
