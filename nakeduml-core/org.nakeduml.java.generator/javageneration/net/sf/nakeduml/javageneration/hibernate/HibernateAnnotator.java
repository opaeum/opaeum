package net.sf.nakeduml.javageneration.hibernate;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.persistence.JpaAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
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

public class HibernateAnnotator extends AbstractHibernateGenerator {
	@VisitAfter()
	public void visitOperation(INakedOperation o) {
		if (o.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(o)) {
			annotateComplexStructure(new OperationMessageStructureImpl(o));
		}
	}

	@VisitAfter()
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		if (oa.isTask()) {
			OpaqueActionMessageStructureImpl msg = new OpaqueActionMessageStructureImpl(oa);
			annotateComplexStructure(msg);
			for (INakedPin p : oa.getPins()) {
				annotateProperty(msg, OJUtil.buildStructuralFeatureMap(msg, p, false));
			}
		}
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class })
	public void visitComplexType(INakedComplexStructure entity) {
		if (super.isPersistent(entity) && OJUtil.hasOJClass(entity)) {
			for (INakedProperty p : entity.getEffectiveAttributes()) {
				if (p.getOwner() instanceof INakedInterface || p.getOwner() == entity) {
					annotateProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
			if (entity instanceof INakedAssociationClass) {
				INakedAssociationClass ac = (INakedAssociationClass) entity;
				mapXToOne(ac, new NakedStructuralFeatureMap(ac.getEnd1()));
				mapXToOne(ac, new NakedStructuralFeatureMap(ac.getEnd2()));
			}
		}
	}

	@VisitBefore(matchSubclasses = true, match = { INakedOutputPin.class })
	public void visitOutputPin(INakedOutputPin node) {
		if (node.getActivity().isPersistent() && BehaviorUtil.mustBeStoredOnActivity(node)) {
			annotateProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitVariable(INakedActivityVariable var) {
		if (var.getActivity().isPersistent() && BehaviorUtil.mustBeStoredOnActivity(var)) {
			annotateProperty(var.getActivity(), OJUtil.buildStructuralFeatureMap(var.getActivity(), var));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitExpansionNode(INakedExpansionNode node) {
		if (node.getActivity().isPersistent() && BehaviorUtil.mustBeStoredOnActivity(node)) {
			annotateProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node) {
		if (BehaviorUtil.mustBeStoredOnActivity(node) && node.getActivity().isPersistent() && (node.isTask() || node.isProcessCall())) {
			annotateProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitParameter(INakedParameter p) {
		if (p.getOwnerElement() instanceof INakedBehavior) {
			INakedBehavior sm = (INakedBehavior) p.getOwnerElement();
			if (sm.isPersistent() && sm.getSpecification() == null) {
				annotateProperty(sm, OJUtil.buildStructuralFeatureMap(sm, p));
			}
		} else if (p.getOwnerElement() instanceof INakedOperation && ((INakedOperation) p.getOwnerElement()).shouldEmulateClass()) {
			OperationMessageStructureImpl o = new OperationMessageStructureImpl((INakedOperation) p.getOwnerElement());
			annotateProperty(o, OJUtil.buildStructuralFeatureMap(o, p));
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier cl) {
		if (OJUtil.hasOJClass(cl)) {
			if (isPersistent(cl)) {
				annotateComplexStructure((INakedComplexStructure) cl);
			} else if (cl instanceof INakedEnumeration) {
				// TODO define enum typeDEF at package level, implement EnumType
				// in
				// hibernate that uses persistent name
			} else if (cl instanceof INakedInterface) {
				if (!cl.hasStereotype(StereotypeNames.HELPER)  && OJUtil.hasOJClass(cl)) {
					OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
					owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
				}
			}
		}
	}

	private void annotateComplexStructure(INakedComplexStructure complexType) {
		OJAnnotatedClass owner = findJavaClass(complexType);
		addAllInstances(complexType, owner);
		OJAnnotationValue table = owner.findAnnotation(new OJPathName("javax.persistence.Table"));
		if (table != null && table.hasAttribute("uniqueConstraints")) {
			OJAnnotationAttributeValue attr = table.findAttribute("uniqueConstraints");
			for (OJAnnotationValue v : attr.getAnnotationValues()) {
				v.findAttribute("columnNames").addStringValue("deleted_on");
			}
		}
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AccessType"), "field"));
		List g = complexType.getGeneralizations();
		if (!isInSingleTableInheritance(complexType)) {
			OJAnnotatedField deletedOn = OJUtil.addProperty(owner, "deletedOn", new OJPathName(Date.class.getName()), true);
			deletedOn.setComment("Initialise to 1000 from 1970");
			deletedOn.setInitExp("Stdlib.FUTURE");
			owner.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
			OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			column.putAttribute(new OJAnnotationAttributeValue("name", "deleted_on"));
			deletedOn.addAnnotationIfNew(column);
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(
					new OJPathName("javax.persistence.TemporalType"), "TIMESTAMP"));
			deletedOn.addAnnotationIfNew(temporal);
			if (g != null && g.size() > 0) {
				OJOperation o = OJUtil.findOperation(owner, "setDeletedOn");
				o.getBody().addToStatements("super.setDeletedOn(deletedOn)");
			}
			OJOperation markDeleted = OJUtil.findOperation(owner, "markDeleted");
			if (markDeleted != null) {
				markDeleted.getBody().addToStatements("setDeletedOn(new Date())");
			}
		} else {
			owner.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
			owner.addToImports("java.util.Date");
		}
		enableHibernateProxy(complexType, owner);
		owner.addToImplementedInterfaces(new OJPathName(HibernateEntity.class.getName()));
	}

	private void annotateProperty(INakedClassifier owner, NakedStructuralFeatureMap map) {
		INakedProperty f = map.getProperty();
		if (isPersistent(owner) && !f.isDerived() && !(f.getAssociation() instanceof INakedAssociationClass)) {
			if (map.isOne()) {
				mapXToOne(owner, map);
			} else {
				OJAnnotatedClass ojOwner = findJavaClass(owner);
				OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
				OJEnumValue TRUE = new OJEnumValue(new OJPathName("org.hibernate.annotations.LazyCollectionOption"), "TRUE");
				OJAnnotationValue lazyCollection = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.LazyCollection"), TRUE);
				field.addAnnotationIfNew(lazyCollection);
				if (f.isOrdered()) {
					implementListSemantics(f, map, field);
				} else if (map.isManyToMany()) {
					implementCollectionId(field);
				}
				if (f.getNakedBaseType() instanceof INakedEnumeration || f.getNakedBaseType() instanceof INakedSimpleType) {
					implementManyForValueTypes(f, map, field);
				} else if (isPersistent(f.getNakedBaseType())) {
					HibernateUtil.applyFilter(field, HibernateUtil.getHibernateDialect(this.config));
				} else {
					// owner.addAnnotation(field, new OJAnnotation(new
					// OJPathName("javax.persistence.Transient")));
				}
				if (f.getNakedBaseType() instanceof INakedInterface && !f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
					HibernateUtil.addManyToAny(owner,field, map,config);
					if (f.isComposite()) {
						HibernateUtil.addCascade(field, CascadeType.ALL);
					}
					field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
				}
			}
		}
	}

	private void mapXToOne(INakedClassifier owner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass ojOwner = findJavaClass(owner);
		OJAnnotatedField field = (OJAnnotatedField) ojOwner.findField(map.umlName());
		INakedProperty f = map.getProperty();
		if (f.getNakedBaseType() instanceof INakedEnumeration) {
			// TODO use custom type that uses the sqlName
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			// TODO use strategies
		} else if (f.getNakedBaseType() instanceof INakedInterface && !f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			HibernateUtil.addAny(field, map);
			if (f.isComposite()) {
				HibernateUtil.addCascade(field, CascadeType.ALL);
				field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
			}
		} else if (isPersistent(f.getNakedBaseType())) {
		}
		// TODO parameterize development mode
		if (f.isRequired() && !f.isInverse() && !JpaAnnotator.DEVELOPMENT_MODE) {
			if (f.getNakedBaseType().conformsTo(workspace.getMappedTypes().getStringType())) {
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotEmpty")));
			} else {
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.hibernate.validator.NotNull")));
			}
		}
		OJPathName indexPathName = new OJPathName("org.hibernate.annotations.Index");
		if(f.getOtherEnd()!=null && f.getOtherEnd().isNavigable() && map.isManyToOne() && field.findAnnotation(indexPathName)==null){
			OJAnnotationValue index = new OJAnnotationValue(indexPathName);
			index.putAttribute("name", "idx_"+ owner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName());
			index.putAttribute("columnNames",f.getMappingInfo().getPersistentName().getAsIs());
			field.putAnnotation(index);
		}
		setDeletedOn(map, ojOwner);
	}

	private void implementListSemantics(INakedProperty f, NakedStructuralFeatureMap map, OJAnnotatedField field) {
		OJAnnotationValue index = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.IndexColumn"));
		INakedClassifier umlOwner = f.getOwner();
		String columnName = null;
		if (map.isManyToMany()) {
			// simple column name - no requirement for uniqueness
			columnName = "idx_in_" + f.getMappingInfo().getPersistentName().getWithoutId();
		} else {
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

	private void implementManyForValueTypes(INakedProperty f, NakedStructuralFeatureMap map, OJAnnotatedField field) {
		OJAnnotationValue collectionOfElements = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionOfElements"));
		OJAnnotationAttributeValue targetElement = new OJAnnotationAttributeValue("targetElement", OJUtil.classifierPathname(f
				.getNakedBaseType()));
		collectionOfElements.putAttribute(targetElement);
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
				"javax.persistence.FetchType"), "LAZY"));
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
	public void visitModel(INakedModel p) {
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


	private void enableHibernateProxy(INakedComplexStructure entity, OJAnnotatedClass owner) {
		if (entity.getSubClasses().size() > 0) {
			OJAnnotationValue proxy = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Proxy"));
			proxy.putAttribute(new OJAnnotationAttributeValue("lazy", Boolean.FALSE));
			owner.putAnnotation(proxy);
		}
	}

	private final void implementCollectionId(OJAnnotatedField field) {
		if (field.getType().getLast().indexOf("Collection") >= 0) {
			// Only applies to Bag semantics
			OJAnnotationValue collectionId = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.CollectionId"));
			OJAnnotationValue columns = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
			columns.putAttribute(new OJAnnotationAttributeValue("name", "id"));
			collectionId.putAttribute(new OJAnnotationAttributeValue("columns", columns));
			OJAnnotationValue type = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Type"));
			type.putAttribute(new OJAnnotationAttributeValue("type", "long"));
			collectionId.putAttribute(new OJAnnotationAttributeValue("type", type));
			collectionId.putAttribute(new OJAnnotationAttributeValue("generator", "id_generator"));
			field.addAnnotationIfNew(collectionId);
		}
	}

	protected final String shortenName(String withoutId, int i) {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(withoutId, "_");
		// Name gets way too long.
		// TODO specify and use max columnNameSize
		int maxLength = (i / st.countTokens()) - 1;// one for the u nderscore
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.length() >= maxLength) {
				sb.append(token.substring(0, maxLength));
			} else {
				sb.append(token);
			}
			if (st.hasMoreTokens()) {
				sb.append("_");
			}
		}
		return sb.toString();
	}

	private void setDeletedOn(NakedStructuralFeatureMap map, OJAnnotatedClass ojOwner) {
		if (map.getFeature() instanceof INakedProperty) {
			INakedProperty p = (INakedProperty) map.getFeature();
			if (p.getOtherEnd() != null && p.getOtherEnd().isComposite()) {
				OJOperation setter = OJUtil.findOperation(ojOwner, map.setter());
				for (OJStatement s : setter.getBody().getStatements()) {
					if (s instanceof OJIfStatement && AttributeImplementor.IF_PARAM_NOT_NULL.equals(s.getName())) {
						OJIfStatement ifParamNotNull = (OJIfStatement) s;
						ifParamNotNull.getThenPart().addToStatements("setDeletedOn(Stdlib.FUTURE)");
						ifParamNotNull.setElsePart(new OJBlock());
						ifParamNotNull.getElsePart().addToStatements("setDeletedOn(new Date())");
					}
				}
			}
		}
	}
	private void addAllInstances(INakedComplexStructure complexType, OJAnnotatedClass ojClass) {
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
		OJIfStatement ifMocked=new OJIfStatement("mockedAllInstances==null");
		allInstances.getBody().addToStatements(ifMocked);
		ifMocked.getThenPart().addToStatements("Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class)");
		ifMocked.getThenPart()
				.addToStatements("return new HashSet(session.createQuery(\"from " + complexType.getName() + "\").list())");
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
