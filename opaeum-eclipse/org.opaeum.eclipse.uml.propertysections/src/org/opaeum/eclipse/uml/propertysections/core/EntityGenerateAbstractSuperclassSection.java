package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EntityGenerateAbstractSuperclassSection extends AbstractBooleanOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return "generateAbstractSupertype";
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE_TOPCASED;
	}
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.ENTITY;
	}
	@Override
	protected String getLabelText(){
		return "Generate superclass only:";
	}
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}
}
