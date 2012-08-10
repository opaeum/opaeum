package org.opaeum.ocl.uml;

import java.util.List;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.expressions.AssociationClassCallExp;
import org.eclipse.ocl.expressions.CollectionItem;
import org.eclipse.ocl.expressions.CollectionLiteralExp;
import org.eclipse.ocl.expressions.CollectionRange;
import org.eclipse.ocl.expressions.IfExp;
import org.eclipse.ocl.expressions.IterateExp;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.ocl.expressions.LetExp;
import org.eclipse.ocl.expressions.MessageExp;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.expressions.TupleLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralPart;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.util.ToStringVisitor;
import org.eclipse.ocl.utilities.AbstractVisitor;
import org.eclipse.ocl.utilities.ExpressionInOCL;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;

public abstract class AbstractOclContext extends AdapterImpl{
	protected String expressionString;
	protected OCLHelper<Classifier,Operation,Property,Constraint> helper;
	protected OCLExpression expression;
	protected ParserException parseException;
	protected NamedElement bodyContainer;
	public AbstractOclContext(NamedElement bodyContainer){
		super();
		this.bodyContainer = bodyContainer;
	}
	protected void reParse(){
		try{
			this.expressionString = retrieveBody();
			this.parseException = null;
			if(expressionString != null && expressionString.length() > 0){
				this.expression = (OCLExpression) helper.createQuery(expressionString);
			}
		}catch(ParserException e){
			e.printStackTrace();
			this.parseException = e;
		}
	}
	protected abstract String retrieveBody();
	public NamedElement getBodyContainer(){
		return bodyContainer;
	}
	public String getExpressionString(){
		return expressionString;
	}
	public OCLHelper<Classifier,Operation,Property,Constraint> getHelper(){
		return helper;
	}
	public OCLExpression getExpression(){
		return expression;
	}
	public boolean hasErrors(){
		if(expression == null){
			return true;
		}else if(parseException != null){
			return true;
		}else if(helper.getProblems() == null){
			return false;
		}else{
			return helper.getProblems().getSeverity() == Diagnostic.ERROR;
		}
	}
	@SuppressWarnings("unchecked")
	public String regenerateExpressionString(){
		Environment environment = helper.getEnvironment();
		ToStringVisitor instance = ToStringVisitor.getInstance(environment);
		return this.expression.accept(instance);
	}
	public boolean dependsOn(final NamedElement ne){
		AbstractVisitor<Boolean,Classifier,Operation,Property,EnumerationLiteral,Parameter,Signal,CallOperationAction,SendSignalAction,Constraint> v = new AbstractVisitor<Boolean,Classifier,Operation,Property,EnumerationLiteral,Parameter,Signal,CallOperationAction,SendSignalAction,Constraint>(){
			@Override
			protected Boolean handleOperationCallExp(OperationCallExp<Classifier,Operation> callExp,Boolean sourceResult,
					List<Boolean> argumentResults){
				return ne == callExp.getReferredOperation() || callExp.getReferredOperation().getOwnedParameters().contains(ne);
			}
			@Override
			protected Boolean handlePropertyCallExp(PropertyCallExp<Classifier,Property> callExp,Boolean sourceResult,
					List<Boolean> qualifierResults){
				return ne == callExp.getReferredProperty() || callExp.getReferredProperty().getQualifiers().contains(ne) || callExp.getType() == ne;
			}
			@Override
			protected Boolean handleAssociationClassCallExp(AssociationClassCallExp<Classifier,Property> callExp,Boolean sourceResult,
					List<Boolean> qualifierResults){
				return callExp.getReferredAssociationClass() == ne || callExp.getReferredAssociationClass().getMembers().contains(ne)
						|| callExp.getReferredAssociationClass().getAllAttributes().contains(ne);
			}
			@Override
			protected Boolean handleVariable(Variable<Classifier,Parameter> variable,Boolean initResult){
				return variable instanceof EmulatedVariable && ((EmulatedVariable) variable).getOriginalElement() == ne;
			}
			@Override
			protected Boolean handleIfExp(IfExp<Classifier> ifExp,Boolean conditionResult,Boolean thenResult,Boolean elseResult){
				return super.handleIfExp(ifExp, conditionResult, thenResult, elseResult);
			}
			@Override
			protected Boolean handleMessageExp(MessageExp<Classifier,CallOperationAction,SendSignalAction> messageExp,Boolean targetResult,
					List<Boolean> argumentResults){
				return ne == messageExp.getSentSignal();
			}
			@Override
			protected Boolean handleTupleLiteralExp(TupleLiteralExp<Classifier,Property> literalExp,List<Boolean> partResults){
				return super.handleTupleLiteralExp(literalExp, partResults);
			}
			@Override
			protected Boolean handleTupleLiteralPart(TupleLiteralPart<Classifier,Property> part,Boolean valueResult){
				return super.handleTupleLiteralPart(part, valueResult);
			}
			@Override
			protected Boolean handleLetExp(LetExp<Classifier,Parameter> letExp,Boolean variableResult,Boolean inResult){
				return super.handleLetExp(letExp, variableResult, inResult);
			}
			@Override
			protected Boolean handleCollectionLiteralExp(CollectionLiteralExp<Classifier> literalExp,List<Boolean> partResults){
				return super.handleCollectionLiteralExp(literalExp, partResults);
			}
			@Override
			protected Boolean handleCollectionItem(CollectionItem<Classifier> item,Boolean itemResult){
				return super.handleCollectionItem(item, itemResult);
			}
			@Override
			protected Boolean handleCollectionRange(CollectionRange<Classifier> range,Boolean firstResult,Boolean lastResult){
				return super.handleCollectionRange(range, firstResult, lastResult);
			}
			@Override
			protected Boolean handleIteratorExp(IteratorExp<Classifier,Parameter> callExp,Boolean sourceResult,List<Boolean> variableResults,
					Boolean bodyResult){
				return super.handleIteratorExp(callExp, sourceResult, variableResults, bodyResult);
			}
			@Override
			protected Boolean handleIterateExp(IterateExp<Classifier,Parameter> callExp,Boolean sourceResult,List<Boolean> variableResults,
					Boolean resultResult,Boolean bodyResult){
				return super.handleIterateExp(callExp, sourceResult, variableResults, resultResult, bodyResult);
			}
			@Override
			protected Boolean handleExpressionInOCL(ExpressionInOCL<Classifier,Parameter> expression,Boolean contextResult,Boolean resultResult,
					List<Boolean> parameterResults,Boolean bodyResult){
				return super.handleExpressionInOCL(expression, contextResult, resultResult, parameterResults, bodyResult);
			}
			@Override
			protected Boolean handleConstraint(Constraint constraint,Boolean specificationResult){
				return ne == constraint;
			}
		};
		return this.expression.accept(v);
	}
}