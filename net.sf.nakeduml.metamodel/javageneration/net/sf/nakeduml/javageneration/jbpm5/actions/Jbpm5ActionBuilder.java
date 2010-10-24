package net.sf.nakeduml.javageneration.jbpm5.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractActionBuilder;
import net.sf.nakeduml.javageneration.oclexpressions.ConstraintGenerator;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class Jbpm5ActionBuilder<A extends INakedActivityNode> extends AbstractActionBuilder {
	protected A node;

	protected Jbpm5ActionBuilder(IOclEngine oclEngine, A node) {
		super(oclEngine);
		this.node = node;
	}

	public abstract void implementActionOn(OJOperation oper);

	public void implementPreConditions(OJOperation oper) {
		if (node instanceof PreAndPostConstrained) {
			implmementConditions(oper, (PreAndPostConstrained) node, true);
		}
	}

	public void implmementConditions(OJOperation oper, PreAndPostConstrained constrained, boolean pre) {
		Collection<IOclContext> conditions =pre? constrained.getPreConditions(): constrained.getPostConditions();
		if (conditions.size() > 0) {
			OJBlock block = new OJBlock();
			if (node instanceof INakedAction) {
				//preConditions and PostConditions work on parameters - emulate pins as parameters
				for (INakedPin pin : ((INakedAction) node).getInput()) {
					buildPinField(oper, block, pin);
				}
			}
			ConstraintGenerator cg = new ConstraintGenerator((OJClass) oper.getOwner(), constrained);
			oper.getBody().addToStatements(cg.buildConstraintsBlock(oper, block,conditions, pre));
		}
	}

	public void implementPostConditions(OJOperation oper) {
		if (node instanceof PreAndPostConstrained) {
			implmementConditions(oper, (PreAndPostConstrained) node, false);
		}
	}

	public void maybeContinueFlow(OJOperation operationContext, OJBlock block, INakedActivityEdge edge) {
		if (edge.getSource() instanceof INakedOutputPin) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), (INakedOutputPin) edge.getSource());
			if (edge.getWeight() != null) {
				if (map.isCollection()) {
					OJIfStatement ifStatement = new OJIfStatement();
					IClassifier integerType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
					if (edge.getWeight() != null) {
					}
					String weight = ValueSpecificationUtil.expressValue(operationContext, edge.getWeight(), edge.getSource().getActivity(),
							integerType);
					ifStatement.setCondition(map.getter() + "().size()>=" + weight);
					block.addToStatements(ifStatement);
					block = ifStatement.getThenPart();
				} else {
					// would not make sense - ignore
				}
			}
		}
		continueFlow(block, edge);
	}

	public boolean requiresUserInteraction() {
		return false;
	}

	public void implementSupportingTaskMethods(OJClass activityClass) {
	}



	protected void continueFlow(OJBlock block, INakedActivityEdge edge) {
		INakedActivityNode target = edge.getEffectiveTarget();
		if (target.isImplicitJoin()) {
			block.addToStatements("waitingToken.signal(\"artificial_join_for_" + edge.getMappingInfo().getPersistentName().getWithoutId()
					+ "\")");
		} else {
			block.addToStatements("waitingToken.signal(\"" + edge.getMappingInfo().getPersistentName().getWithoutId() + "\")");
		}
	}

	public void implementConditionalFlows(OJOperation operationContext, OJBlock parentBlock, boolean getToken) {
		if (getToken) {
			getTokenFromExecutionContext(operationContext.getOwner(), parentBlock);
		}
		// TODO this is fine for decisision nodes. Everywhere else it should
		// actually offer the token to ALL
		// transitions to allow for possible forks
		OJIfStatement ifGuard = null;
		for (INakedActivityEdge flow : node.getConditionalOutgoing()) {
			OJIfStatement newIf = new OJIfStatement();
			newIf.setCondition(ValueSpecificationUtil.expressValue(operationContext, flow.getGuard(), node.getActivity(), oclEngine
					.getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)));
			maybeContinueFlow(operationContext, newIf.getThenPart(), flow);
			OJBlock block = null;
			if (ifGuard == null) {
				block = parentBlock;
			} else {
				block = new OJBlock();
				ifGuard.setElsePart(block);
			}
			block.addToStatements(newIf);
			ifGuard = newIf;
		}
		OJBlock block;
		if (ifGuard == null) {
			block = parentBlock;
		} else {
			ifGuard.setElsePart(new OJBlock());
			block = ifGuard.getElsePart();
		}
		if (node.isImplicitFork() && node.getActivity().isProcess()) {
			// ignore guards and weight here, just go straight to the artificial
			// fork
			// TODO implement a fork that evaluates conditions before leaving
			block.addToStatements("waitingToken.signal(\"artificial_fork_for_" + node.getMappingInfo().getPersistentName().getWithoutId()
					+ "\")");
		} else {
			for (INakedActivityEdge e : node.getDefaultOutgoing()) {
				maybeContinueFlow(operationContext, block, e);
			}
		}
	}

	private void getTokenFromExecutionContext(OJClassifier ojClass, OJBlock parentBlock) {
		parentBlock.addToStatements("Token waitingToken=ExecutionContext.currentExecutionContext().getToken()");
		ojClass.addToImports("org.jbpm.graph.exe.ExecutionContext");
		ojClass.addToImports("org.jbpm.graph.exe.Token");
	}

	/**
	 * Returns and expression string in Java that returns the value as
	 * represenete
	 * 
	 * @param block
	 * @param pin
	 * @return
	 */
	@Override
	protected String expressInputPinOrOutParam(OJBlock block, INakedObjectNode pin) {
		// Either an outputpin or parameterNode
		String expression = null;
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		INakedObjectNode feedingNode = pin.getFeedingNode();
		NakedStructuralFeatureMap feedingMap = OJUtil.buildStructuralFeatureMap(feedingNode.getActivity(), feedingNode);
		retrieveOutParamFromBehaviorInstance(block, feedingNode, feedingMap);
		if (BehaviorUtil.hasExecutionInstance(pin.getActivity())) {
			expression = feedingMap.getter() + "()";
		} else {
			expression = feedingMap.umlName();
		}
		return surroundWithSelectionAndTransformation(expression, edge);
	}

	protected void retrieveOutParamFromBehaviorInstance(OJBlock block, INakedObjectNode feedingNode, StructuralFeatureMap feedingMap) {
		if (feedingNode.getOwnerElement() instanceof INakedCallAction) {
			INakedCallAction callAction = (INakedCallAction) feedingNode.getOwnerElement();
			// Was the invoked element a task or process?
			// will be outputpin
			INakedOutputPin outputPin = (INakedOutputPin) feedingNode;
			if (BehaviorUtil.hasExecutionInstance(callAction.getCalledElement())) {
				INakedParameter p = (INakedParameter) outputPin.getLinkedTypedElement();
				if (callAction.getTargetElement() == null || callAction.getTargetElement().getNakedMultiplicity().isSingleObject()) {
					// Only one call, so retrieve the single result
					block.addToStatements(feedingMap.setter() + "(get" + callAction.getMappingInfo().getJavaName().getCapped() + "().get"
							+ p.getMappingInfo().getJavaName().getCapped() + "())");
				} else {
					NakedStructuralFeatureMap actionMap = OJUtil.buildStructuralFeatureMap(callAction, getOclEngine().getOclLibrary());
					// Multiple calls, so retrieve the result of the last
					// call/TODO fix this - create for loop
					ClassifierMap calledElement = new NakedClassifierMap(callAction.getMessageStructure());
					String local = calledElement.javaType() + " " + actionMap.umlName();
					String exp = "=(" + calledElement.javaType() + ")" + actionMap.getter() + "().get(" + actionMap.getter() + "().size())";
					block.addToStatements(local + exp);
					block.addToStatements(feedingMap.setter() + "(" + actionMap.umlName() + ".get"
							+ p.getMappingInfo().getJavaName().getCapped() + "())");
				}
			}
		}
	}

	public boolean waitsForEvent() {
		return false;
	}
	protected final String buildPinField(OJOperation operationContext, OJBlock block, INakedObjectNode pin) {
		if (pin == null) {
			return "!!NoPin!!";
		} else {
			String pinName = " " + pin.getMappingInfo().getJavaName().toString();
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(pin.getActivity(), pin);
			operationContext.getOwner().addToImports(map.javaTypePath());
			OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
			field.setInitExp(buildPinExpression(operationContext, block, pin));
			block.addToLocals(field);
			return pinName;
		}
	}

}
