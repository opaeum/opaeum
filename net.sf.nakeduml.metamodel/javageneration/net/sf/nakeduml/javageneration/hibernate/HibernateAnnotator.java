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
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.javametamodel.generated.OJVisibilityKindGEN;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.InterfaceUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
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
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.util.HibernateEntity;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.hibernate.annotations.CascadeType;
import org.hibernate.dialect.Dialect;

public class HibernateAnnotator extends AbstractHibernateGenerator {

	@VisitAfter()
	public void visitOperation(INakedOperation o) {
		if (o.shouldEmulateClass()) {
			annotateComplexStructure(new OperationMessageStructureImpl(o));
		}
	}

	@VisitAfter()
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		OpaqueActionMessageStructureImpl msg = new OpaqueActionMessageStructureImpl(oa);
		annotateComplexStructure(msg);
		for (INakedPin p : oa.getPins()) {
			annotateProperty(msg, OJUtil.buildStructuralFeatureMap(msg, p));
		}
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class })
	public void visitComplexType(INakedComplexStructure entity) {
		if (super.isPersistent(entity) && OJUtil.hasOJClass(entity)) {
			for (INakedProperty p : entity.getEffectiveAttributes()) {
				if (p.getOwner() instanceof INakedInterface || p.getOwner()==entity) {
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

	@VisitBefore(matchSubclasses = true, match = { INakedParameterNode.class, INakedOutputPin.class })
	public void visitObjectNode(INakedObjectNode node) {
		if (BehaviorUtil.hasExecutionInstance(node.getActivity())) {
			annotateProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node) {
		if (node.getTargetElement() != null && BehaviorUtil.hasExecutionInstance(node.getActivity())
				&& (BehaviorUtil.isUserTask(node) || BehaviorUtil.hasExecutionInstance(node.getCalledElement()))) {
			annotateProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitParameter(INakedParameter node) {
		if (node.getOwnerElement() instanceof INakedStateMachine) {
			INakedStateMachine sm = (INakedStateMachine) node.getOwnerElement();
			annotateProperty(sm, OJUtil.buildStructuralFeatureMap(sm, node));
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier cl) {
		if (isPersistent(cl) && OJUtil.hasOJClass(cl)) {
			annotateComplexStructure((INakedComplexStructure) cl);
		} else if (cl instanceof INakedEnumeration) {
			// TODO define enum typeDEF at package level, implement EnumType in
			// hibernate that uses persistent name
		} else if (cl instanceof INakedInterface) {
			if (super.hasEntityImplementationsOnly((INakedInterface) cl)) {
				OJAnnotatedInterface owner = (OJAnnotatedInterface) findJavaClass(cl);
				owner.addToSuperInterfaces(new OJPathName(HibernateEntity.class.getName()));
			}
		}
	}

	private void annotateComplexStructure(INakedComplexStructure complexType) {
		OJAnnotatedClass owner = findJavaClass(complexType);
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
				if (f.getNakedBaseType() instanceof INakedInterface) {
					HibernateUtil.addManyToAny(ojOwner, field, f, map, InterfaceUtil.getImplementationsOf(f.getNakedBaseType()));
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
		INakedProperty f=map.getProperty();
		if (f.getNakedBaseType() instanceof INakedEnumeration) {
			// TODO use custom type that uses the sqlName
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
		} else if (f.getNakedBaseType() instanceof INakedInterface) {
			HibernateUtil.addAny(ojOwner, field, f.getMappingInfo().getPersistentName().toString(),
					InterfaceUtil.getImplementationsOf(f.getNakedBaseType()));
			if (f.isComposite()) {
				HibernateUtil.addCascade(field, CascadeType.ALL);
			}
			field.removeAnnotation(new OJPathName("javax.persistence.Transient"));
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
		OJAnnotatedPackage ap = (OJAnnotatedPackage) javaModel.findPackage(OJUtil.packagePathname(p));
		OJAnnotationValue filterDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.FilterDef"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("defaultCondition", "deleted_on > " + getCurrentTimestampSQLFunction()));
		ap.putAnnotation(filterDef);
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

	private String getCurrentTimestampSQLFunction() {
		Dialect dialect = HibernateUtil.getHibernateDialect(this.config);
		return dialect.getCurrentTimestampSQLFunctionName();
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
}
