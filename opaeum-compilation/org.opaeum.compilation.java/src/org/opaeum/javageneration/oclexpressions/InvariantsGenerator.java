package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedTypedElement;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,NakedParsedOclStringResolver.class,
		CodeCleanup.class},after = {OperationAnnotator.class},before = CodeCleanup.class)
public class InvariantsGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void classBefore(INakedClassifier c){
		if(c instanceof INakedInterface){
			// TODO make source populations part of contract?
		}else if(OJUtil.hasOJClass(c)){
			OJAnnotatedClass myClass = findJavaClass(c);
			addConstraintsTo(c, myClass);
			if(c instanceof INakedBehavioredClassifier){
				for(INakedInterfaceRealization ir:((INakedBehavioredClassifier) c).getInterfaceRealizations()){
					addInterfaceConstraintsToImplementation(myClass, ir.getContract());
				}
			}
		}
	}
	public void addInterfaceConstraintsToImplementation(OJAnnotatedClass myClass,INakedInterface contract){
		addConstraintsTo(contract, myClass);
		for(INakedGeneralization g:contract.getNakedGeneralizations()){
			addInterfaceConstraintsToImplementation(myClass, (INakedInterface) g.getGeneral());
		}
	}
	private void addConstraintsTo(INakedClassifier c,OJAnnotatedClass myClass){
		ExpressionCreator ec = new ExpressionCreator(myClass);
		boolean hasConstraints = false;
		for(INakedConstraint rule:c.getOwnedRules()){
			if(rule.getSpecification().isValidOclValue()){
				IOclContext cont = rule.getSpecification().getOclValue();
				IClassifier et = rule.getSpecification().getOclValue().getExpression().getExpressionType();
				Collection<INakedElement> ce = rule.getConstrainedElements();
				if(et.isCollectionKind()){
					if(ce.size() > 0 && ce.iterator().next() instanceof INakedTypedElement){
						String body = ec.makeExpression(cont.getExpression(), false, new ArrayList<OJParameter>());
						OJAnnotatedOperation oper = new OJAnnotatedOperation("get" + rule.getMappingInfo().getJavaName().getCapped());
						myClass.addToImports("java.util.List");
						myClass.addToImports("java.util.ArrayList");
						OJPathName returnType = new OJPathName();
						returnType.addToNames("List");
						ICollectionType type = (ICollectionType) et;
						if(type.getElementType() instanceof INakedClassifier){
							OJPathName pn = OJUtil.classifierPathname((INakedClassifier) type.getElementType());
							returnType.addToElementTypes(pn);
							oper.getBody().addToStatements("return new ArrayList<" + pn.getLast() + ">(" + body + ")");
						}else{
							//Primitive - unlikely but possible
							returnType.addToElementTypes(new OJPathName( "Object"));
							oper.getBody().addToStatements("return new ArrayList<Object>(" + body + ")");
						}
						oper.setReturnType(returnType);
						myClass.addToOperations(oper);
					}else{
						// Invalid
					}
				}else{
					hasConstraints = true;
					OJAnnotatedOperation oper = new OJAnnotatedOperation(getter(rule));
					oper.setReturnType(new OJPathName("boolean"));
					ValueSpecificationUtil.addExtendedKeywords(oper, cont);
					oper.initializeResultVariable("false");
					oper.getBody().addToStatements("result = " + ec.makeExpression(cont.getExpression(), false, new ArrayList<OJParameter>()));
					myClass.addToOperations(oper);
				}
			}else{
				System.out.println("NOT VALID!!!!" + rule.getSpecification().getOclValue().getExpressionString());
			}
		}
		if(hasConstraints){
			addConstraintChecks(myClass, c);
		}
	}
	private String getter(INakedConstraint rule){
		return "is" + rule.getMappingInfo().getJavaName().getCapped();
	}
	// TODO make this optional - may not be required when hibernate validator is
	// used
	private void addConstraintChecks(OJAnnotatedClass context,INakedClassifier cc){
		OJOperation oper = new OJOperation();
		oper.setName("getFailedInvariants");// as in the Constrained interface
		oper.setReturnType(new OJPathName("Set<String>"));
		context.addToImports("java.util.Set");
		context.addToImports("java.util.HashSet");
		OJAnnotatedField failedConstraints = new OJAnnotatedField("failedInvariants", new OJPathName("Set<String>"));
		failedConstraints.setInitExp("new HashSet<String>()");
		oper.getBody().addToLocals(failedConstraints);
		for(INakedConstraint c:cc.getOwnedRules()){
			if(c.getSpecification().isValidOclValue() && !(c.getSpecification().getOclValue().getExpression().getExpressionType().isCollectionKind())){
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
