package org.opaeum.linkage;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IPackage;

import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public class EnvironmentFactory{
	INakedModelWorkspace workspace;
	public EnvironmentFactory(INakedModelWorkspace workspace){
		super();
		this.workspace = workspace;
	}
	public Environment createPreEnvironment(INakedOperation owningBehavior){
		Environment pre = createSimpleBehavioralContext(owningBehavior.getOwner(), owningBehavior);
		return pre;
	}
	public Environment createClassifierEnvironment(INakedClassifier c){
		Environment env = createSelflessEnvironment(c);
		env.addElement("self", new VariableDeclaration("self", c), true);
		if(!(c.getNestingClassifier() == null || c instanceof INakedBehavior)){
			env.addElement("owningObject", new VariableDeclaration("owningObject", c.getNestingClassifier()), true);
		}
		env.addStates(c);
		return env;
	}
	public Environment createInstanceValueEnvironment(INakedValueSpecification instanceValue){
		INakedElementOwner owner = instanceValue.getOwnerElement();
		while(owner != null){
			if(owner instanceof INakedValuePin || owner instanceof INakedAction){
				return createActivityEnvironment((INakedElement) owner, ((INakedValuePin) owner).getActivity());
			}else if(owner instanceof INakedTransition){
				INakedTransition t = (INakedTransition) owner;
				Environment env = createStateMachineEnvironment(t.getStateMachine());
				addFlowParameters(env, t);
				return env;
			}else{
				owner = ((INakedElement) owner).getOwnerElement();
			}
		}
		return createSelflessEnvironment(instanceValue.getNakedRoot());
	}
	public Environment createPreEnvironment(INakedClassifier c,INakedAction action){
		Environment env = null;
		env = createSelflessEnvironment(c);
		// Pins will be made available for pre and post conditions on the action
		for(INakedInputPin parm:action.getInput()){
			if(parm.getType() != null && parm.getName() != null){
				// It could be a value pin where ocl parsing failed and the type is unknown
				env.addElement(parm.getName(), new VariableDeclaration(parm.getName(), parm.getType()), false);
			}
		}
		return env;
	}
	public Environment createStateMachineEnvironment(INakedStateMachine owningBehavior){
		return createBehavioralEnvironment(owningBehavior, owningBehavior);
	}
	public Environment createOpaqueBehaviorEnvironment(INakedOpaqueBehavior owningBehavior){
		return createBehavioralEnvironment(owningBehavior, owningBehavior);
	}
	public Environment createBehaviorEnvironment(INakedBehavior owningBehavior){
		return createBehavioralEnvironment(owningBehavior, owningBehavior);
	}
	Environment createActivityEnvironment(INakedElement startingElement,INakedActivity owningBehavior){
		Environment env = createBehavioralEnvironment(owningBehavior, owningBehavior);
		if(BehaviorUtil.hasExecutionInstance(owningBehavior)){
			while(!(startingElement.getOwnerElement() instanceof ActivityNodeContainer)){
				startingElement = (INakedElement) startingElement.getOwnerElement();
			}
			if(startingElement.getOwnerElement() instanceof INakedStructuredActivityNode){
				env.addElement("this", ((INakedStructuredActivityNode) startingElement.getOwnerElement()).getMessageStructure(), true);
			}
			env.addElement("containingActivity", new VariableDeclaration("containingActivity", owningBehavior), true);
		}else{
			addActivityStructureAsLocalContext(env, startingElement, true);
		}
		return env;
	}
	public Environment createOperationMessageEnvironment(INakedOperation op,INakedMessageStructure message){
		// State Actions
		Environment env = null;
		env = createSelflessEnvironment(op.getNameSpace());
		env.addElement("self", new VariableDeclaration("self", message), true);
		env.addElement("contextObject", new VariableDeclaration("contextObject", op.getContext()), true);
		return env;
	}
	public Environment createSelflessEnvironment(INakedNameSpace ns){
		Environment parent = new Environment();
		Environment env = new Environment();
		env.setParent(parent);
		do{
			addPackageContents(ns, env);
			// import everything up to the nearest packag
		}while((ns = ns.getNameSpace()) instanceof INakedClassifier);
		if(ns != null){
			addPackageContents(ns, env);
		}
		if(workspace.getOpaeumLibrary().getDateType() != null){
			env.addElement("now", new VariableDeclaration("now", workspace.getOpaeumLibrary().getDateType()), true);
		}
		for(INakedElement ne:workspace.getOwnedElements()){
			if(ne.getName() != null){
				parent.addElement(ne.getName(), ne, false);
			}
		}
		return env;
	}
	private void addPackageContents(INakedNameSpace p,Environment env){
		if(p == null)
			return;
		for(IClassifier c:p.getClassifiers()){
			if(c instanceof INakedActivity && ((INakedActivity) c).getActivityKind().isSimpleSynchronousMethod()){
			}else{
				env.addElement(c.getName(), c, false);
			}
		}
		for(IImportedElement imp:p.getImports()){
			if(!imp.isReference()){
				env.addElement(imp.getName(), imp.getElement(), false);
			}
		}
		for(IPackage sub:p.getSubpackages()){
			env.addElement(sub.getName(), sub, false);
		}
	}
	private Environment createBehavioralEnvironment(IParameterOwner owningBehavior,INakedClassifier behaviorAsClassifier){
		if(isContextObjectApplicable(owningBehavior)){
			// Complex Activities, StateMachines, Transition Actions and
			// State Actions
			Environment env = null;
			if(BehaviorUtil.hasExecutionInstance(owningBehavior)){
				env = createClassifierEnvironment(behaviorAsClassifier);
			}else{
				// Probably transition effects and state actions
				env = createSimpleBehavioralContext(owningBehavior);
			}
			if(owningBehavior.getContext() != null){
				env.addElement("contextObject", new VariableDeclaration("contextObject", owningBehavior.getContext()), true);
			}
			addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
			return env;
		}else{
			return createSimpleBehavioralContext(owningBehavior);
		}
	}
	private Environment createSimpleBehavioralContext(IParameterOwner owningBehavior){
		// TODO Auto-generated method stub
		INakedElementOwner owner = owningBehavior.getOwnerElement();
		while(!(owner instanceof INakedClassifier || owner == null)){
			owner = ((INakedElement) owner).getOwnerElement();
		}
		if(owner == null){
			owner = owningBehavior.getOwnerElement();
			while(!(owner instanceof INakedNameSpace)){
				owner = ((INakedElement) owner).getOwnerElement();
			}
			Environment env = createSelflessEnvironment((INakedNameSpace) owner);
			addTypedElementsAsVariables(env, owningBehavior.getArgumentParameters());
			return env;
		}else{
			return createSimpleBehavioralContext((INakedClassifier) owner, owningBehavior);
		}
	}
	private Environment createSimpleBehavioralContext(INakedClassifier context,IParameterOwner owningBehavior){
		Environment env = createClassifierEnvironment(context);
		addTypedElementsAsVariables(env, owningBehavior.getArgumentParameters());
		return env;
	}
	public void addFlowParameters(Environment env,INakedActivityEdge edge){
		if(edge instanceof INakedObjectFlow){
			INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
			INakedObjectNode origin = objectFlow.getOriginatingObjectNode();
			if(origin != null && origin.getName() != null && origin.getType() != null){
				env.addElement(origin.getName(), new VariableDeclaration(origin.getName(), origin.getType()), false);
			}
		}else{
			// TODO only for decision nodes
			INakedActivityEdge controlFlow = (INakedActivityEdge) edge;
			if(controlFlow.getEffectiveSource().getIncoming().size() >= 1){
				INakedActivityEdge prev = controlFlow.getEffectiveSource().getIncoming().iterator().next();
				if(prev instanceof INakedObjectFlow){
					addFlowParameters(env, prev);
				}
			}
		}
	}
	public void addFlowParameters(Environment env,INakedTransition t){
		addTypedElementsAsVariables(env, t.getParameters());
	}
	public void addPostEnvironment(Environment env,INakedAction owner){
		for(INakedOutputPin p:owner.getOutput()){
			if(p.getName() != null){
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		}
	}
	public void addPostEnvironment(Environment env,IParameterOwner owner){
		for(INakedParameter p:owner.getResultParameters()){
			if(p.getName() != null && p.getType() != null){
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
			if(p.isReturn() && p.getType() != null){
				env.addElement("result", new VariableDeclaration("result", p.getType()), false);
			}
		}
	}
	private void addTransitionParametersIfBehaviourContainedByTransition(Environment env,IParameterOwner paramOwner){
		if(paramOwner.getOwnerElement() instanceof INakedTransition){
			INakedTransition t = (INakedTransition) paramOwner.getOwnerElement();
			addTypedElementsAsVariables(env, t.getParameters());
		}
	}
	private void addTypedElementsAsVariables(Environment env,Collection<? extends INakedTypedElement> parameters){
		for(INakedTypedElement p:parameters){
			env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
		}
	}
	private static boolean isContextObjectApplicable(IParameterOwner owningBehavior){
		return BehaviorUtil.hasExecutionInstance(owningBehavior) || owningBehavior.getOwnerElement() instanceof INakedTransition
				|| owningBehavior.getOwnerElement() instanceof INakedState;
	}
	private void addActivityStructureAsLocalContext(Environment env,INakedElement element,boolean includeActivity){
		if(element instanceof INakedStructuredActivityNode){
			addVariables(env, ((INakedStructuredActivityNode) element).getVariables());
		}else if(element instanceof INakedActivity && includeActivity){
			addVariables(env, ((INakedActivity) element).getVariables());
			addTypedElementsAsVariables(env, ((INakedActivity) element).getArgumentParameters());
		}
		if(element instanceof INakedExpansionRegion){
			INakedExpansionRegion node = (INakedExpansionRegion) element;
			List<INakedExpansionNode> input = node.getInputElement();
			for(INakedExpansionNode i:input){
				// USe Basetype - from the inside it looks like multiplicity=1
				env.addElement(i.getName(), new VariableDeclaration(i.getName(), i.getNakedBaseType()), false);
			}
		}
		if(!(element instanceof INakedActivity || element == null)){
			addActivityStructureAsLocalContext(env, (INakedElement) element.getOwnerElement(), includeActivity);
		}
	}
	private void addVariables(Environment env,Collection<INakedActivityVariable> variables){
		for(INakedActivityVariable var:variables){
			env.addElement(var.getName(), new VariableDeclaration(var.getName(), var.getType()), false);
		}
	}
}
