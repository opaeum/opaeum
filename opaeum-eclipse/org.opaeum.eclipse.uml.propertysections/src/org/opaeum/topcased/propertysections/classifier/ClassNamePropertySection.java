package org.opaeum.topcased.propertysections.classifier;

import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ClassNamePropertySection extends ClassifierNamePropertySection{

	@Override
	public String getStereotypeName(){
		return StereotypeNames.ENTITY;
	}
}
