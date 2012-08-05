package org.opaeum.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

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
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.PersistentNameUtil;
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
import org.opaeum.javageneration.jbpm5.AbstractBehaviorVisitor;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.statemachine.StateMachineImplementor;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.DeadlineKind;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		ActivityProcessImplementor.class,StateMachineImplementor.class
},after = {
		StateMachineImplementor.class,ActivityProcessImplementor.class
})
public class ResponsibilityImplementor extends AbstractBehaviorVisitor{
	@VisitBefore
	public void visitActivity(Activity activity){
		if(EmfBehaviorUtil.isResponsibility( activity.getSpecification() ) && EmfBehaviorUtil.hasExecutionInstance(activity)){
		}
		for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively( activity)){
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
				OJAnnotatedClass ojClass = findJavaClass(getLibrary().getMessageStructure( o));
				OJAnnotatedOperation exec = implementExecute(o, ojClass);
				/**
				 * If contextObject instanceof BusinessRole then create task, assign to user,kick off events IF contextObject instanceof
				 * BusinessGateway on a Business Component then see if there is an implementing process contained by the Business Component
				 * If there is,create it and execute it.
				 * Then check if there is a BusinessProcess with that oper as AcceptCall action and resolve it using correlations
				 * Else if there is a delegation to a property of type BusinessRole, create assignees for each business role
				 * if the port has other assignement expressions assign these too
				 */
				ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(o,StereotypeNames.RESPONSIBILITY);
				taskUtil.implementAssignmentsAndDeadlines(exec, exec.getBody(), taskDefinition, "this");
				OperationMap map = ojUtil.buildOperationMap(o);
				EventUtil.addOutgoingEventManagement(ojClass);
				Collection<TimeEvent> deadlines = taskDefinition.getDeadlines();
				OJOperation completed = ojClass.findOperation("completed", Collections.emptyList());
				ojClass.addToImports(new OJPathName("java.util.Date"));
				for(TimeEvent d:deadlines){
					// TODO ensure uniqueness of deadline names
					implementTimeEventCallback(ojClass, d, taskDefinition, map.callbackListenerPath());
					if(EmfEventUtil.getDeadlineKind(d) == DeadlineKind.COMPLETE){
						EventUtil.cancelTimer(completed.getBody(), d, "this");
					}else{
						//TODO 
//						EventUtil.cancelTimer(started.getBody(), d, "this");
					}
					// Repeat if not Null because a previous event may cause the process to end
				}
//				addGetName(oa, ojClass);
			}
		}
	}
	@VisitBefore
	public void visitStateMachine(StateMachine sm){
		if(EmfBehaviorUtil.isResponsibility(sm.getSpecification()) && EmfBehaviorUtil.hasExecutionInstance(sm)){
			// TODO distinguish between tasks and contractedProcesses
		}
	}
	private void visitScreenFlowTask(CallBehaviorAction a){
		StateMachine sm = (StateMachine) a.getBehavior();
		ClassifierMap map = ojUtil.buildClassifierMap(sm,(CollectionKind) null);
		Activity activity = EmfActivityUtil.getContainingActivity(a);
		if(ojUtil.requiresJavaRename( a)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(a));
		}
		OJAnnotatedClass ojClass = new OJAnnotatedClass(NameConverter.capitalize(a.getName()));
		OJPackage pkg = findOrCreatePackage(ojUtil.packagePathname(activity));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		JpaUtil.addClass(ojClass);
		JpaUtil.buildTableAnnotation(ojClass, PersistentNameUtil.getPersistentName( a).getAsIs(), super.config, activity);
		for(Parameter p:sm.getOwnedParameters()){
			StructuralFeatureMap pMap = ojUtil.buildStructuralFeatureMap(p);
			OJAnnotatedOperation setter = new OJAnnotatedOperation(pMap.setter());
			setter.addParam(pMap.fieldname(), pMap.javaTypePath());
			ojClass.addToOperations(setter);
			setter.getBody().addToStatements("get" + NameConverter.capitalize(sm.getName()) + "()." + pMap.setter() + "(" + pMap.fieldname() + ")");
			OJAnnotatedOperation getter = new OJAnnotatedOperation(pMap.getter(), map.javaTypePath());
			getter.getBody().addToStatements("return get" +  NameConverter.capitalize(sm.getName()) + "()." + pMap.getter() + "()");
			ojClass.addToOperations(getter);
		}
		ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(a,StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		implementEmbeddedTask(a, ojClass,taskDefinition);
	}
	private void visitEmbeddedSingleScreenTask(OpaqueAction oa){
		OJAnnotatedClass ojClass = findJavaClass(getLibrary().getMessageStructure( oa));
		ResponsibilityDefinition taskDefinition = getLibrary().getResponsibilityDefinition(oa,StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);

		implementEmbeddedTask(oa, ojClass,taskDefinition);
	}
	private void implementEmbeddedTask(Action oa,OJAnnotatedClass ojClass, ResponsibilityDefinition rd){
		implementExecute(oa, ojClass);
		String callbackMethodName = "on" + NameConverter.capitalize(oa.getName()) + "Completed";
		OJAnnotatedOperation setReturnInfo = new OJAnnotatedOperation("setReturnInfo");
		ojClass.addToOperations(setReturnInfo);
		setReturnInfo.addParam("context", Jbpm5Util.getProcessContext());
		setReturnInfo.getBody().addToStatements("this.nodeInstanceUniqueId=((" + Jbpm5Util.getNodeInstance().getLast() + ")context.getNodeInstance()).getUniqueId()");
		OJUtil.addPersistentProperty(ojClass, "nodeInstanceUniqueId", new OJPathName("String"), true);
		ojClass.addToImports(Jbpm5Util.getNodeInstance());
		implementTask(rd, ojClass, callbackMethodName, ojUtil.classifierPathname(EmfActivityUtil.getContainingActivity(oa)));
		OJAnnotatedOperation isComplete = new OJAnnotatedOperation("isComplete", new OJPathName("boolean"));
		ojClass.addToOperations(isComplete);
		isComplete.initializeResultVariable("getTaskRequest().getCompleted()");
	}
	private void implementTask(ResponsibilityDefinition oa,OJAnnotatedClass ojClass,String callbackMethodName,OJPathName processObject){
		OJAnnotatedOperation completed = new OJAnnotatedOperation("completed");
		ojClass.addToOperations(completed);
		completed.getBody().addToStatements("getProcessObject()." + callbackMethodName + "(getNodeInstanceUniqueId(), this)");
		Collection<TimeEvent> deadlines = oa.getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("started");
		ojClass.addToOperations(started);
		EventUtil.addOutgoingEventManagement(ojClass);
		ojClass.addToImports(new OJPathName("java.util.Date"));
		for(TimeEvent d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementTimeEventCallback(ojClass, d, oa, processObject);
			if(EmfEventUtil.getDeadlineKind(d) == DeadlineKind.COMPLETE){
				EventUtil.cancelTimer(completed.getBody(), d, "this");
			}else{
				EventUtil.cancelTimer(started.getBody(), d, "this");
			}
			// Repeat if not Null because a previous event may cause the process to end
		}
	}
	private void implementTimeEventCallback(OJAnnotatedClass ojClass,TimeEvent d,ResponsibilityDefinition a,OJPathName processObject){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(eventUtil.getEventConsumerName(d), new OJPathName("boolean"));
		ojClass.addToOperations(oper);
		oper.addParam("nodeInstanceUniqueId", new OJPathName("String"));
		oper.addParam("date", new OJPathName("java.util.Date"));
		addCallingProcessObjectField(oper, processObject, a);
		OJIfStatement ifNotNullCallback = new OJIfStatement("callingProcessObject!=null");
		oper.getBody().addToStatements(ifNotNullCallback);
		ifNotNullCallback.getThenPart().addToStatements("return callingProcessObject." + eventUtil.getEventConsumerName(d) + "(date,this)");
		ifNotNullCallback.setElsePart(new OJBlock());
		ifNotNullCallback.getElsePart().addToStatements("return false");
	}
	private void addCallingProcessObjectField(OJAnnotatedOperation op,OJPathName processObject,ResponsibilityDefinition v){
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", processObject);
		op.getBody().addToLocals(callingProcessObject);
		if(v.getDefiningElement() instanceof Operation && EmfBehaviorUtil.isResponsibility((BehavioralFeature) v.getDefiningElement())){
			callingProcessObject.setInitExp("getCallingProcessObject()");
		}else{
			callingProcessObject.setInitExp("getProcessObject()");
		}
	}
	private OJAnnotatedOperation implementExecute(NamedElement element,OJAnnotatedClass ojClass){
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		if(element instanceof Operation && ((Operation) element).getPreconditions() .size() > 0){
			OJUtil.addFailedConstraints(execute);
			execute.getBody().addToStatements("evaluatePreConditions()");
		}
		// add executedOn property for sorting purposes
		OJUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
		OJAnnotatedField f = (OJAnnotatedField) ojClass.findField("executedOn");
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
		f.putAnnotation(column);
		OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(
				new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
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
		if(EmfActionUtil.isLongRunning( ca)){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(ca);
			if(map.isMany()){
				OJAnnotatedClass ojOwner = findJavaClass(EmfActivityUtil.getContainingActivity(ca));
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
			}
		}
	}
}