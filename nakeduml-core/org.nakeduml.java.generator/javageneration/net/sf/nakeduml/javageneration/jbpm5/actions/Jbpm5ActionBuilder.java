package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.basicjava.AbstractNodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActivityNodeMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityUtil;
import net.sf.nakeduml.javageneration.maps.ActionMap;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
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
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public abstract class Jbpm5ActionBuilder<A extends INakedActivityNode> extends AbstractNodeBuilder{
	protected A node;
	protected AbstractObjectNodeExpressor expressor;
	public ActivityNodeMap getMap(){
		return map;
	}
	protected ActivityNodeMap map;
	protected Jbpm5ActionBuilder(final NakedUmlLibrary l,A node){
		super(l, new Jbpm5ObjectNodeExpressor(l));
		this.node = node;
		if(node instanceof INakedAction){
			this.map = new ActionMap((INakedAction) node);
		}else{
			this.map = new ActivityNodeMap(node);
		}
		this.expressor = (AbstractObjectNodeExpressor) super.expressor;
	}
	public void setupVariablesAndArgumentPins(OJAnnotatedOperation oper){
		ActivityUtil.setupVariables(oper, node);
		
		if(node instanceof INakedAction){
			for(INakedPin pin:((INakedAction) node).getInput()){
				OJBlock block = oper.getBody();
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
				oper.getOwner().addToImports(map.javaTypePath());
				OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
				
				field.setInitExp(expressPin(oper, block, pin));
				block.addToLocals(field);
			}
		}
	}
	public void implementFinalStep(OJBlock block){
		if(node.getActivity().getSpecification()!=null){
			NakedOperationMap map = new NakedOperationMap(node.getActivity().getSpecification());
			block.addToStatements("getCallingProcessObject()." +map.callbackListener() + "(this)");
		}
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
		Collection<INakedConstraint> conditions = pre ? constrained.getPreConditions() : constrained.getPostConditions();
		if(conditions.size() > 0){
			if(node instanceof INakedAction){
				if(!pre && node instanceof INakedCallAction && ((INakedCallAction) node).isLongRunning()){
					// Most commonly used for Tasks where there would be a
					// message structure T
					// TODO support other output pins
					for(INakedPin pin:((INakedAction) node).getOutput()){
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
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
	protected final void  buildPinField(OJOperation operationContext,OJBlock block,INakedObjectNode pin){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin, true);
		operationContext.getOwner().addToImports(map.javaTypePath());
		OJAnnotatedField field = new OJAnnotatedField(pin.getMappingInfo().getJavaName().getAsIs(), map.javaTypePath());
		field.setInitExp(expressPin(operationContext, block, pin));
		block.addToLocals(field);
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
