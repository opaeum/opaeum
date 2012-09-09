package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class PackageIsSchemaSection extends AbstractBooleanOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return TagNames.IS_SCHEMA;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
	@Override
	protected String getStereotypeName(Element e){
		return e.eClass().getName();
	}
	@Override
	protected String getLabelText(){
		return "Is Schema in DB:";
	}
	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}
}
