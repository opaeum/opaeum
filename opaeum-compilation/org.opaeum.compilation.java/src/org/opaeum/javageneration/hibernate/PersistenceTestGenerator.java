package org.opaeum.javageneration.hibernate;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {HibernateAnnotator.class},after = {HibernateAnnotator.class})
public class PersistenceTestGenerator extends AbstractTestDataGenerator{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(Classifier c){
		if((isPersistent(c) /* || c instanceof Interface */) && OJUtil.hasOJClass(c)){
			if(ojUtil.requiresJavaRename( c)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, new OJPathName(ojUtil.classifierPathname(c) + "PersistenceTest"));
			}
			OJAnnotatedClass ojClass = findJavaClass(c);
			String name = ojClass.getName();
			String testName = name + "PersistenceTest";
			OJAnnotatedClass test = new OJAnnotatedClass(testName);
			test.addToImports(UtilityCreator.getUtilPathName() + ".HibernateConfigurator");
			OJPackage testPackage = ojClass.getMyPackage();
			testPackage.addToClasses(test);
			super.createTextPath(test, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC);
			Classifier nc = c;
			addPopulate(ojClass, test, nc);
			OJAnnotatedField instance = new OJAnnotatedField("instance", ojClass.getPathName());
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
	protected String getTestDataName(Classifier child){
		return ojUtil.classifierPathname(child).getLast() + "PersistenceTest";
	}
	protected void addPopulate(OJAnnotatedClass ojClass,OJAnnotatedClass test,Classifier c){
		OJOperation populate = new OJAnnotatedOperation("populate");
		populate.addToThrows("Exception");
		test.addToOperations(populate);
		populate.setStatic(true);
		populate.addParam("instance", ojClass.getPathName());
		if(c.getGeneralizations().size() > 0){
			OJPathName superType = getTestDataPath(c.getGeneralizations().get(0).getGeneral());
			test.addToImports(superType);
			populate.getBody().addToStatements(superType.getLast() + ".populate(instance)");
		}
		for(Property f:getLibrary().getEffectiveAttributes(c)){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof Property && (f).isReadOnly());
			if(f.getOwner() == c || (c instanceof BehavioredClassifier && ((BehavioredClassifier) c).getImplementedInterfaces().contains(f.getOwner()))){
				// do properties for directly implemented interfaces too.
				if(map.isOne() && !(f.isDerived() || isReadOnly || EmfPropertyUtil.isInverse(f)) && EmfPropertyUtil.isRequired(f)){
					String defaultValue = calculateDefaultValue(test, populate.getBody(), f);
					populate.getBody().addToStatements("instance." + map.setter() + "(" + defaultValue + ")");
				}
			}
		}
		test.addToOperations(populate);
	}
	protected void addGetInstance(OJAnnotatedClass ojClass,OJAnnotatedClass test,Classifier c){
		OJOperation getInstance = new OJAnnotatedOperation("getInstance");
		test.addToOperations(getInstance);
		getInstance.addToThrows("Exception");
		getInstance.setStatic(true);
		getInstance.setReturnType(ojClass.getPathName());
		test.addToOperations(getInstance);
		OJIfStatement ifNull = new OJIfStatement();
		ifNull.setCondition("instance==null");
		if(c.isAbstract()){
			Collection<? extends Classifier> subClasses = getConcreteSubclassifiersOf(c);
			if(subClasses.size() > 0){
				Classifier child = subClasses.iterator().next();
				OJPathName testPath = getTestDataPath(child);
				test.addToImports(testPath);
				ifNull.getThenPart().addToStatements("instance=" + testPath.getLast() + ".getInstance()");
			}else{
				ifNull.getThenPart().addToStatements("throw new RuntimeException(\"Class " + c.getName() + " has no concrete implementations\")");
			}
		}else{
			ifNull.getThenPart().addToStatements("instance=new " + ojClass.getName() + "()");
			ifNull.getThenPart().addToStatements("populate(instance)");
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager", new OJPathName("javax.persistence.ClassManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getClassManager()");
			ifNull.getThenPart().addToLocals(entityManager);
			ifNull.getThenPart().addToStatements("entityManager.persist(instance)");
		}
		// ifNull.getThenPart().addToStatements("entityManager.flush()"); CAuses
		// hibernate to hang????
		getInstance.getBody().addToStatements(ifNull);
		getInstance.getBody().addToStatements("return instance");
	}
	protected void addCreateNew(OJAnnotatedClass ojClass,OJAnnotatedClass test,Classifier c){
		OJOperation createNew = new OJAnnotatedOperation("createNew");
		createNew.addToThrows("Exception");
		test.addToOperations(createNew);
		createNew.setStatic(true);
		createNew.setReturnType(ojClass.getPathName());
		test.addToOperations(createNew);
		Collection<? extends Classifier> subClasses = EmfClassifierUtil.getAllSubClassifiers(c, getModelInScope());
		if(c.isAbstract()){
			if(subClasses.size() > 0){
				// TODO invalid
				Classifier child = subClasses.iterator().next();
				OJPathName testPath = getTestDataPath(child);
				createNew.getBody().addToStatements("return " + testPath.getLast() + ".createNew()");
			}else{
				createNew.getBody().addToStatements("throw new RuntimeException(\"Class " + c.getName() + " has no concrete implementations\")");
			}
		}else{
			OJAnnotatedField newInstance = new OJAnnotatedField("newInstance", ojClass.getPathName());
			newInstance.setInitExp("new " + ojClass.getName() + "()");
			createNew.getBody().addToLocals(newInstance);
			createNew.getBody().addToStatements("populate(newInstance)");
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager", new OJPathName("javax.persistence.ClassManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getClassManager()");
			createNew.getBody().addToLocals(entityManager);
			createNew.getBody().addToStatements("entityManager.persist(newInstance)");
			createNew.getBody().addToStatements("return newInstance");
		}
	}
	protected void addReset(OJAnnotatedClass test,Classifier c){
		OJAnnotatedField isResetting = new OJAnnotatedField("isResetting", new OJPathName("boolean"));
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
		if(c.getGenerals().size() >= 1){
			Classifier supertype = (Classifier) c.getGenerals().get(0);
			OJPathName featureTest = new OJPathName((ojUtil.classifierPathname(supertype) + "PersistenceTest"));
			ifResetting.getThenPart().addToStatements(featureTest.getLast() + ".reset()");
		}
		for(Property f:c.getAttributes()){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof Property && (f).isReadOnly());
			if(map.isOne() && !(f.isDerived() || isReadOnly || EmfPropertyUtil.isInverse(f)) && f.getType() instanceof Class){
				OJPathName featureTest = new OJPathName(ojUtil.classifierPathname(f.getType()) + "PersistenceTest");
				ifResetting.getThenPart().addToStatements(featureTest.getLast() + ".reset()");
			}
		}
		ifResetting.getThenPart().addToStatements("HibernateConfigurator.getInstance().closeClassManager()");
		ifResetting.getThenPart().addToStatements("isResetting=false");
		reset.getBody().addToStatements(ifResetting);
	}
	protected void addTestInsert(OJAnnotatedClass ojClass,OJAnnotatedClass test,Classifier c){
		if(!(c.isAbstract())){
			OJAnnotatedOperation testInsert = new OJAnnotatedOperation("testInsert");
			test.addToOperations(testInsert);
			testInsert.addToThrows("Exception");
			OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
			atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
			testInsert.putAnnotation(atTest);
			OJAnnotatedField entityManager = new OJAnnotatedField("entityManager", new OJPathName("javax.persistence.ClassManager"));
			entityManager.setInitExp("HibernateConfigurator.getInstance().getClassManager()");
			test.addToImports(entityManager.getType());
			testInsert.getBody().addToLocals(entityManager);
			OJAnnotatedField instance = new OJAnnotatedField("instance", ojClass.getPathName());
			instance.setInitExp("new " + ojClass.getName() + "()");
			testInsert.getBody().addToLocals(instance);
			testInsert.getBody().addToStatements("entityManager.getTransaction().begin()");
			testInsert.getBody().addToStatements("populate(instance)");
			testInsert.getBody().addToStatements("entityManager.persist(instance)");
			testInsert.getBody().addToStatements("entityManager.getTransaction().commit()");
		}
	}
	protected void addTestOptionalFields(OJAnnotatedClass ojClass,OJAnnotatedClass test,Classifier c){
		OJAnnotatedOperation testOptionalFields = new OJAnnotatedOperation("testOptionalFields");
		test.addToOperations(testOptionalFields);
		testOptionalFields.addToThrows("Exception");
		OJAnnotationValue atTest = new OJAnnotationValue(new OJPathName("org.testng.annotations.Test"));
		atTest.putAttribute(new OJAnnotationAttributeValue("groups", "persistence"));
		testOptionalFields.putAnnotation(atTest);
		OJAnnotatedField entityManager = new OJAnnotatedField("entityManager", new OJPathName("javax.persistence.ClassManager"));
		entityManager.setInitExp("HibernateConfigurator.getInstance().getClassManager()");
		test.addToImports(entityManager.getType());
		testOptionalFields.getBody().addToLocals(entityManager);
		OJAnnotatedField instance = new OJAnnotatedField("instance", ojClass.getPathName());
		instance.setInitExp("null");
		testOptionalFields.getBody().addToLocals(instance);
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().begin()");
		testOptionalFields.getBody().addToStatements("instance=getInstance()");
		for(Property p:getLibrary().getEffectiveAttributes(c)){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
			if(map.isOne() && !(p.isDerived() || p.isReadOnly() || EmfPropertyUtil.isInverse(p)) && !EmfPropertyUtil.isRequired(p)){
				testOptionalFields.getBody().addToStatements(
						"instance." + map.setter() + "(" + calculateDefaultValue(test, testOptionalFields.getBody(), p) + ")");
			}
		}
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().commit()");
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().begin()");
		for(Property p:getLibrary().getEffectiveAttributes(c)){
			StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
			if(map.isOne() && !(p.isDerived() || p.isReadOnly() || EmfPropertyUtil.isInverse(p)) && !EmfPropertyUtil.isRequired(p)){
				testOptionalFields.getBody().addToStatements("instance." + map.setter() + "(null)");
			}
		}
		testOptionalFields.getBody().addToStatements("entityManager.getTransaction().commit()");
	}
}
