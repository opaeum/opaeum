package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.javageneration.maps.NakedOperationMap;
import net.sf.nakeduml.javageneration.maps.SignalMap;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedCallEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignalEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.statemachines.INakedCompletionEvent;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.CancelledEvent;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.nakeduml.runtime.domain.TimeUnit;

//TODO refactor into an EventMap
public class EventUtil{
	public static String getEventConsumerName(INakedElement e){
		if(e instanceof INakedSignalEvent){
			return new SignalMap(((INakedSignalEvent) e).getSignal()).eventConsumerMethodName();
		}else if(e instanceof INakedCallEvent){
			return new NakedOperationMap(((INakedCallEvent) e).getOperation()).eventConsumerMethodName();
		}else if(e instanceof INakedCompletionEvent){
			return "on" + e.getMappingInfo().getJavaName().getCapped() + "Completed";
		}else if(e instanceof INakedEvent){
			return e.getMappingInfo().getJavaName().getDecapped() + "Occurred";
		}else{
			return "on" + e.getMappingInfo().getJavaName().getCapped();
		}
	}
	public static OJPathName handlerPathName(INakedOperation s){
		return OJUtil.packagePathname(s.getOwner()).append(s.getMappingInfo().getJavaName().getCapped() + "Handler" + s.getMappingInfo().getNakedUmlId());
	}
	public static OJPathName handlerPathName(INakedEvent e){
		return OJUtil.packagePathname(e.getContext()).append(e.getMappingInfo().getJavaName().getCapped() + "Handler");
	}
	public static void implementChangeEventRequest(OJOperation operation,INakedChangeEvent event){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getChangeExpression();
		if(when != null){
			String changeExpr = ValueSpecificationUtil.expressValue(operation, when, event.getBehaviorContext(), when.getType());
			OJAnnotatedOperation evaluationMethod = new OJAnnotatedOperation(evaluatorName(event), new OJPathName("boolean"));
			evaluationMethod.getBody().addToStatements("return " + changeExpr);
			owner.addToOperations(evaluationMethod);
			OJPathName handler = handlerPathName(event);
			owner.addToImports(handler);
			operation.getBody().addToStatements(
					"getOutgoingEvents().add(new OutgoingEvent(this, new " + handler.getLast() + "(((NodeInstanceImpl)context.getNodeInstance()).getUniqueId())))");
		}else{
			operation.getBody().addToStatements("NO_CHANGE_EXPRESSION_SPECIFIED");
		}
	}
	public static String evaluatorName(INakedChangeEvent event){
		return "evaluate" + event.getMappingInfo().getJavaName().getCapped();
	}
	public static void addOutgoingEventManagement(OJClass ojClass){
		OJPathName pn = new OJPathName(IEventGenerator.class.getName());
		if(!ojClass.getImplementedInterfaces().contains(pn)){
			ojClass.addToImplementedInterfaces(pn);
			ojClass.addToImports(Set.class.getName());
			ojClass.addToImports(HashSet.class.getName());
			ojClass.addToImports(OutgoingEvent.class.getName());
			ojClass.addToImports(CancelledEvent.class.getName());
			OJPathName eventCancellationPath = new OJPathName(Set.class.getName());
			eventCancellationPath.addToElementTypes(new OJPathName(CancelledEvent.class.getName()));
			OJAnnotatedField field1 = OJUtil.addProperty(ojClass, "cancelledEvents", eventCancellationPath, true);
			field1.setInitExp("new HashSet<CancelledEvent>()");
			ojClass.addToImports(HashMap.class.getName());
			field1.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJPathName eventSetPath = new OJPathName(Set.class.getName());
			eventSetPath.addToElementTypes(new OJPathName(OutgoingEvent.class.getName()));
			OJAnnotatedField field2 = OJUtil.addProperty(ojClass, "outgoingEvents", eventSetPath, true);
			field2.setInitExp("new HashSet<OutgoingEvent>()");
			field2.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
	public static void implementTimeEventRequest(OJOperation operation,OJBlock block,INakedTimeEvent event, boolean businessTime){
		implementTimerRequest(operation, block, event, "this",businessTime);
	}
	public static void implementDeadlineRequest(OJOperation operation,OJBlock block,INakedDeadline event,String taskName){
		implementTimerRequest(operation, block, event, taskName,true);
	}
	private static void implementTimerRequest(OJOperation operation,OJBlock block,INakedTimer event,String targetExpression, boolean businessTime){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getWhen();
		OJPathName eventHandler = handlerPathName(event);
		owner.addToImports(eventHandler);
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getContext(), when.getType());
			// TODO add the timeSpecification INstanceSpecification values
			if(event.isRelative()){
				INakedEnumerationLiteral timeUnit = event.getTimeUnit();
				if(businessTime){
					String timeUnitConstant= timeUnit==null? "BUSINESSDAY":timeUnit.getName().toUpperCase();
					owner.addToImports("org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit");
					block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + ",new " + eventHandler.getLast() + "(" + whenExpr + ",BusinessTimeUnit."
							+ timeUnitConstant + ",((NodeInstanceImpl)context.getNodeInstance()).getUniqueId())))");
				}else{
					owner.addToImports(TimeUnit.class.getName());
					
					String timeUnitConstant= "DAY";
					if(timeUnit!=null && TimeUnit.lookup(timeUnit.getName().toUpperCase())!=null){
						timeUnitConstant= timeUnit.getName().toUpperCase();
					}
					block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + ",new " + eventHandler.getLast() + "(" + whenExpr + ",TimeUnit." + timeUnitConstant
							+ ",((NodeInstanceImpl)context.getNodeInstance()).getUniqueId())))");
				}
			}else{
				block.addToStatements("getOutgoingEvents().add(new OutgoingEvent(" + targetExpression + ",new " + eventHandler.getLast() + "(" + whenExpr
						+ ",((NodeInstanceImpl)context.getNodeInstance()).getUniqueId())))");
			}
		}else{
			block.addToStatements("NO_WHEN_EXPRESSION_SPECIFIED");
		}
	}
	public static void cancelTimer(OJBlock block,INakedTimer event,String targetExpression){
		block.addToStatements("getCancelledEvents().add(new CancelledEvent(" + targetExpression + ",\"" + event.getMappingInfo().getIdInModel() + "\"))");
	}
	public static void cancelChangeEvent(OJBlock block,INakedChangeEvent event){
		block.addToStatements("getCancelledEvents().add(new CancelledEvent(this,\"" + event.getMappingInfo().getIdInModel() + "\"))");
	}
	public static String getInvokerName(INakedOperation o){
		return o.getOwner().getMappingInfo().getJavaName().getAsIs() + o.getMappingInfo().getJavaName().getCapped() + o.getMappingInfo().getNakedUmlId() + "Invoker";
	}
	public static void requestEvents(OJAnnotatedOperation operation,Collection<INakedActivityNode> activityNodes,boolean businessCalendarAvailable){
		for(INakedActivityNode node:activityNodes){
			if(node instanceof INakedAcceptEventAction && node.getAllEffectiveIncoming().isEmpty()){
				INakedAcceptEventAction acceptEventAction = (INakedAcceptEventAction) node;
				for(INakedTrigger t:acceptEventAction.getTriggers()){
					if(t.getEvent() instanceof INakedTimeEvent){
						implementTimeEventRequest(operation, operation.getBody(), (INakedTimeEvent) t.getEvent(),businessCalendarAvailable);
					}else if(t.getEvent() instanceof INakedChangeEvent){
						implementChangeEventRequest(operation, (INakedChangeEvent) t.getEvent());
					}
				}
			}
		}
	}
	public static void cancelEvents(OJBlock block,Collection<INakedActivityNode> activityNodes){
		for(INakedActivityNode node:activityNodes){
			if(node instanceof INakedAcceptEventAction && node.getAllEffectiveIncoming().isEmpty()){
				INakedAcceptEventAction acceptEventAction = (INakedAcceptEventAction) node;
				Collection<INakedTrigger> triggers = acceptEventAction.getTriggers();
				for(INakedTrigger t:triggers){
					if(t.getEvent() instanceof INakedTimeEvent){
						cancelTimer(block, (INakedTimeEvent) t.getEvent(), "this");
					}else if(t.getEvent() instanceof INakedChangeEvent){
						cancelChangeEvent(block, (INakedChangeEvent) t.getEvent());
					}
				}
			}
		}
	}
}
