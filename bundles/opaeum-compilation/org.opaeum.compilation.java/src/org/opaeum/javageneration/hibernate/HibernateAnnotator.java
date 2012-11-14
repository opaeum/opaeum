package org.opaeum.javageneration.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
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
import org.opaeum.javageneration.oclexpressions.UtilCreator;
import org.opaeum.javageneration.persistence.JpaAnnotator;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.persistence.AbstractPersistence;

@StepDependency(phase = JavaTransformationPhase.class,requires = {JpaAnnotator.class,UtilCreator.class},after = {JpaAnnotator.class,
		UtilCreator.class,CompositionNodeImplementor.class/*
																											 * Dependendent on the markDelete method being created
																											 */
},before = {})
public class HibernateAnnotator extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitEnumeration(Enumeration e){
		// TODO do something similar for interfaces, even without
		if(ojUtil.getCodeGenerationStrategy(e) == CodeGenerationStrategy.ALL){
			OJPackage pkg = findOrCreatePackage(ojUtil.packagePathname(e.getNamespace()));
			OJAnnotatedClass clss = (OJAnnotatedClass) pkg.findClass(new OJPathName(e.getName() + "Entity"));
			for(Property p:e.getOwnedAttributes()){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
				if(map.isOne()){
					mapXToOne(e, map, clss);
				}
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitInterface(Interface cl){
		if(!StereotypesHelper.hasStereotype(cl, StereotypeNames.HELPER) && ojUtil.hasOJClass(cl)){
			OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
			owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
		}
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass owner,Classifier complexType){
		if(isPersistent(complexType)){
			addAllInstances(complexType, owner);
			if(EmfClassifierUtil.isCompositionParticipant(complexType)){
				Property endToComposite = getLibrary().getEndToComposite(complexType);
				if(endToComposite != null
						&& (EmfPropertyUtil.getOwningClassifier(endToComposite) == complexType || EmfPropertyUtil.getOwningClassifier(endToComposite) instanceof Interface)){
					setDeletedOn(ojUtil.buildStructuralFeatureMap(endToComposite), owner);
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
					OJAnnotationAttributeValue columnNames = v.findAttribute("columnNames");
					columnNames.addStringValue("deleted_on");
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
				OJOperation clear = owner.getUniqueOperation("clear");
				clear.getBody().addToStatements("markDeleted()");
			}
			enableHibernateProxy(complexType, owner);
			owner.addToImplementedInterfaces(new OJPathName(HibernateEntity.class.getName()));
			return true;
		}
		return false;
	}
	@Override
	protected void visitProperty(OJAnnotatedClass ojOwner,Classifier owner,PropertyMap map){
		Property f = map.getProperty();
		if(isPersistent(owner) && !EmfPropertyUtil.isDerived(f) && !map.isStatic()){
			if(map.isOne()){
				mapXToOne(owner, map, ojOwner);
			}else{
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
				OJEnumValue TRUE = new OJEnumValue(new OJPathName("org.hibernate.annotations.LazyCollectionOption"), "TRUE");
				OJAnnotationValue lazyCollection = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.LazyCollection"), TRUE);
				field.addAnnotationIfNew(lazyCollection);
				if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getType() instanceof Interface){
					OJAnnotationValue oneToMany = field.findAnnotation(new OJPathName(OneToMany.class.getName()));
					oneToMany.removeAttribute("mappedBy");
					NameWrapper name = PersistentNameUtil.getPersistentName(f.getOtherEnd());
					if(config.shouldBeCm1Compatible()){
						name = name.getWithoutId();
					}
					JpaUtil.addJoinColumn(field, name.getAsIs(), true);
					OJAnnotationValue where = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Where"));
					where.putAttribute("clause",
							name + "_type='" + (config.shouldBeCm1Compatible() ? ojOwner.getPathName().toString() : EmfWorkspace.getId(owner)) + "'");
					field.addAnnotationIfNew(where);
				}
				if(f.isOrdered()){
					implementListSemantics(map, field);
				}else if(map.isManyToMany()){
					implementCollectionId(field);
				}
				if(map.getBaseType() instanceof Enumeration){
					if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
						HibernateUtil.addEnumResolverAsCustomType(field, ojUtil.classifierPathname(map.getBaseType()));
					}
				}else if(isPersistent(map.getBaseType())){
					HibernateUtil.applyFilter(field, this.config.getDbDialect());
				}else{
					// owner.addAnnotation(field, new OJAnnotation(new
					// OJPathName("javax.persistence.Transient")));
				}
				if(map.getBaseType() instanceof Enumeration || EmfClassifierUtil.isSimpleType(map.getBaseType())){
					OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionOfElements"));
					OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetElement",
							ojUtil.classifierPathname((Classifier) map.getBaseType()));
					collectionOfElements.putAttribute(targetElement);
					OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
							"javax.persistence.FetchType"), "LAZY"));
					collectionOfElements.putAttribute(lazy);
					field.addAnnotationIfNew(collectionOfElements);
				}
				if(map.getBaseType() instanceof Interface && !EmfClassifierUtil.isHelper(map.getBaseType())){
					HibernateUtil.addManyToAny(owner, field, map, config);
					if(f.isComposite()){
						HibernateUtil.addCascade(field, CascadeType.ALL);
					}
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}
		}
	}
	public void mapXToOne(Classifier owner,PropertyMap map,OJAnnotatedClass ojOwner){
		OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.fieldname());
		if(field != null){
			// may have been removed by custom transformation
			Property f = map.getProperty();
			if(map.getBaseType() instanceof Enumeration){
				if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
					HibernateUtil.addEnumResolverAsCustomType(field, ojUtil.classifierPathname(map.getBaseType()));
				}
			}else if(EmfClassifierUtil.isSimpleType(map.getBaseType())){
				// TODO use strategies
			}else if(map.getBaseType() instanceof Interface && !EmfClassifierUtil.isHelper(map.getBaseType())){
				NameWrapper persistentName = PersistentNameUtil.getPersistentName(map.getProperty());
				HibernateUtil.overrideInterfaceValueAtributes(field, persistentName);
				if(f.isComposite()){
					HibernateUtil.addCascade(field, CascadeType.ALL);
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}else if(isPersistent(map.getBaseType())){
			}
			// TODO parameterize development mode
			if(EmfPropertyUtil.isRequired(f) && !EmfPropertyUtil.isInverse(f) && !JpaAnnotator.DEVELOPMENT_MODE){
				if(map.getBaseType().conformsTo(workspace.getOpaeumLibrary().getStringType())){
					field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotEmpty")));
				}else{
					field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotNull")));
				}
			}
			if(map.isOneToOne() && f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && f.getOtherEnd().getType() instanceof Interface){
				if(config.shouldBeCm1Compatible() || true){// TODO
					// NB! for CM both sides need to be non-inverse
					JpaUtil.addJoinColumn(field, PersistentNameUtil.getPersistentName(f).getAsIs(), true);
					OJAnnotationValue oneToOne = field.findAnnotation(new OJPathName("javax.persistence.OneToOne"));
					if(oneToOne == null){
						OJAnnotationValue manyToOne = field.findAnnotation(new OJPathName("javax.persistence.ManyToOne"));
						manyToOne.removeAttribute("mappedBy");
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
			if(!(map.getBaseType() instanceof Interface)){
				// TODO address this by adding an extra field to the entity
				OJPathName indexPathName = new OJPathName("org.hibernate.annotations.Index");
				if(f.getOtherEnd() != null && f.getOtherEnd().isNavigable() && map.isOne() && !EmfPropertyUtil.isInverse(f)
						&& field.findAnnotation(indexPathName) == null){
					OJAnnotationValue index = new OJAnnotationValue(indexPathName);
					index.putAttribute("name", "idx_" + PersistentNameUtil.getPersistentName(owner) + "_" + PersistentNameUtil.getPersistentName(f));
					index.putAttribute("columnNames", PersistentNameUtil.getPersistentName(f).getAsIs());
					field.putAnnotation(index);
				}
			}
		}
	}
	private void implementListSemantics(PropertyMap map,OJAnnotatedField field){
		OJAnnotationValue index = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.IndexColumn"));
		index.putAttribute(new OJAnnotationAttributeValue("name", JpaUtil.generateIndexColumnName(map, "idx")));
		field.addAnnotationIfNew(index);
		// TODO add index in base_table ??? maybe not necessary
		// OJAnnotatedClass ojType=findJavaClass(map.getBaseType());
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
	private void setDeletedOn(PropertyMap map,OJAnnotatedClass ojOwner){
		Property p = (Property) map.getProperty();
		if(!EmfPropertyUtil.isDerived(p) && p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
			OJOperation setter = ojOwner.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
			OJIfStatement st = (OJIfStatement) setter.getBody().findStatementRecursive(AttributeImplementor.IF_PARAM_NOT_NULL);
			if(st == null){
			}else{
				st.getThenPart().addToStatements("setDeletedOn(Stdlib.FUTURE)");
				st.setElsePart(new OJBlock());
				OJOperation markDeleted = ojOwner.getUniqueOperation("markDeleted");
				if(markDeleted != null){
					st.getElsePart().addToStatements("markDeleted()");
				}else{
					st.getElsePart().addToStatements("setDeletedOn(new Date())");
				}
			}
		}
	}
	private void addAllInstances(Classifier complexType,OJAnnotatedClass ojClass){
		OJPathName set = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		set.addToElementTypes(new OJPathName("? extends " + ojClass.getName()));
		OJAnnotatedField mockInstances = new OJAnnotatedField("mockedAllInstances", set);
		mockInstances.setStatic(true);
		ojClass.addToFields(mockInstances);
		OJAnnotatedOperation mockAllInstances = new OJAnnotatedOperation("mockAllInstances");
		ojClass.addToOperations(mockAllInstances);
		mockAllInstances.addParam("newMocks", new OJPathName("java.util.Set"));
		mockAllInstances.setStatic(true);
		mockAllInstances.getBody().addToStatements("mockedAllInstances=newMocks");
		OJAnnotatedOperation allInstances = new OJAnnotatedOperation("allInstances");
		allInstances.addParam("persistence", new OJPathName(AbstractPersistence.class.getName()));
		ojClass.addToOperations(allInstances);
		allInstances.setStatic(true);
		OJIfStatement ifMocked = new OJIfStatement("mockedAllInstances==null");
		allInstances.getBody().addToStatements(ifMocked);
		ifMocked.getThenPart().addToStatements("return new HashSet(persistence.readAll(" + ojClass.getPathName() + ".class))");
		ifMocked.setElsePart(new OJBlock());
		ifMocked.getElsePart().addToStatements("return mockedAllInstances");
		ojClass.addToImports(new OJPathName("java.util.HashSet"));
		OJPathName setExtends = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		setExtends.addToElementTypes(new OJPathName("? extends " + ojClass.getPathName().getLast()));
		allInstances.setReturnType(setExtends);
	}
}