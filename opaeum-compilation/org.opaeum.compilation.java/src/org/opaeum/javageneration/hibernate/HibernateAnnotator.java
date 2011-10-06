package org.opeum.javageneration.hibernate;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;

import org.hibernate.annotations.CascadeType;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.java.metamodel.annotation.OJEnumValue;
import org.opeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opeum.javageneration.basicjava.AttributeImplementor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.UtilCreator;
import org.opeum.javageneration.persistence.JpaAnnotator;
import org.opeum.javageneration.persistence.JpaUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.InverseCalculator;
import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.core.internal.StereotypeNames;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.environment.Environment;
import org.opeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		InverseCalculator.class,PersistentNameGenerator.class,JpaAnnotator.class,UtilCreator.class
},after = {
		JpaAnnotator.class,UtilCreator.class
},before = {})
public class HibernateAnnotator extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitInterface(INakedInterface cl){
		if(!cl.hasStereotype(StereotypeNames.HELPER) && OJUtil.hasOJClass(cl)){
			OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
			owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitComplexStructure(INakedComplexStructure complexType){
		if(OJUtil.hasOJClass(complexType) && isPersistent(complexType)){
			OJAnnotatedClass owner = findJavaClass(complexType);
			addAllInstances(complexType, owner);
			if(complexType instanceof ICompositionParticipant){
				INakedProperty endToComposite = ((ICompositionParticipant) complexType).getEndToComposite();
				if(endToComposite != null && (endToComposite.getOwner() == complexType || endToComposite.getOwner() instanceof INakedInterface)){
					setDeletedOn(OJUtil.buildStructuralFeatureMap(endToComposite), owner);
				}
			}
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
			List<IClassifier> g = complexType.getGeneralizations();
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
			if(complexType instanceof INakedAssociation){
				OJOperation clear = owner.findOperation("clear", Collections.emptyList());
				clear.getBody().addToStatements("markDeleted()");
			}
			enableHibernateProxy(complexType, owner);
			owner.addToImplementedInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		INakedProperty f = map.getProperty();
		if(isPersistent(owner) && !f.isDerived() && !map.isStatic()){
			if(map.isOne()){
				mapXToOne(owner, map);
			}else{
				OJAnnotatedClass ojOwner = findJavaClass(owner);
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				OJEnumValue TRUE = new OJEnumValue(new OJPathName("org.hibernate.annotations.LazyCollectionOption"), "TRUE");
				OJAnnotationValue lazyCollection = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.LazyCollection"), TRUE);
				field.addAnnotationIfNew(lazyCollection);
				if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getNakedBaseType() instanceof INakedInterface){
					JpaUtil.addJoinColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), true);
					OJAnnotationValue where = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Where"));
					where.putAttribute("clause", f.getOtherEnd().getMappingInfo().getPersistentName() + "_type="
							+ (config.shouldBeCm1Compatible() ?  "'" + ojOwner.getPathName().toString() +"'" : owner.getMappingInfo().getOpeumId().toString()));
					field.addAnnotationIfNew(where);
				}
				if(f.isOrdered()){
					implementListSemantics(f, map, field);
				}else if(map.isManyToMany()){
					implementCollectionId(field);
				}
				if(f.getNakedBaseType() instanceof INakedEnumeration){
					if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
						HibernateUtil.addEnumResolverAsCustomType(field, new OJPathName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName()));
					}
				}else if(isPersistent(f.getNakedBaseType())){
					HibernateUtil.applyFilter(field, this.config.getDbDialect());
				}else{
					// owner.addAnnotation(field, new OJAnnotation(new
					// OJPathName("javax.persistence.Transient")));
				}
				if(f.getNakedBaseType() instanceof INakedEnumeration || f.getNakedBaseType() instanceof INakedSimpleType){
					OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionOfElements"));
					OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetElement", OJUtil.classifierPathname(f.getNakedBaseType()));
					collectionOfElements.putAttribute(targetElement);
					OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY"));
					collectionOfElements.putAttribute(lazy);
					field.addAnnotationIfNew(collectionOfElements);
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
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				HibernateUtil.addEnumResolverAsCustomType(field, new OJPathName(f.getNakedBaseType().getMappingInfo().getQualifiedJavaName()));
			}
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
			if(f.getNakedBaseType().conformsTo(workspace.getOpeumLibrary().getStringType())){
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotEmpty")));
			}else{
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotNull")));
			}
		}
		if(map.isOneToOne() && f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getNakedBaseType() instanceof INakedInterface){
			// NB! for CM both sides need to be non-inverse
			JpaUtil.addJoinColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), true);
			OJAnnotationValue oneToOne = field.findAnnotation(new OJPathName("javax.persistence.OneToOne"));
			if(oneToOne == null){
				field.findAnnotation(new OJPathName("javax.persistence.ManyToOne")).removeAttribute("mappedBy");
			}else{
				oneToOne.removeAttribute("mappedBy");
			}
		}
		OJPathName indexPathName = new OJPathName("org.hibernate.annotations.Index");
		if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && map.isOne() && !f.isInverse() && field.findAnnotation(indexPathName) == null){
			OJAnnotationValue index = new OJAnnotationValue(indexPathName);
			index.putAttribute("name", "idx_" + owner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName());
			index.putAttribute("columnNames", f.getMappingInfo().getPersistentName().getAsIs());
			field.putAnnotation(index);
		}
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
	@VisitBefore
	public void visitModel(INakedModel p){
		OJClass stdLib = UtilityCreator.getUtilPack().findClass(new OJPathName("Stdlib"));
		OJAnnotatedField future = new OJAnnotatedField("FUTURE", new OJPathName("java.sql.Timestamp"));
		future.setStatic(true);
		future.setFinal(true);
		future.setVisibility(OJVisibilityKindGEN.PUBLIC);
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
			if(!p.isDerived() && p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
				OJOperation setter = OJUtil.findOperation(ojOwner, map.setter());
				for(OJStatement s:setter.getBody().getStatements()){
					if(s instanceof OJIfStatement && AttributeImplementor.IF_PARAM_NOT_NULL.equals(s.getName())){
						OJIfStatement ifParamNotNull = (OJIfStatement) s;
						ifParamNotNull.getThenPart().addToStatements("setDeletedOn(Stdlib.FUTURE)");
						ifParamNotNull.setElsePart(new OJBlock());
						OJOperation markDeleted = OJUtil.findOperation(ojOwner, "markDeleted");
						if(markDeleted != null){
							ifParamNotNull.getElsePart().addToStatements("markDeleted()");
						}else{
							ifParamNotNull.getElsePart().addToStatements("setDeletedOn(new Date())");
						}
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
		ifMocked.getThenPart().addToStatements("Session session =" + Environment.class.getName() + ".getInstance().getComponent(Session.class)");
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