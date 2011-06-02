package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ScreenFlowTaskCaller extends AbstractCaller<INakedEmbeddedScreenFlowTask>{
	public ScreenFlowTaskCaller(IOclEngine oclEngine,INakedEmbeddedScreenFlowTask action,AbstractObjectNodeExpressor expressor){
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
		OJAnnotatedField processField = new OJAnnotatedField(node.getName(), OJUtil.classifierPathname(node));
		processField.setInitExp(call);
		block.addToLocals(processField);
		INakedResponsibilityDefinition td = node.getTaskDefinition();
		String taskName = processField.getName();
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, td, taskName);
		block.addToStatements(taskName + ".execute()");
		call = taskName;
		fs.addToStatements(expressor.storeResults(resultMap, call, many));
	}
}
