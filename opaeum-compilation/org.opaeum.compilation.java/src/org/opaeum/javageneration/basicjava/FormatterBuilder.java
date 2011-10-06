package org.opaeum.javageneration.basicjava;

import java.util.Collection;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opeum.runtime.domain.AbstractFormatter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class FormatterBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@VisitBefore
	public void visitModel(INakedModel m){
		if(!transformationContext.isIntegrationPhase()){
			OJPathName formatter = formatterPathName(m);
			OJPackage util = findOrCreatePackage(formatter.getHead());
			Collection<INakedSimpleType> simpleTypes = getElementsOfType(INakedSimpleType.class, m.getAllDependencies());
			createFormatter(util, formatter.getLast());
			createFormatterContract(util, formatter.getLast(), simpleTypes);
		}
	}
	public static OJPathName formatterPathName(INakedElement m){
		INakedRootObject ro = m.getRootObject();
		return OJUtil.utilPackagePath(ro).append(ro.getMappingInfo().getJavaName().getCapped() + "Formatter");
	}
	private void createFormatterContract(OJPackage util,String name,Collection<INakedSimpleType> types){
		OJAnnotatedInterface formatterContract = new OJAnnotatedInterface("I" + name);
		util.addToClasses(formatterContract);
		createTextPath(formatterContract, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		for(INakedSimpleType e:types){
			INakedSimpleType type = (INakedSimpleType) e;
			OJAnnotatedOperation parse = new OJAnnotatedOperation("parse" + type.getName(), new OJPathName(type.getMappingInfo().getQualifiedJavaName()));
			parse.addParam("value", new OJPathName("String"));
			formatterContract.addToOperations(parse);
			OJAnnotatedOperation format = new OJAnnotatedOperation("format" + type.getName(), new OJPathName("String"));
			format.addParam("value", new OJPathName(type.getMappingInfo().getQualifiedJavaName()));
			formatterContract.addToOperations(format);
		}
	}
	private void createFormatter(OJPackage util,String name){
		OJAnnotatedClass formatter = new OJAnnotatedClass(name);
		util.addToClasses(formatter);
		formatter.addToImplementedInterfaces(new OJPathName("I" + name));
		formatter.setSuperclass(new OJPathName(AbstractFormatter.class.getName()));
		OJPathName threadLocal = new OJPathName(ThreadLocal.class.getName());
		threadLocal.addToElementTypes(formatter.getPathName());
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", threadLocal);
		instance.setInitExp("new ThreadLocal<" + formatter.getName() + ">()");
		instance.setStatic(true);
		instance.setFinal(true);
		formatter.addToFields(instance);
		OJAnnotatedOperation getInstance = new OJAnnotatedOperation("getInstance", formatter.getPathName());
		formatter.addToOperations(getInstance);
		getInstance.setStatic(true);
		OJAnnotatedField result = new OJAnnotatedField("result", formatter.getPathName());
		getInstance.getBody().addToLocals(result);
		result.setInitExp("INSTANCE.get()");
		OJIfStatement ifNull = new OJIfStatement("result==null", "INSTANCE.set(result=new " + formatter.getName() + "())");
		getInstance.getBody().addToStatements(ifNull);
		getInstance.getBody().addToStatements("return result");
		createTextPath(formatter, JavaSourceFolderIdentifier.DOMAIN_SRC);
	}
}
