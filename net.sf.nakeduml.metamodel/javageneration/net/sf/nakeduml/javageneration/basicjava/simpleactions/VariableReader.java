package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class VariableReader extends SimpleActionBuilder<INakedReadVariableAction>{
	public VariableReader(IOclEngine oclEngine,INakedReadVariableAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		INakedOutputPin result = node.getResult();
		StructuralFeatureMap resultMap = buildResultVariable(operation, result);
		NakedStructuralFeatureMap featureMap=OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
	  String call=featureMap.umlName();
		if(resultMap.isCollection()){
			if(featureMap.isMany()){
				call = resultMap.umlName() + ".addAll(" + call + ")";
			}else{
				call = resultMap.umlName() + ".add(" + call + ")";
			}
		}else{
			// TODO what if the variable is Many? Force the result map to be many!
			call = resultMap.umlName() + "=" + call;
		}
		block.addToStatements(call);
	}
}