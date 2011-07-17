package org.nakeduml.topcased.activitydiagram.filters;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.topcased.propertysections.filters.AbstractFilter;

public class EmbeddedTaskFilter extends AbstractFilter{
	public boolean select(Element e){
		if(e instanceof Action){
			return StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK)
					|| StereotypesHelper.hasKeyword(e, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		}else{
			return false;
		}
	}
}
