package net.sf.nakeduml.javageneration.testgeneration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.profiles.INakedProfile;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;

import org.nakeduml.environment.Environment;
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

public class ArquillianTestJavaGenerator extends AbstractJavaProducingVisitor {
	boolean isIntegrationPhase = false;

	public ArquillianTestJavaGenerator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public class PackageAndProcessCollector extends NakedElementOwnerVisitor {
		private Map<INakedNameSpace, INakedClassifier> packages = new HashMap<INakedNameSpace, INakedClassifier>();
		private Set<INakedBehavior> processes = new HashSet<INakedBehavior>();
		private INakedEntity rootEntity;

		public PackageAndProcessCollector(Collection<INakedRootObject> modelInScope) {
			for (INakedRootObject root : modelInScope) {
				visitRecursively(root);
			}
		}

		@VisitBefore(matchSubclasses = true)
		public void visitEntities(INakedClassifier entity) {
			if (OJUtil.hasOJClass(entity) && !(entity.getNameSpace() instanceof INakedProfile)) {
				packages.put(entity.getNameSpace(), entity);
			}
		}

		@VisitBefore(matchSubclasses = true)
		public void visitEntities(INakedEntity entity) {
			if (entity.getIsAbstract() == false && entity.getEndToComposite() == null) {
				for (INakedProperty p : entity.getEffectiveAttributes()) {
					if (p.isComposite()) {
						this.rootEntity = entity;
						break;
					}
				}
			}
		}

		@VisitBefore(matchSubclasses = true)
		public void visitBehavior(INakedBehavior b) {
			if (b.isProcess()) {
				this.processes.add(b);
			}
		}

	}

	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			PackageAndProcessCollector collector = new PackageAndProcessCollector(getModelInScope());
			// Fetch root entity
			OJPathName utilPkg = UtilityCreator.getUtilPathName();
			createTestUtilClass(collector, utilPkg, OutputRootId.ADAPTOR_GEN_TEST_SRC, model.getFileName());
			createExampleTestClass(model.getFileName(), collector.rootEntity, utilPkg, OutputRootId.ADAPTOR_TEST_SRC);
			createExampleStartup(collector, utilPkg, OutputRootId.ADAPTOR_TEST_SRC);
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace workspace) {
		if (isIntegrationPhase) {
			PackageAndProcessCollector collector = new PackageAndProcessCollector(workspace.getRootObjects());
			OJPathName utilPkg = new OJPathName(config.getMavenGroupId() + ".util");
			createExampleStartup(collector, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_SRC);
			createTestUtilClass(collector, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_GEN_TEST_SRC, workspace.getDirectoryName());
			createExampleTestClass(workspace.getDirectoryName(), collector.rootEntity, utilPkg, OutputRootId.INTEGRATED_ADAPTOR_TEST_SRC);
		}
	}

	private void createExampleTestClass(String hibernatePrefix, INakedEntity root, OJPathName pkg, OutputRootId outputRootId) {
		OJAnnotatedClass dummyTest = new OJAnnotatedClass();
		dummyTest
				.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.runner.RunWith"), new OJPathName("org.jboss.arquillian.junit.Arquillian")));
		dummyTest.setName("ExampleIntegrationTest");
		OJAnnotatedPackage owner = findOrCreatePackage(pkg);
		owner.addToClasses(dummyTest);
		super.createTextPath(dummyTest, outputRootId);
		OJAnnotatedOperation createTestArchive = addCreateTestArchive(dummyTest);
		createTestArchive.getBody().addToStatements("return NakedUmlTestUtil.createTestArchive()");
		OJAnnotatedField session = new OJAnnotatedField("session", new OJPathName("org.hibernate.Session"));
		session.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		dummyTest.addToFields(session);
		OJAnnotatedOperation test = new OJAnnotatedOperation("test");
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.Test")));
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.SuppressWarnings"), "unchecked"));
		test.getBody().addToStatements(
				"List<" + root.getMappingInfo().getJavaName() + "> roots = session.createQuery(\"select h from " + root.getMappingInfo().getJavaName()
						+ " h\").list()");
		dummyTest.addToOperations(test);
		dummyTest.addToImports(new OJPathName("java.util.List"));
		dummyTest.addToImports(OJUtil.classifierPathname(root));
		dummyTest.addToImports(new OJPathName("org.junit.Assert"));
		test.getBody().addToStatements("Assert.assertFalse(roots.size()>0)");
	}

	private void addCreateTestArchive(String hibernatePrefix, OJAnnotatedClass dummyTest, Collection<INakedBehavior> processes) {
		OJAnnotatedOperation createTestArchive = addCreateTestArchive(dummyTest);
		dummyTest.addToImports(new OJPathName("org.jboss.shrinkwrap.api.spec.WebArchive"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.test.adaptor.ArquillianUtils"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.test.NakedUtilTestClasses"));
		createTestArchive.getBody().addToStatements("WebArchive war = ArquillianUtils.createWarArchive(false)");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"META-INF/beans.xml\", \"beans.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"" + hibernatePrefix + "-hibernate.cfg.xml\", \"classes/hibernate.cfg.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"data.generation.properties\", \"data.generation.properties\")");
		for (INakedBehavior p : processes) {
			createTestArchive.getBody().addToStatements(
					"war.addWebResource(\"" + p.getMappingInfo().getJavaPath() + ".rf\", \"" + p.getMappingInfo().getJavaPath() + ".rf\")");
		}
		createTestArchive.getBody().addToStatements("war.addPackages(true, NakedUtilTestClasses.getTestPackages())");
		createTestArchive.getBody().addToStatements("war.addPackages(true, getTestPackages())");
		createTestArchive.getBody().addToStatements("war.addWebResource(Environment.PROPERTIES_FILE_NAME, Environment.PROPERTIES_FILE_NAME)");
		if (isIntegrationPhase) {
			createTestArchive.getBody().addToStatements("war.addManifestResource(\"hornetq-jms.xml\")");
		}
		dummyTest.addToImports("org.nakeduml.environment.Environment");
		createTestArchive.getBody().addToStatements("return war");
	}

	private OJAnnotatedOperation addCreateTestArchive(OJAnnotatedClass dummyTest) {
		OJAnnotatedOperation createTestArchive = new OJAnnotatedOperation("createTestArchive");
		createTestArchive.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.arquillian.api.Deployment")));
		dummyTest.addToOperations(createTestArchive);
		createTestArchive.setStatic(true);
		OJPathName archive = new OJPathName("org.jboss.shrinkwrap.api.Archive");
		archive.addToElementTypes(new OJPathName("?"));
		createTestArchive.setReturnType(archive);
		OJPathName ioExceptionPath = new OJPathName(IOException.class.getName());
		dummyTest.addToImports(ioExceptionPath);
		OJPathName illegalArgumentException = new OJPathName(IllegalArgumentException.class.getName());
		createTestArchive.setThrows(new HashSet<OJPathName>(Arrays.asList(new OJPathName(ClassNotFoundException.class.getName()), ioExceptionPath,
				illegalArgumentException)));
		return createTestArchive;
	}

	private OJAnnotatedClass createTestUtilClass(PackageAndProcessCollector collector, OJPathName packageName, OutputRootId outputRootId, String hibernatePrefix) {
		OJAnnotatedClass testUtil = new OJAnnotatedClass();
		testUtil.setName("NakedUmlTestUtil");
		OJAnnotatedPackage owner = findOrCreatePackage(packageName);
		owner.addToClasses(testUtil);
		addCreateTestArchive(hibernatePrefix, testUtil, collector.processes);
		super.createTextPath(testUtil, outputRootId);
		addGetTestPackagesOper(testUtil, collector.packages.values());
		return testUtil;
	}

	private void addGetTestPackagesOper(OJAnnotatedClass baseTest, Collection<INakedClassifier> collection) {
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
		for (INakedClassifier c : collection) {
			OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(" + OJUtil.classifierPathname(c) + ".class.getPackage())");
			getTestPackages.getBody().addToStatements(addPackage);
		}
		if (isIntegrationPhase) {
			Collection<INakedRootObject> pro = workspace.getPrimaryRootObjects();
			for (INakedRootObject r : pro) {
				if (r instanceof INakedModel) {
					OJPathName utilPath = calculateUtilPath(r);
					OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(" + utilPath + ".Stdlib.class.getPackage())");
					getTestPackages.getBody().addToStatements(addPackage);
				}
			}
		} else {
			baseTest.addToImports(UtilityCreator.getUtilPathName().append("Stdlib"));
			OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(Stdlib.class.getPackage())");
			getTestPackages.getBody().addToStatements(addPackage);
		}
		OJSimpleStatement addPackage = new OJSimpleStatement("packages.add(NakedUmlTestUtil.class.getPackage())");
		getTestPackages.getBody().addToStatements(addPackage);
		getTestPackages.getBody().addToStatements("Package[] result = new Package[packages.size()]");
		getTestPackages.getBody().addToStatements("packages.toArray(result)");
		getTestPackages.getBody().addToStatements("return result");
	}

	private void createExampleStartup(PackageAndProcessCollector collector, OJPathName utilPkg, OutputRootId outputRootId) {
		OJAnnotatedClass startUp = new OJAnnotatedClass();
		startUp.setName("ExampleStartUp");
		OJAnnotatedPackage owner = findOrCreatePackage(utilPkg);
		owner.addToClasses(startUp);
		super.createTextPath(startUp, outputRootId);
		OJPathName rootPathname = OJUtil.classifierPathname(collector.rootEntity);
		startUp.addToImports(rootPathname);
		addClassFields(startUp, collector.rootEntity);
		addStartOperation(startUp, collector.rootEntity);
	}

	private void addClassFields(OJAnnotatedClass startUp, INakedEntity root) {
		OJAnnotatedField session = new OJAnnotatedField("session", new OJPathName("org.hibernate.Session"));
		session.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		OJPathName dependent = new OJPathName("org.nakeduml.seam3.persistence.DependentScopedSession");
		session.addAnnotationIfNew(new OJAnnotationValue(dependent));
		startUp.addToImports(dependent);
		startUp.addToFields(session);
		OJAnnotatedField transaction = new OJAnnotatedField("transaction", new OJPathName("org.jboss.seam.transaction.SeamTransaction"));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.transaction.DefaultTransaction")));
		startUp.addToFields(transaction);
		OJAnnotatedField dataGeneratorProperty = new OJAnnotatedField("dataGeneratorProperty", new OJPathName(
				"org.nakeduml.runtime.adaptor.DataGeneratorProperty"));
		dataGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(dataGeneratorProperty);
		OJAnnotatedField rootGeneratorProperty = new OJAnnotatedField("rootDataGenerator", new OJPathName(root.getMappingInfo().getQualifiedJavaName()
				+ "DataGenerator"));
		rootGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(rootGeneratorProperty);
	}

	private OJAnnotatedOperation addStartOperation(OJAnnotatedClass startUp, INakedEntity root) {
		OJPathName rootPathname = OJUtil.classifierPathname(root);
		OJAnnotatedOperation start = new OJAnnotatedOperation("start");
		if (isIntegrationPhase) {
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
