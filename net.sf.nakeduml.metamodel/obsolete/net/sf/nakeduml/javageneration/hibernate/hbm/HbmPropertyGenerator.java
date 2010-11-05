package net.sf.nakeduml.javageneration.hibernate.hbm;

import net.hibernatehbmmetamodel.AbstractClass;
import net.hibernatehbmmetamodel.Access;
import net.hibernatehbmmetamodel.Collection;
import net.hibernatehbmmetamodel.CollectionKey;
import net.hibernatehbmmetamodel.Filter;
import net.hibernatehbmmetamodel.FirstLevelClass;
import net.hibernatehbmmetamodel.Generated;
import net.hibernatehbmmetamodel.HbmElement;
import net.hibernatehbmmetamodel.ManyToOne;
import net.hibernatehbmmetamodel.ManyToOneLazy;
import net.hibernatehbmmetamodel.Property;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;

public class HbmPropertyGenerator extends HbmJavaProducingVisitor {
	public static final boolean DEVELOPMENT_MODE = true;
	private HbmElement hbmWorkspace;
	
	@VisitAfter(matchSubclasses = true)
	public void annotateProperty(INakedProperty f) {
		INakedClassifier owner = f.getOwner();		
		if (isPersistent(owner) && OJUtil.hasOJClass(owner) && (owner.hasStereotype(SINGLE_TABLE_INHERITANCE) || isInSingleTableInheritance(owner))) {
			if (!f.isDerived()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
				if (map.isOne()) {
					mapXToOne(f, map);
				} else {
					mapXToMany(f, map);
				}
			}
		}
	}

	private void mapXToOne(INakedProperty f, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findJavaClass(f.getOwner());
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		if (f.getNakedBaseType() instanceof INakedEnumeration) {
			mapXToOneEnumeration(f, owner, field);
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			mapXToOneSimpleType(f, owner, field);
		} else if (isPersistent(f.getNakedBaseType())) {
			mapXToOnePersistentType(f, owner, field);
		}
	}

	private void mapXToOnePersistentType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		
		AbstractClass abstractClass = null;
		if (!f.getOwner().hasSupertype()) {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName());
		} else {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName()+"::Join");
		}
		
		// Entities and behaviors
		// Inverse is always OneToOne
		String toOneType = f.isInverse() ? "javax.persistence.OneToOne" : "javax.persistence.ManyToOne";
		
		if (f.isInverse()) {
			throw new RuntimeException("Not yet implemented!");
		} else {
			ManyToOne manyToOne = new ManyToOne(abstractClass);
			manyToOne.setAccess(Access.FIELD);
			manyToOne.setClassName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName());
			manyToOne.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
			manyToOne.setEntityName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName());
			manyToOne.setName(f.getName());
			manyToOne.setManyToOneLazy(ManyToOneLazy.PROXY);
			
//			for (INakedProperty p : ((INakedEntity)f.getOwner()).getUniquenessConstraints()) {
//				if (p.equals(f)) {
//					manyToOne.setUniqueKey(f.getName()+"DeletedOnUniqueKey");
//					List<HbmElement> children = manyToOne.getAbstractClass().getOwnedElement();
//					for (HbmElement hbmElement : children) {
//						if (hbmElement instanceof Property && hbmElement.getQualifiedName().endsWith("DeletedOn")) {
//							((Property)hbmElement).setUniqueKey(f.getName()+"DeletedOnUniqueKey");
//						}
//					}
//				}
//			}
		}
		
		
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName(toOneType));
		JpaUtil.fetchLazy(toOne);
		if (f.getNakedBaseType() instanceof INakedStructuredDataType || f.isComposite()) {
			// Compositional semantics - should also delete Orphan
//			JpaUtil.cascadeAll(toOne);
			throw new RuntimeException("Not yet implemented!");
		}
		// TODO with oneToOne components map a relationship
		// table.
		if (f.isInverse()) {
			// Implies navigable other end and INakedProperty
//			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((f).getOtherEnd());
//			toOne.addAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.umlName()));
			throw new RuntimeException("Not yet implemented!");
		} else {
			// Remember that oneToOne uniqueness will be added as a
			// uniqueConstraint
//			OJAnnotationValue column = JpaUtil.addJoinColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), !f.isRequired());
//			if (isOtherEndOrdered(f)) {
//				// Emulate "inverse" behavior
//				column.addAttribute(new OJAnnotationAttributeValue("insertable", false));
//				column.addAttribute(new OJAnnotationAttributeValue("updatable", false));
//			}
		}
		field.addAnnotationIfNew(toOne);
	}

	private void mapXToOneSimpleType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		
		AbstractClass abstractClass = null;
		if (!f.getOwner().hasSupertype()) {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName());
		} else {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName()+"::Join");
		}
		
		
		if (this.workspace.getMappedTypes().getDateType() != null && f.getNakedBaseType().conformsTo(this.workspace.getMappedTypes().getDateType())) {
			
			Property property = new Property(abstractClass);
			property.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
			property.setName(f.getName());
			property.setType("java.util.Date");
			property.setLazy(false);
			
//			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
//			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "DATE"));
//			field.addAnnotationIfNew(temporal);
		} else if (this.workspace.getMappedTypes().getDateType() != null && f.getNakedBaseType().conformsTo(this.workspace.getMappedTypes().getDateType())) {
			
			Property property = new Property(abstractClass);
			property.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
			property.setName(f.getName());
			property.setType("timestamp");
			property.setLazy(false);
			
//			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
//			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
//			field.addAnnotationIfNew(temporal);
		} else {
//			String name = super.getPathNameInModel(f.getNakedBaseType()).toString();
//			MappedType mappedType = this.workspace.getMappedTypes().getTypeMap().get(name);
//			if (mappedType != null && mappedType.getStrategy(JpaStrategy.class) != null) {
//				mappedType.getStrategy(JpaStrategy.class).annotated(field, f);
//			}
			
			Property property = new Property(abstractClass);
			property.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
			property.setName(f.getName());
			property.setLazy(false);
			property.setType("java.lang.String");
			
		}
	}

	private void mapXToOneEnumeration(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		AbstractClass abstractClass = null;
		if (!f.getOwner().hasSupertype()) {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName());
		} else {
			abstractClass = (AbstractClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName()+"::Join");
		}		
		
		Property propertyEnum = new Property();
		propertyEnum.setType(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName());
		propertyEnum.setName(f.getName());
		propertyEnum.setGenerated(Generated.NEVER);
		propertyEnum.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
		abstractClass.addToProperty(propertyEnum);		
	}

	private boolean isOtherEndOrdered(INakedProperty f) {
		return f instanceof INakedProperty && (f).getOtherEnd() != null && (f).getOtherEnd().isOrdered();
	}

	private void mapXToMany(INakedProperty f, NakedStructuralFeatureMap map) {
		
		FirstLevelClass hbmClass = (FirstLevelClass)findHbmElementFor(this.hbmWorkspace, f.getOwner().getMappingInfo().getQualifiedUmlName());
		
		NakedStructuralFeatureMap otherMap = null;
		if (f instanceof INakedProperty && (f).getOtherEnd() != null) {
			otherMap = new NakedStructuralFeatureMap((f).getOtherEnd());
		}
		OJAnnotatedClass owner = findJavaClass(f.getOwner());
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
		
		if (f.getNakedBaseType() instanceof INakedEnumeration) {
			// Not supported
			throw new RuntimeException("Not suported!");
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			// Not supported
			throw new RuntimeException("Not suported!");
		} else if (isPersistent(f.getNakedBaseType())) {
			// Entities and behaviors, emulated entities
			OJAnnotationValue toMany = null;
			OJPathName baseTypePath = OJUtil.classifierPathname(f.getNakedBaseType());
			OJAnnotationAttributeValue targetEntity = new OJAnnotationAttributeValue("targetEntity", baseTypePath);
			if (f.isInverse()) {
				// Can only be bidirectional - implies the presence of
				// non-inverse other
				// end
				Collection collection = new Collection(hbmClass);
				Filter filter = new Filter(collection);
				filter.setName("noDeletedObjects");
				filter.setCondition("deleted_on > localtimestamp");
				collection.setName(f.getName());
				collection.setQualifiedName(f.getMappingInfo().getQualifiedUmlName());
				collection.setHbmName("set");
				collection.setTable(f.getNakedBaseType().getMappingInfo().getPersistentName().toString());
				collection.setAccess(Access.FIELD);
				
				CollectionKey collectionKey = new CollectionKey(collection);
				collectionKey.setColumn(f.getOwner().getMappingInfo().getPersistentName()+"_id");
				collectionKey.setQualifiedName(f.getMappingInfo().getQualifiedJavaName()+"::Key");
				
				collection.getOneToMany().setQualifiedName(f.getMappingInfo().getQualifiedJavaName()+"::OneToMany");
				collection.getOneToMany().setClassName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName());
				
//				toMany = new OJAnnotationValue(new OJPathName("javax.persistence." + (map.isOneToMany() ? "OneToMany" : "ManyToMany")));
//				if (f.isOrdered() && map.isOneToMany()) {
//					// Map as non-inverse to allow hibernate to update index -
//					// NB!!! this feature requires hibernate
//					String keyToParentTable = calculateKeyToOwnerTable(f);
//					boolean fkNullable = !(f).getOtherEnd().isRequired();
//					JpaUtil.addJoinColumn(field, keyToParentTable, fkNullable);
//				} else {
//					toMany.addAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.umlName()));
//				}
			} else {
				
				throw new RuntimeException("Not yet implemented!");
				
//				toMany = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToMany"));
//				// ManyToMany or non-navigable XToMany
//				String tableName = calculateTableName(f);
//				String keyToParentTable = calculateKeyToOwnerTable(f);
//				OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
//				joinTable.addAttribute(new OJAnnotationAttributeValue("name", tableName));
//				OJAnnotationValue otherJoinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
//				otherJoinColumn.addAttribute(new OJAnnotationAttributeValue("unique", new Boolean(map.isOneToMany())));
//				otherJoinColumn.addAttribute(new OJAnnotationAttributeValue("name", f.getMappingInfo().getPersistentName().getAsIs()));
//				otherJoinColumn.addAttribute(new OJAnnotationAttributeValue("nullable", false));
//				joinTable.addAttribute(new OJAnnotationAttributeValue("inverseJoinColumns", otherJoinColumn));
//				OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
//				joinColumn.addAttribute(new OJAnnotationAttributeValue("name", keyToParentTable));
//				joinColumn.addAttribute(new OJAnnotationAttributeValue("nullable", false));
//				joinTable.addAttribute(new OJAnnotationAttributeValue("joinColumns", joinColumn));
//				field.addAnnotationIfNew(joinTable);
			}
			
			
//			toMany.addAttribute(lazy);
//			toMany.addAttribute(targetEntity);
//			if (f.isComposite()) {
//				JpaUtil.cascadeAll(toMany);
//			}
//			field.addAnnotationIfNew(toMany);
		} else {
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}

	private String calculateKeyToOwnerTable(INakedProperty f) {
		String keyToParentTable = null;
		if (f instanceof INakedProperty && (f).getAssociation() != null) {
			INakedProperty p = f;
			keyToParentTable = p.getOtherEnd().getMappingInfo().getPersistentName().getAsIs();
		} else {
			INakedClassifier nakedOwner = f.getOwner();
			keyToParentTable = nakedOwner.getMappingInfo().getPersistentName().toString();
		}
		return keyToParentTable;
	}

	private String calculateTableName(INakedProperty f) {
		String tableName = null;
		if (f instanceof INakedProperty && (f).getAssociation() != null) {
			INakedProperty p = f;
			tableName = ((INakedAssociation) p.getAssociation()).getMappingInfo().getPersistentName().toString();
		} else {
			INakedClassifier nakedOwner = f.getOwner();
			tableName = nakedOwner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName().getWithoutId();
		}
		return tableName;
	}
	
}
