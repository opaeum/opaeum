package org.opaeum.javageneration.jbpm5.actions;

import java.util.Collection;
import java.util.Collections;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedSingleScreenTaskBuilder extends PotentialTaskActionBuilder<INakedEmbeddedSingleScreenTask>{
	EmbeddedSingleScreenTaskCaller delegate;
	public EmbeddedSingleScreenTaskBuilder(OpaeumLibrary oclEngine,INakedEmbeddedSingleScreenTask node){
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
		implementCallbackOnComplete(activityClass, completeMethodName, message);
	}
	@Override
	protected Collection<INakedClassifier> getRaisedExceptions(){
		return Collections.emptySet();
	}
}
