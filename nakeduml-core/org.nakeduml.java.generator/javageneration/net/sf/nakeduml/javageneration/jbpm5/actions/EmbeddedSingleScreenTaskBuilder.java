package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedSingleScreenTaskBuilder extends PotentialTaskActionBuilder<INakedEmbeddedSingleScreenTask>{
	EmbeddedSingleScreenTaskCaller delegate;
	public EmbeddedSingleScreenTaskBuilder(NakedUmlLibrary oclEngine,INakedEmbeddedSingleScreenTask node){
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
