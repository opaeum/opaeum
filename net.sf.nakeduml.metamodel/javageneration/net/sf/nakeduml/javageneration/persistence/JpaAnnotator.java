package net.sf.nakeduml.javageneration.persistence;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
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
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

public class JpaAnnotator extends AbstractJpaAnnotator {
	public static boolean DEVELOPMENT_MODE = true;

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier cl) {
		if (isPersistent(cl) && OJUtil.hasOJClass(cl)) {
			INakedComplexStructure complexType = (INakedComplexStructure) cl;
			annotateComplexStructure(complexType);
		}
	}

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

	private void annotateComplexStructure(INakedComplexStructure complexType) {
		OJAnnotatedClass ojClass = findJavaClass(complexType);
		buildToString(ojClass, complexType);
		addAllInstances(complexType, ojClass);
		addEquals(ojClass);
		String schema = complexType.getTaggedValue("Schema", "name");
		if (schema == null || schema.isEmpty()) {
			schema = complexType.getNameSpace().getTaggedValue("Schema", "name");
		}
		OJAnnotationValue table = JpaUtil.buildTableAnnotation(ojClass, complexType.getMappingInfo().getPersistentName().getAsIs(),
				this.config, schema);
		if (complexType instanceof INakedEntity) {
			OJAnnotationAttributeValue uniqueConstraints = buildUniqueConstraintAnnotations((INakedEntity) complexType);
			if (uniqueConstraints.hasValues()) {
				table.putAttribute(uniqueConstraints);
				JpaUtil.addNamedQueryForUniquenessConstraints(ojClass, (INakedEntity) complexType);
			}
		}
		if (complexType.getSubClasses().size() > 0) {
			annotateInheritanceType(ojClass);
		}
		if (complexType.getCodeGenerationStrategy().isAbstractSupertypeOnly()
				|| complexType.getCodeGenerationStrategy().isAbstractLibraryOnly()) {
			OJAnnotationValue mappedSuperclass = new OJAnnotationValue(new OJPathName("javax.persistence.MappedSuperclass"));
			ojClass.addAnnotationIfNew(mappedSuperclass);
		} else {
			JpaUtil.addEntity(ojClass);
		}
		if (complexType.getGeneralizations().isEmpty()) {
			String tableName = complexType.getMappingInfo().getPersistentName().getAsIs();
			JpaUtil.addAndAnnotatedIdAndVersion(ojClass, tableName);
		} else {
			OJAnnotationValue discriminatorValue = new OJAnnotationValue(new OJPathName("javax.persistence.DiscriminatorValue"),
					complexType.getMappingInfo().getPersistentName().getAsIs());
			ojClass.addAnnotationIfNew(discriminatorValue);
		}
	}

	public void addAllInstances(INakedComplexStructure complexType, OJAnnotatedClass ojClass) {
		// TODO move elsewhere where dependency on Seam has been confirmed
		OJAnnotatedOperation allInstances = new OJAnnotatedOperation("allInstances");
		ojClass.addToOperations(allInstances);
		allInstances.setStatic(true);
		allInstances.getBody().addToStatements("EntityManager em =(EntityManager)org.jboss.seam.Component.getInstance(\"entityManager\")");
		allInstances.getBody()
				.addToStatements("return new HashSet(em.createQuery(\"from " + complexType.getName() + "\").getResultList())");
		ojClass.addToImports(new OJPathName("javax.persistence.EntityManager"));
		ojClass.addToImports(new OJPathName("java.util.HashSet"));
		OJPathName set = new OJPathName("java.util.Set");
		ojClass.addToImports(set.getDeepCopy());
		set.addToElementTypes(new OJPathName("? extends " + ojClass.getPathName().getLast()));
		allInstances.setReturnType(set);
	}

	/**
	 * Includes all appropriately qualified relationships and one-to-one
	 * relationships
	 * 
	 * @param entity
	 * @return
	 */
	private OJAnnotationAttributeValue buildUniqueConstraintAnnotations(INakedEntity entity) {
		OJAnnotationAttributeValue uniqueConstraints = new OJAnnotationAttributeValue("uniqueConstraints");
		for (INakedProperty p : entity.getUniquenessConstraints()) {
			OJAnnotationValue uniquenessConstraint = new OJAnnotationValue(new OJPathName("javax.persistence.UniqueConstraint"));
			OJAnnotationAttributeValue columns = new OJAnnotationAttributeValue("columnNames", p.getMappingInfo().getPersistentName()
					.getAsIs());
			for (INakedProperty q : p.getOtherEnd().getQualifiers()) {
				columns.addStringValue(q.getMappingInfo().getPersistentName().getAsIs());
			}
			uniquenessConstraint.putAttribute(columns);
			uniqueConstraints.addAnnotationValue(uniquenessConstraint);
		}
		return uniqueConstraints;
	}

	private void annotateInheritanceType(OJAnnotatedClass owner) {
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

	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedClassifier entity) {
		if (super.isPersistent(entity) && OJUtil.hasOJClass(entity)) {
			for (INakedProperty p : entity.getEffectiveAttributes()) {
				if (p.getOwner() instanceof INakedInterface || p.getOwner() == entity) {
					if (p.getAssociation() instanceof INakedAssociationClass) {
						annotateProperty(entity, OJUtil.buildAssociationClassMap(p, getOclEngine().getOclLibrary()));
					} else {
						annotateProperty(entity, OJUtil.buildStructuralFeatureMap(p));
					}
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
	public void visitObjectNode(INakedOutputPin node) {
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
		if (node.getActivity().isPersistent() && BehaviorUtil.mustBeStoredOnActivity(node)) {
			if (node.isTask() || node.isProcessCall()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
				annotateProperty(node.getActivity(), map);
			} else if (BehaviorUtil.hasExecutionInstance(node.getCalledElement())) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary());
				OJAnnotatedField field = (OJAnnotatedField) findJavaClass(node.getActivity()).findField(map.umlName());
				field.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			}
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

	private void annotateProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		if (!map.getProperty().isDerived()) {
			if (map.isOne()) {
				mapXToOne(umlOwner, map);
			} else {
				mapXToMany(umlOwner, map);
			}
		}
	}

	private void mapXToMany(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		OJAnnotationAttributeValue lazy = new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName(
				"javax.persistence.FetchType"), "LAZY"));
		if (p.getNakedBaseType() instanceof INakedEnumeration) {
			// Not supported
		} else if (p.getNakedBaseType() instanceof INakedSimpleType) {
			// Not supported
		} else if (isPersistent(p.getNakedBaseType())) {
			// Entities and behaviors, emulated entities
			OJAnnotationValue toMany = null;
			OJPathName baseTypePath = OJUtil.classifierPathname(p.getNakedBaseType());
			OJAnnotationAttributeValue targetEntity = new OJAnnotationAttributeValue("targetEntity", baseTypePath);
			if (p.isInverse() || p.getAssociation() instanceof INakedAssociationClass) {
				// Can only be bidirectional - implies the presence of
				// non-inverse other
				// end
				toMany = new OJAnnotationValue(new OJPathName("javax.persistence." + (map.isOneToMany() ? "OneToMany" : "ManyToMany")));
				if (p.isOrdered() && map.isOneToMany()) {
					// Map as non-inverse to allow hibernate to update index -
					// NB!!! this feature requires hibernate
					String keyToParentTable = JpaUtil.calculateKeyToOwnerTable(p);
					boolean fkNullable = !(p).getOtherEnd().isRequired();
					JpaUtil.addJoinColumn(field, keyToParentTable, fkNullable);
				} else {
					String otherEndName = null;
					if (p.getOtherEnd() != null) {
						otherEndName = new NakedStructuralFeatureMap(((INakedProperty) p).getOtherEnd()).umlName();
					}
					toMany.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherEndName));
				}
			} else {
				toMany = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToMany"));
				JpaUtil.addJoinTable(umlOwner, p, map, field);
			}
			toMany.putAttribute(lazy);
			toMany.putAttribute(targetEntity);
			if (p.isComposite() || p.getAssociation() instanceof INakedAssociationClass) {
				JpaUtil.cascadeAll(toMany);
			}
			field.addAnnotationIfNew(toMany);
		} else {
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}

	private void buildToString(OJAnnotatedClass owner, INakedClassifier umlClass) {
		OJOperation toString = owner.findToString();
		toString.setBody(new OJBlock());
		toString.setReturnType(new OJPathName("String"));
		toString.setName("toString");
		OJAnnotatedField sb = new OJAnnotatedField();
		sb.setName("sb");
		sb.setType(new OJPathName("StringBuilder"));
		sb.setInitExp("new StringBuilder()");
		toString.getBody().addToLocals(sb);
		List<? extends INakedProperty> features = umlClass.getEffectiveAttributes();
		for (INakedProperty f : features) {
			if (!f.isDerived()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
				if (map.isOne() && !f.isInverse()) {
					if (isPersistent(f.getNakedBaseType())) {
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"" + map.umlName() + "=null\")");
						ifNull.setElsePart(new OJBlock());
						ifNull.getElsePart().addToStatements("sb.append(\"" + map.umlName() + ".id=\")");
						OJSimpleStatement b = new OJSimpleStatement("sb.append(" + map.getter() + "().getId())");
						ifNull.getElsePart().addToStatements(b);
						ifNull.getElsePart().addToStatements("sb.append(\";\")");
						toString.getBody().addToStatements(ifNull);
					} else {
						toString.getBody().addToStatements("sb.append(\"" + map.umlName() + "=\")");
						toString.getBody().addToStatements("sb.append(" + map.getter() + "())");
						toString.getBody().addToStatements("sb.append(\";\")");
					}
				}
			}
		}
		toString.getBody().addToStatements("return sb.toString()");
		owner.addToOperations(toString);
	}

	// TODO move elsewhere??
	public static void addEquals(OJClass ojClass) {
		OJOperation equals = OJUtil.findOperation(ojClass, "equals");
		if (equals == null) {
			equals = new OJAnnotatedOperation();
			ojClass.addToOperations(equals);
		}
		equals.setName("equals");
		equals.addParam("o", new OJPathName("Object"));
		equals.setBody(new OJBlock());
		equals.setReturnType(new OJPathName("boolean"));
		OJIfStatement ifThis = new OJIfStatement("this==o", "return true");
		OJIfStatement ifNotInstance = new OJIfStatement("!(o instanceof " + ojClass.getName() + ")", "return false");
		ifThis.addToElsePart(ifNotInstance);
		OJAnnotatedField other = new OJAnnotatedField();
		other.setName("other");
		other.setType(ojClass.getPathName());
		other.setInitExp("(" + ojClass.getName() + ")o");
		ifNotInstance.setElsePart(new OJBlock());
		ifNotInstance.getElsePart().addToLocals(other);
		OJIfStatement ifIdNull = new OJIfStatement("getId()==null", "return false");
		ifNotInstance.addToElsePart(ifIdNull);
		ifIdNull.addToElsePart("return getId().equals(other.getId())");
		equals.getBody().addToStatements(ifThis);
	}
}
