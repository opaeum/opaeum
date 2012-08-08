package org.opaeum.javageneration.basicjava;

import java.util.Collection;

import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
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
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.AbstractFormatter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class FormatterBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@VisitBefore
	public void visitModel(Model m){
		if(!transformationContext.isIntegrationPhase()){
			OJPathName formatter = formatterPathName(m);
			OJPackage util = findOrCreatePackage(formatter.getHead());
			Collection<DataType> simpleTypes = getElementsOfType(DataType.class, EmfPackageUtil.getAllDependencies(m));
			createFormatter(util, formatter.getLast());
			createFormatterContract(util, formatter.getLast(), simpleTypes);
		}
	}
	private OJPathName formatterPathName(Element m){
		Package ro = EmfElementFinder.getRootObject(m);
		return ojUtil.utilPackagePath(ro).append(NameConverter.capitalize(ro.getName()) + "Formatter");
	}
	private void createFormatterContract(OJPackage util,String name,Collection<DataType> types){
		OJAnnotatedInterface formatterContract = new OJAnnotatedInterface("I" + name);
		util.addToClasses(formatterContract);
		createTextPath(formatterContract, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		for(DataType e:types){
			if(EmfClassifierUtil.isSimpleType(e)){
				OJAnnotatedOperation parse = new OJAnnotatedOperation("parse" + e.getName(), ojUtil.classifierPathname(e));
				parse.addParam("value", new OJPathName("String"));
				formatterContract.addToOperations(parse);
				OJAnnotatedOperation format = new OJAnnotatedOperation("format" + e.getName(), new OJPathName("String"));
				format.addParam("value",  ojUtil.classifierPathname(e));
				formatterContract.addToOperations(format);
			}
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
