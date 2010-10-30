package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
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
import net.sf.nakeduml.seam.TimeEventDispatcher;
import net.sf.nakeduml.util.TimeUnit;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class BpmUtil{
	public static String stepLiteralName(INakedElement s){
		return (s).getMappingInfo().getJavaName().getAsIs().toUpperCase();
	}
	public static OJPathName asyncInterfaceOf(INakedClassifier target){
		OJPathName result = OJUtil.classifierPathname(target);
		String name = "IAsync" + result.getLast();
		result=result.getHead();
		result.addToNames(name);
		return result;
	}
	public static OJPathName getJbpm5Environment() {
		return UtilityCreator.getUtilPathName().append("Jbpm5Environment");
	}
	public static OJPathName getNodeInstance() {
		return new OJPathName("org.jbpm.workflow.instance.NodeInstance");
	}
	public static String generateProcessName(IParameterOwner parameterOwner) {
		return parameterOwner.getOwnerElement().getMappingInfo().getPersistentName() + "_"
				+ parameterOwner.getMappingInfo().getPersistentName();
	}
	public static String getArtificialJoinName(INakedElement target) {
		return "join_for_"+target.getMappingInfo().getPersistentName();
	}
	public static String getGuardMethod(INakedTransition transition) {
		return "is" +transition.getSource().getMappingInfo().getJavaName().getCapped() +transition.getMappingInfo().getJavaName().getCapped();
	}
	public static void implementTimeEvent(OJOperation operation, INakedTimeEvent event, INakedElement source,
			Collection<? extends GuardedFlow> outgoing) {
		OJAnnotatedClass owner = (OJAnnotatedClass) operation.getOwner();
		for (GuardedFlow out : outgoing) {
			owner.addToImports(TimeEventDispatcher.class.getName());
			INakedValueSpecification when = event.getWhen();
			if (when != null) {
				String whenExpr = ValueSpecificationUtil.expressValue(operation, when, event.getContext(), when.getType());
				String callBackMethodName = "on_" + event.getMappingInfo().getPersistentName();
				if (event.isRelative()) {
					owner.addToImports(TimeUnit.class.getName());
					TimeUnit timeUnit = event.getTimeUnit() == null ? TimeUnit.BUSINESS_DAY : event.getTimeUnit();
					operation.getBody().addToStatements(
							"TimeEventDispatcher.getInstance().scheduleEvent(this,\"" + callBackMethodName + "\"," + whenExpr + ",TimeUnit."
									+ timeUnit.name() + ")");
				} else {
					operation.getBody().addToStatements(
							"TimeEventDispatcher.getInstance().scheduleEvent(this,\"" + callBackMethodName + "\"," + whenExpr + ")");
				}
			} else {
				operation.getBody().addToStatements("TimeEventDispatcher.getInstance().createTimer(NO WHEN EXPRESSION SPECIFIED)");
			}
		}
	}
	public static void cancelTimer(OJOperation cancel, INakedTimeEvent event) {
		String callBackMethodName = "on_" + event.getMappingInfo().getPersistentName();
		cancel.getBody().addToStatements(
				"TimeEventDispatcher.getInstance().cancelTimer(this,\"" + callBackMethodName +"\")");
	}
	public static String getArtificialForkName(IRegionOwner owner) {
		return "fork_for_" + owner.getMappingInfo().getPersistentName();
	}
}
