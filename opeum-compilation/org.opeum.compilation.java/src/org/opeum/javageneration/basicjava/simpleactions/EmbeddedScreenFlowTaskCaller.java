package org.opeum.javageneration.basicjava.simpleactions;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.jbpm5.TaskUtil;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opeum.metamodel.workspace.OpeumLibrary;

public class EmbeddedScreenFlowTaskCaller extends AbstractBehaviorCaller<INakedEmbeddedScreenFlowTask>{
	public EmbeddedScreenFlowTaskCaller(OpeumLibrary oclEngine,INakedEmbeddedScreenFlowTask action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	protected void maybeStartBehavior(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		String taskName = resultMap.umlName();
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, node.getTaskDefinition(), taskName);
		block.addToStatements(taskName + ".setReturnInfo(context)");
		block.addToStatements(taskName + ".execute()");
	}
	protected NakedStructuralFeatureMap getResultMap(){
		return OJUtil.buildStructuralFeatureMap(node, getLibrary());
	}
	@Override
	protected boolean shouldStoreMessageStructureOnProcess(){
		return true;
	}
}
