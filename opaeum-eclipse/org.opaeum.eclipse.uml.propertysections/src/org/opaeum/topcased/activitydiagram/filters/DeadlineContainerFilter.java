package org.opaeum.topcased.activitydiagram.filters;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;

public class DeadlineContainerFilter extends AbstractFilter{
	public boolean select(Element e){
		if(e instanceof OpaqueAction){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		}else if(e instanceof CallBehaviorAction){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		}else if(e instanceof Operation){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.RESPONSIBILITY);
		}else{
			return false;
		}
	}
}
