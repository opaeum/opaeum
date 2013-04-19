package org.opaeum.papyrus.uml;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class FirstEndAppliedStereotypeSectionWithView extends AssociationEndAppliedStereotypeSectionWithView{
	public FirstEndAppliedStereotypeSectionWithView(){
		// TODO Auto-generated constructor stub
	}
	protected Property getEnd(Association UMLElement){
		return UMLElement.getMemberEnds().get(0);
	}
}
