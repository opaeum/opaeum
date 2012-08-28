package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

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
