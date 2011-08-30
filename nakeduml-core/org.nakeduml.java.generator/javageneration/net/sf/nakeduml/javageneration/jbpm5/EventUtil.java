package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.statemachines.INakedState;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.TimeUnit;
import org.nakeduml.runtime.event.IEventHandler;

public class EventUtil{
	public static String getEventHandlerName(INakedElement e){
		if(e instanceof INakedEvent){
			return e.getMappingInfo().getJavaName().getDecapped() + "Occurred";
		}else if(e instanceof INakedState){
			return "on" + e.getMappingInfo().getJavaName().getCapped() + "Completed";
		}else{
			return "on" + e.getMappingInfo().getJavaName().getCapped();
		}
	}
	public  static OJPathName handlerPathName(INakedSignal s){
		return new OJPathName(s.getMappingInfo().getQualifiedJavaName() + "Handler");
	}
	public  static OJPathName handlerPathName(INakedOperation s){
		return OJUtil.packagePathname(s.getOwner()).append(s.getMappingInfo().getJavaName().getCapped()+"Handler");
	}

	public static OJPathName handlerPathName(INakedEvent e){
		return OJUtil.packagePathname(e.getContext()).append(e.getMappingInfo().getJavaName().getCapped() + "Handler");
	}
	public static void implementChangeEventRequest(OJOperation operation,INakedChangeEvent event){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getChangeExpression();
		EventUtil.addOutgoingEventManagement(owner);
		if(when != null){
			String changeExpr = ValueSpecificationUtil.expressValue(operation, when, event.getBehaviorContext(), when.getType());
			OJAnnotatedOperation evaluationMethod = new OJAnnotatedOperation(evaluatorName(event), new OJPathName("boolean"));
			evaluationMethod.getBody().addToStatements("return " + changeExpr);
			owner.addToOperations(evaluationMethod);
			OJPathName handler = handlerPathName(event);
			owner.addToImports(handler);
			operation.getBody().addToStatements("getOutgoingEvents().put(this, new " + handler.getLast() + "(((NodeInstanceImpl)context.getNodeInstance()).getUniqueId()))");
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
			OJPathName eventCancellationPath = new OJPathName(Map.class.getName());
			eventCancellationPath .addToElementTypes(new OJPathName("Object"));
			eventCancellationPath .addToElementTypes(new OJPathName("String"));
			OJAnnotatedField field1 = OJUtil.addProperty(ojClass, "cancelledEvents", eventCancellationPath , true);
			field1.setInitExp("new HashMap<Object,String>()");
			ojClass.addToImports(HashMap.class.getName());
			field1.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJPathName eventSetPath = new OJPathName(Map.class.getName());
			eventSetPath.addToElementTypes(new OJPathName("Object"));
			eventSetPath.addToElementTypes(new OJPathName(IEventHandler.class.getName()));
			OJAnnotatedField field2 = OJUtil.addProperty(ojClass, "outgoingEvents", eventSetPath, true);
			field2.setInitExp("new HashMap<Object,IEventHandler>()");
			ojClass.addToImports(HashMap.class.getName());
			field2.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
	public static void implementTimeEventRequest(OJOperation operation,OJBlock block,INakedTimeEvent event){
		implementTimerRequest(operation, block, event, "this");
	}
	public static void implementDeadlineRequest(OJOperation operation,OJBlock block,INakedDeadline event,String taskName){
		implementTimerRequest(operation, block, event, taskName);
	}
	private static void implementTimerRequest(OJOperation operation,OJBlock block,INakedTimer event,String targetExpression){
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		INakedValueSpecification when = event.getWhen();
		OJPathName eventHandler = handlerPathName(event);
		owner.addToImports(eventHandler);
		if(when != null){
			String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getContext(), when.getType());
			// TODO add the timeSpecification INstanceSpecification values
			if(event.isRelative()){
				owner.addToImports(TimeUnit.class.getName());
				TimeUnit timeUnit = event.getTimeUnit() == null ? TimeUnit.BUSINESS_DAY : event.getTimeUnit();
				block.addToStatements("getOutgoingEvents().put(" + targetExpression + ",new " + eventHandler.getLast() + "(" + whenExpr + ",TimeUnit." + timeUnit.name()+",((NodeInstanceImpl)context.getNodeInstance()).getUniqueId()))");
			}else{
				block.addToStatements("getOutgoingEvents().put(" + targetExpression + ",new " + eventHandler.getLast() + "(" + whenExpr + ",((NodeInstanceImpl)context.getNodeInstance()).getUniqueId()))");
			}
		}else{
			block.addToStatements("NO_WHEN_EXPRESSION_SPECIFIED");
		}
		addOutgoingEventManagement(owner);
	}
	public static void cancelTimer(OJBlock block,INakedTimer event,String targetExpression){
		block.addToStatements("getCancelledEvents().put(" + targetExpression + ",\"" + event.getMappingInfo().getIdInModel() + "\")");
	}
	public static void cancelChangeEvent(OJBlock block,INakedChangeEvent event){
		block.addToStatements("getCancelledEvents().put(this,\"" + event.getMappingInfo().getIdInModel() + "\")");
	}
	public static String getInvokerName(INakedOperation o){
		return o.getOwner().getMappingInfo().getJavaName().getAsIs() + o.getMappingInfo().getJavaName().getCapped() + o.getMappingInfo().getNakedUmlId() + "Invoker";
	}
	public static void requestEvents(OJAnnotatedOperation operation,Collection<INakedActivityNode> activityNodes){
		for(INakedActivityNode node:activityNodes){
			if(node instanceof INakedAcceptEventAction && node.getAllEffectiveIncoming().isEmpty()){
				INakedAcceptEventAction acceptEventAction = (INakedAcceptEventAction) node;
				if(acceptEventAction.getTrigger().getEvent() instanceof INakedTimeEvent){
					implementTimeEventRequest(operation, operation.getBody(), (INakedTimeEvent) acceptEventAction.getTrigger().getEvent());
				}else if(acceptEventAction.getTrigger().getEvent() instanceof INakedChangeEvent){
					implementChangeEventRequest(operation, (INakedChangeEvent) acceptEventAction.getTrigger().getEvent());
				}
			}
		}
	}
	public static void cancelEvents(OJBlock block,Collection<INakedActivityNode> activityNodes){
		for(INakedActivityNode node:activityNodes){
			if(node instanceof INakedAcceptEventAction && node.getAllEffectiveIncoming().isEmpty()){
				INakedAcceptEventAction acceptEventAction = (INakedAcceptEventAction) node;
				if(acceptEventAction.getTrigger().getEvent() instanceof INakedTimeEvent){
					cancelTimer(block, (INakedTimeEvent) acceptEventAction.getTrigger().getEvent(), "this");
				}else if(acceptEventAction.getTrigger().getEvent() instanceof INakedChangeEvent){
					cancelChangeEvent(block, (INakedChangeEvent) acceptEventAction.getTrigger().getEvent());
				}
			}
		}
	}
}
