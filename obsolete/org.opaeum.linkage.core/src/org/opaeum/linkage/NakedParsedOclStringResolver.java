package org.opaeum.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.analysis.expressions.AnalysisException;
import nl.klasse.octopus.expressions.internal.analysis.expressions.ExpressionAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.javacc.OclParser;
import nl.klasse.octopus.expressions.internal.types.CollectionLiteralExp;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
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
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.eclipse.uml2.uml.GuardedFlow;
import org.eclipse.uml2.uml.IModifiableTypedElement;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedActivity;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedChangeEvent;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConstraint;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedEnumeration;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedInterface;
import org.eclipse.uml2.uml.INakedModel;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedNameSpace;
import org.eclipse.uml2.uml.INakedOclAction;
import org.eclipse.uml2.uml.INakedOpaqueBehavior;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedPrimitiveType;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedSendSignalAction;
import org.eclipse.uml2.uml.INakedSlot;
import org.eclipse.uml2.uml.INakedStateMachine;
import org.eclipse.uml2.uml.INakedTimeEvent;
import org.eclipse.uml2.uml.INakedTransition;
import org.eclipse.uml2.uml.INakedTriggerEvent;
import org.eclipse.uml2.uml.INakedValuePin;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.bpm.INakedDefinedResponsibility;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.workspace.ModelWorkspace;

@StepDependency(phase = LinkagePhase.class,after = {EnumerationValuesAttributeAdder.class,PinLinker.class,MappedTypeLinker.class,
		SourcePopulationResolver.class,ReferenceResolver.class,TypeResolver.class,ProcessIdentifier.class},requires = {MappedTypeLinker.class,
		PinLinker.class,ReferenceResolver.class,TypeResolver.class,EnumerationValuesAttributeAdder.class})
public class NakedParsedOclStringResolver extends AbstractModelElementLinker{
	@Override
	protected int getThreadPoolSize(){
		return 1;// FOr some reason this is required TODO investigate why because it takes a while to execute
	}
	// TODO optimize to take constraints individually
	protected EnvironmentFactory environmentFactory;
	@Override
	public Collection<INakedElementOwner> getChildren(INakedElementOwner root){
		Collection<INakedElementOwner> children = super.getChildren(root);
		return children;
	}
	@Override
	public void initialize(ModelWorkspace workspace,OpaeumConfig config){
		super.initialize(workspace, config);
		environmentFactory = new EnvironmentFactory(workspace);
	}
	@VisitBefore
	public void visitModel(INakedModel m){
		NakedOperationImpl.VOID_TYPE = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSlot(INakedSlot slot){
		for(INakedValueSpecification s:slot.getValues()){
			if(s.getValue() instanceof ParsedOclString){
				ParsedOclString string = (ParsedOclString) s.getValue();
				INakedClassifier context = slot.getOwningInstance().getClassifier();
				string.setContext(context, s);
				Environment env = null;
				if(slot.getOwningInstance().getClassifier() instanceof INakedEnumeration){
					env = environmentFactory.createSelflessEnvironment(context);
				}else if(slot.getOwningInstance().getOwnerElement() instanceof INakedValueSpecification){
					// Instance VAlue
					env = environmentFactory.createInstanceValueEnvironment((INakedValueSpecification) slot.getOwningInstance().getOwnerElement());
				}
				INakedProperty property = slot.getDefiningFeature();
				if(slot.getDefiningFeature() != null){
					s.setValue(replaceSingleParsedOclString(string, context, property.getType(), env));
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitGuardedFlow(GuardedFlow edge){
		if(edge.hasGuard() && edge.getGuard().getValue() instanceof ParsedOclString){
			ParsedOclString string = (ParsedOclString) edge.getGuard().getValue();
			string.setContext(edge.getOwningBehavior(), edge.getGuard());
			IClassifier booleanType = getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			INakedBehavior owningBehavior = edge.getOwningBehavior();
			Environment env;
			if(owningBehavior instanceof INakedActivity){
				env = environmentFactory.createActivityEnvironment(edge, (INakedActivity) owningBehavior);
				environmentFactory.addFlowParameters(env, (INakedActivityEdge) edge);
			}else{
				env = environmentFactory.createStateMachineEnvironment((INakedStateMachine) owningBehavior);
				environmentFactory.addFlowParameters(env, (INakedTransition) edge);
			}
			INakedClassifier context = edge.getOwningBehavior().getContext();
			if(context == null){
				context = BehaviorUtil.getNearestActualClass(owningBehavior);
			}
			if(context == null){
				context = owningBehavior;
			}
			edge.getGuard().setValue(replaceSingleParsedOclString(string, context, booleanType, env));
			maybeAddAffectedImplementations(owningBehavior);
			if(!owningBehavior.isProcess() && owningBehavior.getContext() != null){
				maybeAddAffectedImplementations(owningBehavior.getContext());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueBehavior(INakedOpaqueBehavior ob){
		IClassifier returnType = null;
		if(ob.getReturnParameter() == null){
			// OCL cannot implement or call void operations. this is a
			// workaround
			returnType = getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		}else{
			returnType = ob.getReturnParameter().getType();
		}
		IOclContext body = ob.getBodyExpression();
		if(body != null && body instanceof ParsedOclString){
			ParsedOclString bodyExpression = (ParsedOclString) body;
			bodyExpression.setContext(ob, ob);
			Environment env = environmentFactory.createOpaqueBehaviorEnvironment(ob);
			ob.setBodyExpression(replaceSingleParsedOclString(bodyExpression, ob, returnType, env));
			maybeAddAffectedImplementations(ob);
			if(!ob.isProcess() && ob.getContext() != null){
				maybeAddAffectedImplementations(ob.getContext());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty attr){
		INakedClassifier c = attr.getOwner();
		INakedValueSpecification iv = attr.getInitialValue();
		if(iv != null && iv.getValue() instanceof ParsedOclString){
			ParsedOclString oclValue = (ParsedOclString) iv.getOclValue();
			oclValue.setContext(c, iv);
			Environment env = environmentFactory.createClassifierEnvironment(c);
			iv.setValue(replaceSingleParsedOclString(oclValue, c, attr.getType(), env));
			IOclContext oc = iv.getOclValue();
			if(iv.isValidOclValue()){
				IClassifier expressionType = oc.getExpression().getExpressionType();
				if(expressionType == null){
				}
				if(expressionType.isCollectionKind()){
					if(attr.getMultiplicity().isSingleObject() && attr.getQualifiers().isEmpty()){
						getErrorMap().putError(iv, CoreValidationRule.OCL,
								"Expression returns multiple values, but the defining property only supports a single value");
					}
				}else{
					if(attr.getMultiplicity().getUpper() > 1 || !attr.getQualifiers().isEmpty()){
						getErrorMap().putError(iv, CoreValidationRule.OCL,
								"Expression returns a single value, but the defining property expects multiple values");
					}
				}
			}
			if(iv.isValidOclValue() && attr.isDerived()){
				overridePinType(attr, iv.getOclValue().getExpression().getExpressionType());
			}
			maybeAddAffectedImplementations(c);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc){
		if(nc instanceof INakedBehavior){
			INakedBehavior b = (INakedBehavior) nc;
			if(BehaviorUtil.hasExecutionInstance(b)){
				Environment env = environmentFactory.createBehaviorEnvironment(b);
				implementOwnedRules(nc, env);
			}
		}else{
			Environment env = environmentFactory.createClassifierEnvironment(nc);
			implementOwnedRules(nc, env);
		}
	}
	private void implementOwnedRules(INakedClassifier nc,Environment env){
		for(INakedConstraint cont:nc.getOwnedRules()){
			if(cont.getSpecification().getOclValue() instanceof ParsedOclString){
				ParsedOclString holder = (ParsedOclString) cont.getSpecification().getOclValue();
				holder.setContext(nc, cont.getSpecification());
				cont.getSpecification().setValue(replaceSingleParsedOclString(holder, nc, null, env));
			}
		}
		if(nc.getOwnedRules().size() > 0){
			maybeAddAffectedImplementations(nc);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitContextualEvent(INakedTriggerEvent ev){
		INakedValueSpecification w = null;
		if(ev instanceof INakedTimeEvent){
			INakedTimeEvent te = (INakedTimeEvent) ev;
			if(te.getWhen() != null){
				w = te.getWhen();
				if(te.isRelative()){
					w.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
				}else{
					w.setType(workspace.getOpaeumLibrary().getDateType());
				}
			}
		}else{
			w = ((INakedChangeEvent) ev).getChangeExpression();
			w.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		}
		if(w != null && w.getValue() instanceof ParsedOclString){
			Environment env = null;
			if(ev.getBehaviorContext() instanceof INakedStateMachine){
				env = environmentFactory.createStateMachineEnvironment((INakedStateMachine) ev.getBehaviorContext());
			}else{
				env = environmentFactory.createActivityEnvironment(ev, (INakedActivity) ev.getBehaviorContext());
			}
			ParsedOclString value = (ParsedOclString) w.getValue();
			value.setContext(ev.getBehaviorContext(), w);
			w.setValue(replaceSingleParsedOclString(value, ev.getBehaviorContext(), w.getType(), env));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation op){
		INakedClassifier owner = op.getOwner();
		Environment env = null;
		if(BehaviorUtil.hasExecutionInstance(op)){
			env = environmentFactory.createOperationMessageEnvironment(op, op.getMessageStructure());
			replacePreAndBodyConditions(op, owner, env);
			replaceParsedOclConstraints(owner, op.getPostConditions(), env);
			if(op instanceof INakedResponsibility){
				popuateDefinedResponsibility(owner, env, (INakedDefinedResponsibility) op);
			}
		}else{
			env = environmentFactory.createPreEnvironment(op);
			replacePreAndBodyConditions(op, owner, env);
			environmentFactory.addPostEnvironment(env, op);
			replaceParsedOclConstraints(owner, op.getPostConditions(), env);
		}
		if(op.getBodyCondition() != null || op.getPostConditions().size() > 0 || op.getPreConditions().size() > 0){
			maybeAddAffectedImplementations(op.getOwner());
			// TODO Auto-generated method stub
		}
	}
	private void replacePreAndBodyConditions(INakedOperation op,INakedClassifier owner,Environment env){
		replaceParsedOclConstraints(owner, op.getPreConditions(), env);
		if(op.getBodyCondition() != null && op.getBodyCondition().getSpecification() != null
				&& op.getBodyCondition().getSpecification().getValue() instanceof ParsedOclString){
			INakedValueSpecification bodyCondition = op.getBodyCondition().getSpecification();
			ParsedOclString bodyExpression = (ParsedOclString) bodyCondition.getOclValue();
			bodyExpression.setContext(owner, op.getBodyCondition().getSpecification());
			bodyCondition.setValue(replaceSingleParsedOclString(bodyExpression, owner, op.getReturnType(), env));
			bodyCondition.setType(op.getReturnType());
			IOclContext ocl = replaceSingleParsedOclString(bodyExpression, owner, op.getReturnType(), env);
			bodyCondition.setValue(ocl);
			if(bodyCondition.isValidOclValue() && op.getReturnParameter() != null){
				// overridePinType(op.getReturnParameter(), ocl.getExpression().getExpressionType());
			}
		}
	}
	private void maybeAddAffectedImplementations(INakedClassifier owner){
		if(owner instanceof INakedInterface){
			INakedInterface intf = (INakedInterface) owner;
			for(INakedClassifier c:intf.getImplementingClassifiers()){
				addAffectedElement(c);
			}
		}else{
			addAffectedElement(owner);
		}
	}
	private void replaceParticipants(INakedElement element,INakedClassifier owner,INakedValueSpecification bodyCondition,Environment env){
		if(bodyCondition != null && bodyCondition.getOclValue() instanceof ParsedOclString){
			ParsedOclString bodyExpression = (ParsedOclString) bodyCondition.getOclValue();
			bodyExpression.setContext(owner, bodyCondition);
			INakedInterface br = workspace.getOpaeumLibrary().getBusinessRole();
			ICollectionType t = getOclLibrary().lookupCollectionType(CollectionMetaType.COLLECTION, br);
			bodyCondition.setValue(replaceSingleParsedOclString(bodyExpression, owner, t, env));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b){
		INakedClassifier ctx = b.getContext();
		if(ctx == null){
			ctx = BehaviorUtil.getNearestActualClass(b);
		}
		Environment env = environmentFactory.createBehaviorEnvironment(b);
		replaceParsedOclConstraints(ctx, b.getPreConditions(), env);
		if(!BehaviorUtil.hasExecutionInstance(b)){
			environmentFactory.addPostEnvironment(env, b);
		}
		replaceParsedOclConstraints(ctx, b.getPostConditions(), env);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitAction(INakedAction a){
		// NB!!! vistAfter to ensure valuepins are done first so that their types can be calculated
		INakedActivity activity = a.getActivity();
		INakedClassifier ctx = activity.getContext();
		if(ctx == null){
			ctx = BehaviorUtil.getNearestActualClass(a);
		}
		if(ctx == null){
			ctx = a.getActivity();
		}
		Environment inside = environmentFactory.createPreEnvironment(ctx, a);
		replaceParsedOclConstraints(ctx, a.getPreConditions(), inside);
		if(a instanceof INakedOclAction){
			INakedOclAction oa = (INakedOclAction) a;
			if(oa.getBodyExpression() instanceof ParsedOclString){
				Collection<INakedInputPin> input = oa.getInput();
				// FIrst do value pins to calculate type
				for(INakedInputPin pin:input){
					if(pin instanceof INakedValuePin){
						visitValuePin((INakedValuePin) pin);
					}
				}
				ParsedOclString expression = (ParsedOclString) oa.getBodyExpression();
				expression.setContext(ctx, oa);
				IClassifier type = oa.getReturnPin() == null ? getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName) : oa.getReturnPin()
						.getType();
				IOclContext newExpression = replaceSingleParsedOclString(expression, ctx, type, inside);
				oa.setBodyExpression(newExpression);
				if(newExpression instanceof OclContextImpl && oa.getReturnPin() != null){
					overridePinType(oa.getReturnPin(), newExpression.getExpression().getExpressionType());
				}
			}
		}else if(a instanceof INakedEmbeddedTask){
			Environment outsideAndInside = environmentFactory.createActivityEnvironment(a, activity);
			for(INakedInputPin parm:a.getInput()){
				if(parm.getType() != null){
					outsideAndInside.addElement(parm.getName(), new VariableDeclaration(parm.getName(), parm.getType()), false);
				}
			}
			popuateDefinedResponsibility(activity, outsideAndInside, (INakedEmbeddedTask) a);
		}
		if(a.getPostConditions().size() > 0){
			environmentFactory.addPostEnvironment(inside, a);
			replaceParsedOclConstraints(ctx, a.getPostConditions(), inside);
		}
		maybeAddAffectedImplementations(activity);
		if(!activity.isProcess() && activity.getContext() != null){
			maybeAddAffectedImplementations(activity.getContext());
		}
	}
	private void popuateDefinedResponsibility(INakedClassifier owner,Environment outside,INakedDefinedResponsibility e){
		INakedResponsibilityDefinition taskDefinition = e.getTaskDefinition();
		replaceParticipants(e, owner, taskDefinition.getPotentialOwners(), outside);
		replaceParticipants(e, owner, taskDefinition.getPotentialBusinessAdministrators(), outside);
		replaceParticipants(e, owner, taskDefinition.getPotentialStakeholders(), outside);
		for(INakedDeadline d:taskDefinition.getDeadlines()){
			INakedValueSpecification w = d.getWhen();
			if(d.isRelative()){
				w.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else{
				w.setType(workspace.getOpaeumLibrary().getDateType());
			}
			if(w != null && w.getValue() instanceof ParsedOclString){
				ParsedOclString value = (ParsedOclString) w.getValue();
				value.setContext(owner, d);
				w.setValue(replaceSingleParsedOclString(value, owner, w.getType(), outside));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitValuePin(INakedValuePin pin){
		if(pin.getValue() != null){
			if(pin.getValue().getValue() instanceof ParsedOclString){
				resolvePinOcl(pin);
			}
			overridePinType(pin);
		}
		if(!pin.hasValidInput() && (pin.getNakedBaseType() == null || pin.getType() == null)){
			pin.setBaseType(workspace.getOpaeumLibrary().getStringType());
			pin.setType(getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
			pin.setMultiplicity(new NakedMultiplicityImpl(0, 1));
		}
		INakedActivity activity = pin.getActivity();
		maybeAddAffectedImplementations(activity);
		if(!activity.isProcess() && activity.getContext() != null){
			maybeAddAffectedImplementations(activity.getContext());
		}
	}
	private void overridePinType(INakedValuePin pin){
		IClassifier type = null;
		if(pin.getValue().isValidOclValue()){
			// Override type and multiplicity of Pin
			type = pin.getValue().getOclValue().getExpression().getExpressionType();
		}else if(pin.getValue().isLiteral()){
			if(pin.getValue().getValue() instanceof Boolean){
				type = getLibrary().getBooleanType();
			}else if(pin.getValue().getValue() instanceof String){
				type = getLibrary().getStringType();
			}else if(pin.getValue().getValue() instanceof Integer){
				type = getLibrary().getIntegerType();
			}else if(pin.getValue().getValue() instanceof Double){
				type = getLibrary().getRealType();
			}
		}
		if(type != null){
			pin.getValue().setType(type);
			overridePinType(pin, type);
		}
	}
	protected void overridePinType(IModifiableTypedElement pin,IClassifier type){
		pin.setType(type);
		if(type instanceof INakedClassifier){
			pin.setBaseType((INakedClassifier) type);
			pin.setMultiplicity(new NakedMultiplicityImpl(pin.getNakedMultiplicity().getLower(), 1));
		}else if(type instanceof ICollectionType){
			ICollectionType collectionType = (ICollectionType) type;
			if(collectionType.getElementType() instanceof INakedClassifier
					&& (pin.getNakedBaseType() == null || !collectionType.getElementType().conformsTo(pin.getNakedBaseType()))){
				pin.setBaseType((INakedClassifier) collectionType.getElementType());
			}
			pin.setType(getOclLibrary().lookupCollectionType(collectionType.getMetaType(), pin.getNakedBaseType()));
			pin.setMultiplicity(new NakedMultiplicityImpl(pin.getNakedMultiplicity().getLower(), Integer.MAX_VALUE));
			pin.setIsOrdered(collectionType.getMetaType() == CollectionMetaType.SEQUENCE
					|| collectionType.getMetaType() == CollectionMetaType.ORDEREDSET);
			pin.setIsUnique(collectionType.getMetaType() == CollectionMetaType.SET
					|| collectionType.getMetaType() == CollectionMetaType.ORDEREDSET);
		}else if(type instanceof StdlibPrimitiveType){
			StdlibPrimitiveType standardType = (StdlibPrimitiveType) type;
			pin.setBaseType(getLibrary().lookupStandardType(standardType));
			pin.setMultiplicity(new NakedMultiplicityImpl(pin.getNakedMultiplicity().getLower(), 1));
		}
	}
	public static void main(String[] args){
		System.out.println(replaceSelfWith("self self =self self. self)(self)self= self", "containgActivity"));
	}
	private static String replaceSelfWith(String from,String matchWord){
		String string = " " + from + " ";
		String seperators = "([\\s\\)=\\(\\.])";
		String replaceAll = string.replaceAll(seperators + "(self)" + seperators, "$1" + matchWord + "$3").replaceAll(
				seperators + "(self)" + seperators, "$1" + matchWord + "$3");
		return replaceAll;
	}
	private void resolvePinOcl(INakedValuePin pin){
		ParsedOclString string = (ParsedOclString) pin.getValue().getValue();
		string.setContext(pin.getActivity(), pin.getValue());
		Environment env = environmentFactory.createActivityEnvironment(pin, pin.getActivity());
		IClassifier type = pin.getType();
		if(pin.getAction() instanceof INakedSendSignalAction && pin == ((INakedSendSignalAction) pin.getAction()).getTarget()){
			type = null;
		}
		pin.getValue().setValue(replaceSingleParsedOclString(string, pin.getActivity(), type, env, true));
		if(pin.getValue().isValidOclValue()){
			overridePinType(pin, pin.getValue().getOclValue().getExpression().getExpressionType());
			if(pin.getNakedMultiplicity().isSingleObject() && pin.getType() instanceof StdlibCollectionType){
				pin.setMultiplicity((INakedMultiplicity) NakedMultiplicityImpl.ZERO_MANY);
			}
		}
	}
	private void replaceParsedOclConstraints(INakedClassifier c,Collection<? extends INakedConstraint> invs,Environment env){
		for(INakedConstraint cont:invs){
			INakedValueSpecification spec = cont.getSpecification();
			if(spec.getValue() instanceof ParsedOclString){
				ParsedOclString holder = (ParsedOclString) spec.getValue();
				holder.setContext(c, spec);
				spec.setValue(replaceSingleParsedOclString(holder, c, StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName), env));
			}
		}
	}
	protected IOclContext replaceSingleParsedOclString(ParsedOclString holder,INakedClassifier c,IClassifier expectedType,Environment env){
		return replaceSingleParsedOclString(holder, c, expectedType, env, false);
	}
	private IOclContext replaceSingleParsedOclString(ParsedOclString holder,INakedClassifier c,IClassifier expectedType,Environment env,
			boolean ignoreMultiplicity){
		if(holder.getExpressionString() == null){
			this.getErrorMap().putError((INakedElement) holder.getOwningModelElement().getModelElement(), CoreValidationRule.OCL,
					EmfValidationUtil.OCL_EXPRESSION_REQUIRED);
			OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
			errCtx.setExpressionString(EmfValidationUtil.OCL_EXPRESSION_REQUIRED);
			return errCtx;
		}else{
			INakedNameSpace ns = c.getNameSpace();
			OclEngine engine = new OclEngine();
			engine.setOclLibrary(getOclLibrary());
			List<IOclError> localErrors = new ArrayList<IOclError>();
			IOclExpression ast = null;
			ExpressionAnalyzer ea = new ExpressionAnalyzer("model", localErrors);
			java.io.StringReader sr = new java.io.StringReader(holder.getExpressionString());
			java.io.Reader r = new java.io.BufferedReader(sr);
			OclParser parser = new OclParser(r);
			try{
				if(getLibrary().getBusinessRole() != null){
					env.addElement("currentRole", new VariableDeclaration("currentRole", getLibrary().getBusinessRole()), false);
				}
				if(getLibrary().getPersonNode() != null){
					env.addElement("currentUser", new VariableDeclaration("currentUser", getLibrary().getPersonNode()), false);
				}
				ast = ea.analyzeParsetree(parser.OclExpression(), c, ns, env);
				IOclContext newC = transformIntoOclContext(holder, ast, localErrors);
				IClassifier expressionType = newC.getExpression().getExpressionType();
				if(expectedType != null){
					if(expectedType instanceof StdlibCollectionType && !(expressionType instanceof StdlibCollectionType)){
						expectedType = ((StdlibCollectionType) expectedType).getElementType();
						// be lenient with multiplicity - will likely be corrected automatically
					}else if(newC.getExpression() instanceof CollectionLiteralExp){
						CollectionLiteralExp exp = (CollectionLiteralExp) newC.getExpression();
						if(exp.getParts().isEmpty()){
							// null, accept single expected type
							expressionType = ((StdlibCollectionType) expressionType).getElementType();
						}
					}else if(!(expectedType instanceof StdlibCollectionType)
							&& (expressionType instanceof StdlibCollectionType && ignoreMultiplicity)){
						expressionType = ((StdlibCollectionType) expressionType).getElementType();
					}
					if(expectedType instanceof INakedPrimitiveType && ((INakedPrimitiveType) expectedType).getOclType() != null){
						expectedType = ((INakedPrimitiveType) expectedType).getOclType();
					}
					if(!expressionType.conformsTo(expectedType)){
						this.getErrorMap().putError(
								(INakedElement) holder.getOwningModelElement().getModelElement(),
								CoreValidationRule.OCL,
								"Return value of type " + expectedType == null ? "null" : expectedType.getName()
										+ " expected, but the expression returns a value of type " + expressionType.getName());
					}
				}
				return newC;
			}catch(AnalysisException e){
				putOclError(holder, e);
				OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
				errCtx.setExpressionString(holder.getExpressionString());
				return errCtx;
			}catch(Throwable e){
				if(localErrors.size() > 0){
					for(IOclError oe:localErrors){
						System.out.println(oe);
					}
					putErrors(holder, localErrors);
				}
				putError(holder, e);
				OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
				errCtx.setExpressionString(holder.getExpressionString());
				return errCtx;
			}
		}
	}
	protected void putOclError(ParsedOclString holder,AnalysisException e){
		INakedElement ne = (INakedElement) holder.getOwningModelElement().getModelElement();
		String msg = e.getError().getErrorMessage();
		Integer column = e.getError().getColumnNumber();
		this.getErrorMap().putError(ne, CoreValidationRule.OCL, msg, column);
		System.out.println(ne.getMappingInfo().getQualifiedUmlName());
		// e.printStackTrace();
	}
	private void putError(ParsedOclString holder,Throwable e){
		INakedElement ne = (INakedElement) holder.getOwningModelElement().getModelElement();
		this.getErrorMap().putError(ne, CoreValidationRule.OCL, "OCL Not well-formed", 1);
		System.out.println(ne.getMappingInfo().getQualifiedUmlName());
	}
	private IOclLibrary getOclLibrary(){
		return this.workspace.getOclEngine().getOclLibrary();
	}
	private IOclContext transformIntoOclContext(ParsedOclString holder,IOclExpression ast,List<IOclError> localErrors){
		IOclContext result = null;
		OclUsageType type = holder.getType();
		if(ast != null){
			OclContextImpl replacement = new OclContextImpl(new String(holder.getName()), type, (ModelElementReferenceImpl) holder.getContext(),
					ast);
			replacement.setExpressionByUser(holder.getExpressionString());
			replacement.setLineAndColumn(holder.getLine(), holder.getColumn());
			replacement.setFilename(holder.getFilename());
			result = replacement;
			// TODO ensure that collection types are correctly implemented for
			// possible empty collections
			if(!localErrors.isEmpty()){
				putErrors(holder, localErrors);
			}
		}else{
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
	private void putErrors(ParsedOclString holder,List<IOclError> localErrors){
		for(IOclError oe:localErrors){
			this.getErrorMap().putError((INakedElement) holder.getOwningModelElement().getModelElement(), CoreValidationRule.OCL,
					oe.getErrorMessage());
		}
	}
}
