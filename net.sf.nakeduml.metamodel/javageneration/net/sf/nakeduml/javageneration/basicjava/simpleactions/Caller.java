package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.util.ExceptionHolder;
import nl.klasse.octopus.oclengine.IOclEngine;

public class Caller extends SimpleNodeBuilder<INakedCallAction> {
	private NakedStructuralFeatureMap callMap;

	public Caller(IOclEngine oclEngine, INakedCallAction action, AbstractObjectNodeExpressor expressor) {
		super(oclEngine, action, expressor);
		if (BehaviorUtil.hasMessageStructure(node)) {
			callMap = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
		}
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation, OJBlock block) {
		if (node.getCalledElement() == null) {
			block.addToStatements("no operation or behavior to call!");
		} else {
			NakedStructuralFeatureMap resultMap = null;
			INakedPin returnPin = node.getReturnPin();
			ActionMap actionMap = new ActionMap(node);
			String call = actionMap.targetName() + "." + node.getCalledElement().getMappingInfo().getJavaName() + "("
					+ populateArguments(operation, node.getArguments()) + ")";
			if (BehaviorUtil.hasMessageStructure(node)) {
				resultMap = OJUtil.buildStructuralFeatureMap(node, oclEngine.getOclLibrary());
			} else if (returnPin != null) {
				resultMap = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
			}
			OJBlock fs = buildLoopThroughTarget(operation, block, actionMap);
			if (resultMap != null) {
				expressor.maybeBuildResultVariable(operation, block, resultMap);
				boolean many = resultMap.isMany();
				if (!(returnPin == null || returnPin.getLinkedTypedElement() == null || BehaviorUtil.hasMessageStructure(node))) {
					many = returnPin.getLinkedTypedElement().getNakedMultiplicity().isMany();
				}
				if (node.isTask()) {
					NakedClassifierMap messageMap = new NakedClassifierMap(node.getMessageStructure());
					fs.addToStatements( messageMap.javaType() + " " + node.getMappingInfo().getJavaName() + " = " + call );
					if (node.getActivity().isProcess()) {
						fs.addToStatements(node.getMappingInfo().getJavaName()  + ".init(context)");
					}
					call = node.getMappingInfo().getJavaName().getAsIs();
				}
				call = expressor.storeResults(resultMap, call, many);
			}
			fs.addToStatements(call);
		}
	}

	private <E extends INakedInputPin> StringBuilder populateArguments(OJOperation operation, Collection<E> input) {
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends INakedInputPin> args = input.iterator();
		while (args.hasNext()) {
			INakedObjectNode pin = args.next();
			arguments.append(buildPinExpression(operation, operation.getBody(), pin));
			if (args.hasNext()) {
				arguments.append(",");
			}
		}
		return arguments;
	}

	public OJTryStatement surroundWithCatchIfNecessary(OJOperation operationContext, OJBlock originalBlock) {
		boolean shouldSurround = BehaviorUtil.shouldSurrounWithTry(node);
		if (shouldSurround) {
			// List<OJField> locals = new
			// ArrayList<OJField>(originalBlock.getLocals());
			List<OJStatement> statements = new ArrayList<OJStatement>(originalBlock.getStatements());
			// originalBlock.removeAllFromLocals();
			originalBlock.removeAllFromStatements();
			OJTryStatement tryStatement = new OJTryStatement();
			tryStatement.setCatchPart(new OJBlock());
			tryStatement.setTryPart(new OJBlock());
			// tryStatement.getTryPart().addToLocals(locals);
			tryStatement.getTryPart().addToStatements(statements);
			operationContext.getOwner().addToImports(ExceptionHolder.class.getName());
			tryStatement.setCatchParam(new OJParameter());
			tryStatement.getCatchParam().setType(new OJPathName("ExceptionHolder"));
			tryStatement.getCatchParam().setName("e");
			originalBlock.addToStatements(tryStatement);
			return tryStatement;
		} else {
			return null;
		}
	}
}
