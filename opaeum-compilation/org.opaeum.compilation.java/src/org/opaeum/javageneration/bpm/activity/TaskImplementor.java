package org.opaeum.javageneration.bpm.activity;

import java.util.Collection;
import java.util.Date;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfEventUtil;
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
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.bpm.AbstractBehaviorVisitor;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.bpm.statemachine.StateMachineImplementor;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.DeadlineKind;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {ActivityProcessImplementor.class,StateMachineImplementor.class},after = {
		StateMachineImplementor.class,ActivityProcessImplementor.class})
public class TaskImplementor extends AbstractBehaviorVisitor{
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
				OJAnnotatedClass ojClass = findJavaClass(getLibrary().getMessageStructure(o));
				OJAnnotatedOperation exec = implementExecute(o, ojClass);
				/**
				 * If contextObject instanceof BusinessRole then create task, assign to user,kick off events IF contextObject instanceof
				 * BusinessGateway on a Business Component then see if there is an implementing process contained by the Business Component If there
				 * is,create it and execute it. Then check if there is a BusinessProcess with that oper as AcceptCall action and resolve it using
				 * correlations Else if there is a delegation to a property of type BusinessRole, create assignees for each business role if the
				 * port has other assignement expressions assign these too
				 */
				ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(o, StereotypeNames.RESPONSIBILITY);
				OJIfStatement ifTask = new OJIfStatement("getRequest() instanceof TaskRequest");
				exec.getBody().addToStatements(ifTask);
				ojClass.addToImports(ojUtil.classifierPathname(getLibrary().getTaskRequest()));
				OJAnnotatedField token = new OJAnnotatedField("token", BpmUtil.ITOKEN);
				token.setInitExp("getReturnInfo()");
				ifTask.getThenPart().addToLocals(token);
				taskUtil.implementAssignmentsAndDeadlines(exec, ifTask.getThenPart(), taskDefinition, "this");
				exec.getBody().addToStatements("getRequest().execute()");
				OperationMap map = ojUtil.buildOperationMap(o);
				EventUtil.addOutgoingEventManagement(ojClass);
				Collection<TimeEvent> deadlines = taskDefinition.getDeadlines();
				OJOperation completed = ojClass.getUniqueOperation("complete");
				ojClass.addToImports(new OJPathName("java.util.Date"));
				for(TimeEvent d:deadlines){
					// TODO ensure uniqueness of deadline names
					implementTimeEventCallback(ojClass, d, taskDefinition, map.callbackListenerPath(),"getCallingBehaviorExecution()");
					if(EmfEventUtil.getDeadlineKind(d) == DeadlineKind.COMPLETE){
						EventUtil.cancelTimer(completed.getBody(), d, "this");
					}else{
						// TODO
						// EventUtil.cancelTimer(started.getBody(), d, "this");
					}
					// Repeat if not Null because a previous event may cause the process to end
				}
				completed.getBody().addToStatements("getRequest().complete()");
				// addGetName(oa, ojClass);
			}
		}
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior ob){
		if(EmfBehaviorUtil.isStandaloneTask(ob)){
			ResponsibilityDefinitionImpl rd = new ResponsibilityDefinitionImpl(getLibrary(), ob, StereotypesHelper.getStereotype(ob,
					StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK));
			OJAnnotatedClass ojBehavior = findJavaClass(ob);
			implementTask(rd, ojBehavior, ojUtil.buildOperationMap(ob).callbackListenerPath(),"getCallingBehaviorExecution()");
		}
	}
	@VisitBefore
	public void visitStateMachine(StateMachine sm){
		if(sm.getSpecification() != null && EmfBehaviorUtil.isResponsibility(sm.getSpecification()) && EmfBehaviorUtil.hasExecutionInstance(sm)){
			// TODO distinguish between tasks and contractedProcesses
		}
		if(EmfBehaviorUtil.isStandaloneTask(sm)){
			ResponsibilityDefinitionImpl rd = new ResponsibilityDefinitionImpl(getLibrary(), sm, StereotypesHelper.getStereotype(sm,
					StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK));
			OJAnnotatedClass ojBehavior = findJavaClass(sm);
			OperationMap map = ojUtil.buildOperationMap(sm);
			implementTask(rd, ojBehavior, map.callbackListenerPath(),"getCallingBehaviorExecution()");
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
		OJAnnotatedOperation exec = implementExecute(oa, ojClass);
		exec.getBody().addToStatements("getRequest().execute()");
		String callbackMethodName = "on" + NameConverter.capitalize(oa.getName()) + "Completed";
		OJAnnotatedOperation setReturnInfo = new OJAnnotatedOperation("setReturnInfo");
		ojClass.addToOperations(setReturnInfo);
		OJUtil.addPersistentProperty(ojClass, "nodeInstanceUniqueId", new OJPathName("String"), true);
		OJAnnotatedOperation completed = implementTask(rd, ojClass, ojUtil.classifierPathname(EmfActivityUtil.getContainingActivity(oa)),"getProcessObject()");
		completed.getBody().addToStatements("getProcessObject()." + callbackMethodName + "(getNodeInstanceUniqueId(), this)");
		OJAnnotatedOperation isComplete = new OJAnnotatedOperation("isComplete", new OJPathName("boolean"));
		ojClass.addToOperations(isComplete);
		isComplete.initializeResultVariable("getTaskRequest().getCompleted()");
	}
	protected OJAnnotatedOperation implementTask(ResponsibilityDefinition oa,OJAnnotatedClass ojClass,OJPathName processObject, String callingObjectExpression){
		OJAnnotatedOperation completed = new OJAnnotatedOperation("complete");
		ojClass.addToOperations(completed);
		Collection<TimeEvent> deadlines = oa.getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("started");
		ojClass.addToOperations(started);
		EventUtil.addOutgoingEventManagement(ojClass);
		ojClass.addToImports(new OJPathName("java.util.Date"));
		for(TimeEvent d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementTimeEventCallback(ojClass, d, oa, processObject,"("+processObject.getLast() +")"+ callingObjectExpression);
			if(EmfEventUtil.getDeadlineKind(d) == DeadlineKind.COMPLETE){
				EventUtil.cancelTimer(completed.getBody(), d, "this");
			}else{
				EventUtil.cancelTimer(started.getBody(), d, "this");
			}
			// Repeat if not Null because a previous event may cause the process to end
		}
		return completed;
	}
	private void implementTimeEventCallback(OJAnnotatedClass ojClass,TimeEvent d,ResponsibilityDefinition a,OJPathName processObject,
			String callingObjectExpression){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(eventUtil.getEventConsumerName(d), new OJPathName("boolean"));
		ojClass.addToOperations(oper);
		oper.addParam("callingToken", BpmUtil.ITOKEN);
		oper.addParam("date", new OJPathName("java.util.Date"));
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", processObject);
		oper.getBody().addToLocals(callingProcessObject);
		callingProcessObject.setInitExp(callingObjectExpression);
		// if(v.getDefiningElement() instanceof Operation && EmfBehaviorUtil.isResponsibility((BehavioralFeature) v.getDefiningElement())){
		// callingProcessObject.setInitExp("getCallingBehaviorExecution()");
		// }else{
		// callingProcessObject.setInitExp("getProcessObject()");
		// }
		OJIfStatement ifNotNullCallback = new OJIfStatement("callingProcessObject!=null");
		oper.getBody().addToStatements(ifNotNullCallback);
		ifNotNullCallback.getThenPart().addToStatements("return callingProcessObject." + eventUtil.getEventConsumerName(d) + "(date,this)");
		ifNotNullCallback.setElsePart(new OJBlock());
		ifNotNullCallback.getElsePart().addToStatements("return true");
	}
	private OJAnnotatedOperation implementExecute(NamedElement element,OJAnnotatedClass ojClass){
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		if(element instanceof Operation && ((Operation) element).getPreconditions().size() > 0){
			OJUtil.addFailedConstraints(execute);
			execute.getBody().addToStatements("evaluatePreconditions()");
		}
		// add executedOn property for sorting purposes
		OJUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		OJAnnotatedField f = (OJAnnotatedField) ojClass.findField("executedOn");
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
		f.putAnnotation(column);
		OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
				"javax.persistence.TemporalType"), "TIMESTAMP"));
		f.putAnnotation(temporal);
		execute.getBody().addToStatements("setExecutedOn(new Date())");
		return execute;
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