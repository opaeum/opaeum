package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedInvocationAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.oclengine.IOclEngine;


/**
 * Base class for all action builders that could potentially build a task representing a usertask. 
 *  
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedInvocationAction> extends Jbpm5ActionBuilder<A>{
	protected PotentialTaskActionBuilder(IOclEngine oclEngine,A node){
		super(oclEngine, node);
	}
	protected <E extends INakedInputPin>StringBuilder populateArguments(OJOperation operation,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<E> args = input.iterator();
		while(args.hasNext()){
			INakedObjectNode pin = args.next();
			String argName = buildPinField(operation, operation.getBody(), pin);
			arguments.append(argName);
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		return arguments;
	}
	@Override
	public void implementSupportingTaskMethods(OJClass activityClass){
		implementJbpmAssignmentsIfNecessary(activityClass);
		implementCompleteMethod(activityClass);
	}
	private void implementCompleteMethod(OJClass activityClass) {
		activityClass.addToImports(BpmUtil.getNodeInstance());
		activityClass.addToImports(BpmUtil.getJbpm5Environment());
		OJOperation complete = new OJAnnotatedOperation();
		complete.setName("on" + node.getMappingInfo().getJavaName().getCapped() + "Completed");
		activityClass.addToOperations(complete);
		implementPostConditions(complete);
		String literalExpression = activityClass.getName() + "State." + BpmUtil.stepLiteralName(node);
		complete.getBody().addToStatements("NodeInstance waitingToken=findWaitingNode(" + literalExpression + ")");
		complete.getBody().addToStatements(
				"List<TaskInstance> tasks=(List<TaskInstance>)processInstance.getTaskMgmtInstance().getUnfinishedTasks(waitingToken)");
		OJIfStatement ifFound = new OJIfStatement();
		ifFound.setCondition("tasks.size()==1");
		OJBlock thenPart = ifFound.getThenPart();
		thenPart.addToStatements("tasks.get(0).end()");
		implementConditionalFlows(complete, thenPart,false);
		complete.getBody().addToStatements(ifFound);
	}
	@Override
	public boolean requiresUserInteraction(){
		return BehaviorUtil.isUserTask(node);
	}

	/**
	 * Implements assignment methods. These methods return an array of strings holding the userNames of the set of users that could possbly
	 * complete the task in question or
	 */
	private void implementJbpmAssignmentsIfNecessary(OJClassifier c){
		// for targets as well as swimlanes
		if(BehaviorUtil.isUserTask(node)){
			INakedTypedElement targetElement = node.getTargetElement();
			NameWrapper cappedJavaName = node.getInPartition() == null ? node.getMappingInfo().getJavaName() : node.getInPartition().getMappingInfo()
					.getJavaName();
			cappedJavaName = cappedJavaName.getCapped();
			ActionMap actionMap = new ActionMap(node);
			if(targetElement.getNakedMultiplicity().getUpper() > 1){
				String getAcorIds = "getActorIdsFor" + cappedJavaName;
				if(c.findOperation(getAcorIds, Collections.EMPTY_LIST) == null){
					// do not duplicate these methods for swimlane references. One per
					// swimlane per process
					OJOperation actorIds = new OJAnnotatedOperation();
					actorIds.setName(getAcorIds);
					c.addToOperations(actorIds);
					actorIds.setReturnType(new OJPathName("String[]"));
					c.addToImports("java.util.ArrayList");
					OJAnnotatedField results = new OJAnnotatedField();
					results.setName("results");
					results.setType(new OJPathName("Collection<String>"));
					results.setInitExp("new ArrayList<String>()");
					actorIds.getBody().addToLocals(results);
					OJBlock forEach = buildLoopThroughTarget(actorIds, actorIds.getBody(), actionMap);
					forEach.addToStatements("results.add(" + actionMap.targetName() + ".getUsername())");
					actorIds.getBody().addToStatements("return results.toArray(new String[results.size()])");
				}
			}else{
				String getActorId = "getActorIdFor" + cappedJavaName;
				if(c.findOperation(getActorId, Collections.EMPTY_LIST) == null){
					// do not duplicate these methods for swimlane references. One per
					// swimlane per process
					OJOperation actorId = new OJAnnotatedOperation();
					actorId.setName(getActorId);
					c.addToOperations(actorId);
					actorId.setReturnType(new OJPathName("String"));
					OJBlock forEach = buildLoopThroughTarget(actorId, actorId.getBody(), actionMap);
					forEach.addToStatements("return " + actionMap.targetName() + ".getUsername()");
					actorId.getBody().addToStatements("return null");
				}
			}
		}
	}

}
