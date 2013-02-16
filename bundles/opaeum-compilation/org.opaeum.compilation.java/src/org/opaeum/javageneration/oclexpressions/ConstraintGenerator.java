package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.EmfConstraintUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

public class ConstraintGenerator{
	OJClass context;
	NamedElement element;
	private ExpressionCreator expressionCreator;
	private IPropertyEmulation library;
	OJUtil ojUtil;
	public ConstraintGenerator(OJUtil ojUtil,OJClass context,NamedElement element){
		super();
		this.ojUtil=ojUtil;
		this.library = ojUtil.getLibrary();
		this.context = context;
		this.element = element;
		expressionCreator = new ExpressionCreator(ojUtil, context);
	}
	public void addConstraintChecks(OJOperation operation,Collection<Constraint> constraints,boolean pre){
		OJBlock block = buildConstraintsBlock(operation, new OJBlock(), constraints, pre);
		if(pre){
			operation.getBody().getStatements().add(0, block);
		}else if(operation.getReturnType() == null || operation.getReturnType().equals(new OJPathName("void"))
				|| ((OJAnnotatedOperation) operation).getResultVariable() != null){
			operation.getBody().getStatements().add(block);
		}else{
			operation.getBody().getStatements().add(operation.getBody().getStatements().size() - 1, block);
		}
	}
	public OJBlock buildConstraintsBlock(OJOperation operation,OJBlock sourceBlock,Collection<Constraint> constraints,boolean pre){
		OJBlock result = new OJBlock();
		// Assume that there could be a last statement to return a value
		// use all the local fields
		List<OJParameter> parameters = new ArrayList<OJParameter>(operation.getParameters());
		for(OJField l:operation.getBody().getLocals()){
			OJParameter parameter = new OJParameter(l.getName(), l.getType());
			parameters.add(parameter);
		}
		if(sourceBlock != null && sourceBlock != operation.getBody()){
			for(OJField l:sourceBlock.getLocals()){
				OJParameter parameter = new OJParameter(l.getName(), l.getType());
				parameters.add(parameter);
			}
		}
		OJPathName failedConstraintsException = new OJPathName("org.opaeum.runtime.domain.FailedConstraintsException");
		context.addToImports(new OJPathName("org.opaeum.runtime.domain.FailedConstraint"));
		if(!operation.getThrows().contains(failedConstraintsException)){
			context.addToImports(failedConstraintsException);
			operation.addToThrows(failedConstraintsException);
			OJAnnotatedField failedConstraints = new OJAnnotatedField("failedConstraints", new OJPathName("Set<FailedConstraint>"));
			failedConstraints.setInitExp("new HashSet<FailedConstraint>()");
			operation.getBody().addToLocals(failedConstraints);
			context.addToImports("java.util.HashSet");
			context.addToImports("java.util.Set");
		}
		for(Constraint post:constraints){
			OJIfStatement ifBroken = new OJIfStatement();
			if(post.getSpecification() instanceof OpaqueExpression){
				OpaqueExpression oe = (OpaqueExpression) post.getSpecification();
				AbstractOclContext oclExpression = library.getOclExpressionContext(oe);
				if(!oclExpression.hasErrors()){
					ValueSpecificationUtil.addExtendedKeywords(operation, oclExpression);
					String expr = expressionCreator.makeExpression(oclExpression, operation.isStatic(), parameters);
					ifBroken.setCondition("!" + expr);
					OJAnnotatedField message = new OJAnnotatedField("message", new OJPathName("String"));
					ifBroken.getThenPart().addToLocals(message);
					StringBuilder sb = new StringBuilder(" ");
					for(OpaqueExpression opaqueExpression:EmfConstraintUtil.getMessageArguments(post)){
						sb.append(",");
						OpaqueExpressionContext argExp = library.getOclExpressionContext(opaqueExpression);
						sb.append(expressionCreator.makeExpression(argExp, false, parameters));
					}
					message.setInitExp(ojUtil.environmentPathname()+ ".INSTANCE.getMessage(\"" + EmfElementUtil.getQualifiedName(post) + "\"" + sb.toString() + ")");
					for(Element element:post.getConstrainedElements()){
						if(element instanceof NamedElement){
							ifBroken.getThenPart().addToStatements(
									"failedConstraints.add(new FailedConstraint(\"" + ((NamedElement) element).getName() + "\" ,message))");
						}
					}
					if(post.getConstrainedElements().isEmpty()){
						ifBroken.getThenPart().addToStatements("failedConstraints.add(new FailedConstraint(null, message))");
					}
					result.addToStatements(ifBroken);
				}
			}
		}
		OJIfStatement ifFailed = new OJIfStatement();
		ifFailed.setCondition("failedConstraints.size()>0");
		ifFailed.getThenPart().addToStatements("throw new FailedConstraintsException(" + pre + "," + "failedConstraints)");
		result.addToStatements(ifFailed);
		return result;
	}
}
