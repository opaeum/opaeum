package org.opaeum.javageneration.bpm.activity;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.eclipse.ResponsibilityDefinitionImpl;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.simpleactions.ObjectNodeExpressor;
import org.opaeum.javageneration.basicjava.simpleactions.SignalSender;
import org.opaeum.javageneration.bpm.AbstractBehaviorVisitor;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.bpm.statemachine.StateMachineImplementor;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.DeadlineKind;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {ActivityProcessImplementor.class,StateMachineImplementor.class},after = {
		StateMachineImplementor.class,ActivityProcessImplementor.class})
public class TaskAndResponsibilityImplementor extends AbstractBehaviorVisitor{
	@VisitBefore
	public void visitActivity(Activity activity){
		if(activity.getSpecification() != null && EmfBehaviorUtil.isResponsibility(activity.getSpecification())
				&& EmfBehaviorUtil.hasExecutionInstance(activity)){
		}
		for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively(activity)){
			if(EmfActionUtil.isScreenFlowTask(n)){
				visitScreenFlowTask((CallBehaviorAction) n);
			}else if(EmfActionUtil.isSingleScreenTask(n)){
				visitEmbeddedSingleScreenTask((OpaqueAction) n);
			}
			if(n instanceof CallAction){
				visitCallAction((CallAction) n);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(Classifier nc){
		for(Operation o:nc.getOperations()){
			if(EmfBehaviorUtil.isResponsibility(o)){
				Classifier msg = getLibrary().getMessageStructure(o);
				OJAnnotatedClass ojClass = findJavaClass(msg);
				/**
				 * If contextObject instanceof BusinessRole then create task, assign to user,kick off events IF contextObject instanceof
				 * BusinessGateway on a Business Component then see if there is an implementing process contained by the Business Component If there
				 * is,create it and execute it. Then check if there is a BusinessProcess with that oper as AcceptCall action and resolve it using
				 * correlations Else if there is a delegation to a property of type BusinessRole, create assignees for each business role if the
				 * port has other assignement expressions assign these too
				 */
				ResponsibilityDefinition rd = getLibrary().getResponsibilityDefinition(o, StereotypeNames.RESPONSIBILITY);
				ojClass.addToImports(ojUtil.classifierPathname(getLibrary().getTaskRequest()));
				OperationMap map = ojUtil.buildOperationMap(o);
				implementStandaloneTaskOrResponsibility(rd, ojClass, map, o.getRedefinedOperations().size() > 0);
				implementIObserver(ojClass , EmfTimeUtil.getTimeObservations(nc), EmfTimeUtil.getDurationObservations(nc));

			}
		}
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior ob){
		if(EmfBehaviorUtil.isStandaloneTask(ob)){
			ResponsibilityDefinitionImpl rd = new ResponsibilityDefinitionImpl(getLibrary(), ob, StereotypesHelper.getStereotype(ob,
					StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK));
			implementStandaloneTask(ob, rd);
		}
	}
	@VisitBefore
	public void visitStateMachine(StateMachine sm){
		if(EmfBehaviorUtil.isStandaloneTask(sm)){
			ResponsibilityDefinitionImpl rd = new ResponsibilityDefinitionImpl(getLibrary(), sm, StereotypesHelper.getStereotype(sm,
					StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK));
			implementStandaloneTask(sm, rd);
		}
	}
	private void visitScreenFlowTask(CallBehaviorAction a){
		StateMachine sm = (StateMachine) a.getBehavior();
		ClassifierMap map = ojUtil.buildClassifierMap(sm, (CollectionKind) null);
		Activity activity = EmfActivityUtil.getContainingActivity(a);
		if(ojUtil.requiresJavaRename(a)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(a));
		}
		OJAnnotatedClass ojClass = new OJAnnotatedClass(NameConverter.capitalize(a.getName()));
		OJPackage pkg = findOrCreatePackage(ojUtil.packagePathname(activity));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		JpaUtil.addClass(ojClass);
		JpaUtil.buildTableAnnotation(ojClass, PersistentNameUtil.getPersistentName(a).getAsIs(), super.config, activity);
		for(Parameter p:sm.getOwnedParameters()){
			PropertyMap pMap = ojUtil.buildStructuralFeatureMap(p);
			OJAnnotatedOperation setter = new OJAnnotatedOperation(pMap.setter());
			setter.addParam(pMap.fieldname(), pMap.javaTypePath());
			ojClass.addToOperations(setter);
			setter.getBody().addToStatements(
					"get" + NameConverter.capitalize(sm.getName()) + "()." + pMap.setter() + "(" + pMap.fieldname() + ")");
			OJAnnotatedOperation getter = new OJAnnotatedOperation(pMap.getter(), map.javaTypePath());
			getter.getBody().addToStatements("return get" + NameConverter.capitalize(sm.getName()) + "()." + pMap.getter() + "()");
			ojClass.addToOperations(getter);
		}
		ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(a, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		implementEmbeddedTask(a, ojClass, taskDefinition);
	}
	private void visitEmbeddedSingleScreenTask(OpaqueAction oa){
		OJAnnotatedClass ojClass = findJavaClass(getLibrary().getMessageStructure(oa));
		ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(oa, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		implementEmbeddedTask(oa, ojClass, taskDefinition);
	}
	private void implementEmbeddedTask(Action oa,OJAnnotatedClass ojClass,ResponsibilityDefinition rd){
		OJAnnotatedOperation exec = implementExecute(ojClass, true, EmfBehaviorUtil.hasSuperClass(oa));
		exec.getBody().addToStatements("getTaskRequest().execute()");
		String callbackMethodName = "on" + NameConverter.capitalize(oa.getName()) + "Completed";
		super.addReturnInfo(ojClass);
		OJAnnotatedOperation completed = implementOnCompletedAndOnStarted(rd, ojClass,
				ojUtil.classifierPathname(EmfActivityUtil.getContainingActivity(oa)), "getProcessObject()", EmfBehaviorUtil.hasSuperClass(oa));
		completed.getBody().addToStatements("getProcessObject()." + callbackMethodName + "(getReturnInfo(), this)");
		implementIObserver(ojClass , EmfTimeUtil.getTimeObservations(oa), EmfTimeUtil.getDurationObservations(oa));

		// OJAnnotatedOperation isComplete = new OJAnnotatedOperation("isComplete", new OJPathName("boolean"));
		// ojClass.addToOperations(isComplete);
		// isComplete.initializeResultVariable("getTaskRequest().getCompleted()");
	}
	private void implementStandaloneTask(Behavior ob,ResponsibilityDefinition rd){
		OJAnnotatedClass ojClass = findJavaClass(ob);
		OperationMap map = ojUtil.buildOperationMap(ob);
		implementStandaloneTaskOrResponsibility(rd, ojClass, map, EmfBehaviorUtil.hasSuperClass(ob));
	}
	protected void implementStandaloneTaskOrResponsibility(ResponsibilityDefinition rd,OJAnnotatedClass ojClass,OperationMap map,
			boolean hasSuperClass){
		OJAnnotatedOperation completed = implementOnCompletedAndOnStarted(rd, ojClass, map.callbackListenerPath(),
				"getCallingBehaviorExecution()", hasSuperClass);
		OJAnnotatedOperation exec = implementExecute(ojClass, true, hasSuperClass);
		exec.getBody().addToStatements("getRequest().execute()");
		if(!hasSuperClass){
			super.addReturnInfo(ojClass);
		}
		// THere must be a field called "token" available
		OJAnnotatedField token = new OJAnnotatedField("token", BpmUtil.ITOKEN);
		token.setInitExp("getReturnInfo()");
		exec.getBody().addToLocals(token);
		taskUtil.implementAssignmentsAndEventGeneration(exec, exec.getBody(), rd, "this");
		EventUtil.addOutgoingEventManagement(ojClass);
		ojClass.addToImports(new OJPathName("java.util.Date"));
		if(!hasSuperClass){
			completed.getBody().addToStatements("getCallingBehaviorExecution()." + map.callbackOperName() + "(getReturnInfo(), this)");
		}
	}
	protected OJAnnotatedOperation implementOnCompletedAndOnStarted(ResponsibilityDefinition oa,OJAnnotatedClass ojClass,
			OJPathName processObject,String callingObjectExpression,boolean hasSuperClass){
		OJAnnotatedOperation completed = new OJAnnotatedOperation("onCompleted");
		completed.addParam("participant", ojUtil.classifierPathname(getLibrary().getParticipant()));
		ojClass.addToOperations(completed);
		Collection<TimeEvent> deadlines = oa.getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("onStarted");
		ojClass.addToOperations(started);
		started.addParam("participant", ojUtil.classifierPathname(getLibrary().getParticipant()));
		EventUtil.addOutgoingEventManagement(ojClass);
		ojClass.addToImports(new OJPathName("java.util.Date"));
		for(TimeEvent d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementTimeEventCallback(ojClass, d, oa, processObject, callingObjectExpression);
			if(EmfEventUtil.getDeadlineKind(d) == DeadlineKind.COMPLETE){
				EventUtil.cancelTimer(completed.getBody(), d, "this");
			}else{
				EventUtil.cancelTimer(started.getBody(), d, "this");
			}
		}
		if(hasSuperClass){
			completed.getBody().addToStatements("super.onCompleted(participant)");
			started.getBody().addToStatements("super.onStarted(participant)");
		}
		return completed;
	}
	private void implementTimeEventCallback(OJAnnotatedClass ojClass,TimeEvent d,ResponsibilityDefinition a,OJPathName processObject,
			String callingObjectExpression){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(eventUtil.getEventConsumerName(d), new OJPathName("boolean"));
		ojClass.addToOperations(oper);
		oper.addParam("callingToken", BpmUtil.ITOKEN);
		oper.addParam("date", new OJPathName("java.util.Date"));
		for(Constraint constraint:a.getTimeEscalations(d)){
			OJBlock block = oper.getBody();
			if(constraint.getSpecification() instanceof OpaqueExpression){
				OpaqueExpressionContext ctx = getLibrary().getOclExpressionContext((OpaqueExpression) constraint.getSpecification());
				if(!ctx.hasErrors()){
					OJIfStatement ifTrue = new OJIfStatement(valueSpecificationUtil.expressOcl(ctx, oper, getLibrary().getBooleanType()));
					oper.getBody().addToStatements(ifTrue);
					block = ifTrue.getThenPart();
				}
			}
			Collection<SendSignalAction> ssas = (Collection<SendSignalAction>) constraint.getValue(
					StereotypesHelper.getStereotype(constraint, StereotypeNames.ESCALATION), TagNames.NOTIFICATIONS);
			for(SendSignalAction ssa:ssas){
				SignalSender signalSender = new SignalSender(ssa, new ObjectNodeExpressor(ojUtil));
				signalSender.implementActionOn(oper, block);
			}
		}
		OJIfStatement ifInstance = new OJIfStatement(callingObjectExpression + " instanceof " + processObject.getLast());
		oper.getBody().addToStatements(ifInstance);
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", processObject);
		ifInstance.getThenPart().addToLocals(callingProcessObject);
		callingProcessObject.setInitExp("(" + processObject.getLast() + ")" + callingObjectExpression);
		ifInstance.getThenPart().addToStatements("return callingProcessObject." + eventUtil.getEventConsumerName(d) + "(date,this)");
		ifInstance.setElsePart(new OJBlock());
		ifInstance.getElsePart().addToStatements("return true");
	}
	private void addGetName(NamedElement c,OJAnnotatedClass ojOperationClass){
		OJOperation getName = ojOperationClass.getUniqueOperation("getName");
		if(getName == null){
			getName = new OJAnnotatedOperation("getName");
			getName.setReturnType(new OJPathName("String"));
			ojOperationClass.addToOperations(getName);
		}else{
			getName.setBody(new OJBlock());
		}
		getName.getBody().addToStatements("return \"" + c.getName() + "On\"+getContextObject().getName()");
	}
	public void visitCallAction(CallAction ca){
		// Always order invocations of contractedProcesses or tasks by the executedOn date
		if(EmfActionUtil.isLongRunning(ca)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(ca);
			if(map.isMany()){
				OJAnnotatedClass ojOwner = findJavaClass(EmfActivityUtil.getContainingActivity(ca));
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
			}
		}
	}
}