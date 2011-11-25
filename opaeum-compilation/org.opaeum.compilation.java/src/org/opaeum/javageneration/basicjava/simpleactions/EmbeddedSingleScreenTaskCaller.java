package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.TaskUtil;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedSingleScreenTaskCaller extends SimpleNodeBuilder<INakedEmbeddedSingleScreenTask>{
	public EmbeddedSingleScreenTaskCaller(OpaeumLibrary oclEngine,INakedEmbeddedSingleScreenTask action,AbstractObjectNodeExpressor expressor){
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
		block.addToStatements(expressor.storeResults(map, taskVar.getName(),false));
		block.addToStatements(taskVar.getName()+".execute()");
	}
}
