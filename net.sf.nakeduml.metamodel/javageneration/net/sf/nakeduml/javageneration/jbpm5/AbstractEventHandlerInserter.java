package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedMessageStructureMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
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
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.util.AbstractSignal;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

public abstract class AbstractEventHandlerInserter extends AbstractJavaProducingVisitor {
	protected abstract void implementEventConsumption(FromNode node, OJIfStatement ifNotNull);

	protected abstract void maybeContinueFlow(OJOperation operationContext, OJBlock block, GuardedFlow flow);

	/**
	 * Inserts a call to the eventListener as the last line of code in the body
	 * of the triggering operation
	 */
	private void insertCallToOperationHandler(INakedBehavior behavior, INakedOperation nakedOperation) {
		// TODO this code assumes that the owner of the operation is the only
		// class listening to the event - what about subclasses?
		OJAnnotatedClass myOwner = null;
		if (behavior.getContext() != null && behavior.getContext().conformsTo(nakedOperation.getOwner())) {
			myOwner = findJavaClass(behavior.getContext());
		} else if (behavior.conformsTo(nakedOperation.getOwner())) {
			myOwner = findJavaClass(behavior);
		}
		NakedOperationMap map = new NakedOperationMap(nakedOperation);
		OJOperation ojOperation = myOwner.findOperation(map.javaOperName(), map.javaParamTypePaths());
		if (ojOperation == null) {
			// In superclass, but not overridden
			ojOperation = new OJAnnotatedOperation(map.javaOperName(), map.javaReturnTypePath());
			myOwner.addToOperations(ojOperation);
			StringBuilder sb = new StringBuilder("super." + map.javaOperName() + "(");
			Iterator<? extends INakedParameter> pIter = nakedOperation.getArgumentParameters().iterator();
			for (INakedParameter p = null; pIter.hasNext();) {
				p = pIter.next();
				ojOperation.addParam(p.getName(), map.javaParamTypePath(p));
				sb.append(p.getName());
				if (pIter.hasNext()) {
					sb.append(',');
				}
			}
			sb.append(")");
			if (nakedOperation.hasReturnParameter()) {
				OJAnnotatedField result = new OJAnnotatedField("result", map.javaReturnTypePath());
				result.setInitExp(sb.toString());
				ojOperation.getBody().addToLocals(result);
				ojOperation.getBody().addToStatements("return result");
			} else {
			}
		}
		OJStatement statement = buildCallToEventHandler(behavior, nakedOperation, ojOperation);
		List<OJStatement> statements = ojOperation.getBody().getStatements();
		if (nakedOperation.hasReturnParameter()) {
			statements.add(statements.size() - 1, statement);
		} else {
			statements.add(statement);
		}
	}

	private OJStatement buildCallToEventHandler(INakedBehavior behavior, INakedElement nakedOperation, OJOperation ojOperation) {
		StringBuilder invocation = new StringBuilder("");
		OJStatement statement;
		if (behavior.isClassifierBehavior()) {
			invocation.append("getClassifierBehavior().");
			appendCall(nakedOperation, ojOperation, invocation);
			statement = new OJSimpleStatement(invocation.toString());
		} else {
			boolean operationBelongsToContext = behavior.getContext() != null && nakedOperation instanceof INakedOperation
					&& behavior.getContext().conformsTo(((INakedOperation) nakedOperation).getOwner());
			if (nakedOperation instanceof INakedSignal || operationBelongsToContext) {
				NakedMessageStructureMap map = new NakedMessageStructureMap(behavior);
				OJForStatement forEach = new OJForStatement("behavior", map.javaBaseTypePath(), map.fieldName());
				invocation.append("behavior.");
				appendCall(nakedOperation, ojOperation, invocation);
				forEach.getBody().addToStatements(invocation.toString());
				statement = forEach;
			} else {
				appendCall(nakedOperation, ojOperation, invocation);
				statement = new OJSimpleStatement(invocation.toString());
			}
		}
		return statement;
	}

	private void appendCall(INakedElement element, OJOperation ojOperation, StringBuilder statement) {
		statement.append("on");
		statement.append(element.getMappingInfo().getJavaName().getCapped());
		statement.append("(");
		Iterator<OJParameter> parms = ojOperation.getParameters().iterator();
		while (parms.hasNext()) {
			OJParameter parm = parms.next();
			statement.append(parm.getName());
			if (parms.hasNext()) {
				statement.append(",");
			}
		}
		statement.append(")");
	}

	/**
	 * Implements the processSignal method for signals and injects the event
	 * listening code for operations
	 * 
	 * @param behaviorClass
	 * @param behavior
	 */
	private void insertEventHandlerCalls(OJAnnotatedClass behaviorClass, INakedTriggerContainer behavior) {
		OJAnnotatedOperation processSignal = new OJAnnotatedOperation("processSignal", new OJPathName("boolean"));
		processSignal.addParam("signal", new OJPathName(AbstractSignal.class.getName()));
		behaviorClass.addToOperations(processSignal);
		for (INakedElement element : behavior.getAllMessageTriggers()) {
			if (element instanceof INakedSignal) {
				INakedSignal signal = (INakedSignal) element;
				insertSignalCallInProcessSignal(processSignal, signal);
				insertCallToSignalHandler(behavior, signal);
			} else if (element instanceof INakedOperation) {
				INakedOperation no = (INakedOperation) element;
				insertCallToOperationHandler(behavior, no);
			}
		}
		processSignal.getBody().addToStatements("return false");
		if (behavior.getContext() != null) {
			OJAnnotatedClass context = findJavaClass(behavior.getContext());
			context.addToOperations(processSignal.getDeepCopy());
		}
	}

	private void insertSignalCallInProcessSignal(OJAnnotatedOperation processSignal, INakedSignal signal) {
		NakedClassifierMap map = new NakedClassifierMap(signal);
		OJIfStatement ifInstance = new OJIfStatement("signal instanceof " + map.javaType());
		processSignal.getBody().addToStatements(ifInstance);
		String signalFieldName = signal.getMappingInfo().getJavaName().getDecapped().toString();
		OJAnnotatedField signalField = new OJAnnotatedField(signalFieldName, map.javaTypePath());
		signalField.setInitExp("(" + map.javaType() + ")signal");
		Iterator<INakedProperty> iter = signal.getArgumentParameters().iterator();
		StringBuilder sb = new StringBuilder("return on" + signal.getMappingInfo().getJavaName().getCapped() + "(");
		while (iter.hasNext()) {
			sb.append(signalFieldName);
			NakedStructuralFeatureMap featureMap = new NakedStructuralFeatureMap(iter.next());
			sb.append('.');
			sb.append(featureMap.getter());
			sb.append("()");
			if (iter.hasNext()) {
				sb.append(',');
			}
		}
		sb.append(')');
		ifInstance.getThenPart().addToStatements(sb.toString());
		ifInstance.getThenPart().addToLocals(signalField);
	}

	private void insertCallToSignalHandler(INakedTriggerContainer behavior, INakedSignal signal) {
		if (behavior.getContext() != null) {			
			//if context==null, the handler has already been implemented
			OJPathName path = OJUtil.classifierPathname(behavior.getContext());
			OJClass myOwner = (OJClass) this.javaModel.findIntfOrCls(path);
			String signalReception = "on" + signal.getMappingInfo().getJavaName().getCapped();
			OJOperation ojOperation = OJUtil.findOperation(myOwner, signalReception);
			if (ojOperation == null) {
				ojOperation = new OJAnnotatedOperation();
				ojOperation.setName(signalReception);
				myOwner.addToOperations(ojOperation);
				for (INakedProperty p : signal.getOwnedAttributes()) {
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					ojOperation.addParam(map.umlName(), map.javaTypePath());
				}
				ojOperation.setReturnType(new OJPathName("boolean"));
				ojOperation.getBody().addToStatements("return false");
			}
			OJStatement statement = buildCallToEventHandler(behavior, signal, ojOperation);
			List<OJStatement> statements = ojOperation.getBody().getStatements();
			statements.add(statements.size() - 1, statement);
		}
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
		for (INakedTypedElement param : (List<? extends INakedTypedElement>) eventActions.getArguments()) {
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
		insertEventHandlerCalls(ojBehavior, behavior);
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
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
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
			ifTokenFound.setCondition("consumed==false && (waitingToken=findWaitingNode(" + literalExpression + ".getQualifiedName()))"
					+ "!=null");
		} else {
			ifTokenFound.setCondition("consumed==false && (waitingToken=findWaitingNode(\""
					+ node.getWaitingElement().getMappingInfo().getPersistentName() + "\"))" + "!=null");
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
