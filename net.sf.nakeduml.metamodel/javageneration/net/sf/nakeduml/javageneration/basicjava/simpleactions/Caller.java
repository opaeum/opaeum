package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class Caller extends SimpleActionBuilder<INakedCallAction>{
	public Caller(IOclEngine oclEngine,INakedCallAction action,ObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		NakedStructuralFeatureMap resultMap = null;
		INakedPin returnPin = node.getReturnPin();
		if(returnPin != null){
			resultMap = expressor.maybeBuildResultVariable(operation, block, returnPin);
		}
		ActionMap actionMap = new ActionMap(node);
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
				+ populateArguments(operation, node.getArguments()) + ")";
		//TODO - support calling complex method that return immediately but with multiple results
		if(returnPin != null){
			if(resultMap.isCollection()){
				if(returnPin.getLinkedTypedElement() != null && returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany()){
					call = resultMap.umlName() + ".addAll(" + call + ")";
				}else{
					call = resultMap.umlName() + ".add(" + call + ")";
				}
			}else{
				call = resultMap.umlName() + "=" + call;
			}
		}
		fs.addToStatements(call);
	}
}
