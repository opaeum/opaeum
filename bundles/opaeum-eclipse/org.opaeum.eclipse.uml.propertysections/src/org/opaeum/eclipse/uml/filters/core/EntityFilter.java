package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;

public class EntityFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e.eClass().equals(UMLPackage.eINSTANCE.getClass_());
	}
}
