package org.opaeum.javageneration.oclexpressions;

import java.util.Collections;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.Model;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {
	UtilCreator.class
})
public class CodeCleanup extends AbstractJavaProducingVisitor{
	@VisitAfter
	public void visitModel(Model p){
		OJPathName utilPathName = UtilityCreator.getUtilPathName();

		OJPackage util = javaModel.findPackage(utilPathName);
		util = javaModel.findPackage(utilPathName);
		for(OJClassifier c:util.getClasses()){
			if(c.getName().equals("Stdlib")){
				for(OJOperation op:c.getOperations()){
					if(op.getName().startsWith("objectAs")){
						OJPathName element = new OJPathName("T");
						op.setGenericTypeParam(element);
						op.getReturnType().setElementTypes(Collections.singletonList(element));
						op.getParameters().get(0).setType(element);
						// op.getBody().getStatements().set(op.getBody().getStatements().size()-1, new OJSimpleStatement("return (" +
						// op.getReturnType().getCollectionTypeName() + ")result"));
					}
				}
				OJPathName T = new OJPathName("T");
				OJAnnotatedOperation collectionAsSingleObject = new OJAnnotatedOperation("collectionAsSingleObject", T);
				c.addToOperations(collectionAsSingleObject);
				collectionAsSingleObject.setGenericTypeParam(T);
				collectionAsSingleObject.setStatic(true);
				OJPathName in = new OJPathName("java.util.Collection");
				in.addToElementTypes(T);
				collectionAsSingleObject.addParam("in", in);
				OJIfStatement ojIfStatement=new OJIfStatement("in.size()==0","return null");
				collectionAsSingleObject.getBody().addToStatements(ojIfStatement);
				ojIfStatement.setElsePart(new OJBlock());
				OJIfStatement ifOne = new OJIfStatement("in.size()==1", "return in.iterator().next()");
				ojIfStatement.getElsePart().addToStatements(ifOne);
				ifOne.setElsePart(new OJBlock());
				ifOne.getElsePart().addToStatements("throw new IllegalArgumentException(\"Input collection contains more than one object: \"+ in.size())");
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
		OJUtil.addTransientProperty(failedConstraintsException, "pre", new OJPathName("Boolean"), true);
		OJUtil.addTransientProperty(failedConstraintsException, "failedConstraints", new OJPathName("Collection<String>"), true);
		OJConstructor c = new OJConstructor();
		c.addParam("pre", "Boolean");
		c.addParam("failedConstraints", "Collection<String>");
		c.getBody().addToStatements("this.failedConstraints=failedConstraints");
		c.getBody().addToStatements("this.pre=pre");
		failedConstraintsException.addToConstructors(c);
		util.addToClasses(failedConstraintsException);
	}
}
