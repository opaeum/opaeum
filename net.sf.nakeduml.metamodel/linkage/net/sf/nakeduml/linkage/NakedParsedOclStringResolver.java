package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
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
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
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
import nl.klasse.octopus.model.IOperation;
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

@StepDependency(phase = OclParsingPhase.class, after = {}, requires = { MappedTypeLinker.class, PinLinker.class, ReferenceResolver.class,
		TypeResolver.class, ValueSpecificationTypeResolver.class, UmlNameRegenerator.class })
public class NakedParsedOclStringResolver extends AbstractModelElementLinker {
	@VisitBefore
	public void processModel(INakedModel model) {
		System.out.println("NakedParsedOclStringResolver.processModel()");
	}

	@VisitBefore(matchSubclasses = true)
	public void procesTimeEvent(NakedTimeEventImpl ev) {
		INakedValueSpecification w = ev.getWhen();
		if (w != null && w.getValue() instanceof ParsedOclString) {
			// Remember that time events' when expression MUST have a type
			// specified
			w.setValue(replaceSingleParsedOclString((ParsedOclString) w.getValue(), ev.getContext(), ev.getContext().getNameSpace(),
					w.getType()));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void procesOperation(INakedOperation op) {
		INakedClassifier c = op.getOwner();
		INakedNameSpace ns = c.getNameSpace();
		op.setPreConditions(replaceParcedOclStringsForOclContext(c, ns, op.getPreConditions()));
		op.setPostConditions(replaceParcedOclStringsForOclContext(c, ns, op.getPostConditions()));
		if (op.getBodyCondition() != null && op.getBodyCondition().getSpecification() != null
				&& op.getBodyCondition().getSpecification().getValue() instanceof ParsedOclString) {
			INakedValueSpecification bodyCondition = op.getBodyCondition().getSpecification();
			ParsedOclString bodyExpression = (ParsedOclString) bodyCondition.getOclValue();
			bodyExpression.setContext(op.getOwner(), op);
			bodyCondition.setValue(replaceSingleParsedOclString(bodyExpression, c, ns, op.getReturnType()));
			bodyCondition.setType(op.getReturnType());
			// TODO support OpaqueBehavior for effects.
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void processValuePin(INakedValuePin pin) {
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
		pin.getValue().setValue(replaceSingleParsedOclString(string, context, context.getNameSpace(), pin.getType()));
	}

	@VisitBefore(matchSubclasses = true)
	public void processSlot(INakedSlot slot) {
		for (INakedValueSpecification s : slot.getValues()) {
			if (s.getValue() instanceof ParsedOclString) {
				ParsedOclString string = (ParsedOclString) s.getValue();
				INakedClassifier context = slot.getOwningInstance().getClassifier();
				string.setContext(context, context);
				s.setValue(replaceSingleParsedOclString(string, context, context.getNameSpace(), s.getType()));
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void processActivityEdge(GuardedFlow edge) {
		if (edge.getGuard() != null && edge.getGuard().getValue() instanceof ParsedOclString) {
			ParsedOclString string = (ParsedOclString) edge.getGuard().getValue();
			IClassifier booleanType = getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			edge.getGuard().setValue(
					replaceSingleParsedOclString(string, edge.getOwningBehavior(), edge.getOwningBehavior().getNameSpace(), booleanType));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void procesOpaqueBehavior(INakedOpaqueBehavior ob) {
		IClassifier returnType = null;
		if (ob.getReturnParameter() == null) {
			if (!(ob.getOwnerElement() instanceof INakedClassifier)) {// owned
																		// by
																		// transitions,
																		// activity
																		// edges
																		// or
																		// states
				String es = ob.getBodyExpression().getExpressionString();
				if (ob.getBodyExpression() instanceof ParsedOclString && es != null && es.endsWith("()")) {
					// TODO interim workaround for el
					((ParsedOclString) ob.getBodyExpression()).setExpressionString(es.substring(0, es.length() - 2));
					return;
				}
			}
			returnType = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		} else {
			returnType = ob.getReturnParameter().getType();
		}
		// OCL cannot implement or call void opertaions
		INakedClassifier c = ob.getContext();
		INakedNameSpace ns = c.getNameSpace();
		ob.setPreConditions(replaceParcedOclStringsForOclContext(c, ns, ob.getPreConditions()));
		ob.setPostConditions(replaceParcedOclStringsForOclContext(c, ns, ob.getPostConditions()));
		INakedValueSpecification body = ob.getBody();
		if (body != null && body.isOclValue()) {
			ParsedOclString bodyExpression = (ParsedOclString) ob.getBodyExpression();
			bodyExpression.setContext(ob.getContext(), ob);
			body.setValue(replaceSingleParsedOclString(bodyExpression, c, ns, returnType));
			body.setType(returnType);
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void property(INakedProperty attr) {
		INakedClassifier c = attr.getOwner();
		INakedNameSpace ns = c.getNameSpace();
		INakedValueSpecification iv = attr.getInitialValue();
		if (iv != null && iv.isOclValue()) {
			ParsedOclString oclValue = (ParsedOclString) iv.getOclValue();
			oclValue.setContext(c, attr);
			iv.setValue(replaceSingleParsedOclString(oclValue, c, ns, attr.getType()));
			iv.setType(attr.getType());
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc) {
		INakedNameSpace ns = nc.getNameSpace();
		replaceParcedOclStrings(nc, ns, nc.getOwnedRules());
		List<IOclContext> loopResults = new ArrayList<IOclContext>(replaceParcedOclStringsForOclContext(nc, ns, nc.getDefinitions()));
		nc.setDefinitions(loopResults);
		if (nc instanceof INakedActivity) {
			for (INakedActivityNode n : ((INakedActivity) nc).getActivityNodesRecursively()) {
				if (n instanceof PreAndPostConstrained) {
					PreAndPostConstrained ppc = (PreAndPostConstrained) n;
					ppc.setPostConditions(replaceParcedOclStringsForOclContext(nc, nc.getNameSpace(), ppc.getPostConditions()));
				}
			}
		}
	}

	private void replaceParcedOclStrings(INakedClassifier c, INakedNameSpace ns, Collection<? extends INakedConstraint> invs) {
		for (INakedConstraint cont : invs) {
			if (cont.getSpecification().isOclValue() && cont.getSpecification().getOclValue() instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont.getSpecification().getOclValue();
				IClassifier basicType = StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName);
				if (cont.getConstrainedElement() instanceof INakedProperty) {
					basicType = getOclLibrary().lookupCollectionType(CollectionMetaType.SET, basicType);
				}
				cont.getSpecification().setValue(replaceSingleParsedOclString(holder, c, ns, basicType));
			}
		}
	}

	private Collection<IOclContext> replaceParcedOclStringsForOclContext(INakedClassifier c, INakedNameSpace ns,
			Collection<? extends IOclContext> invs) {
		Collection<IOclContext> loopResults = new ArrayList<IOclContext>();
		for (IOclContext cont : invs) {
			if (cont instanceof ParsedOclString) {
				ParsedOclString holder = (ParsedOclString) cont;
				loopResults.add(replaceSingleParsedOclString(holder, c, ns, StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName)));
			}
		}
		return loopResults;
	}

	private IOclContext replaceSingleParsedOclString(ParsedOclString holder, INakedClassifier c, INakedNameSpace ns,
			IClassifier expectedType) {
		OclEngine engine = new OclEngine();
		engine.setOclLibrary(getOclLibrary());
		List<IOclError> localErrors = new ArrayList<IOclError>();
		IOclExpression ast = null;
		Environment env = null;
		IModelElement element = holder.getContext().getModelElement();
		if (holder.getType().equals(OclUsageType.BODY) || holder.getType().equals(OclUsageType.PRE)) {
			env = createPreEnvironment(c, ns, element);
		} else if (holder.getType().equals(OclUsageType.POST)) {
			env = createPreEnvironment(c, ns, element);
			if (element instanceof IOperation && ((IOperation) element).getReturnType() != null) {
				env.addElement("result", new VariableDeclaration("result", ((IOperation) element).getReturnType()), false);
			}
		} else {
			env = createEnvironment(c, ns);
		}
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
			e.printStackTrace();
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

	private Environment createEnvironment(INakedClassifier c, INakedNameSpace ns) {
		// TODO add an implicit variable that contains 'now' and 'currentUser'
		// as
		// attributes
		Environment e = Environment.createEnvironment(ns, c);
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

	private Environment createPreEnvironment(INakedClassifier c, INakedNameSpace ns, IModelElement element) {
		Environment env = createEnvironment(c, ns);
		Map<String, IClassifier> parameters = new HashMap<String, IClassifier>();
		if (element instanceof IParameterOwner) {
			for (IParameter parm : ((IParameterOwner) element).getParameters()) {
				parameters.put(parm.getName(), parm.getType());
			}
			// NB!!! IParameterOwner extends IOperation
			if (element instanceof INakedActivity) {
				INakedActivity activity = ((INakedActivity) element);
				for (INakedActivityNode n : activity.getActivityNodesRecursively()) {
					if (n instanceof INakedOutputPin && n.getIncoming().isEmpty() && n.getName() != null) {
						// fake variable
						INakedOutputPin o = (INakedOutputPin) n;
						parameters.put(o.getName(), o.getType());
					}
				}
				for (INakedActivityVariable n : activity.getVariables()) {
					parameters.put(n.getName(), n.getType());
				}
			}
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
