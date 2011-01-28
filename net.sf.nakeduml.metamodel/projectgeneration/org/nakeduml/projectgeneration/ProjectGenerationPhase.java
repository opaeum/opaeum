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
import net.sf.nakeduml.javageneration.auditing.AuditImplementationStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemantics;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfigGenerator;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Step;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.jaxb.JaxbStep;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;

@PhaseDependency(before={FileGenerationPhase.class,PomGenerationPhase.class})
public class ProjectGenerationPhase implements TransformationPhase<ProjectGenerationStep> {

	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<ProjectGenerationStep> features) {
		for (ProjectGenerationStep step : features) {
			step.initialize(config, textWorkspace);
			step.generate();
		}
		return new Object[0];
	}

	public static void main(String[] args) throws Exception {

		Model model = UML2ModelLoader.loadModel("testmodels/testProjectGen.uml");
        EcoreUtil.resolveAll(model);
        File modelFile = new File(model.eResource().getURI().toFileString());
        
		Properties props = new Properties();
		NakedUmlConfig cfg = new NakedUmlConfig(props, "NAKEDGENPROJECT");
		
		cfg.setNakedUmlProjectGenRoot(args[0]);
		cfg.setNakedUmlProjectGenName(args[1]);
		cfg.setNakedUmlProjectGenGroupId(args[2]);
		
		TransformationProcess process = new TransformationProcess();
		Set<Class<? extends TransformationStep>> steps = new HashSet<Class<? extends TransformationStep>>();
		steps.add(StereotypeApplicationExtractor.class);
		steps.add(ProjectGenerationStep.class);
		
		steps.add(PersistenceUsingHibernateStep.class);
        steps.add(HibernateConfigGenerator.class);
        steps.add(OclExpressionExecution.class);
        steps.add(JaxbStep.class);
        steps.add(AuditImplementationStep.class);
        steps.add(ExtendedCompositionSemantics.class);
		
		WorkspaceMappingInfoImpl mappingInfo=new WorkspaceMappingInfoImpl(new File(modelFile.getParentFile(), model.getName()+".mapping.properties"));
		process.execute(cfg, new EmfWorkspace(model, mappingInfo), steps);
		//store maaping info
		mappingInfo.store();
	}
}
