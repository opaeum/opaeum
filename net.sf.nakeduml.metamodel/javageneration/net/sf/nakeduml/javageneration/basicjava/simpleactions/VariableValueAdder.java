package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAddVariableValueAction;
import nl.klasse.octopus.oclengine.IOclEngine;

public class VariableValueAdder extends SimpleNodeBuilder<INakedAddVariableValueAction> {
	public VariableValueAdder(IOclEngine oclEngine, INakedAddVariableValueAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation oper, OJBlock block) {
		String valuePinField = buildPinExpression(oper, block, node.getValue());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node.getContext(), node.getVariable());
		block.addToStatements(expressor.storeResults(map, valuePinField, map.isMany()));
	}
}
