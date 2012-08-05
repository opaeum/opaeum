package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
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
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.AbstractOclContext;

public class ConstraintGenerator{
	OJClass context;
	NamedElement element;
	private ExpressionCreator expressionCreator;
	private OpaeumLibrary library;
	public ConstraintGenerator(OJUtil ojUtil,OJClass context,NamedElement element){
		super();
		this.library = ojUtil.getLibrary();
		this.context = context;
		this.element = element;
		expressionCreator = new ExpressionCreator(ojUtil,context);
	}
	public void addConstraintChecks(OJOperation operation,Collection<Constraint> constraints,boolean pre,String selfExpression){
		OJBlock block = buildConstraintsBlock(operation, new OJBlock(), constraints, pre, selfExpression);
		if(pre){
			operation.getBody().getStatements().add(0, block);
		}else if(operation.getReturnType() == null || operation.getReturnType().equals(new OJPathName("void"))
				|| ((OJAnnotatedOperation) operation).getResultVariable() != null){
			operation.getBody().getStatements().add(block);
		}else{
			operation.getBody().getStatements().add(operation.getBody().getStatements().size() - 1, block);
		}
	}
	public OJBlock buildConstraintsBlock(OJOperation operation,OJBlock sourceBlock,Collection<Constraint> constraints,boolean pre,
			String deleteThisParam){
		OJBlock result = new OJBlock();
		// Assume that there could be a last statement to return a value
		// use all the local fields
		List<OJParameter> parameters = new ArrayList<OJParameter>(operation.getParameters());
		for(OJField l:operation.getBody().getLocals()){
			OJParameter parameter = new OJParameter();
			parameter.setName(l.getName());
			parameter.setType(l.getType());
			parameters.add(parameter);
		}
		if(sourceBlock != null && sourceBlock != operation.getBody()){
			for(OJField l:sourceBlock.getLocals()){
				OJParameter parameter = new OJParameter();
				parameter.setName(l.getName());
				parameter.setType(l.getType());
				parameters.add(parameter);
			}
		}
		OJPathName failedConstraintsException = new OJPathName("org.opaeum.runtime.domain.FailedConstraintsException");
		if(!operation.getThrows().contains(failedConstraintsException)){
			context.addToImports(failedConstraintsException);
			operation.addToThrows(failedConstraintsException);
			OJAnnotatedField failedConstraints = new OJAnnotatedField("failedConstraints", new OJPathName("List<String>"));
			failedConstraints.setInitExp("new ArrayList<String>()");
			operation.getBody().addToLocals(failedConstraints);
			context.addToImports("java.util.ArrayList");
			context.addToImports("java.util.List");
		}
		for(Constraint post:constraints){
			OJIfStatement ifBroken = new OJIfStatement();
			if(post.getSpecification() instanceof OpaqueExpression){
				OpaqueExpression oe = (OpaqueExpression) post.getSpecification();
				AbstractOclContext oclExpression = library.getOclExpressionContext(oe);
				if(!oclExpression.hasErrors()){
					ValueSpecificationUtil.addExtendedKeywords(operation, oclExpression);
					String expr = expressionCreator.makeExpression(oclExpression.getExpression(), operation.isStatic(), parameters);
					ifBroken.setCondition("!" + expr);
					String qname = element.getQualifiedName() + "::" + post.getName();
					ifBroken.getThenPart().addToStatements("failedConstraints.add(\"" + qname + "\")");
					result.addToStatements(ifBroken);
				}
				;
			}
		}
		OJIfStatement ifFailed = new OJIfStatement();
		ifFailed.setCondition("failedConstraints.size()>0");
		ifFailed.getThenPart().addToStatements("throw new FailedConstraintsException(" + pre + "," + "failedConstraints)");
		result.addToStatements(ifFailed);
		return result;
	}
}
