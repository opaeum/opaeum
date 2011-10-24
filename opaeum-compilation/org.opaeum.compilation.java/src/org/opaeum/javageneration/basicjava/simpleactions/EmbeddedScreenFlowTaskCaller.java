package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.TaskUtil;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedScreenFlowTaskCaller extends AbstractBehaviorCaller<INakedEmbeddedScreenFlowTask>{
	public EmbeddedScreenFlowTaskCaller(OpaeumLibrary oclEngine,INakedEmbeddedScreenFlowTask action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	protected void maybeStartBehavior(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		String taskName = resultMap.fieldname();
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
