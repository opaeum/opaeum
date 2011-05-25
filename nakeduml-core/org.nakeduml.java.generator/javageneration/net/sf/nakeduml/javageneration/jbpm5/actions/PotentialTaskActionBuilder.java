package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collections;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.UmlNodeInstance;

/**
 * Base class for all action builders that could potentially build a task
 * representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedCallAction> extends Jbpm5ActionBuilder<A> {
	protected NakedStructuralFeatureMap callMap;

	protected PotentialTaskActionBuilder(IOclEngine oclEngine, A node) {
		super(oclEngine, node);
		if (node instanceof INakedCallAction && BehaviorUtil.hasMessageStructure((INakedCallAction) node)) {
			callMap = OJUtil.buildStructuralFeatureMap((INakedCallAction) node, getOclEngine().getOclLibrary());
		}
	}

	@Override
	public void implementSupportingTaskMethods(OJClass activityClass) {
		implementJbpmAssignmentsIfNecessary(activityClass);
		implementCompleteMethod(activityClass);
	}

	private void implementCompleteMethod(OJClass activityClass) {
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		// TODO find better place for this
		OJAnnotatedOperation complete = null;
		String completeMethodName = null;
		INakedMessageStructure message =null;
		if (node instanceof INakedCallOperationAction && ((INakedCallOperationAction) node).getOperation() != null) {
			INakedCallOperationAction call = (INakedCallOperationAction) node;
			NakedOperationMap map = new NakedOperationMap(call.getOperation());
			activityClass.addToImplementedInterfaces(map.callbackListenerPath());
			completeMethodName = map.callbackOperName();
			message=call.getMessageStructure();
		} else {
			message=((INakedOpaqueAction)node).getMessageStructure();
			completeMethodName = "on" + node.getMappingInfo().getJavaName().getCapped() + "Completed";
		}
		complete = (OJAnnotatedOperation) OJUtil.findOperation(activityClass, completeMethodName);
		activityClass.addToImports(ReflectionUtil.getUtilInterface(UmlNodeInstance.class));
		if (complete == null) {
			complete = new OJAnnotatedOperation();
			complete.setName(completeMethodName);
			activityClass.addToOperations(complete);
			complete.getBody().addToLocals(new OJAnnotatedField("waitingNode", ReflectionUtil.getUtilInterface(UmlNodeInstance.class)));
			complete.addParam("completedTask", new NakedClassifierMap(message).javaTypePath());
		}
		OJIfStatement ifFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(completedTask.getNodeInstanceUniqueId()))!=null");
		complete.getBody().addToStatements(ifFound);
		implementConditions(complete, ifFound.getThenPart(), node, false);
		if (callMap.isOne()) {
		} else {
			OJForStatement forEachTask = new OJForStatement("task", callMap.javaBaseTypePath(), callMap.getter() + "()");
			ifFound.getThenPart().addToStatements(forEachTask);
			forEachTask.getBody().addToStatements(new OJIfStatement("!task.getComplete()", "return"));
		}
		implementConditionalFlows(complete, ifFound.getThenPart());
	}

	@Override
	public boolean isTask() {
		return node.isTask();
	}

	/**
	 * Implements assignment methods. These methods return an array of strings
	 * holding the userNames of the set of users that could possbly complete the
	 * task in question or
	 */
	private void implementJbpmAssignmentsIfNecessary(OJClassifier c) {
		// for targets as well as swimlanes
		if (node.isTask() && node.getTargetElement() != null) {
			INakedTypedElement targetElement = node.getTargetElement();
			NameWrapper cappedJavaName = node.getInPartition() == null ? node.getMappingInfo().getJavaName() : node.getInPartition()
					.getMappingInfo().getJavaName();
			cappedJavaName = cappedJavaName.getCapped();
			ActionMap actionMap = new ActionMap(node);
			if (targetElement.getNakedMultiplicity().getUpper() > 1) {
				String getAcorIds = "getActorIdsFor" + cappedJavaName;
				if (c.findOperation(getAcorIds, Collections.EMPTY_LIST) == null) {
					// do not duplicate these methods for swimlane references.
					// One per
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
					forEach.addToStatements("results.add(" + actionMap.targetName() + ".getUserName())");
					actorIds.getBody().addToStatements("return results.toArray(new String[results.size()])");
				}
			} else {
				String getActorId = "getActorIdFor" + cappedJavaName;
				if (c.findOperation(getActorId, Collections.EMPTY_LIST) == null) {
					// do not duplicate these methods for swimlane references.
					// One per
					// swimlane per process
					OJOperation actorId = new OJAnnotatedOperation();
					actorId.setName(getActorId);
					c.addToOperations(actorId);
					actorId.setReturnType(new OJPathName("String"));
					// Build if statement if necessary
					OJBlock forEach = buildLoopThroughTarget(actorId, actorId.getBody(), actionMap);
					forEach.addToStatements("return " + actionMap.targetName() + ".getUserName()");
					if (targetElement.getNakedMultiplicity().getLower() == 0) {
						actorId.getBody().addToStatements("return null");
					}
				}
			}
		}
	}
}
