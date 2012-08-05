package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.CallBehaviorAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.TaskUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedScreenFlowTaskCaller extends AbstractBehaviorCaller<CallBehaviorAction>{
	private TaskUtil taskUtil;
	public EmbeddedScreenFlowTaskCaller(CallBehaviorAction action,AbstractObjectNodeExpressor expressor){
		super( action, expressor);
		taskUtil=new TaskUtil(expressor.getOjUtil());
	}
	@Override
	protected void maybeStartBehavior(OJAnnotatedOperation operation,OJBlock block,StructuralFeatureMap resultMap){
		String taskName = resultMap.fieldname();
		taskUtil.implementAssignmentsAndDeadlines(operation, block, getLibrary().getResponsibilityDefinition( node, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK), taskName);
		block.addToStatements(taskName + ".setReturnInfo(context)");
		block.addToStatements(taskName + ".execute()");
	}
	protected StructuralFeatureMap getResultMap(){
		return ojUtil.buildStructuralFeatureMap(node);
	}
	@Override
	protected boolean shouldStoreMessageStructureOnProcess(){
		return true;
	}
}
