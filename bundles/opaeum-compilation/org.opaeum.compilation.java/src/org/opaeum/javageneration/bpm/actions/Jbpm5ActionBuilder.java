package org.opaeum.javageneration.bpm.actions;

import java.util.Collection;
import java.util.HashSet;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.feature.TransformationContext;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractNodeBuilder;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.maps.ActivityNodeMap;
import org.opaeum.javageneration.oclexpressions.ConstraintGenerator;
import org.opaeum.javageneration.util.OJUtil;

public abstract class Jbpm5ActionBuilder<A extends ActivityNode> extends AbstractNodeBuilder{
	protected A node;
	protected AbstractObjectNodeExpressor expressor;
	protected EventUtil eventUtil;
	public ActivityNodeMap getMap(){
		return map;
	}
	protected ActivityNodeMap map;
	protected Jbpm5ActionBuilder(final OJUtil ojUtil,A node){
		super(new Jbpm5ObjectNodeExpressor(ojUtil));
		this.node = node;
		if(node instanceof Action){
			this.map = ojUtil.buildActionMap((Action) node);
		}else{
			this.map = ojUtil.buildActivityNodeMap(node);
		}
		eventUtil=new EventUtil(ojUtil);
		this.expressor = (AbstractObjectNodeExpressor) super.expressor;
	}
	public void setupArgumentPins(OJAnnotatedOperation oper){
		// ActivityUtil.setupVariables(oper, node);
		if(node instanceof Action){
			for(Pin pin:((Action) node).getInputs()){
				boolean ignore = node instanceof ReplyAction && pin.equals(((ReplyAction) node).getReturnInformation());
				if(!ignore){
					OJBlock block = oper.getBody();
					PropertyMap map = ojUtil.buildStructuralFeatureMap(pin);
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
		Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
		ifLast.addToThenPart(BpmUtil.endNodeFieldNameFor((NamedElement) EmfElementFinder.getContainer(node)) + "="
				+ getLibrary().getMessageStructure(container).getName() + "State." + BpmUtil.stepLiteralName(node));
		ifLast.addToThenPart("completed()");
	}
	public abstract void implementActionOn(OJAnnotatedOperation oper);
	public void implementPreConditions(OJOperation oper){
		if(node instanceof Action){
			implementConditions(oper, oper.getBody(), (Action) node, true);
		}
	}
	public void implementObersvations(OJOperation oper){
		Namespace container = EmfActivityUtil.getNearestNodeContainer(node);
		for(TimeObservation o: EmfTimeUtil.findTimeObservationsOn( container,node)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(o);
			oper.getBody().addToStatements(map.setter() + "(new Date())");
		}
		for(DurationObservation o:EmfTimeUtil.findDurationObservationsFrom(container,node)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(o);
			oper.getBody().addToStatements(map.setter() + "(new Duration())");
			oper.getBody().addToStatements(map.getter() + ".setFromDate(new Date())");
			// TODO set TimeUnit
		}
		for(DurationObservation o:EmfTimeUtil.findDurationObservationsTo(container,node)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(o);
			oper.getBody().addToStatements(map.getter() + ".setFromDate(new Date())");
			// TODO set quantity and calculate from BusinessCalendar specified somewhere
		}
	}
	public void implementConditions(OJOperation oper,OJBlock block,Action constrained,boolean pre){
		Collection<Constraint> conditions = pre ? constrained.getLocalPreconditions() : constrained.getLocalPostconditions();
		if(conditions.size() > 0){
			if(node instanceof Action){
				if(!pre && EmfActionUtil.isLongRunning((Action) node)){
					// Most commonly used for Tasks where there would be a
					// message structure T
					// TODO support other output pins
					final Action action = (Action) node;
					Collection<Pin> pins = new HashSet<Pin>(action.getOutputs());
					pins.addAll(action.getInputs());
					for(Pin pin:pins){
						PropertyMap map = ojUtil.buildStructuralFeatureMap( pin);
						oper.getOwner().addToImports(map.javaTypePath());
						OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
						field.setInitExp("completedWorkObject." + map.getter() + "()");
						block.addToLocals(field);
					}
				}
			}
			ConstraintGenerator cg = new ConstraintGenerator(ojUtil, (OJClass) oper.getOwner(), constrained);
			block.addToStatements(cg.buildConstraintsBlock(oper, block, conditions, pre));
		}
	}
	protected Activity getContainingActivity(){
		return EmfActivityUtil.getContainingActivity(node);
	}
	public void implementPostConditions(OJOperation oper){
		if(node instanceof Action){
			implementConditions(oper, oper.getBody(), (Action) node, false);
		}
	}
	public boolean isLongRunning(){
		return false;
	}
	public void implementCallbackMethods(OJClass activityClass){
	}
	public void flowTo(OJBlock block,ActivityNode target){
		if(EmfActivityUtil.isImplicitJoin(target)){
			block.addToStatements("waitingNode.flowToNode(\"" + BpmUtil.getArtificialJoinName(target) + "\")");
		}else{
			block.addToStatements("waitingNode.flowToNode(\"" + PersistentNameUtil.getPersistentName( target) + "\")");
		}
	}
	public void implementConditionalFlows(OJOperation operationContext,OJBlock block){
		// TODO implement cases where there are conditions and forks
		if(EmfActivityUtil.isImplicitFork( node)){
			block.addToStatements("waitingNode.flowToNode(\"" + BpmUtil.getArtificialForkName(node) + "\")");
		}else if(EmfActivityUtil.isImplicitDecision(node)){
			block.addToStatements("waitingNode.flowToNode(\"" + BpmUtil.getArtificialChoiceName(node) + "\")");
		}else if(EmfActivityUtil.getAllEffectiveOutgoing( node).size() > 0){
			ActivityEdge flow = EmfActivityUtil.getAllEffectiveOutgoing( node).iterator().next();
			flowTo(block, EmfActivityUtil.getEffectiveTarget(flow));
		}
	}
	public boolean waitsForEvent(){
		return false;
	}
	public boolean hasNodeMethod(){
		// TODO refine this
		return node instanceof Action || node instanceof ObjectNode || node instanceof ExpansionRegion
				|| (node instanceof  FinalNode);
	}
	public boolean isEffectiveFinalNode(){
		return EmfBehaviorUtil.isEffectiveFinalNode(node) || (node instanceof ExpansionNode && ((ExpansionNode) node).getRegionAsOutput()!=null)
				|| node instanceof RaiseExceptionAction;
	}
}
