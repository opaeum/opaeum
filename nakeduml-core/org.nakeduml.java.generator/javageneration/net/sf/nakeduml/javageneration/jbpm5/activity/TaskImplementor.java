package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.bpm.DeadlineKind;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.model.ICollectionType;

import org.nakeduml.annotation.PersistentName;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
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
import org.nakeduml.runtime.domain.AbstractRequest;
import org.nakeduml.runtime.domain.IBusinessServiceInvocation;
import org.nakeduml.runtime.domain.ITaskInvocation;
import org.nakeduml.runtime.domain.IUserInRole;

public class TaskImplementor extends AbstractBehaviorVisitor{
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
		OJAnnotatedClass ojClass = findJavaClass(oa.getMessageStructure());
		ojClass.addToImplementedInterfaces(new OJPathName(ITaskInvocation.class.getName()));
		implementEmbeddedTask(oa, ojClass);
	}
	@VisitBefore
	public void visitResponsibility(INakedResponsibility oa){
		OJAnnotatedClass ojClass = findJavaClass(new OperationMessageStructureImpl(oa));
		ojClass.addToImplementedInterfaces(new OJPathName(IBusinessServiceInvocation.class.getName()));
		OJAnnotatedOperation exec = implementExecute(oa, ojClass);
		TaskUtil.implementAssignmentsAndDeadlines(exec, exec.getBody(), oa.getTaskDefinition(), "self");
	}
	private void implementEmbeddedTask(INakedEmbeddedTask oa,OJAnnotatedClass ojClass){
		ojClass.addToImports(ITaskInvocation.class.getName());
		implementExecute(oa, ojClass);
		Jbpm5Util.implementRelationshipWithProcess(ojClass, true, "callingProcess");
		addSetReturnInfo(ojClass);
		addGetName(oa, ojClass);
		OJAnnotatedOperation getProcessObject = addGetCallingProcessObject(ojClass, new NakedClassifierMap(oa.getActivity()).javaTypePath());
		addRequestForWork(ojClass);
		OJAnnotatedOperation complete = new OJAnnotatedOperation("completed");
		ojClass.addToOperations(complete);
		complete.getBody().addToStatements("getCallingProcessObject().on" + oa.getMappingInfo().getJavaName().getCapped() + "Completed(this)");
		OJAnnotatedOperation completed = complete;
		Collection<INakedDeadline> deadlines = oa.getTaskDefinition().getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("started");
		ojClass.addToOperations(started);
		addCallingProcessObjectField(started);
		addCallingProcessObjectField(completed);
		for(INakedDeadline d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementDeadlineCallback(ojClass, getProcessObject, d);
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
	private void implementDeadlineCallback(OJAnnotatedClass ojClass,OJAnnotatedOperation getProcessObject,INakedDeadline d){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(EventUtil.getCallbackMethodName(d));
		ojClass.addToOperations(oper);
		oper.putAnnotation(new OJAnnotationValue(new OJPathName(PersistentName.class.getName()), d.getMappingInfo().getPersistentName().getAsIs()));
		addCallingProcessObjectField(getProcessObject);
		OJIfStatement ifNotNullCallback = new OJIfStatement("callingProcessObject!=null");
		oper.getBody().addToStatements(ifNotNullCallback);
		ifNotNullCallback.getThenPart().addToStatements("callProcessObject." + EventUtil.getCallbackMethodName(d) + "(this)");
	}
	public void addCallingProcessObjectField(OJAnnotatedOperation getProcessObject){
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", getProcessObject.getReturnType());
		callingProcessObject.setInitExp("getCallingProcessObject()");
	}
	private void addRequestForWork(OJAnnotatedClass ojClass){
		OJAnnotatedField field = OJUtil.addProperty(ojClass, "request", new OJPathName(AbstractRequest.class.getName()), true);
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