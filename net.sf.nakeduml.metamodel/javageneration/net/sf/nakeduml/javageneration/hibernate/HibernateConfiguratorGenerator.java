package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.generated.OJVisibilityKindGEN;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

@StepDependency(phase = StandaloneHibernatePhase.class, requires = HibernateConfigGenerator.class)
public class HibernateConfiguratorGenerator extends AbstractJavaProducingVisitor implements StandaloneHibernateStep {
	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config, TextWorkspace textWorkspace, OJPackage javaModel) {

		super.initialize(workspace, javaModel, config, textWorkspace);
	}

	public void generate() {
		OJPackage util = javaModel.findPackage(UtilityCreator.getUtilPathName());
		OJAnnotatedClass hibernateConfigurator = new OJAnnotatedClass();
		hibernateConfigurator.setName("HibernateConfigurator");
		util.addToClasses(hibernateConfigurator);
		super.createTextPath(hibernateConfigurator, JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC);
		OJAnnotatedField instance = new OJAnnotatedField();
		instance.setType(hibernateConfigurator.getPathName());
		instance.setName("INSTANCE");
		instance.setStatic(true);
		instance.setVisibility(OJVisibilityKindGEN.PUBLIC);
		instance.setInitExp("new HibernateConfigurator()");
		hibernateConfigurator.addToFields(instance);
		addGetConfiguration(hibernateConfigurator);
		addGetEntityManagerFactory(hibernateConfigurator);
		addGetEntityManager(hibernateConfigurator);
		addCloseEntityManager(hibernateConfigurator);
		OJOperation getInstance = new OJAnnotatedOperation();
		getInstance.setName("getInstance");
		getInstance.setStatic(true);
		getInstance.setReturnType(hibernateConfigurator.getPathName());
		getInstance.getBody().addToStatements("return INSTANCE");
		hibernateConfigurator.addToOperations(getInstance);
		OJOperation main = new OJAnnotatedOperation();
		main.setName("main");
		main.addParam("args", new OJPathName("String[]"));
		OJAnnotatedField schemaGen = new OJAnnotatedField();
		schemaGen.setName("schemaGen");
		hibernateConfigurator.addToImports(new OJPathName("org.hibernate.tool.hbm2ddl.SchemaExport"));
		schemaGen.setType(new OJPathName("SchemaExport"));
		schemaGen.setInitExp("new SchemaExport(getInstance().getConfiguration().getHibernateConfiguration())");
		main.getBody().addToLocals(schemaGen);
		main.getBody().addToStatements("schemaGen.setDelimiter(\";\")");
		main.getBody().addToStatements("schemaGen.create(true,true)");
		main.setStatic(true);
		hibernateConfigurator.addToOperations(main);
	}

	private void addCloseEntityManager(OJAnnotatedClass hibernateConfigurator) {
		OJOperation closeEntityManager = new OJAnnotatedOperation();
		closeEntityManager.setName("closeEntityManager");
		hibernateConfigurator.addToOperations(closeEntityManager);
		OJIfStatement ifNull = new OJIfStatement("this.entityManager!=null && this.entityManager.isOpen()", "entityManager.close()");
		OJTryStatement tryIt = new OJTryStatement();
		tryIt.setTryPart(new OJBlock());
		tryIt.setCatchPart(new OJBlock());
		tryIt.getTryPart().addToStatements(ifNull);
		tryIt.getTryPart().addToStatements("this.entityManager=null");
		OJParameter exception = new OJParameter();
		exception.setName("e");
		exception.setType(new OJPathName("Exception"));
		tryIt.setCatchParam(exception);
		closeEntityManager.getBody().addToStatements(tryIt);
	}

	private void addGetEntityManager(OJAnnotatedClass hibernateConfigurator) {
		OJAnnotatedField entityManagerFactory = new OJAnnotatedField();
		entityManagerFactory.setName("entityManagerFactory");
		entityManagerFactory.setType(new OJPathName("javax.persistence.EntityManagerFactory"));
		hibernateConfigurator.addToFields(entityManagerFactory);
		OJOperation getEntityManagerFactory = new OJAnnotatedOperation();
		getEntityManagerFactory.setName("getEntityManagerFactory");
		getEntityManagerFactory.setReturnType(entityManagerFactory.getType());
		hibernateConfigurator.addToOperations(getEntityManagerFactory);
		OJIfStatement ifNull = new OJIfStatement("this.entityManagerFactory==null", "entityManagerFactory=getConfiguration().buildEntityManagerFactory()");
		getEntityManagerFactory.getBody().addToStatements(ifNull);
		getEntityManagerFactory.getBody().addToStatements("return this.entityManagerFactory");
	}

	private void addGetEntityManagerFactory(OJAnnotatedClass hibernateConfigurator) {
		OJAnnotatedField entityManager = new OJAnnotatedField();
		entityManager.setName("entityManager");
		entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
		hibernateConfigurator.addToFields(entityManager);
		OJOperation getEntityManager = new OJAnnotatedOperation();
		getEntityManager.setName("getEntityManager");
		getEntityManager.setReturnType(entityManager.getType());
		hibernateConfigurator.addToOperations(getEntityManager);
		OJIfStatement ifNull = new OJIfStatement("this.entityManager==null", "entityManager=getEntityManagerFactory().createEntityManager()");
		getEntityManager.getBody().addToStatements(ifNull);
		getEntityManager.getBody().addToStatements("return this.entityManager");
	}

	private void addGetConfiguration(OJAnnotatedClass hibernateConfigurator) {
		OJAnnotatedField configuration = new OJAnnotatedField();
		configuration.setName("configuration");
		configuration.setType(new OJPathName("org.hibernate.ejb.Ejb3Configuration"));
		hibernateConfigurator.addToFields(configuration);
		OJOperation getConfiguration = new OJAnnotatedOperation();
		getConfiguration.setName("getConfiguration");
		getConfiguration.setReturnType(configuration.getType());
		hibernateConfigurator.addToOperations(getConfiguration);
		OJIfStatement ifNull = new OJIfStatement("this.configuration==null", "configuration=new Ejb3Configuration()");
		ifNull.getThenPart().addToStatements("this.configuration.configure(\"" + workspace.getName() + ".hibernate.config.xml\")");
		ifNull.getThenPart().addToStatements("this.configuration.setProperty(\"hibernate.validator.autoregister_listeners\", \"false\")");
		ifNull.getThenPart().addToStatements("this.entityManagerFactory=this.configuration.buildEntityManagerFactory()");
		getConfiguration.getBody().addToStatements(ifNull);
		getConfiguration.getBody().addToStatements("return this.configuration");
	}

	public static OJPathName getConfiguratorPathName() {
		return UtilityCreator.getUtilPathName().append("HibernateConfigurator");
	}
}
