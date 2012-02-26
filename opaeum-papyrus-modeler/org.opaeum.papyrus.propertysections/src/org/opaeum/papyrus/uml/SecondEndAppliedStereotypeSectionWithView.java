package org.opaeum.papyrus.uml;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class SecondEndAppliedStereotypeSectionWithView extends AssociationEndAppliedStereotypeSectionWithView{
	public SecondEndAppliedStereotypeSectionWithView(){
		// TODO Auto-generated constructor stub
	}
	protected Property getEnd(Association UMLElement){
		return UMLElement.getMemberEnds().get(1);
	}
}
