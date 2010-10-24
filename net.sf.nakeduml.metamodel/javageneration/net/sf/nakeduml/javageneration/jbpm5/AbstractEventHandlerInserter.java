package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJAnnonymousInnerClass;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class AbstractEventHandlerInserter extends AbstractJavaProducingVisitor {
	protected abstract void implementEventConsumption(FromNode node, OJIfStatement ifNotNull);

	protected abstract void maybeContinueFlow(OJOperation operationContext, OJBlock block, GuardedFlow flow) ;

	/**
	 * Inserts a call to the eventListener as the last line of code in the body
	 * of the triggering operation
	 */
	private void insertEventListenerCall(INakedBehavior behavior, INakedOperation nakedOperation) {
		OJPathName path = OJUtil.classifierPathname(nakedOperation.getOwner());
		OJClass myOwner = (OJClass) this.javaModel.findIntfOrCls(path);
		OJOperation ojOperation = myOwner.findOperation(nakedOperation.getMappingInfo().getJavaName().toString(),
				toOJPathNames(nakedOperation.getParamTypes()));
		if (ojOperation != null) {
			StringBuilder statement = new StringBuilder("");
			if (behavior.isClassifierBehavior() && behavior.getContext().equals(nakedOperation.getOwner())) {
				statement.append("getClassifierBehavior().");
			} else {
				// TODO broadcast to all running istances?
			}
			statement.append("on");
			statement.append(nakedOperation.getMappingInfo().getJavaName().getCapped());
			statement.append("(");
			Iterator parms = ojOperation.getParameters().iterator();
			while (parms.hasNext()) {
				OJParameter parm = (OJParameter) parms.next();
				statement.append(parm.getName());
				if (parms.hasNext()) {
					statement.append(",");
				}
			}
			statement.append(")");
			List<OJStatement> statements = ojOperation.getBody().getStatements();
			if (nakedOperation.hasReturnParameter()) {
				statements.add(statements.size() - 2, new OJSimpleStatement(statement.toString()));
			} else {
				statements.add(new OJSimpleStatement(statement.toString()));
			}
		}
	}

	/**
	 * Implements the processSignal method for signals and injects the event
	 * listening code for operations
	 * 
	 * @param behaviorClass
	 * @param behavior
	 */
	private void implementSignalProcessing(OJAnnotatedClass behaviorClass, INakedTriggerContainer behavior) {
		for (INakedElement element : behavior.getAllMessageTriggers()) {
			if (element instanceof INakedSignal) {
				INakedSignal signal = (INakedSignal) element;
				insertSignalListenerCall(behavior, signal);
			} else if (element instanceof INakedOperation) {
				INakedOperation no = (INakedOperation) element;
				insertEventListenerCall(behavior, no);
			}
		}
	}

	private void insertSignalListenerCall(INakedTriggerContainer behavior, INakedSignal signal) {
		// TODO this assumes that the Behavior is always nested inside the
		// classifier it is behavior for
		OJPathName path = OJUtil.classifierPathname(behavior.getContext() == null ? behavior : behavior.getContext());
		OJClass myOwner = (OJClass) this.javaModel.findIntfOrCls(path);
		String signalReception = "on" + signal.getMappingInfo().getJavaName().getCapped();
		OJOperation ojOperation = OJUtil.findOperation(myOwner, signalReception);
		if (ojOperation == null) {
			ojOperation = new OJAnnotatedOperation();
			ojOperation.setName(signalReception);
			myOwner.addToOperations(ojOperation);
			for (INakedProperty p : signal.getOwnedAttributes()) {
				if (!OJUtil.isBuiltIn(p)) {
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					ojOperation.addParam(map.umlName(), map.javaTypePath());
				}
			}
			ojOperation.setReturnType(new OJPathName("boolean"));
			ojOperation.getBody().addToStatements("return false");
		}
		StringBuilder statement = new StringBuilder("");
		if (behavior.isClassifierBehavior()) {
			statement.append("getClassifierBehavior().");
		} else {
			// TODO broadcast to all running istances?
		}
		statement.append(signalReception);
		statement.append("(");
		Iterator parms = ojOperation.getParameters().iterator();
		while (parms.hasNext()) {
			OJParameter parm = (OJParameter) parms.next();
			statement.append(parm.getName());
			if (parms.hasNext()) {
				statement.append(",");
			}
		}
		statement.append(")");
		List<OJStatement> statements = ojOperation.getBody().getStatements();
		statements.add(statements.size() - 1, new OJSimpleStatement(statement.toString()));
	}

	/**
	 * Implements a method that is to be called when significant events occur.
	 * By convention it starts with the word "on" and returns a boolean
	 * indicating whether the event was executed or not
	 */
	private void implementEventListener(OJClass activityClass, WaitForEventElements eventActions) {
		INakedElement event = eventActions.getEvent();
		String methodName = null;
		if (event instanceof INakedTimeEvent) {
			// apply persistent name semantics - a revision may occur after the
			// original event was logged
			methodName = "on_" + event.getMappingInfo().getPersistentName().getWithoutId();
		} else {
			methodName = "on" + event.getMappingInfo().getJavaName().getCapped();
		}
		OJOperation listener = new OJAnnotatedOperation();
		listener.setName(methodName);
		listener.setReturnType(new OJPathName("boolean"));
		activityClass.addToOperations(listener);
		List params = eventActions.getArguments();
		Iterator paramIterator = params.iterator();
		while (paramIterator.hasNext()) {
			INakedTypedElement param = (INakedTypedElement) paramIterator.next();
			ClassifierMap map = new NakedClassifierMap(param.getType());
			listener.addParam(param.getMappingInfo().getJavaName().toString(), map.javaTypePath());
		}
		OJAnnotatedField processed = new OJAnnotatedField();
		processed.setName("consumed");
		processed.setType(new OJPathName("boolean"));
		processed.setInitExp("false");
		listener.getBody().addToLocals(processed);
		OJAnnotatedField nodes = new OJAnnotatedField();
		nodes.setName("waitingToken");
		nodes.setType(BpmUtil.getNodeInstance());
		listener.getBody().addToLocals(nodes);
		for (FromNode node : eventActions.getWaitingNodes()) {
			addLeavingLogic(listener, node, listener.getBody());
		}
		listener.setReturnType(new OJPathName("boolean"));
		listener.getBody().addToStatements("return consumed");
	}

	protected void implementEventHandling(OJAnnotatedClass ojBehavior, INakedTriggerContainer behavior, Collection<WaitForEventElements> ea) {
		addFindWaitingNode(ojBehavior);
		for (WaitForEventElements eventActions : ea) {
			implementEventListener(ojBehavior, eventActions);
		}
		implementSignalProcessing(ojBehavior, behavior);
	}

	public OJIfStatement implementConditionalFlows(OJOperation operationContext, FromNode node, OJIfStatement ifTokenFound) {
		OJIfStatement ifGuard = null;
		IClassifier booleanType = workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
		for (GuardedFlow flow : node.getConditionalTransitions()) {
			OJIfStatement newIf = new OJIfStatement();
			newIf.setCondition(ValueSpecificationUtil.expressValue(operationContext, flow.getGuard(), flow.getContext(), booleanType));
			newIf.getThenPart().addToStatements("consumed=true");
			maybeContinueFlow(operationContext, newIf.getThenPart(), flow);
			OJBlock block = null;
			if (ifGuard == null) {
				block = ifTokenFound.getThenPart();
			} else {
				block = new OJBlock();
				ifGuard.setElsePart(block);
			}
			block.addToStatements(newIf);
			ifGuard = newIf;
		}
		return ifGuard;
	}

	private void addFindWaitingNode(OJClass activityClass) {
		activityClass.addToImports(BpmUtil.getNodeInstance());
		OJOperation findWaitingNode = new OJAnnotatedOperation();
		activityClass.addToOperations(findWaitingNode);
		findWaitingNode.setName("findWaitingNode");
		findWaitingNode.addParam("step", new OJPathName("String"));
		findWaitingNode.setReturnType(BpmUtil.getNodeInstance());
		OJForStatement forNodeInstances = new OJForStatement();
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(new OJPathName("NodeInstance"));
		forNodeInstances.setElemName("nodeInstance");
		activityClass.addToImports("java.util.Collection");
		forNodeInstances.setCollection("(Collection<? extends NodeInstance>)getProcessInstance().getNodeInstances()");
		findWaitingNode.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("nodeInstance.getNode().getName().equals(step)", "return nodeInstance");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		findWaitingNode.getBody().addToStatements("return null");
	}

	private void addLeavingLogic(OJOperation operationContext, FromNode node, OJBlock body) {
		OJIfStatement ifTokenFound = new OJIfStatement();
		body.addToStatements(ifTokenFound);
		String literalExpression = operationContext.getOwner().getName() + "State." + BpmUtil.stepLiteralName(node.getWaitingElement());
		if (node.isRestingNode()) {
			ifTokenFound.setCondition("consumed==false && (waitingToken=findWaitingNode(" + literalExpression + ".getQualifiedName()))" + "!=null");
		} else {
			ifTokenFound.setCondition("consumed==false && (waitingToken=findWaitingNode(\"" + node.getWaitingElement().getMappingInfo().getPersistentName() + "\"))" + "!=null");
		}
		implementEventConsumption(node, ifTokenFound);
		OJIfStatement ifGuard = implementConditionalFlows(operationContext, node, ifTokenFound);
		GuardedFlow flow = node.getDefaultTransition();
		if (flow != null) {
			// default flow/transition
			OJBlock block = null;
			if (ifGuard == null) {
				block = ifTokenFound.getThenPart();
			} else {
				block = new OJBlock();
				ifGuard.setElsePart(block);
			}
			block.addToStatements("consumed=true");
			maybeContinueFlow(operationContext, block, flow);
		}
	}
}
