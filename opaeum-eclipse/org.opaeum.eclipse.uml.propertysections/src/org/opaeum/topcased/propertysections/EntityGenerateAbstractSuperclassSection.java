package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.base.AbstractBooleanOnStereotypeSection;
import org.opaeum.topcased.propertysections.base.AbstractStringOnStereotypeSection;

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
