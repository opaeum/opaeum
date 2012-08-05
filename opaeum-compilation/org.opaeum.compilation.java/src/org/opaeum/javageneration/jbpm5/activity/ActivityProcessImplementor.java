package org.opaeum.javageneration.jbpm5.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.AcceptEventActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.CallBehaviorActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.CallOperationActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.EmbeddedScreenFlowTaskBuilder;
import org.opaeum.javageneration.jbpm5.actions.EmbeddedSingleScreenTaskBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.actions.ParameterNodeBuilder;
import org.opaeum.javageneration.jbpm5.actions.ReplyActionBuilder;
import org.opaeum.javageneration.jbpm5.actions.SimpleActionBridge;
import org.opaeum.javageneration.maps.StructuredActivityNodeMap;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.runtime.domain.ExceptionHolder;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,CodeCleanup.class},after = {
		OperationAnnotator.class,SimpleActivityMethodImplementor.class /* Needs repeatable sequence in the ocl generating steps */
},before = CodeCleanup.class)
public class ActivityProcessImplementor extends AbstractJavaProcessVisitor{
	private void setupVariables(OJAnnotatedOperation oper,ActivityNode node){
		if(node instanceof StructuredActivityNode){
			Collection<Variable> variables = ((StructuredActivityNode) node).getVariables();
			for(Variable var:variables){
				StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(var);
				OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
				field.setInitExp("(" + map.javaType() + ")context.getVariable(\"" + map.fieldname() + "\")");
				oper.getOwner().addToImports(map.javaTypePath());
				oper.getBody().addToLocals(field);
			}
		}
		if(node.getOwner() instanceof ActivityNode){
			setupVariables(oper, (ActivityNode) node.getOwner());
		}
	}
	private void activityEdge(ActivityEdge edge){
		OJAnnotatedClass c = findJavaClass(EmfActivityUtil.getContainingActivity(edge));
		if(edge instanceof ObjectFlow && ((ObjectFlow) edge).getTransformation() != null){
			SimpleActivityMethodImplementor.generateTransformationMultiplier(c, ((ObjectFlow) edge));
		}
		if(EmfActivityUtil.hasGuard(edge) && EmfBehaviorUtil.hasExecutionInstance(EmfActivityUtil.getContainingActivity(edge))){
			ActivityNode node = EmfActivityUtil.getEffectiveSource(edge);
			OJAnnotatedOperation oper = new OJAnnotatedOperation(Jbpm5Util.getGuardMethod(node, edge));
			c.addToOperations(oper);
			oper.setReturnType(new OJPathName("boolean"));
			setupVariables(oper, node);
			ActivityNode source = EmfActivityUtil.getEffectiveSource(edge);
			if(edge instanceof ObjectFlow){
				addObjectFlowVariable(edge, oper, (ObjectFlow) edge);
			}else if(source instanceof DecisionNode){
				// NB!! we are doing it here for both controlflows and
				// objectflows which is not entirely to uml spec but what the
				// heck,
				// looks like a good idea
				if(source.getIncomings().size() == 1 && source.getIncomings().iterator().next() instanceof ObjectFlow){
					ObjectFlow objectFlow = (ObjectFlow) source.getIncomings().iterator().next();
					addObjectFlowVariable(edge, oper, objectFlow);
				}
			}
			Classifier booleanType = getLibrary().getBooleanType();
			oper.getBody().addToStatements(
					"return " + valueSpecificationUtil.expressValue(oper, edge.getGuard(), EmfActivityUtil.getContainingActivity(edge), booleanType));
			oper.addParam("context", Jbpm5Util.getProcessContext());
		}
	}
	private void addObjectFlowVariable(ActivityEdge edge,OJAnnotatedOperation oper,ObjectFlow objectFlow){
		ObjectNode origin = EmfActivityUtil.getOriginatingObjectNode( objectFlow);
		// TODO the originatingObjectNode may not have the correct type after
		// transformations and selections
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(origin);
		OJAnnotatedField sourceField = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
		oper.getBody().addToLocals(sourceField);
		AbstractObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(ojUtil);
		sourceField.setInitExp(expressor.expressFeedingNodeForObjectFlowGuard(oper.getBody(), objectFlow));
	}
	@VisitBefore(matchSubclasses = true)
	public void implementActivity(Activity activity){
		if(!EmfActivityUtil.isSimpleSynchronousMethod(activity)){
			OJAnnotatedClass activityClasss = findJavaClass(activity);
			addParameterDelegation(activityClasss, activity);
			OJPathName stateClass = ojUtil.statePathname(activity);
			OJAnnotatedOperation getSelf = new OJAnnotatedOperation("getSelf", activityClasss.getPathName());
			getSelf.initializeResultVariable("this");
			activityClasss.addToOperations(getSelf);
			implementContainer(EmfBehaviorUtil.isProcess(activity), stateClass, activity, activity);
		}
	}
	private void implementContainer(boolean isProcess,OJPathName stateClass,Namespace container,Classifier msg){
		OJAnnotatedClass activityClass = findJavaClass(msg);
		implementNodeMethods(container);
		doExecute(msg, activityClass, isProcess);
		if(isProcess){
			implementProcessInterfaceOperations(activityClass, stateClass, msg);
			OJOperation init = activityClass.findOperation("init", Arrays.asList(Jbpm5Util.getProcessContext()));
			eventUtil.requestEvents((OJAnnotatedOperation) init, EmfActivityUtil.getActivityNodes(container),
					getLibrary().getBusinessRole() != null);
		}else{
			Jbpm5Util.implementRelationshipWithProcess(activityClass, false, "process");
			doIsStepActive(activityClass, msg);
			super.addGetNodeInstancesRecursively(activityClass);
			doIsComplete(activityClass, msg);
			doFindNodeInstanceByUniqueId(activityClass);
			addDirtyLogic(activityClass);
		}
		OJUtil.addTransientProperty(activityClass, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"), true).setVisibility(
				OJVisibilityKind.PROTECTED);
		OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
		activityClass.addToOperations(complete);
		if(EmfActivityUtil.getPostconditions(container).size() > 0){
			complete.getBody().addToStatements("evaluatePostConditions()");
			OJUtil.addFailedConstraints(complete);
		}
		for(ActivityNode n:EmfActivityUtil.getActivityNodes(container)){
			if(n instanceof StructuredActivityNode){
				StructuredActivityNode san = (StructuredActivityNode) n;
				Classifier childMsg = getLibrary().getMessageStructure(san);
				OJAnnotatedClass c = findJavaClass(childMsg);
				OJAnnotatedOperation getter = (OJAnnotatedOperation) c.getUniqueOperation("getSelf");
				if(container instanceof Activity){
					getter.initializeResultVariable("getNodeContainer()");
				}else{
					getter.initializeResultVariable("getNodeContainer().getSelf()");
				}
				OJAnnotatedOperation getActivity = new OJAnnotatedOperation("getContainingActivity", ojUtil.classifierPathname(EmfActivityUtil
						.getContainingActivity(container)));
				c.addToOperations(getActivity);
				if(container instanceof Activity){
					getActivity.initializeResultVariable("getNodeContainer()");
				}else{
					getActivity.initializeResultVariable("getNodeContainer().getContainingActivity()");
				}
				if(EmfActivityUtil.getContainingActivity(container).getContext() != null){
					OJAnnotatedOperation contextGetter = (OJAnnotatedOperation) c.getUniqueOperation("getContextObject");
					contextGetter.initializeResultVariable("getSelf().getContextObject()");
				}
				implementVariableDelegation(container, msg, c);
				StructuredActivityNodeMap map = ojUtil.buildStructuredActivityNodeMap(san);
				OJUtil.addPersistentProperty(c, "callingNodeInstanceUniqueId", new OJPathName("String"), true);
				implementContainer(isProcess, stateClass, san, childMsg);
				if(isProcess){
					propagateExceptions(map, c);
					OJOperation completed = c.getUniqueOperation("completed");
					completed.getBody().addToStatements("getNodeContainer()." + map.completeMethodName() + "(getCallingNodeInstanceUniqueId(),this)");
				}
			}
		}
	}
	private void propagateExceptions(StructuredActivityNodeMap map,OJAnnotatedClass ojOperationClass){
		OJAnnotatedOperation propagateException = new OJAnnotatedOperation("propagateException");
		ojOperationClass.addToOperations(propagateException);
		propagateException.addParam("exception", new OJPathName("Object"));
		propagateException.getBody().addToStatements(
				"getNodeContainer()." + map.unhandledExceptionOperName() + "(getCallingNodeInstanceUniqueId(),exception, this)");
	}
	public void implementVariableDelegation(Namespace container,Classifier msg,OJAnnotatedClass c){
		// NB!!! remember this is only for OCL, not for actions. We only implement getters
		for(Variable var:EmfActivityUtil.getVariables(container)){
			StructuralFeatureMap varMap = ojUtil.buildStructuralFeatureMap(var);
			OJAnnotatedOperation delegate = new OJAnnotatedOperation(varMap.getter(), varMap.javaTypePath());
			c.addToOperations(delegate);
			delegate.initializeResultVariable("getNodeContainer()." + varMap.getter() + "()");
		}
		if(container instanceof Activity){
			for(Parameter var:((Activity) container).getOwnedParameters()){
				StructuralFeatureMap varMap = ojUtil.buildStructuralFeatureMap(var);
				OJAnnotatedOperation delegate = new OJAnnotatedOperation(varMap.getter(), varMap.javaTypePath());
				c.addToOperations(delegate);
				delegate.initializeResultVariable("getNodeContainer()." + varMap.getter() + "()");
			}
		}else if(container.getOwner() instanceof Activity || container.getOwner() instanceof StructuredActivityNode){
			implementVariableDelegation((Namespace) container.getOwner(), msg, c);
		}
	}
	private void doExecute(Classifier activity,OJAnnotatedClass activityClass,boolean isProcess){
		OJOperation execute = implementExecute(activityClass, activity);
		if(isProcess){
			execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		}else if(activity instanceof Activity){
			activityClass.addToImports(new OJPathName(ExceptionHolder.class.getName()));
			Activity a = (Activity) activity;
			for(Parameter p:a.getOwnedParameters()){
				if(p.isException()){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
					execute.getBody().addToStatements(
							new OJIfStatement("this." + map.getter() + "()!=null", "throw new ExceptionHolder(this,\"" + p.getName() + "\","
									+ map.getter() + "())"));
				}
			}
			execute.getBody().addToStatements(
					new OJIfStatement("this." + Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + "!=null", "throw new ExceptionHolder(this,\"_raised\","
							+ Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD + ")"));
		}
	}
	private void implementNodeMethods(Namespace activity){
		OJAnnotatedClass activityClass;
		if(activity instanceof StructuredActivityNode){
			activityClass = findJavaClass(getLibrary().getMessageStructure(((StructuredActivityNode) activity)));
		}else{
			activityClass = findJavaClass((Activity) activity);
		}
		activityClass.addToImports(Jbpm5Util.getProcessContext());
		for(ActivityNode node:EmfActivityUtil.getActivityNodes(activity)){
			if(node instanceof Action || node instanceof ActivityParameterNode || node instanceof ControlNode || node instanceof ExpansionRegion
					|| node instanceof ExpansionNode){
				this.implementNodeMethod(activityClass, node);
				if(node instanceof StructuredActivityNode){
					visitEdges(((StructuredActivityNode) node).getContainedEdges());
				}
				if(node instanceof Action && EmfActionUtil.hasMessageStructure((Action) node)){
					for(OutputPin op:((Action) node).getOutputs()){
						if(!(node instanceof AcceptCallAction && ((AcceptCallAction) node).getReturnInformation() == op)){
							implementDerivedGetter(activityClass, op);
						}
					}
				}
				if(node instanceof ExpansionRegion){
					ExpansionRegion region = (ExpansionRegion) node;
					Classifier msg = getLibrary().getMessageStructure(region);
					OJAnnotatedClass msgClass = findJavaClass(msg);
					for(ExpansionNode ip:region.getOutputElements()){
						StructuralFeatureMap propertyMap = ojUtil.buildStructuralFeatureMap(ip);
						StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(ip);
						OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
						msgClass.addToOperations(getter);
						getter.initializeResultVariable("getNodeContainer()." + propertyMap.getter() + "()");
					}
				}
			}
		}
		visitEdges(EmfActivityUtil.getEdges(activity));
	}
	private void implementDerivedGetter(OJAnnotatedClass activityClass,ObjectNode node2){
		StructuralFeatureMap actionMap = ojUtil.buildStructuralFeatureMap((Action) EmfElementFinder.getContainer(node2));
		Namespace container = EmfActivityUtil.getNearestNodeContainer(node2);
		Classifier parentMsg = getLibrary().getMessageStructure(container);
		StructuralFeatureMap pinMap = ojUtil.buildStructuralFeatureMap( node2);
		StructuralFeatureMap propertyMap = ojUtil.buildStructuralFeatureMap( node2);
		List<OJPathName> emptyList = Collections.emptyList();
		OJAnnotatedOperation oper = (OJAnnotatedOperation) activityClass.findOperation(pinMap.getter(), emptyList);
		oper.setBody(new OJBlock());
		if(actionMap.isMany()){
			if(pinMap.isMany()){
				oper.initializeResultVariable(pinMap.javaDefaultValue());
				OJForStatement forEach = new OJForStatement("tmp", actionMap.javaBaseTypePath(), actionMap.getter() + "()");
				oper.getBody().addToStatements(forEach);
				if(propertyMap.isMany()){
					forEach.getBody().addToStatements("result.addAll(tmp." + propertyMap.getter() + "())");
				}else{
					forEach.getBody().addToStatements("result.add(tmp." + propertyMap.getter() + "())");
				}
			}else{
				if(propertyMap.isMany()){
					oper.initializeResultVariable(actionMap.getter() + "().iterator().next()." + propertyMap.getter() + "().iterator().next()");
				}else{
					oper.initializeResultVariable(actionMap.getter() + "().iterator().next()." + propertyMap.getter() + "()");
				}
			}
		}else{
			if(pinMap.isMany()){
				oper.initializeResultVariable(pinMap.javaDefaultValue());
				if(propertyMap.isMany()){
					oper.getBody().addToStatements("result.addAll(" + actionMap.getter() + "()." + propertyMap.getter() + "())");
				}else{
					oper.getBody().addToStatements("result.add(" + actionMap.getter() + "()." + propertyMap.getter() + "())");
				}
			}else{
				if(propertyMap.isMany()){
					oper.initializeResultVariable(actionMap.getter() + "()." + propertyMap.getter() + "().iterator().next()");
				}else{
					oper.initializeResultVariable(actionMap.getter() + "()." + propertyMap.getter() + "()");
				}
			}
		}
	}
	protected void visitEdges(Collection<ActivityEdge> activityEdges){
		for(ActivityEdge edge:activityEdges){
			activityEdge(edge);
		}
	}
	private void implementNodeMethod(OJClass activityClass,ActivityNode node){
		Jbpm5ActionBuilder<?> implementor = null;
		if(node instanceof ExpansionRegion){
			implementor = new ExpansionRegionBuilder(ojUtil, (ExpansionRegion) node);
		}else if(EmfActionUtil.isSingleScreenTask(node)){
			implementor = new EmbeddedSingleScreenTaskBuilder(ojUtil, (OpaqueAction) node);
		}else if(EmfActionUtil.isScreenFlowTask(node)){
			implementor = new EmbeddedScreenFlowTaskBuilder(ojUtil, (CallBehaviorAction) node);
		}else if(node instanceof CallBehaviorAction){
			implementor = new CallBehaviorActionBuilder(ojUtil, (CallBehaviorAction) node);
		}else if(node instanceof CallOperationAction){
			implementor = new CallOperationActionBuilder(ojUtil, (CallOperationAction) node);
		}else if(node instanceof AcceptEventAction){
			implementor = new AcceptEventActionBuilder(ojUtil, (AcceptEventAction) node);
		}else if(node instanceof ActivityParameterNode){
			ActivityParameterNode parameterNode = (ActivityParameterNode) node;
			implementor = new ParameterNodeBuilder(ojUtil, parameterNode);
		}else if(node instanceof ReplyAction){
			ReplyAction action = (ReplyAction) node;
			implementor = new ReplyActionBuilder(ojUtil, action);
		}else{
			implementor = new SimpleActionBridge(ojUtil, node, SimpleActivityMethodImplementor.resolveBuilder(node, getLibrary(),
					new Jbpm5ObjectNodeExpressor(ojUtil)));
		}
		if(implementor.hasNodeMethod()){
			OJAnnotatedOperation operation = new OJAnnotatedOperation(implementor.getMap().doActionMethod());
			OJUtil.addMetaInfo(operation, node);
			activityClass.addToOperations(operation);
			operation.addParam("context", Jbpm5Util.getProcessContext());
			if(implementor.isEffectiveFinalNode()){
				implementor.implementFinalStep(operation.getBody());
			}
			implementor.setupArgumentPins(operation);
			implementor.implementPreConditions(operation);
			implementor.implementObersvations(operation);
			implementor.implementActionOn(operation);
			if(implementor.isLongRunning()){
				implementor.implementCallbackMethods(activityClass);
			}else if(!(implementor.waitsForEvent() || node instanceof ControlNode)){
				implementor.implementPostConditions(operation);
				// implementor.implementConditionalFlows(operation,
				// operation.getBody(), true);
			}
		}
	}
	@Override
	protected Collection<? extends NamedElement> getTopLevelFlows(Classifier umlBehavior){
		return Arrays.asList(umlBehavior);
	}
}
