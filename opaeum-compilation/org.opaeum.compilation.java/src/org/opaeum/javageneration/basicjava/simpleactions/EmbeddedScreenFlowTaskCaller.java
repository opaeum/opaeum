package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.TaskUtil;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedScreenFlowTaskCaller extends AbstractBehaviorCaller<CallBehaviorAction>{
	private TaskUtil taskUtil;
	public EmbeddedScreenFlowTaskCaller(OpaeumLibrary oclEngine,CallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
		taskUtil=new TaskUtil(oclEngine);
	}
	@Override
	protected void maybeStartBehavior(OJAnnotatedOperation operation,OJBlock block,NakedStructuralFeatureMap resultMap){
		String taskName = resultMap.fieldname();
		taskUtil.implementAssignmentsAndDeadlines(operation, block, EmfActionUtil.getTaskDefinition( node, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK), taskName);
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
