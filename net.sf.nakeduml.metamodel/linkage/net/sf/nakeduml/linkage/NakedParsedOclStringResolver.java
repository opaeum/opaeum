package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
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
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedPropertyBridge;
import net.sf.nakeduml.metamodel.models.INakedModel;
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

@StepDependency(phase = OclParsingPhase.class, after = { EnumerationValuesAttributeAdder.class }, requires = { MappedTypeLinker.class,
		PinLinker.class, ReferenceResolver.class, TypeResolver.class, ValueSpecificationTypeResolver.class, UmlNameRegenerator.class,
		EnumerationValuesAttributeAdder.class })
public class NakedParsedOclStringResolver extends AbstractModelElementLinker {
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
	public void processActivityEdge(GuardedFlow edge) {
		if (edge.getGuard() != null && edge.getGuard().getValue() instanceof ParsedOclString) {
			ParsedOclString string = (ParsedOclString) edge.getGuard().getValue();
			IClassifier booleanType = getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			Environment env = createPreEnvironment(edge.getContext(), edge);
			edge.getGuard().setValue(replaceSingleParsedOclString(string, edge.getOwningBehavior(), booleanType, env));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueBehavior(INakedOpaqueBehavior ob) {
		IClassifier returnType = null;
		if (ob.getReturnParameter() == null) {
			returnType = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		} else {
			returnType = ob.getReturnParameter().getType();
		}
		// OCL cannot implement or call void opertaions
		INakedClassifier c = ob.getContext();
		INakedValueSpecification body = ob.getBody();
		if (body != null && body.isOclValue()) {
			ParsedOclString bodyExpression = (ParsedOclString) ob.getBodyExpression();
			bodyExpression.setContext(ob.getContext(), ob);
			Environment env = createPreEnvironment(ob.getContext(), ob);
			body.setValue(replaceSingleParsedOclString(bodyExpression, c, returnType, env));
			body.setType(returnType);
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
			w.setValue(replaceSingleParsedOclString((ParsedOclString) w.getValue(), ev.getContext(), w.getType(), env));
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
	}

	@VisitBefore(matchSubclasses = true)
	public void visitPreAndPostConstrained(PreAndPostConstrained op) {
		INakedClassifier ctx = op.getContext();
		if (ctx == null) {
			if (op instanceof INakedOperation) {
				ctx = ((INakedOperation) op).getOwner();
			} else {
				ctx = (INakedClassifier) op;
			}
		}
		op.setPreConditions(replaceParcedOclStringsForOclContext(ctx, op, op.getPreConditions()));
		op.setPostConditions(replaceParcedOclStringsForOclContext(ctx, op, op.getPostConditions()));
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
		INakedClassifier context = null;
		if (pin.getActivity().getActivityKind().isSimpleSynchronousMethod()) {
			context = pin.getActivity().getContext() == null ? pin.getActivity() : pin.getActivity().getContext();
		} else {
			context = pin.getActivity();
		}
		string.setContext(context, pin.getActivity());
		Environment env = createEnvironment(context);
		env.addElement("contextObject", new VariableDeclaration("contextObject", context), true);
		if (!BehaviorUtil.hasExecutionInstance(pin.getActivity())) {
			addAllParameters(env, pin.getActivity());
		}
		pin.getValue().setValue(replaceSingleParsedOclString(string, context, pin.getType(), env));
	}

	private void replaceParcedOclStrings(INakedClassifier c, Collection<? extends INakedConstraint> invs) {
		for (INakedConstraint cont : invs) {
			if (cont.getSpecification().isOclValue() && cont.getSpecification().getOclValue() instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont.getSpecification().getOclValue();
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
		if (c instanceof INakedBehavior && ((INakedBehavior) c).getContext() != null) {
			INakedBehavior b = (INakedBehavior) c;
			env.addElement("contextObject", new VariableDeclaration("contextObject", b.getContext()), true);
			// TODO if it is a transition guard add the event parameters
			// Iterator iter = ((INakedBehavior)
			// c).getArgumentParameters().iterator();
			// while( iter.hasNext() ){
			// IParameter param = (IParameter)iter.next();
			// env.addElement(param.getName(),
			// new VariableDeclaration(param.getName(), param.getType()),
			// false);
			// }
		}
		ExpressionAnalyzer ea = new ExpressionAnalyzer("model", localErrors);
		java.io.StringReader sr = new java.io.StringReader(holder.getExpressionString());
		java.io.Reader r = new java.io.BufferedReader(sr);
		OclParser parser = new OclParser(r);
		try {
			ast = ea.analyzeParsetree(parser.OclExpression(), c, ns, env);
			IOclContext newC = transformIntoOclContext(holder, ast, localErrors);
			IClassifier expressionType = newC.getExpression().getExpressionType();
			if (expectedType != null) {
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
			return new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
		} catch (Exception e) {
			if (localErrors.size() > 0) {
				for (IOclError oe : localErrors) {
					System.out.println(oe);
				}
				putErrors(holder, localErrors);
			}
			e.printStackTrace();
			putError(holder, e);
			return new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
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
				env.addElement(p.getName(), new TypedPropertyBridge(owner.getActivity(), p), false);
			}
		}
	}

	private Environment createEnvironment(INakedClassifier c) {
		// TODO add a variable that contains 'currentUser'
		Environment e = Environment.createEnvironment(c.getNameSpace(), c);
		if (getBuiltInTypes().getDateType() != null) {
			e.addElement("now", new VariableDeclaration("now", getBuiltInTypes().getDateType()), true);
		}
		for (INakedElement ne : workspace.getOwnedElements()) {
			if (ne.getName() != null) {
				e.addElement(ne.getName(), ne, false);
			} else {
				System.out.println(ne.getId());
			}
		}
		return e;
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
		if (paramOwner.getOwnerElement() instanceof INakedTransition) {
			INakedTransition t = (INakedTransition) paramOwner.getOwnerElement();
			for (INakedParameter p : t.getParameters()) {
				env.addElement(p.getName(), new VariableDeclaration(p.getName(), p.getType()), false);
			}
		}
		// NB!!! IParameterOwner extends IOperation
		if (paramOwner instanceof INakedActivity) {
			INakedActivity activity = ((INakedActivity) paramOwner);
			for (INakedActivityNode n : activity.getActivityNodesRecursively()) {
				if (n instanceof INakedOutputPin && n.getIncoming().isEmpty() && n.getName() != null) {
					// fake variable
					INakedOutputPin o = (INakedOutputPin) n;
					env.addElement(o.getName(), new VariableDeclaration(o.getName(), o.getType()), false);
				}
			}
			for (INakedActivityVariable v : activity.getVariables()) {
				env.addElement(v.getName(), new VariableDeclaration(v.getName(), v.getType()), false);
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
