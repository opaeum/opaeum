package org.opeum.javageneration.oclexpressions;

import java.util.Collections;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.models.INakedModel;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {UtilCreator.class})
public class CodeCleanup extends AbstractJavaProducingVisitor{
	@VisitAfter
	public void visitModel(INakedModel p){
		OJPackage util = javaModel.findPackage(UtilityCreator.getUtilPathName());
		util = javaModel.findPackage(UtilityCreator.getUtilPathName());
		for(OJClassifier c:util.getClasses()){
				if(c.getName().equals("Stdlib")){
					for(OJOperation op:c.getOperations()){
						if(op.getName().startsWith("objectAs")){
							OJPathName element = new OJPathName("T");
							op.setGenericTypeParam(element);
							op.getReturnType().setElementTypes(Collections.singletonList(element));
							op.getParameters().get(0).setType(element);
	//						op.getBody().getStatements().set(op.getBody().getStatements().size()-1, new OJSimpleStatement("return (" + op.getReturnType().getCollectionTypeName() + ")result"));
						}
					}
				}else if(c.getName().startsWith("Comp") && c.getName().indexOf("On") > 0){
				// TODO find a workaround for the associated bug in Octopus
				// TODO support string compares too
				OJOperation oper = c.getOperations().iterator().next();
				for(OJStatement s:oper.getBody().getStatements()){
					if(s instanceof OJIfStatement){
						OJIfStatement is = (OJIfStatement) s;
						if(is.getCondition().contains(".more(")){
							is.setCondition("value0>value1");
						}else if(is.getCondition().contains(".less(")){
							is.setCondition("value0<value1");
						}
					}
				}
			}
		}
		OJAnnotatedClass failedConstraintsException = new OJAnnotatedClass("FailedConstraintsException");
		failedConstraintsException.setSuperclass(new OJPathName("RuntimeException"));
		failedConstraintsException.addToImports("java.util.Collection");
		OJUtil.addProperty(failedConstraintsException, "pre", new OJPathName("Boolean"), true);
		OJUtil.addProperty(failedConstraintsException, "failedConstraints", new OJPathName("Collection<String>"), true);
		OJConstructor c = new OJConstructor();
		c.addParam("pre", "Boolean");
		c.addParam("failedConstraints", "Collection<String>");
		c.getBody().addToStatements("this.failedConstraints=failedConstraints");
		c.getBody().addToStatements("this.pre=pre");
		failedConstraintsException.addToConstructors(c);
		util.addToClasses(failedConstraintsException);
	}
}
