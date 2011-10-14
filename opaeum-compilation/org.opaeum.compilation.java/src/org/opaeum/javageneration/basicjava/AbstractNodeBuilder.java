package org.opaeum.javageneration.basicjava;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.actions.IActionWithTargetPin;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractNodeBuilder {
	protected OpaeumLibrary library;
	protected AbstractObjectNodeExpressor expressor;

	protected AbstractNodeBuilder(OpaeumLibrary library, AbstractObjectNodeExpressor expressor) {
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
			return pin.getName();
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
		if(pin.getOwnerElement() instanceof INakedAction && ((INakedAction) pin.getOwnerElement()).getOwnerElement() instanceof INakedStructuredActivityNode){
			expression = ValueSpecificationUtil.replaceThisWith(expression, "getContainingActivity()");
		}
		return expression;
	}

	protected final OpaeumLibrary getLibrary() {
		return library;
	}
}
