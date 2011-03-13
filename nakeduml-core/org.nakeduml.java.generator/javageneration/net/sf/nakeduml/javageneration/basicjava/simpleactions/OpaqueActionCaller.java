package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.List;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class OpaqueActionCaller extends SimpleNodeBuilder<INakedOpaqueAction> {
	public OpaqueActionCaller(IOclEngine oclEngine, INakedOpaqueAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		INakedPin returnPin = node.getReturnPin();
		if (returnPin == null) {
			// TODO What now?
		} else if(!node.isTask()){
			List<INakedInputPin> args = node.getArguments();
			for (INakedInputPin arg : args) {
				//Make args available as vars to OCL
				NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap(node.getActivity(), arg, false);
				OJAnnotatedField argField = new OJAnnotatedField(argMap.umlName(), argMap.javaTypePath());
				argField.setInitExp(super.buildPinExpression(operation, block, arg));
				block.addToLocals(argField);
			}
			String expr = ValueSpecificationUtil.expressValue(operation, node.getBody(), node.getContext(), returnPin.getType());
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
			expressor.maybeBuildResultVariable(operation, block, map);
			expr=expressor.storeResults(map, expr, returnPin.getNakedMultiplicity().isMany());
			block.addToStatements(expr);
		}else if(!node.isSynchronous()){
			NakedClassifierMap map = new NakedClassifierMap(node.getMessageStructure());
			OJAnnotatedField taskVar = new OJAnnotatedField("task", map.javaTypePath());
			taskVar.setInitExp("new " + map.javaType() + "()");
			operation.getBody().addToLocals(taskVar);
			operation.getBody().addToStatements("task.execute()");
			//TODO assign task
		}
	}
}
