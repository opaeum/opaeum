package net.sf.nakeduml.javageneration.hibernate.hbm;

import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;

import java.util.ArrayList;
import java.util.List;

import net.hibernatehbmmetamodel.AbstractClass;
import net.hibernatehbmmetamodel.Column;
import net.hibernatehbmmetamodel.Discriminator;
import net.hibernatehbmmetamodel.Fetch;
import net.hibernatehbmmetamodel.Generator;
import net.hibernatehbmmetamodel.GeneratorClass;
import net.hibernatehbmmetamodel.HbmClass;
import net.hibernatehbmmetamodel.HbmWorkspace;
import net.hibernatehbmmetamodel.HibernateConfiguration;
import net.hibernatehbmmetamodel.Id;
import net.hibernatehbmmetamodel.Join;
import net.hibernatehbmmetamodel.Key;
import net.hibernatehbmmetamodel.Property;
import net.hibernatehbmmetamodel.PropertyColumn;
import net.hibernatehbmmetamodel.SubClass;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.models.INakedModel;

public class HbmClassGenerator extends HbmJavaProducingVisitor {
	public static final boolean DEVELOPMENT_MODE = true;
	private List<INakedClassifier> subClasses = new ArrayList<INakedClassifier>();
	private HbmWorkspace hbmWorkspace;
	
	public void createTextPath(String outputRoot){
		try{
			textWorkspace.findOrCreateTextOutputRoot(outputRoot);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	/*
	* This is because inheritence hierarchies must be specified in one hbm file, otherwise hibernate does not create the tables when jboss startsup.
	* This is because the order in which hibernate reads in the hbm files matters and there is no order in the classpath
	*/
	@VisitAfter
	public void visitModel(INakedModel model) {
		addSubClasses();
	}
	
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		//This is to make sure the dir is cleared if there are no sources to gen.
		createTextPath(HbmTextSource.GEN_RESOURCE);
		if (isPersistent(c) && hasOJClass(c) && (c.hasStereotype(SINGLE_TABLE_INHERITANCE) || isInSingleTableInheritance(c))) {
			HibernateConfiguration hibernateConfiguration = null;
			if (!c.hasSupertype()) {
				hibernateConfiguration = new HibernateConfiguration(this.hbmWorkspace);
				hibernateConfiguration.setExtendsBaseEntity(true);
				hibernateConfiguration.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::hibernateConfiguration");
				ArrayList<String> path = new ArrayList<String>(c.getPathName().getHead().getNames());
				path.add(c.getMappingInfo().getJavaName()+".hbm.xml");
				hibernateConfiguration.setPath(path);
				createTextPath(hibernateConfiguration, HbmTextSource.GEN_RESOURCE);
				
				hibernateConfiguration.setSchema(getSchema(c));
				
				//This is the base class
				HbmClass hbmClass = new HbmClass(hibernateConfiguration);
				hbmClass.setQualifiedName(c.getMappingInfo().getQualifiedUmlName());
				hbmClass.set_abstract(c.getIsAbstract());
				hbmClass.setEntityName(c.getMappingInfo().getQualifiedJavaName());
				hbmClass.setName(c.getMappingInfo().getQualifiedJavaName());
				hbmClass.setTable(c.getMappingInfo().getPersistentName().toString());
				hbmClass.setSelectBeforeUpdate(false);
				hbmClass.setLazy(true);
				
				Discriminator discriminator = new Discriminator(hbmClass);
				discriminator.setColumn("type_descriminator");
				discriminator.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Discriminator");
				discriminator.setInsert(true);
				discriminator.setNotNull(true);
				
				Id id = new Id(hbmClass);
				id.setName("id");
				id.setType("java.lang.Long");
				id.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Id");
				
				Column column = new Column(id);
				column.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Id:Column");
				column.setName("id");
				
				Generator generator = new Generator(id);
				generator.setGeneratorClass(GeneratorClass.HILO);
				generator.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Id::Generator");
				
				//Add object version and deleted on columns here
				Property objectVersion = new Property(hbmClass);
				objectVersion.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::ObjectVersion");
				objectVersion.setName("objectVersion");
				objectVersion.setType("int");
				objectVersion.setLazy(false);				
				
				PropertyColumn objectVersionColumn = new PropertyColumn(objectVersion);
				objectVersionColumn.setName("object_version");
				objectVersionColumn.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::ObjectVersion::Column");
				
				Property deletedOn = new Property(hbmClass);
				deletedOn.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::DeletedOn");
				deletedOn.setName("deletedOn");
				deletedOn.setType("java.util.Date");
				deletedOn.setLazy(false);				
				
				PropertyColumn propertyColumn = new PropertyColumn(deletedOn);
				propertyColumn.setName("deleted_on");
				propertyColumn.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::ObjectVersion::DeletedOn");
			} else {
				subClasses.add(c);
			}
//			OJAnnotatedClass ojClass = findJavaClass(umlClass);
//			buildToString(ojClass, umlClass);
//			addEquals(ojClass);

			

//			OJAnnotationValue table = JpaUtil.buildTableAnnotation(ojClass, umlClass.getMappingInfo().getPersistentName().getAsIs(), this.config, schema);
//			if (umlClass instanceof INakedEntity) {
//				OJAnnotationAttributeValue uniqueConstraints = buildUniqueConstraintAnnotations((INakedEntity) umlClass);
//				if (uniqueConstraints.hasValues()) {
//					table.addAttribute(uniqueConstraints);
//				}
//			}
//
//			if (umlClass.getSubClasses().size() > 0) {
//				annotateInheritanceType(ojClass, c.hasStereotype(SINGLE_TABLE_INHERITANCE));
//			}
//			if (umlClass.getCodeGenerationStrategy().isAbstractSupertypeOnly() || umlClass.getCodeGenerationStrategy().isAbstractLibraryOnly()) {
//				OJAnnotationValue mappedSuperclass = new OJAnnotationValue(new OJPathName("javax.persistence.MappedSuperclass"));
//				ojClass.addAnnotationIfNew(mappedSuperclass);
//			} else {
//				JpaUtil.addEntity(ojClass);
//			}
//			if (umlClass.getGeneralizations().isEmpty()) {
//				String tableName = umlClass.getMappingInfo().getPersistentName().getAsIs();
//				JpaUtil.addAndAnnotatedIdAndVersion(ojClass, tableName);
//			} else {
//				OJAnnotationValue discriminatorValue = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorValue"), umlClass.getMappingInfo()
//						.getPersistentName().getAsIs());
//				ojClass.addAnnotationIfNew(discriminatorValue);
//
//				if (isInSingleTableInheritance(c)) {
//					buildSecondaryTableAnnotation(umlClass, ojClass, schema);
//				}
//			}
		}
	}

	private void addSubClasses() {
		for (INakedClassifier c : subClasses) {
			HibernateConfiguration hibernateConfiguration = (HibernateConfiguration)findHbmElementFor(this.hbmWorkspace, c.getSupertype().getMappingInfo().getQualifiedUmlName()+"::hibernateConfiguration");
			
			SubClass subClass = new SubClass(hibernateConfiguration);
			subClass.set_abstract(c.getIsAbstract());
			subClass.setQualifiedName(c.getMappingInfo().getQualifiedUmlName());
			subClass.setEntityName(c.getMappingInfo().getQualifiedJavaName());
			subClass.setName(c.getMappingInfo().getQualifiedJavaName());
			subClass.set_extends(c.getSupertype().getMappingInfo().getQualifiedJavaName());
			subClass.setDiscriminatorValue(c.getMappingInfo().getJavaName().getCapped().toString());
			
			Join join = new Join(subClass);
			join.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Join");
			join.setTable(c.getMappingInfo().getPersistentName().toString());
			join.setFetch(Fetch.SELECT);
			join.setSchema(getSchema(c));
			
			Key key = new Key(join);
			key.setColumn("id");
			key.setQualifiedName(c.getMappingInfo().getQualifiedUmlName()+"::Join::Key");
		}
	}

	private String getSchema(INakedClassifier c) {
		String schema = getTag(c, "Schema", "name");
		if (schema == null || schema.isEmpty()) {
			schema = getTag(c.getNameSpace(), "Schema", "name");
			if (schema!=null && !schema.isEmpty()) {
				return schema;
			} else {
				return config.getDefaultSchema();
			}
		}
		return null;
	}

//	private void buildSecondaryTableAnnotation(INakedComplexStructure umlClass, OJAnnotatedClass ojClass, String schema) {
//		OJAnnotationValue secondaryTable = new OJAnnotationValue(new OJPathName("javax.persistence.SecondaryTable"));
//		secondaryTable.addAttribute(new OJAnnotationAttributeValue("name", "`" + umlClass.getMappingInfo().getPersistentName().getAsIs() + "`"));
//		if (StringUtils.isEmpty(schema)) {
//			secondaryTable.addAttribute(new OJAnnotationAttributeValue("schema", "`" + this.config.getDefaultSchema() + "`"));
//		} else {
//			secondaryTable.addAttribute(new OJAnnotationAttributeValue("schema", "`" + schema + "`"));
//		}
//		ojClass.addAnnotationIfNew(secondaryTable);
//	}
//
//	/**
//	 * Includes all appropriately qualified relationships and one-to-one
//	 * relationships
//	 * 
//	 * @param entity
//	 * @return
//	 */
//	private OJAnnotationAttributeValue buildUniqueConstraintAnnotations(INakedEntity entity) {
//		OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
//		for (INakedProperty p : entity.getUniquenessConstraints()) {
//			OJAnnotationValue uniquenessConstraint = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
//			OJAnnotationAttributeValue columns = new OJAnnotationAttributeValue("columnNames", p.getMappingInfo().getPersistentName().getAsIs());
//			for (INakedProperty q : p.getOtherEnd().getQualifiers()) {
//				columns.addStringValue(q.getMappingInfo().getPersistentName().getAsIs());
//			}
//			uniquenessConstraint.addAttribute(columns);
//			uniqueConstraints.addAnnotationValue(uniquenessConstraint);
//		}
//		return uniqueConstraints;
//	}
//
//	private void annotateInheritanceType(OJAnnotatedClass owner, boolean singleTable) {
//		OJAnnotationValue inheritance = new OJAnnotationValue(new OJPathName("javax.persistence.Inheritance"));
//		OJAnnotationAttributeValue inheritanceType = new OJAnnotationAttributeValue("strategy");
//		if (singleTable) {
//			inheritanceType.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.InheritanceType"), "SINGLE_TABLE"));
//		} else {
//			inheritanceType.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.InheritanceType"), "JOINED"));
//		}
//		inheritance.addAttribute(inheritanceType);
//		owner.addAnnotationIfNew(inheritance);
//		OJAnnotationValue discriminator = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorColumn"));
//		OJAnnotationAttributeValue discriminatorType = new OJAnnotationAttributeValue("discriminatorType");
//		discriminatorType.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.DiscriminatorType"), "STRING"));
//		discriminator.addAttribute(discriminatorType);
//		discriminator.addAttribute(new OJAnnotationAttributeValue("name", "type_descriminator"));
//		owner.addAnnotationIfNew(discriminator);
//	}

//	@VisitAfter(matchSubclasses = true)
//	public void annotateProperty(INakedProperty f) {
//		INakedClassifier owner = f.getOwner();		
//		if (isPersistent(owner) && hasOJClass(owner) && (owner.hasStereotype(SINGLE_TABLE_INHERITANCE) || isInSingleTableInheritance(owner))) {
//			if (!f.isDerived()) {
//				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
//				if (map.isOne()) {
//					mapXToOne(f, map);
//				} else {
//					mapXToMany(f, map);
//				}
//			}
//		}
//	}

//	private void buildSecondaryTableColumn(OJAnnotatedField f, INakedProperty p) {
//		boolean addColumnAnnotation = true;
//		Set<OJAnnotationValue> annotations = f.getAnnotations();
//		for (OJAnnotationValue ojAnnotationValue : annotations) {
//			if (ojAnnotationValue.getType().toJavaString().equals("javax.persistence.JoinColumn")) {
////				ojAnnotationValue
////						.addAttribute(new OJAnnotationAttributeValue("table", "`" + p.getOwner().getMappingInfo().getPersistentName().getAsIs() + "`"));
//				addColumnAnnotation = false;
//				break;
//			}
//		}
//		if (addColumnAnnotation) {
//			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
//			column.addAttribute(new OJAnnotationAttributeValue("table", "`" + p.getOwner().getMappingInfo().getPersistentName().getAsIs() + "`"));
//			f.addAnnotationIfNew(column);
//		}
//	}
//
	private void mapXToOne(INakedProperty f, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findJavaClass(f.getOwner());
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		if (f.getBaseType() instanceof INakedEnumeration) {
			throw new RuntimeException("Not yet implemented!");
//			mapXToOneEnumeration(f, owner, field);
		} else if (f.getBaseType() instanceof INakedSimpleType) {
			mapXToOneSimpleType(f, owner, field);
		} else if (isPersistent(f.getNakedBaseType())) {
//			mapXToOnePersistentType(f, owner, field);
		}
	}

	private void mapXToOnePersistentType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		// Entities and behaviors
		// Inverse is always OneToOne
		String toOneType = f.isInverse() ? "javax.persistence.OneToOne" : "javax.persistence.ManyToOne";
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName(toOneType));
		JpaUtil.fetchLazy(toOne);
		if (f.getBaseType() instanceof INakedStructuredDataType || f.isComposite()) {
			// Compositional semantics - should also delete Orphan
			JpaUtil.cascadeAll(toOne);
		}
		// TODO with oneToOne components map a relationship
		// table.
		if (f.isInverse()) {
			// Implies navigable other end and INakedProperty
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((f).getOtherEnd());
			toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.umlName()));
		} else {
			// Remember that oneToOne uniqueness will be added as a
			// uniqueConstraint
			OJAnnotationValue column = JpaUtil.addJoinColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), !f.isRequired());
			if (isOtherEndOrdered(f)) {
				// Emulate "inverse" behavior
				column.putAttribute(new OJAnnotationAttributeValue("insertable", false));
				column.putAttribute(new OJAnnotationAttributeValue("updatable", false));
			}
		}
		field.addAnnotationIfNew(toOne);
	}

	private void mapXToOneSimpleType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		AbstractClass abstractClass = (AbstractClass)this.hbmWorkspace.findOwnedElement(f.getOwner().getMappingInfo().getQualifiedUmlName());
		if (this.workspace.getMappedTypes().getDateType() != null && f.getBaseType().conformsTo(this.workspace.getMappedTypes().getDateType())) {
			
//			Property property = new Property(abstractClass);
//			property.setName(name)
			
			
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "DATE"));
			field.addAnnotationIfNew(temporal);
		} else if (this.workspace.getMappedTypes().getDateType() != null && f.getBaseType().conformsTo(this.workspace.getMappedTypes().getDateType())) {
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
			field.addAnnotationIfNew(temporal);

		}
	}



	private boolean isOtherEndOrdered(INakedProperty f) {
		return f instanceof INakedProperty && (f).getOtherEnd() != null && (f).getOtherEnd().isOrdered();
	}


}
