package net.sf.nakeduml.javageneration.hibernate;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractStructureVisitor;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.JpaAnnotator;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.InverseCalculator;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.hibernate.annotations.CascadeType;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;
import org.nakeduml.java.metamodel.generated.OJVisibilityKindGEN;
import org.nakeduml.runtime.domain.HibernateEntity;

@StepDependency(phase = JavaTransformationPhase.class, requires = { PersistenceStep.class, InverseCalculator.class,
	PersistentNameGenerator.class }, after = { OclExpressionExecution.class, PersistenceStep.class }, before = {})
public class HibernateAnnotator extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true,match = {
		INakedAssociationClass.class
	})
	public void visitAssociationClass(INakedAssociationClass entity){
		if(super.isPersistent(entity) && OJUtil.hasOJClass(entity)){
			mapXToOne(entity, new NakedStructuralFeatureMap(entity.getEnd1()));
			mapXToOne(entity, new NakedStructuralFeatureMap(entity.getEnd2()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitInterface(INakedInterface cl){
		if(!cl.hasStereotype(StereotypeNames.HELPER) && OJUtil.hasOJClass(cl)){
			OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
			owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitComplexStructure(INakedComplexStructure complexType){
		if(OJUtil.hasOJClass(complexType)){
			OJAnnotatedClass owner = findJavaClass(complexType);
			addAllInstances(complexType, owner);
			OJAnnotationValue table = owner.findAnnotation(new OJPathName("javax.persistence.Table"));
			OJAnnotationValue entity = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Entity"));
			entity.setImportType(false);
			entity.putAttribute("dynamicUpdate", true);
			owner.putAnnotation(entity);
			if(table != null && table.hasAttribute("uniqueConstraints")){
				OJAnnotationAttributeValue attr = table.findAttribute("uniqueConstraints");
				for(OJAnnotationValue v:attr.getAnnotationValues()){
					v.findAttribute("columnNames").addStringValue("deleted_on");
				}
			}
			owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AccessType"), "field"));
			List g = complexType.getGeneralizations();
			if(!isInSingleTableInheritance(complexType)){
				OJAnnotatedField deletedOn = OJUtil.addProperty(owner, "deletedOn", new OJPathName(Date.class.getName()), true);
				deletedOn.setComment("Initialise to 1000 from 1970");
				deletedOn.setInitExp("Stdlib.FUTURE");
				owner.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
				OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
				column.putAttribute(new OJAnnotationAttributeValue("name", "deleted_on"));
				deletedOn.addAnnotationIfNew(column);
				OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
						"javax.persistence.TemporalType"), "TIMESTAMP"));
				deletedOn.addAnnotationIfNew(temporal);
				if(g != null && g.size() > 0){
					OJOperation o = OJUtil.findOperation(owner, "setDeletedOn");
					o.getBody().addToStatements("super.setDeletedOn(deletedOn)");
				}
				OJOperation markDeleted = OJUtil.findOperation(owner, "markDeleted");
				if(markDeleted != null){
					markDeleted.getBody().addToStatements("setDeletedOn(new Date())");
				}
			}else{
				owner.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
				owner.addToImports("java.util.Date");
			}
			enableHibernateProxy(complexType, owner);
			owner.addToImplementedInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		INakedProperty f = map.getProperty();
		if(isPersistent(owner) && !f.isDerived() && !(f.getAssociation() instanceof INakedAssociationClass)){
			if(map.isOne()){
				mapXToOne(owner, map);
			}else{
				OJAnnotatedClass ojOwner = findJavaClass(owner);
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				OJEnumValue TRUE = new OJEnumValue(new OJPathName("org.hibernate.annotations.LazyCollectionOption"), "TRUE");
				OJAnnotationValue lazyCollection = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.LazyCollection"), TRUE);
				field.addAnnotationIfNew(lazyCollection);
				if(f.isOrdered()){
					implementListSemantics(f, map, field);
				}else if(map.isManyToMany()){
					implementCollectionId(field);
				}
				if(f.getNakedBaseType() instanceof INakedEnumeration){
					HibernateUtil.addEnumResolverAsCustomType(field, new OJPathName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName()));
					implementManyForValueTypes(f, map, field);
				}else if(f.getNakedBaseType() instanceof INakedSimpleType){
					implementManyForValueTypes(f, map, field);
				}else if(isPersistent(f.getNakedBaseType())){
					HibernateUtil.applyFilter(field, HibernateUtil.getHibernateDialect(this.config));
				}else{
					// owner.addAnnotation(field, new OJAnnotation(new
					// OJPathName("javax.persistence.Transient")));
				}
				if(f.getNakedBaseType() instanceof INakedInterface && !f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
					HibernateUtil.addManyToAny(owner, field, map, config);
					if(f.isComposite()){
						HibernateUtil.addCascade(field, CascadeType.ALL);
					}
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}
		}
	}
	private void mapXToOne(INakedClassifier owner,NakedStructuralFeatureMap map){
		OJAnnotatedClass ojOwner = findJavaClass(owner);
		OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
		INakedProperty f = map.getProperty();
		if(f.getNakedBaseType() instanceof INakedEnumeration){
			HibernateUtil.addEnumResolverAsCustomType(field, new OJPathName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName()));
		}else if(f.getNakedBaseType() instanceof INakedSimpleType){
			// TODO use strategies
		}else if(f.getNakedBaseType() instanceof INakedInterface && !f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
			HibernateUtil.addAny(field, map);
			if(f.isComposite()){
				HibernateUtil.addCascade(field, CascadeType.ALL);
				field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
			}
		}else if(isPersistent(f.getNakedBaseType())){
		}
		// TODO parameterize development mode
		if(f.isRequired() && !f.isInverse() && !JpaAnnotator.DEVELOPMENT_MODE){
			if(f.getNakedBaseType().conformsTo(workspace.getMappedTypes().getStringType())){
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotEmpty")));
			}else{
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotNull")));
			}
		}
		OJPathName indexPathName = new OJPathName("org.hibernate.annotations.Index");
		if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && map.isManyToOne() && field.findAnnotation(indexPathName) == null){
			OJAnnotationValue index = new OJAnnotationValue(indexPathName);
			index.putAttribute("name", "idx_" + owner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName());
			index.putAttribute("columnNames", f.getMappingInfo().getPersistentName().getAsIs());
			field.putAnnotation(index);
		}
		setDeletedOn(map, ojOwner);
	}
	private void implementListSemantics(INakedProperty f,NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJAnnotationValue index = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.IndexColumn"));
		INakedClassifier umlOwner = f.getOwner();
		String columnName = null;
		if(map.isManyToMany()){
			// simple column name - no requirement for uniqueness
			columnName = "idx_in_" + f.getMappingInfo().getPersistentName().getWithoutId();
		}else{
			columnName = "idx_in_";
			String withoutId = f.getMappingInfo().getPersistentName().getWithoutId().getAsIs();
			// complex column name - has to be unique across all usages of the
			// entity
			columnName += shortenName(withoutId, 8);
			columnName += "_on_";
			columnName += shortenName(umlOwner.getMappingInfo().getPersistentName().getAsIs(), 8);
		}
		index.putAttribute(new OJAnnotationAttributeValue("name", columnName));
		field.addAnnotationIfNew(index);
		// TODO add index in base_table ??? maybe not necessary
		// OJAnnotatedClass ojType=findJavaClass(f.getBaseType());
		// ojType.addAnnotation(>???);
	}
	private void implementManyForValueTypes(INakedProperty f,NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("javax.persistence.ElementCollection"));
		OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetElement", OJUtil.classifierPathname(f.getNakedBaseType()));
		collectionOfElements.putAttribute(targetElement);
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
		collectionOfElements.putAttribute(lazy);
		field.addAnnotationIfNew(collectionOfElements);
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
	@VisitBefore
	public void visitModel(INakedModel p){
		OJClass stdLib = UtilityCreator.getUtilPack().findClass(new OJPathName("Stdlib"));
		OJAnnotatedField future = new OJAnnotatedField();
		future.setName("FUTURE");
		future.setStatic(true);
		future.setFinal(true);
		future.setVisibility(OJVisibilityKindGEN.PUBLIC);
		future.setType(new OJPathName("java.sql.Timestamp"));
		future.setInitExp("new Timestamp(1000L*60*60*24*365*1000)");
		stdLib.addToFields(future);
	}
	private void enableHibernateProxy(INakedComplexStructure entity,OJAnnotatedClass owner){
		if(entity.getSubClasses().size() > 0){
			OJAnnotationValue proxy = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Proxy"));
			proxy.putAttribute(new OJAnnotationAttributeValue("lazy", Boolean.FALSE));
			owner.putAnnotation(proxy);
		}
	}
	private final void implementCollectionId(OJAnnotatedField field){
		if(field.getType().getLast().indexOf("Collection") >= 0){
			// Only applies to Bag semantics
			OJAnnotationValue collectionId = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionId"));
			OJAnnotationValue columns = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			columns.putAttribute(new OJAnnotationAttributeValue("name", "id"));
			collectionId.putAttribute(new OJAnnotationAttributeValue("columns", columns));
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Type"));
			type.putAttribute(new OJAnnotationAttributeValue("type", "long"));
			collectionId.putAttribute(new OJAnnotationAttributeValue("type", type));
			collectionId.putAttribute(new OJAnnotationAttributeValue("generator", "sequence"));
			field.addAnnotationIfNew(collectionId);
		}
	}
	protected final String shortenName(String withoutId,int i){
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(withoutId, "_");
		// Name gets way too long.
		// TODO specify and use max columnNameSize
		int maxLength = (i / st.countTokens()) - 1;// one for the u nderscore
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			if(token.length() >= maxLength){
				sb.append(token.substring(0, maxLength));
			}else{
				sb.append(token);
			}
			if(st.hasMoreTokens()){
				sb.append("_");
			}
		}
		return sb.toString();
	}
	private void setDeletedOn(NakedStructuralFeatureMap map,OJAnnotatedClass ojOwner){
		if(map.getFeature() instanceof INakedProperty){
			INakedProperty p = (INakedProperty) map.getFeature();
			if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
				OJOperation setter = OJUtil.findOperation(ojOwner, map.setter());
				for(OJStatement s:setter.getBody().getStatements()){
					if(s instanceof OJIfStatement && AttributeImplementor.IF_PARAM_NOT_NULL.equals(s.getName())){
						OJIfStatement ifParamNotNull = (OJIfStatement) s;
						ifParamNotNull.getThenPart().addToStatements("setDeletedOn(Stdlib.FUTURE)");
						ifParamNotNull.setElsePart(new OJBlock());
						ifParamNotNull.getElsePart().addToStatements("setDeletedOn(new Date())");
					}
				}
			}
		}
	}
	private void addAllInstances(INakedComplexStructure complexType,OJAnnotatedClass ojClass){
		OJPathName set = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		set.addToElementTypes(ojClass.getPathName());
		OJAnnotatedField mockInstances = new OJAnnotatedField("mockedAllInstances", set);
		mockInstances.setStatic(true);
		ojClass.addToFields(mockInstances);
		OJAnnotatedOperation mockAllInstances = new OJAnnotatedOperation("mockAllInstances");
		ojClass.addToOperations(mockAllInstances);
		mockAllInstances.addParam("newMocks", set);
		mockAllInstances.setStatic(true);
		mockAllInstances.getBody().addToStatements("mockedAllInstances=newMocks");
		// TODO move elsewhere where dependency on Seam has been confirmed
		OJAnnotatedOperation allInstances = new OJAnnotatedOperation("allInstances");
		ojClass.addToOperations(allInstances);
		allInstances.setStatic(true);
		OJIfStatement ifMocked = new OJIfStatement("mockedAllInstances==null");
		allInstances.getBody().addToStatements(ifMocked);
		ifMocked.getThenPart().addToStatements("Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class)");
		ifMocked.getThenPart().addToStatements("return new HashSet(session.createQuery(\"from " + complexType.getName() + "\").list())");
		ifMocked.setElsePart(new OJBlock());
		ifMocked.getElsePart().addToStatements("return mockedAllInstances");
		ojClass.addToImports(new OJPathName("org.hibernate.Session"));
		ojClass.addToImports(new OJPathName("java.util.HashSet"));
		OJPathName setExtends = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		setExtends.addToElementTypes(new OJPathName("? extends " + ojClass.getPathName().getLast()));
		allInstances.setReturnType(setExtends);
	}
}