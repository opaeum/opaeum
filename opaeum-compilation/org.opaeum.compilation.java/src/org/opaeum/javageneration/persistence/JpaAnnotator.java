package org.opaeum.javageneration.persistence;

import java.util.Collection;

import javax.persistence.GenerationType;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeImplementor.class},after = {AttributeImplementor.class})
public class JpaAnnotator extends AbstractJpaAnnotator{
	public static boolean DEVELOPMENT_MODE = true;
	public static boolean isJpa2 = false;
	@VisitBefore(matchSubclasses = true)
	public void visitEnumeration(Enumeration e){
		if(!EmfElementUtil.isMarkedForDeletion(e)){
			// TODO do something similar for interfaces, even without
			if(ojUtil.getCodeGenerationStrategy(e) == CodeGenerationStrategy.ALL){
				OJAnnotatedClass clss = new OJAnnotatedClass(e.getName() + "Entity");
				JpaUtil.addClass(clss);
				JpaUtil.buildTableAnnotation(clss, PersistentNameUtil.getPersistentName(e).getAsIs(), config);
				clss.setSuperclass(new OJPathName("org.opaeum.audit.AbstractPersistentEnum"));
				findOrCreatePackage(ojUtil.packagePathname(e.getNamespace())).addToClasses(clss);
				clss.getDefaultConstructor();
				createTextPath(clss, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
				OJConstructor constr = new OJConstructor();
				constr.addParam("e", ojUtil.classifierPathname(e));
				constr.getBody().addToStatements("super(e)");
				clss.addToConstructors(constr);
				for(Property p:e.getOwnedAttributes()){
					PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
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
	protected void visitComplexStructure(Classifier complexType){
		OJAnnotatedClass ojClass = findJavaClass(complexType);
		if(isPersistent(complexType) && ojUtil.hasOJClass(complexType)){
			buildToString(ojClass, complexType);
			NameWrapper persistentName = PersistentNameUtil.getPersistentName(complexType);
			OJAnnotationValue table = JpaUtil.buildTableAnnotation(ojClass, persistentName.getAsIs(), this.config, complexType.getNamespace());
			if(complexType instanceof Class){
				OJAnnotationAttributeValue uniqueConstraints = buildUniqueConstraintAnnotations((Class) complexType);
				if(uniqueConstraints.hasValues()){
					table.putAttribute(uniqueConstraints);
					JpaUtil.addNamedQueryForUniquenessConstraints(ojClass, (Class) complexType, ojUtil);
				}
			}else if(complexType instanceof Association && (EmfAssociationUtil.isClass((Association) complexType))){
				Association ass = (Association) complexType;
				PropertyMap map1 = ojUtil.buildStructuralFeatureMap(ass.getMemberEnds().get(0));
				PropertyMap map2 = ojUtil.buildStructuralFeatureMap(ass.getMemberEnds().get(1));
				OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
				table.putAttribute(uniqueConstraints);
				if(map1.isOne()){
					if(map2.isOne()){
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map1.getPersistentName()
								.getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
						OJAnnotationValue uniquenessConstraint2 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns2 = new OJAnnotationAttributeValue("columnNames", map2.getPersistentName()
								.getAsIs());
						uniquenessConstraint2.putAttribute(columns2);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint2);
					}else{
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map2.getPersistentName()
								.getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						if(map2.getBaseType() instanceof Interface){
							columns1.addStringValue(map2.getPersistentName().getAsIs() + "_type");
						}
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
					}
				}else{
					if(map2.isOne()){
						OJAnnotationValue uniquenessConstraint2 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns2 = new OJAnnotationAttributeValue("columnNames", map1.getPersistentName().getAsIs());
						uniquenessConstraint2.putAttribute(columns2);
						if(map1.getBaseType() instanceof Interface){
							columns2.addStringValue(map1.getPersistentName().getAsIs() + "_type");
						}
						uniqueConstraints.addAnnotationValue(uniquenessConstraint2);
					}else{
						OJAnnotationValue uniquenessConstraint1 = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
						OJAnnotationAttributeValue columns1 = new OJAnnotationAttributeValue("columnNames", map1.getPersistentName()
								.getAsIs());
						columns1.addStringValue(map2.getPersistentName().getAsIs());
						uniquenessConstraint1.putAttribute(columns1);
						uniqueConstraints.addAnnotationValue(uniquenessConstraint1);
					}
				}
			}
			// All classes get default strategy
			annotateInheritanceType(ojClass);
			if(ojUtil.getCodeGenerationStrategy(complexType) == CodeGenerationStrategy.ABSTRACT_SUPERTYPE_ONLY){
				OJAnnotationValue mappedSuperclass = new OJAnnotationValue(new OJPathName("javax.persistence.MappedSuperclass"));
				ojClass.addAnnotationIfNew(mappedSuperclass);
			}else{
				JpaUtil.addClass(ojClass);
			}
			boolean behaviourWithSpecification = complexType instanceof Behavior && ((Behavior) complexType).getSpecification() != null;
			if(!behaviourWithSpecification && complexType.getGeneralizations().isEmpty()){
				JpaIdStrategy jpaIdStrategy = JpaIdStrategyFactory.getStrategy(GenerationType.valueOf(config.getIdGeneratorStrategy()));
				if(!config.shouldBeCm1Compatible()){
					jpaIdStrategy = new Jpa2IdTable();
				}
				JpaUtil.addAndAnnotatedIdAndVersion(jpaIdStrategy, ojClass, complexType);
				if(complexType instanceof Class){
					Collection<Property> primaryKeyProperties = EmfClassifierUtil.getPrimaryKeyProperties( ((Class) complexType));
					if(primaryKeyProperties.size() == 1){
						PropertyMap map = ojUtil.buildStructuralFeatureMap(primaryKeyProperties.iterator().next());
						OJAnnotatedField field = (OJAnnotatedField) ojClass.findField(map.fieldname());
						field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Id")));
						if(map.getBaseType().conformsTo(getLibrary().getIntegerType())){
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
						for(Property p:primaryKeyProperties){
							PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
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
	private OJAnnotationAttributeValue buildUniqueConstraintAnnotations(Class entity){
		OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
		for(Property p:EmfPropertyUtil.getUniquenessConstraints(entity)){
			OJAnnotationValue uniquenessConstraint = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
			OJAnnotationAttributeValue columns = new OJAnnotationAttributeValue("columnNames", PersistentNameUtil.getPersistentName(p).getAsIs());
			for(Property q:p.getOtherEnd().getQualifiers()){
				columns.addStringValue(PersistentNameUtil.getPersistentName(q).getAsIs());
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
	public void visitAssociationClass(Association ac){
	}
	protected void visitProperty(Classifier umlOwner,PropertyMap map){
		if(isPersistent(umlOwner) && ojUtil.hasOJClass(umlOwner)){
			if(!(EmfPropertyUtil.isDerived( map.getProperty()) || map.isStatic())){
				if(map.isOne()){
					mapXToOne(umlOwner, map);
				}else{
					mapXToMany(umlOwner, map);
				}
			}
		}
	}
	private void mapXToMany(Classifier umlOwner,PropertyMap map){
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		Property p = map.getProperty();
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(
				new OJPathName("javax.persistence.FetchType"), "LAZY"));
		if(p.getType() instanceof Enumeration){
			implementManyForValueTypes(p, map, field);
		}else if(EmfClassifierUtil.isSimpleType(p.getType())){
			implementManyForValueTypes(p, map, field);
		}else if(isPersistent((Classifier) p.getType())){
			// Entities and behaviors, emulated entities
			OJAnnotationValue toMany = null;
			OJPathName baseTypePath = ojUtil.classifierPathname((Classifier) p.getType());
			OJAnnotationAttributeValue targetEntity = new OJAnnotationAttributeValue("targetEntity", baseTypePath);
			if(EmfPropertyUtil.isInverse(p)){
				// Can only be bidirectional - implies the presence of
				// non-inverse other end
				toMany = new OJAnnotationValue(new OJPathName("javax.persistence." + (map.isOneToMany() ? "OneToMany" : "ManyToMany")));
				if(p.isOrdered() && map.isOneToMany()){
					// Map as non-inverse to allow hibernate to update index -
					// NB!!! this feature requires hibernate
					String keyToParentTable = JpaUtil.calculateKeyToOwnerTable(p);
					boolean fkNullable = !EmfPropertyUtil.isRequired(p.getOtherEnd());
					JpaUtil.addJoinColumn(field, keyToParentTable, fkNullable);
				}else{
					String otherEndName = null;
					if(p.getOtherEnd() != null){
						otherEndName = ojUtil.buildStructuralFeatureMap(((Property) p).getOtherEnd()).fieldname();
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
			if(p.isComposite() || p.getType() instanceof DataType){
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
	private void implementManyForValueTypes(Property f,PropertyMap map,OJAnnotatedField field){
		if(isJpa2){
			OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("javax.persistence.ElementCollection"));
			OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetEntity", ojUtil.classifierPathname((Classifier) f
					.getType()));
			collectionOfElements.putAttribute(targetElement);
			OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
					"javax.persistence.FetchType"), "LAZY"));
			collectionOfElements.putAttribute(lazy);
			field.addAnnotationIfNew(collectionOfElements);
		}
		OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
		Element umlOwner = f.getOwner();
		String tableName = PersistentNameUtil.getPersistentName(umlOwner) + "_" + PersistentNameUtil.getPersistentName(f).getWithoutId();
		joinTable.putAttribute(new OJAnnotationAttributeValue("name", tableName));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("unique", new Boolean(map.isOneToOne())));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("name", PersistentNameUtil.getPersistentName(umlOwner).toString() + "_id"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
		joinTable.putAttribute(new OJAnnotationAttributeValue("joinColumns", joinColumn));
		field.addAnnotationIfNew(joinTable);
	}
	private void implementMap(PropertyMap map,OJAnnotatedField field){
		PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(map.getProperty());
		OJAnnotationValue mapKey = new OJAnnotationValue(new OJPathName("javax.persistence.MapKey"));
		field.putAnnotation(mapKey);
		mapKey.putAttribute("name", otherMap.qualifierProperty());
	}
	private void buildToString(OJAnnotatedClass owner,Classifier umlClass){
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
		// List<? extends Property> features = umlClass.getEffectiveAttributes();
		// for(Property f:features){
		// if(!f.isDerived()){
		// NakedStructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
		// if(map.isOne() && !f.isInverse()){
		// if(isPersistent(f.getType())){
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
	private boolean isMap(PropertyMap map){
		return super.isMap(map.getProperty());
	}
}
