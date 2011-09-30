package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.maps.NakedOperationMap;
import org.opeum.metamodel.actions.INakedCallOperationAction;
import org.opeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opeum.metamodel.bpm.INakedEmbeddedTask;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedSingleScreenTaskBuilder extends PotentialTaskActionBuilder<INakedEmbeddedSingleScreenTask>{
	EmbeddedSingleScreenTaskCaller delegate;
	public EmbeddedSingleScreenTaskBuilder(OpeumLibrary oclEngine,INakedEmbeddedSingleScreenTask node){
		super(oclEngine, node);
		delegate = new EmbeddedSingleScreenTaskCaller(oclEngine, node, new Jbpm5ObjectNodeExpressor(oclEngine));
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!node.isSynchronous()){
			// TODO think of exception pins perhaps
			delegate.implementActionOn(operation, operation.getBody());
		} // build task variable
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
	@Override
	public void implementCallbackMethods(OJClass activityClass){
		implementCompleteMethod(activityClass);
	}
	private void implementCompleteMethod(OJClass activityClass){
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		String completeMethodName = null;
		INakedMessageStructure message = null;
		message = ((INakedEmbeddedTask) node).getMessageStructure();
		completeMethodName = "on" + node.getMappingInfo().getJavaName().getCapped() + "Completed";
		implementCompleteMethod(activityClass, completeMethodName, message);
	}
}
