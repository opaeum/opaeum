package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.List;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class EmbeddedSingleScreenTaskCaller extends SimpleNodeBuilder<INakedEmbeddedSingleScreenTask>{
	public EmbeddedSingleScreenTaskCaller(NakedUmlLibrary oclEngine,INakedEmbeddedSingleScreenTask action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		OJAnnotatedField taskVar = expressor.buildResultVariable(operation, block, map);
		taskVar.setInitExp("new " + map.javaType() + "()");
		block.addToLocals(taskVar);
		List<INakedInputPin> inputValues = node.getInputValues();
		INakedActivity activity = node.getActivity();
		for(INakedInputPin input:inputValues){
			NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(activity, input, false);
			operation.getBody().addToStatements(taskVar.getName()+"." + propertyMap.setter() + "(" + readPin(operation, block, input) + ")");
		}
		block.addToStatements(taskVar.getName()+".setReturnInfo(context)");
		TaskUtil.implementAssignmentsAndDeadlines(operation, block, node.getTaskDefinition(), taskVar.getName());
		//Add to containment tree
		INakedProperty attr = activity.findEmulatedAttribute(node);
		block.addToStatements(OJUtil.buildStructuralFeatureMap(attr).adder()+"("+ taskVar.getName() + ")");
		//Store invocation in process
		block.addToStatements(expressor.setterForSingleResult(map, taskVar.getName()));
		block.addToStatements(taskVar.getName()+".execute()");
	}
}
