package org.opeum.javageneration.oclexpressions;

import nl.klasse.octopus.model.IOperation;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeExpressionGenerator.class,ConstrainedImplementor.class,AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,
		ConstrainedImplementor.class
},after = {
		AttributeExpressionGenerator.class,ConstrainedImplementor.class,AttributeExpressionGenerator.class,PreAndPostConditionGenerator.class,InvariantsGenerator.class,
		ConstrainedImplementor.class
})
public class OclTestGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitEntity(INakedEntity entity){
		OJPathName pn = OJUtil.classifierPathname(entity);
		OJPackage pkg = findOrCreatePackage(pn.getHead());
		OJAnnotatedClass test = new OJAnnotatedClass(pn.getLast() + "Test");
		pkg.addToClasses(test);
		OJAnnotatedInterface testInterface = new OJAnnotatedInterface(pn.getLast() + "TestContract");
		pkg.addToClasses(testInterface);
		test.addToImports(testInterface.getPathName());
		test.addToImplementedInterfaces(testInterface.getPathName());
		for(INakedProperty p:entity.getOwnedAttributes()){
			if(p.getInitialValue() != null && p.getInitialValue().isOclValue()){
				String name = "test" + p.getMappingInfo().getJavaName() + "InitialValue";
				addTestMEthod(entity, pn, test, testInterface, name);
//				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
				// testInitialValue.getBody().addToStatements("assert object."
				// + map.getter() + "().equals(" + map.javaDefaultValue()
				// +")");
			}
		}
		for(IOperation oper:entity.getOperations()){
			INakedOperation o = (INakedOperation) oper;
			if(o.getBodyCondition() != null && o.getBodyCondition().getSpecification().isOclValue()){
				String name = "test" + o.getMappingInfo().getJavaName();
				addTestMEthod(entity, pn, test, testInterface, name);
				// testInitialValue.getBody().addToStatements("assert object."
				// + map.getter() + "().equals(" + map.javaDefaultValue()
				// +")");
			}
		}
		for(INakedConstraint nc:entity.getOwnedRules()){
			if(!(nc.getName().startsWith("uniqueIn") || nc.getName().toLowerCase().startsWith("sourcepopulation"))){
				addTestMEthod(entity, pn, test, testInterface, "test" + nc.getMappingInfo().getJavaName().getCapped());
			}
		}
		if(test.getOperations().size() > 0){
			createTextPath(testInterface, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC);
			createTextPath(test, JavaSourceFolderIdentifier.DOMAIN_TEST_SRC);
		}
	}
	private void addTestMEthod(INakedEntity entity,OJPathName pn,OJAnnotatedClass test,OJAnnotatedInterface testInterface,String name){
		String newName=name;
		int i=0;
		while(OJUtil.findOperation(test, newName)!=null){
			newName=name+i;
		}
		OJAnnotatedOperation testInitialValue = new OJAnnotatedOperation(newName);
		testInterface.addToOperations(testInitialValue.getDeepCopy());
		testInitialValue.putAnnotation(new OJAnnotationValue(new OJPathName("org.junit.Test")));
		test.addToOperations(testInitialValue);
		if(!entity.getIsAbstract()){
			INakedProperty endToComposite = entity.getEndToComposite();
			if(endToComposite != null && !endToComposite.getNakedBaseType().getIsAbstract()){
				NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(endToComposite);
				test.addToImports(compositeEndMap.javaBaseTypePath());
				OJAnnotatedField compositionalOwner = new OJAnnotatedField("parent", compositeEndMap.javaBaseTypePath());
				compositionalOwner.setInitExp("new " + compositeEndMap.javaBaseType() + "()");
				testInitialValue.getBody().addToLocals(compositionalOwner);
				OJAnnotatedField object = new OJAnnotatedField("object", pn);
				object.setInitExp("new " + pn.getLast() + "(parent)");
				testInitialValue.getBody().addToLocals(object);
			}else{
				OJAnnotatedField object = new OJAnnotatedField("object", pn);
				object.setInitExp("new " + pn.getLast() + "()");
				testInitialValue.getBody().addToLocals(object);
			}
		}
	}
}