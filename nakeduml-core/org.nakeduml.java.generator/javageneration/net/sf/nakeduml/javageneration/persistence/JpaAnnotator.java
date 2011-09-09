package net.sf.nakeduml.javageneration.persistence;

import java.util.List;

import javax.persistence.GenerationType;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.basicjava.ToStringBuilder;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedDataType;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,PersistentNameGenerator.class,ToStringBuilder.class
},after = {
		AttributeImplementor.class,ToStringBuilder.class
})
public class JpaAnnotator extends AbstractJpaAnnotator{
	public static boolean DEVELOPMENT_MODE = true;
	public static boolean isJpa2 = false;
	protected void visitComplexStructure(INakedComplexStructure complexType){
		OJAnnotatedClass ojClass = findJavaClass(complexType);
		if(isPersistent(complexType) && OJUtil.hasOJClass(complexType)){
			buildToString(ojClass, complexType);
			OJAnnotationValue table = JpaUtil.buildTableAnnotation(ojClass, complexType.getMappingInfo().getPersistentName().getAsIs(), this.config,
					complexType.getNameSpace());
			if(complexType instanceof INakedEntity){
				OJAnnotationAttributeValue uniqueConstraints = buildUniqueConstraintAnnotations((INakedEntity) complexType);
				if(uniqueConstraints.hasValues()){
					table.putAttribute(uniqueConstraints);
					JpaUtil.addNamedQueryForUniquenessConstraints(ojClass, (INakedEntity) complexType);
				}
			}
			// All classes get default strategy
			annotateInheritanceType(ojClass);
			if(complexType.getCodeGenerationStrategy().isAbstractSupertypeOnly() || complexType.getCodeGenerationStrategy().isAbstractLibraryOnly()){
				OJAnnotationValue mappedSuperclass = new OJAnnotationValue(new OJPathName("javax.persistence.MappedSuperclass"));
				ojClass.addAnnotationIfNew(mappedSuperclass);
			}else{
				JpaUtil.addEntity(ojClass);
			}
			boolean behaviourWithSpecification = complexType instanceof INakedBehavior && ((INakedBehavior) complexType).getSpecification() != null;
			if(!behaviourWithSpecification && complexType.getGeneralizations().isEmpty()){
				JpaIdStrategy jpaIdStrategy = JpaIdStrategyFactory.getStrategy(GenerationType.valueOf(config.getIdGeneratorStrategy()));
				JpaUtil.addAndAnnotatedIdAndVersion(jpaIdStrategy, ojClass, complexType);
			}else{
				OJAnnotationValue discriminatorValue = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorValue"), complexType.getMappingInfo()
						.getPersistentName().getAsIs());
				ojClass.addAnnotationIfNew(discriminatorValue);
			}
			ojClass.putAnnotation(JpaUtil.buildFilterAnnotation("noDeletedObjects"));
		}
	}
	/**
	 * Includes all appropriately qualified relationships and one-to-one relationships
	 * 
	 * @param entity
	 * @return
	 */
	private OJAnnotationAttributeValue buildUniqueConstraintAnnotations(INakedEntity entity){
		OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
		for(INakedProperty p:entity.getUniquenessConstraints()){
			OJAnnotationValue uniquenessConstraint = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
			OJAnnotationAttributeValue columns = new OJAnnotationAttributeValue("columnNames", p.getMappingInfo().getPersistentName().getAsIs());
			for(INakedProperty q:p.getOtherEnd().getQualifiers()){
				columns.addStringValue(q.getMappingInfo().getPersistentName().getAsIs());
			}
			uniquenessConstraint.putAttribute(columns);
			uniqueConstraints.addAnnotationValue(uniquenessConstraint);
		}
		return uniqueConstraints;
	}
	private void annotateInheritanceType(OJAnnotatedClass owner){
		OJAnnotationValue inheritance = new OJAnnotationValue(new OJPathName("javax.persistence.Inheritance"));
		OJAnnotationAttributeValue inheritanceType = new OJAnnotationAttributeValue("strategy");
		inheritanceType.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.InheritanceType"), "JOINED"));
		inheritance.putAttribute(inheritanceType);
		owner.addAnnotationIfNew(inheritance);
		OJAnnotationValue discriminator = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorColumn"));
		OJAnnotationAttributeValue discriminatorType = new OJAnnotationAttributeValue("discriminatorType");
		discriminatorType.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.DiscriminatorType"), "STRING"));
		discriminator.putAttribute(discriminatorType);
		discriminator.putAttribute(new OJAnnotationAttributeValue("name", "type_descriminator"));
		owner.addAnnotationIfNew(discriminator);
	}
	@VisitBefore
	public void visitAssociationClass(INakedAssociationClass ac){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node){
		if(node.getActivity().isPersistent() && BehaviorUtil.mustBeStoredOnActivity(node)){
			if(node.isLongRunning()){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getLibrary());
				visitProperty(node.getActivity(), map);
			}else if(BehaviorUtil.hasExecutionInstance(node.getCalledElement())){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getLibrary());
				OJAnnotatedField field = (OJAnnotatedField) findJavaClass(node.getActivity()).findField(map.umlName());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			}
		}
	}
	protected void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		if(isPersistent(umlOwner) && OJUtil.hasOJClass(umlOwner)){
			if(!(map.getProperty().isDerived() || map.isStatic())){
				if(map.isOne()){
					mapXToOne(umlOwner, map);
				}else{
					mapXToMany(umlOwner, map);
				}
			}
		}
	}
	private void mapXToMany(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
		if(p.getNakedBaseType() instanceof INakedEnumeration){
			implementManyForValueTypes(p, map, field);
		}else if(p.getNakedBaseType() instanceof INakedSimpleType){
			implementManyForValueTypes(p, map, field);
		}else if(isPersistent(p.getNakedBaseType())){
			// Entities and behaviors, emulated entities
			OJAnnotationValue toMany = null;
			OJPathName baseTypePath = OJUtil.classifierPathname(p.getNakedBaseType());
			OJAnnotationAttributeValue targetEntity = new OJAnnotationAttributeValue("targetEntity", baseTypePath);
			if(p.isInverse() || p.getAssociation() instanceof INakedAssociationClass){
				// Can only be bidirectional - implies the presence of
				// non-inverse other
				// end
				toMany = new OJAnnotationValue(new OJPathName("javax.persistence." + (map.isOneToMany() ? "OneToMany" : "ManyToMany")));
				if(p.isOrdered() && map.isOneToMany()){
					// Map as non-inverse to allow hibernate to update index -
					// NB!!! this feature requires hibernate
					String keyToParentTable = JpaUtil.calculateKeyToOwnerTable(p);
					boolean fkNullable = !(p).getOtherEnd().isRequired();
					JpaUtil.addJoinColumn(field, keyToParentTable, fkNullable);
				}else{
					String otherEndName = null;
					if(p.getOtherEnd() != null){
						otherEndName = new NakedStructuralFeatureMap(((INakedProperty) p).getOtherEnd()).umlName();
					}
					toMany.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherEndName));
				}
			}else{
				toMany = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToMany"));
				JpaUtil.addJoinTable(umlOwner, map, field, this.config);
			}
			toMany.putAttribute(lazy);
			toMany.putAttribute(targetEntity);
			if(p.isComposite() || p.getBaseType() instanceof INakedDataType){
				JpaUtil.cascadeAll(toMany);
			}
			field.addAnnotationIfNew(toMany);
		}else{
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
	private void implementManyForValueTypes(INakedProperty f,NakedStructuralFeatureMap map,OJAnnotatedField field){
		if(isJpa2){
			OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("javax.persistence.ElementCollection"));
			OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetClass", OJUtil.classifierPathname(f.getNakedBaseType()));
			collectionOfElements.putAttribute(targetElement);
			OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
			collectionOfElements.putAttribute(lazy);
			field.addAnnotationIfNew(collectionOfElements);
		}
		OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
		INakedElement umlOwner = f.getOwner();
		String tableName = umlOwner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName().getWithoutId();
		joinTable.putAttribute(new OJAnnotationAttributeValue("name", tableName));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("unique", new Boolean(map.isOneToOne())));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("name", umlOwner.getMappingInfo().getPersistentName().toString() + "_id"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
		joinTable.putAttribute(new OJAnnotationAttributeValue("joinColumns", joinColumn));
		field.addAnnotationIfNew(joinTable);
	}
	private void buildToString(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = owner.findToString();
		if(toString == null){
			toString = new OJAnnotatedOperation("toString");
			owner.addToOperations(toString);
			toString.setReturnType(new OJPathName("String"));
		}
		toString.setBody(new OJBlock());
		toString.setReturnType(new OJPathName("String"));
		toString.setName("toString");
		OJAnnotatedField sb = new OJAnnotatedField("sb", new OJPathName("StringBuilder"));
		sb.setInitExp("new StringBuilder()");
		toString.getBody().addToLocals(sb);
		List<? extends INakedProperty> features = umlClass.getEffectiveAttributes();
		for(INakedProperty f:features){
			if(!f.isDerived()){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
				if(map.isOne() && !f.isInverse()){
					if(isPersistent(f.getNakedBaseType())){
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"" + map.umlName() + "=null\")");
						ifNull.setElsePart(new OJBlock());
						ifNull.getElsePart().addToStatements("sb.append(\"" + map.umlName() + ".id=\")");
						OJSimpleStatement b = new OJSimpleStatement("sb.append(" + map.getter() + "().getId())");
						ifNull.getElsePart().addToStatements(b);
						ifNull.getElsePart().addToStatements("sb.append(\";\")");
						toString.getBody().addToStatements(ifNull);
					}else{
						toString.getBody().addToStatements("sb.append(\"" + map.umlName() + "=\")");
						toString.getBody().addToStatements("sb.append(" + map.getter() + "())");
						toString.getBody().addToStatements("sb.append(\";\")");
					}
				}
			}
		}
		toString.getBody().addToStatements("return sb.toString()");
		owner.addToOperations(toString);
	}
	// TODO move elsewhere??
	public static void addEquals(OJClass ojClass){
		OJOperation equals = OJUtil.findOperation(ojClass, "equals");
		if(equals == null){
			equals = new OJAnnotatedOperation("equals");
			ojClass.addToOperations(equals);
		}else{
			equals.removeAllFromParameters();
			equals.setBody(new OJBlock());
		}
		equals.addParam("o", new OJPathName("Object"));
		equals.setReturnType(new OJPathName("boolean"));
		OJIfStatement ifThis = new OJIfStatement("this==o", "return true");
		OJIfStatement ifNotInstance = new OJIfStatement("!(o instanceof " + ojClass.getName() + ")", "return false");
		ifThis.addToElsePart(ifNotInstance);
		OJAnnotatedField other = new OJAnnotatedField("other", ojClass.getPathName());
		other.setInitExp("(" + ojClass.getName() + ")o");
		ifNotInstance.setElsePart(new OJBlock());
		ifNotInstance.getElsePart().addToLocals(other);
		OJIfStatement ifIdNull = new OJIfStatement("getId()==null", "return false");
		ifNotInstance.addToElsePart(ifIdNull);
		ifIdNull.addToElsePart("return getId().equals(other.getId())");
		equals.getBody().addToStatements(ifThis);
	}
}
