package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractJavaProcessVisitor;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.StateMachineUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.hibernate.annotations.Type;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.UmlNodeInstance;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		PersistenceStep.class,OclExpressionExecution.class,ProcessIdentifier.class
},after = {
		PersistenceStep.class,OclExpressionExecution.class
})
public class StateMachineImplementor extends AbstractJavaProcessVisitor{
	private OJAnnotatedClass javaStateMachine;
	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel m){
	}
	@VisitBefore(matchSubclasses = true)
	public void state(INakedState state){
		NakedStateMap map = new NakedStateMap(state);
		if(state.getKind().isRestingState()){
			OJOperation getter = new OJAnnotatedOperation();
			getter.setReturnType(new OJPathName("boolean"));
			getter.setName("get" + state.getStatePath().toString().replace("::","_"));
			javaStateMachine.addToOperations(getter);
			if(state.getStateMachine().isClassifierBehavior()){
				OJAnnotatedClass context = findJavaClass(state.getStateMachine().getContext());
				OJOperation copy = getter.getCopy();
				copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." + copy.getName() + "()");
				context.addToOperations(copy);
			}
			getter.getBody().addToStatements("return isStepActive(" + javaStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
		}
		implementOnEntryIfRequired(state, map);
		implementOnExitIfRequired(state, map);
		if(state.getKind().isShallowHistory() || state.getKind().isDeepHistory()){
			String fieldName = state.getMappingInfo().getJavaName().getAsIs();
			OJPathName statePat = new OJPathName(javaStateMachine.getPathName().toJavaString() + "State");
			OJAnnotatedField historyField = OJUtil.addProperty(javaStateMachine, fieldName, statePat, true);
			OJAnnotationValue enumeratd = new OJAnnotationValue(new OJPathName(Type.class.getName()));
			enumeratd.putAttribute("type", javaStateMachine.getName() + "StateResolver");
			historyField.putAnnotation(enumeratd);
		}
	}
	private void implementOnExitIfRequired(INakedState state,NakedStateMap map){
		if(StateMachineUtil.doesWorkOnExit(state)){
			OJAnnotatedOperation onExit = new OJAnnotatedOperation(map.getOnExitMethod());
			javaStateMachine.addToOperations(onExit);
			onExit.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getExit() != null){
				implementBehaviorOn(state, state.getExit(), onExit);
			}
			for(INakedTransition t:state.getOutgoing()){
				if(t.getTrigger() != null){
					if(t.getTrigger().getEvent() instanceof INakedTimeEvent){
						EventUtil.cancelTimer(onExit.getBody(), (INakedTimeEvent) t.getTrigger().getEvent(), "this");
					}else if(t.getTrigger().getEvent() instanceof INakedChangeEvent){
						EventUtil.cancelChangeEvent(onExit.getBody(), (INakedChangeEvent) t.getTrigger().getEvent());
					}
				}
			}
		}
	}
	private void implementOnEntryIfRequired(INakedState state,NakedStateMap map){
		if(StateMachineUtil.doesWorkOnEntry(state)){
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation(map.getOnEntryMethod());
			javaStateMachine.addToOperations(onEntry);
			onEntry.addParam("context", Jbpm5Util.getProcessContext());
			if(state.getEntry() != null){
				implementBehaviorOn(state, state.getEntry(), onEntry);
			}
			for(INakedTransition t:state.getOutgoing()){
				if(t.getTrigger() != null){
					if(t.getTrigger().getEvent() instanceof INakedTimeEvent){
						EventUtil.implementTimeEventRequest(onEntry, onEntry.getBody(), (INakedTimeEvent) t.getTrigger().getEvent());
					}else if(t.getTrigger().getEvent() instanceof INakedChangeEvent){
						EventUtil.implementChangeEventRequest(onEntry, (INakedChangeEvent) t.getTrigger().getEvent());
					}
				}
			}
			INakedState historyPeer = StateMachineUtil.getHistoryPeer(state);
			if(historyPeer != null && state.getKind().isRestingState()){
				String setter = "set" + historyPeer.getMappingInfo().getJavaName().getCapped().getAsIs();
				if(state.getKind().isFinal()){
					onEntry.getBody().addToStatements(setter + "(null)");
				}else{
					onEntry.getBody().addToStatements(setter + "(" + javaStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
				}
			}
			if(state.getDoActivity() != null){
				implementBehaviorOn(state, state.getDoActivity(), onEntry);
			}
			OJBlock defaultTransitions = onEntry.getBody();
			if(state.getKind().isShallowHistory()){
				String stateGetter = "get" + state.getMappingInfo().getJavaName().getCapped().getAsIs();
				OJAnnotatedField umlState = new OJAnnotatedField("umlState", new OJPathName(UmlNodeInstance.class.getName()));
				javaStateMachine.addToImports(new OJPathName(UmlNodeInstance.class.getName()));
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
				defaultTransitions.addToStatements(EventUtil.getEventHandlerName(state) + "()");
			}
			if(state.getKind().isFinal() && state.getContainer().getRegionOwner() instanceof INakedState){
				javaStateMachine.addToImports("org.jbpm.workflow.instance.NodeInstanceContainer");
				onEntry.getBody().addToStatements("((NodeInstanceContainer)context.getNodeInstance().getNodeInstanceContainer()).removeNodeInstance((NodeInstanceImpl)context.getNodeInstance())");
				onEntry.getBody().addToStatements("((NodeInstanceContainer) context.getNodeInstance().getNodeInstanceContainer()).nodeInstanceCompleted((NodeInstanceImpl)context.getNodeInstance(), null)");
			}
		}
	}
	private void implementBehaviorOn(INakedState state,INakedBehavior behavior,OJAnnotatedOperation onEntry){
		if(behavior instanceof INakedActivity){
			SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
			impl.initialize(javaModel, config, textWorkspace, super.transformationContext);
			impl.setWorkspace(workspace);
			impl.implementActivityOn((INakedActivity) behavior, onEntry);
		}else if(behavior instanceof INakedOpaqueBehavior){
			INakedOpaqueBehavior b = (INakedOpaqueBehavior) behavior;
			IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
			onEntry.getBody().addToStatements(ValueSpecificationUtil.expressValue(onEntry, b.getBody(), state.getStateMachine(), voidType));
		}
	};
	@VisitBefore(matchSubclasses = true)
	public void transition(INakedTransition transition){
		if(transition.getGuard() != null && transition.getGuard().isValidOclValue() && transition.getSource().getKind().isChoice()){
			OJOperation getter = new OJAnnotatedOperation();
			getter.setReturnType(new OJPathName("boolean"));
			getter.setName(Jbpm5Util.getGuardMethod(transition));
			javaStateMachine.addToOperations(getter);
			IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			String expression = ValueSpecificationUtil.expressValue(getter, transition.getGuard(), transition.getStateMachine(), booleanType);
			getter.getBody().addToStatements("return " + expression);
		}
	};
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine){
		javaStateMachine = findJavaClass(umlStateMachine);
		addImports(javaStateMachine);
		javaStateMachine.setName(umlStateMachine.getMappingInfo().getJavaName().toString());
		OJPackage statePackage = findOrCreatePackage(javaStateMachine.getPathName().getHead());
		statePackage.addToClasses(javaStateMachine);
		OJPathName stateClass = statePackage.getPathName();
		stateClass.addToNames(umlStateMachine.getMappingInfo().getJavaName() + "State");
		implementProcessInterfaceOperations(javaStateMachine, stateClass, umlStateMachine);
		OJOperation execute = implementExecute(javaStateMachine, umlStateMachine);
		execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");
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
}
