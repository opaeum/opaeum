package net.sf.nakeduml.javageneration.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractTextProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.CharArrayTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@StepDependency(phase = StandaloneHibernatePhase.class, requires = PersistenceUsingHibernateStep.class)
public class HibernateConfigGenerator extends AbstractTextProducingVisitor implements StandaloneHibernateStep {
	public static final class MappingCollector extends AbstractJavaProducingVisitor {
		private final HashSet<OJPathName> classes;
		private final HashSet<OJPathName> packages;

		@VisitBefore(matchSubclasses = true)
		public void visitClassifier(INakedClassifier c) {
			if (super.isPersistent(c)) {
				classes.add(OJUtil.classifierPathname(c));
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
	}

	public static final String RESOURCE_DIR = "gen-src";

	public void generate() {
		HashMap<String, Object> vars = new HashMap<String, Object>();
		MappingCollector collector = new MappingCollector();
		collector.startVisiting(workspace);
		Set<OJClass> classesRecursively = getClassesRecursively();
		List<OJClass> sortedClasses = new ArrayList<OJClass>(classesRecursively);
		Collections.sort(sortedClasses, new Comparator<OJClass>() {
			@Override
			public int compare(OJClass o1, OJClass o2) {
				if (o1.getName().endsWith("Audit")) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		vars.put("persistentClasses", sortedClasses);
		vars.put("packages", sortedClasses);
		// TODO find better way of knowing the audit step is present
		boolean requiresAudit = false;
		for (OJClass ojClass : sortedClasses) {
			requiresAudit = ojClass.getName().endsWith("Audit");
			if (requiresAudit) {
				break;
			}
		}
		vars.put("requiresAuditing", requiresAudit);
		vars.put("config", this.config);
		processTemplate(this.workspace.getGeneratingModelsOrProfiles().get(0), "templates/Model/Jbpm4HibernateConfig.vsl",
		/* this.config.getProjectName()+ "." + */"hibernate.cfg.xml", getOutputRootId(), vars);
	}

	private OutputRootId getOutputRootId() {
		if (workspace.isSingleModelWorkspace()) {
			return CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTORS_GEN_RESOURCE;
		} else {
			return CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE;
		}
	}

	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config, TextWorkspace textWorkspace, OJPackage javaModel) {
		super.initialize(workspace, config, textWorkspace);
		this.javaModel = javaModel;
	}
}
