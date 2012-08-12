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
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
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
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.generated.OJClassifierGEN;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.bpm.AbstractJavaProcessVisitor;
import org.opaeum.javageneration.bpm.BpmUtil;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.bpm.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.javageneration.bpm.activity.ActivityProcessImplementor;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,CodeCleanup.class},after = {
		OperationAnnotator.class,ActivityProcessImplementor.class
/* Needs repeatable sequence in the ocl generating steps */
},before = CodeCleanup.class)
public class StateMachineImplementor extends AbstractJavaProcessVisitor{
	private static final OJPathName STATE_ACTIVATION = new OJPathName("org.opaeum.runtime.statemachines.StateActivation");
	private static final OJPathName PSEUDO_STATE_ACTIVATION = new OJPathName("org.opaeum.runtime.statemachines.PseudoStateActivation");
	private static final OJPathName REGION_ACTIVATION = new OJPathName("org.opaeum.runtime.statemachines.RegionActivation");
	static final OJPathName STATE_MACHINE_TOKEN = new OJPathName("org.opaeum.runtime.statemachines.StateMachineToken");
	private static final OJPathName STATE_MACHINE_EXECUTION = new OJPathName("org.opaeum.runtime.statemachines.IStateMachineExecution");
	private static final OJPathName HISTORY_STATE_ACTIVATION = new OJPathName("org.opaeum.runtime.statemachines.HistoryStateActivation");
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine umlStateMachine){
		OJAnnotatedClass ojStateMachine = findJavaClass(umlStateMachine);
		OJPathName tokenPath = ojUtil.tokenPathName(umlStateMachine);
		if(EmfElementUtil.isMarkedForDeletion(umlStateMachine)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, tokenPath);
		}else{
			if(ojUtil.requiresJavaRename(umlStateMachine)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, tokenPath);
			}
			ojStateMachine.addToImplementedInterfaces(STATE_MACHINE_EXECUTION);
			OJPathName map = new OJPathName("java.util.Map");
			map.addToElementTypes(new OJPathName("String"));
			OJPathName STATE_MACHINE_EXECUTION_ELEMENT = new OJPathName("org.opaeum.runtime.statemachines.IStateMachineExecutionElement");
			map.addToElementTypes(STATE_MACHINE_EXECUTION_ELEMENT);
			ojStateMachine.addToFields(new OJAnnotatedField("executionElements", map));
			OJAnnotatedOperation getExecutionElement = new OJAnnotatedOperation("getExecutionElements", map);
			getExecutionElement.initializeResultVariable("executionElements");
			OJIfStatement ifNull = new OJIfStatement("executionElements==null", "result=executionElements=new HashMap<String,"
					+ STATE_MACHINE_EXECUTION_ELEMENT + ">()");
			getExecutionElement.getBody().addToStatements(ifNull);
			for(Region region:umlStateMachine.getRegions()){
				OJPathName rpn = ojUtil.classifierPathname(region);
				ojStateMachine.addToImports(rpn);
				ifNull.getThenPart().addToStatements("new " + rpn.getLast() + "(this).linkTransitions()");
			}
			ojStateMachine.addToOperations(getExecutionElement);
			OJAnnotatedClass tokenClass = new OJAnnotatedClass(tokenPath.getLast());
			findOrCreatePackage(tokenPath.getHead()).addToClasses(tokenClass);
			createTextPath(tokenClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			if(umlStateMachine.getGenerals().size() > 0 && umlStateMachine.getGenerals().get(0) instanceof StateMachine){
				tokenClass.setSuperclass(ojUtil.tokenPathName((Behavior) umlStateMachine.getGenerals().get(0)));
				OJConstructor c = new OJConstructor();
				tokenClass.addToConstructors(c);
				c.addParam("parentToken", tokenClass.getPathName());
				c.setDelegateConstructor("super(parentToken)");
			}else{
				tokenClass.setSuperclass(STATE_MACHINE_TOKEN);
				OJConstructor c = new OJConstructor();
				tokenClass.addToConstructors(c);
				c.addParam("parentToken", tokenClass.getPathName());
				c.getBody().addToStatements("this.parentToken=parentToken");
				buildTokenClass(ojStateMachine, tokenPath, tokenClass);
			}
			OJAnnotatedOperation removeToken = new OJAnnotatedOperation("removeToken");
			ojStateMachine.addToOperations(removeToken);
			removeToken.addParam("smToken", STATE_MACHINE_TOKEN);
			removeToken.getBody().addToStatements("tokens.remove((" + tokenClass.getName() + ")smToken)");
			OJAnnotatedOperation createToken = new OJAnnotatedOperation("createToken", tokenClass.getPathName());
			ojStateMachine.addToOperations(createToken);
			createToken.addParam("smToken", STATE_MACHINE_TOKEN);
			createToken.initializeResultVariable("new " + tokenClass.getName() + "((" + tokenClass.getName() + ")smToken)");
			createToken.getBody().addToStatements("tokens.add(result)");
			OJUtil.addTransientProperty(ojStateMachine, Jbpm5ObjectNodeExpressor.EXCEPTION_FIELD, new OJPathName("Object"), true).setVisibility(
					OJVisibilityKind.PROTECTED);
			OJOperation execute = implementExecute(ojStateMachine, umlStateMachine);
			addImports(ojStateMachine, umlStateMachine);
		}
		visitRegions(ojStateMachine, umlStateMachine.getRegions());
	}
	public void buildTokenClass(OJAnnotatedClass ojStateMachine,OJPathName tokenPath,OJAnnotatedClass tokenClass){
		OJUtil.addPersistentProperty(tokenClass, "stateMachineExecution", ojStateMachine.getPathName(), true);
		OJUtil.addPersistentProperty(tokenClass, "parentToken", tokenPath, true);
		OJPathName setOfTokens = new OJPathName("java.util.Set");
		setOfTokens.addToElementTypes(tokenPath);
		OJAnnotatedField childTokens = OJUtil.addPersistentProperty(tokenClass, "childTokens", setOfTokens, true);
		OJAnnotationValue childTokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		childTokens.addAnnotationIfNew(childTokensOneToMany);
		childTokensOneToMany.putAttribute("mappedBy", "parentToken");
		OJAnnotatedField tokens = new OJAnnotatedField("tokens", setOfTokens);
		tokens.setVisibility(OJVisibilityKind.PROTECTED);
		ojStateMachine.addToFields(tokens);
		OJAnnotatedOperation getTokens = new OJAnnotatedOperation("getTokens", setOfTokens);
		ojStateMachine.addToOperations(getTokens);
		getTokens.initializeResultVariable("tokens");
		OJAnnotationValue tokensOneToMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
		tokens.addAnnotationIfNew(tokensOneToMany);
		tokensOneToMany.putAttribute("mappedBy", "stateMachineExecution");
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
			if(vertex instanceof State){
				implementIsInState(map, umlStateMachine, ojStateMachine);
			}
			OJAnnotatedClass stateClass = new OJAnnotatedClass(javaType.getLast());
			findOrCreatePackage(javaType.getHead()).addToClasses(stateClass);
			createTextPath(stateClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			stateClass.setSuperclass(vertex instanceof State ? STATE_ACTIVATION : PSEUDO_STATE_ACTIVATION);
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
					stateClass.setSuperclass(HISTORY_STATE_ACTIVATION);
					OJUtil.addPersistentProperty(ojStateMachine, fieldName, new OJPathName("String"), true);
					String stateGetter = "get" + NameConverter.capitalize(pseudostate.getName());
					OJAnnotatedOperation getHistoricalState = new OJAnnotatedOperation("getHistoricalStateId", new OJPathName("String"));
					stateClass.addToOperations(getHistoricalState);
					getHistoricalState.initializeResultVariable("null");
					OJIfStatement ifNotNul = new OJIfStatement("getStateMachineExecution()." + stateGetter + "()!=null");
					getHistoricalState.getBody().addToStatements(ifNotNul);
					ifNotNul.getThenPart().addToStatements("result=getStateMachineExecution()." + stateGetter + "()");
				}
			}
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getStateMachineExecution", ojStateMachine.getPathName());
			stateClass.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(" + ojStateMachine.getName() + ")super.getStateMachineExecution()");
			implementOnEntryIfRequired(ojStateMachine, stateClass, vertex, map);
			implementOnExitIfRequired(ojStateMachine, stateClass, vertex, map);
			OJAnnotatedOperation onCompletion=new OJAnnotatedOperation("onComplete",new OJPathName("boolean"));
			stateClass.addToOperations(onCompletion);
			onCompletion.initializeResultVariable("false");
			for(Transition transition:vertex.getOutgoings()){
				OJPathName cn = ojUtil.classifierPathname(transition);
				OJUtil.addTransientProperty(stateClass, cn.getLast(), cn, true);
				if(transition.getTriggers().isEmpty()){
					onCompletion.getBody().addToStatements(new OJIfStatement(cn.getLast()+"." + eventUtil.getEventConsumerName(vertex) + "()", "return true"));
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
	public void implementIsInState(StateMap map,StateMachine umlStateMachine,OJAnnotatedClass ojStateMachine){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(new OJPathName("boolean"));
		ojStateMachine.addToOperations(getter);
		// TODO optimise this to a single method in the implementation class
		OJForStatement forEachToken = new OJForStatement("token", STATE_MACHINE_TOKEN, "getTokens()");
		getter.getBody().addToStatements(forEachToken);
		ojStateMachine.addToImports(map.javaType());
		OJIfStatement ifInstance = new OJIfStatement("token.getCurrentExecutionElement() instanceof " + map.javaType().getLast(), "return true");
		forEachToken.getBody().addToStatements(ifInstance);
		getter.initializeResultVariable("false");
		if(EmfBehaviorUtil.isClassifierBehavior(umlStateMachine)){
			OJAnnotatedClass context = findJavaClass(umlStateMachine.getContext());
			OJOperation copy = getter.getCopy();
			copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." + copy.getName() + "()");
			context.addToOperations(copy);
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
			onEntry.addParam("token", STATE_MACHINE_TOKEN);
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
			for(Transition transition:vertex.getOutgoings()){
				if(transition.getTriggers().size() > 0 && transition.getTriggers().get(0).getEvent() != null){
					Trigger wfe = transition.getTriggers().get(0);
					if(wfe.getEvent() instanceof TimeEvent && EmfEventUtil.isDeadline(wfe.getEvent())){
						// fired and cancelled from task
					}else if(wfe.getEvent() instanceof TimeEvent){
						eventUtil.implementTimeEventRequest(onEntry, onEntry.getBody(), (TimeEvent) wfe.getEvent(),
								getLibrary().getBusinessRole() != null);
					}else if(wfe.getEvent() instanceof ChangeEvent){
						eventUtil.implementChangeEventRequest(onEntry, (ChangeEvent) wfe.getEvent());
					}
				}
			}
			if(vertex instanceof FinalState && vertex.getContainer().getOwner() instanceof State){
				onEntry.getBody().addToStatements("token.setHasRunToCompletion(true)");
				onEntry.getBody().addToStatements("token.getParentToken().fireCompletionEvent()");
			}
		}
	}
	private void implementBehaviorOn(Behavior behavior,OJAnnotatedOperation onEntry,OJBlock block){
		if(behavior instanceof Activity){
			SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
			impl.initialize(javaModel, config, textWorkspace, workspace, ojUtil);
			impl.setWorkspace(workspace);
			impl.implementActivityOn((Activity) behavior, onEntry, block);
		}else if(behavior instanceof OpaqueBehavior){
			OpaqueBehavior b = (OpaqueBehavior) behavior;
			OpaqueBehaviorContext oclBehaviorContext = getLibrary().getOclBehaviorContext(b);
			Classifier voidType = getLibrary().getOclLibrary().getOclVoid();
			block.addToStatements(valueSpecificationUtil.expressOcl(oclBehaviorContext, onEntry, voidType));
		}
	};
	private void transition(Transition transition){
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
			OJAnnotatedOperation getStateMachineExecution = new OJAnnotatedOperation("getStateMachineExecution", ojStateMachine.getPathName());
			transitionInstance.addToOperations(getStateMachineExecution);
			getStateMachineExecution.initializeResultVariable("(" + ojStateMachine.getName() + ")super.getStateMachineExecution()");
			OJPathName TRANSITION_INSTANCE = new OJPathName("org.opaeum.runtime.statemachines.TransitionInstance");
			transitionInstance.setSuperclass(TRANSITION_INSTANCE);
			OJConstructor c = new OJConstructor();
			transitionInstance.addToConstructors(c);
			c.addParam("regionActivation", REGION_ACTIVATION);
			c.setDelegateConstructor("super(\"" + EmfWorkspace.getId(transition) + "\",regionActivation,\""
					+ EmfWorkspace.getId(transition.getSource()) + "\",\"" + EmfWorkspace.getId(transition.getSource()) + "\")");
			OJPathName sourcePathName = ojUtil.classifierPathname(transition.getSource());
			transitionInstance.addToImports(sourcePathName);
			c.getBody().addToStatements("((" + sourcePathName.getLast() + ")getSource()).set" + transitionInstance.getName() + "(this)");
			OJAnnotatedOperation onEvent;
			if(transition.getTriggers().size() > 0 && transition.getTriggers().get(0).getEvent() != null){
				onEvent = createEventConsumerSignature(null, transitionInstance, transition.getTriggers().get(0).getEvent());
			}else{
				onEvent = new OJAnnotatedOperation(eventUtil.getEventConsumerName(transition.getSource()), new OJPathName("boolean"));
				onEvent.initializeResultVariable("false");
				transitionInstance.addToOperations(onEvent);
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
			transitionInstance.addToImports(STATE_MACHINE_TOKEN);
			mainBlock.addToStatements("StateMachineToken token= getMainSource().exit()");
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
			OJPathName classifierPathname = ojUtil.classifierPathname(r);
			OJAnnotatedClass regionActivation = new OJAnnotatedClass(classifierPathname.getLast());
			javaModel.findOrCreatePackage(classifierPathname.getHead()).addToClasses(regionActivation);
			createTextPath(regionActivation, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			regionActivation.setSuperclass(REGION_ACTIVATION);
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
}
