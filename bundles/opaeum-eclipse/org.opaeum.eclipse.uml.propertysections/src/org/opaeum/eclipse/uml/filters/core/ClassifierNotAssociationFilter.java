package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;

public class ClassifierNotAssociationFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Classifier && !e.eClass().equals(UMLPackage.Literals.ASSOCIATION);
	}
}
