package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.bpm.TaskUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EmbeddedSingleScreenTaskCaller extends SimpleNodeBuilder<OpaqueAction>{
	private TaskUtil taskUtil;
	public EmbeddedSingleScreenTaskCaller(OpaqueAction action,AbstractObjectNodeExpressor expressor){
		super(action, expressor);
		taskUtil=new TaskUtil(expressor.getOjUtil());
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(node);
		OJAnnotatedField taskVar = expressor.buildResultVariable(operation, block, map);
		taskVar.setInitExp("new " + map.javaType() + "()");
		block.addToLocals(taskVar);
		List<InputPin> inputValues = node.getInputValues();
		for(InputPin input:inputValues){
			PropertyMap propertyMap = ojUtil.buildStructuralFeatureMap(input);
			operation.getBody().addToStatements(taskVar.getName()+"." + propertyMap.setter() + "(" + readPin(operation, block, input) + ")");
		}
		block.addToStatements(taskVar.getName()+".setReturnInfo(context)");
		taskUtil.implementAssignmentsAndDeadlines(operation, block, getLibrary().getResponsibilityDefinition( node, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK), taskVar.getName());
		//Add to containment tree
		block.addToStatements(ojUtil.buildStructuralFeatureMap(node).adder()+"("+ taskVar.getName() + ")");
		//Store invocation in process
		block.addToStatements(expressor.storeResults(map, taskVar.getName(),false));
		block.addToStatements(taskVar.getName()+".execute()");
	}
}
