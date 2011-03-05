package net.sf.nakeduml.javageneration.testgeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedParameter;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;

import org.nakeduml.projectgeneration.DefaultConfigGenerationPhase;

@StepDependency(phase = DefaultConfigGenerationPhase.class, requires = { TextFileGenerator.class }, before = { TextFileGenerator.class })
public class ArquillianTestGenerator extends AbstractJavaProducingVisitor {
	boolean isIntegrationPhase = false;

	public ArquillianTestGenerator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public class PackgeCollector extends NakedElementOwnerVisitor {
		private Set<INakedPackage> packages = new HashSet<INakedPackage>();
		private INakedEntity rootEntity;

		@VisitBefore(matchSubclasses = true)
		public void visitEntities(INakedEntity entity) {
			packages.add((INakedPackage) entity.getNameSpace());
			if (entity.getIsAbstract() == false && entity.getEndToComposite() == null) {
				for (INakedProperty p : entity.getEffectiveAttributes()) {
					if (p.isComposite()) {
						this.rootEntity = entity;
						break;
					}
				}
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			PackgeCollector collector = new PackgeCollector();
			for (INakedRootObject root : model.getDependencies()) {
				collector.visitRecursively(root);
			}
			collector.visitRecursively(model);
			// Fetch root entity
			OJAnnotatedClass baseTest = createBaseTestClass(collector.packages,UtilityCreator.getUtilPathName());
			createDummyTestClass(baseTest, collector.rootEntity, UtilityCreator.getUtilPathName());
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace model) {
		if (isIntegrationPhase) {
			PackgeCollector collector = new PackgeCollector();
			for (INakedRootObject root : (ArrayList<INakedRootObject>) model.getOwnedElements()) {
				collector.visitRecursively(root);
			}
			OJPathName utilPkg = new OJPathName(config.getMavenGroupId() + ".util");
			createStartOperation(collector.rootEntity,utilPkg);
			OJAnnotatedClass baseTest = createBaseTestClass(collector.packages, utilPkg);
			createDummyTestClass(baseTest, collector.rootEntity, utilPkg);
		}
	}

	private void createDummyTestClass(OJAnnotatedClass baseTest, INakedEntity root, OJPathName pkg) {
		OJAnnotatedClass dummyTest = new OJAnnotatedClass();
		dummyTest.setSuperclass(baseTest.getPathName());
		dummyTest.setName("DummyTest");
		dummyTest.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.runner.RunWith"), new OJPathName(
				"org.jboss.arquillian.junit.Arquillian")));
		OJAnnotatedPackage owner = findOrCreatePackage(pkg);
		owner.addToClasses(dummyTest);
		super.createTextPath(dummyTest, getOutputRootId());
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
		createTestArchive.setThrows(new HashSet<OJPathName>(Arrays.asList(new OJPathName(ClassNotFoundException.class.getName()),
				ioExceptionPath, illegalArgumentException)));
		dummyTest.addToImports(new OJPathName("org.jboss.shrinkwrap.api.spec.WebArchive"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.arquillian.ArquillianUtils"));
		dummyTest.addToImports(new OJPathName("org.nakeduml.test.NakedUtilTestClasses"));
		createTestArchive.getBody().addToStatements("WebArchive war = ArquillianUtils.createWarArchive(false)");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"WEB-INF/beans.xml\", \"beans.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"hibernate.cfg.xml\", \"classes/hibernate.cfg.xml\")");
		createTestArchive.getBody().addToStatements("war.addWebResource(\"data.generation.properties\", \"data.generation.properties\")");
		createTestArchive.getBody().addToStatements("war.addPackages(true, NakedUtilTestClasses.getTestPackages())");
		createTestArchive.getBody().addToStatements("war.addPackages(true, getTestPackages())");
		createTestArchive.getBody().addToStatements("return war");
		OJAnnotatedOperation test = new OJAnnotatedOperation("test");
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.junit.Test")));
		test.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.SuppressWarnings"), "unchecked"));
		test.getBody().addToStatements(
				"List<" + root.getMappingInfo().getJavaName() + "> roots = session.createQuery(\"select h from "
						+ root.getMappingInfo().getJavaName() + " h\").list()");
		dummyTest.addToOperations(test);
		dummyTest.addToImports(new OJPathName("java.util.List"));
		dummyTest.addToImports(OJUtil.classifierPathname(root));
		dummyTest.addToImports(new OJPathName("org.junit.Assert"));
		test.getBody().addToStatements("Assert.assertTrue(roots.size()>0)");
	}

	private OJAnnotatedClass createBaseTestClass(Collection<INakedPackage> packages, OJPathName packageName) {
		OJAnnotatedClass baseTest = new OJAnnotatedClass();
		baseTest.setName("BaseTest");
		OJAnnotatedPackage owner = findOrCreatePackage(packageName);
		owner.addToClasses(baseTest);
		super.createTextPath(baseTest, getOutputRootId());
		createGetTestPackagesOper(baseTest, packages);
		return baseTest;
	}

	private void createGetTestPackagesOper(OJAnnotatedClass baseTest, Collection<INakedPackage> packages) {
		baseTest.addToImports(new OJPathName("java.lang.Package"));
		OJPathName list = new OJPathName("java.util.List");
		baseTest.addToImports(list);
		OJAnnotatedOperation getTestPackages = new OJAnnotatedOperation("getTestPackages");
		getTestPackages.setReturnType(new OJPathName("java.lang.Package[]"));
		baseTest.addToOperations(getTestPackages);
		getTestPackages.setVisibility(OJVisibilityKind.PUBLIC);
		getTestPackages.setStatic(true);
		OJPathName ioExceptionPath = new OJPathName(IOException.class.getName());
		getTestPackages.setThrows(new HashSet<OJPathName>(Arrays.asList(new OJPathName(ClassNotFoundException.class.getName()),
				ioExceptionPath)));
		baseTest.addToImports(ioExceptionPath);
		OJField classes = new OJField();
		classes.setName("packages");
		OJPathName classPath = new OJPathName("java.lang.Package");
		list.addToElementTypes(classPath);
		classes.setType(list);
		classes.setInitExp("new ArrayList<Package>()");
		baseTest.addToImports(new OJPathName("java.util.ArrayList"));
		getTestPackages.getBody().addToLocals(classes);
		for (INakedPackage p : packages) {
			String entityName = "";
			for (IClassifier c : p.getClassifiers()) {
				if (c instanceof INakedEntity) {
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

	private void createStartOperation(INakedEntity root, OJPathName utilPkg) {
		OJAnnotatedClass startUp = new OJAnnotatedClass();
		startUp.setName("StartUp");
		OJAnnotatedPackage owner = findOrCreatePackage(utilPkg);
		owner.addToClasses(startUp);
		super.createTextPath(startUp, getOutputRootId());
		OJPathName rootPathname = OJUtil.classifierPathname(root);
		startUp.addToImports(rootPathname);
		addClassFields(startUp, root);
		addStartOperation(startUp, root);
	}

	private OutputRootId getOutputRootId() {
		if (isIntegrationPhase) {
			return JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_TEST_SRC;
		} else {
			return JavaTextSource.OutputRootId.ADAPTOR_GEN_TEST_SRC;
		}
	}

	private void addClassFields(OJAnnotatedClass startUp, INakedEntity root) {
		OJAnnotatedField session = new OJAnnotatedField("session", new OJPathName("org.hibernate.Session"));
		session.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(session);
		OJAnnotatedField transaction = new OJAnnotatedField("transaction", new OJPathName(
				"org.jboss.seam.persistence.transaction.SeamTransaction"));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		transaction.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.persistence.transaction.DefaultTransaction")));
		startUp.addToFields(transaction);
		OJAnnotatedField dataGeneratorProperty = new OJAnnotatedField("dataGeneratorProperty", new OJPathName(
				"org.nakeduml.runtime.adaptor.DataGeneratorProperty"));
		dataGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(dataGeneratorProperty);
		OJAnnotatedField rootGeneratorProperty = new OJAnnotatedField("rootDataGenerator", new OJPathName(root.getMappingInfo()
				.getQualifiedJavaName() + "DataGenerator"));
		rootGeneratorProperty.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.inject.Inject")));
		startUp.addToFields(rootGeneratorProperty);
	}

	private OJAnnotatedOperation addStartOperation(OJAnnotatedClass startUp, INakedEntity root) {
		OJPathName rootPathname = OJUtil.classifierPathname(root);
		OJAnnotatedOperation start = new OJAnnotatedOperation("start");
		OJAnnotatedParameter param = new OJAnnotatedParameter("webapp", new OJPathName("org.jboss.seam.servlet.WebApplication"));
		OJPathName observes = new OJPathName("javax.enterprise.event.Observes");
		startUp.addToImports(observes);
		param.addAnnotationIfNew(new OJAnnotationValue(observes));
		OJPathName started = new OJPathName("org.jboss.seam.servlet.event.Started");
		startUp.addToImports(started);
		param.addAnnotationIfNew(new OJAnnotationValue(started));
		start.addToParameters(param);
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
				+ " a where a.name = :name\").setText(\"name\", dataGeneratorProperty.getProperty(\"" + rootName.toLowerCase()
				+ ".name_0\")).uniqueResult()");
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
