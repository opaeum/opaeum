package net.sf.nakeduml.javageneration.seam;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class SeamObjectFactoryMaker extends AbstractJavaProducingVisitor {
	private OJAnnotatedClass enumFactory;
	@Override
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		enumFactory = new OJAnnotatedClass();
		enumFactory.setName("SeamFactory");
		OJPathName path = new OJPathName("util");
		OJPackage pack =  this.javaModel.findPackage(path);
		enumFactory.setMyPackage(pack);
		
		OJAnnotationValue name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Name"), "seamFactory");
		enumFactory.putAnnotation(name);
		name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Scope"));
		name.putAttribute(new OJAnnotationAttributeValue("value",new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"),"EVENT")));
		enumFactory.putAnnotation(name);
		
		OJAnnotatedOperation oper = new OJAnnotatedOperation();
		oper.setName("booleanFilters");
		OJPathName pathName = new OJPathName("java.util.List");
		
		OJPathName enumPathName = new OJPathName("util.BooleanFilter");
		enumFactory.addToImports(enumPathName); 
		List<OJPathName> x = new ArrayList<OJPathName>();
		x.add(enumPathName);
		pathName.setElementTypes(x);
		oper.setReturnType(pathName);
		
		OJAnnotationValue factory = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Factory"));
		factory.putAttribute(new OJAnnotationAttributeValue("value", "booleanFilterFactory"));
		factory.putAttribute(new OJAnnotationAttributeValue("scope", new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "SESSION")));
		oper.putAnnotation(factory);
		
		OJStatement statement = new OJSimpleStatement("return Arrays.asList(BooleanFilter.values())");
		oper.getBody().addToStatements(statement);
		enumFactory.addToImports("java.util.Arrays");
		enumFactory.addToOperations(oper);
		
		super.createTextPath(enumFactory, JavaTextSource.GEN_SRC);
	}
	@VisitAfter
	public void visitEnums(INakedEnumeration c) {
		OJAnnotatedOperation oper = new OJAnnotatedOperation();
		oper.setName(c.getMappingInfo().getJavaName().getDecapped().getPlural().toString());
		OJPathName pathName = new OJPathName("java.util.List");
		
		OJPathName enumPathName = new OJPathName(c.getMappingInfo().getQualifiedJavaName().toString());
		enumFactory.addToImports(enumPathName); 
		List<OJPathName> x = new ArrayList<OJPathName>();
		x.add(enumPathName);
		pathName.setElementTypes(x);
		oper.setReturnType(pathName);
		
		OJAnnotationValue factory = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Factory"));
		factory.putAttribute(new OJAnnotationAttributeValue("value", c.getMappingInfo().getJavaName().getDecapped().toString()+"Factory"));
		factory.putAttribute(new OJAnnotationAttributeValue("scope", new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "SESSION")));
		oper.putAnnotation(factory);
		
		OJStatement statement = new OJSimpleStatement("return Arrays.asList(" + c.getMappingInfo().getJavaName().toString() + ".values())");
		oper.getBody().addToStatements(statement);
		enumFactory.addToImports("java.util.Arrays");
		
		enumFactory.addToOperations(oper);
	}
//	@VisitAfter(matchSubclasses=true)
//	public void visitAttribute(INakedProperty p){
//		if(!p.isDerived() && isPersistent(p.getBaseType()) && p.isComposite()){
//			OJAnnotatedOperation oper = new OJAnnotatedOperation();
//			oper.setName(p.getMappingInfo().getJavaName().getDecapped().getPlural().toString());
//			OJPathName factoryPathName = new OJPathName(p.getBaseType().getMappingInfo().getQualifiedJavaName().toString());
//			OJPathName ownerPathName = new OJPathName(p.getOwner().getMappingInfo().getQualifiedJavaName().toString());
//			enumFactory.addToImports(factoryPathName); 
//			enumFactory.addToImports(ownerPathName); 
//
//			OJPathName pathName = new OJPathName(p.getBaseType().getMappingInfo().getQualifiedJavaName().toString());
//			oper.setReturnType(pathName);
//			enumFactory.addToImports(new OJPathName("org.jboss.seam.contexts.Contexts")); 
//			
//			oper.getBody().addToStatements("return ((" + p.getOwner().getMappingInfo().getJavaName().toString() + ")Contexts.getConversationContext().get(\"" + p.getOwner().getMappingInfo().getJavaName().getDecapped().toString() + "\")).create" + p.getMappingInfo().getJavaName().getCapped().toString() + "()");
//			OJAnnotationValue factory = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Factory"));
//			factory.addAttribute(new OJAnnotationAttributeValue("value", p.getBaseType().getMappingInfo().getJavaName().getDecapped().toString()));
//			factory.addAttribute(new OJAnnotationAttributeValue("scope", new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "PAGE")));
//			oper.putAnnotation(factory);
//			enumFactory.addToOperations(oper);
//		}
//	}
	
	@VisitAfter(matchSubclasses=true)
	public void visitAttribute(INakedEntity c){
		
		if (!c.getIsAbstract()) {
			
			for (INakedProperty p : c.getEffectiveAttributes()) {
				if(!p.isDerived() && isPersistent(p.getNakedBaseType()) && p.isComposite()){
					
					
					OJAnnotatedOperation oper = new OJAnnotatedOperation();
					oper.setName(c.getMappingInfo().getJavaName().getDecapped().toString() + "_" + p.getMappingInfo().getJavaName().getDecapped().getPlural().toString());
					OJPathName factoryPathName = new OJPathName(p.getNakedBaseType().getMappingInfo().getQualifiedJavaName().toString());
					OJPathName ownerPathName = new OJPathName(c.getMappingInfo().getQualifiedJavaName().toString());
					enumFactory.addToImports(factoryPathName); 
					enumFactory.addToImports(ownerPathName); 
					
					OJPathName pathName = new OJPathName(p.getNakedBaseType().getMappingInfo().getQualifiedJavaName().toString());
					oper.setReturnType(pathName);
					enumFactory.addToImports(new OJPathName("org.jboss.seam.contexts.Contexts")); 
					
					oper.getBody().addToStatements("return ((" + c.getMappingInfo().getJavaName().toString() + ")Contexts.getConversationContext().get(\"" + c.getMappingInfo().getJavaName().getDecapped().toString() + "\")).create" + p.getMappingInfo().getJavaName().getCapped().toString() + "()");
					OJAnnotationValue factory = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Factory"));
					factory.putAttribute(new OJAnnotationAttributeValue("value", c.getMappingInfo().getJavaName().getDecapped().toString() + "_" + p.getNakedBaseType().getMappingInfo().getJavaName().toString()));
					factory.putAttribute(new OJAnnotationAttributeValue("scope", new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "PAGE")));
					oper.putAnnotation(factory);
					enumFactory.addToOperations(oper);
				}
			}			
		}
		
	}
	
}
