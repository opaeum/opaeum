package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.UmlNodeInstance;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedAction> extends Jbpm5ActionBuilder<A>{
	protected NakedStructuralFeatureMap callMap;
	protected PotentialTaskActionBuilder(IOclEngine oclEngine,A node){
		super(oclEngine, node);
		if(node instanceof INakedCallAction && BehaviorUtil.hasMessageStructure((INakedCallAction) node)){
			callMap = OJUtil.buildStructuralFeatureMap((INakedCallAction) node, getOclEngine().getOclLibrary());
		}
	}
	@Override
	public void implementSupportingTaskMethods(OJClass activityClass){
		implementCompleteMethod(activityClass);
	}
	private void implementCompleteMethod(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		// TODO find better place for this
		OJAnnotatedOperation complete = null;
		String completeMethodName = null;
		INakedMessageStructure message = null;
		if(node instanceof INakedCallOperationAction && ((INakedCallOperationAction) node).getOperation() != null){
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
		activityClass.addToImports(ReflectionUtil.getUtilInterface(UmlNodeInstance.class));
		if(complete == null){
			complete = new OJAnnotatedOperation();
			complete.setName(completeMethodName);
			activityClass.addToOperations(complete);
			complete.getBody().addToLocals(new OJAnnotatedField("waitingNode", ReflectionUtil.getUtilInterface(UmlNodeInstance.class)));
			complete.addParam("completedTask", new NakedClassifierMap(message).javaTypePath());
		}
		OJIfStatement ifFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(completedTask.getNodeInstanceUniqueId()))!=null");
		complete.getBody().addToStatements(ifFound);
		implementConditions(complete, ifFound.getThenPart(), node, false);
		if(callMap.isOne()){
		}else{
			OJForStatement forEachTask = new OJForStatement("task", callMap.javaBaseTypePath(), callMap.getter() + "()");
			ifFound.getThenPart().addToStatements(forEachTask);
			forEachTask.getBody().addToStatements(new OJIfStatement("!task.getComplete()", "return"));
		}
		implementConditionalFlows(complete, ifFound.getThenPart());
	}
	@Override
	public abstract boolean isTask();
}
