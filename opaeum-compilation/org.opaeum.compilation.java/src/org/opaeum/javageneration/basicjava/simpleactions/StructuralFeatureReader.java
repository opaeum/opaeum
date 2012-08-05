package org.opaeum.javageneration.basicjava.simpleactions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class StructuralFeatureReader extends SimpleNodeBuilder<ReadStructuralFeatureAction> {
	public StructuralFeatureReader(ReadStructuralFeatureAction action, AbstractObjectNodeExpressor expressor) {
		super(action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		OutputPin result = node.getResult();
		StructuralFeatureMap resultMap = ojUtil.buildStructuralFeatureMap(result);
		expressor.buildResultVariable(operation, block, resultMap);
		ActionMap actionMap = ojUtil.buildActionMap(node);
		OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
		StructuralFeatureMap featureMap = ojUtil.buildStructuralFeatureMap((Property) node.getStructuralFeature());
		String call = actionMap.targetName() + "." + featureMap.getter() + "()";
		call=expressor.storeResults(resultMap, call, featureMap.isMany());
		fs.addToStatements(call);
	}
}