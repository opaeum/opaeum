package org.nakeduml.tinker.generator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.basicjava.HashcodeBuilder;
import net.sf.nakeduml.javageneration.basicjava.ToXmlStringBuilder;
import net.sf.nakeduml.javageneration.composition.CompositionNodeImplementor;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerAttributeImplementor.class, PersistentNameGenerator.class, HashcodeBuilder.class }, after = {HashcodeBuilder.class, ToXmlStringBuilder.class, ExtendedCompositionSemantics.class, PersistentNameGenerator.class, CompositionNodeImplementor.class})
public class TinkerImplementNodeStep extends StereotypeAnnotator {


	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			ojClass.addToImports(TinkerGenerationUtil.graphDbPathName);
			ojClass.addToImports(TinkerGenerationUtil.edgePathName);
			ojClass.addToImports(new OJPathName("org.nakeduml.runtime.domain.IntrospectionUtil"));
			implementTinkerCompositionNode(ojClass);
			implementIsRoot(ojClass, c.getEndToComposite() == null);
			addPersistentConstructor(ojClass);
			if (c.getGeneralizations().isEmpty()) {
				persistUid(ojClass);
				extendsBaseSoftDelete(ojClass);
				addGetObjectVersion(ojClass);
				addGetSetId(ojClass);
				initialiseVertexInPersistentConstructor(c, ojClass);
				addCreateComponentsToDefaultConstructor(ojClass);
			} else {
				addSuperWithPersistenceToDefaultConstructor(ojClass);
			}
			
			if (c.getEndToComposite() != null) {
				addInitVertexToConstructorWithOwningObject(ojClass, c);
			} else {
				if (!c.getIsAbstract() && !hasSuperwithCompositeParent(c)) {
					attachCompositeRootToDbRoot(ojClass, c);
				}
			}	
			addContructorWithVertex(ojClass, c);
		}
	}
	
	private void persistUid(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getUid = (OJAnnotatedOperation) OJUtil.findOperation(ojClass, "getUid");
		getUid.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getUid.getBody().removeAllFromStatements();
		getUid.getBody().addToStatements("String uid = (String) this.vertex.getProperty(\"uid\")");
		OJIfStatement ifStatement = new OJIfStatement("uid==null || uid.trim().length()==0");
		ifStatement.setCondition("uid==null || uid.trim().length()==0");
		ifStatement.addToThenPart("uid=UUID.randomUUID().toString()");
		ifStatement.addToThenPart("this.vertex.setProperty(\"uid\", uid)");
		getUid.getBody().addToStatements(ifStatement);
		getUid.getBody().addToStatements("return uid");
	}	

	private void implementTinkerCompositionNode(OJAnnotatedClass ojClass) {
		ojClass.removeFromImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.CompositionNode"));
		ojClass.addToImplementedInterfaces(TinkerGenerationUtil.tinkerCompositionNodePathName);
	}

	private void addGetSetId(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getId = new OJAnnotatedOperation("getId");
		getId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getId.setReturnType(new OJPathName("java.lang.Long"));
		getId.getBody().addToStatements("return TinkerIdUtil.getId(this.vertex)");
		ojClass.addToImports(TinkerGenerationUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getId);

		OJAnnotatedOperation setId = new OJAnnotatedOperation("setId");
		setId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		setId.addParam("id", new OJPathName("java.lang.Long"));
		setId.getBody().addToStatements("TinkerIdUtil.setId(this.vertex, id)");
		ojClass.addToOperations(setId);
	}

	private void addGetObjectVersion(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getObjectVersion = new OJAnnotatedOperation("getObjectVersion");
		getObjectVersion.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getObjectVersion.setReturnType(new OJPathName("int"));
		getObjectVersion.getBody().addToStatements("return TinkerIdUtil.getVersion(this.vertex)");
		ojClass.addToImports(TinkerGenerationUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getObjectVersion);
	}

	private void implementIsRoot(OJAnnotatedClass ojClass, boolean b) {
		OJAnnotatedOperation isRoot = new OJAnnotatedOperation("isTinkerRoot");
		isRoot.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		isRoot.setReturnType(new OJPathName("boolean"));
		isRoot.getBody().addToStatements("return " + b);
		ojClass.addToOperations(isRoot);
	}
	
	private void extendsBaseSoftDelete(OJAnnotatedClass ojClass) {
		ojClass.setSuperclass(TinkerGenerationUtil.BASE_AUDIT_SOFT_DELETE_TINKER);
	}
	
	private void addPersistentConstructor(OJAnnotatedClass ojClass) {
		OJConstructor persistentConstructor = new OJConstructor();
		persistentConstructor.setName(TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_NAME);
		persistentConstructor.addParam(TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME, new OJPathName("java.lang.Boolean"));
		ojClass.addToConstructors(persistentConstructor);
	}
	
	private void initialiseVertexInPersistentConstructor(INakedEntity c, OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("this.vertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"" + TinkerGenerationUtil.getClassMetaId(ojClass) + "\")");
		constructor.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		constructor.getBody().addToStatements("defaultCreate()");
		ojClass.addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
	}
	private void addSuperWithPersistenceToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("super( "+TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME+" )"));
	}
	private void addInitVertex(OJAnnotatedClass ojClass, INakedEntity c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJAnnotatedOperation initVertex = new OJAnnotatedOperation(TinkerGenerationUtil.INIT_VERTEX);
		initVertex.addParam("owningObject", compositeEndMap.javaBaseTypePath());
		initVertex.setVisibility(OJVisibilityKind.PRIVATE);
		if (compositeEndMap.isOneToOne()) {
			initVertex.getBody().addToStatements(
					"Iterable<Edge> iter = owningObject.getVertex().getOutEdges" + "(\"" + c.getEndToComposite().getAssociation().getName() + "\")");
			OJIfStatement ifAllReadyHasOne = new OJIfStatement("iter.iterator().hasNext()");
			ifAllReadyHasOne.addToThenPart(TinkerGenerationUtil.graphDbAccess + ".removeVertex(this.vertex)");
			ifAllReadyHasOne.addToThenPart("throw new IllegalStateException(\""
					+ c.getEndToComposite().getOtherEnd().getOwner().getMappingInfo().getQualifiedJavaName() + " already has an association with "
					+ c.getMappingInfo().getQualifiedJavaName() + ", the relationship is one to one!\")");
			initVertex.getBody().addToStatements(ifAllReadyHasOne);
		}

		// Add association meta information, i.e. classname of other end
		String associationName = c.getEndToComposite().getAssociation().getName();
		initVertex.getBody().addToStatements(
				"Edge edge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, owningObject.getVertex(), this.vertex, \"" + associationName + "\")");
		initVertex.getBody().addToStatements("edge.setProperty(\"outClass\", owningObject.getClass().getName())");
		initVertex.getBody().addToStatements("edge.setProperty(\"inClass\", "+TinkerGenerationUtil.TINKER_GET_CLASSNAME+")");

		OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
		ojClass.addToImports(edgePathName);
		ojClass.addToOperations(initVertex);
	}
	private void addInitVertexToConstructorWithOwningObject(OJAnnotatedClass ojClass, INakedEntity c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().getStatements()
					.add(0, new OJSimpleStatement("this.vertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"" + TinkerGenerationUtil.getClassMetaId(ojClass) + "\")"));
			constructor.getBody().getStatements().add(1, new OJSimpleStatement("createComponents()"));
			constructor.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
			constructor.getBody().addToStatements("defaultCreate()");
			ojClass.addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
		} else {
			constructor.getBody().getStatements().add(0, new OJSimpleStatement("super(true)"));
		}
	}
	private boolean hasSuperwithCompositeParent(ICompositionParticipant c) {
		if (c.getEndToComposite() != null) {
			return true;
		} else {
			if (c.getSupertype() != null) {
				return hasSuperwithCompositeParent((ICompositionParticipant) c.getSupertype());
			} else {
				return false;
			}
		}
	}
	
	private void attachCompositeRootToDbRoot(OJAnnotatedClass ojClass, INakedEntity c) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements(
				"Edge edge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, " + TinkerGenerationUtil.graphDbAccess + ".getRoot(), this.vertex, \"root\")");
		constructor.getBody().addToStatements("edge.setProperty(\"inClass\", "+TinkerGenerationUtil.TINKER_GET_CLASSNAME+")");
	}
	
	private void removeOwningObjectMethodFromConstructorWithOwningObject(
			INakedEntity c, OJAnnotatedClass ojClass) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		OJStatement addToOwningObject = constructor.getBody().findStatementRecursive(CompositionNodeImplementor.ADD_TO_OWNING_OBJECT);
		constructor.getBody().removeFromStatements(addToOwningObject);
	}

	private void addCreateComponentsToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("createComponents()");
	}

	private void addContructorWithVertex(OJAnnotatedClass ojClass, INakedEntity c) {
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("vertex", new OJPathName("com.tinkerpop.blueprints.pgm.Vertex"));
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().addToStatements("this.vertex=vertex");
		} else {
			constructor.getBody().addToStatements("super(vertex)");
		}
		ojClass.addToConstructors(constructor);
	}

}
