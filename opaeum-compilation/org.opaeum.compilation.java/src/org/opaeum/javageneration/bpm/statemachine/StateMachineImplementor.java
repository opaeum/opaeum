package org.opaeum.javageneration.bpm.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
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
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.bpm.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.bpm.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.bpm.activity.ActivityProcessImplementor;
import org.opaeum.javageneration.hibernate.HibernateUtil;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.HistoryStateActivation;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.PseudoStateActivation;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,CodeCleanup.class},after = {
		OperationAnnotator.class,ActivityProcessImplementor.class
/* Needs repeatable sequence in the ocl generating steps */
},before = CodeCleanup.class)
public class StateMachineImplementor extends AbstractJavaProcessVisitor{
	private static final OJPathName STATE_ACTIVATION = new OJPathName(StateActivation.class.getName());
	private static final OJPathName PSEUDO_STATE_ACTIVATION = new OJPathName(PseudoStateActivation.class.getName());
	private static final OJPathName REGION_ACTIVATION = new OJPathName(RegionActivation.class.getName());
	private static final OJPathName TRANSITION_INSTANCE = new OJPathName(TransitionInstance.class.getName());
	private static final OJPathName STATE_MACHINE_EXECUTION = new OJPathName(IStateMachineExecution.class.getName());
	private static final OJPathName HISTORY_STATE_ACTIVATION = new OJPathName(HistoryStateActivation.class.getName());
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine umlStateMachine){
		Behavior behavior = umlStateMachine;
		OJAnnotatedClass ojStateMachine = findJavaClass(behavior);
		if(EmfElementUtil.isMarkedForDeletion(behavior)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.tokenPathName(behavior));
		}else{
			if(ojUtil.requiresJavaRename(behavior)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.tokenPathName(behavior));
			}
			ojStateMachine.addToImplementedInterfaces(STATE_MACHINE_EXECUTION);
			implementExecute(ojStateMachine, behavior);
			implementIProcess(umlStateMachine, ojStateMachine);
			implementIBehaviorExecution(behavior, ojStateMachine, HibernateUtil. STATE_MACHINE_TOKEN);
			OJUtil.addTransientProperty(ojStateMachine, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"), true).setVisibility(
					OJVisibilityKind.PROTECTED);
			addImports(ojStateMachine, umlStateMachine);
		}
		visitRegions(ojStateMachine, umlStateMachine.getRegions());
	}
	public void initializeExecutionElements(Behavior behavior,OJAnnotatedClass ojStateMachine,OJIfStatement ifNull){
		for(Region region:((StateMachine) behavior).getRegions()){
			OJPathName rpn = ojUtil.classifierPathname(region);
			ojStateMachine.addToImports(rpn);
			ifNull.getThenPart().addToStatements("new " + rpn.getLast() + "(this).linkTransitions()");
		}
	}
	private void state(Vertex vertex){
		StateMap map = ojUtil.buildStateMap(vertex);
		StateMachine umlStateMachine = EmfStateMachineUtil.getStateMachine(vertex);
		OJAnnotatedClass ojStateMachine = findJavaClass(umlStateMachine);
		OJPathName javaType = map.javaType();
		if(EmfElementUtil.isMarkedForDeletion(vertex)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(vertex));
		}else{
			if(ojUtil.requiresJavaRename(vertex)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(vertex));
			}
			OJAnnotatedClass stateClass = new OJAnnotatedClass(javaType.getLast());
			if(vertex instanceof State){
				implementIProcessStep(stateClass,vertex, getOperationTriggers(vertex));
			}
			findOrCreatePackage(javaType.getHead()).addToClasses(stateClass);
			createTextPath(stateClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			stateClass.setSuperclass(vertex instanceof State ? STATE_ACTIVATION.getCopy() : PSEUDO_STATE_ACTIVATION.getCopy());
			stateClass.addToImports(ojUtil.statePathname(umlStateMachine));
			OJConstructor c = new OJConstructor();
			stateClass.addToConstructors(c);
			c.setDelegateConstructor("super(\"" + EmfWorkspace.getId(vertex) + "\",region)");
			c.addParam("region", ojUtil.classifierPathname(vertex.getContainer()));
			if(vertex instanceof Pseudostate){
				Pseudostate pseudostate = (Pseudostate) vertex;
				if(pseudostate.getKind() == PseudostateKind.INITIAL_LITERAL){
					c.getBody().addToStatements("setInitial(true)");
				}else if(pseudostate.getKind() == PseudostateKind.SHALLOW_HISTORY_LITERAL
						|| pseudostate.getKind() == PseudostateKind.DEEP_HISTORY_LITERAL){
					c.getBody().addToStatements("setInitial(true)");
					String fieldName = vertex.getName();
					stateClass.setSuperclass(HISTORY_STATE_ACTIVATION.getCopy());
					OJUtil.addPersistentProperty(ojStateMachine, fieldName, new OJPathName("String"), true);
					String stateGetter = "get" + NameConverter.capitalize(pseudostate.getName());
					OJAnnotatedOperation getHistoricalState = new OJAnnotatedOperation("getHistoricalStateId", new OJPathName("String"));
					stateClass.addToOperations(getHistoricalState);
					getHistoricalState.initializeResultVariable("null");
					OJIfStatement ifNotNul = new OJIfStatement("getBehaviorExecution()." + stateGetter + "()!=null");
					getHistoricalState.getBody().addToStatements(ifNotNul);
					ifNotNul.getThenPart().addToStatements("result=getBehaviorExecution()." + stateGetter + "()");
				}
			}
			stateClass.getSuperclass().addToElementTypes(ojUtil.classifierPathname(umlStateMachine));
			stateClass.getSuperclass().addToElementTypes(ojUtil.tokenPathName(umlStateMachine));
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getBehaviorExecution", ojStateMachine.getPathName());
			stateClass.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(" + ojStateMachine.getName() + ")super.getBehaviorExecution()");
			if(umlStateMachine.getContext() != null){
				OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject", ojUtil.classifierPathname(umlStateMachine
						.getContext()));
				stateClass.addToOperations(getContextObject);
				getContextObject.initializeResultVariable("((" + ojStateMachine.getName() + ")super.getBehaviorExecution()).getContextObject()");
			}
			implementOnEntryIfRequired(ojStateMachine, stateClass, vertex, map);
			implementOnExitIfRequired(ojStateMachine, stateClass, vertex, map);
			OJAnnotatedOperation onCompletion = new OJAnnotatedOperation("onComplete", new OJPathName("boolean"));
			stateClass.addToOperations(onCompletion);
			onCompletion.initializeResultVariable("false");
			for(Transition transition:vertex.getOutgoings()){
				OJPathName cn = ojUtil.classifierPathname(transition);
				OJUtil.addTransientProperty(stateClass, cn.getLast(), cn, true);
				if(transition.getTriggers().isEmpty()){
					onCompletion.getBody().addToStatements(
							new OJIfStatement(cn.getLast() + "." + eventUtil.getEventConsumerName(vertex) + "()", "return true"));
				}
			}
			if(vertex instanceof State){
				State state = (State) vertex;
				for(Region region:state.getRegions()){
					OJPathName rpn = ojUtil.classifierPathname(region);
					stateClass.addToImports(rpn);
					c.getBody().addToStatements("regions.add(new " + rpn.getLast() + "(this))");
				}
			}
		}
	}
	private void implementOnExitIfRequired(OJAnnotatedClass ojStateMachine,OJAnnotatedClass stateClass,Vertex vertex,StateMap map){
		if(vertex instanceof State && EmfStateMachineUtil.doesWorkOnExit((State) vertex)){
			State state = (State) vertex;
			OJAnnotatedOperation onExit = new OJAnnotatedOperation("onExit");
			stateClass.addToOperations(onExit);
			if(state.getExit() != null){
				implementBehaviorOn(state.getExit(), onExit, onExit.getBody());
			}
			for(Transition t:state.getOutgoings()){
				Collection<Trigger> triggers = t.getTriggers();
				for(Trigger trigger:triggers){
					if(trigger.getEvent() instanceof TimeEvent){
						EventUtil.cancelTimer(onExit.getBody(), (TimeEvent) trigger.getEvent(), "this");
					}else if(trigger.getEvent() instanceof ChangeEvent){
						EventUtil.cancelChangeEvent(onExit.getBody(), (ChangeEvent) trigger.getEvent());
					}
				}
			}
		}
	}
	private void implementOnEntryIfRequired(OJAnnotatedClass ojStateMachine,OJAnnotatedClass stateClass,Vertex vertex,StateMap map){
		if(EmfStateMachineUtil.doesWorkOnEntry(vertex)){
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation("onEntry");
			stateClass.addToOperations(onEntry);
			OJPathName tokenPathName = ojUtil.tokenPathName(EmfStateMachineUtil.getStateMachine(vertex));
			onEntry.addParam("token", tokenPathName);
			boolean isComplexState = false;
			if(vertex instanceof State){
				State state = (State) vertex;
				if(state.getEntry() != null){
					implementBehaviorOn(state.getEntry(), onEntry, onEntry.getBody());
				}
				Pseudostate historyPeer = EmfStateMachineUtil.getHistoryPeer(state);
				if(historyPeer != null){
					String setter = "getStateMachineExecution().set" + NameConverter.capitalize(historyPeer.getName());
					if(state instanceof FinalState){
						onEntry.getBody().addToStatements(setter + "(null)");
					}else{
						onEntry.getBody().addToStatements(setter + "(\"" + EmfWorkspace.getId(state) + "\")");
					}
				}
				if(state.getDoActivity() != null){
					implementBehaviorOn(state.getDoActivity(), onEntry, onEntry.getBody());
				}
				isComplexState = state.isComposite() || state.isOrthogonal();
			}
			Collection<Transition> completionTransitions = EmfStateMachineUtil.getCompletionTransitions(vertex);
			if(!EmfStateMachineUtil.isShallowHistory(vertex) && completionTransitions.size() > 0 && !isComplexState){
				onEntry.getBody().addToStatements("token.fireCompletionEvent()");
			}
			boolean hasEvents = false;
			for(Transition transition:vertex.getOutgoings()){
				if(transition.getTriggers().size() > 0 && transition.getTriggers().get(0).getEvent() != null){
					Trigger wfe = transition.getTriggers().get(0);
					if(wfe.getEvent() instanceof TimeEvent && EmfEventUtil.isDeadline(wfe.getEvent())){
						// fired and cancelled from task
					}else if(wfe.getEvent() instanceof TimeEvent){
						hasEvents = true;
						eventUtil.implementTimeEventRequest(onEntry, onEntry.getBody(), (TimeEvent) wfe.getEvent(),
								getLibrary().getBusinessRole() != null);
					}else if(wfe.getEvent() instanceof ChangeEvent){
						eventUtil.implementChangeEventRequest(onEntry, (ChangeEvent) wfe.getEvent());
						hasEvents = true;
					}
				}
			}
			if(hasEvents){
				stateClass.addToImports(new OJPathName(OutgoingEvent.class.getName()));
				stateClass.addToImports(new OJPathName(CancelledEvent.class.getName()));
			}
			if(vertex instanceof FinalState && vertex.getContainer().getOwner() instanceof State){
				onEntry.getBody().addToStatements("token.setHasRunToCompletion(true)");
				onEntry.getBody().addToStatements("(("+tokenPathName.getLast() +")token.getParentToken()).fireCompletionEvent()");
			}
		}
	}
	private void implementBehaviorOn(Behavior behavior,OJAnnotatedOperation onEntry,OJBlock block){
		if(behavior instanceof Activity){
			SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
			impl.initialize(javaModel, config, textWorkspace, workspace, ojUtil);
			impl.setWorkspace(workspace);
			impl.implementActivityOn((Activity) behavior, onEntry, block);
			onEntry.getOwner().addToImports(new OJPathName(OutgoingEvent.class.getName()));
		}else if(behavior instanceof OpaqueBehavior){
			OpaqueBehavior b = (OpaqueBehavior) behavior;
			OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext(b);
			Classifier voidType = getLibrary().getOclLibrary().getOclVoid();
			block.addToStatements(valueSpecificationUtil.expressOcl(oclBehaviorContext, onEntry, voidType));
		}
	};
	private void transition(Transition transition){
		StateMachine umlStateMachine = EmfStateMachineUtil.getStateMachine(transition);
		if(EmfElementUtil.isMarkedForDeletion(transition)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(transition));
		}else{
			if(ojUtil.requiresJavaRename(transition)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(transition));
			}
			OJPathName classifierPathname = ojUtil.classifierPathname(transition);
			OJAnnotatedClass transitionInstance = new OJAnnotatedClass(classifierPathname.getLast());
			findOrCreatePackage(classifierPathname.getHead()).addToClasses(transitionInstance);
			createTextPath(transitionInstance, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			OJAnnotatedClass ojStateMachine = findJavaClass(EmfStateMachineUtil.getStateMachine(transition));
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getBehaviorExecution", ojStateMachine.getPathName());
			transitionInstance.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(" + ojStateMachine.getName() + ")super.getBehaviorExecution()");
			OJPathName superClass = TRANSITION_INSTANCE.getCopy();
			transitionInstance.setSuperclass(superClass);
			transitionInstance.getSuperclass().addToElementTypes(ojUtil.classifierPathname(umlStateMachine));
			transitionInstance.getSuperclass().addToElementTypes(ojUtil.tokenPathName(umlStateMachine));
			OJConstructor c = new OJConstructor();
			transitionInstance.addToConstructors(c);
			c.addParam("regionActivation", ojUtil.classifierPathname(transition.getContainer()));
			c.setDelegateConstructor("super(\"" + EmfWorkspace.getId(transition) + "\",regionActivation,\""
					+ EmfWorkspace.getId(transition.getSource()) + "\",\"" + EmfWorkspace.getId(transition.getSource()) + "\")");
			OJPathName sourcePathName = ojUtil.classifierPathname(transition.getSource());
			transitionInstance.addToImports(sourcePathName);
			c.getBody().addToStatements("((" + sourcePathName.getLast() + ")getSource()).set" + transitionInstance.getName() + "(this)");
			OJAnnotatedOperation onEvent;
			if(transition.getTriggers().size() > 0 && transition.getTriggers().get(0).getEvent() != null){
				onEvent = createEventConsumerSignature(null, transitionInstance, transition.getTriggers().get(0).getEvent());
				// TODO clean this up
				onEvent.removeFromParameters(onEvent.findParameter("callingToken"));
			}else{
				onEvent = new OJAnnotatedOperation(eventUtil.getEventConsumerName(transition.getSource()), new OJPathName("boolean"));
				onEvent.initializeResultVariable("false");
				transitionInstance.addToOperations(onEvent);
			}
			if(umlStateMachine.getContext() != null){
				OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject", ojUtil.classifierPathname(umlStateMachine
						.getContext()));
				transitionInstance.addToOperations(getContextObject);
				getContextObject.initializeResultVariable("((" + ojStateMachine.getName() + ")super.getBehaviorExecution()).getContextObject()");
			}
			OJBlock mainBlock = onEvent.getBody();
			if(EmfStateMachineUtil.hasGuard(transition)){
				OpaqueExpressionContext context = getLibrary().getOclExpressionContext((OpaqueExpression) transition.getGuard().getSpecification());
				if(!context.hasErrors()){
					ValueSpecificationUtil.addExtendedKeywords(onEvent, context);
					List<OJParameter> parameters = new ArrayList<OJParameter>(onEvent.getParameters());
					ValueSpecificationUtil.buildContext(onEvent, parameters, onEvent.getBody());
					ExpressionCreator ec = new ExpressionCreator(ojUtil, transitionInstance);
					OJIfStatement ifGuard = new OJIfStatement(ec.makeExpression(context, false, onEvent.getParameters()));
					onEvent.getBody().addToStatements(ifGuard);
					mainBlock = ifGuard.getThenPart();
				}
			}
			mainBlock.addToStatements("result=true");
			mainBlock.addToStatements(ojUtil.tokenPathName(umlStateMachine).getLast() + " token= getMainSource().exit()");
			if(transition.getEffect() != null){
				implementBehaviorOn(transition.getEffect(), onEvent, mainBlock);
			}
			mainBlock.addToStatements("getMainTarget().enter(token,target)");
		}
	}
	private void visitRegions(OJAnnotatedClass ojStateMachine,Collection<Region> regions){
		for(Region r:regions){
			region(r);
			for(Vertex s:r.getSubvertices()){
				this.state(s);
				if(s instanceof State){
					visitRegions(ojStateMachine, ((State) s).getRegions());
				}
			}
			for(Transition t:r.getTransitions()){
				this.transition(t);
			}
		}
	}
	private void region(Region r){
		if(EmfElementUtil.isMarkedForDeletion(r)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(r));
		}else{
			if(ojUtil.requiresJavaRename(r)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(r));
			}
			StateMachine umlStateMachine = EmfStateMachineUtil.getStateMachine(r);
			OJPathName classifierPathname = ojUtil.classifierPathname(r);
			OJAnnotatedClass regionActivation = new OJAnnotatedClass(classifierPathname.getLast());
			javaModel.findOrCreatePackage(classifierPathname.getHead()).addToClasses(regionActivation);
			createTextPath(regionActivation, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			regionActivation.setSuperclass(REGION_ACTIVATION.getCopy());
			regionActivation.getSuperclass().addToElementTypes(ojUtil.classifierPathname(umlStateMachine));
			OJPathName tokenPathName = ojUtil.tokenPathName(umlStateMachine);
			if(EmfClassifierUtil.getSubClasses(umlStateMachine).size()>0){
				tokenPathName.addToElementTypes(ojUtil.classifierPathname(umlStateMachine));
			}
			regionActivation.getSuperclass().addToElementTypes(tokenPathName);
			OJConstructor c = new OJConstructor();
			regionActivation.addToConstructors(c);
			OJPathName ownerType = ojUtil.classifierPathname((NamedElement) r.getOwner());
			c.addParam("owner", ownerType);
			c.setDelegateConstructor("super(\"" + EmfWorkspace.getId(r) + "\",owner)");
			for(Vertex vertex:r.getSubvertices()){
				OJPathName vertexPath = ojUtil.classifierPathname(vertex);
				StateMap map = ojUtil.buildStateMap(vertex);
				regionActivation.addToImports(map.javaType());
				c.getBody().addToStatements("vertices.add(new " + vertexPath.getLast() + "(this))");
			}
			OJAnnotatedOperation linkTransitions = new OJAnnotatedOperation("linkTransitions");
			regionActivation.addToOperations(linkTransitions);
			for(Transition transition:r.getTransitions()){
				OJPathName transitionPath = ojUtil.classifierPathname(transition);
				regionActivation.addToImports(transitionPath);
				linkTransitions.getBody().addToStatements("transitions.add(new " + transitionPath.getLast() + "(this))");
			}
			linkTransitions.getBody().addToStatements("super.linkTransitions()");
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
	private Collection<Trigger> getOperationTriggers(Vertex step){
		Vertex state = (Vertex) step;
		Collection<Trigger> result = new ArrayList<Trigger>();
		List<Transition> outgoing = state.getOutgoings();
		for(Transition t:outgoing){
			Collection<Trigger> triggers = t.getTriggers();
			for(Trigger trigger:triggers){
				if(trigger.getEvent() instanceof CallEvent){
					result.add(trigger);
				}
			}
		}
		return result;
	}

}
