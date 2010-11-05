package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class StructuralFeatureReader extends SimpleActionBuilder<INakedReadStructuralFeatureAction>{
	public StructuralFeatureReader(IOclEngine oclEngine,INakedReadStructuralFeatureAction action, ObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		INakedOutputPin result = node.getResult();
		NakedStructuralFeatureMap resultMap = expressor.maybeBuildResultVariable(operation, block, result);
		ActionMap actionMap = new ActionMap(node);
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		String call = actionMap.targetName() + "." + new NakedStructuralFeatureMap(node.getFeature()).getter() + "()";
		NakedStructuralFeatureMap featureMap = OJUtil.buildStructuralFeatureMap(node.getFeature());
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
		fs.addToStatements(call);
	}
}