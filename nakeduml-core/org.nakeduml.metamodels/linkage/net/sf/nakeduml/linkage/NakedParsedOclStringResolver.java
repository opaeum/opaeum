package net.sf.nakeduml.linkage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Activity;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedContextualEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedChangeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.IModifiableTypedElement;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
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
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

@StepDependency(phase = LinkagePhase.class,after = {
		EnumerationValuesAttributeAdder.class,PinLinker.class,MappedTypeLinker.class,SourcePopulationResolver.class,ReferenceResolver.class,TypeResolver.class,
		ProcessIdentifier.class
},requires = {
		MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,ValueSpecificationTypeResolver.class,UmlNameRegenerator.class,
		EnumerationValuesAttributeAdder.class
})
public class NakedParsedOclStringResolver extends AbstractModelElementLinker{
	EnvironmentFactory environmentFactory;
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		Collection<? extends INakedElementOwner> children = super.getChildren(root);
		return children;
	}
	@Override
	public void initialize(INakedModelWorkspace workspace,NakedUmlConfig config){
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
				Environment env = environmentFactory.createSelflessEnvironment(context);
				s.setValue(replaceSingleParsedOclString(string, context, slot.getDefiningFeature().getType(), env));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitGuardedFlow(GuardedFlow edge){
		if(edge.getGuard() != null && edge.getGuard().getValue() instanceof ParsedOclString){
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
			edge.getGuard().setValue(replaceSingleParsedOclString(string, context, booleanType, env));
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
			if(iv.isValidOclValue()){
				overridePinType(attr, iv.getOclValue().getExpression().getExpressionType());
			}
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
				IClassifier basicType = StdlibBasic.getBasicType(IOclLibrary.BooleanTypeName);
				if(cont.getConstrainedElement() instanceof INakedProperty){
					basicType = getOclLibrary().lookupCollectionType(CollectionMetaType.SET, ((INakedProperty) cont.getConstrainedElement()).getBaseType());
				}
				cont.getSpecification().setValue(replaceSingleParsedOclString(holder, nc, basicType, env));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitContextualEvent(INakedContextualEvent ev){
		INakedValueSpecification w = null;
		if(ev instanceof INakedTimeEvent){
			INakedTimeEvent te = (INakedTimeEvent) ev;
			w = te.getWhen();
			if(te.isRelative()){
				w.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else{
				w.setType(workspace.getNakedUmlLibrary().getDateType());
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
			if(bodyCondition.isValidOclValue()){
				overridePinType(op.getReturnParameter(), ocl.getExpression().getExpressionType());
			}
			// TODO support OpaqueBehavior for effects.
		}
	}
	private void replaceParticipants(INakedElement element,INakedClassifier owner,INakedValueSpecification bodyCondition,Environment env){
		if(bodyCondition != null && bodyCondition.getOclValue() instanceof ParsedOclString){
			ParsedOclString bodyExpression = (ParsedOclString) bodyCondition.getOclValue();
			bodyExpression.setContext(owner, bodyCondition);
			bodyCondition.setValue(replaceSingleParsedOclString(bodyExpression, owner, null, env));// TODO expect the user type
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
		Environment inside = environmentFactory.createPreEnvironment(ctx, a);
		replaceParsedOclConstraints(ctx, a.getPreConditions(), inside);
		if(a instanceof INakedOclAction){
			INakedOclAction oa = (INakedOclAction) a;
			if(oa.getBodyExpression() != null){
				Collection<INakedInputPin> input = oa.getInput();
				// FIrst do value pins to calculate type
				for(INakedInputPin pin:input){
					if(pin instanceof INakedValuePin){
						visitValuePin((INakedValuePin) pin);
					}
				}
				ParsedOclString expression = (ParsedOclString) oa.getBodyExpression();
				expression.setContext(ctx, oa);
				IClassifier type = oa.getReturnPin() == null ? getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName) : oa.getReturnPin().getType();
				IOclContext newExpression = replaceSingleParsedOclString(expression, ctx, type, inside);
				oa.setBodyExpression(newExpression);
				if(newExpression instanceof OclContextImpl && oa.getReturnPin() != null){
					overridePinType(oa.getReturnPin(), newExpression.getExpression().getExpressionType());
				}
			}
		}else if(a instanceof INakedEmbeddedTask){
			Environment outsideAndInside = environmentFactory.createActivityEnvironment(a, activity);
			for(INakedInputPin parm:a.getInput()){
				outsideAndInside.addElement(parm.getName(), new VariableDeclaration(parm.getName(), parm.getType()), false);
			}
			popuateDefinedResponsibility(activity, outsideAndInside, (INakedEmbeddedTask) a);
		}
		environmentFactory.addPostEnvironment(inside, a);
		replaceParsedOclConstraints(ctx, a.getPostConditions(), inside);
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
				w.setType(workspace.getNakedUmlLibrary().getDateType());
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
	}
	private void overridePinType(INakedValuePin pin){
		IClassifier type = null;
		if(pin.getValue().isValidOclValue()){
			// Override type and multiplicity of Pin
			type = pin.getValue().getOclValue().getExpression().getExpressionType();
		}else if(pin.getValue().isLiteral()){
			if(pin.getValue().getValue() instanceof Boolean){
				type = getBuiltInTypes().getBooleanType();
			}else if(pin.getValue().getValue() instanceof String){
				type = getBuiltInTypes().getStringType();
			}else if(pin.getValue().getValue() instanceof Integer){
				type = getBuiltInTypes().getIntegerType();
			}else if(pin.getValue().getValue() instanceof Double){
				type = getBuiltInTypes().getRealType();
			}
		}
		if(type != null){
			pin.getValue().setType(type);
			overridePinType(pin, type);
		}
	}
	private void overridePinType(IModifiableTypedElement pin,IClassifier type){
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
			pin.setIsOrdered(collectionType.getMetaType() == CollectionMetaType.SEQUENCE || collectionType.getMetaType() == CollectionMetaType.ORDEREDSET);
			pin.setIsUnique(collectionType.getMetaType() == CollectionMetaType.SET || collectionType.getMetaType() == CollectionMetaType.ORDEREDSET);
		}else if(type instanceof StdlibPrimitiveType){
			StdlibPrimitiveType standardType = (StdlibPrimitiveType) type;
			pin.setBaseType(getBuiltInTypes().lookupStandardType(standardType));
		}
	}
	private void resolvePinOcl(INakedValuePin pin){
		ParsedOclString string = (ParsedOclString) pin.getValue().getValue();
		string.setContext(pin.getActivity(), pin.getValue());
		Environment env = environmentFactory.createActivityEnvironment(pin, pin.getActivity());
		pin.getValue().setValue(replaceSingleParsedOclString(string, pin.getActivity(), pin.getType(), env));
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
	private IOclContext replaceSingleParsedOclString(ParsedOclString holder,INakedClassifier c,IClassifier expectedType,Environment env){
		if(holder.getExpressionString() == null){
			this.getErrorMap().putError((INakedElement) holder.getOwningModelElement().getModelElement(), CoreValidationRule.OCL, "No expression provided");
			OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
			errCtx.setExpressionString("No expression provided");
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
				if(getBuiltInTypes().getBusinessRole() != null){
					env.addElement("currentUser", new VariableDeclaration("currentUser", getBuiltInTypes().getBusinessRole()), false);
				}
				ast = ea.analyzeParsetree(parser.OclExpression(), c, ns, env);
				IOclContext newC = transformIntoOclContext(holder, ast, localErrors);
				IClassifier expressionType = newC.getExpression().getExpressionType();
				if(expectedType != null){
					if(expectedType instanceof StdlibCollectionType && !(expressionType instanceof StdlibCollectionType)){
						expectedType = ((StdlibCollectionType) expectedType).getElementType();
						// be lenient with multiplicity - will likely be corrected automatically
					}
					if(expressionType instanceof StdlibCollectionType && !(expectedType instanceof StdlibCollectionType)){
						expressionType = ((StdlibCollectionType) expressionType).getElementType();
						// be lenient with multiplicity - will likely be corrected automatically
					}
					if(expressionType instanceof INakedPrimitiveType){
						expectedType = ((INakedPrimitiveType) expressionType).getOclType();
					}
					if(expectedType instanceof INakedPrimitiveType){
						expectedType = ((INakedPrimitiveType) expectedType).getOclType();
					}
					if(!expressionType.conformsTo(expectedType)){
						this.getErrorMap().putError((INakedElement) holder.getOwningModelElement().getModelElement(), CoreValidationRule.OCL,
								"Return value of type " + expectedType.getName() + " expected, but the expression returns a value of type " + expressionType.getName());
					}
				}
				return newC;
			}catch(AnalysisException e){
				System.out.println(holder.getExpressionString());
				e.printStackTrace(System.out);
				putOclError(holder, e);
				OclErrContextImpl errCtx = new OclErrContextImpl(holder.getName(), holder.getType(), holder.getContext());
				errCtx.setExpressionString(holder.getExpressionString());
				return errCtx;
			}catch(Throwable e){
				System.out.println(holder.getExpressionString());
				if(localErrors.size() > 0){
					for(IOclError oe:localErrors){
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
	}
	protected void putOclError(ParsedOclString holder,AnalysisException e){
		INakedElement ne = (INakedElement) holder.getOwningModelElement().getModelElement();
		String msg = e.getError().getErrorMessage();
		Integer column = e.getError().getColumnNumber();
		this.getErrorMap().putError(ne, CoreValidationRule.OCL, msg, column);
	}
	private void putError(ParsedOclString holder,Throwable e){
		INakedElement ne = (INakedElement) holder.getOwningModelElement().getModelElement();
		String msg = e.getMessage();
		this.getErrorMap().putError(ne, CoreValidationRule.OCL, msg, 1);
	}
	private IOclLibrary getOclLibrary(){
		return this.workspace.getOclEngine().getOclLibrary();
	}
	private IOclContext transformIntoOclContext(ParsedOclString holder,IOclExpression ast,List<IOclError> localErrors){
		IOclContext result = null;
		OclUsageType type = holder.getType();
		if(ast != null){
			OclContextImpl replacement = new OclContextImpl(new String(holder.getName()), type, (ModelElementReferenceImpl) holder.getContext(), ast);
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
			this.getErrorMap().putError((INakedElement) holder.getOwningModelElement().getModelElement(), CoreValidationRule.OCL, oe.getErrorMessage());
		}
	}
}
