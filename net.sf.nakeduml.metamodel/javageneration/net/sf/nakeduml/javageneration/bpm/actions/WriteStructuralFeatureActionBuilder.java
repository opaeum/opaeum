package net.sf.nakeduml.javageneration.bpm.actions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.oclengine.IOclEngine;

public class WriteStructuralFeatureActionBuilder extends JbpmActionBuilder<INakedAddStructuralFeatureValueAction>{
	public WriteStructuralFeatureActionBuilder(IOclEngine oclEngine,INakedAddStructuralFeatureValueAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJOperation operation){
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, operation.getBody(), actionMap);
		StructuralFeatureMap map = new NakedStructuralFeatureMap(node.getFeature());
		forEach.addToStatements(actionMap.targetName()+"." + map.setter() + "(" + buildPinField(operation, forEach, node.getValue()) + ")");
	}
}
