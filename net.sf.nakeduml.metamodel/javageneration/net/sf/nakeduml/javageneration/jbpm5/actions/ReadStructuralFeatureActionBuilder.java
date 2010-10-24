package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ReadStructuralFeatureActionBuilder extends Jbpm5ActionBuilder<INakedReadStructuralFeatureAction>{
	public ReadStructuralFeatureActionBuilder(IOclEngine oclEngine,INakedReadStructuralFeatureAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, operation.getBody(), actionMap);
		NakedStructuralFeatureMap featureMap = new NakedStructuralFeatureMap(node.getFeature());
		NakedStructuralFeatureMap resultMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), node.getResult());
		if(resultMap.isCollection()){
			forEach.addToStatements(resultMap.adder() + "("+actionMap.targetName()+"." + featureMap.getter() + "())");
		}else{
			forEach.addToStatements(resultMap.setter() + "("+actionMap.targetName()+"." + featureMap.getter() + "())");
		}
	}

}
