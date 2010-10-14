package net.sf.nakeduml.javageneration.bpm.actions;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.util.TimeUnit;
import nl.klasse.octopus.oclengine.IOclEngine;

public class AcceptEventActionBuilder extends JbpmActionBuilder<INakedAcceptEventAction> {
	public AcceptEventActionBuilder(IOclEngine oclEngine, INakedAcceptEventAction node) {
		super(oclEngine, node);
	}

	@Override
	public void implementActionOn(OJOperation operation) {
		if (node.getActionType().isAcceptTimeEventAction()) {
			implementTimeEvent(operation, (INakedTimeEvent) node.getEvent(), node, node.getAllEffectiveOutgoing());
			OJOperation cancel = new OJAnnotatedOperation();
			cancel.setName("cancel" + node.getMappingInfo().getJavaName().getCapped());
			operation.getOwner().addToOperations(cancel);
			cancelTimer(cancel, (INakedTimeEvent) node.getEvent());
		}
	}

	public static void implementTimeEvent(OJOperation operation, INakedTimeEvent event, INakedElement source,
			Collection<? extends GuardedFlow> outgoing) {
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		OJOperation getSchedulerService = OJUtil.findOperation(owner, "getSchedulerService");
		if (getSchedulerService == null) {
			OJAnnotatedField field = OJUtil.addProperty(owner, "schedulerService", new OJPathName("org.jbpm.scheduler.SchedulerService"),
					true);
			field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			OJIfStatement ifNull = new OJIfStatement("this.schedulerService==null",
					"return (SchedulerService)Services.getCurrentService(Services.SERVICENAME_SCHEDULER)");
			ifNull.setElsePart(new OJBlock());
			ifNull.getElsePart().addToStatements("return this.schedulerService");
			getSchedulerService = OJUtil.findOperation(owner, "getSchedulerService");
			getSchedulerService.setBody(new OJBlock());
			getSchedulerService.getBody().addToStatements(ifNull);
		}
		for (GuardedFlow out : outgoing) {
			owner.addToImports("org.jbpm.graph.def.Action");
			owner.addToImports("org.jbpm.scheduler.SchedulerService");
			owner.addToImports("org.jbpm.svc.Services");
			OJAnnotatedField timer = new OJAnnotatedField();
			String timerName = "timer" + out.getMappingInfo().getJavaName();
			timer.setName(timerName);
			OJPathName timerPathName = new OJPathName("org.jbpm.job.Timer");
			owner.addToImports(timerPathName);
			owner.addToImports(new OJPathName("org.jbpm.graph.exe.ExecutionContext"));
			timer.setType(timerPathName);
			timer.setInitExp("new Timer(ExecutionContext.currentExecutionContext().getToken())");
			operation.getBody().addToLocals(timer);
			operation.getBody().addToStatements(timerName + ".setName(\"" + timerName + "\")");
			operation.getBody()
					.addToStatements(timerName + ".setGraphElement(ExecutionContext.currentExecutionContext().getEventSource())");
			operation.getBody().addToStatements(timerName + ".setAction(new Action())");
			// apply persistent name semantics - a revision may occur after the
			// original event was logged
			operation.getBody().addToStatements(
					timerName + ".getAction().setActionExpression(\"#{process.on_"
							+ event.getMappingInfo().getPersistentName().getWithoutId() + "}\")");
			INakedValueSpecification when = event.getWhen();
			if (when != null) {
				String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getContext(), when.getType());
				if (event.isRelative()) {
					// TODO make the use of the jBpm calendar configurable -
					// alternative is
					// workingHours
					owner.addToImports(new OJPathName("org.jbpm.calendar.BusinessCalendar"));
					owner.addToImports(new OJPathName("org.jbpm.calendar.Duration"));
					operation.getBody().addToStatements("BusinessCalendar b=new BusinessCalendar()");
					operation.getBody().addToStatements(
							timerName + ".setDueDate(b.add(new java.util.Date(), new Duration(\"\"+" + whenExpr + " + \" "
									+ toJBPMTimeUnit(event.getTimeUnit()) + "\")))");
				} else {
					operation.getBody().addToStatements(timerName + ".setDueDate(" + whenExpr + ")");
				}
			}else{
				operation.getBody().addToStatements(timerName + ".setDueDate(NO WHEN EXPRESSION SPECIFIED)");
			}
			operation.getBody().addToStatements("getSchedulerService().createTimer(" + timerName + ")");
		}
	}

	private static String toJBPMTimeUnit(TimeUnit t) {
		switch (t) {
		case ACTUAL_HOUR:
			return "hour";
		case ACTUAL_MINUTE:
			return "minute";
		case CALENDAR_DAY:
			return "day";
		case CALENDAR_WEEK:
			return "week";
		case CALENDAR_MONTH:
			return "month";
		case BUSINESS_MINUTE:
			return "business minute";
		case BUSINESS_HOUR:
			return "business hour";
		case BUSINESS_DAY:
			return "business day";
		case BUSINESS_WEEK:
			return "business week";
		}
		return "hour";
	}

	public static void cancelTimer(OJOperation cancel, INakedTimeEvent event) {
		cancel.getBody().addToStatements(
				"getSchedulerService().deleteTimersByName(\"" + event.getMappingInfo().getPersistentName().getWithoutId()
						+ "\",ExecutionContext.currentExecutionContext().getToken())");
	}
}
