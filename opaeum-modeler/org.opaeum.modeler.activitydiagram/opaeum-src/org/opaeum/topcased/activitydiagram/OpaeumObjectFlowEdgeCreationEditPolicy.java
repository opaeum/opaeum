package org.opaeum.topcased.activitydiagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.activitydiagram.policies.ObjectFlowEdgeCreationEditPolicy;
import org.topcased.modeler.utils.Utils;

public class OpaeumObjectFlowEdgeCreationEditPolicy extends ObjectFlowEdgeCreationEditPolicy{
	protected boolean checkSource(GraphElement source,GraphEdge edge){
		if(super.checkSource(source, edge)==false){
			return false;
		}else{
			return checkSource(source);
		}
	}
	protected boolean checkSource(GraphElement source){
		if(super.checkSource(source) == false){
			return false;
		}else{
			EObject object = Utils.getElement(source);
			if((object instanceof ObjectNode || object instanceof MergeNode || object instanceof JoinNode) && ((ActivityNode) object).getOutgoings().size() > 0){
				return false;
			}else if(object instanceof ForkNode || object instanceof DecisionNode){
				for(ActivityEdge e:((ActivityNode) object).getOutgoings()){
					if(!(e instanceof ObjectFlow)){
						return false;
					}
				}
			}
			if(object instanceof InitialNode){
				return false;
			}else if(object instanceof JoinNode){
				for(ActivityEdge e:((ControlNode) object).getIncomings()){
					if(e instanceof ObjectFlow){
						return true;
					}
				}
				// TODO might cause a chicken egg problem
				return false;
			}else if(object instanceof ControlNode){
				for(ActivityEdge e:((ControlNode) object).getIncomings()){
					if(!(e instanceof ObjectFlow)){
						return false;
					}
				}
			}
			return object instanceof ObjectNode || object instanceof ControlNode;
		}
	}
	protected boolean checkTargetForSource(GraphElement source,GraphElement target,GraphElement edge){
		if(super.checkTargetForSource(source, target, edge) == false){
			return false;
		}else{
			return checkTargetForSource(source, target);
		}
	}
	@Override
	protected boolean checkTargetForSource(GraphElement source,GraphElement target){
		if(super.checkTargetForSource(source, target) == false){
			return false;
		}else{
			EObject object = Utils.getElement(target);
			if((object instanceof ObjectNode || object instanceof DecisionNode || object instanceof ForkNode) && ((ActivityNode) object).getIncomings().size() > 0){
				return false;
			}
			if(object instanceof FinalNode){
				return false;
			}else if(object instanceof ControlNode){
				for(ActivityEdge e:((ControlNode) object).getOutgoings()){
					if(!(e instanceof ObjectFlow)){
						return false;
					}
				}
				for(ActivityEdge e:((ControlNode) object).getIncomings()){
					if(!(e instanceof ObjectFlow)){
						return false;
					}
				}
			}
			return object instanceof ObjectNode || object instanceof ControlNode;
		}
	}
}