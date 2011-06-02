package net.sf.nakeduml.javageneration.jbpm5;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

import org.nakeduml.annotation.PersistentName;
import org.nakeduml.event.ChangeEvent;
import org.nakeduml.event.Deadline;
import org.nakeduml.event.TimeEvent;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.IEventSource;
import org.nakeduml.runtime.domain.TimeUnit;

public class EventUtil{
	public static String getCallbackMethodName(INakedEvent e){
		return  e.getMappingInfo().getJavaName().getDecapped() + "Occurred";
	}
	public static String getEventMethodPersistentName(INakedElement event){
		return  event.getMappingInfo().getPersistentName() + "_occurred";
	}
	public static void implementChangeEventRequest(OJOperation operation,INakedChangeEvent event){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getChangeExpression();
		operation.getOwner().addToImports(ChangeEvent.class.getName());
		EventUtil.addOutgoingEventManagement(owner);
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getBehaviorContext(), when.getType());
			OJAnnotatedOperation evaluationMethod = new OJAnnotatedOperation("evaluate" + event.getMappingInfo().getJavaName().getCapped(), new OJPathName("boolean"));
			evaluationMethod
					.putAnnotation(new OJAnnotationValue(new OJPathName(PersistentName.class.getName()), "evaluate_" + event.getMappingInfo().getPersistentName()));
			evaluationMethod.getBody().addToStatements("return " + whenExpr);
			owner.addToOperations(evaluationMethod);
			operation.getBody().addToStatements(
					"getOutgoingEvents().add(new ChangeEvent(this,\"" + EventUtil.getEventMethodPersistentName(event) + "\",\"" + "evaluate_"
							+ event.getMappingInfo().getPersistentName() + "\"))");
		}else{
			operation.getBody().addToStatements("NO_CHANGE_EXPRESSION_SPECIFIED");
		}
	}

	public static void addOutgoingEventManagement(OJClass ojClass){
		OJPathName pn = new OJPathName(IEventSource.class.getName());
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
	public static void implementTimeEventRequest(OJOperation operation,OJBlock block, INakedTimeEvent event){
		operation.getOwner().addToImports(TimeEvent.class.getName());
		implementTimerRequest(operation, block, event, "self", "TimeEvent");
	}
	public static void implementDeadlineRequest(OJOperation operation,OJBlock block, INakedDeadline event, String taskName){
		operation.getOwner().addToImports(Deadline.class.getName());
		implementTimerRequest(operation, block, event, taskName, "Deadline");
	}
	private static void implementTimerRequest(OJOperation operation,OJBlock block,INakedTimer event,String targetExpression,String eventType){
		INakedClassifier context=event.getNearestClassifier();
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		String callBackMethodName=getEventMethodPersistentName(event);
		INakedValueSpecification when = event.getWhen();
		operation.getOwner().addToImports(TimeEvent.class.getName());
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, context, when.getType());
			if(event.isRelative()){
				owner.addToImports(TimeUnit.class.getName());
				TimeUnit timeUnit = event.getTimeUnit() == null ? TimeUnit.BUSINESS_DAY : event.getTimeUnit();
				block.addToStatements(
						"getOutgoingEvents().add(new "+eventType+"("+targetExpression+",\"" + callBackMethodName + "\"," + whenExpr + ",TimeUnit." + timeUnit.name() + "))");
			}else{
				block.addToStatements("getOutgoingEvents().add(new "+eventType+"("+targetExpression+",\"" + callBackMethodName + "\"," + whenExpr + "))");
			}
		}else{
			block.addToStatements("NO_WHEN_EXPRESSION_SPECIFIED");
		}
		addOutgoingEventManagement(owner);
	}
	public static void cancelTimer(OJBlock block, INakedTimer event,String targetExpression){
		block.addToStatements("getOutgoingEvents().add(new TimeEvent("+targetExpression+",\"" + getEventMethodPersistentName(event) + "\",true))");
	}
	public static void cancelChangeEvent(OJBlock block,INakedChangeEvent event){
		block.addToStatements("getOutgoingEvents().add(new ChangeEvent(this,\"" + getEventMethodPersistentName(event) + "\",true))");
	}
}
