package org.opaeum.linkage;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IParameter;

import org.eclipse.uml2.uml.ActivityNodeContainer;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedActivity;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityVariable;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedExpansionRegion;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedMessageStructure;
import org.eclipse.uml2.uml.INakedNameSpace;
import org.eclipse.uml2.uml.INakedObjectFlow;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOpaqueBehavior;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedParameter;
import org.eclipse.uml2.uml.INakedState;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.eclipse.uml2.uml.INakedStructuredActivityNode;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.INakedTypedElement;
import org.eclipse.uml2.uml.INakedValuePin;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.eclipse.uml2.uml.IParameterOwner;
import org.opaeum.metamodel.activities.internal.StructuredActivityNodeClassifier;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public class EnvironmentFactory{
	ModelWorkspace workspace;
	public EnvironmentFactory(ModelWorkspace workspace){
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
		return createBehavioralEnvironment(owningBehavior);
	}
	public Environment createOpaqueBehaviorEnvironment(INakedOpaqueBehavior owningBehavior){
		return createBehavioralEnvironment(owningBehavior);
	}
	public Environment createBehaviorEnvironment(INakedBehavior owningBehavior){
		return createBehavioralEnvironment(owningBehavior);
	}
	Environment createActivityEnvironment(INakedElement startingElement,INakedActivity owningBehavior){
		Environment env = null;
		if(BehaviorUtil.hasExecutionInstance(owningBehavior)){
			while(!(startingElement instanceof ActivityNodeContainer)){
				startingElement = (INakedElement) startingElement.getOwnerElement();
			}
			if(startingElement instanceof INakedStructuredActivityNode){
				INakedStructuredActivityNode node = (INakedStructuredActivityNode) startingElement;
				StructuredActivityNodeClassifier msg = (StructuredActivityNodeClassifier) node.getMessageStructure();
				env = createSelflessEnvironment(msg);
				env.addElement("self", msg.getEndToSelf(), true);
				if(owningBehavior.getContext() != null){
					env.addElement("contextObject", msg.getEndToContext(), true);
				}
				env.addElement("this", new VariableDeclaration("this", msg), true);
			}else{
				env = createBehavioralEnvironment(owningBehavior);
				if(owningBehavior.getContext() != null){
					env.addElement("contextObject", owningBehavior.getEndToComposite(), true);
				}
			}
			if(owningBehavior.getEndToComposite() != null){
				// Not entirely correct - might but Octop
			}
		}else if(isContextObjectApplicable(owningBehavior)){
			env = createBehavioralEnvironment(owningBehavior);
			addActivityStructureAsLocalContext(env, startingElement, true);
			INakedElementOwner owner = owningBehavior.getOwnerElement();
			while(!(owner == null || owner instanceof INakedStateMachine)){
				owner = ((INakedElement) owner).getOwnerElement();
			}
			if(owner instanceof INakedStateMachine && ((INakedStateMachine) owner).getEndToComposite() != null){
				env.addElement("contextObject", ((INakedStateMachine) owner).getEndToComposite(), true);
			}
		}else{
			env = createBehavioralEnvironment(owningBehavior);
			addActivityStructureAsLocalContext(env, startingElement, true);
		}
		return env;
	}
	public Environment createOperationMessageEnvironment(INakedOperation op,INakedMessageStructure message){
		Environment env = null;
		env = createSelflessEnvironment(op.getNameSpace());
		env.addElement("self", ((OperationMessageStructureImpl) message).getEndToSelf(), true);
		env.addElement("this", new VariableDeclaration("this", message), true);
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
			env.addElement("now", new VariableDeclaration("now", workspace.getOpaeumLibrary().getDateType()), false);
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
			}else if(c.getName()!=null){
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
	private Environment createBehavioralEnvironment(INakedBehavior owningBehavior){
		if(isContextObjectApplicable(owningBehavior)){
			// Complex Activities, StateMachines, Transition Actions and
			// State Actions
			Environment env = null;
			if(BehaviorUtil.hasExecutionInstance(owningBehavior)){
				env = createClassifierEnvironment(owningBehavior);
				if(owningBehavior.getEndToComposite() != null){
					env.addElement("contextObject", owningBehavior.getEndToComposite(), true);
				}
			}else{
				// Probably transition effects and state actions
				env = createSimpleBehavioralContext(owningBehavior);
				INakedElementOwner owner = owningBehavior.getOwnerElement();
				while(!(owner == null || owner instanceof INakedStateMachine)){
					owner = ((INakedElement) owner).getOwnerElement();
				}
				if(owner instanceof INakedStateMachine && ((INakedStateMachine) owner).getEndToComposite() != null){
					env.addElement("contextObject", ((INakedStateMachine) owner).getEndToComposite(), true);
				}
			}
			addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
			return env;
		}else{
			return createSimpleBehavioralContext(owningBehavior);
		}
	}
	private Environment createSimpleBehavioralContext(IParameterOwner owningBehavior){
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
			addTypedElementsAsParameters(env, owningBehavior.getArgumentParameters());
			return env;
		}else{
			return createSimpleBehavioralContext((INakedClassifier) owner, owningBehavior);
		}
	}
	private Environment createSimpleBehavioralContext(INakedClassifier context,IParameterOwner owningBehavior){
		Environment env = createClassifierEnvironment(context);
		addTypedElementsAsParameters(env, owningBehavior.getArgumentParameters());
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
		addTypedElementsAsParameters(env, t.getParameters());
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
			addTypedElementsAsParameters(env, t.getParameters());
		}
	}
	private void addTypedElementsAsParameters(Environment env,Collection<? extends INakedTypedElement> parameters){
		for(final INakedTypedElement p:parameters){
			if(p instanceof IParameter){
				env.addElement(p.getName(), (IParameter) p, false);
			}else{
				env.addElement(p.getName(), new SignalParameter(p), false);
			}
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
			addTypedElementsAsParameters(env, ((INakedActivity) element).getArgumentParameters());
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
