package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

/**
 * This class optimses one-to-one relationships by implementing the inverse end as a singleton list. When using Hibernate for persistence,
 * this is needed to ensure that Hibernate resolves the entity from the cache
 */
public class OneToOneOptimiser extends AbstractHibernateGenerator{
	@VisitBefore(matchSubclasses = true)
	public void visitAssociationEnd(INakedProperty end){
		if(!end.isDerived() && end.getOtherEnd() != null){
			OJClassifier myOwner = findJavaClass(end.getOwner());
			if(myOwner instanceof OJClass){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(end);
				if(map.isOneToOne() && end.isInverse()){
					changeOneToOne(end, (OJClass) myOwner);
				}
			}
		}
	}
	private void changeOneToOne(INakedProperty aw,OJClass owner){
		StructuralFeatureMap map = new NakedStructuralFeatureMap(aw);
		StructuralFeatureMap otherMap = new NakedStructuralFeatureMap(aw.getOtherEnd());
		modifyField(owner, map);
		implementSetter(owner, map, otherMap);
		implementGetter(owner, map);
		OJOperation internalRemover = OJUtil.findOperation(owner, map.internalRemover());
		if(internalRemover != null){
			internalRemover.setBody(new OJBlock());
			String remove = "this." + map.umlName() + ".clear()";
			OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()!=null && " + map.getter() + "().equals(" + map.umlName() + ")", remove);
			internalRemover.getBody().addToStatements(ifEquals);
		}
		OJOperation internalAdder = OJUtil.findOperation(owner, map.internalAdder());
		if(internalAdder != null){
			String add= "this." + map.umlName() + ".add(" + map.umlName() + ")";
			internalAdder.setBody(new OJBlock());
			OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()==null || !" + map.getter() + "().equals(" + map.umlName() + ")", "this." + map.umlName() + ".clear()");
			ifEquals.addToThenPart(add);
			internalAdder.getBody().addToStatements(ifEquals);
		}
	}
	private void modifyField(OJClass owner,StructuralFeatureMap map){
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		OJPathName path = new OJPathName("java.util.List");
		field.setType(path);
		field.setInitExp("new ArrayList<" + map.javaBaseType() + ">()");
		OJAnnotationValue oneToOne = field.findAnnotation(new OJPathName("javax.persistence.OneToOne"));
		oneToOne.setType(new OJPathName("javax.persistence.OneToMany"));
		path.addToElementTypes(map.javaBaseTypePath());
		owner.addToImports(path);
		HibernateUtil.applyFilter(field,HibernateUtil.getHibernateDialect(this.config));
	}
	private void implementGetter(OJClass owner,StructuralFeatureMap map){
		OJOperation getter = OJUtil.findOperation(owner, map.getter());
		OJBlock getterBody = new OJBlock();
		getter.setBody(getterBody);
		OJIfStatement ifEmpty = new OJIfStatement("this." + map.umlName() + ".isEmpty()", "return null");
		OJBlock elsePart = new OJBlock();
		elsePart.addToStatements("return (" + map.javaType() + ")" + map.umlName() + ".get(0)");
		ifEmpty.setElsePart(elsePart);
		getterBody.addToStatements(ifEmpty);
	}
	private void implementSetter(OJClass owner,StructuralFeatureMap map,StructuralFeatureMap otherMap){
		OJOperation setter = OJUtil.findOperation(owner, map.setter());
		setter.getBody().getLocals().clear();
		OJBlock setterBody = setter.getBody();
		OJIfStatement ifNew = null;
		for(OJStatement s:setterBody.getStatements()){
			if(AttributeImplementor.IF_OLD_VALUE_NULL.equals(s.getName())){
				ifNew=(OJIfStatement) s;
			}
		}
		
		ifNew.setElsePart(new OJBlock());
		setter.setBody(setterBody);
		setterBody.addToStatements(ifNew);
		ifNew.setCondition("!this." + map.umlName() + ".contains(" + map.umlName() + ")");
		OJBlock setterImpl = new OJBlock();
		ifNew.setThenPart(setterImpl);
		OJIfStatement ifPrevValueSet = new OJIfStatement();
		setterImpl.addToStatements(ifPrevValueSet);
		ifPrevValueSet.setCondition("!this." + map.umlName() + ".isEmpty()");
		ifPrevValueSet.setThenPart(new OJBlock());
		ifPrevValueSet.getThenPart().addToStatements("this." + map.getter() + "()." + otherMap.internalRemover() + "(this)");
		setterImpl.addToStatements("this." + map.umlName() + ".clear()");
		OJIfStatement ifNotNull = new OJIfStatement(map.umlName() + "!=null", "this." + map.umlName() + ".add(" + map.umlName() + ")");
		ifNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "(this)");
		setterImpl.addToStatements(ifNotNull);
	}
}
