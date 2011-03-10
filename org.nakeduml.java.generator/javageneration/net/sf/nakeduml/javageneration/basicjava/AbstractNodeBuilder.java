package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;

public abstract class AbstractNodeBuilder {
	protected IOclEngine oclEngine;
	protected AbstractObjectNodeExpressor expressor;

	protected AbstractNodeBuilder(IOclEngine oclEngine, AbstractObjectNodeExpressor expressor) {
		this.oclEngine = oclEngine;
		this.expressor=expressor;
	}

	public OJBlock buildLoopThroughTarget(OJOperation operationContext, OJBlock block, ActionMap actionMap) {
		if (actionMap.targetIsImplicitObject()) {
			return block;
		} else {
			IActionWithTarget action = actionMap.getActionWithTarget();
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
	
	protected String buildControlNodeExpression(OJOperation operationContext, OJBlock block, INakedControlNode cn) {
		return expressor.expressControlNode(block, cn);
	}
	
	protected final IOclEngine getOclEngine() {
		return oclEngine;
	}
}
