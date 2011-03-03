package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import javax.enterprise.context.ContextNotActiveException;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJTryStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

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
		super.createTextPath(jbpmKnowledgeBase, JavaTextSource.GEN_SRC);
		addCommonImports(jbpmKnowledgeBase);
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
		
		OJTryStatement contextNotActive = new OJTryStatement();
		contextNotActive.setTryPart(new OJBlock());
		contextNotActive.getTryPart().addToStatements("return (JbpmKnowledgeBase)Component.INSTANCE.getInstance(JbpmKnowledgeBase.class)");
		contextNotActive.setCatchPart(new OJBlock());
		contextNotActive.getCatchPart().addToStatements(new OJIfStatement("mockInstance==null", "mockInstance=new JbpmKnowledgeBase()"));
		contextNotActive.getCatchPart().addToStatements("return mockInstance");
		contextNotActive.setCatchParam(new OJParameter("e", new OJPathName(ContextNotActiveException.class.getName())));
		getInstance.getBody().addToStatements(contextNotActive);
		
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
		OJAnnotatedField instance = new OJAnnotatedField("mockInstance", jbpmKnowledgeSession.getPathName());
		instance.setStatic(true);
		jbpmKnowledgeSession.addToFields(instance);
		super.createTextPath(jbpmKnowledgeSession, JavaTextSource.GEN_SRC);
		addCommonImports(jbpmKnowledgeSession);
		OJAnnotatedOperation getInstance = new OJAnnotatedOperation();
		getInstance.setName("getInstance");
		getInstance.setStatic(true);
		getInstance.setReturnType(jbpmKnowledgeSession.getPathName());
		
		OJTryStatement contextNotActive = new OJTryStatement();
		contextNotActive.setTryPart(new OJBlock());
		contextNotActive.getTryPart().addToStatements("return (JbpmKnowledgeSession)Component.INSTANCE.getInstance(JbpmKnowledgeSession.class)");
		contextNotActive.setCatchParam(new OJParameter("e", new OJPathName(ContextNotActiveException.class.getName())));
		
		getInstance.getBody().addToStatements(contextNotActive);
		
		OJIfStatement ifNull = new OJIfStatement("mockInstance==null", "mockInstance = new JbpmKnowledgeSession()");
		contextNotActive.getCatchPart().addToStatements(ifNull);
		contextNotActive.getCatchPart().addToStatements("return mockInstance");
		jbpmKnowledgeSession.addToOperations(getInstance);
		
		OJAnnotatedOperation getKnowledgeBase =new OJAnnotatedOperation("getJbpmKnowledgeBase", new OJPathName("AbstractJbpmKnowledgeBase"));
		jbpmKnowledgeSession.addToImports("net.sf.nakeduml.jbpm.AbstractJbpmKnowledgeBase");
		getKnowledgeBase.getBody().addToStatements("return JbpmKnowledgeBase.getInstance()");
		jbpmKnowledgeSession.addToOperations(getKnowledgeBase);		
		OJAnnotatedOperation getHibernateSession =new OJAnnotatedOperation("getHibernateSession", new OJPathName("org.hibernate.Session"));
		getHibernateSession.getBody().addToStatements("return (Session)Component.INSTANCE.getInstance(Session.class)");
		jbpmKnowledgeSession.addToOperations(getHibernateSession);
		jbpmKnowledgeSession.addToImports("org.hibernate.Session");
	}

	private void addCommonImports(OJAnnotatedClass jbpmKnowledgeSession) {
		jbpmKnowledgeSession.addToImports("org.drools.KnowledgeBase");
		jbpmKnowledgeSession.addToImports("org.drools.builder.KnowledgeBuilder");
		jbpmKnowledgeSession.addToImports("org.drools.io.ResourceFactory");
		jbpmKnowledgeSession.addToImports("org.drools.builder.ResourceType");
		jbpmKnowledgeSession.addToImports("javax.enterprise.context.ContextNotActiveException");
		jbpmKnowledgeSession.addToImports("net.sf.nakeduml.seam.Component");
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b) {
		if (b.isProcess() || (b instanceof INakedActivity && ((INakedActivity)b).getActivityKind()!= ActivityKind.SIMPLE_SYNCHRONOUS_METHOD)) {
			prepareKnowledgeBaseBody.addToStatements("kbuilder.add(ResourceFactory.newClassPathResource(\""
					+ b.getMappingInfo().getJavaPath() + ".rf\"), ResourceType.DRF)");
		}
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		return root.getOwnedElements();
	}
	
}
