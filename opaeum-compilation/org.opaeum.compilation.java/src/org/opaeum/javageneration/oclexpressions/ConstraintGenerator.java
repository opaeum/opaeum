package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.metamodel.core.INakedConstraint;

public class ConstraintGenerator {
	OJClass context;
	IModelElement element;
	private ExpressionCreator expressionCreator;

	public ConstraintGenerator(OJClass context, IModelElement element) {
		super();
		this.context = context;
		this.element = element;
		expressionCreator = new ExpressionCreator(context);
	}

	public void addConstraintChecks(OJOperation operation, Collection<INakedConstraint> constraints, boolean pre,String selfExpression) {
		OJBlock block = buildConstraintsBlock(operation, new OJBlock(), constraints, pre,selfExpression);
		if (pre) {
			operation.getBody().getStatements().add(0, block);
		} else if (operation.getReturnType() == null || operation.getReturnType().equals(new OJPathName("void"))) {
			operation.getBody().getStatements().add(block);
		} else {
			operation.getBody().getStatements().add(operation.getBody().getStatements().size() - 1, block);
		}
	}

	public OJBlock buildConstraintsBlock(OJOperation operation, OJBlock sourceBlock, Collection<INakedConstraint> constraints, boolean pre, String selfExpression) {
		OJBlock result = new OJBlock();
		// Assume that there could be a last statement to return a value
		// use all the local fields
		List<OJParameter> parameters = new ArrayList<OJParameter>(operation.getParameters());
		for (OJField l : operation.getBody().getLocals()) {
			OJParameter parameter = new OJParameter();
			parameter.setName(l.getName());
			parameter.setType(l.getType());
			parameters.add(parameter);
		}
		if (sourceBlock !=null && sourceBlock != operation.getBody()) {
			for (OJField l : sourceBlock.getLocals()) {
				OJParameter parameter = new OJParameter();
				parameter.setName(l.getName());
				parameter.setType(l.getType());
				parameters.add(parameter);
			}
		}
		OJPathName failedConstraintsException = new OJPathName("org.opeum.runtime.domain.FailedConstraintsException");
		if(!operation.getThrows().contains(failedConstraintsException)){
			context.addToImports(failedConstraintsException);
			operation.addToThrows(failedConstraintsException);
			OJAnnotatedField failedConstraints = new OJAnnotatedField("failedConstraints",new OJPathName("List<String>"));
			failedConstraints.setInitExp("new ArrayList<String>()");
			operation.getBody().addToLocals(failedConstraints);
			context.addToImports("java.util.ArrayList");
			context.addToImports("java.util.List");
		}
		for (INakedConstraint post : constraints) {
			OJIfStatement ifBroken = new OJIfStatement();
			if (post.getSpecification().isValidOclValue()) {
				IOclContext oclValue = post.getSpecification().getOclValue();
				String expr = expressionCreator.makeExpression(oclValue.getExpression(), operation.isStatic(), parameters);
				if(oclValue.getExpressionString().matches("self")){
					expr=ValueSpecificationUtil.replaceThisWith(expr, selfExpression);
				}
					
				ifBroken.setCondition("!" + expr);
				String qname = element.getPathName() + "::" + post.getName();
				ifBroken.getThenPart().addToStatements("failedConstraints.add(\"" + qname + "\")");
				result.addToStatements(ifBroken);
			}
		}
		OJIfStatement ifFailed = new OJIfStatement();
		ifFailed.setCondition("failedConstraints.size()>0");
		ifFailed.getThenPart().addToStatements("throw new FailedConstraintsException(" + pre + "," + "failedConstraints)");
		result.addToStatements(ifFailed);
		return result;
	}
}
