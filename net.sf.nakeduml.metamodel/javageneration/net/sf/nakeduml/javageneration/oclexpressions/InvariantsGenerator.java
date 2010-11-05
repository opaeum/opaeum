package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.ArrayList;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.oclengine.IOclContext;

public class InvariantsGenerator extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true)
	public void classBefore(INakedClassifier c) {
		if (c instanceof INakedInterface) {
			// TODO make source populations part of contract?

		} else if (OJUtil.hasOJClass(c)) {
			OJAnnotatedClass myClass = findJavaClass(c);
			addConstraintsTo(c, myClass);
			for (INakedInterfaceRealization ir : c.getInterfaceRealizations()) {
				addInterfaceConstraintsToImplementation(myClass, ir.getContract());
			}
		}
	}

	public void addInterfaceConstraintsToImplementation(OJAnnotatedClass myClass, INakedInterface contract) {
		addConstraintsTo(contract, myClass);
		for (INakedGeneralization g : contract.getNakedGeneralizations()) {
			addInterfaceConstraintsToImplementation(myClass, (INakedInterface)g.getGeneral());
		}
	}

	private void addConstraintsTo(INakedClassifier c, OJAnnotatedClass myClass) {
		ExpressionCreator ec = new ExpressionCreator(myClass);
		boolean hasConstraints = false;
		for (INakedConstraint rule : c.getOwnedRules()) {
			if (rule.getSpecification().isValidOclValue()) {
				IOclContext cont = rule.getSpecification().getOclValue();
				if (rule.getConstrainedElement() instanceof INakedTypedElement) {
					String body = ec.makeExpression(cont.getExpression(), false, new ArrayList<OJParameter>());
					OJOperation oper = new OJOperation();
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(c, (INakedTypedElement) rule.getConstrainedElement());
					oper.setName(map.getter() + "SourcePopulation");
					myClass.addToImports("java.util.Set");
					myClass.addToImports("java.util.HashSet");

					OJPathName returnType = new OJPathName();
					returnType.addToNames("Set");
					returnType.addToElementTypes(map.javaBaseTypePath());
					oper.setReturnType(returnType);

					oper.getBody().addToStatements("return new HashSet<" + map.javaBaseType() + ">(" + body + ")");
					myClass.addToOperations(oper);
				} else {
					hasConstraints = true;
					OJOperation oper = new OJAnnotatedOperation();
					oper.setName(getter(rule));
					oper.setReturnType(new OJPathName("boolean"));
					oper.getBody()
							.addToStatements("return " + ec.makeExpression(cont.getExpression(), false, new ArrayList<OJParameter>()));
					myClass.addToOperations(oper);
				}
			} else {
				System.out.println("NOT VALID!!!!" + rule.getSpecification().getOclValue().getExpressionString());
			}
		}
		if (hasConstraints) {
			addConstraintChecks(myClass, c);
		}
	}

	private String getter(INakedConstraint rule) {
		return "is" + rule.getMappingInfo().getJavaName().getCapped();
	}

	// TODO make this optional - may not be required when hibernate validator is
	// used
	private void addConstraintChecks(OJAnnotatedClass context, INakedClassifier cc) {
		OJOperation oper = new OJOperation();
		oper.setName("getFailedInvariants");// as in the Constrained interface
		oper.setReturnType(new OJPathName("Set<String>"));
		context.addToImports("java.util.Set");
		context.addToImports("java.util.HashSet");
		OJAnnotatedField failedConstraints = new OJAnnotatedField();
		failedConstraints.setType(new OJPathName("Set<String>"));
		failedConstraints.setName("failedInvariants");
		failedConstraints.setInitExp("new HashSet<String>()");
		oper.getBody().addToLocals(failedConstraints);
		for (INakedConstraint c : cc.getOwnedRules()) {
			if (c.getSpecification().isValidOclValue() && !(c.getConstrainedElement() instanceof INakedTypedElement)) {
				OJIfStatement ifBroken = new OJIfStatement();
				ifBroken.setCondition("!" + getter(c) + "()");
				String qname = c.getMappingInfo().getQualifiedJavaName();
				ifBroken.getThenPart().addToStatements("failedInvariants.add(\"" + qname + "\")");
				oper.getBody().addToStatements(ifBroken);
			}
		}
		oper.getBody().addToStatements("return failedInvariants");
		context.addToOperations(oper);
	}
}
