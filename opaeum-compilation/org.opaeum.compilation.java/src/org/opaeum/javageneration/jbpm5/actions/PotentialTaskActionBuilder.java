package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedAction> extends Jbpm5ActionBuilder<A>{
	protected NakedStructuralFeatureMap callMap;
	protected PotentialTaskActionBuilder(OpaeumLibrary oclEngine,A node){
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
