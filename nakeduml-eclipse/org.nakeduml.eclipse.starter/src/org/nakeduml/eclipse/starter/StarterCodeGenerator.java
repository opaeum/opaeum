package org.nakeduml.eclipse.starter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.hibernate.PersistenceUsingHibernateStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.pomgeneration.MavenProjectCodeGenerator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;
import org.nakeduml.bootstrap.WarBootstrapStep;
import org.nakeduml.eclipse.ApplyProfileAction;
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
	public StarterCodeGenerator(ResourceSet resourceSet,NakedUmlConfig cfg,File modelDirectory){
		super(resourceSet, cfg, modelDirectory);
	}
	@Override
	protected NakedUmlConfig prepareConfig() throws IOException{
		NakedUmlConfig cfg = super.prepareConfig();
		cfg.mapOutputRoot(OutputRootId.GENERATOR_SRC, true, "-generator", "src/main/java");
		for(File file:cfg.getOutputRoot().listFiles()){
			if(file.getName().equals(".project") || file.getName().equals(".classpath")){
				file.delete();
			}
		}
		cfg.mapOutputRoot(OutputRootId.GENERATOR_SRC, true, "-generator", "src/main/java");
		return cfg;
	}
	@Override
	protected EmfWorkspace loadSingleModel(File modelFile) throws Exception{
		EmfWorkspace result = super.loadSingleModel(modelFile);
		setMappedImplementationPackage(result);
		return result;
	}
	protected void setMappedImplementationPackage(EmfWorkspace result){
		for(Package pkg:result.getPrimaryModels()){
			if(pkg instanceof Model){
				Model model = (Model) pkg;
				Profile pf = ApplyProfileAction.applyNakedUmlProfile(model);
				Stereotype st = pf.getOwnedStereotype(StereotypeNames.PACKAGE);
				if(!model.isStereotypeApplied(st)){
					model.applyStereotype(st);
				}
				model.setValue(st, "mappedImplementationPackage", super.cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
				try{
					model.eResource().save(new HashMap());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	protected EmfWorkspace loadDirectory() throws IOException{
		EmfWorkspace result = super.loadDirectory();
		setMappedImplementationPackage(result);
		return result;
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
		return toSet(HibernateIntegratedAcrossMultipleProjects.class, Jbpm5IntegratedAcrossMultipleProjects.class, IntegrationTestsAcrossMultipleModels.class,
				WarBootstrapStep.class);
	}
	public File getOutputRoot(){
		return cfg.getOutputRoot();
	}
}