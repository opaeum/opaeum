package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class OclActionCaller extends SimpleNodeBuilder<INakedOclAction> {
	public OclActionCaller(OpaeumLibrary oclEngine, INakedOclAction action, AbstractObjectNodeExpressor expressor) {
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
				OJAnnotatedField argField = new OJAnnotatedField(argMap.fieldname(), argMap.javaTypePath());
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
