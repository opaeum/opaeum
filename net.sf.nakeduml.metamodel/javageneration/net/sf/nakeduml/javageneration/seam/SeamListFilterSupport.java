package net.sf.nakeduml.javageneration.seam;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;

public class SeamListFilterSupport extends AbstractJavaProducingVisitor {

	private static String FILTER_ROLE = "Filter";
	
	@VisitAfter(matchSubclasses=true)
	public void visitClass(INakedClassifier c) {
		if (OJUtil.hasOJClass(c)&& isPersistent(c) && !c.getIsAbstract()) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			OJAnnotationValue name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Role"));
			name.putAttribute(new OJAnnotationAttributeValue("name",c.getMappingInfo().getJavaName().getDecapped().toString()+FILTER_ROLE));
			name.putAttribute(new OJAnnotationAttributeValue("scope",new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "PAGE")));
			ojClass.putAnnotation(name);
		}
	}
	
	@VisitAfter(matchSubclasses=true)
	public void visitAttribute(INakedProperty p){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
		if (map.isOne() && !p.isDerived() && p.getNakedBaseType().getName().equalsIgnoreCase("boolean")) {
			OJAnnotatedClass owner = findJavaClass(p.getOwner());
			OJAnnotatedField field = new OJAnnotatedField();
			field.setName(p.getMappingInfo().getJavaName().getDecapped().toString()+FILTER_ROLE);
			field.setType(new OJPathName("util.BooleanFilter"));
			field.setVisibility(OJVisibilityKind.PRIVATE);
			owner.addToFields(field);
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			field.setInitExp("BooleanFilter.NONE");
			
			buildGetter(owner, map, new OJPathName("util.BooleanFilter"));
			buildSetter(owner, map, new OJPathName("util.BooleanFilter"));
		} else if (map.isOne() && !p.isDerived() && (p.getNakedBaseType() instanceof INakedEnumeration || p.getNakedBaseType() instanceof INakedSimpleType)) {
			OJAnnotatedClass owner = findJavaClass(p.getOwner());
			if (owner!=null) {
				OJAnnotatedField field = new OJAnnotatedField();
				field.setName(p.getMappingInfo().getJavaName().getDecapped().toString()+FILTER_ROLE);
				field.setType(map.javaTypePath());
				field.setVisibility(OJVisibilityKind.PRIVATE);
				owner.addToFields(field);
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
				
				buildGetter(owner, map, map.javaTypePath());
				buildSetter(owner, map, map.javaTypePath());
			}
		}
	}
	
	private OJOperation buildGetter(OJAnnotatedClass owner,NakedStructuralFeatureMap map,OJPathName javaTypePath){
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter()+FILTER_ROLE);
		getter.setReturnType(javaTypePath);
		owner.addToOperations(getter);
		if(owner instanceof OJAnnotatedInterface){
		}else{
			getter.getBody().addToStatements("return " + map.umlName()+FILTER_ROLE);
		}
		getter.setStatic(map.isStatic());
		return getter;
	}
	
	private void buildSetter(OJAnnotatedClass owner,NakedStructuralFeatureMap map,OJPathName javaTypePath) {
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName(map.setter()+FILTER_ROLE);
		setter.addParam(map.umlName()+FILTER_ROLE, javaTypePath);
		owner.addToOperations(setter);
		setter.getBody().addToStatements("this." + map.umlName()+FILTER_ROLE + "=" + map.umlName()+FILTER_ROLE);
	}
	
}
