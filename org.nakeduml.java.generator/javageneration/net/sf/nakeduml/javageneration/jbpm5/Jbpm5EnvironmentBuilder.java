package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor {
	public class BehaviorVisitor extends
			VisitorAdapter<INakedElement, INakedModel> {
		@VisitBefore(matchSubclasses = true)
		public void visitBehavior(INakedBehavior b) {
			if (b.isProcess()) {
				prepareKnowledgeBaseBody
						.addToStatements("kbuilder.add(ResourceFactory.newClassPathResource(\""
								+ b.getMappingInfo().getJavaPath()
								+ ".rf\"), ResourceType.DRF)");
			}
		}

		@Override
		public Collection<? extends INakedElement> getChildren(
				INakedElement parent) {
			return parent.getOwnedElements();
		}
	}

	private OJBlock prepareKnowledgeBaseBody;
	boolean isIntegrationPhase = true;

	public Jbpm5EnvironmentBuilder(boolean isIntegrationPhase) {
		this.isIntegrationPhase = isIntegrationPhase;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace workspace) {
		if (isIntegrationPhase) {
			OJPathName pn = new OJPathName(config.getMavenGroupId()
					+ ".util.jbpm.adaptor");
			createJbpmKnowledgeBase(pn, OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
			visitModels(workspace.getOwnedElements());

			JpaUtil.addNamedQueries(
					findOrCreatePackage(HibernateUtil.getHibernatePackage(true)),
					"ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			OJPathName adaptorUtilPathname = UtilityCreator.getUtilPathName()
					.append("jbpm").append("adaptor");
			createJbpmKnowledgeBase(adaptorUtilPathname,
					OutputRootId.ADAPTOR_GEN_TEST_SRC);
			BehaviorVisitor visitor = visitModels(getModelInScope());
			OJPathName domainUtilPathname = UtilityCreator.getUtilPathName()
					.append("jbpm").append("domain");
			createJbpmKnowledgeBase(domainUtilPathname,
					OutputRootId.DOMAIN_GEN_TEST_SRC);
			visitor.startVisiting(model);
			JpaUtil.addNamedQueries(
					findOrCreatePackage(HibernateUtil.getHibernatePackage(true)),
					"ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
			JpaUtil.addNamedQueries(
					findOrCreatePackage(HibernateUtil
							.getHibernatePackage(false)),
					"ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}

	private BehaviorVisitor visitModels(
			Collection<? extends INakedElement> roots) {
		BehaviorVisitor visitor = new BehaviorVisitor();
		for (INakedElement root : roots) {
			if (root instanceof INakedModel) {
				visitor.startVisiting((INakedModel) root);
			}
		}
		return visitor;
	}

	private void createJbpmKnowledgeBase(OJPathName utilPathName,
			Enum<?> outputRootId) {
		OJAnnotatedClass jbpmKnowledgeBase = new OJAnnotatedClass();
		jbpmKnowledgeBase.setName("JbpmKnowledgeBase");
		OJPackage utilPack = findOrCreatePackage(utilPathName);
		utilPack.addToClasses(jbpmKnowledgeBase);
		super.createTextPath(jbpmKnowledgeBase, outputRootId);
		addCommonImports(jbpmKnowledgeBase);
		jbpmKnowledgeBase.setSuperclass(new OJPathName(
				"org.nakeduml.environment.AbstractJbpmKnowledgeBase"));
		OJAnnotatedOperation prepareKnowledgeBase = new OJAnnotatedOperation();
		jbpmKnowledgeBase.addToOperations(prepareKnowledgeBase);
		prepareKnowledgeBase.setName("prepareKnowledgeBuilder");
		prepareKnowledgeBase.addParam("kbuilder", new OJPathName(
				"KnowledgeBuilder"));
		prepareKnowledgeBaseBody = prepareKnowledgeBase.getBody();
		OJAnnotatedField instance = new OJAnnotatedField("mockInstance",
				jbpmKnowledgeBase.getPathName());
		jbpmKnowledgeBase.addToFields(instance);
		instance.setStatic(true);
	}

	private void addCommonImports(OJAnnotatedClass jbpmKnowledgeSession) {
		jbpmKnowledgeSession.addToImports("org.drools.KnowledgeBase");
		jbpmKnowledgeSession
				.addToImports("org.drools.builder.KnowledgeBuilder");
		jbpmKnowledgeSession.addToImports("org.drools.io.ResourceFactory");
		jbpmKnowledgeSession.addToImports("org.drools.builder.ResourceType");
	}
}
