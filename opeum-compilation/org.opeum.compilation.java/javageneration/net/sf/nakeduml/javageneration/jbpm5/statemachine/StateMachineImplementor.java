package org.opeum.javageneration.jbpm5.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.NakedStateMap;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opeum.javageneration.jbpm5.AbstractJavaProcessVisitor;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.oclexpressions.CodeCleanup;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.linkage.ProcessIdentifier;
import org.opeum.linkage.StateMachineUtil;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.statemachines.INakedRegion;
import org.opeum.metamodel.statemachines.INakedState;
import org.opeum.metamodel.statemachines.INakedStateMachine;
import org.opeum.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.hibernate.annotations.Type;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.runtime.domain.IProcessObject;
import org.opeum.runtime.domain.IProcessStep;
import org.opeum.runtime.domain.UmlNodeInstance;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		OperationAnnotator.class,ProcessIdentifier.class,CompositionEmulator.class,NakedParsedOclStringResolver.class,CodeCleanup.class
},after = {
	OperationAnnotator.class
},before = CodeCleanup.class)
public class StateMachineImplementor extends AbstractJavaProcessVisitor{
	private ThreadLocal<OJAnnotatedClass> javaStateMachine=new ThreadLocal<OJAnnotatedClass>();
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine){
		setJavaStateMachine(findJavaClass(umlStateMachine));
		addImports(getJavaStateMachine());
		implementProcessInterfaceOperations(getJavaStateMachine(), new OJPathName(umlStateMachine.getMappingInfo().getQualifiedJavaName() + "State"), umlStateMachine);
		OJOperation execute = implementExecute(getJavaStateMachine(), umlStateMachine);
		execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
		visitRegions(umlStateMachine.getRegions());
	}
	private void state(INakedState state){
		NakedStateMap map = new NakedStateMap(state);
		if(state.getKind().isRestingState()){
			OJOperation getter = new OJAnnotatedOperation("get" + state.getStatePath().toString().replace("::", "_"));
			getter.setReturnType(new OJPathName("boolean"));
			getJavaStateMachine().addToOperations(getter);
			if(state.getStateMachine().isClassifierBehavior()){
				OJAnnotatedClass context = findJavaClass(state.getStateMachine().getContext());
				OJOperation copy = getter.getCopy();
				copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." + copy.getName() + "()");
				context.addToOperations(copy);
			}
			getter.getBody().addToStatements("return isStepActive(" + getJavaStateMachine().getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
		}
		implementOnEntryIfRequired(state, map);
		implementOnExitIfRequired(state, map);
		if(state.getKind().isShallowHistory() || state.getKind().isDeepHistory()){
			String fieldName = state.getMappingInfo().getJavaName().getAsIs();
			OJPathName statePat = new OJPathName(getJavaStateMachine().getPathName().toJavaString() + "State");
			OJAnnotatedField historyField = OJUtil.addProperty(getJavaStateMachine(), fieldName, statePat, true);
			OJAnnotationValue enumeratd = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			enumeratd.putAttribute("type", getJavaStateMachine().getName() + "StateResolver");
			historyField.putAnnotation(enumeratd);
		}
	}
	private void implementOnExitIfRequired(INakedState state,NakedStateMap map){
		if(StateMachineUtil.doesWorkOnExit(state)){
			OJAnnotatedOperation onExit = new OJAnnotatedOperation(map.getOnExitMethod());
			getJavaStateMachine().addToOperations(onExit);
			onExit.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getExit() != null){
				implementBehaviorOn(state, state.getExit(), onExit);
			}
			for(INakedTransition t:state.getOutgoing()){
				Collection<INakedTrigger> triggers = t.getTriggers();
				for(INakedTrigger trigger:triggers){
					if(trigger != null){
						if(trigger.getEvent() instanceof INakedTimeEvent){
							EventUtil.cancelTimer(onExit.getBody(), (INakedTimeEvent) trigger.getEvent(), "this");
						}else if(trigger.getEvent() instanceof INakedChangeEvent){
							EventUtil.cancelChangeEvent(onExit.getBody(), (INakedChangeEvent)trigger.getEvent());
						}
					}
				}
			}
		}
	}
	private void implementOnEntryIfRequired(INakedState state,NakedStateMap map){
		if(StateMachineUtil.doesWorkOnEntry(state)){
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation(map.getOnEntryMethod());
			getJavaStateMachine().addToOperations(onEntry);
			onEntry.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getEntry() != null){
				implementBehaviorOn(state, state.getEntry(), onEntry);
			}
			INakedState historyPeer = StateMachineUtil.getHistoryPeer(state);
			if(historyPeer != null && state.getKind().isRestingState()){
				String setter = "set" + historyPeer.getMappingInfo().getJavaName().getCapped().getAsIs();
				if(state.getKind().isFinal()){
					onEntry.getBody().addToStatements(setter + "(null)");
				}else{
					onEntry.getBody().addToStatements(setter + "(" + getJavaStateMachine().getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
				}
			}
			if(state.getDoActivity() != null){
				implementBehaviorOn(state, state.getDoActivity(), onEntry);
			}
			OJBlock defaultTransitions = onEntry.getBody();
			if(state.getKind().isShallowHistory()){
				String stateGetter = "get" + state.getMappingInfo().getJavaName().getCapped().getAsIs();
				OJAnnotatedField umlState = new OJAnnotatedField("umlState", new OJPathName("org.opeum.runtime.domain.UmlNodeInstance"));
				getJavaStateMachine().addToImports(new OJPathName(UmlNodeInstance.class.getName()));
				umlState.setInitExp("(UmlNodeInstance)context.getNodeInstance()");
				onEntry.getBody().addToLocals(umlState);
				OJIfStatement ifNotNul = new OJIfStatement(stateGetter + "()!=null");
				onEntry.getBody().addToStatements(ifNotNul);
				ifNotNul.getThenPart().addToStatements("umlState.transitionToNode(" + stateGetter + "().getId(),null)");
				defaultTransitions = new OJBlock();
				ifNotNul.setElsePart(defaultTransitions);
			}
			Collection<INakedTransition> completionTransitions = state.getCompletionTransitions();
			if(completionTransitions.size() > 0 && !(state.getKind().isComposite() || state.getKind().isOrthogonal())){
				defaultTransitions.addToStatements(EventUtil.getEventConsumerName(state.getCompletionEvent()) + "()");
			}
			if(state.getKind().isFinal() && state.getContainer().getRegionOwner() instanceof INakedState){
				getJavaStateMachine().addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
				onEntry.getBody().addToStatements(
						"((NodeInstanceContainer)context.getNodeInstance().getNodeInstanceContainer()).removeNodeInstance((NodeInstanceImpl)context.getNodeInstance())");
				onEntry.getBody()
						.addToStatements(
								"((NodeInstanceContainer) context.getNodeInstance().getNodeInstanceContainer()).nodeInstanceCompleted((NodeInstanceImpl)context.getNodeInstance(), null)");
			}
		}
	}
	private void implementBehaviorOn(INakedState state,INakedBehavior behavior,OJAnnotatedOperation onEntry){
		if(behavior instanceof INakedActivity){
			SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
			impl.initialize(javaModel, config, textWorkspace, workspace);
			impl.setWorkspace(workspace);
			impl.implementActivityOn((INakedActivity) behavior, onEntry);
		}else if(behavior instanceof INakedOpaqueBehavior){
			INakedOpaqueBehavior b = (INakedOpaqueBehavior) behavior;
			IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
			onEntry.getBody().addToStatements(ValueSpecificationUtil.expressValue(onEntry, b.getBody(), state.getStateMachine(), voidType));
		}
	};
	private void transition(INakedTransition transition){
		if(transition.hasGuard() && transition.getGuard().isValidOclValue() && transition.getSource().getKind().isChoice()){
			OJOperation getter = new OJAnnotatedOperation(Jbpm5Util.getGuardMethod(transition));
			getter.setReturnType(new OJPathName("boolean"));
			getJavaStateMachine().addToOperations(getter);
			IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			String expression = ValueSpecificationUtil.expressValue(getter, transition.getGuard(), transition.getStateMachine(), booleanType);
			getter.getBody().addToStatements("return " + expression);
		}
	};
	private void visitRegions(List<INakedRegion> regions){
		for(INakedRegion r:regions){
			for(INakedState s:r.getStates()){
				this.state(s);
				visitRegions(s.getRegions());
			}
			for(INakedTransition t:r.getTransitions()){
				this.transition(t);
			}
		}
	}
	private void addImports(OJClass javaStateMachine){
		javaStateMachine.addToImports(new OJPathName(Set.class.getName()));
		javaStateMachine.addToImports(new OJPathName(HashSet.class.getName()));
		javaStateMachine.addToImports(new OJPathName(List.class.getName()));
		javaStateMachine.addToImports(new OJPathName(ArrayList.class.getName()));
		javaStateMachine.addToImports(new OJPathName(Timestamp.class.getName()));
		javaStateMachine.addToImports(IProcessObject.class.getName());
		javaStateMachine.addToImports(IProcessStep.class.getName());
	}
	@Override
	protected Collection<? extends INakedElement> getTopLevelFlows(INakedBehavior umlBehavior){
		return ((INakedStateMachine) umlBehavior).getRegions();
	}
	private OJAnnotatedClass getJavaStateMachine(){
		return javaStateMachine.get();
	}
	private void setJavaStateMachine(OJAnnotatedClass javaStateMachine){
		this.javaStateMachine.set(javaStateMachine);
	}
}
