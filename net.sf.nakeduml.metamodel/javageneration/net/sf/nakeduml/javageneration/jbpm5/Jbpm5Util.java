package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.jbpm.TimeEventDispatcher;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.IRegionOwner;
import net.sf.nakeduml.util.TimeUnit;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class Jbpm5Util {
	public static String stepLiteralName(INakedElement s) {
		return (s).getMappingInfo().getJavaName().getAsIs().toUpperCase();
	}

	public static OJPathName asyncInterfaceOf(INakedClassifier target) {
		OJPathName result = OJUtil.classifierPathname(target);
		String name = "IAsync" + result.getLast();
		result = result.getHead();
		result.addToNames(name);
		return result;
	}

	public static OJPathName getJbpmKnowledgeSession() {
		return new OJPathName("org.nakeduml.jbpm5.JbpmKnowledgeSession");
	}

	public static OJPathName getNodeInstance() {
		return new OJPathName("org.jbpm.workflow.instance.impl.NodeInstanceImpl");
	}

	public static String generateProcessName(IParameterOwner parameterOwner) {
		return parameterOwner.getOwnerElement().getMappingInfo().getPersistentName() + "_"
				+ parameterOwner.getMappingInfo().getPersistentName();
	}

	public static String getArtificialJoinName(INakedElement target) {
		return "join_for_" + target.getMappingInfo().getPersistentName();
	}

	public static String getGuardMethod(GuardedFlow t) {
		return "is" + t.getSource().getMappingInfo().getJavaName().getCapped() + t.getMappingInfo().getJavaName().getCapped();
	}

	public static void implementTimeEvent(OJOperation operation, INakedTimeEvent event, INakedElement source,
			Collection<? extends GuardedFlow> outgoing) {
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		for (GuardedFlow out : outgoing) {
			owner.addToImports(TimeEventDispatcher.class.getName());
			INakedValueSpecification when = event.getWhen();
			if (when != null) {
				String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getContext(), when.getType());
				String callBackMethodName = getTimerCallbackMethodName(event);
				if (event.isRelative()) {
					owner.addToImports(TimeUnit.class.getName());
					TimeUnit timeUnit = event.getTimeUnit() == null ? TimeUnit.BUSINESS_DAY : event.getTimeUnit();
					operation.getBody().addToStatements(
							"TimeEventDispatcher.getInstance().scheduleEvent(this,\"" + callBackMethodName + "\"," + whenExpr
									+ ",TimeUnit." + timeUnit.name() + ")");
				} else {
					operation.getBody().addToStatements(
							"TimeEventDispatcher.getInstance().scheduleEvent(this,\"" + callBackMethodName + "\"," + whenExpr + ")");
				}
			} else {
				operation.getBody().addToStatements("TimeEventDispatcher.getInstance().createTimer(NO WHEN EXPRESSION SPECIFIED)");
			}
		}
	}

	public static String getTimerCallbackMethodName(INakedTimeEvent event) {
		return "on_" + event.getMappingInfo().getPersistentName();
	}

	public static void cancelTimer(OJOperation cancel, INakedTimeEvent event) {
		String callBackMethodName = getTimerCallbackMethodName(event);
		cancel.getBody().addToStatements("TimeEventDispatcher.getInstance().cancelTimer(this,\"" + callBackMethodName + "\")");
	}

	public static String getArtificialForkName(INakedElement owner) {
		return "fork_for_" + owner.getMappingInfo().getPersistentName();
	}

	public static OJPathName getExceptionHolder() {
		return new OJPathName("net.sf.nakeduml.util.ExceptionHolder");
	}

	public static String endNodeFieldNameFor(INakedElement flow) {
		return "endNodeIn" + flow.getMappingInfo().getJavaName();
	}

	public static OJPathName getWorkflowProcesInstance() {
		return new OJPathName("org.jbpm.workflow.instance.WorkflowProcessInstance");
	}

	public static OJPathName getWorkflowProcessImpl() {
		return new OJPathName("org.jbpm.workflow.core.impl.WorkflowProcessImpl");
	}

	public static OJPathName getNode() {
		return new OJPathName("org.jbpm.workflow.core.impl.NodeImpl");
	}
}
