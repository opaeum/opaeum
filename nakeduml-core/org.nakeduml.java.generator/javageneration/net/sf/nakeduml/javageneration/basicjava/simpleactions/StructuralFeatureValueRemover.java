package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.maps.ActionMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class StructuralFeatureValueRemover extends SimpleNodeBuilder<INakedRemoveStructuralFeatureValueAction> {
	public StructuralFeatureValueRemover(NakedUmlLibrary oclEngine, INakedRemoveStructuralFeatureValueAction action,
			AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		String valuePinField = readPin(operation, block, node.getValue());
		ActionMap actionMap = new ActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(node.getFeature());
		if (map.isOne()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(" + valuePinField + ")");
		} else if (node.getValue().getNakedMultiplicity().isSingleObject()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.remover() + "(" + valuePinField + ")");
		} else {
			forEach.addToStatements(actionMap.targetName() + "." + map.removeAll() + "(" + valuePinField + ")");
		}
	}
}