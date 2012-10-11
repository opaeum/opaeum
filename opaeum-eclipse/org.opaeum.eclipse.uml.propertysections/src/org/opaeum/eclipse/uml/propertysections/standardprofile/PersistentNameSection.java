package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class PersistentNameSection extends AbstractStringOnStereotypeSection{
	@Override
	protected Element getElement(EObject e){
		return (Element) e;
	}
	@Override
	protected String getAttributeName(){
		return TagNames.PERSISTENT_NAME;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
	@Override
	protected String getStereotypeName(Element e){
		if(e instanceof Class){
			return StereotypeNames.ENTITY;
		}else if(e instanceof Property){
			if(((Property) e).getAssociation()==null){
				return StereotypeNames.ATTRIBUTE;
			}else{
				return StereotypeNames.ASSOCIATION_END;
			}
		}
		return e.eClass().getName();
	}
	@Override
	public String getLabelText(){
		return "Name in DB:";
	}
}
