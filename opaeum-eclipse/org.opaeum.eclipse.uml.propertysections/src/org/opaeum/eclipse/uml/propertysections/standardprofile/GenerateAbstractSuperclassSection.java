package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class GenerateAbstractSuperclassSection extends AbstractBooleanOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return TagNames.GENERATE_ABSTRACT_SUPERTYPE;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
	@Override
	protected String getStereotypeName(Element e){
		if(e instanceof org.eclipse.uml2.uml.Class){
			return StereotypeNames.ENTITY;
		}else{
			return e.eClass().getName();
		}
	}
	@Override
	public String getLabelText(){
		return "Generate superclass only:";
	}
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}
}
