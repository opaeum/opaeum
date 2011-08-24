package net.sf.nakeduml.javageneration.basicjava;

import java.util.Set;

import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.AbstractFormatter;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class FormatterBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@VisitBefore
	public void visitModel(INakedModel m){
		if(!transformationContext.isIntegrationPhase()){
			OJAnnotatedPackage util = findOrCreatePackage(UtilityCreator.getUtilPathName());
			createFormatter(util, m.getName());
			createFormatterContract(util, m.getName());
		}
	}
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace m){
		if(transformationContext.isIntegrationPhase()){
			OJAnnotatedPackage util = findOrCreatePackage(UtilityCreator.getUtilPathName());
			createFormatter(util, m.getName());
			createFormatterContract(util, m.getName());
		}
	}
	protected void createFormatterContract(OJAnnotatedPackage util,String name){
		OJAnnotatedInterface formatterContract = new OJAnnotatedInterface("I" + NameConverter.capitalize(name) + "Formatter");
		util.addToClasses(formatterContract);
		Set<INakedSimpleType> types = getElementsOfTypeInScope(INakedSimpleType.class);
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
	protected void createFormatter(OJAnnotatedPackage util,String name){
		OJAnnotatedClass formatter = new OJAnnotatedClass(NameConverter.capitalize(name) + "Formatter");
		util.addToClasses(formatter);
		formatter.addToImplementedInterfaces(new OJPathName("I" + formatter.getName()));
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
