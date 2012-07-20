package org.nakeduml.tinker.generator;

import java.util.Collections;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.basicjava.HashcodeBuilder;
import org.opaeum.javageneration.basicjava.ToXmlStringBuilder;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { TinkerAttributeImplementor.class, PersistentNameGenerator.class, HashcodeBuilder.class }, after = {HashcodeBuilder.class, ToXmlStringBuilder.class, ExtendedCompositionSemantics.class, PersistentNameGenerator.class, CompositionNodeImplementor.class})
public class TinkerImplementNodeStep extends StereotypeAnnotator {

//	@VisitAfter(matchSubclasses = true)
//	public void visitActivity(INakedActivity c) {
//		if (OJUtil.hasOJClass(c)) {
//			OJAnnotatedClass ojClass = findJavaClass(c);
//			ojClass.addToImports(TinkerGenerationUtil.graphDbPathName);
//			ojClass.addToImports(TinkerGenerationUtil.edgePathName);
//			ojClass.addToImports(TinkerGenerationUtil.introspectionUtilPathName);
//			implementTinkerCompositionNode(ojClass);
//			implementIsRoot(ojClass, c.getEndToComposite() == null);
//			addPersistentConstructor(ojClass);
//			if (c.getGeneralizations().isEmpty()) {
//				persistUid(ojClass);
//				extendsBaseSoftDelete(ojClass);
//				addGetObjectVersion(ojClass);
//				addGetSetId(ojClass);
//				initialiseVertexInPersistentConstructor(ojClass);
//				addCreateComponentsToDefaultConstructor(ojClass);
//			} else {
//				addSuperWithPersistenceToDefaultConstructor(ojClass);
//			}
//			
//			if (c.getEndToComposite() != null) {
//				addInitVertexToConstructorWithOwningObject(ojClass, c);
//			} else {
//				if (!c.getIsAbstract() && !hasSuperwithCompositeParent(c)) {
//					attachCompositeRootToDbRoot(ojClass);
//				}
//			}	
//			addContructorWithVertex(ojClass);
//		}
//		
//	}

	@VisitAfter(matchSubclasses = true)
	public void visitSignal(INakedSignal c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			ojClass.addToImports(TinkerGenerationUtil.graphDbPathName);
			ojClass.addToImports(TinkerGenerationUtil.edgePathName);
			ojClass.addToImports(TinkerGenerationUtil.introspectionUtilPathName);
			implementIsRoot(ojClass, false);
			addPersistentConstructor(ojClass);
			if (c.getGeneralizations().isEmpty()) {
				persistUid(ojClass);
				extendsBaseSoftDelete(ojClass, c);
				addGetObjectVersion(ojClass);
				addGetSetId(ojClass);
				initialiseVertexInPersistentConstructor(ojClass, c);
			} else {
				addSuperWithPersistenceToDefaultConstructor(ojClass);
			}
			addContructorWithVertex(ojClass, c);
		}
	}
	
	@VisitAfter(matchSubclasses = true)
	public void visitClass(ICompositionParticipant c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType) && !(c instanceof INakedInterface)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			ojClass.addToImports(TinkerGenerationUtil.graphDbPathName);
			ojClass.addToImports(TinkerGenerationUtil.edgePathName);
			ojClass.addToImports(TinkerGenerationUtil.introspectionUtilPathName);
			implementTinkerCompositionNode(ojClass);
			implementIsRoot(ojClass, c.getEndToComposite() == null);
			addPersistentConstructor(ojClass);
			if (c.getGeneralizations().isEmpty()) {
				persistUid(ojClass);
				extendsBaseSoftDelete(ojClass, c);
				addGetObjectVersion(ojClass);
				addGetSetId(ojClass);
				initialiseVertexInPersistentConstructor(ojClass, c);
				addCreateComponentsToDefaultConstructor(ojClass, c);
			} else {
				addSuperWithPersistenceToDefaultConstructor(ojClass);
			}
			
			if (c.getEndToComposite() != null && !(c instanceof INakedInterface)) {
				addInitVertexToConstructorWithOwningObject(ojClass, c);
			} else {
				if (!c.isAbstract() && !hasSuperwithCompositeParent(c)) {
					attachCompositeRootToDbRoot(ojClass);
				}
			}	
			addContructorWithVertex(ojClass, c);
		}
	}
	
	private void persistUid(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getUid = (OJAnnotatedOperation) ojClass.findOperation("getUid", Collections.emptyList());
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
		ojClass.removeFromImplementedInterfaces(TinkerGenerationUtil.compositionNodePathName);
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
		TinkerGenerationUtil.addOverrideAnnotation(getObjectVersion);
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
	
	private void extendsBaseSoftDelete(OJAnnotatedClass ojClass, INakedClassifier c) {
		if (c instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c).getClassifierBehavior()!=null) {
			ojClass.setSuperclass(TinkerGenerationUtil.BASE_BEHAVIORED_CLASSIFIER);
		} else {
			ojClass.setSuperclass(TinkerGenerationUtil.BASE_AUDIT_SOFT_DELETE_TINKER);
		}
	}
	
	private void addPersistentConstructor(OJAnnotatedClass ojClass) {
		OJConstructor persistentConstructor = new OJConstructor();
		persistentConstructor.setName(TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_NAME);
		persistentConstructor.addParam(TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME, new OJPathName("java.lang.Boolean"));
		ojClass.addToConstructors(persistentConstructor);
	}
	
	private void initialiseVertexInPersistentConstructor(OJAnnotatedClass ojClass, INakedClassifier c) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("this.vertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"" + TinkerGenerationUtil.getClassMetaId(ojClass) + "\")");
		if (c instanceof ICompositionParticipant) {
			constructor.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		}
		constructor.getBody().addToStatements("defaultCreate()");
		ojClass.addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
	}
	private void addSuperWithPersistenceToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("super( "+TinkerGenerationUtil.PERSISTENT_CONSTRUCTOR_PARAM_NAME+" )"));
	}
	private void addInitVertexToConstructorWithOwningObject(OJAnnotatedClass ojClass, ICompositionParticipant c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().getStatements()
					.add(0, new OJSimpleStatement("this.vertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"" + TinkerGenerationUtil.getClassMetaId(ojClass) + "\")"));
			constructor.getBody().getStatements().add(1, new OJSimpleStatement("createComponents()"));
			if (c instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c).getClassifierBehavior()!=null) {
				//After init, before addToOwningObject
				constructor.getBody().addToStatements(3, new OJSimpleStatement("attachToRoot()"));
			}
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
	
	private void attachCompositeRootToDbRoot(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements(
				"Edge edge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, " + TinkerGenerationUtil.graphDbAccess + ".getRoot(), this.vertex, \"root\")");
		constructor.getBody().addToStatements("edge.setProperty(\"inClass\", "+TinkerGenerationUtil.TINKER_GET_CLASSNAME+")");
	}

	private void addCreateComponentsToDefaultConstructor(OJAnnotatedClass ojClass, INakedClassifier c) {
		OJConstructor constructor = ojClass.findConstructor(new OJPathName("java.lang.Boolean"));
		constructor.getBody().addToStatements("createComponents()");
		if (c instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) c).getClassifierBehavior()!=null) {
			constructor.getBody().addToStatements("attachToRoot()");
		}
	}

	private void addContructorWithVertex(OJAnnotatedClass ojClass, INakedClassifier c) {
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
