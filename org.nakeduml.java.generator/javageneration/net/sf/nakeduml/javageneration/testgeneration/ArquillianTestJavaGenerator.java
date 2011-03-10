package net.sf.nakeduml.javageneration.testgeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedParameter;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

public class ArquillianTestJavaGenerator extends AbstractJavaProducingVisitor{
	boolean isIntegrationPhase = false;
	public ArquillianTestJavaGenerator(boolean isIntegrationPhase){
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}
	public class PackgeCollector extends NakedElementOwnerVisitor{
		private Set<INakedPackage> packages = new HashSet<INakedPackage>();
		private INakedEntity rootEntity;
		@VisitBefore(matchSubclasses = true)
		public void visitEntities(INakedEntity entity){
			packages.add((INakedPackage) entity.getNameSpace());
			if(entity.getIsAbstract() == false && entity.getEndToComposite() == null){
				for(INakedProperty p:entity.getEffectiveAttributes()){
					if(p.isComposite()){
						this.rootEntity = entity;
						break;
					}
				}
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel model){
		if(!isIntegrationPhase){
			PackgeCollector collector = new PackgeCollector();
			for(INakedRootObject root:getModelInScope()){
				collector.visitRecursively(root);
			}
			// Fetch root entity
			OJPathName utilPkg = UtilityCreator.getUtilPathName();
			OJAnnotatedClass baseTest = createBaseTestClass(collector.packages, utilPkg, OutputRootId.ADAPTOR_GEN_TEST_SRC);
			createExampleTestClass(baseTest, model.getFileName(), collector.rootEntity, utilPkg, OutputRootId.ADAPTOR_TEST_SRC);
			createStartOperation(collector.rootEntity, utilPkg, OutputRootId.ADAPTOR_TEST_SRC);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace workspace){
		if(isIntegrationPhase){
			PackgeCollector collector = new PackgeCollector();
			for(INakedRootObject root:(ArrayList<INakedRootObject>) workspace.getOwnedElements()){
				collector.visitRecursively(root);
			}
			OJPathName utilPkg = new OJPathName(config.getMavenGroupId() + ".util");
			createBaseTestClass(collector.packages, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_GEN_TEST_SRC);
			createStartOperation(collector.rootEntity, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_SRC);
			OJAnnotatedClass baseTest = createBaseTestClass(collector.packages, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_GEN_TEST_SRC);
			createExampleTestClass(baseTest, workspace.getDirectoryName(), collector.rootEntity, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_TEST_SRC);
		}
	}
	private void createExampleTestClass(OJAnnotatedClass baseTest,String hibernatePrefix, INakedEntity root,OJPathName pkg,OutputRootId outputRootId){
		OJAnnotatedClass dummyTest = new OJAnnotatedClass();
		dummyTest.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.runner.RunWith"),new OJPathName("org.jboss.arquillian.junit.Arquillian")));
		dummyTest.setSuperclass(baseTest.getPathName());
		dummyTest.setName("ExampleIntegrationTest");
		OJAnnotatedPackage owner = findOrCreatePackage(pkg);
		owner.addToClasses(dummyTest);
		super.createTextPath(dummyTest, outputRootId);
		OJAnnotatedField session = new OJAnnotatedField("session", new OJPathName("org.hibernate.Session"));
		session.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		dummyTest.addToFields(session);
		OJAnnotatedOperation createTestArchive = new OJAnnotatedOperation("createTestArchive");
		dummyTest.addToOperations(createTestArchive);
		createTestArchive.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.arquillian.api.Deployment")));
		createTestArchive.setStatic(true);
		OJPathName archive = new OJPathName("org.jboss.shrinkwrap.api.Archive");
		archive.addToElementTypes(new OJPathName("?"));
		createTestArchive.setReturnType(archive);
		OJPathName ioExceptionPath = new OJPathName(IOException.class.getName());
		dummyTest.addToImports(ioExceptionPath);
		OJPathName illegalArgumentException = new OJPathName(IllegalArgumentException.class.getName());
		createTestArchive.setThrows(new HashSet<OJPathName>(Arrays.asList(new OJPathName(ClassNotFoundException.class.getName()), ioExceptionPath,
				illegalArgumentException)));
		dummyTest.addToImports(new OJPathName("org.jboss.shrinkwrap.api.spec.WebArchive"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.test.adaptor.ArquillianUtils"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.test.NakedUtilTestClasses"));
		createTestArchive.getBody().addToStatements("WebArchive war = ArquillianUtils.createWarArchive(false)");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"META-INF/beans.xml\", \"beans.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"" + hibernatePrefix + "-hibernate.cfg.xml\", \"classes/hibernate.cfg.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"data.generation.properties\", \"data.generation.properties\")");
		createTestArchive.getBody().addToStatements("war.addPackages(true, NakedUtilTestClasses.getTestPackages())");
		createTestArchive.getBody().addToStatements("war.addPackages(true, getTestPackages())");
		createTestArchive.getBody().addToStatements("return war");
		OJAnnotatedOperation test = new OJAnnotatedOperation("test");
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.Test")));
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.SuppressWarnings"), "unchecked"));
		test.getBody().addToStatements(
				"List<" + root.getMappingInfo().getJavaName() + "> roots = session.createQuery(\"select h from " + root.getMappingInfo().getJavaName() + " h\").list()");
		dummyTest.addToOperations(test);
		dummyTest.addToImports(new OJPathName("java.util.List"));
		dummyTest.addToImports(OJUtil.classifierPathname(root));
		dummyTest.addToImports(new OJPathName("org.junit.Assert"));
		test.getBody().addToStatements("Assert.assertFalse(roots.size()>0)");
	}
	private OJAnnotatedClass createBaseTestClass(Collection<INakedPackage> packages,OJPathName packageName,OutputRootId outputRootId){
		OJAnnotatedClass baseTest = new OJAnnotatedClass();
		baseTest.setName("BaseTest");
		OJAnnotatedPackage owner = findOrCreatePackage(packageName);
		owner.addToClasses(baseTest);
		super.createTextPath(baseTest, outputRootId);
		createGetTestPackagesOper(baseTest, packages);
		return baseTest;
	}
	private void createGetTestPackagesOper(OJAnnotatedClass baseTest,Collection<INakedPackage> packages){
		baseTest.addToImports(new OJPathName("java.lang.Package"));
		OJPathName list = new OJPathName("java.util.List");
		baseTest.addToImports(list);
		OJAnnotatedOperation getTestPackages = new OJAnnotatedOperation("getTestPackages");
		getTestPackages.setReturnType(new OJPathName("java.lang.Package[]"));
		baseTest.addToOperations(getTestPackages);
		getTestPackages.setVisibility(OJVisibilityKind.PUBLIC);
		getTestPackages.setStatic(true);
		OJPathName ioExceptionPath = new OJPathName(IOException.class.getName());
		getTestPackages.setThrows(new HashSet<OJPathName>(Arrays.asList(new OJPathName(ClassNotFoundException.class.getName()), ioExceptionPath)));
		baseTest.addToImports(ioExceptionPath);
		OJField classes = new OJField();
		classes.setName("packages");
		OJPathName classPath = new OJPathName("java.lang.Package");
		list.addToElementTypes(classPath);
		classes.setType(list);
		classes.setInitExp("new ArrayList<Package>()");
		baseTest.addToImports(new OJPathName("java.util.ArrayList"));
		getTestPackages.getBody().addToLocals(classes);
		for(INakedPackage p:packages){
			String entityName = "";
			for(IClassifier c:p.getClassifiers()){
				if(c instanceof INakedEntity){
					entityName = c.getName();
					baseTest.addToImports(OJUtil.classifierPathname((INakedEntity) c));
					break;
				}
			}
			OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(" + entityName + ".class.getPackage())");
			getTestPackages.getBody().addToStatements(addPackage);
		}
		baseTest.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
		OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(Stdlib.class.getPackage())");
		getTestPackages.getBody().addToStatements(addPackage);
		addPackage = new OJSimpleStatement("packages.add(BaseTest.class.getPackage())");
		getTestPackages.getBody().addToStatements(addPackage);
		getTestPackages.getBody().addToStatements("Package[] result = new Package[packages.size()]");
		getTestPackages.getBody().addToStatements("packages.toArray(result)");
		getTestPackages.getBody().addToStatements("return result");
	}
	private void createStartOperation(INakedEntity root,OJPathName utilPkg,OutputRootId outputRootId){
		OJAnnotatedClass startUp = new OJAnnotatedClass();
		startUp.setName("ExampleStartUp");
		OJAnnotatedPackage owner = findOrCreatePackage(utilPkg);
		owner.addToClasses(startUp);
		super.createTextPath(startUp, outputRootId);
		OJPathName rootPathname = OJUtil.classifierPathname(root);
		startUp.addToImports(rootPathname);
		addClassFields(startUp, root);
		addStartOperation(startUp, root);
	}
	private void addClassFields(OJAnnotatedClass startUp,INakedEntity root){
		OJAnnotatedField session = new OJAnnotatedField("session", new OJPathName("org.hibernate.Session"));
		session.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		OJPathName dependent = new OJPathName("org.nakeduml.seam3.persistence.DependentScopedSession");
		session.addAnnotationIfNew(new OJAnnotationValue(dependent));
		startUp.addToImports(dependent);
		startUp.addToFields(session);
		OJAnnotatedField transaction = new OJAnnotatedField("transaction", new OJPathName("org.jboss.seam.persistence.transaction.SeamTransaction"));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.persistence.transaction.DefaultTransaction")));
		startUp.addToFields(transaction);
		OJAnnotatedField dataGeneratorProperty = new OJAnnotatedField("dataGeneratorProperty", new OJPathName("org.nakeduml.runtime.adaptor.DataGeneratorProperty"));
		dataGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(dataGeneratorProperty);
		OJAnnotatedField rootGeneratorProperty = new OJAnnotatedField("rootDataGenerator", new OJPathName(root.getMappingInfo().getQualifiedJavaName() + "DataGenerator"));
		rootGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(rootGeneratorProperty);
	}
	private OJAnnotatedOperation addStartOperation(OJAnnotatedClass startUp,INakedEntity root){
		OJPathName rootPathname = OJUtil.classifierPathname(root);
		OJAnnotatedOperation start = new OJAnnotatedOperation("start");
		if(isIntegrationPhase){
			OJAnnotatedParameter param = new OJAnnotatedParameter("webapp", new OJPathName("org.jboss.seam.servlet.WebApplication"));
			OJPathName observes = new OJPathName("javax.enterprise.event.Observes");
			startUp.addToImports(observes);
			param.addAnnotationIfNew(new OJAnnotationValue(observes));
			OJPathName started = new OJPathName("org.jboss.seam.servlet.event.Started");
			startUp.addToImports(started);
			param.addAnnotationIfNew(new OJAnnotationValue(started));
			start.addToParameters(param);
		}
		startUp.addToOperations(start);
		OJTryStatement ojTryStatement = new OJTryStatement();
		start.getBody().addToStatements(ojTryStatement);
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		OJBlock tryBlock = new OJBlock();
		ojTryStatement.setTryPart(tryBlock);
		OJSimpleStatement ojSimpleStatement = new OJSimpleStatement("transaction.begin()");
		tryBlock.addToStatements(ojSimpleStatement);
		OJField rootField = new OJField();
		rootField.setName("the" + root.getMappingInfo().getJavaName().toString());
		rootField.setType(rootPathname);
		String rootName = root.getMappingInfo().getJavaName().toString();
		rootField.setInitExp("(" + rootName + ")session.createQuery(\"from " + rootName
				+ " a where a.name = :name\").setText(\"name\", dataGeneratorProperty.getProperty(\"" + rootName.toLowerCase() + ".name_0\")).uniqueResult()");
		OJIfStatement ifStatement = new OJIfStatement();
		ifStatement.setCondition(rootField.getName() + " == null");
		OJBlock ifBlock = new OJBlock();
		OJField generatedRoots = new OJField();
		generatedRoots.setName(root.getMappingInfo().getJavaName().getPlural().getDecapped().toString());
		OJPathName list = new OJPathName("java.util.List");
		startUp.addToImports(list);
		list.addToElementTypes(rootPathname);
		generatedRoots.setType(list);
		generatedRoots.setInitExp("rootDataGenerator.create" + rootName + "()");
		ifBlock.addToLocals(generatedRoots);
		ifStatement.setThenPart(ifBlock);
		tryBlock.addToLocals(rootField);
		tryBlock.addToStatements(ifStatement);
		OJForStatement forRoots = new OJForStatement(rootName.toLowerCase(), rootPathname, generatedRoots.getName());
		ifBlock.addToStatements(forRoots);
		OJSimpleStatement persist = new OJSimpleStatement("session.persist(" + rootName.toLowerCase() + ")");
		forRoots.getBody().addToStatements(persist);
		OJSimpleStatement populate = new OJSimpleStatement("rootDataGenerator.populate" + rootName + "(" + generatedRoots.getName() + ")");
		ifBlock.addToStatements(populate);
		OJSimpleStatement flush = new OJSimpleStatement("session.flush()");
		ifBlock.addToStatements(flush);
		OJSimpleStatement commit = new OJSimpleStatement("transaction.commit()");
		ifBlock.addToStatements(commit);
		OJBlock catchBlock = new OJBlock();
		ojTryStatement.setCatchPart(catchBlock);
		OJTryStatement catchTry = new OJTryStatement();
		catchBlock.addToStatements(catchTry);
		OJBlock catchTryBlock = new OJBlock();
		catchTry.setTryPart(catchTryBlock);
		catchTryBlock.addToStatements("transaction.rollback()");
		catchTry.setCatchParam(new OJParameter("e1", new OJPathName("java.lang.Exception")));
		OJBlock tryCatchCatch = new OJBlock();
		tryCatchCatch.addToStatements("throw new RuntimeException(e1)");
		catchTry.setCatchPart(tryCatchCatch);
		catchBlock.addToStatements("throw new RuntimeException(e)");
		return start;
	}
}
