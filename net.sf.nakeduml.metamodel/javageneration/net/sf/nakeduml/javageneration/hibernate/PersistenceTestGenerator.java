package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractTestDataGenerator;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class PersistenceTestGenerator extends AbstractTestDataGenerator {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if ((isPersistent(c) /* || c instanceof INakedInterface */) && OJUtil.hasOJClass(c)) {
			OJAnnotatedClass test = new OJAnnotatedClass();
			test.addToImports(UtilityCreator.getUtilPathName() + ".HibernateConfigurator");
			OJAnnotatedClass ojClass = findJavaClass(c);
			String name = ojClass.getName();
			String testName = name + "PersistenceTest";
			test.setName(testName);
			OJPackage testPackage = ojClass.getMyPackage();
			testPackage.addToClasses(test);
			super.createTextPath(test, JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC);
			INakedClassifier nc = c;
			addPopulate(ojClass, test, nc);
			OJAnnotatedField instance = new OJAnnotatedField();
			instance.setStatic(true);
			instance.setName("instance");
			instance.setType(ojClass.getPathName());
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
		OJOperation populate = new OJAnnotatedOperation();
		populate.addToThrows("Exception");
		test.addToOperations(populate);
		populate.setStatic(true);
		populate.setName("populate");
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
		OJOperation getInstance = new OJAnnotatedOperation();
		test.addToOperations(getInstance);
		getInstance.addToThrows("Exception");
		getInstance.setStatic(true);
		getInstance.setName("getInstance");
		getInstance.setReturnType(ojClass.getPathName());
		test.addToOperations(getInstance);
		OJIfStatement ifNull = new OJIfStatement();
		ifNull.setCondition("instance==null");
		if (c.getIsAbstract()) {
			Collection<? extends INakedClassifier> subClasses = getConcreteImplementations(c);
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
			OJAnnotatedField entityManager = new OJAnnotatedField();
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			entityManager.setName("entityManager");
			entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
			ifNull.getThenPart().addToLocals(entityManager);
			ifNull.getThenPart().addToStatements("entityManager.persist(instance)");
		}
		// ifNull.getThenPart().addToStatements("entityManager.flush()"); CAuses
		// hibernate to hang????
		getInstance.getBody().addToStatements(ifNull);
		getInstance.getBody().addToStatements("return instance");
	}

	protected void addCreateNew(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJOperation createNew = new OJAnnotatedOperation();
		createNew.addToThrows("Exception");
		test.addToOperations(createNew);
		createNew.setStatic(true);
		createNew.setName("createNew");
		createNew.setReturnType(ojClass.getPathName());
		test.addToOperations(createNew);
		Collection<? extends INakedClassifier> subClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(c,getModelInScope());
		if (c.getIsAbstract()) {
			if (subClasses.size() > 0) {
				INakedClassifier child = subClasses.iterator().next();
				OJPathName testPath = getTestDataPath(child);
				createNew.getBody().addToStatements("return " + testPath.getLast() + ".createNew()");
			}else{
				createNew.getBody().addToStatements("throw new RuntimeException(\"Entity "+ c.getName() +" has no concrete implementations\")");
			}
		} else {
			OJAnnotatedField newInstance = new OJAnnotatedField();
			newInstance.setName("newInstance");
			newInstance.setInitExp("new " + ojClass.getName() + "()");
			newInstance.setType(ojClass.getPathName());
			createNew.getBody().addToLocals(newInstance);
			createNew.getBody().addToStatements("populate(newInstance)");
			OJAnnotatedField entityManager = new OJAnnotatedField();
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			entityManager.setName("entityManager");
			entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
			createNew.getBody().addToLocals(entityManager);
			createNew.getBody().addToStatements("entityManager.persist(newInstance)");
			createNew.getBody().addToStatements("return newInstance");
		}
	}

	protected void addReset(OJAnnotatedClass test, INakedClassifier c) {
		OJAnnotatedField isResetting = new OJAnnotatedField();
		isResetting.setName("isResetting");
		isResetting.setInitExp("false");
		isResetting.setStatic(true);
		isResetting.setType(new OJPathName("boolean"));
		test.addToFields(isResetting);
		OJAnnotatedOperation reset = new OJAnnotatedOperation();
		test.addToOperations(reset);
		reset.setStatic(true);
		OJAnnotationValue afterTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.AfterMethod"));
		afterTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
		afterTest.putAttribute(new OJAnnotationAttributeValue("alwaysRun", true));
		reset.putAnnotation(afterTest);
		reset.setName("reset");
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
			OJAnnotatedOperation testInsert = new OJAnnotatedOperation();
			test.addToOperations(testInsert);
			testInsert.setName("testInsert");
			testInsert.addToThrows("Exception");
			OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
			atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
			testInsert.putAnnotation(atTest);
			OJAnnotatedField entityManager = new OJAnnotatedField();
			entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
			entityManager.setName("entityManager");
			entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
			test.addToImports(entityManager.getType());
			testInsert.getBody().addToLocals(entityManager);
			OJAnnotatedField instance = new OJAnnotatedField();
			instance.setName("instance");
			instance.setType(ojClass.getPathName());
			instance.setInitExp("new " + ojClass.getName() + "()");
			testInsert.getBody().addToLocals(instance);
			testInsert.getBody().addToStatements("entityManager.getTransaction().begin()");
			testInsert.getBody().addToStatements("populate(instance)");
			testInsert.getBody().addToStatements("entityManager.persist(instance)");
			testInsert.getBody().addToStatements("entityManager.getTransaction().commit()");
		}
	}

	protected void addTestOptionalFields(OJAnnotatedClass ojClass, OJAnnotatedClass test, INakedClassifier c) {
		OJAnnotatedOperation testOptionalFields = new OJAnnotatedOperation();
		test.addToOperations(testOptionalFields);
		testOptionalFields.setName("testOptionalFields");
		testOptionalFields.addToThrows("Exception");
		OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
		atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
		testOptionalFields.putAnnotation(atTest);
		OJAnnotatedField entityManager = new OJAnnotatedField();
		entityManager.setInitExp("HibernateConfigurator.getInstance().getEntityManager()");
		entityManager.setName("entityManager");
		entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
		test.addToImports(entityManager.getType());
		testOptionalFields.getBody().addToLocals(entityManager);
		OJAnnotatedField instance = new OJAnnotatedField();
		instance.setName("instance");
		instance.setType(ojClass.getPathName());
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
