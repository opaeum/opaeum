package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.TaskUtil;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineImplementor;
import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;
import org.nakeduml.runtime.domain.DeadlineKind;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		ActivityProcessImplementor.class,StateMachineImplementor.class
},after = {
		StateMachineImplementor.class,ActivityProcessImplementor.class
})
public class ResponsibilityImplementor extends AbstractBehaviorVisitor{
	@VisitBefore
	public void visitActivity(INakedActivity activity){
		if(activity.getSpecification() instanceof INakedResponsibility && BehaviorUtil.hasExecutionInstance(activity)){
			OJAnnotatedClass activityClass = findJavaClass(activity);
		}
		for(INakedActivityNode n:activity.getActivityNodesRecursively()){
			if(n instanceof INakedEmbeddedScreenFlowTask){
				visitScreenFlowTask((INakedEmbeddedScreenFlowTask) n);
			}else if(n instanceof INakedEmbeddedSingleScreenTask){
				visitEmbeddedSingleScreenTask((INakedEmbeddedSingleScreenTask) n);
			}
			if(n instanceof INakedCallAction){
				visitCallAction((INakedCallAction) n);
			}
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitClassifier(INakedClassifier nc){
		for(IOperation o:nc.getOperations()){
			if(o instanceof INakedResponsibility){
				INakedResponsibility oa = (INakedResponsibility) o;
				OJAnnotatedClass ojClass = findJavaClass(oa.getMessageStructure());
				OJAnnotatedOperation exec = implementExecute(oa, ojClass);
				TaskUtil.implementAssignmentsAndDeadlines(exec, exec.getBody(), oa.getTaskDefinition(), "self");
				NakedOperationMap map = new NakedOperationMap(oa);
				implementTask(oa, ojClass, map.callbackOperName(), map.callbackListenerPath());
				addGetName(oa, ojClass);
			}
		}
	}
	@VisitBefore
	public void visitStateMachine(INakedStateMachine sm){
		if(sm.getSpecification() instanceof INakedResponsibility && BehaviorUtil.hasExecutionInstance(sm)){
			// TODO distinguish between tasks and contractedProcesses
			OJAnnotatedClass ojClass = findJavaClass(sm);
		}
	}
	private void visitScreenFlowTask(INakedEmbeddedScreenFlowTask a){
		INakedStateMachine sm = (INakedStateMachine) a.getBehavior();
		NakedClassifierMap map = new NakedClassifierMap(sm);
		if(a.getMappingInfo().requiresJavaRename()){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(a.getActivity().getMappingInfo().getOldQualifiedJavaName() + "."
					+ a.getMappingInfo().getOldJavaName().getCapped()));
		}
		OJAnnotatedClass ojClass = new OJAnnotatedClass(a.getMappingInfo().getJavaName().getCapped().getAsIs());
		OJPackage pkg = findOrCreatePackage(OJUtil.packagePathname(a.getActivity()));
		pkg.addToClasses(ojClass);
		createTextPath(ojClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		JpaUtil.addEntity(ojClass);
		JpaUtil.buildTableAnnotation(ojClass, a.getMappingInfo().getPersistentName().getAsIs(), super.config, a.getActivity());
		OJAnnotatedField field = OJUtil.addProperty(ojClass, sm.getMappingInfo().getJavaName().getDecapped().getAsIs(), map.javaTypePath(), true);
		OJAnnotationValue manyToone = field.putAnnotation(new OJAnnotationValue(new OJPathName(ManyToOne.class.getName())));
		manyToone.putAttribute("cascade", new OJEnumValue(new OJPathName(CascadeType.class.getName()), "ALL"));
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
	private void visitEmbeddedSingleScreenTask(INakedEmbeddedSingleScreenTask oa){
		OJAnnotatedClass ojClass = findJavaClass(oa.getMessageStructure());
		implementEmbeddedTask(oa, ojClass);
	}
	private void implementEmbeddedTask(INakedEmbeddedTask oa,OJAnnotatedClass ojClass){
		implementExecute(oa, ojClass);
		String callbackMethodName = "on" + oa.getMappingInfo().getJavaName().getCapped() + "Completed";
		OJAnnotatedOperation setReturnInfo = new OJAnnotatedOperation("setReturnInfo");
		ojClass.addToOperations(setReturnInfo);
		setReturnInfo.addParam("context", Jbpm5Util.getProcessContext());
		setReturnInfo.getBody().addToStatements("this.nodeInstanceUniqueId=((" + Jbpm5Util.getNodeInstance().getLast() + ")context.getNodeInstance()).getUniqueId()");
		OJUtil.addProperty(ojClass, "nodeInstanceUniqueId", new OJPathName("String"), true);
		ojClass.addToImports(Jbpm5Util.getNodeInstance());
		implementTask(oa, ojClass, callbackMethodName, new OJPathName(oa.getActivity().getMappingInfo().getQualifiedJavaName()));
	}
	private void implementTask(INakedDefinedResponsibility oa,OJAnnotatedClass ojClass,String callbackMethodName,OJPathName processObject){
		OJAnnotatedOperation completed = new OJAnnotatedOperation("completed");
		ojClass.addToOperations(completed);
		completed.getBody().addToStatements("getProcessObject()." + callbackMethodName + "(getNodeInstanceUniqueId(), this)");
		Collection<INakedDeadline> deadlines = oa.getTaskDefinition().getDeadlines();
		OJAnnotatedOperation started = new OJAnnotatedOperation("started");
		ojClass.addToOperations(started);
		EventUtil.addOutgoingEventManagement(ojClass);
		ojClass.addToImports(new OJPathName("java.util.Date"));
		for(INakedDeadline d:deadlines){
			// TODO ensure uniqueness of deadline names
			implementDeadlineCallback(ojClass, d, oa, processObject);
			if(d.getKind() == DeadlineKind.COMPLETE){
				EventUtil.cancelTimer(completed.getBody(), d, "this");
			}else{
				EventUtil.cancelTimer(started.getBody(), d, "this");
			}
			// Repeat if not Null because a previous event may cause the process to end
		}
	}
	private void implementDeadlineCallback(OJAnnotatedClass ojClass,INakedDeadline d,INakedDefinedResponsibility a,OJPathName processObject){
		OJAnnotatedOperation oper = new OJAnnotatedOperation(EventUtil.getEventConsumerName(d),new OJPathName("boolean"));
		ojClass.addToOperations(oper);
		oper.addParam("nodeInstanceUniqueId", new OJPathName("String"));
		oper.addParam("date", new OJPathName("java.util.Date"));
		
		addCallingProcessObjectField(oper, processObject, a);
		OJIfStatement ifNotNullCallback = new OJIfStatement("callingProcessObject!=null");
		oper.getBody().addToStatements(ifNotNullCallback);
		ifNotNullCallback.getThenPart().addToStatements("return callingProcessObject." + EventUtil.getEventConsumerName(d) + "(date,this)");
		ifNotNullCallback.setElsePart(new OJBlock());
		ifNotNullCallback.getElsePart().addToStatements("return false");
	}
	private void addCallingProcessObjectField(OJAnnotatedOperation op,OJPathName processObject,INakedDefinedResponsibility v){
		OJAnnotatedField callingProcessObject = new OJAnnotatedField("callingProcessObject", processObject);
		op.getBody().addToLocals(callingProcessObject);
		if(v instanceof INakedResponsibility){
			callingProcessObject.setInitExp("getCallingProcessObject()");
		}else{
			callingProcessObject.setInitExp("getProcessObject()");
		}
	}
	private OJAnnotatedOperation implementExecute(PreAndPostConstrained element,OJAnnotatedClass ojClass){
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
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
			getName = new OJAnnotatedOperation("getName");
			getName.setReturnType(new OJPathName("String"));
			ojOperationClass.addToOperations(getName);
		}else{
			getName.setBody(new OJBlock());
		}
		getName.getBody().addToStatements("return \"" + c.getName() + "On\"+getContextObject().getName()");
	}
	public void visitCallAction(INakedCallAction ca){
		// Always order invocations of contractedProcesses or tasks by the executedOn date
		if(ca.isLongRunning()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(ca, getLibrary());
			if(map.isMany()){
				OJAnnotatedClass ojOwner = findJavaClass(ca.getActivity());
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.OrderBy"), "executedOn"));
			}
		}
	}
}