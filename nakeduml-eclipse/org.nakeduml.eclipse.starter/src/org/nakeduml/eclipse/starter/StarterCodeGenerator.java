package org.nakeduml.eclipse.starter;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.nakeduml.bootstrap.WarBootstrapStep;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.HibernateIntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.IntegrationTests;
import org.nakeduml.generation.features.IntegrationTestsAcrossMultipleModels;
import org.nakeduml.generation.features.Jbpm5IntegratedAcrossMultipleProjects;
import org.nakeduml.generation.features.PersistenceUsingHibernate;

public class StarterCodeGenerator extends MavenProjectCodeGenerator{
	public enum OutputRootId{
		GENERATOR_ROOT,
		GENERATOR_SRC
	}
	private NakedUmlConfigDialog dialog;
	protected StarterCodeGenerator(NakedUmlConfigDialog dialog,String modelDirectory){
		super(calculateOutputRoot(dialog), modelDirectory);
		this.dialog = dialog;
	}
	private static String calculateOutputRoot(NakedUmlConfigDialog dialog){
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		return new File(root.getLocation().toFile(), dialog.getWorkspaceIdentifier()).getAbsolutePath();
	}
	protected NakedUmlConfig buildConfig(EmfWorkspace workspace) throws IOException{
		NakedUmlConfig cfg = new NakedUmlConfig();
		cfg.setOutputRoot(super.outputRoot);
		cfg.load(new File(super.modelDirectory, workspace.getDirectoryName() + "-nakeduml.properties"), workspace.getName());
		String domain = dialog.getCompanyDomain();
		StringBuilder mavenGroup = null;
		StringTokenizer st = new StringTokenizer(domain, ".");
		while(st.hasMoreTokens()){
			if(mavenGroup == null){
				mavenGroup = new StringBuilder(st.nextToken());
			}else{
				mavenGroup.insert(0, '.');
				mavenGroup.insert(0, st.nextToken());
			}
		}
		mavenGroup.append('.');
		mavenGroup.append(dialog.getWorkspaceIdentifier());
		cfg.setMavenGroupId(mavenGroup.toString());
		mapOutputRoots(cfg);
		cfg.mapOutputRoot(OutputRootId.GENERATOR_SRC, true, "-generator", "src/main/java");
		cfg.store();
		return cfg;
	}
	@Override
	protected Set<Class<? extends TransformationStep>> getSteps(){
		Set<Class<? extends TransformationStep>> basicSteps = getBasicSteps();
		basicSteps.add(EclipseProjectGenerationStep.class);
		return basicSteps;
	}
	public static Set<Class<? extends TransformationStep>> getBasicSteps(){
		return toSet(PersistenceUsingHibernateStep.class, ExtendedCompositionSemantics.class, OclExpressionExecution.class, StereotypeApplicationExtractor.class,
				BpmUsingJbpm5.class, PersistenceUsingHibernate.class, IntegrationTests.class);
	}
	@Override
	protected Set<Class<? extends TransformationStep>> getIntegrationSteps(){
		Set<Class<? extends TransformationStep>> basicIntegrationSteps = getBasicIntegrationSteps();
		basicIntegrationSteps.add(EclipseProjectGenerationStep.class);
		basicIntegrationSteps.add(GeneratorGenerationStep.class);
		basicIntegrationSteps.add(GeneratorPomStep.class);
		return basicIntegrationSteps;
	}
	public static Set<Class<? extends TransformationStep>> getBasicIntegrationSteps(){
		return toSet(HibernateIntegratedAcrossMultipleProjects.class, Jbpm5IntegratedAcrossMultipleProjects.class,
				IntegrationTestsAcrossMultipleModels.class, WarBootstrapStep.class);
	}
}