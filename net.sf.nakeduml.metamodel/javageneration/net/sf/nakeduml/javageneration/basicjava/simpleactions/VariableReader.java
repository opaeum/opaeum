package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class VariableReader extends SimpleActionBuilder<INakedReadVariableAction> {
	public VariableReader(IOclEngine oclEngine, INakedReadVariableAction action, ObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = expressor.maybeBuildResultVariable(operation, block, result);
		NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		String call = featureMap.umlName();
		if(resultMap.isCollection()){
			if(featureMap.isMany()){
				call = expressor.resultGetter(resultMap) + ".addAll(" + call + ")";
			}else{
				call = expressor.resultGetter(resultMap) + ".add(" + call + ")";
			}
		}else{
			// TODO what if the feature is Many? Force the result map to be many?
			call = expressor.resultSetter(resultMap,call);
		}
		block.addToStatements(call);
	}
}