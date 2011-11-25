package org.opaeum.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractNodeBuilder;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.basicjava.simpleactions.ActivityNodeMap;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ConstraintGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedRaiseExceptionAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.GuardedFlow;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.PreAndPostConstrained;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class Jbpm5ActionBuilder<A extends INakedActivityNode> extends AbstractNodeBuilder{
	protected A node;
	protected AbstractObjectNodeExpressor expressor;
	public ActivityNodeMap getMap(){
		return map;
	}
	protected ActivityNodeMap map;
	protected Jbpm5ActionBuilder(final OpaeumLibrary l,A node){
		super(l, new Jbpm5ObjectNodeExpressor(l));
		this.node = node;
		if(node instanceof INakedAction){
			this.map = new ActionMap((INakedAction) node);
		}else{
			this.map = new ActivityNodeMap(node);
		}
		this.expressor = (AbstractObjectNodeExpressor) super.expressor;
	}
	public void setupArgumentPins(OJAnnotatedOperation oper){
		// ActivityUtil.setupVariables(oper, node);
		if(node instanceof INakedAction){
			for(INakedPin pin:((INakedAction) node).getInput()){
				boolean ignore = node instanceof INakedReplyAction && pin.equals(((INakedReplyAction) node).getReturnInfo());
				if(!ignore){
					OJBlock block = oper.getBody();
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, false);
					oper.getOwner().addToImports(map.javaTypePath());
					OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
					field.setInitExp(expressPin(oper, block, pin));
					block.addToLocals(field);
				}
			}
		}
	}
	public void implementFinalStep(OJBlock block){
		OJIfStatement ifLast = new OJIfStatement("getProcessInstance().getNodeInstances().size()==1");
		block.addToStatements(ifLast);
		ifLast.addToThenPart(Jbpm5Util.endNodeFieldNameFor((INakedElement) node.getOwnerElement()) + "="
				+ node.getNearestStructuredElementAsClassifier().getMappingInfo().getJavaName() + "State." + Jbpm5Util.stepLiteralName(node));
		ifLast.addToThenPart("completed()");
	}
	public abstract void implementActionOn(OJAnnotatedOperation oper);
	public void implementPreConditions(OJOperation oper){
		if(node instanceof PreAndPostConstrained){
			implementConditions(oper, oper.getBody(), (PreAndPostConstrained) node, true);
		}
	}
	public void implementObersvations(OJOperation oper){
		for(INakedTimeObservation o:node.getNearestNodeContainer().findTimeObservation(node)){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getNearestStructuredElementAsClassifier(), o);
			oper.getBody().addToStatements(map.setter() + "(new Date())");
		}
		for(INakedDurationObservation o:node.getNearestNodeContainer().findDurationObservationFrom(node)){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getNearestStructuredElementAsClassifier(), o);
			oper.getBody().addToStatements(map.setter() + "(new Duration())");
			oper.getBody().addToStatements(map.getter() + ".setFromDate(new Date())");
			// TODO set TimeUnit
		}
		for(INakedDurationObservation o:node.getNearestNodeContainer().findDurationObservationTo(node)){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getNearestStructuredElementAsClassifier(), o);
			oper.getBody().addToStatements(map.getter() + ".setFromDate(new Date())");
			// TODO set quantity and calculate from BusinessCalendar specified somewhere
		}
	}
	public void implementConditions(OJOperation oper,OJBlock block,PreAndPostConstrained constrained,boolean pre){
		Collection<INakedConstraint> conditions = pre ? constrained.getPreConditions() : constrained.getPostConditions();
		if(conditions.size() > 0){
			if(node instanceof INakedAction){
				if(!pre && ((INakedAction) node).isLongRunning()){
					// Most commonly used for Tasks where there would be a
					// message structure T
					// TODO support other output pins
					final INakedAction action = (INakedAction) node;
					Collection<INakedPin> pins = new HashSet<INakedPin>(action.getOutput());
					pins.addAll(action.getInput());
					for(INakedPin pin:pins){
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, false);
						oper.getOwner().addToImports(map.javaTypePath());
						OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
						field.setInitExp("completedWorkObject." + map.getter() + "()");
						block.addToLocals(field);
					}
				}
			}
			ConstraintGenerator cg = new ConstraintGenerator((OJClass) oper.getOwner(), constrained);
			String selfExpr = node.getOwnerElement() instanceof INakedActivity ? "this" : "getContainingActivity()";
			block.addToStatements(cg.buildConstraintsBlock(oper, block, conditions, pre, selfExpr));
		}
	}
	public void implementPostConditions(OJOperation oper){
		if(node instanceof PreAndPostConstrained){
			implementConditions(oper, oper.getBody(), (PreAndPostConstrained) node, false);
		}
	}
	public boolean isLongRunning(){
		return false;
	}
	public void implementCallbackMethods(OJClass activityClass){
	}
	public void flowTo(OJBlock block,INakedActivityNode target){
		if(target.isImplicitJoin()){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialJoinName(target) + "\")");
		}else{
			block.addToStatements("waitingNode.flowToNode(\"" + target.getMappingInfo().getPersistentName() + "\")");
		}
	}
	public void implementConditionalFlows(OJOperation operationContext,OJBlock block){
		// TODO implement cases where there are conditions and forks
		block.addToStatements("this.processDirty=true");
		if(node.isImplicitFork()){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(node.isImplicitDecision()){
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else if(node.getAllEffectiveOutgoing().size() > 0){
			GuardedFlow flow = node.getAllEffectiveOutgoing().iterator().next();
			flowTo(block, ((INakedActivityEdge) flow).getEffectiveTarget());
		}
	}
	public boolean waitsForEvent(){
		return false;
	}
	public boolean hasNodeMethod(){
		// TODO refine this
		return node instanceof INakedAction || node instanceof INakedObjectNode || node instanceof INakedExpansionRegion
				|| (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isFinalNode());
	}
	public boolean isEffectiveFinalNode(){
		return BehaviorUtil.isEffectiveFinalNode(node) || (node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement())
				|| node instanceof INakedRaiseExceptionAction;
	}
}
