package org.opeum.javageneration.jbpm5.actions;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.IActionWithTargetElement;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.workspace.OpeumLibrary;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedAction> extends Jbpm5ActionBuilder<A>{
	protected NakedStructuralFeatureMap callMap;
	protected PotentialTaskActionBuilder(OpeumLibrary oclEngine,A node){
		super(oclEngine, node);
		if(node instanceof IActionWithTargetElement && BehaviorUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap((IActionWithTargetElement)node, getLibrary());
		}
	}
	protected void implementCompleteMethod(OJClass activityClass,String completeMethodName,INakedMessageStructure message){
		OJAnnotatedOperation complete;
		complete = (OJAnnotatedOperation) OJUtil.findOperation(activityClass, completeMethodName);
		activityClass.addToImports(AbstractEventConsumptionImplementor.UML_NODE_INSTANCE);
		if(complete == null){
			complete = new OJAnnotatedOperation(completeMethodName);
			activityClass.addToOperations(complete);
			complete.getBody().addToLocals(new OJAnnotatedField("waitingNode", AbstractEventConsumptionImplementor.UML_NODE_INSTANCE));
			complete.addParam("nodeInstanceUniqueId", new OJPathName("String"));
			complete.addParam("completedWorkObject", new NakedClassifierMap(message).javaTypePath());
		}
		OJIfStatement ifFound = new OJIfStatement("(waitingNode=(UmlNodeInstance)findNodeInstanceByUniqueId(nodeInstanceUniqueId))!=null");
		complete.getBody().addToStatements(ifFound);
		implementConditions(complete, ifFound.getThenPart(), node, false);
		if(callMap.isOne()){
			//continue with process
		}else{
			//continue with process only if all tasks are complete
			OJForStatement forEachTask = new OJForStatement("workObject", callMap.javaBaseTypePath(), callMap.getter() + "()");
			ifFound.getThenPart().addToStatements(forEachTask);
			forEachTask.getBody().addToStatements(new OJIfStatement("!workObject.isComplete()", "return"));
		}
		implementConditionalFlows(complete, ifFound.getThenPart());
	}
	@Override
	public abstract boolean isLongRunning();
}
