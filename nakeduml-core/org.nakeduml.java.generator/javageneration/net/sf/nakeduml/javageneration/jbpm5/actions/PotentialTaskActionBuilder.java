package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedAction> extends Jbpm5ActionBuilder<A>{
	protected NakedStructuralFeatureMap callMap;
	protected PotentialTaskActionBuilder(NakedUmlLibrary oclEngine,A node){
		super(oclEngine, node);
		if(node instanceof IActionWithTargetElement && BehaviorUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap((IActionWithTargetElement)node, getLibrary());
		}
	}
	@Override
	public void implementSupportingTaskMethods(OJClass activityClass){
		implementCompleteMethod(activityClass);
	}
	private void implementCompleteMethod(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		OJAnnotatedOperation complete = null;
		String completeMethodName = null;
		INakedMessageStructure message = null;
		if(node instanceof INakedCallOperationAction && ((INakedCallOperationAction) node).getOperation() instanceof INakedResponsibility){
			INakedCallOperationAction call = (INakedCallOperationAction) node;
			NakedOperationMap map = new NakedOperationMap(call.getOperation());
			activityClass.addToImplementedInterfaces(map.callbackListenerPath());
			completeMethodName = map.callbackOperName();
			message = call.getMessageStructure();
		}else{
			message = ((INakedEmbeddedTask) node).getMessageStructure();
			completeMethodName = "on" + node.getMappingInfo().getJavaName().getCapped() + "Completed";
		}
		complete = (OJAnnotatedOperation) OJUtil.findOperation(activityClass, completeMethodName);
		activityClass.addToImports(AbstractEventHandlerInserter.UML_NODE_INSTANCE);
		if(complete == null){
			complete = new OJAnnotatedOperation(completeMethodName);
			activityClass.addToOperations(complete);
			complete.getBody().addToLocals(new OJAnnotatedField("waitingNode", AbstractEventHandlerInserter.UML_NODE_INSTANCE));
			complete.addParam("completedTask", new NakedClassifierMap(message).javaTypePath());
		}
		OJIfStatement ifFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(completedTask.getNodeInstanceUniqueId()))!=null");
		complete.getBody().addToStatements(ifFound);
		implementConditions(complete, ifFound.getThenPart(), node, false);
		if(callMap.isOne()){
			//continue with process
		}else{
			//continue with process only if all tasks are complete
			OJForStatement forEachTask = new OJForStatement("task", callMap.javaBaseTypePath(), callMap.getter() + "()");
			ifFound.getThenPart().addToStatements(forEachTask);
			forEachTask.getBody().addToStatements(new OJIfStatement("!task.isComplete()", "return"));
		}
		implementConditionalFlows(complete, ifFound.getThenPart());
	}
	@Override
	public abstract boolean isTask();
}
