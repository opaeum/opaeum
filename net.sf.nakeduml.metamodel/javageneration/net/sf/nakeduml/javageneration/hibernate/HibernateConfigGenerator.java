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

public class HibernateConfigGenerator extends AbstractTextProducingVisitor {
	boolean isIntegrationPhase = true;

	public HibernateConfigGenerator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public static final class MappingCollector extends AbstractJavaProducingVisitor {
		private final HashSet<OJPathName> classes = new HashSet<OJPathName>();
		private final HashSet<OJPathName> packages = new HashSet<OJPathName>();

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

		@VisitBefore(matchSubclasses = true)
		public void visitModel(INakedModel model) {
			packages.add(OJUtil.packagePathname(model));
		}

		@VisitBefore(matchSubclasses = true)
		public void visitInterface(INakedInterface it) {
			packages.add(OJUtil.packagePathname(it.getNameSpace()));
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
			HashMap<String, Object> vars = buildVars(ownedElements);
			processTemplate(workspace, "templates/Model/Jbpm4HibernateConfig.vsl", "hibernate.cfg.xml", getOutputRootId(), vars);
		}
	}

	private HashMap<String, Object> buildVars(Collection<? extends INakedElement> models) {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		boolean requiresAudit = transformationContext.isFeatureSelected(AuditImplementationStep.class);
		vars.put("requiresAuditing", requiresAudit);
		vars.put("config", this.config);
		MappingCollector collector = new MappingCollector();
		// do all models
		for (INakedElement element : models) {
			if (element instanceof INakedModel) {
				collector.visitRecursively(element);
			}
		}
		vars.put("persistentClasses", collector.classes);
		vars.put("packages", collector.packages);
		return vars;
	}

	@VisitBefore
	public void visitModel(INakedModel model) {
		if (!isIntegrationPhase) {
			HashMap<String, Object> vars = buildVars(model.getDependencies());
			processTemplate(model, "templates/Model/Jbpm4HibernateConfig.vsl", "hibernate.cfg.xml", getOutputRootId(), vars);
		}
	}


	private OutputRootId getOutputRootId() {
		if (isIntegrationPhase) {
			return CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTORS_GEN_RESOURCE;
		} else {
			return CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE;
		}
	}
}
