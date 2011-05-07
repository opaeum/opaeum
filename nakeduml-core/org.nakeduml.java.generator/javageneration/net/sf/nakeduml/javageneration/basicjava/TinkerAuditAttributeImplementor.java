package net.sf.nakeduml.javageneration.basicjava;

import java.util.Date;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
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
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.name.NameConverter;
import org.util.TinkerFormatter;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

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
		if (OJUtil.hasOJClass(p.getOwner())) {
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
			} else if (p.isDerived() || p.isReadOnly()) {
				OJAnnotatedClass owner = findAuditJavaClass(umlOwner);
				buildGetter(owner, map, true);
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
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
				if (map.isManyToOne() && map.getProperty().getSubsettedProperties().isEmpty()) {
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
				getter.getBody().addToStatements(
						"return (" + map.javaAuditBaseTypePath().getLast() + ") this.vertex.getProperty(\""
								+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	private void buildGetAuditForOne(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(map.getProperty().getOtherEnd());
		OJOperation getAllAuditsForOne = new OJOperation();
		getAllAuditsForOne.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForOne.setName("getAuditFor" + NameConverter.capitalize(map.umlName()));
		getAllAuditsForOne.addParam("previous", owner.getPathName());
		getAllAuditsForOne.addParam("transactionNo", new OJPathName("int"));
		getAllAuditsForOne.setReturnType(map.javaAuditBaseTypePath());
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart(map.javaAuditBaseTypePath().getLast() + " result = previous.getAuditFor" + NameConverter.capitalize(map.umlName()) + "(transactionNo)");
		OJIfStatement ifStatement2 = new OJIfStatement("result != null", "return result");
		ifStatement2.addToElsePart("return getAuditFor" + NameConverter.capitalize(map.umlName()) + "(previous.getPreviousAuditEntry(), transactionNo)");
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
		getAuditsForThisOne.setReturnType(map.javaAuditBaseTypePath());
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		String associationName = map.getProperty().getAssociation().getName();
		if (isComposite) {
			getAuditsForThisOne.getBody().addToStatements(
					"Iterable<Edge> iter = this.vertex.getOutEdges(\"" + associationName + "\")");
		} else {
			getAuditsForThisOne.getBody().addToStatements(
					"Iterable<Edge> iter = this.vertex.getInEdges(\"" + associationName + "\")");
		}
		OJIfStatement ifStatement = new OJIfStatement("iter.iterator().hasNext()", "Edge edge = iter.iterator().next()");

		OJIfStatement ifNotDeleted = new OJIfStatement(
				"edge.getProperty(\"deletedOn\")==null || TinkerFormatter.parse((String) edge.getProperty(\"deletedOn\")).after(new Date())");
		ifStatement.addToThenPart(ifNotDeleted);

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		ifNotDeleted.addToThenPart(ojTryStatement);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					map.javaAuditBaseTypePath().getLast() + " instance = (" +map.javaAuditBaseTypePath().getLast()+")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					map.javaAuditBaseTypePath().getLast() + " instance = (" +map.javaAuditBaseTypePath().getLast()+")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
		}
		ojTryStatement.getTryPart().addToStatements(map.javaAuditBaseTypePath().getLast() + " previous = instance");
		ojTryStatement.getTryPart().addToStatements("List<? extends "+map.javaAuditBaseTypePath().getLast()+"> nextAudits = instance.getNextAuditEntries()");
		OJForStatement forStatement = new OJForStatement("nextAudit", map.javaAuditBaseTypePath(), "nextAudits");
		ojTryStatement.getTryPart().addToStatements(forStatement);
		OJIfStatement ifStatement2;
		if (!isComposite) {
			ifStatement2 = new OJIfStatement("nextAudit.getVertex().getOutEdges(\""+associationName+"\").iterator().hasNext()");
			ifStatement2.addToThenPart("Edge nextEdge = nextAudit.getVertex().getOutEdges(\""+associationName+"\").iterator().next()");
			ifStatement2.addToThenPart("Vertex oneVertex = nextEdge.getInVertex()");
		} else {
			ifStatement2 = new OJIfStatement("nextAudit.getVertex().getInEdges(\""+associationName+"\").iterator().hasNext()");
			ifStatement2.addToThenPart("Edge nextEdge = nextAudit.getVertex().getInEdges(\""+associationName+"\").iterator().next()");
			ifStatement2.addToThenPart("Vertex oneVertex = nextEdge.getOutVertex()");
		}
		
		OJIfStatement ifStatement3 = new OJIfStatement("(Integer)oneVertex.getProperty(\"transactionNo\")<=transactionNo || TinkerFormatter.parse((String)nextEdge.getProperty(\"deletedOn\")).before(new Date())");
		ifStatement3.addToThenPart("previous = null");
		ifStatement2.addToThenPart(ifStatement3);
		
		ifStatement2.addToThenPart("break");
		forStatement.getBody().addToStatements(ifStatement2);
		forStatement.getBody().addToStatements("previous = nextAudit");
		ojTryStatement.getTryPart().addToStatements("return previous");
		
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getAuditsForThisOne.getBody().addToStatements(ifStatement);
		getAuditsForThisOne.getBody().addToStatements("return null");

	}

	private void buildGetAllAuditsForMany(NakedStructuralFeatureMap map, OJAnnotatedClass owner) {
		OJOperation getAllAuditsForMany = new OJOperation();
		getAllAuditsForMany.setVisibility(OJVisibilityKind.PRIVATE);
		getAllAuditsForMany.setName("getAllAuditsFor" + map.javaBaseTypePath().getLast());
		getAllAuditsForMany.addParam("previous", owner.getPathName());
		getAllAuditsForMany.addParam("audits", map.javaAuditTypePath());
		getAllAuditsForMany.addParam("transactionNo", new OJPathName("int"));
		OJIfStatement ifStatement = new OJIfStatement("previous != null");
		ifStatement.addToThenPart("audits.addAll(previous.getAuditsFor" + NameConverter.capitalize(map.umlName()) + "(audits, transactionNo))");
		ifStatement.addToThenPart("getAllAuditsFor" + map.javaBaseTypePath().getLast() + "(previous.getPreviousAuditEntry(), audits, transactionNo)");
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
		OJPathName param = new OJPathName("java.util.Set");
		param.addToElementTypes(map.javaAuditBaseTypePath());
		getAuditsForThisMany.addParam("audits", param);
		getAuditsForThisMany.addParam("transactionNo", new OJPathName("int"));
		getAuditsForThisMany.setReturnType(map.javaAuditTypePath());
		OJField result = new OJField();
		result.setType(map.javaAuditTypePath());
		result.setName("result");
		result.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 3) + TinkerAuditCreator.AUDIT + ">()");
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);

		getAuditsForThisMany.getBody().addToLocals(result);
		INakedClassifier manyClassifier = map.getProperty().getOtherEnd().getOwner();
		
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		String asociationName = map.getProperty().getAssociation().getName();
		if (isComposite) {
			getAuditsForThisMany.getBody().addToStatements(
					"Iterable<Edge> iter = this.vertex.getOutEdges(\"" + asociationName + "\")");
		} else {
			getAuditsForThisMany.getBody().addToStatements(
					"Iterable<Edge> iter = this.vertex.getInEdges(\"" + asociationName + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);

		OJIfStatement ifNotDeleted = new OJIfStatement(
				"edge.getProperty(\"deletedOn\")==null || TinkerFormatter.parse((String) edge.getProperty(\"deletedOn\")).after(new Date())");
		forStatement.getBody().addToStatements(ifNotDeleted);

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		ifNotDeleted.addToThenPart(ojTryStatement);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					map.javaAuditBaseTypePath().getLast() +  " instance = (" + manyClassifier.getMappingInfo().getJavaName().getAsIs() + TinkerAuditCreator.AUDIT
							+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					map.javaAuditBaseTypePath().getLast() + " instance = (" + manyClassifier.getMappingInfo().getJavaName().getAsIs() + TinkerAuditCreator.AUDIT
							+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
		}
		
		OJIfStatement ifStatement = new OJIfStatement("!audits.contains(instance)");
		ojTryStatement.getTryPart().addToStatements(ifStatement);
		ifStatement.addToThenPart(map.javaAuditBaseTypePath().getLast() + " previous = instance");
		ifStatement.addToThenPart("List<? extends " + map.javaAuditBaseTypePath().getLast()+ "> nextAudits = instance.getNextAuditEntries()");
		OJForStatement ojForStatement = new OJForStatement("nextAudit", map.javaAuditBaseTypePath(), "nextAudits");
		
		OJIfStatement ifStatement2;
		if (!isComposite) { 
			ifStatement2 = new OJIfStatement("nextAudit.getVertex().getOutEdges(\""+asociationName+"\").iterator().hasNext()");
			ifStatement2.addToThenPart("Edge nextEdge = nextAudit.getVertex().getOutEdges(\""+asociationName+"\").iterator().next()");
			ifStatement2.addToThenPart("Vertex oneVertex = nextEdge.getInVertex()");
		} else {
			ifStatement2 = new OJIfStatement("nextAudit.getVertex().getInEdges(\""+asociationName+"\").iterator().hasNext()");
			ifStatement2.addToThenPart("Edge nextEdge = nextAudit.getVertex().getInEdges(\""+asociationName+"\").iterator().next()");
			ifStatement2.addToThenPart("Vertex oneVertex = nextEdge.getOutVertex()");
		}
		
		OJIfStatement ifStatement3 = new OJIfStatement("(Integer)oneVertex.getProperty(\"transactionNo\")<=transactionNo || TinkerFormatter.parse((String)nextEdge.getProperty(\"deletedOn\")).before(new Date())");
		ifStatement3.addToThenPart("previous = null");
		ifStatement2.addToThenPart(ifStatement3);
		ifStatement2.addToThenPart("break");
		ojForStatement.getBody().addToStatements(ifStatement2);
		ifStatement.addToThenPart(ojForStatement);
		ojForStatement.getBody().addToStatements("previous = nextAudit");
		OJIfStatement ifStatement4 = new OJIfStatement("previous != null", "result.add(previous)");
		ifStatement.addToThenPart(ifStatement4);

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getAuditsForThisMany.getBody().addToStatements(forStatement);
		getAuditsForThisMany.getBody().addToStatements("return result");
	}

	private void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getBody().addToStatements("return getAuditFor" + NameConverter.capitalize(map.umlName()) + "(this, getTransactionNo())");
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getOwner().addToImports(new OJPathName("java.util.Map"));
		getter.getOwner().addToImports(new OJPathName("java.util.HashMap"));
		getter.getOwner().addToImports(new OJPathName("net.sf.nakeduml.javageneration.basicjava.TinkerUtil"));
		OJField audits = new OJField();
		audits.setName("allAudits");
		audits.setType(map.javaAuditTypePath());
		audits.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 3) + TinkerAuditCreator.AUDIT + ">()");
		getter.getBody().addToLocals(audits);
		getter.getBody().addToStatements("getAllAuditsFor" + map.javaBaseTypePath().getLast() + "(this, allAudits, getTransactionNo())");
		getter.getBody().addToStatements("return allAudits");
	}

	private boolean calculateDirection(NakedStructuralFeatureMap map, boolean isComposite) {
		if (map.isOneToOne() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
		} else if (map.isOneToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
		} else if (map.isManyToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = 0 > map.getProperty().getName().compareTo(map.getProperty().getOtherEnd().getName());
		}
		return isComposite;
	}

}
