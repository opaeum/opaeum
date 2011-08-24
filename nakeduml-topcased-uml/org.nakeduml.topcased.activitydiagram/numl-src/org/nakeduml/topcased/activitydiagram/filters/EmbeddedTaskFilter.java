package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

public class EmbeddedTaskFilter extends AbstractFilter{
	public boolean select(Element e){
		if(e instanceof OpaqueAction){
			return StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		}else if(e instanceof CallBehaviorAction){
			return StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		}else{
			return false;
		}
	}
}
