package org.opaeum.javageneration.persistence;

import java.util.Collection;

import javax.persistence.GenerationType;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedDataType;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeImplementor.class,PersistentNameGenerator.class},after = {AttributeImplementor.class})
public class JpaAnnotator extends AbstractJpaAnnotator{
	public static boolean DEVELOPMENT_MODE = true;
	public static boolean isJpa2 = false;
	@VisitBefore(matchSubclasses = true)
	public void visitEnumeration(INakedEnumeration e){
		if(!e.isMarkedForDeletion()){
			// TODO do something similar for interfaces, even without
			if(e.getCodeGenerationStrategy().isAll()){
				OJAnnotatedClass clss = new OJAnnotatedClass(e.getName() + "Entity");
				JpaUtil.addEntity(clss);
				JpaUtil.buildTableAnnotation(clss, e.getMappingInfo().getPersistentName().getAsIs(), config);
				clss.setSuperclass(new OJPathName("org.opaeum.audit.AbstractPersistentEnum"));
				findOrCreatePackage(OJUtil.packagePathname(e.getNameSpace())).addToClasses(clss);
				clss.getDefaultConstructor();
				createTextPath(clss, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
				OJConstructor constr = new OJConstructor();
				constr.addParam("e", OJUtil.classifierPathname(e));
				constr.getBody().addToStatements("super(e)");
				clss.addToConstructors(constr);
				for(INakedProperty p:e.getOwnedAttributes()){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					if(map.isOne()){
						constr.getBody().addToStatements("this." + map.fieldname() + "=e." + map.getter() + "()");
						OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
						clss.addToFields(field);
						mapXToOne(map, clss);
					}
				}
			}
		}
	}
	protected void visitComplexStructure(INakedComplexStructure complexType){
		OJAnnotatedClass ojClass = findJavaClass(complexType);
		if(isPersistent(complexType) && OJUtil.hasOJClass(complexType)){
			buildToString(ojClass, complexType);
			NameWrapper persistentName = complexType.getMappingInfo().getPersistentName();

			OJAnnotationValue table = JpaUtil.buildTableAnnotation(ojClass, persistentName.getAsIs(), this.config, complexType.getNameSpace());
			if(complexType instanceof INakedEntity){
				OJAnnotationAttributeValue uniqueConstraints = buildUniqueConstraintAnnotations((INakedEntity) complexType);
				if(uniqueConstraints.hasValues()){
					table.putAttribute(uniqueConstraints);
					JpaUtil.addNamedQueryForUniquenessConstraints(ojClass, (INakedEntity) complexType);
				}
			}else if(complexType instanceof INakedAssociation && ((INakedAssociation) complexType).isClass()){
				INakedAssociation ass = (INakedAssociation) complexType;
				NakedStructuralFeatureMap map1 = OJUtil.buildStructuralFeatureMap(ass.getEnd1());
				NakedStructuralFeatureMap map2 = OJUtil.buildStructuralFeatureMap(ass.getEnd2());
				OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
				table.putAttribute(uniqueConstraints);
				if(map1.isOne()){
					if(map2.isOne()){
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map1.getProperty().getMappingInfo()
								.getPersistentName().getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
						OJAnnotationValue uniquenessConstraint2 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns2 = new OJAnnotationAttributeValue("columnNames", map2.getProperty().getMappingInfo()
								.getPersistentName().getAsIs());
						uniquenessConstraint1.putAttribute(columns2);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint2);
					}else{
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map2.getProperty().getMappingInfo()
								.getPersistentName().getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						if(map2.getProperty().getNakedBaseType() instanceof INakedInterface){
							columns1.addStringValue(map2.getProperty().getMappingInfo().getPersistentName().getAsIs() + "_type");
						}
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
					}
				}else{
					if(map2.isOne()){
						OJAnnotationValue uniquenessConstraint2 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns2 = new OJAnnotationAttributeValue("columnNames", map1.getProperty().getMappingInfo()
								.getPersistentName().getAsIs());
						uniquenessConstraint2.putAttribute(columns2);
						if(map1.getProperty().getNakedBaseType() instanceof INakedInterface){
							columns2.addStringValue(map1.getProperty().getMappingInfo().getPersistentName().getAsIs() + "_type");
						}
						uniqueConstraints.addAnnotationValue(uniquenessConstraint2);
					}else{
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map1.getProperty().getMappingInfo()
								.getPersistentName().getAsIs());
						columns1.addStringValue(map2.getProperty().getMappingInfo().getPersistentName().getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
					}
				}
			}
			// All classes get default strategy
			annotateInheritanceType(ojClass);
			if(complexType.getCodeGenerationStrategy().isAbstractSupertypeOnly()){
				OJAnnotationValue mappedSuperclass = new OJAnnotationValue(new OJPathName("javax.persistence.MappedSuperclass"));
				ojClass.addAnnotationIfNew(mappedSuperclass);
			}else{
				JpaUtil.addEntity(ojClass);
			}
			boolean behaviourWithSpecification = complexType instanceof INakedBehavior
					&& ((INakedBehavior) complexType).getSpecification() != null;
			if(!behaviourWithSpecification && complexType.getGeneralizations().isEmpty()){
				JpaIdStrategy jpaIdStrategy = JpaIdStrategyFactory.getStrategy(GenerationType.valueOf(config.getIdGeneratorStrategy()));
				if(!config.shouldBeCm1Compatible()){
					jpaIdStrategy = new Jpa2IdTable();
				}
				JpaUtil.addAndAnnotatedIdAndVersion(jpaIdStrategy, ojClass, complexType);
				if(complexType instanceof INakedEntity){
					Collection<INakedProperty> primaryKeyProperties = ((INakedEntity) complexType).getPrimaryKeyProperties();
					if(primaryKeyProperties.size() == 1){
						NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(primaryKeyProperties.iterator().next());
						OJAnnotatedField field = (OJAnnotatedField) ojClass.findField(map.fieldname());
						field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Id")));
						if(map.getProperty().getNakedBaseType().conformsTo(getLibrary().getIntegerType())){
							OJAnnotatedOperation getId = new OJAnnotatedOperation("getId", new OJPathName("Long"));
							ojClass.addToOperations(getId);
							getId.initializeResultVariable(field.getName() + ".longValue()");
							OJAnnotatedOperation setId = new OJAnnotatedOperation("setId");
							setId.addParam("i", new OJPathName("Long"));
							ojClass.addToOperations(setId);
						}
					}else if(primaryKeyProperties.size() > 1){
						OJAnnotatedClass cls = new OJAnnotatedClass(ojClass.getName() + "Id");
						ojClass.getMyPackage().addToClasses(cls);
						createTextPath(cls, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
						cls.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Embeddable")));
						cls.addToImplementedInterfaces(new OJPathName("java.io.Serializable"));
						for(INakedProperty p:primaryKeyProperties){
							NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
							OJUtil.addPersistentProperty(cls, map.umlName(), map.javaTypePath(), true);
							mapXToOne(map, cls);
						}
						OJUtil.addPersistentProperty(ojClass, "id", cls.getPathName(), true).addAnnotationIfNew(
								new OJAnnotationValue(new OJPathName("javax.persistence.EmbeddedId")));
					}
				}
			}else{
				OJAnnotationValue discriminatorValue = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorValue"),
						persistentName.getAsIs());
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
	public void visitAssociationClass(INakedAssociation ac){
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
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		INakedProperty p = map.getProperty();
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(
				new OJPathName("javax.persistence.FetchType"), "LAZY"));
		if(p.getNakedBaseType() instanceof INakedEnumeration){
			implementManyForValueTypes(p, map, field);
		}else if(p.getNakedBaseType() instanceof INakedSimpleType){
			implementManyForValueTypes(p, map, field);
		}else if(isPersistent(p.getNakedBaseType())){
			// Entities and behaviors, emulated entities
			OJAnnotationValue toMany = null;
			OJPathName baseTypePath = OJUtil.classifierPathname(p.getNakedBaseType());
			OJAnnotationAttributeValue targetEntity = new OJAnnotationAttributeValue("targetEntity", baseTypePath);
			if(p.isInverse()){
				// Can only be bidirectional - implies the presence of
				// non-inverse other end
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
						otherEndName = new NakedStructuralFeatureMap(((INakedProperty) p).getOtherEnd()).fieldname();
					}else{
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
			if(isMap(map)){
				implementMap(map, field);
			}
		}else{
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
	private void implementManyForValueTypes(INakedProperty f,NakedStructuralFeatureMap map,OJAnnotatedField field){
		if(isJpa2){
			OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("javax.persistence.ElementCollection"));
			OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetClass", OJUtil.classifierPathname(f
					.getNakedBaseType()));
			collectionOfElements.putAttribute(targetElement);
			OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
					"javax.persistence.FetchType"), "LAZY"));
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
	private void implementMap(NakedStructuralFeatureMap map,OJAnnotatedField field){
		NakedStructuralFeatureMap otherMap = OJUtil.buildStructuralFeatureMap(map.getProperty());
		OJAnnotationValue mapKey = new OJAnnotationValue(new OJPathName("javax.persistence.MapKey"));
		field.putAnnotation(mapKey);
		mapKey.putAttribute("name", otherMap.qualifierProperty());
	}
	private void buildToString(OJAnnotatedClass owner,INakedClassifier umlClass){
		// OJOperation toString = owner.findToString();
		// if(toString == null){
		// toString = new OJAnnotatedOperation("toString");
		// owner.addToOperations(toString);
		// toString.setReturnType(new OJPathName("String"));
		// }
		// toString.setBody(new OJBlock());
		// toString.setReturnType(new OJPathName("String"));
		// toString.setName("toString");
		// OJAnnotatedField sb = new OJAnnotatedField("sb", new OJPathName("StringBuilder"));
		// sb.setInitExp("new StringBuilder()");
		// toString.getBody().addToLocals(sb);
		// List<? extends INakedProperty> features = umlClass.getEffectiveAttributes();
		// for(INakedProperty f:features){
		// if(!f.isDerived()){
		// NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
		// if(map.isOne() && !f.isInverse()){
		// if(isPersistent(f.getNakedBaseType())){
		// OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"" + map.umlName() + "=null\")");
		// ifNull.setElsePart(new OJBlock());
		// ifNull.getElsePart().addToStatements("sb.append(\"" + map.umlName() + ".id=\")");
		// OJSimpleStatement b = new OJSimpleStatement("sb.append(" + map.getter() + "().getId())");
		// ifNull.getElsePart().addToStatements(b);
		// ifNull.getElsePart().addToStatements("sb.append(\";\")");
		// toString.getBody().addToStatements(ifNull);
		// }else{
		// toString.getBody().addToStatements("sb.append(\"" + map.umlName() + "=\")");
		// toString.getBody().addToStatements("sb.append(" + map.getter() + "())");
		// toString.getBody().addToStatements("sb.append(\";\")");
		// }
		// }
		// }
		// }
		// toString.getBody().addToStatements("return sb.toString()");
		// owner.addToOperations(toString);
	}
	// TODO move elsewhere??
	public static void addEquals(OJClass ojClass){
		// OJOperation equals = OJUtil.findOperation(ojClass, "equals");
		// if(equals == null){
		// equals = new OJAnnotatedOperation("equals");
		// ojClass.addToOperations(equals);
		// }else{
		// equals.removeAllFromParameters();
		// equals.setBody(new OJBlock());
		// }
		// equals.addParam("o", new OJPathName("Object"));
		// equals.setReturnType(new OJPathName("boolean"));
		// OJIfStatement ifThis = new OJIfStatement("this==o", "return true");
		// OJIfStatement ifNotInstance = new OJIfStatement("!(o instanceof " + ojClass.getName() + ")", "return false");
		// ifThis.addToElsePart(ifNotInstance);
		// OJAnnotatedField other = new OJAnnotatedField("other", ojClass.getPathName());
		// other.setInitExp("(" + ojClass.getName() + ")o");
		// ifNotInstance.setElsePart(new OJBlock());
		// ifNotInstance.getElsePart().addToLocals(other);
		// OJIfStatement ifIdNull = new OJIfStatement("getId()==null", "return false");
		// ifNotInstance.addToElsePart(ifIdNull);
		// ifIdNull.addToElsePart("return getId().equals(other.getId())");
		// equals.getBody().addToStatements(ifThis);
	}
	private boolean isMap(NakedStructuralFeatureMap map){
		return super.isMap(map.getProperty());
	}
}
