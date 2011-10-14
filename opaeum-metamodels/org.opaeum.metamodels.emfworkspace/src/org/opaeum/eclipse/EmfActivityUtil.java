package org.opaeum.eclipse;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;

public class EmfActivityUtil{
	public static ObjectNode findFeedingNode(ObjectFlow o){
		if(o.getSource() instanceof ObjectNode){
			return (ObjectNode) o.getSource();
		}else if(o.getSource() instanceof ControlNode){
			ControlNode c = (ControlNode) o.getSource();
			// Assume validations will catch invalid scenarios
			for(ActivityEdge activityEdge:c.getIncomings()){
				if(activityEdge instanceof ObjectFlow){
					return findFeedingNode((ObjectFlow) activityEdge);
				}
			}
		}
		return null;
	}
	public static ObjectNode findFedNode(ObjectFlow o){
		if(o.getTarget() instanceof ObjectNode){
			return (ObjectNode) o.getTarget();
		}else if(o.getSource() instanceof ControlNode){
			ControlNode c = (ControlNode) o.getTarget();
			// Assume validations will catch invalid scenarios
			for(ActivityEdge activityEdge:c.getIncomings()){
				if(activityEdge instanceof ObjectFlow){
					return findFedNode((ObjectFlow) activityEdge);
				}
			}
		}
		return null;
	}
	public static class TypeAndMultiplicity{
		Type type;
		boolean isUnique;
		boolean isOrdered;
		boolean isMany;
		public Classifier getType(){
			return (Classifier) type;
		}
		public boolean isUnique(){
			return isUnique;
		}
		public boolean isOrdered(){
			return isOrdered;
		}
		public boolean isMany(){
			return isMany;
		}
		public TypeAndMultiplicity(TypedElement type,MultiplicityElement me){
			if(type != null){
				this.type = type.getType();
			}
			if(me != null){
				this.isUnique = me.isUnique();
				this.isOrdered = me.isOrdered();
				this.isMany = me.getUpper() > 1 || me.getUpper() < 0;
			}
		}
		private TypeAndMultiplicity(TypedElement type,boolean isUnique,boolean isOrdered,boolean isMany){
			super();
			if(type != null){
				this.type = type.getType();
			}
			this.isUnique = isUnique;
			this.isOrdered = isOrdered;
			this.isMany = isMany;
		}
	}
	public static TypeAndMultiplicity findSourceType(ObjectFlow o){
		if(o.getSource() instanceof ObjectNode){
			ObjectNode source = (ObjectNode) o.getSource();
			return calculateSourceType(source);
		}else if(o.getSource() instanceof ControlNode){
			ControlNode c = (ControlNode) o.getSource();
			// Assume validations will catch invalid scenarios
			for(ActivityEdge activityEdge:c.getIncomings()){
				if(activityEdge instanceof ObjectFlow){
					return findSourceType((ObjectFlow) activityEdge);
				}
			}
		}
		return null;
	}
	private static TypeAndMultiplicity calculateSourceType(ObjectNode source){
		if(source.getType() == null){
			if(source instanceof OutputPin){
				OutputPin pinSource = (OutputPin) source;
				Action action = (Action) pinSource.getOwner();
				return calculateType(action, pinSource);
			}else if(source instanceof ActivityParameterNode){
				ActivityParameterNode p = (ActivityParameterNode) source;
				Parameter pr = p.getParameter();
				return new TypeAndMultiplicity(pr, pr);
			}else if(source instanceof ExpansionNode){
				ExpansionNode e = (ExpansionNode) source;
				if(e.getRegionAsInput() != null){
					for(ActivityEdge activityEdge:e.getIncomings()){
						if(activityEdge instanceof ObjectFlow){
							ObjectNode findFeedingNode = findFeedingNode((ObjectFlow) activityEdge);
							TypeAndMultiplicity result = calculateSourceType(findFeedingNode);
							result.isMany = false;
							return result;
						}
					}
				}else{
					for(ActivityEdge activityEdge:e.getIncomings()){
						if(activityEdge instanceof ObjectFlow){
							ObjectNode findFeedingNode = findFeedingNode((ObjectFlow) activityEdge);
							TypeAndMultiplicity result = calculateSourceType(findFeedingNode);
							result.isMany = true;
							result.isUnique=false;
							result.isOrdered=false;
							return result;
						}
					}
				}
			}
		}else{
			return getTypeAndMultiplicity(source);
		}
		return new TypeAndMultiplicity(null, false, false, false);
	}
	protected static TypeAndMultiplicity getTypeAndMultiplicity(ObjectNode source){
		if(source instanceof Pin){
			Pin pinSource = (Pin) source;
			return new TypeAndMultiplicity(pinSource, pinSource);
		}else if(source instanceof ExpansionNode){
			ExpansionNode e = (ExpansionNode) source;
			if(e.getRegionAsInput() != null){
				return new TypeAndMultiplicity(e, false, false, false);
			}else{
				return new TypeAndMultiplicity(e, false, false, true);
			}
		}else if(source instanceof ActivityParameterNode){
			ActivityParameterNode p = (ActivityParameterNode) source;
			return new TypeAndMultiplicity(p, p.getParameter());
		}else{
			return new TypeAndMultiplicity(source, false, false, false);
		}
	}
	private static TypeAndMultiplicity calculateType(Action action,OutputPin pinSource){
		if(action instanceof ReadVariableAction && ((ReadVariableAction) action).getResult() == pinSource){
			ReadVariableAction a = (ReadVariableAction) action;
			return new TypeAndMultiplicity(a.getVariable(), a.getVariable());
		}else if(action instanceof ReadStructuralFeatureAction && ((ReadStructuralFeatureAction) action).getResult() == pinSource){
			ReadStructuralFeatureAction a = (ReadStructuralFeatureAction) action;
			return new TypeAndMultiplicity(a.getStructuralFeature(), a.getStructuralFeature());
		}
		// TODO etc
		return null;
	}
	public static TypeAndMultiplicity findTargetType(ObjectFlow o){
		if(o.getTarget() instanceof ObjectNode){
			ObjectNode target = (ObjectNode) o.getTarget();
			return calculateTargetType(target);
		}else if(o.getSource() instanceof ControlNode){
			ControlNode c = (ControlNode) o.getSource();
			// Assume validations will catch invalid scenarios
			for(ActivityEdge activityEdge:c.getIncomings()){
				if(activityEdge instanceof ObjectFlow){
					return findTargetType((ObjectFlow) activityEdge);
				}
			}
		}
		return null;
	}
	private static TypeAndMultiplicity calculateTargetType(ObjectNode target){
		if(target.getType() == null){
			if(target instanceof InputPin){
				InputPin pinSource = (InputPin) target;
				Action action = (Action) pinSource.getOwner();
				return calculateType(action, pinSource);
			}else if(target instanceof ActivityParameterNode){
				ActivityParameterNode p = (ActivityParameterNode) target;
				Parameter pr = p.getParameter();
				return new TypeAndMultiplicity(pr, pr);
			}else if(target instanceof ExpansionNode){
				ExpansionNode e = (ExpansionNode) target;
				if(e.getRegionAsInput() != null){
					for(ActivityEdge activityEdge:e.getIncomings()){
						if(activityEdge instanceof ObjectFlow){
							ObjectNode findFeedingNode = findFeedingNode((ObjectFlow) activityEdge);
							TypeAndMultiplicity result = calculateSourceType(findFeedingNode);
							result.isMany = true;
							result.isOrdered=false;
							result.isUnique=false;
							return result;
						}
					}
				}else{
					for(ActivityEdge activityEdge:e.getIncomings()){
						if(activityEdge instanceof ObjectFlow){
							ObjectNode findFeedingNode = findFeedingNode((ObjectFlow) activityEdge);
							TypeAndMultiplicity result = calculateSourceType(findFeedingNode);
							result.isMany = false;
							return result;
						}
					}
				}
			}
		}else if(target instanceof Pin){
			Pin pinSource = (Pin) target;
			return new TypeAndMultiplicity(pinSource, pinSource);
		}else if(target instanceof ExpansionNode){
			ExpansionNode e = (ExpansionNode) target;
			if(e.getRegionAsInput() != null){
				return new TypeAndMultiplicity(e, false, false, false);
			}else{
				return new TypeAndMultiplicity(e, false, false, true);
			}
		}
		return new TypeAndMultiplicity(null, false, false, false);
	}
	private static TypeAndMultiplicity calculateType(Action action,InputPin pinSource){
		if(action instanceof AddVariableValueAction && ((AddVariableValueAction) action).getValue() == pinSource){
			AddVariableValueAction a = (AddVariableValueAction) action;
			return new TypeAndMultiplicity(a.getVariable(), a.getVariable());
		}else if(action instanceof AddStructuralFeatureValueAction && ((AddStructuralFeatureValueAction) action).getValue() == pinSource){
			AddStructuralFeatureValueAction a = (AddStructuralFeatureValueAction) action;
			return new TypeAndMultiplicity(a.getStructuralFeature(), a.getStructuralFeature());
		}
		// TODO etc
		return null;
	}
	public static Activity getContainingActivity(Element action){
		while(!(action instanceof Activity || action == null)){
			action=action.getOwner();
		}
		return (Activity) action;
	}
}
