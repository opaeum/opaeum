package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

public class PersistentNameFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Property || e instanceof Component || e instanceof Model || e.eClass().equals(UMLPackage.eINSTANCE.getClass_())
				|| e.eClass().equals(UMLPackage.eINSTANCE.getPackage());
	}
}
