package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.List;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.TaskUtil;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class EmbeddedSingleScreenTaskCaller extends SimpleNodeBuilder<OpaqueAction>{
	private TaskUtil taskUtil;
	public EmbeddedSingleScreenTaskCaller(OpaeumLibrary oclEngine,OpaqueAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
		taskUtil=new TaskUtil(oclEngine);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		OJAnnotatedField taskVar = expressor.buildResultVariable(operation, block, map);
		taskVar.setInitExp("new " + map.javaType() + "()");
		block.addToLocals(taskVar);
		List<InputPin> inputValues = node.getInputValues();
		Activity activity = getContainingActivity();
		for(InputPin input:inputValues){
			NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(activity, input, false);
			operation.getBody().addToStatements(taskVar.getName()+"." + propertyMap.setter() + "(" + readPin(operation, block, input) + ")");
		}
		block.addToStatements(taskVar.getName()+".setReturnInfo(context)");
		taskUtil.implementAssignmentsAndDeadlines(operation, block, EmfActionUtil.getTaskDefinition( node, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK), taskVar.getName());
		//Add to containment tree
		Property attr = activity.findEmulatedAttribute(node);
		block.addToStatements(OJUtil.buildStructuralFeatureMap(attr).adder()+"("+ taskVar.getName() + ")");
		//Store invocation in process
		block.addToStatements(expressor.storeResults(map, taskVar.getName(),false));
		block.addToStatements(taskVar.getName()+".execute()");
	}
}
