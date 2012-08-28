package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ClassNamePropertySection extends ClassifierNamePropertySection{

	@Override
	public String getStereotypeName(){
		return StereotypeNames.ENTITY;
	}
}
