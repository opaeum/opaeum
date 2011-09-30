package org.opeum.javageneration.basicjava;

import org.opeum.javageneration.maps.ActionMap;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.metamodel.actions.IActionWithTargetElement;
import org.opeum.metamodel.actions.IActionWithTargetPin;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.activities.INakedValuePin;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;

public abstract class AbstractNodeBuilder {
	protected OpeumLibrary library;
	protected AbstractObjectNodeExpressor expressor;

	protected AbstractNodeBuilder(OpeumLibrary library, AbstractObjectNodeExpressor expressor) {
		this.library=library;
		this.expressor=expressor;
	}

	public OJBlock buildLoopThroughTarget(OJOperation operationContext, OJBlock block, ActionMap actionMap) {
		if (actionMap.targetIsImplicitObject()) {
			return block;
		} else {
			IActionWithTargetElement action = (IActionWithTargetElement) actionMap.getAction();
			String expression = null;
			if (action.getInPartition() != null) {
				// TODO is this wise? letting the partition override all pins
				if (action.getInPartition().getRepresents() instanceof INakedProperty) {
					expression = actionMap.targetMap().getter() + "()";
				} else if (action.getInPartition().getRepresents() instanceof INakedClassifier) {
					
					expression = "NOT IMPLEMENTED";
					// TODO use group assignment here
				}
			} else if(action instanceof IActionWithTargetPin){
				expression = readPin(operationContext, block, ((IActionWithTargetPin) action).getTarget());
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
	protected final String readPin(OJOperation operationContext, OJBlock block, INakedObjectNode pin) {
		if(expressor.pinsAvailableAsVariables()){
			return TypedElementPropertyBridge.locallyUniqueName(pin);
		}else{
			return expressPin(operationContext, block, pin);
		}
	}

	protected final String expressPin(OJOperation operationContext, OJBlock block, INakedObjectNode pin) {
		String expression = null;
		if (pin instanceof INakedValuePin) {
			expression = ValueSpecificationUtil.expressValue(operationContext, ((INakedValuePin) pin).getValue(), pin.getActivity(),
					pin.getType());
		}else if(pin.getIncomingExceptionHandler()!=null){
			expression=this.expressor.expressExceptionInput(block,pin);
		} else if (pin.getIncoming().size() == 0) {
			expression = ValueSpecificationUtil.expressDefaultOrImplicitObject(pin.getActivity(), pin.getType());
		} else if (pin.getIncoming().size() == 1) {
			expression = this.expressor.expressInputPinOrOutParamOrExpansionNode(block, pin);
		} else {
			throw new IllegalStateException("Multiple flows are not supported into ObjectNodes");
		}
		return expression;
	}

	
	protected final OpeumLibrary getLibrary() {
		return library;
	}
}
