package org.nakeduml.projectgeneration;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.load.UML2ModelLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.jaxb.JaxbStep;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;

@PhaseDependency(before = { FileGenerationPhase.class, PomGenerationPhase.class }, after = { JavaTransformationPhase.class })
public class ProjectGenerationPhase implements TransformationPhase<AbstractProjectGenerationStep> {

	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private OJAnnotatedPackage javaModel;
	@InputModel
	private INakedModelWorkspace workspace;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<AbstractProjectGenerationStep> features) {
		for (AbstractProjectGenerationStep step : features) {
			step.initialize(workspace, javaModel, config, textWorkspace);
			step.startVisiting(workspace);
		}
		return new Object[0];
	}

	public static void main(String[] args) throws Exception {
		// Properties props = new Properties();
		// NakedUmlConfig cfg = new NakedUmlConfig(props, "NAKEDGENPROJECT");
		// cfg.setNakedUmlProjectGenRoot(args[1]);
		// cfg.setNakedUmlProjectGenName(args[2]);
		// cfg.setNakedUmlProjectGenGroupId(args[3]);
		// TransformationProcess process = new TransformationProcess();
		// Set<Class<? extends TransformationStep>> steps = new HashSet<Class<?
		// extends TransformationStep>>();
		// if (args[0].equals("ear")) {
		// steps.add(EarProjectGenerationStep.class);
		// } else if (args[0].equals("war")) {
		// steps.add(WarProjectGenerationStep.class);
		// }
		// process.execute(cfg, null, steps);

		Model model = UML2ModelLoader.loadModel("testmodels/testProjectGen.uml");
		EcoreUtil.resolveAll(model);
		File modelFile = new File(model.eResource().getURI().toFileString());

		Properties props = new Properties();
		NakedUmlConfig cfg = new NakedUmlConfig(props, "NAKEDGENPROJECT");

		cfg.setNakedUmlProjectGenRoot(args[1]);
		cfg.setNakedUmlProjectGenGroupId(args[3]);

		TransformationProcess process = new TransformationProcess();
		Set<Class<? extends TransformationStep>> steps = new HashSet<Class<? extends TransformationStep>>();

		// if (args[0].equals("ear")) {
		// steps.add(EarProjectGenerationStep.class);
		// } else if (args[0].equals("war")) {
		steps.add(WarProjectGenerationStep.class);
		// }

		steps.add(StereotypeApplicationExtractor.class);
		steps.add(PersistenceUsingHibernateStep.class);
		steps.add(HibernateConfigGenerator.class);
		steps.add(OclExpressionExecution.class);
		steps.add(JaxbStep.class);
		steps.add(AuditImplementationStep.class);
		steps.add(ExtendedCompositionSemantics.class);

		WorkspaceMappingInfoImpl mappingInfo = new WorkspaceMappingInfoImpl(new File(modelFile.getParentFile(), model.getName() + ".mapping.properties"));
		process.execute(cfg, new EmfWorkspace(model, mappingInfo), steps);
		// store maaping info
		mappingInfo.store();
	}
}
