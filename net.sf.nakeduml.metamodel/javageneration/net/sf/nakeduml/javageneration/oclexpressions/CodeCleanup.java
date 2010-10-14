package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class CodeCleanup extends AbstractJavaProducingVisitor{
	@VisitAfter
	public void visitModel(INakedModel p){
		OJPackage util = javaModel.findPackage(UtilityCreator.getUtilPathName());
		util=javaModel.findPackage(UtilityCreator.getUtilPathName());
		for(OJClassifier c:util.getClasses()){
			if(c.getName().equals("Stdlib")){ 
				for(OJOperation op:c.getOperations()){
					if(op.getName().startsWith("objectAs")){
						OJPathName element = new OJPathName("E");
						op.setGenericTypeParam(element);
						op.getReturnType().setElementTypes(Collections.singletonList(element));
						op.getParameters().get(0).setType(element);
						op.getBody().getStatements().set(2, new OJSimpleStatement("return (" + op.getReturnType().getCollectionTypeName() + ")result"));
					}
				}
			}else if(c.getName().startsWith("Comp") && c.getName().indexOf("On") > 0){
				//TODO find a workaround for the associated bug in Octopus
				//TODO support string compares too
				OJOperation oper = c.getOperations().get(0);
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
		OJAnnotatedClass failedConstraintsException = new OJAnnotatedClass();
		failedConstraintsException.setName("FailedConstraintsException");
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
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(hasOJClass(c)){
			OJClass ojClass = findJavaClass(c);
			ojClass.addToImports(new OJPathName("java.util.ArrayList"));// Octopus bug
			OJOperation o = OJUtil.findOperation(ojClass, "hashCode");
			if(o != null){
				ojClass.removeFromOperations(o);
			}
		}
	}
}
