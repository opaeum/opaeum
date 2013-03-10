package org.opaeum.javageneration.bpm.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FinalState;
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
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.bpm.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.bpm.ObservationUtil;
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
import org.opaeum.runtime.domain.IProcessObjectBase;
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
			implementIObserver(ojStateMachine, EmfTimeUtil.getTimeObservations(umlStateMachine), EmfTimeUtil.getDurationObservations(umlStateMachine));
			OJOperation exec = implementExecute(ojStateMachine, isPersistent(behavior), EmfBehaviorUtil.hasSuperClass(umlStateMachine));
			for(Region region:umlStateMachine.getRegions()){
				String regionName = ojUtil.classifierPathname(region).getLast();
				exec.getBody().addToStatements("((" + regionName + ")getExecutionElements().get(" + regionName + ".ID)).initiate(null)");
			}
			implementIProcess(umlStateMachine, ojStateMachine);
			implementIBehaviorExecution(behavior, ojStateMachine, HibernateUtil.STATE_MACHINE_TOKEN);
			addTransientProperty(ojStateMachine, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"), true).setVisibility(
					OJVisibilityKind.PROTECTED);
			addImports(ojStateMachine, umlStateMachine);
		}
		visitRegions(ojStateMachine, umlStateMachine.getRegions());
	}
	public void initializeExecutionElements(Behavior behavior,OJAnnotatedClass ojStateMachine,OJIfStatement ifNull){
		OJPathName set = new OJPathName("java.util.Set");
		set.addToElementTypes(REGION_ACTIVATION);
		OJAnnotatedField regions = new OJAnnotatedField("regions", set);
		ifNull.getThenPart().addToLocals(regions);
		regions.setInitExp("new HashSet<" + REGION_ACTIVATION.getLast() + ">()");
		ojStateMachine.addToImports(new OJPathName("java.util.HashSet"));
		for(Region region:((StateMachine) behavior).getRegions()){
			OJPathName rpn = ojUtil.classifierPathname(region);
			ojStateMachine.addToImports(rpn);
			ifNull.getThenPart().addToStatements("regions.add(new " + rpn.getLast() + "<" + ojStateMachine.getName() + ">(this))");
		}
		OJForStatement forEachRegion = new OJForStatement("ra", REGION_ACTIVATION, "regions");
		ifNull.getThenPart().addToStatements(forEachRegion);
		forEachRegion.getBody().addToStatements("ra.linkTransitions()");
	}
	private void state(Vertex vertex){
		StateMap map = ojUtil.buildStateMap(vertex);
		StateMachine umlStateMachine = EmfStateMachineUtil.getNearestApplicableStateMachine(vertex);
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
				implementIProcessStep(stateClass, vertex, getOperationTriggers(vertex));
			}
			findOrCreatePackage(javaType.getHead()).addToClasses(stateClass);
			createTextPath(stateClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			stateClass.addToImports(ojUtil.statePathname(umlStateMachine));
			stateClass.addToImports(ojUtil.classifierPathname(umlStateMachine));
			addId(vertex, stateClass);
			OJConstructor c = new OJConstructor();
			stateClass.addToConstructors(c);
			stateClass.addGenericTypeParam("SME extends " + ojUtil.classifierPathname(umlStateMachine).getLast());
			if(vertex instanceof State && ((State) vertex).getRedefinedState() != null){
				State rs = ((State) vertex).getRedefinedState();
				OJPathName rsPathName = ojUtil.classifierPathname(rs).getCopy();
				rsPathName.addToElementTypes(new OJPathName("SME"));
				stateClass.setSuperclass(rsPathName);
				c.setDelegateConstructor("super(region)");
				c.getBody().addToStatements("getBehaviorExecution().getExecutionElements().put(ID, this)");
			}else{
				if(EmfStateMachineUtil.isShallowHistory(vertex)){
					stateClass.setSuperclass(HISTORY_STATE_ACTIVATION.getCopy());
				}else{
					stateClass.setSuperclass(vertex instanceof State ? STATE_ACTIVATION.getCopy() : PSEUDO_STATE_ACTIVATION.getCopy());
				}
				stateClass.getSuperclass().addToElementTypes(new OJPathName("SME"));
				OJPathName tokenPathName = ojUtil.tokenPathName(umlStateMachine).getCopy();
				stateClass.getSuperclass().addToElementTypes(tokenPathName);
				tokenPathName.addToElementTypes(new OJPathName("SME"));
				c.setDelegateConstructor("super(ID,region)");
			}
			OJPathName regionPath = ojUtil.classifierPathname(vertex.getContainer()).getCopy();
			regionPath.addToElementTypes(new OJPathName("SME"));
			c.addParam("region", regionPath);
			if(vertex instanceof Pseudostate){
				Pseudostate pseudostate = (Pseudostate) vertex;
				if(pseudostate.getKind() == PseudostateKind.INITIAL_LITERAL){
					c.getBody().addToStatements("setInitial(true)");
				}else if(pseudostate.getKind() == PseudostateKind.SHALLOW_HISTORY_LITERAL
						|| pseudostate.getKind() == PseudostateKind.DEEP_HISTORY_LITERAL){
					c.getBody().addToStatements("setInitial(true)");
					String fieldName = vertex.getName();
					ojUtil.addPersistentProperty(ojStateMachine, fieldName, new OJPathName("String"), true);
					String stateGetter = "get" + NameConverter.capitalize(pseudostate.getName());
					OJAnnotatedOperation getHistoricalState = new OJAnnotatedOperation("getHistoricalStateId", new OJPathName("String"));
					stateClass.addToOperations(getHistoricalState);
					getHistoricalState.initializeResultVariable("null");
					OJIfStatement ifNotNul = new OJIfStatement("getBehaviorExecution()." + stateGetter + "()!=null");
					getHistoricalState.getBody().addToStatements(ifNotNul);
					ifNotNul.getThenPart().addToStatements("result=getBehaviorExecution()." + stateGetter + "()");
				}
			}
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getBehaviorExecution", new OJPathName("SME"));
			stateClass.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(SME)super.getBehaviorExecution()");
			if(umlStateMachine.getContext() != null){
				OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject", ojUtil.classifierPathname(umlStateMachine
						.getContext()));
				stateClass.addToOperations(getContextObject);
				getContextObject.initializeResultVariable("((" + ojStateMachine.getName() + ")super.getBehaviorExecution()).getContextObject()");
			}
			implementOnEntryIfRequired(ojStateMachine, stateClass, vertex, map);
			implementOnExitIfRequired(ojStateMachine, stateClass, vertex, map);
			OJAnnotatedOperation onCompletion = new OJAnnotatedOperation("onCompletion", new OJPathName("boolean"));
			stateClass.addToOperations(onCompletion);
			onCompletion.initializeResultVariable("false");
			for(Transition transition:vertex.getOutgoings()){
				OJPathName cn = ojUtil.classifierPathname(transition).getCopy();
				cn.addToElementTypes(new OJPathName("SME"));
				addTransientProperty(stateClass, cn.getLast(), cn, true);
				if(getTriggers(transition).isEmpty()){
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
			OJPathName setOfOutgoingEvents = new OJPathName("java.util.Set");
			setOfOutgoingEvents.addToElementTypes(new OJPathName(OutgoingEvent.class.getName()));
			OJAnnotatedOperation getOutgoingEvents = new OJAnnotatedOperation("getOutgoingEvents", setOfOutgoingEvents);
			getOutgoingEvents.initializeResultVariable("getBehaviorExecution().getOutgoingEvents()");
			stateClass.addToOperations(getOutgoingEvents);
			OJPathName setOfCancelledEvents = new OJPathName("java.util.Set");
			setOfCancelledEvents.addToElementTypes(new OJPathName(CancelledEvent.class.getName()));
			OJAnnotatedOperation getCancelledEvents = new OJAnnotatedOperation("getCancelledEvents", setOfCancelledEvents);
			getCancelledEvents.initializeResultVariable("getBehaviorExecution().getCancelledEvents()");
			stateClass.addToOperations(getCancelledEvents);
		}
		
	}
	private void addId(Element vertex,OJAnnotatedClass stateClass){
		OJAnnotatedField id = new OJAnnotatedField("ID", new OJPathName("String"));
		id.setStatic(true);
		id.setVisibility(OJVisibilityKind.PUBLIC);
		id.setInitExp("\"" + EmfWorkspace.getId(vertex) + "\"");
		stateClass.addToFields(id);
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
				Collection<Trigger> triggers = getTriggers(t);
				for(Trigger trigger:triggers){
					if(trigger.getEvent() instanceof TimeEvent){
						EventUtil.cancelTimer(onExit.getBody(), (TimeEvent) trigger.getEvent(), "this");
					}else if(trigger.getEvent() instanceof ChangeEvent){
						EventUtil.cancelChangeEvent(onExit.getBody(), (ChangeEvent) trigger.getEvent());
					}
				}
			}
			new ObservationUtil(ojUtil).implementObservationsOnExit(vertex, onExit, "getStateMachineExecution()");
		}
	}
	private void implementOnEntryIfRequired(OJAnnotatedClass ojStateMachine,OJAnnotatedClass stateClass,Vertex vertex,StateMap map){
		if(EmfStateMachineUtil.doesWorkOnEntry(vertex)){
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation("onEntry");
			stateClass.addToOperations(onEntry);
			OJPathName tokenPathName = ojUtil.tokenPathName(EmfStateMachineUtil.getNearestApplicableStateMachine(vertex));
			onEntry.addParam("token", tokenPathName);
			boolean isComplexState = false;
			onEntry.getBody().addToStatements("super.onEntry(token)");
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
						onEntry.getBody().addToStatements(setter + "(ID)");
					}
				}
				if(state.getDoActivity() != null){
					implementBehaviorOn(state.getDoActivity(), onEntry, onEntry.getBody());
				}
				isComplexState = state.isComposite() || state.isOrthogonal();
			}
			Collection<Transition> completionTransitions = EmfStateMachineUtil.getCompletionTransitions(vertex);
			if(completionTransitions.size() > 0 && !isComplexState){
				onEntry.getBody().addToStatements("token.fireCompletionEvent()");
			}
			boolean hasEvents = false;
			for(Transition transition:vertex.getOutgoings()){
				EList<Trigger> triggers = getTriggers(transition);
				if(triggers.size() > 0 && triggers.get(0).getEvent() != null){
					Trigger wfe = triggers.get(0);
					if(wfe.getEvent() instanceof TimeEvent && EmfEventUtil.isDeadline(wfe.getEvent())){
						// fired and cancelled from task
					}else if(wfe.getEvent() instanceof TimeEvent){
						hasEvents = true;
						eventUtil.implementTimeEventRequest(onEntry, onEntry.getBody(), (TimeEvent) wfe.getEvent(),
								getLibrary().getBusinessRole() != null);
					}else if(wfe.getEvent() instanceof ChangeEvent){
						ChangeEvent ce = (ChangeEvent) wfe.getEvent();
						if(ce.getChangeExpression() instanceof OpaqueExpression){
							eventUtil.implementChangeEventRequest(onEntry, ce, (OpaqueExpression) ce.getChangeExpression());
							hasEvents = true;
						}
					}
				}
			}
			if(hasEvents){
				stateClass.addToImports(new OJPathName(OutgoingEvent.class.getName()));
				stateClass.addToImports(new OJPathName(CancelledEvent.class.getName()));
			}
			if(vertex instanceof FinalState){
				onEntry.getBody().addToStatements("token.setHasRunToCompletion(true)");
				if(vertex.getContainer().getOwner() instanceof State){
					onEntry.getBody().addToStatements("((" + tokenPathName.getLast() + ")token.getParentToken()).fireCompletionEvent()");
				}
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
		StateMachine umlStateMachine = EmfStateMachineUtil.getNearestApplicableStateMachine(transition);
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
			transitionInstance.addToImports(ojUtil.classifierPathname(umlStateMachine));
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getBehaviorExecution", new OJPathName("SME"));
			transitionInstance.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(SME)super.getBehaviorExecution()");
			transitionInstance.addGenericTypeParam("SME extends " + ojUtil.classifierPathname(umlStateMachine).getLast());
			addId(transition, transitionInstance);
			OJConstructor c = new OJConstructor();
			transitionInstance.addToConstructors(c);
			c.addParam("regionActivation", ojUtil.classifierPathname(transition.getContainer()));
			Vertex source = transition.getSource();
			if(transition.getRedefinedTransition() != null){
				source = transition.getRedefinedTransition().getSource();
			}
			OJPathName sourcePathName = ojUtil.classifierPathname(source);
			OJPathName targetPathName = ojUtil.classifierPathname(transition.getTarget());
			if(transition.getRedefinedTransition() != null){
				transitionInstance.setSuperclass(ojUtil.classifierPathname(transition.getRedefinedTransition()).getCopy());
				transitionInstance.getSuperclass().addToElementTypes(new OJPathName("SME"));
				OJPathName rtPathName = ojUtil.classifierPathname(transition.getRedefinedTransition());
				c.setDelegateConstructor("super(regionActivation)");
				c.getBody().addToStatements(
						"this.source=(" + sourcePathName.getLast() + ") .getStateMachineExecution().getExecutionElements().get("
								+ sourcePathName.getLast() + ".ID)");
				c.getBody().addToStatements(
						"this.target=(" + targetPathName.getLast() + ") .getStateMachineExecution().getExecutionElements().get("
								+ targetPathName.getLast() + ".ID)");
				c.getBody().addToStatements("getBehaviorExecution().getExecutionElements().put(" + rtPathName + ".ID, this)");
			}else{
				c.setDelegateConstructor("super(ID,regionActivation," + sourcePathName.getLast() + ".ID," + targetPathName.getLast() + ".ID)");
				OJPathName superClass = TRANSITION_INSTANCE.getCopy();
				transitionInstance.setSuperclass(superClass);
				transitionInstance.getSuperclass().addToElementTypes(new OJPathName("SME"));
				OJPathName tokenPathName = ojUtil.tokenPathName(umlStateMachine).getCopy();
				tokenPathName.addToElementTypes(new OJPathName("SME"));
				transitionInstance.getSuperclass().addToElementTypes(tokenPathName);
			}
			transitionInstance.addToImports(sourcePathName);
			transitionInstance.addToImports(targetPathName);
			c.getBody().addToStatements("((" + sourcePathName.getLast() + ")getSource()).set" + transitionInstance.getName() + "(this)");
			OJAnnotatedOperation onEvent;
			EList<Trigger> triggers = getTriggers(transition);
			if(triggers.size() > 0 && triggers.get(0).getEvent() != null){
				onEvent = createEventConsumerSignature(umlStateMachine, transitionInstance, triggers.get(0).getEvent());
				// TODO clean this up
				onEvent.getBody().getStatements().clear();
				onEvent.removeFromParameters(onEvent.findParameter("callingToken"));
			}else{
				onEvent = new OJAnnotatedOperation(eventUtil.getEventConsumerName(source), new OJPathName("boolean"));
				onEvent.initializeResultVariable("false");
				transitionInstance.addToOperations(onEvent);
			}
			if(umlStateMachine.getContext() != null){
				OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject", ojUtil.classifierPathname(umlStateMachine
						.getContext()));
				transitionInstance.addToOperations(getContextObject);
				getContextObject.initializeResultVariable("((SME)super.getBehaviorExecution()).getContextObject()");
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
			mainBlock.addToStatements(ojUtil.tokenPathName(umlStateMachine).getLast() + "<SME> token= getMainSource().exit()");
			mainBlock.addToStatements("super.onEntry(token)");
			if(transition.getEffect() != null){
				implementBehaviorOn(transition.getEffect(), onEvent, mainBlock);
			}
			mainBlock.addToStatements("getMainTarget().enter(token,target)");
			mainBlock.addToStatements("super.onExit(token)");
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
			StateMachine umlStateMachine = EmfStateMachineUtil.getNearestApplicableStateMachine(r);
			OJPathName classifierPathname = ojUtil.classifierPathname(r);
			OJAnnotatedClass regionActivation = new OJAnnotatedClass(classifierPathname.getLast());
			javaModel.findOrCreatePackage(classifierPathname.getHead()).addToClasses(regionActivation);
			regionActivation.addToImports(ojUtil.classifierPathname(umlStateMachine));
			createTextPath(regionActivation, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			regionActivation.addGenericTypeParam("SME extends " + ojUtil.classifierPathname(umlStateMachine).getLast());
			OJConstructor c = new OJConstructor();
			regionActivation.addToConstructors(c);
			if(r.getState() != null){
				OJPathName statePath = ojUtil.classifierPathname(r.getState()).getCopy();
				statePath.addToElementTypes(new OJPathName("SME"));
				c.addParam("owner", statePath);
			}else{
				c.addParam("owner", new OJPathName("SME"));
			}
			if(r.getExtendedRegion() != null){
				regionActivation.setSuperclass(ojUtil.classifierPathname(r.getExtendedRegion()).getCopy());
				regionActivation.getSuperclass().addToElementTypes(new OJPathName("SME"));
				c.setDelegateConstructor("super(owner)");
				c.getBody().addToStatements("getBehaviorExecution().getExecutionElements().put(ID,this)");
			}else{
				regionActivation.setSuperclass(REGION_ACTIVATION.getCopy());
				regionActivation.getSuperclass().addToElementTypes(new OJPathName("SME"));
				OJPathName tokenPathName = ojUtil.tokenPathName(umlStateMachine);
				tokenPathName.addToElementTypes(new OJPathName("SME"));
				regionActivation.getSuperclass().addToElementTypes(tokenPathName);
				c.setDelegateConstructor("super(ID,owner)");
			}
			addId(r, regionActivation);
			for(Vertex vertex:r.getSubvertices()){
				OJPathName vertexPath = ojUtil.classifierPathname(vertex);
				StateMap map = ojUtil.buildStateMap(vertex);
				regionActivation.addToImports(map.javaType());
				c.getBody().addToStatements("vertices.add(new " + vertexPath.getLast() + "<SME>(this))");
			}
			OJAnnotatedOperation linkTransitions = new OJAnnotatedOperation("linkTransitions");
			regionActivation.addToOperations(linkTransitions);
			for(Transition transition:r.getTransitions()){
				OJPathName transitionPath = ojUtil.classifierPathname(transition);
				regionActivation.addToImports(transitionPath);
				linkTransitions.getBody().addToStatements("transitions.add(new " + transitionPath.getLast() + "<SME>(this))");
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
			javaStateMachine.addToImports(IProcessObjectBase.class.getName());
		}
		javaStateMachine.addToImports(IProcessStep.class.getName());
	}
	private Collection<Trigger> getOperationTriggers(Vertex step){
		Vertex state = (Vertex) step;
		Collection<Trigger> result = new ArrayList<Trigger>();
		List<Transition> outgoing = state.getOutgoings();
		for(Transition t:outgoing){
			Collection<Trigger> triggers = getTriggers(t);
			for(Trigger trigger:triggers){
				if(trigger.getEvent() instanceof CallEvent){
					result.add(trigger);
				}
			}
		}
		return result;
	}
	protected EList<Trigger> getTriggers(Transition t){
		if(t.getRedefinedTransition() != null){
			return t.getRedefinedTransition().getTriggers();
		}
		return t.getTriggers();
	}
}
