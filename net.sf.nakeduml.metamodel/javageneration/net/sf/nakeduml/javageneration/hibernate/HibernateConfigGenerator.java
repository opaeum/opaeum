package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractTextProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.CharArrayTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class HibernateConfigGenerator extends AbstractTextProducingVisitor {
	boolean isIntegrationPhase = true;
	boolean isAdaptorEnvironment;

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

	public static final String RESOURCE_DIR = "gen-src";

	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace) {
		if (isIntegrationPhase) {
			Collection<? extends INakedElement> ownedElements = workspace.getOwnedElements();
			HashMap<String, Object> vars = buildVars(ownedElements, new OJPathName(config.getMavenGroupId() + ".util"));
			processTemplate(workspace, "templates/Model/Jbpm4HibernateConfig.vsl", workspace.getDirectoryName() + "-hibernate.cfg.xml",
					getOutputRootId(), vars);
		}
	}

	private HashMap<String, Object> buildVars(Collection<? extends INakedElement> models, OJPathName pkg) {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		boolean requiresAudit = transformationContext.isFeatureSelected(AuditImplementationStep.class);
		vars.put("requiresAuditing", requiresAudit);
		vars.put("config", this.config);
		vars.put("isAdaptorEnvironment", isAdaptorEnvironment);
		MappingCollector collector = new MappingCollector();
		// do all models
		for (INakedElement element : models) {
			if (element instanceof INakedModel) {
				collector.visitRecursively(element);
			}
		}
		vars.put("persistentClasses", collector.classes);
		vars.put("pkg", pkg);
		return vars;
	}

	@VisitBefore
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			HashMap<String, Object> vars = buildVars(model.getDependencies(), UtilityCreator.getUtilPathName());
			processTemplate(model, "templates/Model/Jbpm4HibernateConfig.vsl", model.getFileName() + "-hibernate.cfg.xml",
					getOutputRootId(), vars);
			isAdaptorEnvironment = true;
			processTemplate(model, "templates/Model/Jbpm4HibernateConfig.vsl", model.getFileName() + "-hibernate.cfg.xml",
					getOutputRootId(), vars);
		}
	}

	private OutputRootId getOutputRootId() {
		if (isIntegrationPhase) {
			return CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_RESOURCE;
		} else if (isAdaptorEnvironment) {
			return CharArrayTextSource.OutputRootId.ADAPTOR_GEN_TEST_RESOURCE;
		} else {
			return CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE;
		}
	}
}
