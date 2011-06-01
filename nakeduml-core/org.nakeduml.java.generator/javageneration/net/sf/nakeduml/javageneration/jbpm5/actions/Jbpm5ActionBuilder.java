package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractNodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActivityNodeMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityUtil;
import net.sf.nakeduml.javageneration.oclexpressions.ConstraintGenerator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public abstract class Jbpm5ActionBuilder<A extends INakedActivityNode> extends AbstractNodeBuilder{
	protected A node;
	protected Jbpm5ObjectNodeExpressor expressor;
	public ActivityNodeMap getMap(){
		return map;
	}
	protected ActivityNodeMap map;
	protected Jbpm5ActionBuilder(final IOclEngine oclEngine,A node){
		super(oclEngine, new Jbpm5ObjectNodeExpressor(oclEngine));
		this.node = node;
		if(node instanceof INakedAction){
			this.map = new ActionMap((INakedAction) node);
		}else{
			this.map = new ActivityNodeMap(node);
		}
		this.expressor = (Jbpm5ObjectNodeExpressor) super.expressor;
	}
	public void setupVariables(OJAnnotatedOperation oper){
		ActivityUtil.setupVariables(oper, node);
	}
	public void implementFinalStep(OJBlock block){
		block.addToStatements(Jbpm5Util.endNodeFieldNameFor(node.getActivity()) + "=" + node.getActivity().getMappingInfo().getJavaName() + "State."
				+ Jbpm5Util.stepLiteralName(node));
	}
	public abstract void implementActionOn(OJAnnotatedOperation oper);
	public void implementPreConditions(OJOperation oper){
		if(node instanceof PreAndPostConstrained){
			implementConditions(oper, oper.getBody(), (PreAndPostConstrained) node, true);
		}
	}
	public void implementConditions(OJOperation oper,OJBlock block,PreAndPostConstrained constrained,boolean pre){
		Collection<IOclContext> conditions = pre ? constrained.getPreConditions() : constrained.getPostConditions();
		if(conditions.size() > 0){
			if(node instanceof INakedAction){
				// preConditions and PostConditions on actions operate on pins - make pins available as parameters
				for(INakedPin pin:((INakedAction) node).getInput()){
					buildPinField(oper, block, pin, false);
				}
				if(!pre && node instanceof INakedCallAction && ((INakedCallAction) node).isLongRunning()){
					// Most commonly used for Tasks where there would be a
					// message structure T
					// TODO support other output pins
					for(INakedPin pin:((INakedAction) node).getOutput()){
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, false);
						oper.getOwner().addToImports(map.javaTypePath());
						OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
						field.setInitExp("completedTask." + map.getter() + "()");
						block.addToLocals(field);
					}
				}
			}
			ConstraintGenerator cg = new ConstraintGenerator((OJClass) oper.getOwner(), constrained);
			block.addToStatements(cg.buildConstraintsBlock(oper, block, conditions, pre));
		}
	}
	public void implementPostConditions(OJOperation oper){
		if(node instanceof PreAndPostConstrained){
			implementConditions(oper, oper.getBody(), (PreAndPostConstrained) node, false);
		}
	}
	public boolean isTask(){
		return false;
	}
	public void implementSupportingTaskMethods(OJClass activityClass){
	}
	public void flowTo(OJBlock block,INakedActivityNode target){
		if(target.isImplicitJoin()){
			block.addToStatements("waitingNode.takeTransition(\"" + Jbpm5Util.getArtificialJoinName(target) + "\")");
		}else{
			block.addToStatements("waitingNode.takeTransition(\"" + target.getMappingInfo().getPersistentName() + "\")");
		}
	}
	public void implementConditionalFlows(OJOperation operationContext,OJBlock block){
		// TODO implement cases where there are conditions and forks
		if(node.isImplicitFork()){
			block.addToStatements("waitingNode.takeTransition(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(node.isImplicitDecision()){
			block.addToStatements("waitingNode.takeTransition(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else if(node.getAllEffectiveOutgoing().size() > 0){
			GuardedFlow flow = node.getAllEffectiveOutgoing().iterator().next();
			flowTo(block, ((INakedActivityEdge) flow).getEffectiveTarget());
		}
	}
	public boolean waitsForEvent(){
		return false;
	}
	protected final String buildPinField(OJOperation operationContext,OJBlock block,INakedObjectNode pin){
		return buildPinField(operationContext, block, pin, true);
	}
	protected final String buildPinField(OJOperation operationContext,OJBlock block,INakedObjectNode pin,boolean ensureUniqueness){
		if(pin == null){
			return "!!NoPin!!";
		}else{
			String pinName = " " + pin.getMappingInfo().getJavaName().toString();
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, ensureUniqueness);
			operationContext.getOwner().addToImports(map.javaTypePath());
			OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
			field.setInitExp(buildPinExpression(operationContext, block, pin));
			block.addToLocals(field);
			return pinName;
		}
	}
	public boolean hasNodeMethod(){
		// TODO refine this
		return node instanceof INakedAction || node instanceof INakedObjectNode || node instanceof INakedExpansionRegion
				|| (node instanceof INakedControlNode && ((INakedControlNode) node).getControlNodeType().isFinalNode());
	}
	public boolean isEffectiveFinalNode(){
		return BehaviorUtil.isEffectiveFinalNode(node);
	}
}
