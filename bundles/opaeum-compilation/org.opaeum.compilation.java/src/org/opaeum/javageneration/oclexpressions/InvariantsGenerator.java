package org.opaeum.javageneration.oclexpressions;

import java.util.ArrayList;
import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.creators.ExpressionCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.compiler.ocltojava.JavaCompilationPlugin;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OclQueryContext;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class,CodeCleanup.class},after = {OperationAnnotator.class},before = CodeCleanup.class)
public class InvariantsGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void classBefore(Classifier c){
		if(c instanceof Interface){
			// TODO make source populations part of contract?
		}else if(ojUtil.hasOJClass(c)){
			OJAnnotatedClass myClass = findJavaClass(c);
			addConstraintsTo(c, myClass);
			if(c instanceof BehavioredClassifier){
				for(InterfaceRealization ir:((BehavioredClassifier) c).getInterfaceRealizations()){
					addInterfaceConstraintsToImplementation(myClass, ir.getContract());
				}
			}
		}
	}
	public void addInterfaceConstraintsToImplementation(OJAnnotatedClass myClass,Interface contract){
		addConstraintsTo(contract, myClass);
		for(Generalization g:contract.getGeneralizations()){
			addInterfaceConstraintsToImplementation(myClass, (Interface) g.getGeneral());
		}
	}
	private void addConstraintsTo(Classifier c,OJAnnotatedClass myClass){
		ExpressionCreator ec = new ExpressionCreator(ojUtil, myClass);
		boolean hasConstraints = false;
		for(Constraint rule:c.getOwnedRules()){
			if(rule.getSpecification() instanceof OpaqueExpression){
				OpaqueExpression oe = (OpaqueExpression) rule.getSpecification();
				AbstractOclContext oclExpression = getLibrary().getOclExpressionContext(oe);
				if(!oclExpression.hasErrors()){
					Classifier et = (Classifier) oclExpression.getExpression().getType();
					Collection<Element> ce = rule.getConstrainedElements();
					if(et instanceof CollectionType){
						if(ce.size() > 0 && ce.iterator().next() instanceof TypedElement){
							String body = ec.makeExpression(oclExpression, false, new ArrayList<OJParameter>());
							OJAnnotatedOperation oper = new OJAnnotatedOperation("get" + NameConverter.capitalize(rule.getName()));
							myClass.addToImports("java.util.List");
							myClass.addToImports("java.util.ArrayList");
							OJPathName returnType = new OJPathName();
							returnType.addToNames("List");
							CollectionType type = (CollectionType) et;
							if(type.getElementType() instanceof Classifier){
								OJPathName pn = ojUtil.classifierPathname((Classifier) type.getElementType());
								returnType.addToElementTypes(pn);
								oper.getBody().addToStatements("return new ArrayList<" + pn.getLast() + ">(" + body + ")");
							}else{
								// Primitive - unlikely but possible
								returnType.addToElementTypes(new OJPathName("Object"));
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
						ValueSpecificationUtil.addExtendedKeywords(oper, oclExpression);
						oper.initializeResultVariable("false");
						oper.getBody().addToStatements("result = " + ec.makeExpression(oclExpression, false, new ArrayList<OJParameter>()));
						myClass.addToOperations(oper);
					}
				}else{
					JavaCompilationPlugin.logWarning("Java could not be generated for invalid ocl: " + oclExpression.getExpressionString());
				}
			}
		}
		if(hasConstraints){
			addConstraintChecks(myClass, c);
		}
		for(OclQueryContext oclExpression:getLibrary().getEmulatedPropertyHolder(c).getQueries()){
			if(!oclExpression.hasErrors()){
				PropertyMap map = ojUtil.buildStructuralFeatureMap((TypedElement) oclExpression.getBodyContainer());
				OJPathName coll = new OJPathName("java.util.Collection");
				coll.addToElementTypes(map.javaBaseTypePath());
				coll.markAsExtendingElement(map.javaBaseTypePath());
				OJAnnotatedOperation sourceGetter = new OJAnnotatedOperation(map.getter() + "SourcePopulation", coll);
				sourceGetter.initializeResultVariable(ec.makeExpression(oclExpression, false, new ArrayList<OJParameter>()));
				myClass.addToOperations(sourceGetter);
				OJAnnotatedOperation getter = (OJAnnotatedOperation) myClass.findOperation(map.getter(), new ArrayList<OJPathName>());
				if(getter != null){
					OJAnnotationValue metaInfo = getter.findAnnotation(new OJPathName(PropertyMetaInfo.class.getName()));
					if(metaInfo != null && metaInfo.findAttribute("lookupMethod") == null){
						metaInfo.putAttribute("lookupMethod", sourceGetter.getName());
					}
				}
			}
		}
	}
	private String getter(Constraint rule){
		return "is" + NameConverter.capitalize(rule.getName());
	}
	// TODO make this optional - may not be required when hibernate validator is
	// used
	private void addConstraintChecks(OJAnnotatedClass context,Classifier cc){
		OJOperation oper = new OJOperation("getFailedInvariants");
		oper.setReturnType(new OJPathName("Set<String>"));
		context.addToImports("java.util.Set");
		context.addToImports("java.util.HashSet");
		OJAnnotatedField failedConstraints = new OJAnnotatedField("failedInvariants", new OJPathName("Set<String>"));
		failedConstraints.setInitExp("new HashSet<String>()");
		oper.getBody().addToLocals(failedConstraints);
		for(Constraint c:cc.getOwnedRules()){
			if(c.getSpecification() instanceof OpaqueExpression){
				OpaqueExpression oe = (OpaqueExpression) c.getSpecification();
				AbstractOclContext oclExpression = getLibrary().getOclExpressionContext(oe);
				if(!oclExpression.hasErrors() && !(oclExpression.getExpression().getType() instanceof CollectionType)){
					OJIfStatement ifBroken = new OJIfStatement();
					ifBroken.setCondition("!" + getter(c) + "()");
					String qname = c.getQualifiedName().replace("::", ".");
					ifBroken.getThenPart().addToStatements("failedInvariants.add(\"" + qname + "\")");
					oper.getBody().addToStatements(ifBroken);
				}
			}
		}
		oper.getBody().addToStatements("return failedInvariants");
		context.addToOperations(oper);
	}
}
