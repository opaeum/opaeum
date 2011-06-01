package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedSingleScreenTaskCaller extends SimpleNodeBuilder<INakedEmbeddedSingleScreenTask>{
	public EmbeddedSingleScreenTaskCaller(IOclEngine oclEngine,INakedEmbeddedSingleScreenTask action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedClassifierMap map = new NakedClassifierMap(node.getMessageStructure());
		OJAnnotatedField taskVar = new OJAnnotatedField("task", map.javaTypePath());
		taskVar.setInitExp("new " + map.javaType() + "()");
		operation.getBody().addToLocals(taskVar);
		List<INakedInputPin> inputValues = node.getInputValues();
		for(INakedInputPin input:inputValues){
			NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), input, false);
			operation.getBody().addToStatements("task." + propertyMap.setter() + "(" + buildPinExpression(operation, block, input) + ")");
		}
		operation.getBody().addToStatements("task.setReturnInfo(context)");
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, node.getTaskDefinition(), "task.getTaskInstance()");
		// TODO populate people assignments
		operation.getBody().addToStatements("task.execute()");
	}
}
