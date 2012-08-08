package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;

public class StructuralFeatureClearer extends SimpleNodeBuilder<ClearStructuralFeatureAction> {
	public StructuralFeatureClearer(ClearStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
		super( action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		ActionMap actionMap = ojUtil.buildActionMap(node);
		OJBlock forEach = buildLoopThroughTarget(operation, block, actionMap);
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap((Property) node.getStructuralFeature());
		if (map.isOne()) {
			forEach.addToStatements(actionMap.targetName() + "." + map.setter() + "(null)");
		} else {
			forEach.addToStatements(actionMap.targetName() + "." + map.clearer() + "()");
		}
	}
}
