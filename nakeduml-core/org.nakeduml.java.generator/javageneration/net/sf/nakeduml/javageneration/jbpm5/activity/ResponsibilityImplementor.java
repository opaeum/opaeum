package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.bpm.DeadlineKind;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

public class ResponsibilityImplementor extends AbstractBehaviorVisitor{
	public static final OJPathName RESPONSIBILITY_OBJECT = new OJPathName("org.nakeduml.bpm.ResponsibilityObject");
	public static final OJPathName ABSTRACT_REQUEST = new OJPathName("org.nakeduml.bpm.AbstractRequest");
	public static final OJPathName TASK_OBJECT = new OJPathName("org.nakeduml.bpm.TaskObject");
	public static final OJPathName OPERATION_PROCESS_OBJECT = new OJPathName("org.nakeduml.bpm.OperationProcessObject");
	public static final OJPathName PROCESS_REQUEST = new OJPathName("org.nakeduml.bpm.ProcessRequest");
	public static final OJPathName TASK_REQUEST = new OJPathName("org.nakeduml.bpm.TaskRequest");
	@VisitBefore
	public void visitActivity(INakedActivity activity){
		if(activity.getSpecification() instanceof INakedResponsibility && BehaviorUtil.hasExecutionInstance(activity)){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			activityClass.addToImplementedInterfaces(OPERATION_PROCESS_OBJECT);
			OJAnnotatedField field = OJUtil.addProperty(activityClass, "processRequest", PROCESS_REQUEST, true);
			field.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		}
	}
	@VisitBefore
	public void visitStateMachine(INakedStateMachine sm){
		if(sm.getSpecification() instanceof INakedResponsibility && BehaviorUtil.hasExecutionInstance(sm)){
			// TODO distinguish between tasks and processes
			OJAnnotatedClass ojClass = findJavaClass(sm);
			ojClass.addToImplementedInterfaces(TASK_OBJECT);
			OJAnnotatedField field = OJUtil.addProperty(ojClass, "taskRequest", TASK_REQUEST, true);
			field.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		}
	}
	@VisitBefore
	public void visitScreenFlowTask(INakedEmbeddedScreenFlowTask a){
		INakedStateMachine sm = (INakedStateMachine) a.getBehavior();
		NakedClassifierMap map = new NakedClassifierMap(sm);
		OJAnnotatedClass ojClass = new OJAnnotatedClass(a.getMappingInfo().getJavaName().getCapped().getAsIs());
		OJAnnotatedPackage pkg = findOrCreatePackage(OJUtil.packagePathname(a.getActivity()));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, OutputRootId.DOMAIN_GEN_SRC);
		JpaUtil.addEntity(ojClass);
		JpaUtil.buildTableAnnotation(ojClass, a.getMappingInfo().getPersistentName().getAsIs(), super.config, a.getActivity());
		OJAnnotatedField field = OJUtil.addProperty(ojClass, sm.getMappingInfo().getJavaName().getDecapped().getAsIs(), map.javaTypePath(), true);
		OJAnnotationValue manyToone = field.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		manyToone.putAttribute("cascadeType", new OJEnumValue(new OJPathName(CascadeType.class.getName()), "ALL"));
		for(INakedParameter p:sm.getOwnedParameters()){
			NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(a.getActivity(), p);
			OJAnnotatedOperation setter = new OJAnnotatedOperation(pMap.setter());
			setter.addParam(pMap.umlName(), pMap.javaTypePath());
			ojClass.addToOperations(setter);
			setter.getBody().addToStatements("get" + sm.getMappingInfo().getJavaName().getCapped() + "()." + pMap.setter() + "(" + pMap.umlName() + ")");
			OJAnnotatedOperation getter = new OJAnnotatedOperation(pMap.getter(), map.javaTypePath());
			getter.getBody().addToStatements("return get" + sm.getMappingInfo().getJavaName().getCapped() + "()." + pMap.getter() + "()");
			ojClass.addToOperations(getter);
		}
		implementEmbeddedTask(a, ojClass);
	}
	@VisitBefore
	public void visitEmbeddedSingleScreenTask(INakedEmbeddedSingleScreenTask oa){
		OJAnnotatedClass ojClass = findJavaClass(oa.getMessageStructure(getOclEngine().getOclLibrary()));
		ojClass.addToImplementedInterfaces(TASK_OBJECT);
		implementEmbeddedTask(oa, ojClass);
	}
	@VisitBefore
	public void visitResponsibility(INakedResponsibility oa){
		OJAnnotatedClass ojClass = findJavaClass(oa.getMessageStructure(getOclEngine().getOclLibrary()));
		ojClass.addToImplementedInterfaces(RESPONSIBILITY_OBJECT);
		OJAnnotatedOperation exec = implementExecute(oa, ojClass);
		TaskUtil.implementAssignmentsAndDeadlines(exec, exec.getBody(), oa.getTaskDefinition(), "self");
		implementTask(oa, ojClass, new NakedOperationMap(oa).callbackOperName());
	}
	private void implementEmbeddedTask(INakedEmbeddedTask oa,OJAnnotatedClass ojClass){
		ojClass.addToImports(TASK_OBJECT);
		implementExecute(oa, ojClass);
		String callbackMethodName = "on" + oa.getMappingInfo().getJavaName().getCapped() + "Completed";
		implementTask(oa, ojClass, callbackMethodName);
	}
	private void implementTask(INakedDefinedResponsibility oa,OJAnnotatedClass ojClass,String callbackMethodName){
		addSetReturnInfo(ojClass);
		addGetName(oa, ojClass);
		OJAnnotatedOperation getProcessObject = addGetCallingProcessObject(ojClass, new NakedClassifierMap(oa.getTaskDefinition().getExpressionContext()).javaTypePath());
		addRequestForWork(ojClass);
		OJAnnotatedOperation completed = new OJAnnotatedOperation("completed");
		ojClass.addToOperations(completed);
		addCallingProcessObjectField(completed,oa);
		OJIfStatement ifNotNull = new OJIfStatement("callingProcessObject!=null");
		completed.getBody().addToStatements(ifNotNull);
		ifNotNull.getThenPart().addToStatements("callingProcessObject." + callbackMethodName + "(this)");
		Collection<INakedDeadline> deadlines = oa.getTaskDefinition().getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("started");
		addCallingProcessObjectField(started,oa);
		ojClass.addToOperations(started);
		for(INakedDeadline d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementDeadlineCallback(ojClass, getProcessObject, d,oa);
			OJIfStatement ifNotNullCancel = new OJIfStatement("callingProcessObject!=null");
			EventUtil.cancelTimer(ifNotNullCancel.getThenPart(), d, "this");
			// Repeat if not Null because a previous event may cause the process to end
			if(d.getKind() == DeadlineKind.COMPLETE){
				completed.getBody().addToStatements(ifNotNullCancel);
			}else{
				started.getBody().addToStatements(ifNotNullCancel);
			}
		}
	}
	private void implementDeadlineCallback(OJAnnotatedClass ojClass,OJAnnotatedOperation getProcessObject,INakedDeadline d, INakedDefinedResponsibility a){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(EventUtil.getCallbackMethodName(d));
		ojClass.addToOperations(oper);
		//TODO give this some thought
		OJUtil.addMetaInfo(oper, a);
		addCallingProcessObjectField(getProcessObject,a);
		OJIfStatement ifNotNullCallback = new OJIfStatement("callingProcessObject!=null");
		oper.getBody().addToStatements(ifNotNullCallback);
		ifNotNullCallback.getThenPart().addToStatements("callProcessObject." + EventUtil.getCallbackMethodName(d) + "(this)");
	}
	private void addCallingProcessObjectField(OJAnnotatedOperation getProcessObject,INakedDefinedResponsibility v){
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", getProcessObject.getReturnType());
		if(v instanceof INakedResponsibility){
			callingProcessObject.setInitExp("getCallingProcessObject()");
		}else{
			callingProcessObject.setInitExp("getProcessObject()");
		}
	}
	private void addRequestForWork(OJAnnotatedClass ojClass){
		OJAnnotatedField field = OJUtil.addProperty(ojClass, "request", ABSTRACT_REQUEST, true);
		OJAnnotationValue manyToone = field.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		manyToone.putAttribute("cascadeType", new OJEnumValue(new OJPathName(CascadeType.class.getName()), "ALL"));
	}
	private OJAnnotatedOperation implementExecute(PreAndPostConstrained element,OJAnnotatedClass ojClass){
		OJAnnotatedOperation execute = new OJAnnotatedOperation();
		execute.setName("execute");
		ojClass.addToOperations(execute);
		if(element instanceof INakedOperation && element.getPreConditions().size() > 0){
			OJUtil.addFailedConstraints(execute);
			execute.getBody().addToStatements("evaluatePreConditions()");
		}
		// add executedOn property for sorting purposes
		OJUtil.addProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
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
	private void addGetName(INakedElement c,OJAnnotatedClass ojOperationClass){
		OJOperation getName = OJUtil.findOperation(ojOperationClass, "getName");
		if(getName == null){
			getName = new OJAnnotatedOperation();
			getName.setName("getName");
			getName.setReturnType(new OJPathName("String"));
			ojOperationClass.addToOperations(getName);
		}else{
			getName.setBody(new OJBlock());
		}
		getName.getBody().addToStatements("return \"" + c.getName() + "On\"+getContextObject().getName()");
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction ca){
		// Always order invocations of processes or tasks by the executedOn date
		if(ca.isLongRunning()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(ca, getOclEngine().getOclLibrary());
			if(map.isMany()){
				OJAnnotatedClass ojOwner = findJavaClass(ca.getActivity());
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
			}
		}
	}
}