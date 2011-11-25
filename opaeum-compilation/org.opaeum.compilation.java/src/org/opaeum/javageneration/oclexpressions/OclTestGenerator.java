package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.model.IOperation;

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
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

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
		while(test.getUniqueOperation(newName)!=null){
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