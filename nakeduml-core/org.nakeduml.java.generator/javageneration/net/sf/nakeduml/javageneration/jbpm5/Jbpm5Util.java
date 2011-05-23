package net.sf.nakeduml.javageneration.jbpm5;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;

import org.nakeduml.annotation.PersistentName;
import org.nakeduml.environment.ITimeEventDispatcher;
import org.nakeduml.event.ChangeEvent;
import org.nakeduml.event.TimeEvent;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.AbstractEventSource;
import org.nakeduml.runtime.domain.ExceptionHolder;
import org.nakeduml.runtime.domain.TimeUnit;

public class Jbpm5Util{
	public static String stepLiteralName(INakedElement s){
		return (s).getMappingInfo().getJavaName().getAsIs().toUpperCase();
	}
	public static OJPathName asyncInterfaceOf(INakedClassifier target){
		OJPathName result = OJUtil.classifierPathname(target);
		String name = "IAsync" + result.getLast();
		result = result.getHead();
		result.addToNames(name);
		return result;
	}
	public static OJPathName getNodeInstance(){
		return new OJPathName("org.jbpm.workflow.instance.impl.NodeInstanceImpl");
	}
	public static String generateProcessName(IParameterOwner parameterOwner){
		return parameterOwner.getOwnerElement().getMappingInfo().getPersistentName() + "_" + parameterOwner.getMappingInfo().getPersistentName();
	}
	public static String getArtificialJoinName(INakedElement target){
		return "join_for_" + target.getMappingInfo().getPersistentName();
	}
	public static String getGuardMethod(GuardedFlow t){
		return "is" + t.getSource().getMappingInfo().getJavaName().getCapped() + t.getMappingInfo().getJavaName().getCapped();
	}
	public static void implementTimeEventRequest(OJOperation operation,INakedTimeEvent event){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getWhen();
		operation.getOwner().addToImports(TimeEvent.class.getName());
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getOwningBehavior(), when.getType());
			String callBackMethodName = getEventMethodPersistentName(event);
			if(event.isRelative()){
				owner.addToImports(TimeUnit.class.getName());
				TimeUnit timeUnit = event.getTimeUnit() == null ? TimeUnit.BUSINESS_DAY : event.getTimeUnit();
				operation.getBody().addToStatements(
						"getOutgoingEvents().add(new TimeEvent(this,\"" + callBackMethodName + "\"," + whenExpr + ",TimeUnit." + timeUnit.name() + "))");
			}else{
				operation.getBody().addToStatements("getOutgoingEvents().add(new TimeEvent(this,\"" + callBackMethodName + "\"," + whenExpr + "))");
			}
		}else{
			operation.getBody().addToStatements("NO_WHEN_EXPRESSION_SPECIFIED");
		}
		addOutgoingEventManagement(owner);
	}
	public static void implementChangeEventRequest(OJOperation operation,INakedChangeEvent event){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getChangeExpression();
		operation.getOwner().addToImports(ChangeEvent.class.getName());
		addOutgoingEventManagement(owner);
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getOwningBehavior(), when.getType());
			OJAnnotatedOperation evaluationMethod = new OJAnnotatedOperation("evaluate" + event.getMappingInfo().getJavaName().getCapped(), new OJPathName("boolean"));
			evaluationMethod
					.putAnnotation(new OJAnnotationValue(new OJPathName(PersistentName.class.getName()), "evaluate_" + event.getMappingInfo().getPersistentName()));
			evaluationMethod.getBody().addToStatements("return " + whenExpr);
			owner.addToOperations(evaluationMethod);
			operation.getBody().addToStatements(
					"getOutgoingEvents().add(new ChangeEvent(this,\"" + getEventMethodPersistentName(event) + "\",\"" + "evaluate_"
							+ event.getMappingInfo().getPersistentName() + "\"))");
		}else{
			operation.getBody().addToStatements("NO_CHANGE_EXPRESSION_SPECIFIED");
		}
	}
	public static void addOutgoingEventManagement(OJClass ojClass){
		OJPathName pn = new OJPathName(AbstractEventSource.class.getName());
		if(!ojClass.getImplementedInterfaces().contains(pn)){
			ojClass.addToImplementedInterfaces(pn);
			OJPathName eventSetPath = new OJPathName(Set.class.getName());
			eventSetPath.addToElementTypes(new OJPathName("Object"));
			OJAnnotatedField field = OJUtil.addProperty(ojClass, "outgoingEvents", eventSetPath, true);
			field.setInitExp("new HashSet<Object>()");
			ojClass.addToImports(HashSet.class.getName());
			field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
	public static String getEventMethodPersistentName(INakedElement event){
		return "on_" + event.getMappingInfo().getPersistentName();
	}
	public static void cancelTimer(OJOperation cancel,INakedTimeEvent event){
		cancel.getOwner().addToImports(ITimeEventDispatcher.class.getName());
		cancel.getBody().addToStatements("getOutgoingEvents().add(new TimeEvent(this,\"" + getEventMethodPersistentName(event) + "\",true))");
	}
	public static void cancelChangeEvent(OJOperation cancel,INakedChangeEvent event){
		cancel.getOwner().addToImports(ITimeEventDispatcher.class.getName());
		cancel.getBody().addToStatements("getOutgoingEvents().add(new ChangeEvent(this,\"" + getEventMethodPersistentName(event) + "\",true))");
	}
	public static String getArtificialForkName(INakedElement owner){
		return "fork_for_" + owner.getMappingInfo().getPersistentName();
	}
	public static OJPathName getExceptionHolder(){
		return new OJPathName(ExceptionHolder.class.getName());
	}
	public static String endNodeFieldNameFor(INakedElement flow){
		return "endNodeIn" + flow.getMappingInfo().getJavaName();
	}
	public static OJPathName getWorkflowProcesInstance(){
		return new OJPathName("org.jbpm.workflow.instance.WorkflowProcessInstance");
	}
	public static OJPathName getWorkflowProcessImpl(){
		return new OJPathName("org.jbpm.workflow.core.impl.WorkflowProcessImpl");
	}
	public static OJPathName getNode(){
		return new OJPathName("org.jbpm.workflow.core.impl.NodeImpl");
	}
	public static String getArtificialChoiceName(INakedActivityNode node){
		return node.getMappingInfo().getPersistentName().getAsIs() + "_choice";
	}
}
