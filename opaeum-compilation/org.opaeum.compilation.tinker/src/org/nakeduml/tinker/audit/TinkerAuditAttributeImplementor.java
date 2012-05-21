package org.nakeduml.tinker.audit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerAuditPhase.class, requires = { TinkerAuditSuperTypeGenerator.class, TinkerAuditClassTransformation.class }, after = {
		TinkerAuditSuperTypeGenerator.class, TinkerAuditClassTransformation.class })
public class TinkerAuditAttributeImplementor extends AbstractAuditJavaProducingVisitor {

	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_IF = "buildPolymorphicGetterForToOneIf";
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_TRY = "buildPolymorphicGetterForToOneTry";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_FOR = "buildPolymorphicGetterForToManyFor";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_TRY = "buildPolymorphicGetterForToManyTry";

	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public final static String ATRTIBUTE_STRATEGY_TINKER = "TINKER";

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)) {
				visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner()) && !(p.getOwner() instanceof INakedEnumeration)) {
			visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
		}
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			} else if (p.isDerived()) {
				copyDerivedProperty(umlOwner, map);
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
	}

	private void copyDerivedProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass originalClass = findJavaClass(umlOwner);
		OJAnnotatedClass auditClass = findAuditJavaClass(umlOwner);
		OJOperation getter = originalClass.findOperation(map.getter(), Collections.EMPTY_LIST);
		OJOperation copy = getter.getDeepCopy();
		Set<OJPathName> setToRename = new HashSet<OJPathName>();
		setToRename.add(map.javaBaseTypePath());
		copy.renameAll(setToRename, "Audit");
		auditClass.addToOperations(copy);
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
		buildGetter(owner, map, false);
	}

	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		AuditStructuralFeatureMapWrapper auditMap = new AuditStructuralFeatureMapWrapper(map);
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setName(map.getter());
		getter.setReturnType(auditMap.javaTypePath());
		owner.addToOperations(getter);

		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			INakedProperty prop = map.getProperty();
			owner.addToImports(TinkerGenerationUtil.edgePathName);
			owner.addToImports(TinkerGenerationUtil.vertexPathName);
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne()) {
					buildGetAuditForOne(map, owner);
					buildGetAuditForThisOne(map, owner);
					buildPolymorphicGetterForToOne(map, getter);
				} else if (map.isOneToMany()) {
					buildGetAllAuditsForMany(map, owner);
					buildGetAuditsForThisMany(map, owner);
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isManyToMany()) {
					buildGetAllAuditsForMany(map, owner);
					buildGetAuditsForThisMany(map, owner);
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isOneToOne()) {
					buildGetAuditForOne(map, owner);
					buildGetAuditForThisOne(map, owner);
					buildPolymorphicGetterForToOne(map, getter);
				}
			} else {
				if (!prop.isDerived()) {
					if (prop.getBaseType() instanceof INakedEntity) {
						if (map.isOne()) {
							buildGetAuditForOne(map, owner);
							buildGetAuditForThisOne(map, owner);
							buildPolymorphicGetterForToOne(map, getter);
						} else if (map.isMany()) {
							buildGetAllAuditsForMany(map, owner);
							buildGetAuditsForThisMany(map, owner);
							buildPolymorphicGetterForMany(map, getter);
						} else {
						}
					} else if (prop.getBaseType() instanceof INakedEnumeration) {
						getter.setReturnType(map.javaTypePath());
						if (map.isOne()) {
							TinkerAttributeImplementor.buildGetterForToOneEnumeration(map, getter, prop);
							addResultToGetter(getter);
						} else {
							TinkerAttributeImplementor.buildGetterForManyEnumeration(owner, map, getter, prop);
						}
					} else {
						getter.setReturnType(map.javaTypePath());
						if (map.isOne()) {
							getter.getBody().addToStatements(
									"return (" + map.javaTypePath() + ") this.vertex.getProperty(\""
											+ TinkerGenerationUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
						} else {
							// TinkerAttributeImplementor.buildGetterForToManyEmbbedded(owner,
							// map, getter, prop);
							buildGetAllAuditsForMany(map, owner, true);
							buildGetAuditsForThisMany(map, owner, true);
							buildPolymorphicGetterForMany(map, getter, true);
						}
					}
				}
			}
		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	private void addResultToGetter(OJAnnotatedOperation getter) {
		getter.getBody().getStatements().add(0, new OJSimpleStatement(getter.getReturnType().getLast() + " result = null"));
		getter.getBody().addToStatements("return result");
	}

	private void buildGetAuditForOne(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		AuditStructuralFeatureMapWrapper auditMap = new AuditStructuralFeatureMapWrapper(map);

		OJOperation getAllAuditsForOne = new OJOperation();
		getAllAuditsForOne.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForOne.setName("getAuditFor" + NameConverter.capitalize(map.umlName()));
		getAllAuditsForOne.addParam("previous", owner.getPathName());

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(auditMap.javaBaseTypePath());
		getAllAuditsForOne.addParam("removedAudits", param);

		getAllAuditsForOne.addParam("transactionNo", new OJPathName("java.lang.Long"));
		getAllAuditsForOne.setReturnType(auditMap.javaBaseTypePath());
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart(auditMap.javaBaseTypePath().getLast() + " result = previous.getAuditFor" + NameConverter.capitalize(map.umlName())
				+ "(transactionNo, removedAudits)");
		OJIfStatement ifStatement2 = new OJIfStatement("result != null", "return result");
		ifStatement2.addToElsePart("return getAuditFor" + NameConverter.capitalize(map.umlName()) + "(previous.getPreviousAuditEntry(), removedAudits, transactionNo)");
		ifStatement.addToThenPart(ifStatement2);
		getAllAuditsForOne.getBody().addToStatements(ifStatement);
		getAllAuditsForOne.getBody().addToStatements("return null");
		owner.addToOperations(getAllAuditsForOne);
	}

	private void buildGetAuditForThisOne(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		AuditStructuralFeatureMapWrapper auditMap = new AuditStructuralFeatureMapWrapper(map);

		owner.addToImports(TinkerGenerationUtil.tinkerFormatter);
		owner.addToImports(new OJPathName("java.util.Date"));
		owner.addToImports(new OJPathName("java.util.HashSet"));
		OJOperation getAuditsForThisOne = new OJOperation();
		getAuditsForThisOne.setVisibility(OJVisibilityKind.PRIVATE);
		owner.addToOperations(getAuditsForThisOne);
		getAuditsForThisOne.setName("getAuditFor" + NameConverter.capitalize(map.umlName()));
		getAuditsForThisOne.addParam("transactionNo", new OJPathName("java.lang.Long"));

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(auditMap.javaBaseTypePath());
		getAuditsForThisOne.addParam("removedAudits", param);

		getAuditsForThisOne.setReturnType(auditMap.javaBaseTypePath());
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);

		// boolean isComposite = map.getProperty().isComposite();
		// isComposite = TinkerGenerationUtil.calculateDirection(map,
		// isComposite);
		boolean isComposite = map.getProperty().isInverse();

		String associationName = TinkerGenerationUtil.getEdgeName(map);
		if (isComposite) {
			getAuditsForThisOne.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + associationName + "\")");
		} else {
			getAuditsForThisOne.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + associationName + "\")");
		}
		OJForStatement forEdges = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "iter");
		OJIfStatement ifNotDeleted = new OJIfStatement("edge.getProperty(\"deletedOn\")==null");

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);

		forEdges.getBody().addToStatements(ojTryStatement);
		ojTryStatement.getTryPart().addToStatements(ifNotDeleted);

		OJSimpleStatement forClass = new OJSimpleStatement();
		OJSimpleStatement constructClass = new OJSimpleStatement();
		if (isComposite) {
			forClass.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			constructClass.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
			ifNotDeleted.addToThenPart(forClass);
			ifNotDeleted.addToThenPart(constructClass);
		} else {
			forClass.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			constructClass.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
			ifNotDeleted.addToThenPart(forClass);
			ifNotDeleted.addToThenPart(constructClass);
		}

		OJIfStatement ifRemovedAuditContains = new OJIfStatement("!removedAudits.containsKey(instance.getOriginalUid())");
		ifRemovedAuditContains.addToThenPart("return (" + auditMap.javaBaseTypePath().getLast() + ")iterateToLatest(transactionNo, instance)");
		ifNotDeleted.addToThenPart(ifRemovedAuditContains);

		ifNotDeleted.addToElsePart(forClass);
		ifNotDeleted.addToElsePart(constructClass);
		ifNotDeleted.addToElsePart("removedAudits.put(instance.getOriginalUid(), instance)");

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getAuditsForThisOne.getBody().addToStatements(forEdges);
		getAuditsForThisOne.getBody().addToStatements("return null");

	}

	private void buildGetAllAuditsForMany(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		buildGetAllAuditsForMany(map, owner, false);
	}

	private void buildGetAllAuditsForMany(NakedStructuralFeatureMap map, OJAnnotatedClass owner, boolean embedded) {
		NakedStructuralFeatureMap auditMap;
		if (!embedded) {
			auditMap = new AuditStructuralFeatureMapWrapper(map);
		} else {
			auditMap = map;
		}

		OJOperation getAllAuditsForMany = new OJOperation();
		getAllAuditsForMany.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForMany.setName("getAllAuditsFor" + NameConverter.capitalize(map.umlName()));
		getAllAuditsForMany.addParam("previous", owner.getPathName());

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(auditMap.javaBaseTypePath());

		getAllAuditsForMany.addParam("audits", param);
		getAllAuditsForMany.addParam("removedAudits", param);

		getAllAuditsForMany.addParam("transactionNo", new OJPathName("java.lang.Long"));
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart("audits.putAll(previous.getAuditsFor" + NameConverter.capitalize(map.umlName()) + "(audits, removedAudits, transactionNo))");
		ifStatement.addToThenPart("getAllAuditsFor" + NameConverter.capitalize(map.umlName()) + "(previous.getPreviousAuditEntry(), audits, removedAudits, transactionNo)");
		getAllAuditsForMany.getBody().addToStatements(ifStatement);
		owner.addToOperations(getAllAuditsForMany);
	}

	private void buildGetAuditsForThisMany(NakedStructuralFeatureMap map, OJClass owner) {
		buildGetAuditsForThisMany(map, owner, false);
	}

	private void buildGetAuditsForThisMany(NakedStructuralFeatureMap map, OJClass owner, boolean embedded) {
		NakedStructuralFeatureMap auditMap;
		if (!embedded) {
			auditMap = new AuditStructuralFeatureMapWrapper(map);
		} else {
			auditMap = map;
		}
		owner.addToImports(TinkerGenerationUtil.tinkerFormatter);
		owner.addToImports(new OJPathName("java.util.Date"));
		OJOperation getAuditsForThisMany = new OJOperation();
		getAuditsForThisMany.setVisibility(OJVisibilityKind.PRIVATE);
		owner.addToOperations(getAuditsForThisMany);
		getAuditsForThisMany.setName("getAuditsFor" + NameConverter.capitalize(map.umlName()));
		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(auditMap.javaBaseTypePath());
		getAuditsForThisMany.addParam("audits", param);
		getAuditsForThisMany.addParam("removedAudits", param);
		getAuditsForThisMany.addParam("transactionNo", new OJPathName("java.lang.Long"));
		getAuditsForThisMany.setReturnType(param);
		OJField result = new OJField();
		result.setType(param);
		result.setName("result");
		result.setInitExp("new HashMap<String, " + auditMap.javaBaseTypePath().getLast() + ">()");
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		getAuditsForThisMany.getBody().addToLocals(result);

		INakedClassifier manyClassifier;
		boolean isComposite;
		if (map.getProperty().getOtherEnd() != null) {
			manyClassifier = map.getProperty().getOtherEnd().getOwner();
			isComposite = map.getProperty().isInverse();
		} else {
			manyClassifier = (INakedClassifier) map.getProperty().getBaseType();
			isComposite = true;
		}

		String asociationName = TinkerGenerationUtil.getEdgeName(map);
		if (isComposite) {
			getAuditsForThisMany.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + asociationName + "\")");
		} else {
			getAuditsForThisMany.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + asociationName + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		OJTryStatement ojTryStatement = new OJTryStatement();
		OJIfStatement ifNotDeleted = new OJIfStatement("edge.getProperty(\"deletedOn\")==null");
		forStatement.getBody().addToStatements(ojTryStatement);
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		ojTryStatement.getTryPart().addToStatements(ifNotDeleted);
		OJSimpleStatement classForName = new OJSimpleStatement();
		OJSimpleStatement constructMany = new OJSimpleStatement();
		if (isComposite) {
			if (!embedded) {
				classForName.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
				ifNotDeleted.addToThenPart(classForName);
				constructMany.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast() + ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
			} else {
				constructMany.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast()
						+ ")edge.getInVertex().getProperty(\"value\")");
			}
			ifNotDeleted.addToThenPart(constructMany);
		} else {
			if (!embedded) {
				classForName.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
				ifNotDeleted.addToThenPart(classForName);
				constructMany.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast() + ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
			} else {
				constructMany.setExpression(auditMap.javaBaseTypePath().getLast() + " instance = (" + auditMap.javaBaseTypePath().getLast()
						+ ")edge.getOutVertex().getProperty(\"value\")");
			}
			ifNotDeleted.addToThenPart(constructMany);
		}
		OJIfStatement ifStatement;
		if (!embedded) {
			ifStatement = new OJIfStatement("!removedAudits.containsKey(instance.getOriginalUid()) && !audits.containsKey(instance.getOriginalUid())");
			ifStatement.addToThenPart(auditMap.javaBaseTypePath().getLast() + " previous = (" + auditMap.javaBaseTypePath().getLast() + ")iterateToLatest(transactionNo, instance)");
		} else {
			ifStatement = new OJIfStatement("!removedAudits.containsKey(instance.toString()) && !audits.containsKey(instance.toString())");
		}
		ifNotDeleted.addToThenPart(ifStatement);
		if (!embedded) {
			ifStatement.addToThenPart("result.put(previous.getOriginalUid(), previous)");
		} else {
			ifStatement.addToThenPart("result.put(instance.toString(), instance)");
		}
		ifNotDeleted.addToElsePart(classForName);
		ifNotDeleted.addToElsePart(constructMany);
		if (!embedded) {
			ifNotDeleted.addToElsePart("removedAudits.put(instance.getOriginalUid(), instance)");
		} else {
			ifNotDeleted.addToElsePart("removedAudits.put(instance.toString(), instance)");
		}

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		getAuditsForThisMany.getBody().addToStatements(forStatement);
		getAuditsForThisMany.getBody().addToStatements("return result");
	}

	private void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		AuditStructuralFeatureMapWrapper auditMap = new AuditStructuralFeatureMapWrapper(map);

		getter.getBody().addToStatements(
				"return getAuditFor" + NameConverter.capitalize(map.umlName()) + "(this, new HashMap<String, " + auditMap.javaBaseTypePath().getLast()
						+ ">(), (getNextAuditEntry()!=null?getNextAuditEntry().getTransactionNo():-1))");
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		buildPolymorphicGetterForMany(map, getter, false);
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter, boolean embedded) {
		NakedStructuralFeatureMap auditMap;
		if (!embedded) {
			auditMap = new AuditStructuralFeatureMapWrapper(map);
		} else {
			auditMap = map;
		}

		getter.getOwner().addToImports(new OJPathName("java.util.Map"));
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
		OJField audits = new OJField();
		OJPathName var = new OJPathName("java.util.Map");
		var.addToElementTypes(new OJPathName("String"));
		var.addToElementTypes(auditMap.javaBaseTypePath());
		audits.setInitExp("new HashMap<String, " + auditMap.javaBaseTypePath() + ">()");
		audits.setName("allAudits");
		audits.setType(var);
		getter.getBody().addToLocals(audits);
		getter.getBody().addToStatements(
				"getAllAuditsFor" + NameConverter.capitalize(map.umlName()) + "(this, allAudits, new HashMap<String, " + auditMap.javaBaseTypePath().getLast()
						+ ">(), (getNextAuditEntry()!=null?getNextAuditEntry().getTransactionNo():-1))");
		getter.getBody().addToStatements("return new " + auditMap.javaDefaultTypePath().getLast() + "(allAudits.values())");
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
	}

}
