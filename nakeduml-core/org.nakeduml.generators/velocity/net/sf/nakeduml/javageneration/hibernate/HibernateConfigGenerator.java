package net.sf.nakeduml.javageneration.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import net.sf.nakeduml.feature.SortedProperties;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.javageneration.auditing.IntegratedAuditMetaDefStep;
import net.sf.nakeduml.javageneration.jbpm5.IntegratedJbpm5EnvironmentStep;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5JavaStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakedum.velocity.AbstractTextProducingVisitor;
import org.nakeduml.environment.Environment;
import org.nakeduml.java.metamodel.OJPathName;

public class HibernateConfigGenerator extends AbstractTextProducingVisitor {
	private static final String CDI_ENVIRONMENT = "org.nakeduml.environment.adaptor.CdiEnvironment";
	private static final String DOMAIN_ENVIRONMENT = "org.nakeduml.environment.domain.DomainEnvironment";
	boolean isIntegrationPhase = true;

	public HibernateConfigGenerator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public static final class MappingCollector extends AbstractJavaProducingVisitor {
		private final HashSet<OJPathName> classes = new HashSet<OJPathName>();

		@VisitBefore(matchSubclasses = true)
		public void visitClassifier(INakedClassifier c) {
			if (isPersistent(c)) {
				classes.add(OJUtil.classifierPathname(c));
			}
		}

		public void visitOpaqueAction(INakedOpaqueAction a) {
			if (a.isTask()) {
				classes.add(OJUtil.classifierPathname(a.getMessageStructure()));
			}
		}

		public void visitOperation(INakedOperation o) {
			if (o.isUserResponsibility()) {
				classes.add(OJUtil.classifierPathname(new OperationMessageStructureImpl(o)));
			}
		}

		@Override
		public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
			return super.getChildren(root);
		}
	}

	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		if (isIntegrationPhase) {
			String hibernateConfigName = workspace.getDirectoryName() + "-hibernate.cfg.xml";
			generateConfigAndEnvironment((Collection<INakedRootObject>) workspace.getOwnedElements(), hibernateConfigName,
					OutputRootId.INTEGRATED_ADAPTOR_GEN_RESOURCE, true);
		}
	}

	@VisitBefore
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			String hibernateConfigName = model.getFileName() + "-hibernate.cfg.xml";
			Collection<INakedRootObject> selfAndDependencies = new ArrayList<INakedRootObject>(model.getDependencies());
			selfAndDependencies.add(model);
			generateConfigAndEnvironment(selfAndDependencies, hibernateConfigName, OutputRootId.DOMAIN_GEN_TEST_RESOURCE, false);
			generateConfigAndEnvironment(selfAndDependencies, hibernateConfigName, OutputRootId.ADAPTOR_GEN_TEST_RESOURCE, true);
			generateConfigAndEnvironment(selfAndDependencies, "standalone-" +hibernateConfigName, OutputRootId.ADAPTOR_GEN_TEST_RESOURCE, false);
			HashMap<String, Object> vars = buildVars(selfAndDependencies, true);
			processTemplate(workspace, "templates/Model/Jbpm4HibernateConfig.vsl", "standalone-"+hibernateConfigName, OutputRootId.ADAPTOR_GEN_TEST_RESOURCE, vars);
		}
	}

	private void generateConfigAndEnvironment(Collection<INakedRootObject> models, String hibernateConfigName, OutputRootId outputRootId,
			boolean isAdaptorEnvironment) {
		SortedProperties domainSortedProperties = new SortedProperties();
		if (isAdaptorEnvironment) {
			domainSortedProperties.setProperty(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION, UtilityCreator.getUtilPathName()
					+ ".jbpm.adaptor.JbpmKnowledgeBase");
		} else {
			domainSortedProperties.setProperty(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION, UtilityCreator.getUtilPathName()
					+ ".jbpm.domain.JbpmKnowledgeBase");
		}
		domainSortedProperties.setProperty(Environment.ENVIRONMENT_IMPLEMENTATION, isAdaptorEnvironment ? CDI_ENVIRONMENT
				: DOMAIN_ENVIRONMENT);
		domainSortedProperties.setProperty(Environment.HIBERNATE_CONFIG_NAME, hibernateConfigName);
		findOrCreateTextFile(domainSortedProperties, outputRootId, Environment.PROPERTIES_FILE_NAME);
		HashMap<String, Object> vars = buildVars(models, isAdaptorEnvironment);
		processTemplate(workspace, "templates/Model/Jbpm4HibernateConfig.vsl", hibernateConfigName, outputRootId, vars);
	}

	private HashMap<String, Object> buildVars(Collection<? extends INakedElement> models, boolean isAdaptorEnvironment) {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		boolean requiresAudit = transformationContext.isAnyOfFeaturesSelected(AuditImplementationStep.class,IntegratedAuditMetaDefStep.class);
		vars.put("requiresAuditing", requiresAudit);
		vars.put("config", this.config);
		vars.put("isAdaptorEnvironment", isAdaptorEnvironment);
		vars.put("requiresJbpm", transformationContext.isAnyOfFeaturesSelected(Jbpm5JavaStep.class, IntegratedJbpm5EnvironmentStep.class));
		MappingCollector collector = new MappingCollector();
		// do all models
		for (INakedElement element : models) {
			if (element instanceof INakedModel) {
				collector.visitRecursively(element);
			}
		}
		vars.put("persistentClasses", collector.classes);
		vars.put("pkg", HibernateUtil.getHibernatePackage(isAdaptorEnvironment));
		return vars;
	}
}
