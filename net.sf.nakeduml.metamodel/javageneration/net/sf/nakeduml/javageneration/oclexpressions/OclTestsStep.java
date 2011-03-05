package net.sf.nakeduml.javageneration.oclexpressions;

import org.testng.annotations.Test;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IOperation;

@StepDependency(phase = JavaTransformationPhase.class, requires = { OclExpressionExecution.class }, after = { BasicJavaModelStep.class })
public class OclTestsStep extends AbstractJavaTransformationStep {
	public class OclTestGenerator extends AbstractJavaProducingVisitor {
		@VisitBefore
		public void visitEntity(INakedEntity entity) {
			OJPathName pn = OJUtil.classifierPathname(entity);
			OJAnnotatedPackage pkg = findOrCreatePackage(pn.getHead());
			OJAnnotatedClass test = new OJAnnotatedClass(pn.getLast() + "Test");
			pkg.addToClasses(test);
			OJAnnotatedInterface testInterface = new OJAnnotatedInterface("I" + pn.getLast() + "Test");
			pkg.addToClasses(testInterface);
			test.addToImplementedInterfaces(testInterface.getPathName());
			for (INakedProperty p : entity.getOwnedAttributes()) {
				if (p.getInitialValue() != null && p.getInitialValue().isOclValue()) {
					String name = "test" + p.getMappingInfo().getJavaName() + "InitialValue";
					addTestMEthod(entity, pn, test, testInterface, name);
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
					// testInitialValue.getBody().addToStatements("assert object."
					// + map.getter() + "().equals(" + map.javaDefaultValue()
					// +")");
				}
			}
			for (IOperation oper : entity.getOperations()) {
				INakedOperation o = (INakedOperation) oper;
				if (o.getBodyCondition() != null && o.getBodyCondition().getSpecification().isOclValue()) {
					String name = "test" + o.getMappingInfo().getJavaName();
					addTestMEthod(entity, pn, test, testInterface, name);
					// testInitialValue.getBody().addToStatements("assert object."
					// + map.getter() + "().equals(" + map.javaDefaultValue()
					// +")");
				}
			}
			// TODO operations
			if (test.getOperations().size() > 0) {
				createTextPath(testInterface, JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC);
				createTextPath(test, JavaTextSource.OutputRootId.DOMAIN_TEST_SRC);
			}
		}

		private void addTestMEthod(INakedEntity entity, OJPathName pn, OJAnnotatedClass test, OJAnnotatedInterface testInterface,
				String name) {
			
			OJAnnotatedOperation testInitialValue = new OJAnnotatedOperation(name);
			testInterface.addToOperations(testInitialValue.getDeepCopy());
			testInitialValue.putAnnotation(new OJAnnotationValue(new OJPathName(Test.class.getName())));
			test.addToOperations(testInitialValue);
			if (!entity.getIsAbstract()) {
				INakedProperty endToComposite = entity.getEndToComposite();
				if (endToComposite != null && !endToComposite.getNakedBaseType().getIsAbstract()) {
					NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(endToComposite);
					test.addToImports(compositeEndMap.javaBaseTypePath());
					OJAnnotatedField compositionalOwner = new OJAnnotatedField("parent", compositeEndMap.javaBaseTypePath());
					compositionalOwner.setInitExp("new " + compositeEndMap.javaBaseType() + "()");
					testInitialValue.getBody().addToLocals(compositionalOwner);
					OJAnnotatedField object = new OJAnnotatedField("object", pn);
					object.setInitExp("new " + pn.getLast() + "(parent)");
					testInitialValue.getBody().addToLocals(object);
				} else {
					OJAnnotatedField object = new OJAnnotatedField("object", pn);
					object.setInitExp("new " + pn.getLast() + "()");
					testInitialValue.getBody().addToLocals(object);
				}
			}
		}
	}

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		OclTestGenerator ctg = new OclTestGenerator();
		ctg.initialize(javaModel, config, textWorkspace, context);
		ctg.startVisiting(workspace);
	}
}
