package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.List;

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
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
		OJAnnotatedField taskVar = expressor.buildResultVariable(operation, block, map);
		taskVar.setInitExp("new " + map.javaType() + "()");
		block.addToLocals(taskVar);
		List<INakedInputPin> inputValues = node.getInputValues();
		for(INakedInputPin input:inputValues){
			NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), input, false);
			operation.getBody().addToStatements(node.getMappingInfo().getJavaName().getAsIs()+"." + propertyMap.setter() + "(" + readPin(operation, block, input) + ")");
		}
		block.addToStatements(node.getMappingInfo().getJavaName().getAsIs()+".setReturnInfo(context)");
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, node.getTaskDefinition(), node.getMappingInfo().getJavaName().getAsIs());
		//Add to contaiment tree
		block.addToStatements(map.adder()+"("+ taskVar.getName() + ")");
		//Store invocation in process
		block.addToStatements(expressor.setterForSingleResult(map, taskVar.getName()));
		block.addToStatements(node.getMappingInfo().getJavaName().getAsIs()+".execute()");
	}
}
