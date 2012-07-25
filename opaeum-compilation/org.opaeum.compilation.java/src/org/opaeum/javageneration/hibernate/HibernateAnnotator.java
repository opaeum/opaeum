package org.opaeum.javageneration.hibernate;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;
import org.hibernate.annotations.CascadeType;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.UtilCreator;
import org.opaeum.javageneration.persistence.JpaAnnotator;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.environment.Environment;

@StepDependency(phase = JavaTransformationPhase.class,requires = {JpaAnnotator.class,UtilCreator.class},after = {JpaAnnotator.class,
		UtilCreator.class,CompositionNodeImplementor.class/*
																											 * Dependendent on the markDelete method being created
																											 */
},before = {})
public class HibernateAnnotator extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitEnumeration(Enumeration e){
		// TODO do something similar for interfaces, even without
		if(EmfClassifierUtil.getCodeGenerationStrategy( e)==CodeGenerationStrategy.ALL){
			OJPackage pkg = findOrCreatePackage(OJUtil.packagePathname(e.getNamespace()));
			OJAnnotatedClass clss = (OJAnnotatedClass) pkg.findClass(new OJPathName(e.getName() + "Class"));
			for(Property p:e.getOwnedAttributes()){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if(map.isOne()){
					mapXToOne(e, map, clss);
				}
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitInterface(Interface cl){
		if(!StereotypesHelper.hasStereotype(cl, StereotypeNames.HELPER) && OJUtil.hasOJClass(cl)){
			OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
			owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitComplexStructure(Classifier complexType){
		if(OJUtil.hasOJClass(complexType) && isPersistent(complexType)){
			OJAnnotatedClass owner = findJavaClass(complexType);
			addAllInstances(complexType, owner);
			if(EmfClassifierUtil.isCompositionParticipant(complexType )){
				Property endToComposite = getLibrary().getEndToComposite(complexType);
				if(endToComposite != null && (endToComposite.getOwner() == complexType || endToComposite.getOwner() instanceof Interface)){
					setDeletedOn(OJUtil.buildStructuralFeatureMap(endToComposite), owner);
				}
			}
			OJAnnotationValue table = owner.findAnnotation(new OJPathName("javax.persistence.Table"));
			OJAnnotationValue entity = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Class"));
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
			List<Classifier> g = complexType.getGenerals();
			if(!isInSingleTableInheritance(complexType)){
				OJAnnotatedField deletedOn = OJUtil.addPersistentProperty(owner, "deletedOn", new OJPathName(Date.class.getName()), true);
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
					OJOperation o = owner.getUniqueOperation("setDeletedOn");
					o.getBody().addToStatements("super.setDeletedOn(deletedOn)");
				}
				OJOperation markDeleted = owner.getUniqueOperation("markDeleted");
				if(markDeleted != null){
					markDeleted.getBody().addToStatements("setDeletedOn(new Date())");
				}
			}else{
				owner.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
				owner.addToImports("java.util.Date");
			}
			if(complexType instanceof Association){
				OJOperation clear = owner.findOperation("clear", Collections.emptyList());
				clear.getBody().addToStatements("markDeleted()");
			}
			enableHibernateProxy(complexType, owner);
			owner.addToImplementedInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	protected void visitProperty(Classifier owner,NakedStructuralFeatureMap map){
		Property f = map.getProperty();
		if(isPersistent(owner) && !f.isDerived() && !map.isStatic()){
			if(map.isOne()){
				mapXToOne(owner, map);
			}else{
				OJAnnotatedClass ojOwner = findJavaClass(owner);
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
				OJEnumValue TRUE = new OJEnumValue(new OJPathName("org.hibernate.annotations.LazyCollectionOption"), "TRUE");
				OJAnnotationValue lazyCollection = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.LazyCollection"), TRUE);
				field.addAnnotationIfNew(lazyCollection);
				if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getType() instanceof Interface){
					OJAnnotationValue oneToMany = field.findAnnotation(new OJPathName(OneToMany.class.getName()));
					oneToMany.removeAttribute("mappedBy");
					JpaUtil.addJoinColumn(field, PersistentNameUtil.getPersistentName( f).getAsIs(), true);
					OJAnnotationValue where = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Where"));
					where.putAttribute("clause",
							PersistentNameUtil.getPersistentName(f.getOtherEnd()) + "_type=" + "'"
									+ (config.shouldBeCm1Compatible() ? ojOwner.getPathName().toString() : EmfWorkspace.getId( owner) + "'"));
					field.addAnnotationIfNew(where);
				}
				if(f.isOrdered()){
					implementListSemantics(map, field);
				}else if(map.isManyToMany()){
					implementCollectionId(field);
				}
				if(f.getType() instanceof Enumeration){
					if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
						HibernateUtil.addEnumResolverAsCustomType(field, OJUtil.classifierPathname(f.getType()));
					}
				}else if(isPersistent(f.getType())){
					HibernateUtil.applyFilter(field, this.config.getDbDialect());
				}else{
					// owner.addAnnotation(field, new OJAnnotation(new
					// OJPathName("javax.persistence.Transient")));
				}
				if(f.getType() instanceof Enumeration || EmfClassifierUtil.isSimpleType(f.getType())){
					OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionOfElements"));
					OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetElement",
							OJUtil.classifierPathname((Classifier) f.getType()));
					collectionOfElements.putAttribute(targetElement);
					OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
							"javax.persistence.FetchType"), "LAZY"));
					collectionOfElements.putAttribute(lazy);
					field.addAnnotationIfNew(collectionOfElements);
				}
				if(f.getType() instanceof Interface && !EmfClassifierUtil.isHelper(f.getType())){
					HibernateUtil.addManyToAny(owner, field, map, config);
					if(f.isComposite()){
						HibernateUtil.addCascade(field, CascadeType.ALL);
					}
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}
		}
	}
	private void mapXToOne(Classifier owner,NakedStructuralFeatureMap map){
		OJAnnotatedClass ojOwner = findJavaClass(owner);
		mapXToOne(owner, map, ojOwner);
	}
	public void mapXToOne(Classifier owner,NakedStructuralFeatureMap map,OJAnnotatedClass ojOwner){
		OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
		if(field != null){
			// may have been removed by custom transformation
			Property f = map.getProperty();
			if(f.getType() instanceof Enumeration){
				if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
					HibernateUtil.addEnumResolverAsCustomType(field, OJUtil.classifierPathname(f.getType()));
				}
			}else if(EmfClassifierUtil.isSimpleType(f.getType())){
				// TODO use strategies
			}else if(f.getType() instanceof Interface && !EmfClassifierUtil.isHelper(f.getType())){
				// if(config.shouldBeCm1Compatible()){
				// HibernateUtil.addAny(field, map);
				// }else{
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Embedded.class.getName())));
				OJAnnotationValue overrides = new OJAnnotationValue(new OJPathName(AttributeOverrides.class.getName()));
				OJAnnotationValue identifier = new OJAnnotationValue(new OJPathName(AttributeOverride.class.getName()));
				identifier.putAttribute("name", "identifier");
				overrides.addAnnotationValue(identifier);
				OJAnnotationValue identifierColumn = new OJAnnotationValue(new OJPathName(Column.class.getName()));
				identifier.putAttribute("column", identifierColumn);
				identifierColumn.putAttribute("name", PersistentNameUtil.getPersistentName( map.getProperty()).getAsIs());
				field.addAnnotationIfNew(overrides);
				OJAnnotationValue classIdentifier = new OJAnnotationValue(new OJPathName(AttributeOverride.class.getName()));
				classIdentifier.putAttribute("name", "classIdentifier");
				OJAnnotationValue classIdentifierColumn = new OJAnnotationValue(new OJPathName(Column.class.getName()));
				classIdentifier.putAttribute("column", classIdentifierColumn);
				classIdentifierColumn.putAttribute("name", PersistentNameUtil.getPersistentName(map.getProperty()).getAsIs() + "_type");
				overrides.addAnnotationValue(classIdentifier);
				// }
				if(f.isComposite()){
					HibernateUtil.addCascade(field, CascadeType.ALL);
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}else if(isPersistent(f.getType())){
			}
			// TODO parameterize development mode
			if(EmfPropertyUtil.isRequired(f) && !EmfPropertyUtil.isInverse(f) && !JpaAnnotator.DEVELOPMENT_MODE){
				if(f.getType().conformsTo(workspace.getOpaeumLibrary().getStringType())){
					field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotEmpty")));
				}else{
					field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotNull")));
				}
			}
			if(map.isOneToOne() && f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getType() instanceof Interface){
				if(config.shouldBeCm1Compatible() || true){// TODO
					// NB! for CM both sides need to be non-inverse
					JpaUtil.addJoinColumn(field, PersistentNameUtil .getPersistentName(f).getAsIs(), true);
					OJAnnotationValue oneToOne = field.findAnnotation(new OJPathName("javax.persistence.OneToOne"));
					if(oneToOne == null){
						field.findAnnotation(new OJPathName("javax.persistence.ManyToOne")).removeAttribute("mappedBy");
					}else{
						oneToOne.removeAttribute("mappedBy");
					}
				}else{
					// TODO this code only works on *ToMany
					OJAnnotationValue oneToMany = field.findAnnotation(new OJPathName(OneToMany.class.getName()));
					oneToMany.removeAttribute("mappedBy");
					JpaUtil.addJoinColumn(field, PersistentNameUtil.getPersistentName(f).getAsIs(), true);
					OJAnnotationValue where = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Where"));
					where.putAttribute("clause",
							PersistentNameUtil.getPersistentName(f.getOtherEnd()) + "_type=" + "'"
									+ (config.shouldBeCm1Compatible() ? ojOwner.getPathName().toString() : EmfWorkspace.getId(owner) + "'"));
					field.addAnnotationIfNew(where);
				}
			}
			if(!(f.getType() instanceof Interface)){
				// TODO address this by adding an extra field to the entity
				OJPathName indexPathName = new OJPathName("org.hibernate.annotations.Index");
				if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && map.isOne() && !EmfPropertyUtil.isInverse(f)
						&& field.findAnnotation(indexPathName) == null){
					OJAnnotationValue index = new OJAnnotationValue(indexPathName);
					index.putAttribute("name", "idx_" + PersistentNameUtil.getPersistentName( owner) + "_" + PersistentNameUtil.getPersistentName(f));
					index.putAttribute("columnNames", PersistentNameUtil.getPersistentName(f).getAsIs());
					field.putAnnotation(index);
				}
			}
		}
	}
	private void implementListSemantics(NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJAnnotationValue index = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.IndexColumn"));
		index.putAttribute(new OJAnnotationAttributeValue("name", JpaUtil.generateIndexColumnName(map, "idx")));
		field.addAnnotationIfNew(index);
		// TODO add index in base_table ??? maybe not necessary
		// OJAnnotatedClass ojType=findJavaClass(f.getType());
		// ojType.addAnnotation(>???);
	}
	@VisitBefore
	public void visitModel(Model p){
		OJClass stdLib = UtilityCreator.getUtilPack().findClass(new OJPathName("Stdlib"));
		OJAnnotatedField future = new OJAnnotatedField("FUTURE", new OJPathName("java.sql.Timestamp"));
		future.setStatic(true);
		future.setFinal(true);
		future.setVisibility(OJVisibilityKindGEN.PUBLIC);
		future.setInitExp("new Timestamp(1000L*60*60*24*365*1000)");
		stdLib.addToFields(future);
	}
	private void enableHibernateProxy(Classifier entity,OJAnnotatedClass owner){
		if(EmfClassifierUtil.getSubClasses(entity).size() > 0){
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
	private void setDeletedOn(NakedStructuralFeatureMap map,OJAnnotatedClass ojOwner){
		if(map.getFeature() instanceof Property){
			Property p = (Property) map.getFeature();
			if(!p.isDerived() && p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
				OJOperation setter = ojOwner.getUniqueOperation(map.setter());
				for(OJStatement s:setter.getBody().getStatements()){
					if(s instanceof OJIfStatement && AttributeImplementor.IF_PARAM_NOT_NULL.equals(s.getName())){
						OJIfStatement ifParamNotNull = (OJIfStatement) s;
						ifParamNotNull.getThenPart().addToStatements("setDeletedOn(Stdlib.FUTURE)");
						ifParamNotNull.setElsePart(new OJBlock());
						OJOperation markDeleted = ojOwner.getUniqueOperation("markDeleted");
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
	private void addAllInstances(Classifier complexType,OJAnnotatedClass ojClass){
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
		ifMocked.getThenPart().addToStatements(
				"CmtPersistence session =" + Environment.class.getName() + ".getInstance().getComponent(CmtPersistence.class)");
		ifMocked.getThenPart().addToStatements("return new HashSet(session.readAll(" + ojClass.getPathName() + ".class))");
		ojClass.addToImports(new OJPathName("org.opaeum.runtime.persistence.CmtPersistence"));
		ifMocked.setElsePart(new OJBlock());
		ifMocked.getElsePart().addToStatements("return mockedAllInstances");
		ojClass.addToImports(new OJPathName("java.util.HashSet"));
		OJPathName setExtends = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		setExtends.addToElementTypes(new OJPathName("? extends " + ojClass.getPathName().getLast()));
		allInstances.setReturnType(setExtends);
	}
}