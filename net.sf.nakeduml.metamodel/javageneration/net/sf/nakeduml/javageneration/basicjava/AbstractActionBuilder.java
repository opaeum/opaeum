package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.oclengine.IOclEngine;

public abstract class AbstractActionBuilder {
	protected IOclEngine oclEngine;
	protected ObjectNodeExpressor expressor;

	protected AbstractActionBuilder(IOclEngine oclEngine, ObjectNodeExpressor expressor) {
		this.oclEngine = oclEngine;
		this.expressor=expressor;
	}

	public OJBlock buildLoopThroughTarget(OJOperation operationContext, OJBlock block, ActionMap actionMap) {
		if (actionMap.targetIsImplicitObject()) {
			return block;
		} else {
			IActionWithTarget action = actionMap.getAction();
			String expression = null;
			if (action.getInPartition() != null) {
				// TODO is this wise? letting the partition override all pins
				if (action.getInPartition().getRepresents() instanceof INakedProperty) {
					expression = actionMap.targetMap().getter() + "()";
				} else if (action.getInPartition().getRepresents() instanceof INakedClassifier) {
					expression = "NOT IMPLEMENTED";
					// TODO use group assignment here
				}
			} else {
				expression = buildPinExpression(operationContext, block, action.getTarget());
			}
			if (actionMap.targetMap().isOne()) {
				OJPathName targetPath = actionMap.targetMap().javaBaseTypePath();
				String targetVar = targetPath.getLast() + " " + actionMap.targetName() + "=" + expression;
				block.addToStatements(targetVar);
				if (actionMap.targetMap().isOptional()) {
					OJIfStatement fs = new OJIfStatement();
					fs.setCondition(actionMap.targetName() + "!=null");
					block.addToStatements(fs);
					operationContext.getOwner().addToImports(targetPath);
					return fs.getThenPart();
				} else {
					return block;
				}
			} else {
				OJPathName targetPath = actionMap.targetMap().javaBaseTypePath();
				operationContext.getOwner().addToImports("java.util.Collection");
				expression = "(Collection<" + targetPath.getLast() + ">)" + expression;
				OJForStatement fs = new OJForStatement();
				fs.setCollection(expression);
				fs.setElemName(actionMap.targetName());
				fs.setElemType(targetPath);
				fs.setBody(new OJBlock());
				block.addToStatements(fs);
				operationContext.getOwner().addToImports(targetPath);
				return fs.getBody();
			}
		}
	}

	protected final String buildPinExpression(OJOperation operationContext, OJBlock block, INakedObjectNode pin) {
		String expression = null;
		if (pin instanceof INakedValuePin) {
			expression = ValueSpecificationUtil.expressValue(operationContext, ((INakedValuePin) pin).getValue(), pin.getActivity(),
					pin.getType());
		} else if (pin.getIncoming().size() == 0) {
			expression = ValueSpecificationUtil.expressDefaultOrImplicitObject(pin.getActivity(), pin.getType());
		} else if (pin.getIncoming().size() == 1) {
			expression = this.expressor.expressInputPinOrOutParam(block, pin);
		} else {
			throw new IllegalStateException("Multiple flows are not supported into ObjectNodes");
		}
		return expression;
	}
	protected <E extends INakedInputPin>StringBuilder populateArguments(OJOperation operation,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends INakedInputPin> args = input.iterator();
		while(args.hasNext()){
			INakedObjectNode pin = args.next();
			arguments.append(buildPinExpression(operation, operation.getBody(), pin));
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		return arguments;
	}

	protected final IOclEngine getOclEngine() {
		return oclEngine;
	}
}
