package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.Caller;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ClassifierBehaviorStarter;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ControlNodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ExpansionNodeImplementor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectCreator;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.OpaqueActionCaller;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.SignalSender;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureClearer;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureReader;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureValueAdder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.StructuralFeatureValueRemover;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableClearer;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableReader;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableValueAdder;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.VariableValueRemover;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAddStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.actions.INakedAddVariableValueAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedClearStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedClearVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.INakedRaiseExceptionAction;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import net.sf.nakeduml.metamodel.actions.INakedRemoveVariableValueAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.oclengine.IOclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class SimpleActivityMethodImplementor extends AbstractJavaProducingVisitor {
	@VisitBefore
	public void implementActivity(INakedActivity a) {
		if (a.getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD && OJUtil.hasOJClass(a.getContext()) && !a.isClassifierBehavior()
				&& a.getOwnerElement() instanceof INakedClassifier) {
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			// Does not support: implicit or explicit parallelism
			// Does not have loopbacks
			// Does not accept any events
			// output pin names must be unique
			// cannot call processes, user responsibilities, or any process that
			// does not return immediately
			// Object flows: sourceAndTarget must be conformant and
			// multilplicity must be compatible
			// only one node should
			OJAnnotatedClass owner = findJavaClass(a.getContext());
			OperationMap am = new NakedOperationMap(a.getSpecification() == null ? a : a.getSpecification());
			OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(am.javaOperName(), am.javaParamTypePaths());
			implementActivityOn(a, oper);
		}
	}

	public void implementActivityOn(INakedActivity a, OJAnnotatedOperation oper) {
		oper.setBody(new OJBlock());
		OJBlock block = oper.getBody();
		INakedActivityNode first = getFirstNode(a.getStartNodes());
		addVariables(a, a.getVariables(), oper.getBody(), oper.getOwner());
		if (first != null) {
			implementNode(oper, block, first);
		}
	}

	private void addVariables(INakedActivity a, Collection<INakedActivityVariable> vars, OJBlock body, OJClassifier owner) {
		for (INakedActivityVariable var : vars) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(a, var);
			OJField field = new OJField();
			field.setName(map.umlName());
			field.setType(map.javaTypePath());
			field.setInitExp(map.javaBaseDefaultValue());
			body.addToLocals(field);
			owner.addToImports(map.javaBaseTypePath());
			owner.addToImports(map.javaDefaultTypePath());
		}
	}

	private void implementNode(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode node) {
		if (node instanceof INakedControlNode) {
			implementControlNode(operation, block, (INakedControlNode) node);
		} else if (node instanceof INakedAction) {
			if (node instanceof INakedExpansionRegion) {
				implementExpansionRegion(operation, block, (INakedExpansionRegion) node);
			} else if (node instanceof INakedStructuredActivityNode) {
				implementStructuredActivityNode(operation, block, (INakedStructuredActivityNode) node);
			} else if (node != null) {
				implementObjectNodeOrAtomicAction(operation, block, node);
			}
		} else {
			// OBject node
			implementObjectNodeOrAtomicAction(operation, block, node);
		}
	}

	private void implementStructuredActivityNode(OJAnnotatedOperation operation, OJBlock block, INakedStructuredActivityNode node) {
		OJBlock nodeBlock = new OJBlock();
		block.addToStatements(nodeBlock);
		implementNode(operation, nodeBlock, getFirstNode(node.getStartNodes()));
		maybeImplementNextNode(operation, block, node);
	}

	private void implementObjectNodeOrAtomicAction(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode node) {
		SimpleNodeBuilder<?> builder = resolveBuilder(node, getOclEngine(), new ObjectNodeExpressor(getOclEngine().getOclLibrary()));
		if (builder != null) {
			builder.implementActionOn(operation, block);
			if (builder instanceof Caller) {
				block = surroundWithCatchIfRequired((INakedCallAction) node, (Caller) builder, operation, block);
			}
		}
		if (!(node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement())) {
			maybeImplementNextNode(operation, block, node);
		}
	}

	private void implementExpansionRegion(OJAnnotatedOperation operation, OJBlock block, INakedExpansionRegion region) {
		INakedExpansionNode input = region.getInputElement().get(0);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(region.getActivity(), input);
		List<INakedExpansionNode> output = region.getOutputElement();
		for (INakedExpansionNode expansionNode : output) {
			NakedStructuralFeatureMap outMap = OJUtil.buildStructuralFeatureMap(region.getActivity().getContext(), expansionNode);
			OJAnnotatedField outField = new OJAnnotatedField(outMap.umlName(), outMap.javaTypePath());
			outField.setInitExp(outMap.javaDefaultValue());
			operation.getOwner().addToImports(outMap.javaBaseDefaultTypePath());
			operation.getOwner().addToImports(outMap.javaTypePath());
			block.addToLocals(outField);
		}
		ObjectNodeExpressor expressor = new ObjectNodeExpressor(getOclEngine().getOclLibrary());
		OJForStatement forEach = new OJForStatement(input.getName(), map.javaBaseTypePath(),
				expressor.expressInputPinOrOutParamOrExpansionNode(block, input));
		block.addToStatements(forEach);
		addVariables(region.getActivity(), region.getVariables(), forEach.getBody(), operation.getOwner());
		// TODO get first node, likely an inputElement to implement
		if (input.getDefaultOutgoing().size() == 1) {
			implementNode(operation, forEach.getBody(), input.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		} else {
			implementNode(operation, forEach.getBody(), getFirstNode(region.getStartNodes()));
		}
		maybeImplementNextNode(operation, block, region);
	}

	private void implementControlNode(OJAnnotatedOperation operation, OJBlock block, INakedControlNode cn) {
		switch (cn.getControlNodeType()) {
		case INITIAL_NODE:
		case MERGE_NODE:
			// TODO Does not seem like this would work. We need to pop the block
			// stack
			maybeImplementNextNode(operation, block, cn);
			break;
		case ACTIVITY_FINAL_NODE:
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
			break;
		case DECISION_NODE:
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
			
			OJBlock elseBlock = block;
			
			INakedActivityEdge incomingEdge = cn.getIncoming().iterator().next();
			if (incomingEdge instanceof INakedObjectFlow) {
				OJField decisionNodeVar = new OJField();
				decisionNodeVar.setName(incomingEdge.getSource().getName());
				INakedClassifier type = ((INakedObjectNode)incomingEdge.getSource()).getNakedBaseType();
				decisionNodeVar.setType(OJUtil.classifierPathname(type));

				SimpleNodeBuilder<?> builder = resolveBuilder(cn, getOclEngine(), new ObjectNodeExpressor(getOclEngine().getOclLibrary()));
				String expression = builder.buildControlNodeExpression(operation, block, cn);
				decisionNodeVar.setInitExp(expression);
				
				elseBlock.addToLocals(decisionNodeVar);
			}
			
			OJIfStatement ifStatement = null;
			for (INakedActivityEdge edge : cn.getConditionalOutgoing()) {
				ifStatement = new OJIfStatement();
				elseBlock.addToStatements(ifStatement);
				ifStatement.setCondition(ValueSpecificationUtil.expressValue(operation, edge.getGuard(), cn.getActivity().getContext(),
						super.workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)));
				implementNode(operation, ifStatement.getThenPart(), edge.getEffectiveTarget());
				ifStatement.setElsePart(new OJBlock());
				elseBlock = ifStatement.getElsePart();
			}
			if (cn.getDefaultOutgoing().isEmpty()) {
				if (ifStatement != null) {
					ifStatement.setElsePart(null);
				}
			} else {
				maybeImplementNextNode(operation, elseBlock, cn);
			}
			break;
		default:
			break;
		}
	}

	public static SimpleNodeBuilder<?> resolveBuilder(INakedActivityNode node, IOclEngine oclEngine, AbstractObjectNodeExpressor expressor) {
		SimpleNodeBuilder<?> actionBuilder = null;
		if (node instanceof INakedParameterNode) {
			actionBuilder = new ParameterNodeImplementor(oclEngine, (INakedParameterNode) node, expressor);
		} else if (node instanceof INakedExpansionNode) {
			actionBuilder = new ExpansionNodeImplementor(oclEngine, (INakedExpansionNode) node, expressor);
		} else if (node instanceof INakedAddStructuralFeatureValueAction) {
			actionBuilder = new StructuralFeatureValueAdder(oclEngine, (INakedAddStructuralFeatureValueAction) node, expressor);
		} else if (node instanceof INakedRemoveStructuralFeatureValueAction) {
			actionBuilder = new StructuralFeatureValueRemover(oclEngine, (INakedRemoveStructuralFeatureValueAction) node, expressor);
		} else if (node instanceof INakedClearStructuralFeatureAction) {
			actionBuilder = new StructuralFeatureClearer(oclEngine, (INakedClearStructuralFeatureAction) node, expressor);
		} else if (node instanceof INakedReadStructuralFeatureAction) {
			actionBuilder = new StructuralFeatureReader(oclEngine, (INakedReadStructuralFeatureAction) node, expressor);
		} else if (node instanceof INakedAddVariableValueAction) {
			actionBuilder = new VariableValueAdder(oclEngine, (INakedAddVariableValueAction) node, expressor);
		} else if (node instanceof INakedRemoveVariableValueAction) {
			actionBuilder = new VariableValueRemover(oclEngine, (INakedRemoveVariableValueAction) node, expressor);
		} else if (node instanceof INakedClearVariableAction) {
			actionBuilder = new VariableClearer(oclEngine, (INakedClearVariableAction) node, expressor);
		} else if (node instanceof INakedReadVariableAction) {
			actionBuilder = new VariableReader(oclEngine, (INakedReadVariableAction) node, expressor);
		} else if (node instanceof INakedOpaqueAction) {
			actionBuilder = new OpaqueActionCaller(oclEngine, (INakedOpaqueAction) node, expressor);
		} else if (node instanceof INakedCallAction) {
			actionBuilder = new Caller(oclEngine, (INakedCallAction) node, expressor);
		} else if (node instanceof INakedCreateObjectAction) {
			actionBuilder = new ObjectCreator(oclEngine, (INakedCreateObjectAction) node, expressor);
		} else if (node instanceof INakedStartClassifierBehaviorAction) {
			actionBuilder = new ClassifierBehaviorStarter(oclEngine, (INakedStartClassifierBehaviorAction) node, expressor);
		} else if (node instanceof INakedSendSignalAction) {
			actionBuilder = new SignalSender(oclEngine, (INakedSendSignalAction) node, expressor);
		} else if (node instanceof INakedRaiseExceptionAction) {
			actionBuilder = new ExceptionRaiser(oclEngine, (INakedRaiseExceptionAction) node, expressor);
		} else if (node instanceof INakedControlNode) {
			actionBuilder = new ControlNodeBuilder(oclEngine, (INakedControlNode) node, expressor);
		}
		return actionBuilder;
	}

	private void maybeImplementNextNode(OJAnnotatedOperation operation, OJBlock block, INakedActivityNode pn) {
		if (pn.getDefaultOutgoing().size() == 1) {
			OJBlock nodeBlock = new OJBlock();
			block.addToStatements(nodeBlock);
			implementNode(operation, nodeBlock, pn.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}
	}

	private static INakedActivityNode getFirstNode(Collection<INakedActivityNode> startNodes) {
		if (startNodes.isEmpty()) {
			return null;
		} else {
			// IGnore unconnected parameter nodes
			for (INakedActivityNode node : startNodes) {
				if (!(node.getAllEffectiveOutgoing().isEmpty() && node instanceof INakedParameterNode)) {
					return node;
				}
			}
			return null;
		}
	}

	private OJBlock surroundWithCatchIfRequired(INakedCallAction nakedCall, Caller caller, OJAnnotatedOperation operation,
			OJBlock originalBlock) {
		if (BehaviorUtil.shouldSurrounWithTry(nakedCall)) {
			OJTryStatement tryStatement = caller.surroundWithCatchIfNecessary(operation, originalBlock);
			for (INakedOutputPin e : nakedCall.getExceptionPins()) {
				NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(nakedCall.getContext(), e);
				OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
				operation.getOwner().addToImports(pathName);
				OJIfStatement statement = new OJIfStatement();
				statement.setCondition("e.isParameter(\"" + e.getLinkedTypedElement().getName() + "\")");
				OJField parm = new OJField();
				parm.setName(pinMap.umlName());
				parm.setType(pinMap.javaTypePath());
				parm.setInitExp("(" + pinMap.javaType() + ")e.getValue()");
				statement.getThenPart().addToLocals(parm);
				tryStatement.getCatchPart().addToStatements(statement);
				if (e.getOutgoing().size() > 0) {
					INakedActivityEdge outgoing = e.getOutgoing().iterator().next();
					implementNode(operation, statement.getThenPart(), outgoing.getEffectiveTarget());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			for (INakedExceptionHandler e : nakedCall.getHandlers()) {
				StringBuilder sb = new StringBuilder();
				Iterator<INakedClassifier> iter = e.getExceptionTypes().iterator();
				while (iter.hasNext()) {
					INakedClassifier type = iter.next();
					OJPathName pathName = OJUtil.classifierPathname(type);
					sb.append("e.getValue() instanceof ");
					sb.append(pathName.getLast());
					operation.getOwner().addToImports(pathName);
					if (iter.hasNext()) {
						sb.append("||");
					}
				}
				OJIfStatement statement = new OJIfStatement(sb.toString());
				tryStatement.getCatchPart().addToStatements(statement);
				if (e.getHandlerBody() != null) {
					implementNode(operation, statement.getThenPart(), e.getHandlerBody());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			return tryStatement.getTryPart();
		} else {
			return originalBlock;
		}
	}
}
