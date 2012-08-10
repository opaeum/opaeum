package org.opaeum.javageneration.jbpm5.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;
import org.hibernate.annotations.Type;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.activity.ActivityProcessImplementor;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.IProcessStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,CodeCleanup.class},after = {
		OperationAnnotator.class,ActivityProcessImplementor.class
/* Needs repeatable sequence in the ocl generating steps */
},before = CodeCleanup.class)
public class StateMachineImplementor extends AbstractJavaProcessVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine umlStateMachine){
		OJAnnotatedClass ojStateMachine = findJavaClass(umlStateMachine);
		addImports(ojStateMachine, umlStateMachine);
		OJUtil.addTransientProperty(ojStateMachine, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"), true).setVisibility(
				OJVisibilityKind.PROTECTED);
		addParameterDelegation(ojStateMachine, umlStateMachine);
		implementProcessInterfaceOperations(ojStateMachine, ojUtil.statePathname(umlStateMachine), umlStateMachine);
		OJOperation execute = implementExecute(ojStateMachine, umlStateMachine);
		execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		visitRegions(ojStateMachine, umlStateMachine.getRegions());
	}
	private void state(Vertex state){
		StateMap map = ojUtil.buildStateMap(state);
		OJAnnotatedClass ojStateMachine = findJavaClass(EmfStateMachineUtil.getStateMachine(state));
		if(state instanceof State){
			OJOperation getter = new OJAnnotatedOperation("get" + EmfStateMachineUtil.getStatePath(state).replace("::", "_"));
			getter.setReturnType(new OJPathName("boolean"));
			ojStateMachine.addToOperations(getter);
			if(EmfBehaviorUtil.isClassifierBehavior(EmfStateMachineUtil.getStateMachine(state))){
				OJAnnotatedClass context = findJavaClass(EmfStateMachineUtil.getStateMachine(state).getContext());
				OJOperation copy = getter.getCopy();
				copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." + copy.getName() + "()");
				context.addToOperations(copy);
			}
			getter.getBody().addToStatements(
					"return isStepActive(" + ojStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
		}
		implementOnEntryIfRequired(ojStateMachine, state, map);
		implementOnExitIfRequired(ojStateMachine, state, map);
		if(state instanceof Pseudostate
				&& (((Pseudostate) state).getKind() == PseudostateKind.SHALLOW_HISTORY_LITERAL || ((Pseudostate) state).getKind() == PseudostateKind.DEEP_HISTORY_LITERAL)){
			String fieldName = state.getName();
			OJPathName statePat = new OJPathName(ojStateMachine.getPathName().toJavaString() + "State");
			OJAnnotatedField historyField = OJUtil.addPersistentProperty(ojStateMachine, fieldName, statePat, true);
			OJAnnotationValue enumeratd = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			enumeratd.putAttribute("type", ojStateMachine.getName() + "StateResolver");
			historyField.putAnnotation(enumeratd);
		}
	}
	private void implementOnExitIfRequired(OJAnnotatedClass ojStateMachine,Vertex vertex,StateMap map){
		if(vertex instanceof State && EmfStateMachineUtil.doesWorkOnExit((State) vertex)){
			State state = (State) vertex;
			OJAnnotatedOperation onExit = new OJAnnotatedOperation(map.getOnExitMethod());
			ojStateMachine.addToOperations(onExit);
			onExit.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getExit() != null){
				implementBehaviorOn(state, state.getExit(), onExit);
			}
			for(Transition t:state.getOutgoings()){
				Collection<Trigger> triggers = t.getTriggers();
				for(Trigger trigger:triggers){
					if(trigger != null){
						if(trigger.getEvent() instanceof TimeEvent){
							EventUtil.cancelTimer(onExit.getBody(), (TimeEvent) trigger.getEvent(), "this");
						}else if(trigger.getEvent() instanceof ChangeEvent){
							EventUtil.cancelChangeEvent(onExit.getBody(), (ChangeEvent) trigger.getEvent());
						}
					}
				}
			}
		}
	}
	private void implementOnEntryIfRequired(OJAnnotatedClass ojStateMachine,Vertex vertex,StateMap map){
		if(vertex instanceof State && EmfStateMachineUtil.doesWorkOnEntry(vertex)){
			State state = (State) vertex;
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation(map.getOnEntryMethod());
			ojStateMachine.addToOperations(onEntry);
			onEntry.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getEntry() != null){
				implementBehaviorOn(state, state.getEntry(), onEntry);
			}
			Pseudostate historyPeer = EmfStateMachineUtil.getHistoryPeer(state);
			if(historyPeer != null){
				String setter = "set" + NameConverter.capitalize(historyPeer.getName());
				if(state instanceof FinalState){
					onEntry.getBody().addToStatements(setter + "(null)");
				}else{
					onEntry.getBody().addToStatements(setter + "(" + ojStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
				}
			}
			if(state.getDoActivity() != null){
				implementBehaviorOn(state, state.getDoActivity(), onEntry);
			}
			OJBlock defaultTransitions = onEntry.getBody();
			if(state instanceof Pseudostate && ((Pseudostate) state).getKind() == PseudostateKind.SHALLOW_HISTORY_LITERAL){
				String stateGetter = "get" + NameConverter.capitalize(state.getName());
				OJAnnotatedField umlState = new OJAnnotatedField("umlState", Jbpm5Util.UML_NODE_INSTANCE);
				ojStateMachine.addToImports(Jbpm5Util.UML_NODE_INSTANCE);
				umlState.setInitExp("(UmlNodeInstance)context.getNodeInstance()");
				onEntry.getBody().addToLocals(umlState);
				OJIfStatement ifNotNul = new OJIfStatement(stateGetter + "()!=null");
				onEntry.getBody().addToStatements(ifNotNul);
				ifNotNul.getThenPart().addToStatements("umlState.transitionToNode(" + stateGetter + "().getId(),null)");
				defaultTransitions = new OJBlock();
				ifNotNul.setElsePart(defaultTransitions);
			}
			Collection<Transition> completionTransitions = EmfStateMachineUtil.getCompletionTransitions( state);
			if(completionTransitions.size() > 0 && !(state.isComposite() || state.isOrthogonal())){
				defaultTransitions.addToStatements(eventUtil.getEventConsumerName(state) + "()");
			}
			if(state instanceof FinalState && state.getContainer().getOwner() instanceof State){
				ojStateMachine.addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
				onEntry
						.getBody()
						.addToStatements(
								"((NodeInstanceContainer)context.getNodeInstance().getNodeInstanceContainer()).removeNodeInstance((NodeInstanceImpl)context.getNodeInstance())");
				onEntry
						.getBody()
						.addToStatements(
								"((NodeInstanceContainer) context.getNodeInstance().getNodeInstanceContainer()).nodeInstanceCompleted((NodeInstanceImpl)context.getNodeInstance(), null)");
			}
		}
	}
	private void implementBehaviorOn(State state,Behavior behavior,OJAnnotatedOperation onEntry){
		if(behavior instanceof Activity){
			SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
			impl.initialize(javaModel, config, textWorkspace, workspace, ojUtil);
			impl.setWorkspace(workspace);
			impl.implementActivityOn((Activity) behavior, onEntry);
		}else if(behavior instanceof OpaqueBehavior){
			OpaqueBehavior b = (OpaqueBehavior) behavior;
			OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext(b);
			Classifier voidType = getLibrary().getOclLibrary().getOclVoid();
			onEntry.getBody().addToStatements(valueSpecificationUtil.expressOcl(oclBehaviorContext, onEntry, voidType));
		}
	};
	private void transition(OJAnnotatedClass ojStateMachine,Transition transition){
		if(EmfStateMachineUtil.hasGuard(transition) && transition.getSource() instanceof Pseudostate
				&& ((Pseudostate) transition.getSource()).getKind() == PseudostateKind.CHOICE_LITERAL){
			OJOperation getter = new OJAnnotatedOperation(Jbpm5Util.getGuardMethod(transition.getSource(), transition));
			getter.setReturnType(new OJPathName("boolean"));
			ojStateMachine.addToOperations(getter);
			Classifier booleanType = getLibrary().getBooleanType();
			OpaqueExpressionContext context = getLibrary().getOclExpressionContext((OpaqueExpression) transition.getGuard().getSpecification());
			if(!context.hasErrors()){
				String expression = valueSpecificationUtil.expressOcl(context, getter, booleanType);
				getter.getBody().addToStatements("return " + expression);
			}
		}
	};
	private void visitRegions(OJAnnotatedClass ojStateMachine,Collection<Region> regions){
		for(Region r:regions){
			for(Vertex s:r.getSubvertices()){
				this.state(s);
				if(s instanceof State){
					visitRegions(ojStateMachine, ((State) s).getRegions());
				}
			}
			for(Transition t:r.getTransitions()){
				this.transition(ojStateMachine, t);
			}
		}
	}
	private void addImports(OJClass javaStateMachine,StateMachine sm){
		javaStateMachine.addToImports(new OJPathName(Set.class.getName()));
		javaStateMachine.addToImports(new OJPathName(HashSet.class.getName()));
		javaStateMachine.addToImports(new OJPathName(List.class.getName()));
		javaStateMachine.addToImports(new OJPathName(ArrayList.class.getName()));
		javaStateMachine.addToImports(new OJPathName(Timestamp.class.getName()));
		if(!sm.conformsTo(getLibrary().getAbstractRequest())){
			javaStateMachine.addToImports(IProcessObject.class.getName());
		}
		javaStateMachine.addToImports(IProcessStep.class.getName());
	}
	@Override
	protected Collection<? extends NamedElement> getTopLevelFlows(Classifier umlBehavior){
		return ((StateMachine) umlBehavior).getRegions();
	}
}
