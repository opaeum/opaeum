package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class OclActionCaller extends SimpleNodeBuilder<INakedOclAction> {
	public OclActionCaller(IOclEngine oclEngine, INakedOclAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedPin returnPin = node.getReturnPin();
		if (returnPin == null) {
			// TODO What now?
		} else {
			List<INakedInputPin> args = node.getInputValues();
			for (INakedInputPin arg : args) {
				//Make args available as vars to OCL
				//Will lead to duplicate variables in simple synchronous methods
				NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), arg, false);
				OJAnnotatedField argField = new OJAnnotatedField(argMap.umlName(), argMap.javaTypePath());
				argField.setInitExp(super.readPin(operation, block, arg));
				block.addToLocals(argField);
			}
			String expr = ValueSpecificationUtil.expressValue(operation, node.getBody(), node.getContext(), returnPin.getType());
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
			expressor.buildResultVariable(operation, block, map);
			expr=expressor.storeResults(map, expr, returnPin.getNakedMultiplicity().isMany());
			block.addToStatements(expr);
		}
	}
}
