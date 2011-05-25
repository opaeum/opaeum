package org.nakeduml.tinker.auditing.tinker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.name.NameConverter;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class TinkerAuditAttributeImplementor extends StereotypeAnnotator {

	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_IF = "buildPolymorphicGetterForToOneIf";
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_TRY = "buildPolymorphicGetterForToOneTry";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_FOR = "buildPolymorphicGetterForToManyFor";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_TRY = "buildPolymorphicGetterForToManyTry";

	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public final static String ATRTIBUTE_STRATEGY_TINKER = "TINKER";

	@Override
	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class, INakedAssociationClass.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)) {
				if (p.getAssociation() instanceof INakedAssociationClass) {
					// TODO test this may create duplicates
					// buildAssociationClassLogic(entity,
					// (INakedAssociationClass) p.getAssociation());
				} else {
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner()) && !(p.getOwner() instanceof INakedEnumeration)) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
				// visitProperty(p.getOwner(),
				// OJUtil.buildAssociationClassMap(p,getOclEngine().getOclLibrary()));
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
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
		Map<String, OJPathName> pathMap = new HashMap<String, OJPathName>();
		pathMap.put(map.javaBaseTypePath().toJavaString(), map.javaBaseTypePath());
		copy.renameAll(pathMap, "Audit");
		auditClass.addToOperations(copy);
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
		buildGetter(owner, map, false);
	}

	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaAuditTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			INakedProperty prop = map.getProperty();
			owner.addToImports(TinkerUtil.edgePathName);
			owner.addToImports(TinkerUtil.vertexPathName);
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
					} else {
						if (map.isOne()) {
							getter.getBody().addToStatements(
									"return (" + map.javaTypePath() + ") this.vertex.getProperty(\""
											+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
						} else {
							OJField result = new OJField();
							result.setName("result");
							result.setType(map.javaAuditTypePath());
							result.setInitExp("(" + map.javaAuditTypePath().getCollectionTypeName() + ") this.vertex.getProperty(\""
									+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
							getter.getBody().addToLocals(result);
							OJIfStatement ifNull = new OJIfStatement("result != null");
							ifNull.addToThenPart("return result");
							ifNull.addToElsePart("return " + map.javaDefaultValue());
							getter.getBody().addToStatements(ifNull);
						}
					}
				}
			}
		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	private void buildGetAuditForOne(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		OJOperation getAllAuditsForOne = new OJOperation();
		getAllAuditsForOne.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForOne.setName("getAuditFor" + NameConverter.capitalize(map.umlName()));
		getAllAuditsForOne.addParam("previous", owner.getPathName());

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(map.javaAuditBaseTypePath());
		getAllAuditsForOne.addParam("removedAudits", param);

		getAllAuditsForOne.addParam("transactionNo", new OJPathName("int"));
		getAllAuditsForOne.setReturnType(map.javaAuditBaseTypePath());
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart(map.javaAuditBaseTypePath().getLast() + " result = previous.getAuditFor" + NameConverter.capitalize(map.umlName())
				+ "(transactionNo, removedAudits)");
		OJIfStatement ifStatement2 = new OJIfStatement("result != null", "return result");
		ifStatement2.addToElsePart("return getAuditFor" + NameConverter.capitalize(map.umlName())
				+ "(previous.getPreviousAuditEntry(), removedAudits, transactionNo)");
		ifStatement.addToThenPart(ifStatement2);
		getAllAuditsForOne.getBody().addToStatements(ifStatement);
		getAllAuditsForOne.getBody().addToStatements("return null");
		owner.addToOperations(getAllAuditsForOne);
	}

	private void buildGetAuditForThisOne(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		owner.addToImports(TinkerUtil.tinkerFormatter);
		owner.addToImports(new OJPathName("java.util.Date"));
		owner.addToImports(new OJPathName("java.util.HashSet"));
		OJOperation getAuditsForThisOne = new OJOperation();
		getAuditsForThisOne.setVisibility(OJVisibilityKind.PRIVATE);
		owner.addToOperations(getAuditsForThisOne);
		getAuditsForThisOne.setName("getAuditFor" + NameConverter.capitalize(map.umlName()));
		getAuditsForThisOne.addParam("transactionNo", new OJPathName("int"));

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(map.javaAuditBaseTypePath());
		getAuditsForThisOne.addParam("removedAudits", param);

		getAuditsForThisOne.setReturnType(map.javaAuditBaseTypePath());
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		String associationName = TinkerUtil.getEdgeName(map);
		if (isComposite) {
			getAuditsForThisOne.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + associationName + "\")");
		} else {
			getAuditsForThisOne.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + associationName + "\")");
		}
		OJForStatement forEdges = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		OJIfStatement ifNotDeleted = new OJIfStatement("edge.getProperty(\"deletedOn\")==null");

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);

		forEdges.getBody().addToStatements(ojTryStatement);
		ojTryStatement.getTryPart().addToStatements(ifNotDeleted);

		OJSimpleStatement forClass = new OJSimpleStatement();
		OJSimpleStatement constructClass = new OJSimpleStatement();
		if (isComposite) {
			forClass.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			constructClass.setExpression(map.javaAuditBaseTypePath().getLast() + " instance = (" + map.javaAuditBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
			ifNotDeleted.addToThenPart(forClass);
			ifNotDeleted.addToThenPart(constructClass);
		} else {
			forClass.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			constructClass.setExpression(map.javaAuditBaseTypePath().getLast() + " instance = (" + map.javaAuditBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
			ifNotDeleted.addToThenPart(forClass);
			ifNotDeleted.addToThenPart(constructClass);
		}

		OJIfStatement ifRemovedAuditContains = new OJIfStatement("!removedAudits.containsKey(instance.getOriginal().getUid())");
		ifRemovedAuditContains.addToThenPart("return (" + map.javaAuditBaseTypePath().getLast() + ")iterateToLatest(transactionNo, instance)");
		ifNotDeleted.addToThenPart(ifRemovedAuditContains);

		ifNotDeleted.addToElsePart(forClass);
		ifNotDeleted.addToElsePart(constructClass);
		ifNotDeleted.addToElsePart("removedAudits.put(instance.getOriginal().getUid(), instance)");

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getAuditsForThisOne.getBody().addToStatements(forEdges);
		getAuditsForThisOne.getBody().addToStatements("return null");

	}

	private void buildGetAllAuditsForMany(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		OJOperation getAllAuditsForMany = new OJOperation();
		getAllAuditsForMany.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForMany.setName("getAllAuditsFor" + NameConverter.capitalize(map.umlName()));
		getAllAuditsForMany.addParam("previous", owner.getPathName());

		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(map.javaAuditBaseTypePath());

		getAllAuditsForMany.addParam("audits", param);
		getAllAuditsForMany.addParam("removedAudits", param);

		getAllAuditsForMany.addParam("transactionNo", new OJPathName("int"));
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart("audits.putAll(previous.getAuditsFor" + NameConverter.capitalize(map.umlName()) + "(audits, removedAudits, transactionNo))");
		ifStatement.addToThenPart("getAllAuditsFor" + NameConverter.capitalize(map.umlName())
				+ "(previous.getPreviousAuditEntry(), audits, removedAudits, transactionNo)");
		getAllAuditsForMany.getBody().addToStatements(ifStatement);
		owner.addToOperations(getAllAuditsForMany);
	}

	private void buildGetAuditsForThisMany(NakedStructuralFeatureMap map, OJClass owner) {
		owner.addToImports(TinkerUtil.tinkerFormatter);
		owner.addToImports(new OJPathName("java.util.Date"));
		OJOperation getAuditsForThisMany = new OJOperation();
		getAuditsForThisMany.setVisibility(OJVisibilityKind.PRIVATE);
		owner.addToOperations(getAuditsForThisMany);
		getAuditsForThisMany.setName("getAuditsFor" + NameConverter.capitalize(map.umlName()));
		OJPathName param = new OJPathName("java.util.Map");
		param.addToElementTypes(new OJPathName("String"));
		param.addToElementTypes(map.javaAuditBaseTypePath());
		getAuditsForThisMany.addParam("audits", param);
		getAuditsForThisMany.addParam("removedAudits", param);
		getAuditsForThisMany.addParam("transactionNo", new OJPathName("int"));
		getAuditsForThisMany.setReturnType(param);
		OJField result = new OJField();
		result.setType(param);
		result.setName("result");
		result.setInitExp("new HashMap<String, " + map.javaAuditBaseTypePath().getLast() + ">()");
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		getAuditsForThisMany.getBody().addToLocals(result);
		
		INakedClassifier manyClassifier;
		boolean isComposite;
		if (map.getProperty().getOtherEnd() != null) {
			manyClassifier = map.getProperty().getOtherEnd().getOwner();
			isComposite = map.getProperty().isComposite();
			isComposite = TinkerUtil.calculateDirection(map, isComposite);
		} else {
			manyClassifier = (INakedClassifier) map.getProperty().getBaseType();
			isComposite = true;
		}		
		
		String asociationName = TinkerUtil.getEdgeName(map);
		if (isComposite) {
			getAuditsForThisMany.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + asociationName + "\")");
		} else {
			getAuditsForThisMany.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + asociationName + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		OJTryStatement ojTryStatement = new OJTryStatement();
		OJIfStatement ifNotDeleted = new OJIfStatement("edge.getProperty(\"deletedOn\")==null");
		forStatement.getBody().addToStatements(ojTryStatement);
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		ojTryStatement.getTryPart().addToStatements(ifNotDeleted);
		OJSimpleStatement classForName = new OJSimpleStatement();
		OJSimpleStatement constructMany = new OJSimpleStatement();
		if (isComposite) {
			classForName.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ifNotDeleted.addToThenPart(classForName);
			constructMany.setExpression(map.javaAuditBaseTypePath().getLast() + " instance = (" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
					+ TinkerAuditCreator.AUDIT + ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
			ifNotDeleted.addToThenPart(constructMany);
		} else {
			classForName.setExpression("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ifNotDeleted.addToThenPart(classForName);
			constructMany.setExpression(map.javaAuditBaseTypePath().getLast() + " instance = (" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
					+ TinkerAuditCreator.AUDIT + ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
			ifNotDeleted.addToThenPart(constructMany);
		}
		OJIfStatement ifStatement = new OJIfStatement(
				"!removedAudits.containsKey(instance.getOriginal().getUid()) && !audits.containsKey(instance.getOriginal().getUid())");
		ifNotDeleted.addToThenPart(ifStatement);
		ifStatement.addToThenPart(map.javaAuditBaseTypePath().getLast() + " previous = (" + map.javaAuditBaseTypePath().getLast()
				+ ")iterateToLatest(transactionNo, instance)");
		ifStatement.addToThenPart("result.put(previous.getOriginal().getUid(), previous)");

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifNotDeleted.addToElsePart(classForName);
		ifNotDeleted.addToElsePart(constructMany);
		ifNotDeleted.addToElsePart("removedAudits.put(instance.getOriginal().getUid(), instance)");
		getAuditsForThisMany.getBody().addToStatements(forStatement);
		getAuditsForThisMany.getBody().addToStatements("return result");
	}

	private void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getBody().addToStatements(
				"return getAuditFor" + NameConverter.capitalize(map.umlName()) + "(this, new HashMap<String, " + map.javaAuditBaseTypePath().getLast()
						+ ">(), (getNextAuditEntry()!=null?getNextAuditEntry().getTransactionNo():-1))");
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getOwner().addToImports(new OJPathName("java.util.Map"));
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
		OJField audits = new OJField();
		OJPathName var = new OJPathName("java.util.Map");
		var.addToElementTypes(new OJPathName("String"));
		var.addToElementTypes(map.javaAuditBaseTypePath());
		audits.setInitExp("new HashMap<String, " + map.javaAuditBaseTypePath() + ">()");
		audits.setName("allAudits");
		audits.setType(var);
		getter.getBody().addToLocals(audits);
		getter.getBody().addToStatements(
				"getAllAuditsFor" + NameConverter.capitalize(map.umlName()) + "(this, allAudits, new HashMap<String, " + map.javaAuditBaseTypePath().getLast()
						+ ">(), (getNextAuditEntry()!=null?getNextAuditEntry().getTransactionNo():-1))");
		getter.getBody().addToStatements("return new " + map.javaAuditDefaultTypePath().getLast() + "(allAudits.values())");
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
	}

}
