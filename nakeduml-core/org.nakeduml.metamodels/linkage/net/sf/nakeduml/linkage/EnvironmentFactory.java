package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IParameter;

public class EnvironmentFactory {
	INakedModelWorkspace workspace;

	public EnvironmentFactory(INakedModelWorkspace workspace) {
		super();
		this.workspace = workspace;
	}

	public Environment createEnvironment(INakedClassifier c) {
		// TODO add a variable that contains 'currentUser'
		Environment parent = new Environment();
		Environment env = new Environment();
		env.setParent(parent);
		env.addElement("self", new VariableDeclaration("self", c), true);
		env.addStates(c);
		INakedNameSpace ns=c;
		while (ns instanceof INakedClassifier && c.getNameSpace().getNameSpace() != null) {
			// import everything up to the nearest package.
			env.addPackageContents(ns);
			ns=ns.getNameSpace();
		}
		if(ns!=null){
			env.addPackageContents(ns);
		}
		
		if (workspace.getMappedTypes().getDateType() != null) {
			env.addElement("now", new VariableDeclaration("now", workspace.getMappedTypes().getDateType()), true);
		}
		for (INakedElement ne : workspace.getOwnedElements()) {
			if (ne.getName() != null) {
				parent.addElement(ne.getName(), ne, false);
			} else {
				System.out.println(ne.getId() + "has no name!!");
			}
		}
		return env;
	}

	public Environment createPreEnvironment(INakedClassifier c, IModelElement element) {
		Environment env = createEnvironment(c);
		Map<String, IClassifier> parameters = new HashMap<String, IClassifier>();
		if (element instanceof IParameterOwner) {
			addAllParameters(env, (IParameterOwner) element);
		} else if (element instanceof INakedAction) {
			for (INakedInputPin parm : ((INakedAction) element).getInput()) {
				parameters.put(parm.getName(), parm.getType());
			}
		}
		for (Map.Entry<String, IClassifier> e : parameters.entrySet()) {
			env.addElement(e.getKey(), new VariableDeclaration(e.getKey(), e.getValue()), false);
		}
		return env;
	}

	public Environment prepareBehaviorEnvironment(INakedElement element, INakedBehavior owningBehavior) {
		Environment env;
		if (isContextObjectApplicable(owningBehavior)) {
			// Complex Activities, StateMachines, Transition Actions and
			// State Actions
			env = createPreEnvironment(BehaviorUtil.getNearestActualClass(owningBehavior), element);
			if (owningBehavior.getContext() != null) {
				env.addElement("contextObject", new VariableDeclaration("contextObject", owningBehavior.getContext()), true);
			}
			addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
			if (BehaviorUtil.hasExecutionInstance(owningBehavior)) {
				env.addElement("this", new VariableDeclaration("this", new ActivityVariableContext(owningBehavior, element, owningBehavior)), true);
				addActivityStructureAsLocalContext(env, element, false);
			} else {
				addActivityStructureAsLocalContext(env, element, true);
			}
		} else {
			// Simple Synchronous methods
			env = createPreEnvironment(owningBehavior.getContext(), element);
			addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
			addActivityStructureAsLocalContext(env, element, true);
		}
		return env;
	}

	public void addFlowParameters(Environment env, GuardedFlow edge) {
		if (edge instanceof INakedTransition) {
			List<? extends INakedTypedElement> parameters = ((INakedTransition) edge).getParameters();
			for (INakedTypedElement p : parameters) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		} else if (edge instanceof INakedObjectFlow) {
			INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
			INakedObjectNode origin = objectFlow.getOriginatingObjectNode();
			if (origin != null) {
				env.addElement(origin.getName(), new VariableDeclaration(origin.getName(), origin.getType()), false);
			}
		}else {
			//TODO only for decision nodes
			INakedActivityEdge controlFlow= (INakedActivityEdge) edge;
			if(controlFlow.getEffectiveSource().getIncoming().size()>=1){
				INakedActivityEdge prev = controlFlow.getEffectiveSource().getIncoming().iterator().next();
				if(prev instanceof INakedObjectFlow){
					addFlowParameters(env, prev);
				}
			}
		}
	}

	public void addPostEnvironment(Environment env, IModelElement element) {
		if (element instanceof IParameterOwner) {
			IParameterOwner owner = (IParameterOwner) element;
			for (INakedParameter p : owner.getResultParameters()) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
				if (p.isReturn()) {
					env.addElement("result", new VariableDeclaration("result", p.getType()), false);
				}
			}
		} else if (element instanceof INakedAction) {
			INakedAction owner = (INakedAction) element;
			for (INakedOutputPin p : owner.getOutput()) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		}
	}

	private void addTransitionParametersIfBehaviourContainedByTransition(Environment env, IParameterOwner paramOwner) {
		if (paramOwner.getOwnerElement() instanceof INakedTransition) {
			INakedTransition t = (INakedTransition) paramOwner.getOwnerElement();
			for (INakedTypedElement p : t.getParameters()) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		}
	}

	private void addAllParameters(Environment env, IParameterOwner paramOwner) {
		for (IParameter p : paramOwner.getParameters()) {
			env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
		}
	}

	private static boolean isContextObjectApplicable(INakedBehavior owningBehavior) {
		boolean isContextApplicable = owningBehavior instanceof INakedStateMachine
				|| (owningBehavior instanceof INakedActivity && ((INakedActivity) owningBehavior).getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD)
				|| owningBehavior.getOwnerElement() instanceof INakedTransition || owningBehavior.getOwnerElement() instanceof INakedState;
		return isContextApplicable;
	}

	private void addActivityStructureAsLocalContext(Environment env, INakedElement element, boolean includeActivity) {
		if (element instanceof INakedStructuredActivityNode) {
			addVariables(env, ((INakedStructuredActivityNode) element).getVariables());
		} else if (element instanceof INakedActivity && includeActivity) {
			addVariables(env, ((INakedActivity) element).getVariables());
			addAllParameters(env, (IParameterOwner) element);
		}
		if (element instanceof INakedExpansionRegion) {
			INakedExpansionRegion node = (INakedExpansionRegion) element;
			List<INakedExpansionNode> input = node.getInputElement();
			for (INakedExpansionNode i : input) {
				// USe Basetype - from the inside it looks like multiplicity=1
				env.addElement(i.getName(), new VariableDeclaration(i.getName(), i.getNakedBaseType()), false);
			}
		}
		if (!(element instanceof INakedActivity || element == null)) {
			addActivityStructureAsLocalContext(env, (INakedElement) element.getOwnerElement(), includeActivity);
		}
	}

	private void addVariables(Environment env, Collection<INakedActivityVariable> variables) {
		for (INakedActivityVariable var : variables) {
			env.addElement(var.getName(), new VariableDeclaration(var.getName(), var.getType()), false);
		}
	}
}
