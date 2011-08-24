package org.nakeduml.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EAttribute;



public class ActionPotentialOwnersSection extends AbstractArtificialOpaqueExpressionSection{
	public String getExpressionName(){
		return "potentialOwners";
	}
	@Override
	public String getStereotypeName(){
		return "EmbeddedSingleScreenTask";
	}

}
