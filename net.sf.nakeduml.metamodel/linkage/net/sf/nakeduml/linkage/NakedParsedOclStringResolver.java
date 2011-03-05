package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;
import net.sf.nakeduml.validation.namegeneration.UmlNameRegenerator;
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.analysis.expressions.AnalysisException;
import nl.klasse.octopus.expressions.internal.analysis.expressions.ExpressionAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.javacc.OclParser;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
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

@StepDependency(phase = LinkagePhase.class, after = { EnumerationValuesAttributeAdder.class, PinLinker.class, MappedTypeLinker.class,
		SourcePopulationResolver.class, ReferenceResolver.class, TypeResolver.class, ProcessIdentifier.class }, requires = {
		MappedTypeLinker.class, PinLinker.class, ReferenceResolver.class, TypeResolver.class, ValueSpecificationTypeResolver.class,
		UmlNameRegenerator.class, EnumerationValuesAttributeAdder.class })
public class NakedParsedOclStringResolver extends AbstractModelElementLinker {
	EnvironmentFactory environmentFactory;

	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		super.initialize(workspace, config);
		environmentFactory = new EnvironmentFactory(workspace);
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
				Environment env = environmentFactory.createEnvironment(context);
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
			Environment env = environmentFactory.prepareBehaviorEnvironment(edge, owningBehavior);
			environmentFactory.addFlowParameters(env, edge);
			INakedClassifier context = edge.getOwningBehavior().getContext();
			if (context == null) {
				context = BehaviorUtil.getNearestActualClass(owningBehavior);
			}
			edge.getGuard().setValue(replaceSingleParsedOclString(string, context, booleanType, env));
		}
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
		IOclContext body = ob.getBodyExpression();
		if (body != null && body instanceof ParsedOclString) {
			ParsedOclString bodyExpression = (ParsedOclString) body;
			bodyExpression.setContext(ob.getContext(), ob);
			if (ob.getContext() == null) {
				INakedClassifier surrogateContext = BehaviorUtil.getNearestActualClass(ob.getOwnerElement());
				Environment env = environmentFactory.createPreEnvironment(surrogateContext, ob);
				ob.setBodyExpression(replaceSingleParsedOclString(bodyExpression, surrogateContext, returnType, env));
			} else {
				Environment env = environmentFactory.createPreEnvironment(ob.getContext(), ob);
				ob.setBodyExpression(replaceSingleParsedOclString(bodyExpression, ob.getContext(), returnType, env));
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
			Environment env = environmentFactory.createEnvironment(c);
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
			Environment env = environmentFactory.createEnvironment(ev.getContext());
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
			Environment env = environmentFactory.createPreEnvironment(owner, op);
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
			ctx = BehaviorUtil.getNearestActualClass(b);
		}
		b.setPreConditions(replaceParcedOclStringsForOclContext(ctx, b, b.getPreConditions()));
		b.setPostConditions(replaceParcedOclStringsForOclContext(ctx, b, b.getPostConditions()));
	}

	@VisitBefore(matchSubclasses = true)
	public void visitAction(INakedAction a) {
		INakedClassifier ctx = a.getActivity().getContext();
		if (ctx == null) {
			ctx = BehaviorUtil.getNearestActualClass(a);
		}
		a.setPreConditions(replaceParcedOclStringsForOclContext(ctx, a, a.getPreConditions()));
		a.setPostConditions(replaceParcedOclStringsForOclContext(ctx, a, a.getPostConditions()));
		if (a instanceof INakedOpaqueAction) {
			INakedOpaqueAction oa = (INakedOpaqueAction) a;
			if (oa.getReturnPin() != null && oa.getBodyExpression() != null) {
				Environment env = environmentFactory.createPreEnvironment(ctx, oa);
				ParsedOclString expression = (ParsedOclString) oa.getBodyExpression();
				expression.setContext(ctx, oa);
				IOclContext newExpression = replaceSingleParsedOclString(expression, ctx, oa.getReturnPin().getType(), env);
				oa.setBodyExpression(newExpression);
				if (newExpression instanceof OclContextImpl) {
					overridePinType(oa.getReturnPin(), newExpression.getExpression().getExpressionType());
				}
			}
		}
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
			pin.getValue().setType(type);
			overridePinType(pin, type);
		}
	}

	private void overridePinType(INakedPin pin, IClassifier type) {
		pin.setType(type);
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

	private void resolvePinOcl(INakedValuePin pin) {
		ParsedOclString string = (ParsedOclString) pin.getValue().getValue();
		INakedClassifier nearestActualClass = BehaviorUtil.getNearestActualClass(pin.getActivity());
		string.setContext(nearestActualClass, nearestActualClass);
		Environment env = environmentFactory.prepareBehaviorEnvironment(pin, pin.getActivity());
		INakedClassifier context = pin.getActivity().getContext();
		if (context == null) {
			context = nearestActualClass;
		}
		pin.getValue().setValue(replaceSingleParsedOclString(string, context, pin.getType(), env));
	}

	private void replaceParcedOclStrings(INakedClassifier c, Collection<? extends INakedConstraint> invs) {
		for (INakedConstraint cont : invs) {
			if (cont.getSpecification().isOclValue() && cont.getSpecification().getOclValue() instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont.getSpecification().getOclValue();
				holder.setContext(c, c);
				IClassifier basicType = StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName);
				Environment env = environmentFactory.createEnvironment(c);
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
				Environment env = environmentFactory.createPreEnvironment(c, element);
				if (holder.getType().equals(OclUsageType.POST)) {
					environmentFactory.addPostEnvironment(env, element);
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

}
