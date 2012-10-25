package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
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
import org.opaeum.javageneration.oclexpressions.PreAndPostConditionGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class,
		PreAndPostConditionGenerator.class
/* Needs repeatable sequence in the ocl generating steps */
})
public class SimpleActivityMethodImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void implementActivities(BehavioredClassifier bc){
		Collection<? extends Behavior> ownedBehaviors = bc.getOwnedBehaviors();
		for(Behavior b:ownedBehaviors){
			if(b instanceof Activity){
				Activity a = (Activity) b;
				if(EmfActivityUtil.isSimpleSynchronousMethod(a) && ojUtil.hasOJClass(a.getContext()) && !EmfBehaviorUtil.isClassifierBehavior(a)
						&& a.getOwner() instanceof Classifier){
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
					OperationMap am = ojUtil.buildOperationMap(a.getSpecification() == null ? a : a.getSpecification());
					OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(am.javaOperName(), am.javaParamTypePaths());
					implementActivityOn(a, oper, oper.getBody());
				}
			}
		}
	}
	public void implementActivityOn(Activity a,OJAnnotatedOperation oper,OJBlock block){
		ActivityNode first = getFirstNode(EmfActivityUtil.getStartNodes(a));
		addVariables(a, a.getVariables(), oper.getBody(), oper.getOwner());
		if(first != null){
			implementNode(oper, block, first);
		}
	}
	private void addVariables(Activity a,Collection<Variable> vars,OJBlock body,OJClassifier owner){
		for(Variable var:vars){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(var);
			OJField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
			field.setInitExp(map.javaDefaultValue());
			body.addToLocals(field);
			owner.addToImports(map.javaBaseTypePath());
			owner.addToImports(map.javaDefaultTypePath());
		}
	}
	private void implementNode(OJAnnotatedOperation operation,OJBlock block,ActivityNode node){
		if(node instanceof ControlNode){
			implementControlNode(operation, block, (ControlNode) node);
		}else if(node instanceof Action){
			if(node instanceof ExpansionRegion){
				implementExpansionRegion(operation, block, (ExpansionRegion) node);
			}else if(node instanceof StructuredActivityNode){
				implementStructuredActivityNode(operation, block, (StructuredActivityNode) node);
			}else if(node != null){
				implementObjectNodeOrAtomicAction(operation, block, node);
			}
		}else{
			// OBject node
			implementObjectNodeOrAtomicAction(operation, block, node);
		}
	}
	private void implementStructuredActivityNode(OJAnnotatedOperation operation,OJBlock block,StructuredActivityNode node){
		OJBlock nodeBlock = new OJBlock();
		block.addToStatements(nodeBlock);
		implementNode(operation, nodeBlock, getFirstNode(EmfActivityUtil.getStartNodes(node)));
		maybeImplementNextNode(operation, block, node);
	}
	private void implementObjectNodeOrAtomicAction(OJAnnotatedOperation operation,OJBlock block,ActivityNode node){
		SimpleNodeBuilder<?> builder = resolveBuilder(node, getLibrary(), new ObjectNodeExpressor(ojUtil));
		if(builder != null){
			builder.implementActionOn(operation, block);
			if(builder instanceof AbstractCaller){
				block = surroundWithCatchIfRequired((CallAction) node, (AbstractCaller<?>) builder, operation, block);
			}
		}
		if(!(node instanceof ExpansionNode && ((ExpansionNode) node).getRegionAsOutput() != null)){
			maybeImplementNextNode(operation, block, node);
		}
	}
	private void implementExpansionRegion(OJAnnotatedOperation operation,OJBlock block,ExpansionRegion region){
		ExpansionNode input = region.getInputElements().get(0);
		PropertyMap map = ojUtil.buildStructuralFeatureMap(input);
		List<ExpansionNode> output = region.getOutputElements();
		for(ExpansionNode expansionNode:output){
			PropertyMap outMap = ojUtil.buildStructuralFeatureMap(expansionNode);
			OJAnnotatedField outField = new OJAnnotatedField(outMap.fieldname(), outMap.javaTypePath());
			outField.setInitExp(outMap.javaDefaultValue());
			operation.getOwner().addToImports(outMap.javaBaseDefaultTypePath());
			operation.getOwner().addToImports(outMap.javaTypePath());
			block.addToLocals(outField);
		}
		ObjectNodeExpressor expressor = new ObjectNodeExpressor(ojUtil);
		OJForStatement forEach = new OJForStatement(map.fieldname(), map.javaBaseTypePath(),
				expressor.expressInputPinOrOutParamOrExpansionNode(block, input));
		block.addToStatements(forEach);
		addVariables(EmfActivityUtil.getContainingActivity(region), region.getVariables(), forEach.getBody(), operation.getOwner());
		// TODO get first node, likely an inputElement to implement
		Set<ActivityEdge> defaultOutgoing = EmfActivityUtil.getDefaultOutgoing(input);
		if(defaultOutgoing.size() == 1){
			implementNode(operation, forEach.getBody(), EmfActivityUtil.getEffectiveTarget(defaultOutgoing.iterator().next()));
		}else{
			implementNode(operation, forEach.getBody(), getFirstNode(EmfActivityUtil.getStartNodes(region)));
		}
		maybeImplementNextNode(operation, block, region);
	}
	private void implementControlNode(OJAnnotatedOperation operation,OJBlock block,ControlNode cn){
		if(cn instanceof InitialNode || cn instanceof MergeNode){
			// TODO Does not seem like this would work. We need to pop the block
			// stack
			maybeImplementNextNode(operation, block, cn);
		}else if(cn instanceof ActivityFinalNode){
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
		}else if(cn instanceof DecisionNode){
			// implementNode(oper, block,
			// first.getDefaultOutgoing().iterator().next().getEffectiveTarget());
			OJBlock elseBlock = block;
			ActivityEdge incomingEdge = cn.getIncomings().iterator().next();
			if(incomingEdge instanceof ObjectFlow){
				// TODO the originatingOBjectNode my not have the correct type after transformations and selections
				PropertyMap map = ojUtil.buildStructuralFeatureMap(EmfActivityUtil.getOriginatingObjectNode((ObjectFlow) incomingEdge));
				if(block.findLocal(map.fieldname()) == null && operation.findParameter(map.fieldname()) == null){
					// TODO check for more scopes
					OJAnnotatedField decisionNodeVar = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
					ObjectNodeExpressor expressor = new ObjectNodeExpressor(ojUtil);
					decisionNodeVar.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(block, (ObjectFlow) incomingEdge));
					elseBlock.addToLocals(decisionNodeVar);
				}
			}
			OJIfStatement ifStatement = null;
			for(ActivityEdge edge:EmfActivityUtil.getConditionalOutgoing(cn)){
				ifStatement = new OJIfStatement();
				elseBlock.addToStatements(ifStatement);
				if(edge.getGuard() instanceof OpaqueExpression){
					OpaqueExpressionContext oclExpressionContext = getLibrary().getOclExpressionContext((OpaqueExpression) edge.getGuard());
					ifStatement.setCondition(valueSpecificationUtil.expressOcl(oclExpressionContext, operation, getLibrary().getBooleanType()));
				}
				implementNode(operation, ifStatement.getThenPart(), EmfActivityUtil.getEffectiveTarget(edge));
				ifStatement.setElsePart(new OJBlock());
				elseBlock = ifStatement.getElsePart();
			}
			if(EmfActivityUtil.getDefaultOutgoing(cn).isEmpty()){
				if(ifStatement != null){
					ifStatement.setElsePart(null);
				}
			}else{
				maybeImplementNextNode(operation, elseBlock, cn);
			}
		}
	}
	public static SimpleNodeBuilder<?> resolveBuilder(ActivityNode node,IPropertyEmulation lib,AbstractObjectNodeExpressor expressor){
		SimpleNodeBuilder<?> actionBuilder = null;
		if(node instanceof ActivityParameterNode){
			actionBuilder = new ParameterNodeImplementor((ActivityParameterNode) node, expressor);
		}else if(node instanceof ExpansionNode){
			actionBuilder = new ExpansionNodeImplementor((ExpansionNode) node, expressor);
		}else if(node instanceof AddStructuralFeatureValueAction){
			actionBuilder = new StructuralFeatureValueAdder((AddStructuralFeatureValueAction) node, expressor);
		}else if(node instanceof RemoveStructuralFeatureValueAction){
			actionBuilder = new StructuralFeatureValueRemover((RemoveStructuralFeatureValueAction) node, expressor);
		}else if(node instanceof ClearStructuralFeatureAction){
			actionBuilder = new StructuralFeatureClearer((ClearStructuralFeatureAction) node, expressor);
		}else if(node instanceof ReadStructuralFeatureAction){
			actionBuilder = new StructuralFeatureReader((ReadStructuralFeatureAction) node, expressor);
		}else if(node instanceof AddVariableValueAction){
			actionBuilder = new VariableValueAdder((AddVariableValueAction) node, expressor);
		}else if(node instanceof RemoveVariableValueAction){
			actionBuilder = new VariableValueRemover((RemoveVariableValueAction) node, expressor);
		}else if(node instanceof ClearVariableAction){
			actionBuilder = new VariableClearer((ClearVariableAction) node, expressor);
		}else if(node instanceof ReadVariableAction){
			actionBuilder = new VariableReader((ReadVariableAction) node, expressor);
		}else if(node instanceof OpaqueAction){
			actionBuilder = new OclActionCaller((OpaqueAction) node, expressor);
		}else if(EmfActionUtil.isSingleScreenTask(node)){
			actionBuilder = new EmbeddedSingleScreenTaskCaller((OpaqueAction) node, expressor);
		}else if(node instanceof CallOperationAction){
			actionBuilder = new OperationCaller((CallOperationAction) node, expressor);
		}else if(EmfActionUtil.isScreenFlowTask(node)){
			actionBuilder = new EmbeddedScreenFlowTaskCaller((CallBehaviorAction) node, expressor);
		}else if(node instanceof CallBehaviorAction){
			actionBuilder = new BehaviorCaller((CallBehaviorAction) node, expressor);
		}else if(node instanceof CreateObjectAction){
			actionBuilder = new ObjectCreator((CreateObjectAction) node, expressor);
		}else if(node instanceof StartClassifierBehaviorAction){
			actionBuilder = new ClassifierBehaviorStarter((StartClassifierBehaviorAction) node, expressor);
		}else if(node instanceof SendSignalAction){
			actionBuilder = new SignalSender((SendSignalAction) node, expressor);
		}else if(node instanceof RaiseExceptionAction){
			actionBuilder = new ExceptionRaiser((RaiseExceptionAction) node, expressor);
		}
		return actionBuilder;
	}
	private void maybeImplementNextNode(OJAnnotatedOperation operation,OJBlock block,ActivityNode pn){
		Set<ActivityEdge> defaultOutgoing = EmfActivityUtil.getDefaultOutgoing(pn);
		if(defaultOutgoing.size() == 1){
			OJBlock nodeBlock = new OJBlock();
			block.addToStatements(nodeBlock);
			ActivityEdge out = defaultOutgoing.iterator().next();
			if(out instanceof ObjectFlow){
				ObjectFlow of = (ObjectFlow) out;
				if(of.getSelection() instanceof OpaqueBehavior){
					Behavior opaqueBehavior = of.getSelection();
					OperationMap omap = ojUtil.buildOperationMap(opaqueBehavior);
					OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext((OpaqueBehavior) opaqueBehavior);
					if(!oclBehaviorContext.hasErrors()){
						ClassifierMap cMap = ojUtil.buildClassifierMap(oclBehaviorContext.getExpression().getType());
						OJAnnotatedOperation selection = new OJAnnotatedOperation(omap.javaOperName(), cMap.javaTypePath());
						operation.getOwner().addToOperations(selection);
						Parameter in = omap.getArgumentParameters().get(0);
						selection.addParam(in.getName(), omap.javaParamTypePath(in));
						selection.initializeResultVariable(valueSpecificationUtil.expressOcl(oclBehaviorContext, selection, oclBehaviorContext
								.getExpression().getType()));
					}
				}
				if(of.getTransformation() != null){
					if(of.getTransformation() instanceof OpaqueBehavior){
						Behavior opaqueBehavior = of.getTransformation();
						OperationMap omap = ojUtil.buildOperationMap(opaqueBehavior);
						OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext((OpaqueBehavior) opaqueBehavior);
						if(!oclBehaviorContext.hasErrors()){
							ClassifierMap cMap = ojUtil.buildClassifierMap(oclBehaviorContext.getExpression().getType());
							OJAnnotatedOperation selection = new OJAnnotatedOperation(omap.javaOperName(), cMap.javaTypePath());
							operation.getOwner().addToOperations(selection);
							Parameter in = omap.getArgumentParameters().get(0);
							selection.addParam(in.getName(), omap.javaParamTypePath(in));
							selection.initializeResultVariable(valueSpecificationUtil.expressOcl(oclBehaviorContext, selection, oclBehaviorContext
									.getExpression().getType()));
						}
					}
					generateTransformationMultiplier(operation.getOwner(), of, ojUtil);
				}
			}
			implementNode(operation, nodeBlock, EmfActivityUtil.getEffectiveTarget(out));
		}
	}
	public static void generateTransformationMultiplier(OJClassifier owner,ObjectFlow of,OJUtil ojUtil2){
		ObjectNode originatingObjectNode = EmfActivityUtil.getOriginatingObjectNode(of);
		if(EmfActivityUtil.isMultivalued(originatingObjectNode) || of.getSelection() != null
				&& EmfBehaviorUtil.getReturnParameter(of.getSelection()).isMultivalued()){
			TypedElement arg = null;
			if(of.getSelection() != null){
				arg = EmfBehaviorUtil.getReturnParameter(of.getSelection());
			}else{
				arg = originatingObjectNode;
			}
			if(arg instanceof MultiplicityElement && ((MultiplicityElement) arg).isMultivalued()){
				PropertyMap targetMap = ojUtil2.buildStructuralFeatureMap((ObjectNode) of.getTarget());
				OJPathName resultTypePath = targetMap.javaTypePath();
				if(of.getTarget() instanceof ExpansionNode){
					resultTypePath = new OJPathName("java.util.Collection");
					resultTypePath.addToElementTypes(targetMap.javaBaseTypePath());
				}
				String transformOperName = ojUtil2.buildOperationMap(of.getTransformation()).javaOperName();
				OJAnnotatedOperation transformMany = new OJAnnotatedOperation(transformOperName, resultTypePath);
				owner.addToOperations(transformMany);
				PropertyMap argMap = ojUtil2.buildStructuralFeatureMap(arg);
				transformMany.addParam(argMap.fieldname(), argMap.javaTypePath());
				if(of.getTarget() instanceof ExpansionNode){
					transformMany.initializeResultVariable("new ArrayList<" + targetMap.javaType() + ">()");
				}else{
					transformMany.initializeResultVariable(targetMap.javaDefaultValue());
				}
				OJForStatement foreach = new OJForStatement("tmp", argMap.javaBaseTypePath(), argMap.fieldname());
				transformMany.getBody().addToStatements(foreach);
				if(targetMap.isOne() && !(of.getTarget() instanceof ExpansionNode)){
					foreach.getBody().addToStatements("return " + transformOperName + "(tmp)");
				}else if(EmfBehaviorUtil.getReturnParameter(of.getTransformation()).getUpper() == 1){
					foreach.getBody().addToStatements("result.add(" + transformOperName + "(tmp))");
				}else{
					foreach.getBody().addToStatements("result.addAll(" + transformOperName + "(tmp))");
				}
			}
		}
	}
	private static ActivityNode getFirstNode(Collection<ActivityNode> startNodes){
		if(startNodes.isEmpty()){
			return null;
		}else{
			// IGnore unconnected parameter nodes
			for(ActivityNode node:startNodes){
				if(!(EmfActivityUtil.getAllEffectiveOutgoing(node).isEmpty() && node instanceof ActivityParameterNode)){
					return node;
				}
			}
			return null;
		}
	}
	private OJBlock surroundWithCatchIfRequired(CallAction nakedCall,AbstractCaller<?> caller,OJAnnotatedOperation operation,
			OJBlock originalBlock){
		if(EmfBehaviorUtil.shouldSurrounWithTry(nakedCall)){
			OJTryStatement tryStatement = caller.surroundWithCatchIfNecessary(operation, originalBlock);
			for(OutputPin e:EmfActionUtil.getExceptionPins(nakedCall)){
				PropertyMap pinMap = ojUtil.buildStructuralFeatureMap(e);
				OJPathName pathName = ojUtil.classifierPathname((Classifier) e.getType());
				operation.getOwner().addToImports(pathName);
				OJIfStatement statement = new OJIfStatement();
				statement.setCondition("e.isParameter(\"" + EmfActionUtil.getLinkedTypedElement(e).getName() + "\")");
				OJField parm = new OJAnnotatedField(pinMap.fieldname(), pinMap.javaTypePath());
				parm.setInitExp("(" + pinMap.javaType() + ")e.getValue()");
				statement.getThenPart().addToLocals(parm);
				tryStatement.getCatchPart().addToStatements(statement);
				if(e.getOutgoings().size() > 0){
					ActivityEdge outgoing = e.getOutgoings().iterator().next();
					implementNode(operation, statement.getThenPart(), EmfActivityUtil.getEffectiveTarget(outgoing));
				}
				// break flow on exception
				statement.getThenPart().addToStatements("return");
				tryStatement.getCatchPart().addToStatements(statement);
			}
			for(ExceptionHandler e:nakedCall.getHandlers()){
				StringBuilder sb = new StringBuilder();
				Iterator<Classifier> iter = e.getExceptionTypes().iterator();
				while(iter.hasNext()){
					Classifier type = iter.next();
					OJPathName pathName = ojUtil.classifierPathname(type);
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
	public void setWorkspace(EmfWorkspace workspace){
		this.workspace = workspace;
	}
}
