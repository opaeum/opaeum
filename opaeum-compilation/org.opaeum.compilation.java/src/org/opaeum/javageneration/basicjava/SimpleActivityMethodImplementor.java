package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.simpleactions.AbstractCaller;
import org.opaeum.javageneration.basicjava.simpleactions.BehaviorCaller;
import org.opaeum.javageneration.basicjava.simpleactions.ClassifierBehaviorStarter;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedScreenFlowTaskCaller;
import org.opaeum.javageneration.basicjava.simpleactions.EmbeddedSingleScreenTaskCaller;
import org.opaeum.javageneration.basicjava.simpleactions.ExpansionNodeImplementor;
import org.opaeum.javageneration.basicjava.simpleactions.ObjectCreator;
import org.opaeum.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import org.opaeum.javageneration.basicjava.simpleactions.OclActionCaller;
import org.opaeum.javageneration.basicjava.simpleactions.OperationCaller;
import org.opaeum.javageneration.basicjava.simpleactions.ParameterNodeImplementor;
import org.opaeum.javageneration.basicjava.simpleactions.SignalSender;
import org.opaeum.javageneration.basicjava.simpleactions.SimpleNodeBuilder;
import org.opaeum.javageneration.basicjava.simpleactions.StructuralFeatureClearer;
import org.opaeum.javageneration.basicjava.simpleactions.StructuralFeatureReader;
import org.opaeum.javageneration.basicjava.simpleactions.StructuralFeatureValueAdder;
import org.opaeum.javageneration.basicjava.simpleactions.StructuralFeatureValueRemover;
import org.opaeum.javageneration.basicjava.simpleactions.VariableClearer;
import org.opaeum.javageneration.basicjava.simpleactions.VariableReader;
import org.opaeum.javageneration.basicjava.simpleactions.VariableValueAdder;
import org.opaeum.javageneration.basicjava.simpleactions.VariableValueRemover;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedAddStructuralFeatureValueAction;
import org.opaeum.metamodel.actions.INakedAddVariableValueAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.actions.INakedClearStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedClearVariableAction;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.actions.INakedRaiseExceptionAction;
import org.opaeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.actions.INakedRemoveStructuralFeatureValueAction;
import org.opaeum.metamodel.actions.INakedRemoveVariableValueAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedStartClassifierBehaviorAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
public class SimpleActivityMethodImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void implementActivities(INakedBehavioredClassifier bc){
		Collection<? extends INakedBehavior> ownedBehaviors = bc.getOwnedBehaviors();
		for(INakedBehavior b:ownedBehaviors){
			if(b instanceof INakedActivity){
				INakedActivity a = (INakedActivity) b;
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
					OperationMap am = OJUtil.buildOperationMap(a.getSpecification() == null ? a : a.getSpecification());
					OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(am.javaOperName(), am.javaParamTypePaths());
					implementActivityOn(a, oper);
				}
			}
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
			field.setName(map.fieldname());
			field.setType(map.javaTypePath());
			field.setInitExp(map.javaDefaultValue());
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
				block = surroundWithCatchIfRequired((INakedCallAction) node, (AbstractCaller<?>) builder, operation, block);
			}
		}
		if(!(node instanceof INakedExpansionNode && ((INakedExpansionNode) node).isOutputElement())){
			maybeImplementNextNode(operation, block, node);
		}
	}
	private void implementExpansionRegion(OJAnnotatedOperation operation,OJBlock block,INakedExpansionRegion region){
		INakedExpansionNode input = region.getInputElement().get(0);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(region.getActivity(), input, false);
		List<INakedExpansionNode> output = region.getOutputElement();
		for(INakedExpansionNode expansionNode:output){
			NakedStructuralFeatureMap outMap = OJUtil.buildStructuralFeatureMap(region.getActivity().getContext(), expansionNode, true);
			OJAnnotatedField outField = new OJAnnotatedField(outMap.fieldname(), outMap.javaTypePath());
			outField.setInitExp(outMap.javaDefaultValue());
			operation.getOwner().addToImports(outMap.javaBaseDefaultTypePath());
			operation.getOwner().addToImports(outMap.javaTypePath());
			block.addToLocals(outField);
		}
		ObjectNodeExpressor expressor = new ObjectNodeExpressor(getLibrary());
		OJForStatement forEach = new OJForStatement(map.fieldname(), map.javaBaseTypePath(), expressor.expressInputPinOrOutParamOrExpansionNode(block, input));
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
				if(block.findLocal(map.fieldname()) == null && operation.findParameter(map.fieldname()) == null){
					// TODO check for more scopes
					OJAnnotatedField decisionNodeVar = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
					ObjectNodeExpressor expressor = new ObjectNodeExpressor(getLibrary());
					decisionNodeVar.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(block, (INakedObjectFlow) incomingEdge));
					elseBlock.addToLocals(decisionNodeVar);
				}
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
	public static SimpleNodeBuilder<?> resolveBuilder(INakedActivityNode node,OpaeumLibrary lib,AbstractObjectNodeExpressor expressor){
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
			INakedActivityEdge out = pn.getDefaultOutgoing().iterator().next();
			if(out instanceof INakedObjectFlow && ((INakedObjectFlow) out).getTransformation() != null){
				generateTransformationMultiplier(operation.getOwner(), ((INakedObjectFlow) out));
			}
			implementNode(operation, nodeBlock, out.getEffectiveTarget());
		}
	}
	public static void generateTransformationMultiplier(OJClassifier owner,INakedObjectFlow of){
		if(of.getOriginatingObjectNode().getNakedMultiplicity().isMany() || of.getSelection() != null
				&& of.getSelection().getReturnParameter().getNakedMultiplicity().isMany()){
			INakedTypedElement arg = null;
			if(of.getSelection() != null){
				arg = of.getSelection().getReturnParameter();
			}else{
				arg = of.getOriginatingObjectNode();
			}
			if(arg.getNakedMultiplicity().isMany()){
				NakedStructuralFeatureMap targetMap = OJUtil.buildStructuralFeatureMap(of.getActivity(), (INakedObjectNode) of.getTarget());
				OJPathName resultTypePath = targetMap.javaTypePath();
				if(of.getTarget() instanceof INakedExpansionNode){
					resultTypePath = new OJPathName("java.util.Collection");
					resultTypePath.addToElementTypes(targetMap.javaBaseTypePath());
				}
				String transformOperName = OJUtil.buildOperationMap(of.getTransformation()).javaOperName();
				OJAnnotatedOperation transformMany = new OJAnnotatedOperation(transformOperName, resultTypePath);
				owner.addToOperations(transformMany);
				NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap(of.getActivity(), arg);
				transformMany.addParam(argMap.fieldname(), argMap.javaTypePath());
				if(of.getTarget() instanceof INakedExpansionNode){
					transformMany.initializeResultVariable("new ArrayList<" + targetMap.javaType() + ">()");
				}else{
					transformMany.initializeResultVariable(targetMap.javaDefaultValue());
				}
				OJForStatement foreach = new OJForStatement("tmp", argMap.javaBaseTypePath(), argMap.fieldname());
				transformMany.getBody().addToStatements(foreach);
				if(targetMap.isOne() && !(of.getTarget() instanceof INakedExpansionNode)){
					foreach.getBody().addToStatements("return " + transformOperName + "(tmp)");
				}else if(of.getTransformation().getReturnParameter().getNakedMultiplicity().isOne()){
					foreach.getBody().addToStatements("result.add(" + transformOperName + "(tmp))");
				}else{
					foreach.getBody().addToStatements("result.addAll(" + transformOperName + "(tmp))");
				}
			}
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
	private OJBlock surroundWithCatchIfRequired(INakedCallAction nakedCall,AbstractCaller<?> caller,OJAnnotatedOperation operation,OJBlock originalBlock){
		if(BehaviorUtil.shouldSurrounWithTry(nakedCall)){
			OJTryStatement tryStatement = caller.surroundWithCatchIfNecessary(operation, originalBlock);
			for(INakedOutputPin e:nakedCall.getExceptionPins()){
				NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(nakedCall.getActivity().getContext(), e);
				OJPathName pathName = OJUtil.classifierPathname(e.getNakedBaseType());
				operation.getOwner().addToImports(pathName);
				OJIfStatement statement = new OJIfStatement();
				statement.setCondition("e.isParameter(\"" + e.getLinkedTypedElement().getName() + "\")");
				OJField parm = new OJField();
				parm.setName(pinMap.fieldname());
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
