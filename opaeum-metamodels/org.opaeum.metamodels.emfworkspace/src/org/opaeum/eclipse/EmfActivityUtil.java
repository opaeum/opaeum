package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EmfActivityUtil{
	public static Namespace getNearestNodeContainer(ActivityNode node){
		EObject container = EmfElementFinder.getContainer(node);
		if(container instanceof Activity || container instanceof StructuredActivityNode){
			return (Namespace) container;
		}else if(container instanceof ActivityNode){
			return getNearestNodeContainer((ActivityNode) container);
		}else{
			return null;
		}
	}
	public static ObjectNode getOriginatingObjectNode(ObjectFlow flow){
		if(flow.getSource() instanceof ObjectNode){
			return (ObjectNode) flow.getSource();
		}else if(flow.getSource() instanceof ControlNode){
			ControlNode c = (ControlNode) flow.getSource();
			Set<ActivityEdge> allEffectiveIncoming = getAllEffectiveIncoming(c);
			if((c instanceof JoinNode || c instanceof MergeNode) && multipleObjectFlows(allEffectiveIncoming)){
				// Eliminate guess work. Under these conditions it would be misleading to return anything
				return null;
			}
			for(ActivityEdge edge:allEffectiveIncoming){
				if(edge instanceof ObjectFlow){
					return getOriginatingObjectNode(((ObjectFlow) edge));
				}
			}
		}
		return null;
	}
	private static boolean multipleObjectFlows(Set<ActivityEdge> allEffectiveIncoming){
		int count = 0;
		for(ActivityEdge edge:allEffectiveIncoming){
			if(edge instanceof ObjectFlow){
				count++;
			}
		}
		return count > 1;
	}
	public static Set<ActivityEdge> getDefaultOutgoing(ActivityNode node){
		Set<ActivityEdge> outgoing = getOutgoing(node, false);
		if(node instanceof CallAction){
			Iterator<ActivityEdge> iterator = outgoing.iterator();
			while(iterator.hasNext()){
				ActivityEdge activityEdge = iterator.next();
				if(activityEdge.getSource() instanceof OutputPin){
					TypedElement te = EmfActionUtil.getLinkedTypedElement((Pin) activityEdge.getSource());
					if(te instanceof Parameter && ((Parameter) te).isException()){
						iterator.remove();
					}
				}
			}
		}
		return outgoing;
	}
	public static Set<ActivityEdge> getConditionalOutgoing(ActivityNode node){
		return getOutgoing(node, true);
	}
	private static Set<ActivityEdge> getOutgoing(ActivityNode node,boolean hasGuard){
		Set<ActivityEdge> results = new TreeSet<ActivityEdge>(new ElementComparator());
		for(ActivityEdge e:getAllEffectiveOutgoing(node)){
			if(hasGuard(e) == hasGuard){
				results.add(e);
			}
		}
		return results;
	}
	public static Collection<ActivityNode> getStartNodes(Namespace ns){
		Collection<ActivityNode> result = new TreeSet<ActivityNode>(new ElementComparator());
		for(ActivityNode node:getActivityNodes(ns)){
			if(node instanceof ActivityParameterNode){
				ActivityParameterNode parmNode = (ActivityParameterNode) node;
				// Ignore parameter nodes that have no outgoing edges, e.g.
				// out-parameters
				if(isArgument(parmNode.getParameter()) && getAllEffectiveIncoming(parmNode).isEmpty()){
					result.add(node);
				}
			}else if(getAllEffectiveIncoming(node).isEmpty()){
				if(!(node instanceof Action && handlesException((Action) node)) || node instanceof AcceptEventAction){
					result.add(node);
				}
			}
		}
		return result;
	}
	private static boolean handlesException(Action node){
		EList<InputPin> inputs = node.getInputs();
		for(InputPin inputPin:inputs){
			if(getIncomingExceptionHandlers(inputPin).size() > 0){
				return true;
			}
		}
		return false;
	}
	public static Collection<ExceptionHandler> getIncomingExceptionHandlers(ObjectNode n){
		ECrossReferenceAdapter cra = ECrossReferenceAdapter.getCrossReferenceAdapter(n);
		Collection<ExceptionHandler> result = new TreeSet<ExceptionHandler>();
		for(Setting setting:cra.getNonNavigableInverseReferences(n)){
			if(setting.getEObject() instanceof ExceptionHandler){
				result.add((ExceptionHandler) setting.getEObject());
			}
		}
		return result;
	}
	private static boolean isArgument(Parameter parameter){
		// TODO Auto-generated method stub
		return parameter.getDirection() == ParameterDirectionKind.IN_LITERAL
				|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL;
	}
	public static Collection<ActivityNode> getActivityNodes(Namespace ne){
		if(ne instanceof Activity){
			return ((Activity) ne).getOwnedNodes();
		}else if(ne instanceof StructuredActivityNode){
			return ((StructuredActivityNode) ne).getContainedNodes();
		}else{
			return Collections.emptySet();
		}
	}
	public static String getNodePath(ActivityNode n){
		StringBuilder sb = new StringBuilder();
		maybeAppendParent(n, sb);
		sb.append(n.getName());
		return sb.toString();
	}
	private static void maybeAppendParent(ActivityNode v,StringBuilder sb){
		if(v.getInStructuredNode() != null){
			sb.append(v.getInStructuredNode().getName());
			sb.append("::");
			maybeAppendParent(v.getInStructuredNode(), sb);
		}
	}
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
							result.isUnique = false;
							result.isOrdered = false;
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
		if(target instanceof ActivityParameterNode && ((ActivityParameterNode) target).getParameter() != null){
			ActivityParameterNode p = (ActivityParameterNode) target;
			Parameter pr = p.getParameter();
			return new TypeAndMultiplicity(pr, pr);
		}else if(target.getType() == null){
			if(target instanceof InputPin){
				InputPin pinSource = (InputPin) target;
				Action action = (Action) pinSource.getOwner();
				return calculateType(action, pinSource);
			}else if(target instanceof ExpansionNode){
				ExpansionNode e = (ExpansionNode) target;
				if(e.getRegionAsInput() != null){
					for(ActivityEdge activityEdge:e.getIncomings()){
						if(activityEdge instanceof ObjectFlow){
							ObjectNode findFeedingNode = findFeedingNode((ObjectFlow) activityEdge);
							TypeAndMultiplicity result = calculateSourceType(findFeedingNode);
							result.isMany = true;
							result.isOrdered = false;
							result.isUnique = false;
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
			action = action.getOwner();
		}
		return (Activity) action;
	}
	public static Collection<ActivityNode> getActivityNodesRecursively(Activity containingActivity){
		Set<ActivityNode> result = new TreeSet<ActivityNode>(new ElementComparator());
		EList<ActivityNode> ownedNodes = containingActivity.getOwnedNodes();
		for(ActivityNode activityNode:ownedNodes){
			result.add(activityNode);
			if(activityNode instanceof StructuredActivityNode){
				result.addAll(getActivityNodesRecursively((Activity) activityNode));
			}
		}
		return result;
	}
	public static Collection<ActivityNode> getActivityNodesRecursively(StructuredActivityNode containingActivity){
		Set<ActivityNode> result = new TreeSet<ActivityNode>(new ElementComparator());
		EList<ActivityNode> ownedNodes = containingActivity.getContainedNodes();
		for(ActivityNode activityNode:ownedNodes){
			result.add(activityNode);
			if(activityNode instanceof StructuredActivityNode){
				result.addAll(((StructuredActivityNode) activityNode).getContainedNodes());
			}
		}
		return result;
	}
	public static ExpansionRegion getExpansionRegion(ExpansionNode node){
		return node.getRegionAsInput() == null ? node.getRegionAsOutput() : node.getRegionAsInput();
	}
	public static List<Variable> getVariables(Namespace container){
		if(container instanceof StructuredActivityNode){
			return ((StructuredActivityNode) container).getVariables();
		}else if(container instanceof Activity){
			return ((Activity) container).getVariables();
		}
		return Collections.emptyList();
	}
	public static Collection<ActivityEdge> getEdges(Namespace container){
		if(container instanceof StructuredActivityNode){
			return ((StructuredActivityNode) container).getEdges();
		}else if(container instanceof Activity){
			return ((Activity) container).getEdges();
		}
		return Collections.emptyList();
	}
	public static boolean hasGuard(ActivityEdge edge){
		return edge.getGuard() instanceof OpaqueExpression;
	}
	public static boolean isSimpleSynchronousMethod(Activity activity){
		if(StereotypesHelper.hasStereotype(activity, StereotypeNames.METHOD)){
			return true;
		}else if(EmfBehaviorUtil.isProcess(activity) || EmfBehaviorUtil.isComplectSynchronousMethod(activity)){
			return false;
		}else{
			return true;
		}
	}
	public static Set<ActivityEdge> getAllEffectiveIncoming(ActivityNode node){
		Set<ActivityEdge> result = new TreeSet<ActivityEdge>(new ElementComparator());
		result.addAll(node.getIncomings());
		if(node instanceof Action){
			EList<InputPin> inputs = ((Action) node).getInputs();
			for(InputPin inputPin:inputs){
				result.addAll(inputPin.getIncomings());
			}
		}
		return result;
	}
	public static Set<ActivityEdge> getAllEffectiveOutgoing(ActivityNode node){
		Set<ActivityEdge> result = new TreeSet<ActivityEdge>(new ElementComparator());
		result.addAll(node.getOutgoings());
		if(node instanceof Action){
			EList<OutputPin> inputs = ((Action) node).getOutputs();
			for(OutputPin inputPin:inputs){
				result.addAll(inputPin.getOutgoings());
			}
		}
		return result;
	}
	public static ActivityNode getEffectiveTarget(ActivityEdge e){
		if(e instanceof ControlFlow){
			return e.getTarget();
		}else{
			ObjectFlow of = (ObjectFlow) e;
			if(of.getTarget() instanceof InputPin){
				return (ActivityNode) ((InputPin) of.getTarget()).getOwner();
			}else{
				return of.getTarget();
			}
		}
	}
	public static ActivityNode getEffectiveSource(ActivityEdge e){
		if(e instanceof ControlFlow){
			return e.getSource();
		}else{
			ObjectFlow of = (ObjectFlow) e;
			if(of.getSource() instanceof OutputPin){
				return (ActivityNode) ((OutputPin) of).getOwner();
			}else{
				return of.getTarget();
			}
		}
	}
	public static boolean isImplicitJoin(ActivityNode target){
		return getAllEffectiveIncoming(target).size() > 0;
	}
	public static boolean isImplicitFork(ActivityNode target){
		return getDefaultOutgoing(target).size() > 0;
	}
	public static boolean isImplicitDecision(ActivityNode target){
		return getConditionalOutgoing(target).size() > 0;
	}
	public static List<Constraint> getPostconditions(Namespace container){
		if(container instanceof Behavior){
			return ((Behavior) container).getPostconditions();
		}else if(container instanceof Action){
			return ((Action) container).getLocalPostconditions();
		}
		return Collections.emptyList();
	}
	private static ObjectNode getObjectNodeSource(Collection<ActivityEdge> source){
		for(ActivityEdge edge:source){
			if(edge instanceof ObjectFlow){
				return getOriginatingObjectNode((ObjectFlow) edge);
			}
		}
		return null;
	}
	public static ObjectNode getFeedingNode(ObjectNode node){
		return getObjectNodeSource(node.getIncomings());
	}
	public static boolean isMultivalued(ObjectNode n){
		if(n instanceof MultiplicityElement){
			return ((MultiplicityElement) n).isMultivalued();
		}else{
			ValueSpecification upperBound = n.getUpperBound();
			
			if(upperBound instanceof LiteralUnlimitedNatural){
				return upperBound.integerValue()>1 || upperBound.integerValue()==LiteralUnlimitedNatural.UNLIMITED;
			}else if(upperBound instanceof LiteralInteger){
				return upperBound.integerValue()>1;
			}else{
				return false;
			}
		}
	}
	public static ObjectNode getFedNode(ObjectNode source){
		return getObjectNodeTarget(source.getOutgoings());
	}
	private static ObjectNode getObjectNodeTarget(Collection<ActivityEdge> source){
		for(ActivityEdge edge:source){
			if(edge instanceof ObjectFlow){
				return getFedObjectNode((ObjectFlow) edge);
			}
		}
		return null;
	}
	private static ObjectNode getFedObjectNode(ObjectFlow edge){
		if(edge.getTarget() instanceof ObjectNode){
			return (ObjectNode) edge.getTarget();
		}else if(edge.getTarget() instanceof ControlNode){
			ControlNode c= (ControlNode) edge.getTarget();
			Set<ActivityEdge> allEffectiveOutgoing= getAllEffectiveOutgoing(edge.getTarget());
			if((c instanceof ForkNode ||c instanceof DecisionNode) && multipleObjectFlows(allEffectiveOutgoing)){
				//Eliminate guess work. Under these conditions it would be misleading to return anything
				return null;
			}
			for(ActivityEdge outgoing:allEffectiveOutgoing){
				if(outgoing instanceof ObjectFlow){
					return getFedObjectNode((ObjectFlow) outgoing);
				}
			}
		}
		return null;
	}

}
