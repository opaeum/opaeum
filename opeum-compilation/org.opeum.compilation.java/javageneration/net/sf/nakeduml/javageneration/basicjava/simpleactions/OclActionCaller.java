package org.opeum.javageneration.basicjava.simpleactions;

import java.util.List;

import org.opeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.actions.INakedOclAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedPin;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class OclActionCaller extends SimpleNodeBuilder<INakedOclAction> {
	public OclActionCaller(OpeumLibrary oclEngine, INakedOclAction action, AbstractObjectNodeExpressor expressor) {
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
