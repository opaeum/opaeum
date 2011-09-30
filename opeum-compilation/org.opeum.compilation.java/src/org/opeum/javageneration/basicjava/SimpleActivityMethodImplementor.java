package org.opeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.simpleactions.AbstractCaller;
import org.opeum.javageneration.basicjava.simpleactions.BehaviorCaller;
import org.opeum.javageneration.basicjava.simpleactions.ClassifierBehaviorStarter;
import org.opeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opeum.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import org.opeum.javageneration.basicjava.simpleactions.ExpansionNodeImplementor;
import org.opeum.javageneration.basicjava.simpleactions.ObjectCreator;
import org.opeum.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import org.opeum.javageneration.basicjava.simpleactions.OclActionCaller;
import org.opeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opeum.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import org.opeum.javageneration.basicjava.simpleactions.SignalSender;
import org.opeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opeum.javageneration.basicjava.simpleactions.StructuralFeatureClearer;
import org.opeum.javageneration.basicjava.simpleactions.StructuralFeatureReader;
import org.opeum.javageneration.basicjava.simpleactions.StructuralFeatureValueAdder;
import org.opeum.javageneration.basicjava.simpleactions.StructuralFeatureValueRemover;
import org.opeum.javageneration.basicjava.simpleactions.VariableClearer;
import org.opeum.javageneration.basicjava.simpleactions.VariableReader;
import org.opeum.javageneration.basicjava.simpleactions.VariableValueAdder;
import org.opeum.javageneration.basicjava.simpleactions.VariableValueRemover;
import org.opeum.javageneration.maps.NakedOperationMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedAddStructuralFeatureValueAction;
import org.opeum.metamodel.actions.INakedAddVariableValueAction;
import org.opeum.metamodel.actions.INakedCallAction;
import org.opeum.metamodel.actions.INakedCallBehaviorAction;
import org.opeum.metamodel.actions.INakedCallOperationAction;
import org.opeum.metamodel.actions.INakedClearStructuralFeatureAction;
import org.opeum.metamodel.actions.INakedClearVariableAction;
import org.opeum.metamodel.actions.INakedCreateObjectAction;
import org.opeum.metamodel.actions.INakedExceptionHandler;
import org.opeum.metamodel.actions.INakedOclAction;
import org.opeum.metamodel.actions.INakedRaiseExceptionAction;
import org.opeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opeum.metamodel.actions.INakedReadVariableAction;
import org.opeum.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import org.opeum.metamodel.actions.INakedRemoveVariableValueAction;
import org.opeum.metamodel.actions.INakedSendSignalAction;
import org.opeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opeum.metamodel.activities.ActivityKind;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityEdge;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedActivityVariable;
import org.opeum.metamodel.activities.INakedControlNode;
import org.opeum.metamodel.activities.INakedExpansionNode;
import org.opeum.metamodel.activities.INakedExpansionRegion;
import org.opeum.metamodel.activities.INakedObjectFlow;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.INakedParameterNode;
import org.opeum.metamodel.activities.INakedStructuredActivityNode;
import org.opeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.metamodel.workspace.OpeumLibrary;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJTryStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
public class SimpleActivityMethodImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void implementActivity(INakedActivity a){
		if(a.getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD && OJUtil.hasOJClass(a.getContext()) && !a.isClassifierBehavior()
				&& a.getOwnerElement() instanceof INakedClassifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			// Does not support: implicit or explicit parallelism
			// Does not have loopbacks
			// Does not accept any events
			// output pin names must be unique
			// cannot call contractedProcesses, user responsibilities, or any process that
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
	public void implementActivityOn(INakedActivity a,OJAnnotatedOperation oper){
		oper.setBody(new OJBlock());
		OJBlock block = oper.getBody();
		INakedActivityNode first = getFirstNode(a.getStartNodes());
		addVariables(a, a.getVariables(), oper.getBody(), oper.getOwner());
		if(first != null){
			implementNode(oper, block, first);
		}
	}
	private void addVariables(INakedActivity a,Collection<INakedActivityVariable> vars,OJBlock body,OJClassifier owner){
		for(INakedActivityVariable var:vars){
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
	private void implementNode(OJAnnotatedOperation operation,OJBlock block,INakedActivityNode node){
		if(node instanceof INakedControlNode){
			implementControlNode(operation, block, (INakedControlNode) node);
		}else if(node instanceof INakedAction){
			if(node instanceof INakedExpansionRegion){
				implementExpansionRegion(operation, block, (INakedExpansionRegion) node);
			}else if(node instanceof INakedStructuredActivityNode){
				implementStructuredActivityNode(operation, block, (INakedStructuredActivityNode) node);
			}else if(node != null){
				implementObjectNodeOrAtomicAction(operation, block, node);
			}
		}else{
			// OBject node
			implementObjectNodeOrAtomicAction(operation, block, node);
		}
	}
	private void implementStructuredActivityNode(OJAnnotatedOperation operation,OJBlock block,INakedStructuredActivityNode node){
		OJBlock nodeBlock = new OJBlock();
		block.addToStatements(nodeBlock);
		implementNode(operation, nodeBlock, getFirstNode(node.getStartNodes()));
		maybeImplementNextNode(operation, block, node);
	}
	private void implementObjectNodeOrAtomicAction(OJAnnotatedOperation operation,OJBlock block,INakedActivityNode node){
		SimpleNodeBuilder<?> builder = resolveBuilder(node, getLibrary(), new ObjectNodeExpressor(getLibrary()));
		if(builder != null){
			builder.implementActionOn(operation, block);
			if(builder instanceof AbstractCaller){
				block = surroundWithCatchIfRequired((INakedCallAction) node, (AbstractCaller) builder, operation, block);
			}
		}
		if(!(node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement())){
			maybeImplementNextNode(operation, block, node);
		}
	}
	private void implementExpansionRegion(OJAnnotatedOperation operation,OJBlock block,INakedExpansionRegion region){
		INakedExpansionNode input = region.getInputElement().get(0);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(region.getActivity(), input);
		List<INakedExpansionNode> output = region.getOutputElement();
		for(INakedExpansionNode expansionNode:output){
			NakedStructuralFeatureMap outMap = OJUtil.buildStructuralFeatureMap(region.getActivity().getContext(), expansionNode);
			OJAnnotatedField outField = new OJAnnotatedField(outMap.umlName(), outMap.javaTypePath());
			outField.setInitExp(outMap.javaDefaultValue());
			operation.getOwner().addToImports(outMap.javaBaseDefaultTypePath());
			operation.getOwner().addToImports(outMap.javaTypePath());
			block.addToLocals(outField);
		}
		ObjectNodeExpressor expressor = new ObjectNodeExpressor(getLibrary());
		OJForStatement forEach = new OJForStatement(input.getName(), map.javaBaseTypePath(), expressor.expressInputPinOrOutParamOrExpansionNode(block, input));
		block.addToStatements(forEach);
		addVariables(region.getActivity(), region.getVariables(), forEach.getBody(), operation.getOwner());
		// TODO get first node, likely an inputElement to implement
		if(input.getDefaultOutgoing().size() == 1){
			implementNode(operation, forEach.getBody(), input.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}else{
			implementNode(operation, forEach.getBody(), getFirstNode(region.getStartNodes()));
		}
		maybeImplementNextNode(operation, block, region);
	}
	private void implementControlNode(OJAnnotatedOperation operation,OJBlock block,INakedControlNode cn){
		switch(cn.getControlNodeType()){
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
			if(incomingEdge instanceof INakedObjectFlow){
				// TODO the originatingOBjectNode my not have the correct type after transformations and selections
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(cn.getActivity(), ((INakedObjectFlow) incomingEdge).getOriginatingObjectNode(), false);
				OJAnnotatedField decisionNodeVar = new OJAnnotatedField(map.umlName(), map.javaTypePath());
				ObjectNodeExpressor expressor = new ObjectNodeExpressor(getLibrary());
				decisionNodeVar.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(block, (INakedObjectFlow) incomingEdge));
				elseBlock.addToLocals(decisionNodeVar);
			}
			OJIfStatement ifStatement = null;
			for(INakedActivityEdge edge:cn.getConditionalOutgoing()){
				ifStatement = new OJIfStatement();
				elseBlock.addToStatements(ifStatement);
				ifStatement.setCondition(ValueSpecificationUtil.expressValue(operation, edge.getGuard(), cn.getActivity().getContext(), super.workspace.getOclEngine()
						.getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)));
				implementNode(operation, ifStatement.getThenPart(), edge.getEffectiveTarget());
				ifStatement.setElsePart(new OJBlock());
				elseBlock = ifStatement.getElsePart();
			}
			if(cn.getDefaultOutgoing().isEmpty()){
				if(ifStatement != null){
					ifStatement.setElsePart(null);
				}
			}else{
				maybeImplementNextNode(operation, elseBlock, cn);
			}
			break;
		default:
			break;
		}
	}
	public static SimpleNodeBuilder<?> resolveBuilder(INakedActivityNode node,OpeumLibrary lib,AbstractObjectNodeExpressor expressor){
		SimpleNodeBuilder<?> actionBuilder = null;
		if(node instanceof INakedParameterNode){
			actionBuilder = new ParameterNodeImplementor(lib, (INakedParameterNode) node, expressor);
		}else if(node instanceof INakedExpansionNode){
			actionBuilder = new ExpansionNodeImplementor(lib, (INakedExpansionNode) node, expressor);
		}else if(node instanceof INakedAddStructuralFeatureValueAction){
			actionBuilder = new StructuralFeatureValueAdder(lib, (INakedAddStructuralFeatureValueAction) node, expressor);
		}else if(node instanceof INakedRemoveStructuralFeatureValueAction){
			actionBuilder = new StructuralFeatureValueRemover(lib, (INakedRemoveStructuralFeatureValueAction) node, expressor);
		}else if(node instanceof INakedClearStructuralFeatureAction){
			actionBuilder = new StructuralFeatureClearer(lib, (INakedClearStructuralFeatureAction) node, expressor);
		}else if(node instanceof INakedReadStructuralFeatureAction){
			actionBuilder = new StructuralFeatureReader(lib, (INakedReadStructuralFeatureAction) node, expressor);
		}else if(node instanceof INakedAddVariableValueAction){
			actionBuilder = new VariableValueAdder(lib, (INakedAddVariableValueAction) node, expressor);
		}else if(node instanceof INakedRemoveVariableValueAction){
			actionBuilder = new VariableValueRemover(lib, (INakedRemoveVariableValueAction) node, expressor);
		}else if(node instanceof INakedClearVariableAction){
			actionBuilder = new VariableClearer(lib, (INakedClearVariableAction) node, expressor);
		}else if(node instanceof INakedReadVariableAction){
			actionBuilder = new VariableReader(lib, (INakedReadVariableAction) node, expressor);
		}else if(node instanceof INakedOclAction){
			actionBuilder = new OclActionCaller(lib, (INakedOclAction) node, expressor);
		}else if(node instanceof INakedEmbeddedSingleScreenTask){
			actionBuilder = new EmbeddedSingleScreenTaskCaller(lib, (INakedEmbeddedSingleScreenTask) node, expressor);
		}else if(node instanceof INakedCallOperationAction){
			actionBuilder = new OperationCaller(lib, (INakedCallOperationAction) node, expressor);
		}else if(node instanceof INakedEmbeddedScreenFlowTask){
			actionBuilder = new EmbeddedScreenFlowTaskCaller(lib, (INakedEmbeddedScreenFlowTask) node, expressor);
		}else if(node instanceof INakedCallBehaviorAction){
			actionBuilder = new BehaviorCaller(lib, (INakedCallBehaviorAction) node, expressor);
		}else if(node instanceof INakedCreateObjectAction){
			actionBuilder = new ObjectCreator(lib, (INakedCreateObjectAction) node, expressor);
		}else if(node instanceof INakedStartClassifierBehaviorAction){
			actionBuilder = new ClassifierBehaviorStarter(lib, (INakedStartClassifierBehaviorAction) node, expressor);
		}else if(node instanceof INakedSendSignalAction){
			actionBuilder = new SignalSender(lib, (INakedSendSignalAction) node, expressor);
		}else if(node instanceof INakedRaiseExceptionAction){
			actionBuilder = new ExceptionRaiser(lib, (INakedRaiseExceptionAction) node, expressor);
		}
		return actionBuilder;
	}
	private void maybeImplementNextNode(OJAnnotatedOperation operation,OJBlock block,INakedActivityNode pn){
		if(pn.getDefaultOutgoing().size() == 1){
			OJBlock nodeBlock = new OJBlock();
			block.addToStatements(nodeBlock);
			implementNode(operation, nodeBlock, pn.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}
	}
	private static INakedActivityNode getFirstNode(Collection<INakedActivityNode> startNodes){
		if(startNodes.isEmpty()){
			return null;
		}else{
			// IGnore unconnected parameter nodes
			for(INakedActivityNode node:startNodes){
				if(!(node.getAllEffectiveOutgoing().isEmpty() && node instanceof INakedParameterNode)){
					return node;
				}
			}
			return null;
		}
	}
	private OJBlock surroundWithCatchIfRequired(INakedCallAction nakedCall,AbstractCaller caller,OJAnnotatedOperation operation,OJBlock originalBlock){
		if(BehaviorUtil.shouldSurrounWithTry(nakedCall)){
			OJTryStatement tryStatement = caller.surroundWithCatchIfNecessary(operation, originalBlock);
			for(INakedOutputPin e:nakedCall.getExceptionPins()){
				NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(nakedCall.getActivity().getContext(), e);
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
				if(e.getOutgoing().size() > 0){
					INakedActivityEdge outgoing = e.getOutgoing().iterator().next();
					implementNode(operation, statement.getThenPart(), outgoing.getEffectiveTarget());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			for(INakedExceptionHandler e:nakedCall.getHandlers()){
				StringBuilder sb = new StringBuilder();
				Iterator<INakedClassifier> iter = e.getExceptionTypes().iterator();
				while(iter.hasNext()){
					INakedClassifier type = iter.next();
					OJPathName pathName = OJUtil.classifierPathname(type);
					sb.append("e.getValue() instanceof ");
					sb.append(pathName.getLast());
					operation.getOwner().addToImports(pathName);
					if(iter.hasNext()){
						sb.append("||");
					}
				}
				OJIfStatement statement = new OJIfStatement(sb.toString());
				tryStatement.getCatchPart().addToStatements(statement);
				if(e.getHandlerBody() != null){
					implementNode(operation, statement.getThenPart(), e.getHandlerBody());
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			return tryStatement.getTryPart();
		}else{
			return originalBlock;
		}
	}
	public void setWorkspace(INakedModelWorkspace workspace){
		this.workspace = workspace;
	}
}