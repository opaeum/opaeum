package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclErrContextImpl;

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

	public void addConstraintChecks(OJOperation operation, Collection<IOclContext> constraints, boolean pre) {
		String prefix = pre ? "pre" : "post";
		// Assume that there could be a last statement to return a value
		// use all the local fields
		List<OJParameter> parameters = new ArrayList<OJParameter>(operation.getParameters());
		for (OJField l : operation.getBody().getLocals()) {
			OJParameter parameter = new OJParameter();
			parameter.setName(l.getName());
			parameter.setType(l.getType());
			parameters.add(parameter);
		}
		OJBlock block = new OJBlock();
		OJAnnotatedField failedConstraints = new OJAnnotatedField();
		failedConstraints.setType(new OJPathName("List<String>"));
		failedConstraints.setName(prefix + "FailedConstraints");
		failedConstraints.setInitExp("new ArrayList<String>()");
		block.addToLocals(failedConstraints);
		OJPathName failedConstraintsException = UtilityCreator.getUtilPathName().append("FailedConstraintsException");
		context.addToImports(failedConstraintsException);
		context.addToImports("java.util.ArrayList");
		context.addToImports("java.util.List");
		int i = 0;
		for (IOclContext post : constraints) {
			OJIfStatement ifBroken = new OJIfStatement();
			if (!(post instanceof OclErrContextImpl)) {
				ifBroken.setCondition("!" + expressionCreator.makeExpression(post.getExpression(), operation.isStatic(), parameters));
				String qname = element.getPathName() + "::" + post.getName();
				ifBroken.getThenPart().addToStatements(prefix + "FailedConstraints.add(\"" + qname + "\")");
				block.addToStatements(ifBroken);
			}
			i++;
		}
		operation.addToThrows(failedConstraintsException);
		OJIfStatement ifFailed = new OJIfStatement();
		ifFailed.setCondition(prefix + "FailedConstraints.size()>0");
		ifFailed.getThenPart().addToStatements("throw new FailedConstraintsException("+pre + "," + prefix + "FailedConstraints)");
		block.addToStatements(ifFailed);
		if(pre){
			operation.getBody().getStatements().add(0,block);
		}else if(operation.getReturnType()==null){
			operation.getBody().getStatements().add(block);
		}else{
			operation.getBody().getStatements().add(operation.getBody().getStatements().size()-1, block);
		}
	}
}
