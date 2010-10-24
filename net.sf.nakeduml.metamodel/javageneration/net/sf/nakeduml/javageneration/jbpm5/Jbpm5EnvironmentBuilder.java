package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor {
	private static final OJPathName STATEFUL_KNOWLEDGE_SESSION_PATH = new OJPathName("org.drools.runtime.StatefulKnowledgeSession");
	private OJBlock readKnowledgeBaseBody;

	@VisitBefore(matchSubclasses = true)
	public void visitModel(INakedModel model) {
		OJAnnotatedClass jbpm5Environment = new OJAnnotatedClass();
		jbpm5Environment.setName("Jbpm5Environment");
		UtilityCreator.getUtilPack().addToClasses(jbpm5Environment);
		OJPathName threadLocalPath = new OJPathName("ThreadLocal");
		OJAnnotatedField threadLocal = new OJAnnotatedField("session", threadLocalPath);
		threadLocal.setStatic(true);
		threadLocal.setInitExp("new ThreadLocal<StatefulKnowledgeSession>()");
		jbpm5Environment.addToFields(threadLocal);
		threadLocalPath.addToElementTypes(STATEFUL_KNOWLEDGE_SESSION_PATH);
		super.createTextPath(jbpm5Environment, JavaTextSource.GEN_SRC);
		jbpm5Environment.addToImports("java.util.Properties");
		jbpm5Environment.addToImports("org.drools.KnowledgeBase");
		jbpm5Environment.addToImports("org.drools.SessionConfiguration");
		jbpm5Environment.addToImports("org.drools.builder.KnowledgeBuilder");
		jbpm5Environment.addToImports("org.drools.builder.KnowledgeBuilderFactory");
		jbpm5Environment.addToImports("org.drools.builder.ResourceType");
		jbpm5Environment.addToImports("org.drools.compiler.ProcessBuilderFactory");
		jbpm5Environment.addToImports("org.drools.impl.EnvironmentImpl");
		jbpm5Environment.addToImports("org.drools.io.ResourceFactory");
		jbpm5Environment.addToImports("org.drools.logger.KnowledgeRuntimeLogger");
		jbpm5Environment.addToImports("org.drools.logger.KnowledgeRuntimeLoggerFactory");
		jbpm5Environment.addToImports("org.drools.runtime.StatefulKnowledgeSession");
		jbpm5Environment.addToImports("org.drools.runtime.process.ProcessRuntimeFactory");
		jbpm5Environment.addToImports("org.jbpm.process.builder.ProcessBuilderFactoryServiceImpl");
		jbpm5Environment.addToImports("org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl");
		jbpm5Environment.addToImports("org.jbpm.process.instance.event.DefaultSignalManagerFactory");
		jbpm5Environment.addToImports("org.jbpm.process.instance.impl.DefaultProcessInstanceManagerFactory");
		jbpm5Environment.addToImports("org.jbpm.workflow.instance.impl.NodeInstanceFactoryRegistry");
		jbpm5Environment.addToImports("org.jbpm.workflow.instance.impl.factory.CreateNewNodeFactory");
		jbpm5Environment.addToImports("org.jbpm.workflow.core.node.StateNode");
		jbpm5Environment.addToImports("org.jbpm.workflow.core.node.Join");
		jbpm5Environment.addToImports("org.jbpm.workflow.core.node.EndNode");
		jbpm5Environment.addToImports("net.sf.nakeduml.jbpmstatemachine.Uml2StateInstance");
		jbpm5Environment.addToImports("net.sf.nakeduml.jbpmstatemachine.Uml2EndStateInstance");
		jbpm5Environment.addToImports("net.sf.nakeduml.jbpmstatemachine.Uml2JoinInstance");
		OJAnnotatedOperation getKnowledgeRuntime = new OJAnnotatedOperation();
		getKnowledgeRuntime.setName("getKnowledgeSession");
		getKnowledgeRuntime.setStatic(true);
		getKnowledgeRuntime.setReturnType(STATEFUL_KNOWLEDGE_SESSION_PATH);
		OJIfStatement ifNull = new OJIfStatement("session.get()==null");
		getKnowledgeRuntime.getBody().addToStatements(ifNull);
		OJTryStatement tryStatement = new OJTryStatement();
		ifNull.getThenPart().addToStatements(tryStatement);
		tryStatement.getTryPart().addToStatements("KnowledgeBase kbase = readKnowledgeBase()");
		tryStatement.getTryPart().addToStatements("Properties properties = new Properties()");
		tryStatement.getTryPart().addToStatements(
				"properties.setProperty(\"drools.processInstanceManagerFactory\", DefaultProcessInstanceManagerFactory.class.getName())");
		tryStatement.getTryPart().addToStatements(
				"properties.setProperty(\"drools.processSignalManagerFactory\", DefaultSignalManagerFactory.class.getName())");
		tryStatement.getTryPart().addToStatements("SessionConfiguration cfg = new SessionConfiguration(properties)");
		tryStatement.getTryPart().addToStatements("EnvironmentImpl env = new EnvironmentImpl()");
		tryStatement.getTryPart().addToStatements("session.set(kbase.newStatefulKnowledgeSession(cfg,env))");
		tryStatement.getTryPart().addToStatements(
				"KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(session.get(), \"test\")");
		getKnowledgeRuntime.getBody().addToStatements("return session.get()");
		tryStatement.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
		tryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		jbpm5Environment.addToOperations(getKnowledgeRuntime);
		OJAnnotatedOperation readKnowledgeBase = new OJAnnotatedOperation();
		jbpm5Environment.addToOperations(readKnowledgeBase);
		readKnowledgeBase.setName("readKnowledgeBase");
		readKnowledgeBase.setStatic(true);
		readKnowledgeBase.setReturnType(new OJPathName("KnowledgeBase"));
		readKnowledgeBaseBody = readKnowledgeBase.getBody();
		readKnowledgeBaseBody
				.addToStatements("ProcessBuilderFactory.setProcessBuilderFactoryService(new ProcessBuilderFactoryServiceImpl())");
		readKnowledgeBaseBody
				.addToStatements("ProcessRuntimeFactory.setProcessRuntimeFactoryService(new ProcessRuntimeFactoryServiceImpl())");
		readKnowledgeBaseBody.addToStatements("KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder()");
		readKnowledgeBaseBody.addToStatements("NodeInstanceFactoryRegistry.INSTANCE.register(StateNode.class, new CreateNewNodeFactory(Uml2StateInstance.class))");
		readKnowledgeBaseBody.addToStatements("NodeInstanceFactoryRegistry.INSTANCE.register(EndNode.class, new CreateNewNodeFactory(Uml2EndStateInstance.class))");
		readKnowledgeBaseBody.addToStatements("NodeInstanceFactoryRegistry.INSTANCE.register(Join.class, new CreateNewNodeFactory(Uml2JoinInstance.class))");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b) {
		if (b.isProcess()) {
			readKnowledgeBaseBody.addToStatements("kbuilder.add(ResourceFactory.newClassPathResource(\"" + b.getMappingInfo().getJavaPath()
					+ ".rf\"), ResourceType.DRF)");
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitModelAfter(INakedModel model) {
		readKnowledgeBaseBody.addToStatements("return kbuilder.newKnowledgeBase()");
	}
}
