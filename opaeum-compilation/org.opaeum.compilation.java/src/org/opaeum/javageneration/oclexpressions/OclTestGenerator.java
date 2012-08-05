package org.opaeum.javageneration.oclexpressions;


import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeExpressionGenerator.class,ConstrainedImplementor.class,
		AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,ConstrainedImplementor.class},after = {
		AttributeExpressionGenerator.class,ConstrainedImplementor.class,AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,
		InvariantsGenerator.class,ConstrainedImplementor.class})
public class OclTestGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitClass(Class entity){
		OJPathName pn = ojUtil.classifierPathname(entity);
		OJPackage pkg = findOrCreatePackage(pn.getHead());
		OJAnnotatedClass test = new OJAnnotatedClass(pn.getLast() + "Test");
		pkg.addToClasses(test);
		OJAnnotatedInterface testInterface = new OJAnnotatedInterface(pn.getLast() + "TestContract");
		pkg.addToClasses(testInterface);
		test.addToImports(testInterface.getPathName());
		test.addToImplementedInterfaces(testInterface.getPathName());
		for(Property p:entity.getOwnedAttributes()){
			if(p.getDefaultValue() != null && p.getDefaultValue() instanceof OpaqueExpression){
				String name = "test" + p.getName() + "InitialValue";
				addTestMEthod(entity, pn, test, testInterface, name);
				// NakedStructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
				// testInitialValue.getBody().addToStatements("assert object."
				// + map.getter() + "().equals(" + map.javaDefaultValue()
				// +")");
			}
		}
		for(Operation oper:entity.getOperations()){
			Operation o = (Operation) oper;
			if(o.getBodyCondition() != null && o.getBodyCondition().getSpecification() instanceof OpaqueExpression){
				String name = "test" + o.getName();
				addTestMEthod(entity, pn, test, testInterface, name);
				// testInitialValue.getBody().addToStatements("assert object."
				// + map.getter() + "().equals(" + map.javaDefaultValue()
				// +")");
			}
		}
		for(Constraint nc:entity.getOwnedRules()){
			if(!(nc.getName().startsWith("uniqueIn") || nc.getName().toLowerCase().startsWith("sourcepopulation"))){
				addTestMEthod(entity, pn, test, testInterface, "test" + NameConverter.capitalize(nc.getName()));
			}
		}
		if(test.getOperations().size() > 0){
			createTextPath(testInterface, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC);
			createTextPath(test, JavaSourceFolderIdentifier.DOMAIN_TEST_SRC);
		}
	}
	private void addTestMEthod(Class entity,OJPathName pn,OJAnnotatedClass test,OJAnnotatedInterface testInterface,String name){
		String newName = name;
		int i = 0;
		while(test.getUniqueOperation(newName) != null){
			newName = name + i;
		}
		OJAnnotatedOperation testInitialValue = new OJAnnotatedOperation(newName);
		testInterface.addToOperations(testInitialValue.getDeepCopy());
		testInitialValue.putAnnotation(new OJAnnotationValue(new OJPathName("org.junit.Test")));
		test.addToOperations(testInitialValue);
		if(!entity.isAbstract()){
			Property endToComposite = getLibrary().getEndToComposite(entity);
			if(endToComposite != null && !((Classifier) endToComposite.getType()).isAbstract()){
				StructuralFeatureMap compositeEndMap = ojUtil.buildStructuralFeatureMap(endToComposite);
				test.addToImports(compositeEndMap.javaBaseTypePath());
				OJAnnotatedField compositionalOwner = new OJAnnotatedField("parent", compositeEndMap.javaBaseTypePath());
				compositionalOwner.setInitExp("new " + compositeEndMap.javaBaseType() + "()");
				testInitialValue.getBody().addToLocals(compositionalOwner);
				OJAnnotatedField object = new OJAnnotatedField("object", pn);
				if(endToComposite.getOtherEnd().getQualifiers().size() > 0){
					object.setInitExp("null");
					if(endToComposite.getOtherEnd().getQualifiers().size() == 1){
						if(endToComposite.getOtherEnd().getQualifiers().get(0).getType() instanceof Enumeration){
							Enumeration en=(Enumeration) endToComposite.getOtherEnd().getQualifiers().get(0).getType();
							if(en.getOwnedLiterals().size()>0){
								test.addToImports(ojUtil.classifierPathname(en));
								object.setInitExp("new " + pn.getLast() + "("+en.getName() +"."+ en.getOwnedLiterals().get(0).getName().toUpperCase() +",parent)");
							}
						}
					}
				}else{
					object.setInitExp("new " + pn.getLast() + "(parent)");
					testInitialValue.getBody().addToLocals(object);
				}
			}else{
				OJAnnotatedField object = new OJAnnotatedField("object", pn);
				object.setInitExp("new " + pn.getLast() + "()");
				testInitialValue.getBody().addToLocals(object);
			}
		}
	}
}