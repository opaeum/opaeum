package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;

public class StructuralFeatureValueRemover extends SimpleNodeBuilder<RemoveStructuralFeatureValueAction> {
	public StructuralFeatureValueRemover(RemoveStructuralFeatureValueAction action,
			AbstractObjectNodeExpressor expressor) {
		super(action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		String valuePinField = readPin(operation, block, node.getValue());
		ActionMap actionMap = ojUtil.buildActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap((Property) node.getStructuralFeature());
		if (map.isOne()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(" + valuePinField + ")");
		} else if (node.getValue().isMultivalued()==false) {
			forEach.addToStatements(actionMap.targetName() + "." + map.remover() + "(" + valuePinField + ")");
		} else {
			forEach.addToStatements(actionMap.targetName() + "." + map.removeAll() + "(" + valuePinField + ")");
		}
	}
}