package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor {
	private OJBlock prepareKnowledgeBaseBody;

	@VisitBefore(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace model) {
		createJbpmKnowledgeSession();
		createJbpmKnowledgeBase();
	}

	private void createJbpmKnowledgeBase() {
		OJAnnotatedClass jbpmKnowledgeBase = new OJAnnotatedClass();
		jbpmKnowledgeBase.setName("JbpmKnowledgeBase");
		OJPackage utilPack = findOrCreatePackage(Jbpm5Util.getJbpmKnowledgeSession().getHead());
		utilPack.addToClasses(jbpmKnowledgeBase);
		super.createTextPath(jbpmKnowledgeBase, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
		OJAnnotationValue name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Name"));
		name.addStringValue("jbpmKnowledgeBase");
		jbpmKnowledgeBase.addAnnotationIfNew(name);
		addCommonImports(jbpmKnowledgeBase);
		addEventScope(jbpmKnowledgeBase);
		jbpmKnowledgeBase.setSuperclass(new OJPathName("net.sf.nakeduml.jbpm.AbstractJbpmKnowledgeBase"));
		OJAnnotatedOperation prepareKnowledgeBase = new OJAnnotatedOperation();
		jbpmKnowledgeBase.addToOperations(prepareKnowledgeBase);
		prepareKnowledgeBase.setName("prepareKnowledgeBuilder");
		prepareKnowledgeBase.addParam("kbuilder", new OJPathName("KnowledgeBuilder"));
		prepareKnowledgeBaseBody = prepareKnowledgeBase.getBody();
		OJAnnotatedField instance = new OJAnnotatedField("mockInstance", jbpmKnowledgeBase.getPathName());
		jbpmKnowledgeBase.addToFields(instance);
		instance.setStatic(true);
		OJAnnotatedOperation getInstance = new OJAnnotatedOperation("getInstance", jbpmKnowledgeBase.getPathName());
		jbpmKnowledgeBase.addToOperations(getInstance);
		OJIfStatement ifEventContext = new OJIfStatement("Contexts.isEventContextActive()",
				"return (JbpmKnowledgeBase)Component.getInstance(\"jbpmKnowledgeBase\")");
		getInstance.getBody().addToStatements(ifEventContext);
		ifEventContext.setElsePart(new OJBlock());
		ifEventContext.getElsePart().addToStatements(new OJIfStatement("mockInstance==null", "mockInstance=new JbpmKnowledgeBase()"));
		ifEventContext.getElsePart().addToStatements("return mockInstance");
		getInstance.setStatic(true);
	}

	public void addEventScope(OJAnnotatedClass jbpmKnowledgeBase) {
		OJAnnotationValue scope = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Scope"));
		scope.addEnumValue(new OJEnumValue(new OJPathName("org.jboss.seam.ScopeType"), "EVENT"));
		jbpmKnowledgeBase.addAnnotationIfNew(scope);
	}

	private void createJbpmKnowledgeSession() {
		OJAnnotatedClass jbpmKnowledgeSession = new OJAnnotatedClass();
		jbpmKnowledgeSession.setName("JbpmKnowledgeSession");
		jbpmKnowledgeSession.setSuperclass(new OJPathName("net.sf.nakeduml.jbpm.AbstractJbpmKnowledgeSession"));
		OJPackage utilPack = findOrCreatePackage(Jbpm5Util.getJbpmKnowledgeSession().getHead());
		utilPack.addToClasses(jbpmKnowledgeSession);
		addEventScope(jbpmKnowledgeSession);
		OJAnnotatedField instance = new OJAnnotatedField("mockInstance", jbpmKnowledgeSession.getPathName());
		instance.setStatic(true);
		jbpmKnowledgeSession.addToFields(instance);
		super.createTextPath(jbpmKnowledgeSession, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
		OJAnnotationValue name = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Name"));
		name.addStringValue("jbpmKnowledgeSession");
		jbpmKnowledgeSession.addAnnotationIfNew(name);
		addCommonImports(jbpmKnowledgeSession);
		OJAnnotatedOperation getInstance = new OJAnnotatedOperation();
		getInstance.setName("getInstance");
		getInstance.setStatic(true);
		getInstance.setReturnType(jbpmKnowledgeSession.getPathName());
		OJIfStatement ifEventContextActive = new OJIfStatement("Contexts.isEventContextActive()",
				"return (JbpmKnowledgeSession)Component.getInstance(\"jbpmKnowledgeSession\")");
		getInstance.getBody().addToStatements(ifEventContextActive);
		OJIfStatement ifNull = new OJIfStatement("mockInstance==null", "mockInstance = new JbpmKnowledgeSession()");
		ifEventContextActive.setElsePart(new OJBlock());
		ifEventContextActive.getElsePart().addToStatements(ifNull);
		ifEventContextActive.getElsePart().addToStatements("return mockInstance");
		jbpmKnowledgeSession.addToOperations(getInstance);
		OJAnnotatedOperation getKnowledgeBase =new OJAnnotatedOperation("getJbpmKnowledgeBase", new OJPathName("AbstractJbpmKnowledgeBase"));
		jbpmKnowledgeSession.addToImports("net.sf.nakeduml.jbpm.AbstractJbpmKnowledgeBase");
		getKnowledgeBase.getBody().addToStatements("return JbpmKnowledgeBase.getInstance()");
		jbpmKnowledgeSession.addToOperations(getKnowledgeBase);		
		OJAnnotatedOperation getEntityManager =new OJAnnotatedOperation("getEntityManager", new OJPathName("EntityManager"));
		getEntityManager.getBody().addToStatements("return (EntityManager)Component.getInstance(\"entityManager\")");
		jbpmKnowledgeSession.addToOperations(getEntityManager);
		jbpmKnowledgeSession.addToImports("javax.persistence.EntityManager");
	}

	private void addCommonImports(OJAnnotatedClass jbpmKnowledgeSession) {
		jbpmKnowledgeSession.addToImports("org.drools.KnowledgeBase");
		jbpmKnowledgeSession.addToImports("org.drools.builder.KnowledgeBuilder");
		jbpmKnowledgeSession.addToImports("org.drools.io.ResourceFactory");
		jbpmKnowledgeSession.addToImports("org.drools.builder.ResourceType");
		jbpmKnowledgeSession.addToImports("org.jboss.seam.Component");
		jbpmKnowledgeSession.addToImports("org.jboss.seam.contexts.Contexts");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b) {
		if (b.isProcess()) {
			prepareKnowledgeBaseBody.addToStatements("kbuilder.add(ResourceFactory.newClassPathResource(\""
					+ b.getMappingInfo().getJavaPath() + ".rf\"), ResourceType.DRF)");
		}
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		return root.getOwnedElements();
	}
	
}
