package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedMessageStructureMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.UmlNodeInstance;

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
		OJAnnotatedClass ojContext = null;
		boolean operationBelongsToContext = behavior.getContext() != null && behavior.getContext().conformsTo(nakedOperation.getOwner());
		if (operationBelongsToContext) {
			ojContext = findJavaClass(behavior.getContext());
		} else if (behavior.conformsTo(nakedOperation.getOwner())) {
			ojContext = findJavaClass(behavior);
		}
		NakedOperationMap map = new NakedOperationMap(nakedOperation);
		OJOperation ojOperation = ojContext.findOperation(map.javaOperName(), map.javaParamTypePaths());
		if (ojOperation == null) {
			// In superclass, but not overridden
			ojOperation = implementInheritedEventMethod(nakedOperation, ojContext, map);
		}
		OJStatement statement = null;
		if (operationBelongsToContext) {
			statement = buildCallFromContextToEventHandlerOnBehavior(behavior, nakedOperation, ojOperation);
		} else {
			// Operation belongs to behavior directly
			statement = new OJSimpleStatement(callToEventHandler(nakedOperation, ojOperation));
		}
		List<OJStatement> statements = ojOperation.getBody().getStatements();
		if (nakedOperation.hasReturnParameter()) {
			statements.add(statements.size() - 1, statement);
		} else {
			statements.add(statement);
		}
	}

	private OJOperation implementInheritedEventMethod(INakedOperation nakedOperation, OJAnnotatedClass myOwner, NakedOperationMap map) {
		OJOperation ojOperation;
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
		return ojOperation;
	}

	private OJStatement buildCallFromContextToEventHandlerOnBehavior(INakedBehavior behavior, INakedElement nakedOperation,
			OJOperation ojOperation) {
		OJStatement statement;
		if (behavior.isClassifierBehavior()) {
			statement = new OJSimpleStatement("getClassifierBehavior()." + callToEventHandler(nakedOperation, ojOperation));
		} else {
			NakedMessageStructureMap map = new NakedMessageStructureMap(behavior);
			OJForStatement forEach = new OJForStatement("behavior", map.javaBaseTypePath(), map.fieldName());
			forEach.getBody().addToStatements("behavior." + callToEventHandler(nakedOperation, ojOperation));
			statement = forEach;
		}
		return statement;
	}

	private String callToEventHandler(INakedElement element, OJOperation ojOperation) {
		StringBuilder statement = new StringBuilder("on");
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
		return statement.toString();
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
		if (behavior.getContext() != null) {
			OJAnnotatedClass context = findJavaClass(behavior.getContext());
			OJOperation parentProcessSignal = OJUtil.findOperation(context, "processSignal");
			if (parentProcessSignal == null) {
				OJOperation copy = processSignal.getDeepCopy();
				context.addToOperations(copy);
				copy.getBody().addToStatements("return false");
			} else {
				List<OJStatement> toStatements = parentProcessSignal.getBody().getStatements();
				List<OJStatement> fromStatements = processSignal.getBody().getStatements();
				for (OJStatement ojStatement : fromStatements) {
					toStatements.add(toStatements.size() - 1, ojStatement);
				}
			}
		}
		processSignal.getBody().addToStatements("return false");
	}

	private void insertSignalCallInProcessSignal(OJAnnotatedOperation processSignal, INakedSignal signal) {
		NakedClassifierMap map = new NakedClassifierMap(signal);
		
		OJIfStatement ifInstance = new OJIfStatement("signal instanceof " + map.javaType());
		processSignal.getOwner().addToImports(map.javaTypePath());
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
			// if context==null, the handler has already been implemented
			OJPathName path = OJUtil.classifierPathname(behavior.getContext());
			OJClass myOwner = (OJClass) this.javaModel.findIntfOrCls(path);
			myOwner.addToImports(signal.getMappingInfo().getQualifiedJavaName());
			String signalReception = "on" + signal.getMappingInfo().getJavaName().getCapped();
			OJOperation ojOperation = OJUtil.findOperation(myOwner, signalReception);
			if (ojOperation == null) {
				ojOperation = new OJAnnotatedOperation();
				ojOperation.setName(signalReception);
				myOwner.addToOperations(ojOperation);
				for (INakedProperty p : signal.getArgumentParameters()) {
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					ojOperation.addParam(map.umlName(), map.javaTypePath());
				}
				ojOperation.setReturnType(new OJPathName("boolean"));
				ojOperation.getBody().addToStatements("return false");
			}
			OJStatement statement = buildCallFromContextToEventHandlerOnBehavior(behavior, signal, ojOperation);
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
			methodName = Jbpm5Util.getTimerCallbackMethodName((INakedTimeEvent) event);
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
		nodes.setName("waitingNode");
		OJPathName umlNodeInstance = ReflectionUtil.getUtilInterface(UmlNodeInstance.class);
		activityClass.addToImports(umlNodeInstance);
		nodes.setType(umlNodeInstance);
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
		activityClass.addToImports(Jbpm5Util.getNodeInstance());
		OJOperation findWaitingNodeByNodeId = new OJAnnotatedOperation();
		activityClass.addToOperations(findWaitingNodeByNodeId);
		findWaitingNodeByNodeId.setName("findWaitingNodeByNodeId");
		findWaitingNodeByNodeId.addParam("step", new OJPathName("long"));
		findWaitingNodeByNodeId.setReturnType(Jbpm5Util.getNodeInstance());
		OJForStatement forNodeInstances = new OJForStatement();
		forNodeInstances.setBody(new OJBlock());
		forNodeInstances.setElemType(Jbpm5Util.getNodeInstance());
		forNodeInstances.setElemName("nodeInstance");
		activityClass.addToImports("java.util.Collection");
		forNodeInstances.setCollection("getNodeInstancesRecursively()");
		findWaitingNodeByNodeId.getBody().addToStatements(forNodeInstances);
		OJIfStatement ifNameEquals = new OJIfStatement("(("+Jbpm5Util.getNode().getLast() +")nodeInstance.getNode()).getId()==step", "return nodeInstance");
		forNodeInstances.getBody().addToStatements(ifNameEquals);
		findWaitingNodeByNodeId.getBody().addToStatements("return null");
	}

	private void addLeavingLogic(OJOperation operationContext, FromNode node, OJBlock body) {
		OJIfStatement ifTokenFound = new OJIfStatement();
		body.addToStatements(ifTokenFound);
		String literalExpression = operationContext.getOwner().getName() + "State." + Jbpm5Util.stepLiteralName(node.getWaitingElement());
		if (node.isRestingNode()) {
			ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(" + literalExpression
					+ ".getId()))" + "!=null");
		} else {
			ifTokenFound.setCondition("consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId("
					+ node.getWaitingElement().getMappingInfo().getNakedUmlId() + "l))" + "!=null");
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
