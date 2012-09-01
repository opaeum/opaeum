package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class DeadlineContainerFilter extends AbstractFilter{
	public boolean select(Element e){
		if(e instanceof OpaqueAction){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		}else if(e instanceof CallBehaviorAction){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		}else if(e instanceof Operation){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.RESPONSIBILITY);
		}else if(e instanceof OpaqueBehavior){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
		}else if(e instanceof StateMachine){
			return StereotypesHelper.hasStereotype(e, StereotypeNames.STANDALONE_SCREENFLOW_TASK);
		}else{
			return false;
		}
	}
}
