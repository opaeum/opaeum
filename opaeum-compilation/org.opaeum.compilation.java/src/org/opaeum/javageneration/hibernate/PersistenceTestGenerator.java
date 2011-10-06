package org.opaeum.javageneration.hibernate;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractTestDataGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.GeneralizationUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class, requires = {HibernateAnnotator.class}, after = { HibernateAnnotator.class })
public class PersistenceTestGenerator extends AbstractTestDataGenerator {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if ((isPersistent(c) /* || c instanceof INakedInterface */) && OJUtil.hasOJClass(c)) {
			if(c.getMappingInfo().requiresJavaRename()){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, new OJPathName(c.getMappingInfo().getQualifiedJavaName() + "PersistenceTest"));
			}
			OJAnnotatedClass ojClass = findJavaClass(c);
			String name = ojClass.getName();
			String testName = name + "PersistenceTest";
			OJAnnotatedClass test = new OJAnnotatedClass(testName);
			test.addToImports(UtilityCreator.getUtilPathName() + ".HibernateConfigurator");
			OJPackage testPackage = ojClass.getMyPackage();
			testPackage.addToClasses(test);
			super.createTextPath(test, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC);
			INakedClassifier nc = c;
			addPopulate(ojClass, test, nc);
			OJAnnotatedField instance = new OJAnnotatedField("instance",ojClass.getPathName());
			instance.setStatic(true);
			test.addToFields(instance);
			addGetInstance(ojClass, test, nc);
			addCreateNew(ojClass, test, nc);
			addTestInsert(ojClass, test, nc);
			addTestOptionalFields(ojClass, test, nc);
			addReset(test, nc);
		}
	}

	@Override
	protected String getTestDataName(INakedClassifier child) {
		return OJUtil.classifierPathname(child).getLast() + "PersistenceTest";
	}

	protected void addPopulate(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJOperation populate = new OJAnnotatedOperation("populate");
		populate.addToThrows("Exception");
		test.addToOperations(populate);
		populate.setStatic(true);
		populate.addParam("instance", ojClass.getPathName());
		if (c.getNakedGeneralizations().size() > 0) {
			OJPathName superType = getTestDataPath(c.getSupertype());
			test.addToImports(superType);
			populate.getBody().addToStatements(superType.getLast() + ".populate(instance)");
		}
		for (INakedProperty f : c.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if (f.getOwner() == c || c.getInterfaces().contains(f.getOwner())) {
				// do properties for directly implemented interfaces too.
				if (map.isOne() && !(f.isDerived() || isReadOnly || f.isInverse()) && f.isRequired()) {
					String defaultValue = calculateDefaultValue(test, populate.getBody(), f);
					populate.getBody().addToStatements("instance." + map.setter() + "(" + defaultValue + ")");
				}
			}
		}
		test.addToOperations(populate);
	}

	protected void addGetInstance(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJOperation getInstance = new OJAnnotatedOperation("getInstance");
		test.addToOperations(getInstance);
		getInstance.addToThrows("Exception");
		getInstance.setStatic(true);
		getInstance.setReturnType(ojClass.getPathName());
		test.addToOperations(getInstance);
		OJIfStatement ifNull = new OJIfStatement();
		ifNull.setCondition("instance==null");
		if (c.getIsAbstract()) {
			Collection<? extends INakedClassifier> subClasses = getConcreteSubclassifiersOf(c);
			if (subClasses.size() > 0) {
				INakedClassifier child = subClasses.iterator().next();
				OJPathName testPath = getTestDataPath(child);
				test.addToImports(testPath);
				ifNull.getThenPart().addToStatements("instance=" + testPath.getLast() + ".getInstance()");
			}else{
				ifNull.getThenPart().addToStatements("throw new RuntimeException(\"Entity "+ c.getName() +" has no concrete implementations\")");
			}
		} else {
			ifNull.getThenPart().addToStatements("instance=new " + ojClass.getName() + "()");
			ifNull.getThenPart().addToStatements("populate(instance)");
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager",new OJPathName("javax.persistence.EntityManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			ifNull.getThenPart().addToLocals(entityManager);
			ifNull.getThenPart().addToStatements("entityManager.persist(instance)");
		}
		// ifNull.getThenPart().addToStatements("entityManager.flush()"); CAuses
		// hibernate to hang????
		getInstance.getBody().addToStatements(ifNull);
		getInstance.getBody().addToStatements("return instance");
	}

	protected void addCreateNew(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJOperation createNew = new OJAnnotatedOperation("createNew");
		createNew.addToThrows("Exception");
		test.addToOperations(createNew);
		createNew.setStatic(true);
		createNew.setReturnType(ojClass.getPathName());
		test.addToOperations(createNew);
		Collection<? extends INakedClassifier> subClasses = GeneralizationUtil.getAllSubClassifiers(c,getModelInScope());
		if (c.getIsAbstract()) {
			if (subClasses.size() > 0) {
				//TODO invalid
				INakedClassifier child = subClasses.iterator().next();
				OJPathName testPath = getTestDataPath(child);
				createNew.getBody().addToStatements("return " + testPath.getLast() + ".createNew()");
			}else{
				createNew.getBody().addToStatements("throw new RuntimeException(\"Entity "+ c.getName() +" has no concrete implementations\")");
			}
		} else {
			OJAnnotatedField newInstance = new OJAnnotatedField("newInstance", ojClass.getPathName());
			newInstance.setInitExp("new " + ojClass.getName() + "()");
			createNew.getBody().addToLocals(newInstance);
			createNew.getBody().addToStatements("populate(newInstance)");
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager",new OJPathName("javax.persistence.EntityManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			createNew.getBody().addToLocals(entityManager);
			createNew.getBody().addToStatements("entityManager.persist(newInstance)");
			createNew.getBody().addToStatements("return newInstance");
		}
	}

	protected void addReset(OJAnnotatedClass test, INakedClassifier c) {
		OJAnnotatedField isResetting = new OJAnnotatedField("isResetting",new OJPathName("boolean"));
		isResetting.setInitExp("false");
		isResetting.setStatic(true);
		test.addToFields(isResetting);
		OJAnnotatedOperation reset = new OJAnnotatedOperation("reset");
		test.addToOperations(reset);
		reset.setStatic(true);
		OJAnnotationValue afterTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.AfterMethod"));
		afterTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
		afterTest.putAttribute(new OJAnnotationAttributeValue("alwaysRun", true));
		reset.putAnnotation(afterTest);
		test.addToOperations(reset);
		OJIfStatement ifResetting = new OJIfStatement("isResetting==false", "isResetting=true");
		ifResetting.getThenPart().addToStatements("instance=null");
		if (c.hasSupertype()) {
			OJPathName featureTest = new OJPathName(
			// (((INakedEntity)
			// c.getSupertype()).getMappingInfo().getQualifiedJavaName() +
			// "PersistenceTest"));
					(((INakedClassifier) c.getSupertype()).getMappingInfo().getQualifiedJavaName() + "PersistenceTest"));
			ifResetting.getThenPart().addToStatements(featureTest.getLast() + ".reset()");
		}
		for (INakedProperty f : c.getOwnedAttributes()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if (map.isOne() && !(f.isDerived() || isReadOnly || f.isInverse()) && f.getNakedBaseType() instanceof INakedEntity) {
				OJPathName featureTest = new OJPathName(
						(((INakedEntity) f.getNakedBaseType()).getMappingInfo().getQualifiedJavaName() + "PersistenceTest"));
				ifResetting.getThenPart().addToStatements(featureTest.getLast() + ".reset()");
			}
		}
		ifResetting.getThenPart().addToStatements("HibernateConfigurator.getInstance().closeEntityManager()");
		ifResetting.getThenPart().addToStatements("isResetting=false");
		reset.getBody().addToStatements(ifResetting);
	}

	protected void addTestInsert(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		if (!(c.getIsAbstract())) {
			OJAnnotatedOperation testInsert = new OJAnnotatedOperation("testInsert");
			test.addToOperations(testInsert);
			testInsert.addToThrows("Exception");
			OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
			atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
			testInsert.putAnnotation(atTest);
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager",new OJPathName("javax.persistence.EntityManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			test.addToImports(entityManager.getType());
			testInsert.getBody().addToLocals(entityManager);
			OJAnnotatedField instance = new OJAnnotatedField("instance",ojClass.getPathName());
			instance.setInitExp("new " + ojClass.getName() + "()");
			testInsert.getBody().addToLocals(instance);
			testInsert.getBody().addToStatements("entityManager.getTransaction().begin()");
			testInsert.getBody().addToStatements("populate(instance)");
			testInsert.getBody().addToStatements("entityManager.persist(instance)");
			testInsert.getBody().addToStatements("entityManager.getTransaction().commit()");
		}
	}

	protected void addTestOptionalFields(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJAnnotatedOperation testOptionalFields = new OJAnnotatedOperation("testOptionalFields");
		test.addToOperations(testOptionalFields);
		testOptionalFields.addToThrows("Exception");
		OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
		atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
		testOptionalFields.putAnnotation(atTest);
		OJAnnotatedField entityManager = new OJAnnotatedField("entityManager",new OJPathName("javax.persistence.EntityManager"));
		entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
		test.addToImports(entityManager.getType());
		testOptionalFields.getBody().addToLocals(entityManager);
		OJAnnotatedField instance = new OJAnnotatedField("instance",ojClass.getPathName());
		instance.setInitExp("null");
		testOptionalFields.getBody().addToLocals(instance);
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().begin()");
		testOptionalFields.getBody().addToStatements("instance=getInstance()");
		for (INakedProperty p : c.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			if (map.isOne() && !(p.isDerived() || p.isReadOnly() || p.isInverse()) && !p.isRequired()) {
				testOptionalFields.getBody().addToStatements(
						"instance." + map.setter() + "(" + calculateDefaultValue(test, testOptionalFields.getBody(), p) + ")");
			}
		}
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().commit()");
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().begin()");
		for (INakedProperty p : c.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			if (map.isOne() && !(p.isDerived() || p.isReadOnly() || p.isInverse()) && !p.isRequired()) {
				testOptionalFields.getBody().addToStatements("instance." + map.setter() + "(null)");
			}
		}
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().commit()");
	}
}
