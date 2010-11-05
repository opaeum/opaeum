package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatingElement;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;
import net.sf.nakeduml.validation.namegeneration.UmlNameRegenerator;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.analysis.expressions.AnalysisException;
import nl.klasse.octopus.expressions.internal.analysis.expressions.ExpressionAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.javacc.OclParser;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.ModelElementReferenceImpl;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclErrContextImpl;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.library.StdlibBasic;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;
import nl.klasse.tools.common.Check;

@StepDependency(phase = OclParsingPhase.class, after = { EnumerationValuesAttributeAdder.class }, requires = { MappedTypeLinker.class,
		PinLinker.class, ReferenceResolver.class, TypeResolver.class, ValueSpecificationTypeResolver.class, UmlNameRegenerator.class,
		EnumerationValuesAttributeAdder.class })
public class NakedParsedOclStringResolver extends AbstractModelElementLinker {
	public static final class ActivityVariableContext extends MessageStructureImpl {
		private final INakedBehavior activity;

		public ActivityVariableContext(INakedClassifier owner, INakedElement element, INakedBehavior activity) {
			super(owner, element);
			this.activity = activity;
		}

		@Override
		public IPackage getRoot() {
			return activity.getRoot();
		}

		@Override
		public List<? extends INakedConstraint> getOwnedRules() {
			return Collections.emptyList();
		}

		@Override
		public List<? extends IOclContext> getDefinitions() {
			return Collections.emptyList();
		}

		@Override
		public boolean isPersistent() {
			return false;
		}

		@Override
		public List<INakedProperty> getOwnedAttributes() {
			List<INakedProperty> result = new ArrayList<INakedProperty>();
			addVariables(result, element);
			return result;
		}

		private void addVariables(List<INakedProperty> result, INakedElement element) {
			Collection<INakedActivityVariable> variables = null;
			if (element instanceof INakedActivity) {
				variables = ((INakedActivity) element).getVariables();
			} else if (element instanceof INakedStructuredActivityNode) {
				variables = ((INakedStructuredActivityNode) element).getVariables();
			}
			if (variables != null) {
				for (INakedActivityVariable var : variables) {
					result.add(new TypedElementPropertyBridge(activity, var));
				}
			}
			if (!(element == null || element instanceof INakedActivity)) {
				addVariables(result, (INakedElement) element.getOwnerElement());
			}
		}
	}

	@VisitBefore
	public void visitModel(INakedModel m) {
		NakedOperationImpl.VOID_TYPE = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitSlot(INakedSlot slot) {
		for (INakedValueSpecification s : slot.getValues()) {
			if (s.getValue() instanceof ParsedOclString) {
				ParsedOclString string = (ParsedOclString) s.getValue();
				INakedClassifier context = slot.getOwningInstance().getClassifier();
				string.setContext(context, context);
				Environment env = createEnvironment(context);
				s.setValue(replaceSingleParsedOclString(string, context, s.getType(), env));
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitGuardedFlow(GuardedFlow edge) {
		if (edge.getGuard() != null && edge.getGuard().getValue() instanceof ParsedOclString) {
			ParsedOclString string = (ParsedOclString) edge.getGuard().getValue();
			string.setContext(edge.getOwningBehavior(), edge.getOwningBehavior());
			IClassifier booleanType = getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			INakedBehavior owningBehavior = edge.getOwningBehavior();
			if (isContextObjectApplicable(owningBehavior)) {
				// Complex Activities, StateMachines, Transition Actions and
				// State Actions
				Environment env = createPreEnvironment(getNearestActualClass(owningBehavior), edge);
				if (owningBehavior.getContext() != null) {
					env.addElement("contextObject", new VariableDeclaration("contextObject", owningBehavior.getContext()), true);
				}
				addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
				if (BehaviorUtil.hasExecutionInstance(edge.getOwningBehavior())) {
					env.addElement("this",
							new VariableDeclaration("this", new ActivityVariableContext(edge.getOwningBehavior(), edge, edge.getOwningBehavior())),
							true);
				} else {
					addActivityStructureAsLocalContext(env, edge.getOwningBehavior());
				}
				addFlowParameters(env, edge);
				edge.getGuard().setValue(replaceSingleParsedOclString(string, owningBehavior, booleanType, env));
			} else {
				// Simple Synchronous methods
				Environment env = createPreEnvironment(owningBehavior.getContext(), edge);
				addFlowParameters(env, edge);
				addTransitionParametersIfBehaviourContainedByTransition(env, owningBehavior);
				addActivityStructureAsLocalContext(env, edge);
				edge.getGuard().setValue(replaceSingleParsedOclString(string, owningBehavior, booleanType, env));
			}
		}
	}

	private void addFlowParameters(Environment env, GuardedFlow edge) {
		if (edge instanceof INakedTransition) {
			List<INakedParameter> parameters = ((INakedTransition) edge).getParameters();
			for (INakedParameter p : parameters) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		} else if (edge instanceof INakedObjectFlow) {
			INakedObjectNode source = (INakedObjectNode) ((INakedObjectFlow) edge).getSource();
			env.addElement(source.getName(), new VariableDeclaration(source.getName(), source.getType()), false);
		}
	}

	private boolean isContextObjectApplicable(INakedBehavior owningBehavior) {
		boolean isContextApplicable = owningBehavior instanceof INakedStateMachine
				|| (owningBehavior instanceof INakedActivity && ((INakedActivity) owningBehavior).getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD)
				|| owningBehavior.getOwnerElement() instanceof INakedTransition || owningBehavior.getOwnerElement() instanceof INakedState;
		return isContextApplicable;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueBehavior(INakedOpaqueBehavior ob) {
		IClassifier returnType = null;
		if (ob.getReturnParameter() == null) {
			// OCL cannot implement or call void operations. this is a
			// workaround
			returnType = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		} else {
			returnType = ob.getReturnParameter().getType();
		}
		INakedClassifier c = ob.getContext();
		INakedValueSpecification body = ob.getBody();
		if (body != null && body.getValue() instanceof ParsedOclString) {
			ParsedOclString bodyExpression = (ParsedOclString) body.getValue();
			bodyExpression.setContext(ob.getContext(), ob);
			if (ob.getContext() == null) {
				INakedClassifier surrogateContext = getNearestActualClass(ob.getOwnerElement());
				Environment env = createPreEnvironment(surrogateContext, ob);
				body.setValue(replaceSingleParsedOclString(bodyExpression, surrogateContext, returnType, env));
				body.setType(returnType);
			} else {
				Environment env = createPreEnvironment(ob.getContext(), ob);
				body.setValue(replaceSingleParsedOclString(bodyExpression, c, returnType, env));
				body.setType(returnType);
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty attr) {
		INakedClassifier c = attr.getOwner();
		INakedValueSpecification iv = attr.getInitialValue();
		if (iv != null && iv.isOclValue()) {
			ParsedOclString oclValue = (ParsedOclString) iv.getOclValue();
			oclValue.setContext(c, attr);
			Environment env = createEnvironment(c);
			iv.setValue(replaceSingleParsedOclString(oclValue, c, attr.getType(), env));
			iv.setType(attr.getType());
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc) {
		replaceParcedOclStrings(nc, nc.getOwnedRules());
		List<IOclContext> loopResults = new ArrayList<IOclContext>(replaceParcedOclStringsForOclContext(nc, nc, nc.getDefinitions()));
		nc.setDefinitions(loopResults);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitTimeEvent(NakedTimeEventImpl ev) {
		INakedValueSpecification w = ev.getWhen();
		if (w != null && w.getValue() instanceof ParsedOclString) {
			// Remember that time events' when expression MUST have a type
			// specified
			Environment env = createEnvironment(ev.getContext());
			ParsedOclString value = (ParsedOclString) w.getValue();
			value.setContext(ev.getContext(), ev.getContext());
			w.setValue(replaceSingleParsedOclString(value, ev.getContext(), w.getType(), env));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation op) {
		if (op.getBodyCondition() != null && op.getBodyCondition().getSpecification() != null
				&& op.getBodyCondition().getSpecification().getValue() instanceof ParsedOclString) {
			INakedValueSpecification bodyCondition = op.getBodyCondition().getSpecification();
			ParsedOclString bodyExpression = (ParsedOclString) bodyCondition.getOclValue();
			INakedClassifier owner = op.getOwner();
			bodyExpression.setContext(owner, op);
			Environment env = createPreEnvironment(owner, op);
			bodyCondition.setValue(replaceSingleParsedOclString(bodyExpression, owner, op.getReturnType(), env));
			bodyCondition.setType(op.getReturnType());
			// TODO support OpaqueBehavior for effects.
		}
		op.setPreConditions(replaceParcedOclStringsForOclContext(op.getOwner(), op, op.getPreConditions()));
		op.setPostConditions(replaceParcedOclStringsForOclContext(op.getOwner(), op, op.getPostConditions()));
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b) {
		INakedClassifier ctx = b.getContext();
		if (ctx == null) {
			ctx = b;
		}
		b.setPreConditions(replaceParcedOclStringsForOclContext(ctx, b, b.getPreConditions()));
		b.setPostConditions(replaceParcedOclStringsForOclContext(ctx, b, b.getPostConditions()));
	}

	@VisitBefore(matchSubclasses = true)
	public void visitAction(INakedAction a) {
		INakedClassifier ctx = a.getContext();
		if (ctx == null) {
			ctx = a.getActivity();
		}
		a.setPreConditions(replaceParcedOclStringsForOclContext(ctx, a, a.getPreConditions()));
		a.setPostConditions(replaceParcedOclStringsForOclContext(ctx, a, a.getPostConditions()));
	}

	@VisitBefore(matchSubclasses = true)
	public void visitValuePin(INakedValuePin pin) {
		if (pin.getValue() != null) {
			if (pin.getValue().getValue() instanceof ParsedOclString) {
				resolvePinOcl(pin);
			}
			overridePinType(pin);
		}
	}

	private void overridePinType(INakedValuePin pin) {
		IClassifier type = null;
		if (pin.getValue().isValidOclValue()) {
			// Override type and multiplicity of Pin
			type = pin.getValue().getOclValue().getExpression().getExpressionType();
		} else if (pin.getValue().isLiteral()) {
			if (pin.getValue().getValue() instanceof Boolean) {
				type = getBuiltInTypes().getBooleanType();
			} else if (pin.getValue().getValue() instanceof String) {
				type = getBuiltInTypes().getStringType();
			} else if (pin.getValue().getValue() instanceof Integer) {
				type = getBuiltInTypes().getIntegerType();
			} else if (pin.getValue().getValue() instanceof Double) {
				type = getBuiltInTypes().getRealType();
			}
		}
		if (type != null) {
			pin.setType(type);
			pin.getValue().setType(type);
			if (type instanceof INakedClassifier) {
				pin.setBaseType((INakedClassifier) type);
				pin.setMultiplicity(new NakedMultiplicityImpl(pin.getNakedMultiplicity().getLower(), 1));
			} else if (type instanceof ICollectionType) {
				ICollectionType collectionType = (ICollectionType) type;
				pin.setBaseType((INakedClassifier) collectionType.getElementType());
				pin.setMultiplicity(new NakedMultiplicityImpl(pin.getNakedMultiplicity().getLower(), Integer.MAX_VALUE));
			} else if (type instanceof StdlibPrimitiveType) {
				StdlibPrimitiveType standardType = (StdlibPrimitiveType) type;
				pin.setBaseType(getBuiltInTypes().lookupStandardType(standardType));
			}
		}
	}

	private void resolvePinOcl(INakedValuePin pin) {
		ParsedOclString string = (ParsedOclString) pin.getValue().getValue();
		Environment env = null;
		INakedClassifier nearestActualClass = getNearestActualClass(pin.getActivity());
		string.setContext(nearestActualClass, nearestActualClass);
		if (isContextObjectApplicable(pin.getActivity())) {
			// complex activities or transition effects or state actions
			env = createEnvironment(nearestActualClass);
			if (pin.getActivity().getContext() != null) {
				env.addElement("contextObject", new VariableDeclaration("contextObject", pin.getActivity().getContext()), true);
			}
			addTransitionParametersIfBehaviourContainedByTransition(env, pin.getActivity());
			final INakedActivity activity = pin.getActivity();
			if (pin.getActivity().getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
				addActivityStructureAsLocalContext(env, pin);
			} else {
				env.addElement("this", new VariableDeclaration("this", new ActivityVariableContext(pin.getActivity(), pin, activity)), true);
			}
			pin.getValue().setValue(replaceSingleParsedOclString(string, nearestActualClass, pin.getType(), env));
		} else {
			// Simple Activities
			INakedClassifier context = pin.getActivity().getContext();
			env = createEnvironment(pin.getActivity().getContext());
			addAllParameters(env, pin.getActivity());
			addActivityStructureAsLocalContext(env, pin);
			addTransitionParametersIfBehaviourContainedByTransition(env, pin.getActivity());
			pin.getValue().setValue(replaceSingleParsedOclString(string, context, pin.getType(), env));
		}
	}

	private INakedClassifier getNearestActualClass(INakedElementOwner ownerElement) {
		// Returns the first ownerElement that has an OJClass
		while (ownerElement instanceof INakedElement
				&& !(ownerElement instanceof INakedClassifier && OJUtil.hasOJClass((INakedClassifier) ownerElement))) {
			ownerElement = ((INakedElement) ownerElement).getOwnerElement();
		}
		if (ownerElement instanceof INakedClassifier) {
			return (INakedClassifier) ownerElement;
		} else {
			return null;
		}
	}

	private void addActivityStructureAsLocalContext(Environment env, INakedElement element) {
		if (element instanceof INakedStructuredActivityNode) {
			addVariables(env, ((INakedStructuredActivityNode) element).getVariables());
		} else if (element instanceof INakedActivity) {
			addVariables(env, ((INakedActivity) element).getVariables());
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
			addActivityStructureAsLocalContext(env, (INakedElement) element.getOwnerElement());
		}
	}

	private void addVariables(Environment env, Collection<INakedActivityVariable> variables) {
		for (INakedActivityVariable var : variables) {
			env.addElement(var.getName(), new VariableDeclaration(var.getName(), var.getType()), false);
		}
	}

	private void replaceParcedOclStrings(INakedClassifier c, Collection<? extends INakedConstraint> invs) {
		for (INakedConstraint cont : invs) {
			if (cont.getSpecification().isOclValue() && cont.getSpecification().getOclValue() instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont.getSpecification().getOclValue();
				holder.setContext(c, c);
				IClassifier basicType = StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName);
				Environment env = createEnvironment(c);
				if (cont.getConstrainedElement() instanceof INakedProperty) {
					basicType = getOclLibrary().lookupCollectionType(CollectionMetaType.SET, basicType);
				}
				cont.getSpecification().setValue(replaceSingleParsedOclString(holder, c, basicType, env));
			}
		}
	}

	private Collection<IOclContext> replaceParcedOclStringsForOclContext(INakedClassifier c, INakedElement element,
			Collection<? extends IOclContext> invs) {
		Collection<IOclContext> loopResults = new ArrayList<IOclContext>();
		for (IOclContext cont : invs) {
			if (cont instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont;
				Environment env = createPreEnvironment(c, element);
				if (holder.getType().equals(OclUsageType.POST)) {
					addPostEnvironment(env, element);
				}
				loopResults.add(replaceSingleParsedOclString(holder, c, StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName), env));
			}
		}
		return loopResults;
	}

	private IOclContext replaceSingleParsedOclString(ParsedOclString holder, INakedClassifier c, IClassifier expectedType, Environment env) {
		INakedNameSpace ns = c.getNameSpace();
		OclEngine engine = new OclEngine();
		engine.setOclLibrary(getOclLibrary());
		List<IOclError> localErrors = new ArrayList<IOclError>();
		IOclExpression ast = null;
		ExpressionAnalyzer ea = new ExpressionAnalyzer("model", localErrors);
		java.io.StringReader sr = new java.io.StringReader(holder.getExpressionString());
		java.io.Reader r = new java.io.BufferedReader(sr);
		OclParser parser = new OclParser(r);
		try {
			ast = ea.analyzeParsetree(parser.OclExpression(), c, ns, env);
			IOclContext newC = transformIntoOclContext(holder, ast, localErrors);
			IClassifier expressionType = newC.getExpression().getExpressionType();
			if (expectedType != null) {
				// Strings are default types, assume no type has been set
				if (!expressionType.conformsTo(expectedType)) {
					this.getErrorMap().putError(
							holder,
							CoreValidationRule.OCL,
							"Return value of type " + expectedType.getName() + " expected, but the expression returns a value of type "
									+ expressionType.getName());
				}
			}
			return newC;
		} catch (AnalysisException e) {
			System.out.println(holder.getExpressionString());
			e.printStackTrace(System.out);
			this.getErrorMap().putError(holder, CoreValidationRule.OCL, e.getError().getErrorMessage());
			OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
			errCtx.setExpressionString(holder.getExpressionString());
			return errCtx;
		} catch (Exception e) {
			System.out.println(holder.getExpressionString());
			if (localErrors.size() > 0) {
				for (IOclError oe : localErrors) {
					System.out.println(oe);
				}
				putErrors(holder, localErrors);
			}
			e.printStackTrace();
			putError(holder, e);
			OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
			errCtx.setExpressionString(holder.getExpressionString());
			return errCtx;
		}
	}

	private void addPostEnvironment(Environment env, IModelElement element) {
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

	private Environment createEnvironment(INakedClassifier c) {
		// TODO add a variable that contains 'currentUser'
		Environment parent = new Environment();
		Environment env = new Environment();
		env.addPackageContents(c.getNameSpace());
		env.setParent(parent);
		env.addElement("self", new VariableDeclaration("self", c), true);
		env.addStates(c);
		while (c.getNameSpace() instanceof INakedClassifier && c.getNameSpace().getNameSpace() != null) {
			// import everything up to the nearest package.
			c = (INakedClassifier) c.getNameSpace();
			env.addPackageContents(c.getNameSpace());
		}
		if (getBuiltInTypes().getDateType() != null) {
			env.addElement("now", new VariableDeclaration("now", getBuiltInTypes().getDateType()), true);
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

	private Environment createPreEnvironment(INakedClassifier c, IModelElement element) {
		Environment env = createEnvironment(c);
		Map<String, IClassifier> parameters = new HashMap<String, IClassifier>();
		if (element instanceof IParameterOwner) {
			addAllParameters(env, (IParameterOwner) element);
		} else if (element instanceof INakedAction) {
			for (INakedInputPin parm : ((INakedAction) element).getInput()) {
				parameters.put(parm.getName(), parm.getType());
			}
		} else if (element instanceof INakedTransition) {
			for (IParameter parm : ((INakedTransition) element).getParameters()) {
				parameters.put(parm.getName(), parm.getType());
			}
		}
		for (Map.Entry<String, IClassifier> e : parameters.entrySet()) {
			env.addElement(e.getKey(), new VariableDeclaration(e.getKey(), e.getValue()), false);
		}
		return env;
	}

	private void addAllParameters(Environment env, IParameterOwner paramOwner) {
		for (IParameter p : paramOwner.getParameters()) {
			env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
		}
	}

	private void addTransitionParametersIfBehaviourContainedByTransition(Environment env, IParameterOwner paramOwner) {
		if (paramOwner.getOwnerElement() instanceof INakedTransition) {
			INakedTransition t = (INakedTransition) paramOwner.getOwnerElement();
			for (INakedParameter p : t.getParameters()) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		}
	}

	private void putError(ParsedOclString holder, Exception e) {
		this.getErrorMap().putError(holder, CoreValidationRule.OCL, e.toString());
	}

	private IOclLibrary getOclLibrary() {
		return this.workspace.getOclEngine().getOclLibrary();
	}

	private IOclContext transformIntoOclContext(ParsedOclString holder, IOclExpression ast, List<IOclError> localErrors) {
		IOclContext result = null;
		OclUsageType type = holder.getType();
		if (ast != null) {
			OclContextImpl replacement = new OclContextImpl(new String(holder.getName()), type, new NakedElementReference(
					(ModelElementReferenceImpl) holder.getContext()), ast);
			replacement.setExpressionByUser(holder.getExpressionString());
			replacement.setLineAndColumn(holder.getLine(), holder.getColumn());
			replacement.setFilename(holder.getFilename());
			result = replacement;
			// TODO ensure that collection types are correctly implemented for
			// possible empty collections
			if (!localErrors.isEmpty()) {
				putErrors(holder, localErrors);
			}
		} else {
			OclErrContextImpl replacement = new OclErrContextImpl(new String(holder.getName()), type, holder.getContext());
			replacement.setExpressionString(holder.getExpressionString());
			replacement.setErrors(localErrors);
			replacement.setLineAndColumn(holder.getLine(), holder.getColumn());
			replacement.setFilename(holder.getFilename());
			result = replacement;
			putErrors(holder, localErrors);
		}
		return result;
	}

	private void putErrors(ParsedOclString holder, List<IOclError> localErrors) {
		for (IOclError oe : localErrors) {
			this.getErrorMap().putError(holder, CoreValidationRule.OCL, oe.getErrorMessage());
		}
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		// Only do the selected model
		if (root instanceof INakedModelWorkspace) {
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		} else {
			return super.getChildren(root);
		}
	}
}
