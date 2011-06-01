package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.bpm.INakedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.TaskDelegation;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ScreenFlowTaskCaller extends AbstractCaller<INakedScreenFlowTask>{
	public ScreenFlowTaskCaller(IOclEngine oclEngine,INakedScreenFlowTask action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap resultMap = null;
		INakedPin returnPin = node.getReturnPin();
		ActionMap actionMap = new ActionMap(node);
		String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
				+ populateArgumentPinsAndBuildArgumentString(operation, node.getArguments()) + ")";
		resultMap = OJUtil.buildStructuralFeatureMap(node, oclEngine.getOclLibrary());
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		expressor.maybeBuildResultVariable(operation, block, resultMap);
		boolean many = resultMap.isMany();
		if(!(returnPin == null || returnPin.getLinkedTypedElement() == null || BehaviorUtil.hasMessageStructure(node))){
			many = returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany();
		}
		NakedClassifierMap messageMap = new NakedClassifierMap(node.getMessageStructure());
		OJAnnotatedField processField = new OJAnnotatedField(node.getName(), messageMap.javaTypePath());
		processField.setInitExp(call);
		block.addToLocals(processField);
		INakedResponsibilityDefinition td = node.getTaskDefinition();
		String taskName = processField.getName();
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, td, taskName);
		block.addToStatements(taskName + ".execute()");
		call = taskName;
		call = expressor.storeResults(resultMap, call, many);
		fs.addToStatements(call);
	}
}
