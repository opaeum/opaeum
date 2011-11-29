package org.nakeduml.tinker.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.feature.DefaultTransformationLog;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.WorkspaceMappingInfo;
import net.sf.nakeduml.filegeneration.TextFileDeleter;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.tinker.emf.extraction.TinkerFeatureExtractor;
import org.nakeduml.tinker.emf.extraction.TinkerNameSpaceExtractor;
import org.nakeduml.tinker.generator.TinkerCollectionStep;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.nakeduml.tinker.generator.TinkerQualifierGenerator;
import org.nakeduml.tinker.javageneration.composition.TinkerComponentInitializer;
import org.nakeduml.tinker.javageneration.composition.TinkerCompositionNodeImplementor;
import org.nakeduml.tinker.linkage.TinkerNakedParsedOclStringResolver;
import org.nakeduml.tinker.linkage.TinkerQualifierLogicCalculator;
import org.nakeduml.tinker.linkage.TinkerQualifierResolver;

public class GenerateTinkerTest {

	public static void main(String[] args) throws Exception  {
		GenerateTinkerTest generateTest = new GenerateTinkerTest();
		generateTest.generate("tinker-test");
		generateTest.generateIntegrationCode();
	}
	
	TransformationProcess process = new TransformationProcess();
	WorkspaceMappingInfo mappingInfo;
	private String workspaceRoot;
	private ResourceSet resourceSet;
	private NakedUmlConfig cfg;
	public GenerateTinkerTest(){
		String workspaceRoot = "/home/pieter/workspace-opium";
		this.resourceSet = EmfWorkspaceLoader.setupStandAloneAppForUML2();
		this.workspaceRoot = workspaceRoot;
		this.cfg = new NakedUmlConfig(new File(workspaceRoot + "/nakeduml/nakeduml-tests/TestModels/Models/tinker/nakeduml.properties"));
		this.cfg.setOutputRoot(new File(workspaceRoot +"/nakeduml/nakeduml-tests/tinkertests") );
		process.initialize(cfg, getSteps());
	}
	public void generate(String modelName) throws Exception{
		long start = System.currentTimeMillis();
		File modelFile = new File(workspaceRoot + "/nakeduml/nakeduml-tests/TestModels/Models/tinker/" + modelName + ".uml");
		process.replaceModel(EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg));
		process.execute(new DefaultTransformationLog());
		System.out.println(modelName + " took " + (System.currentTimeMillis() - start) + "ms");
	}
	private Set<Class<? extends ITransformationStep>> getSteps(){
		Set<Class<? extends ITransformationStep>> steps = new HashSet<Class<? extends ITransformationStep>>();
		steps.add(OclExpressionExecution.class);
		steps.add(StereotypeApplicationExtractor.class);
		steps.add(ExtendedCompositionSemantics.class);
		steps.add(TinkerImplementNodeStep.class);
		steps.add(TinkerNameSpaceExtractor.class);
		steps.add(TinkerCollectionStep.class);
		steps.add(TinkerFeatureExtractor.class);
		steps.add(TinkerNakedParsedOclStringResolver.class);
		steps.add(TinkerQualifierLogicCalculator.class);
		steps.add(TinkerQualifierResolver.class);
		steps.add(TinkerQualifierGenerator.class);
		steps.add(TinkerCompositionNodeImplementor.class);
		steps.add(TinkerComponentInitializer.class);
		steps.addAll(LinkagePhase.getAllSteps());
		return steps;
	}
	public void generateIntegrationCode() throws FileNotFoundException,IOException{
		INakedModelWorkspace workspace = process.findModel(INakedModelWorkspace.class);
		process.replaceModel(EmfWorkspaceLoader.loadDirectory(resourceSet, new File(workspaceRoot + "/nakeduml/nakeduml-tests/TestModels/Models/tinker/"), cfg));
		workspace.clearGeneratingModelOrProfiles();
		for(INakedRootObject ro:workspace.getPrimaryRootObjects()){
			if(ro instanceof INakedModel){
				workspace.addGeneratingRootObject(ro);
			}
		}
		process.integrate(new DefaultTransformationLog());
		System.out.println("Writing files");
		TextFileDeleter tfd = new TextFileDeleter();
		tfd.initialize(cfg);
		tfd.startVisiting(process.findModel(TextWorkspace.class));
		TextFileGenerator tff = new TextFileGenerator();
		tff.initialize(cfg);
		tff.startVisiting(process.findModel(TextWorkspace.class));
		this.cfg.getWorkspaceMappingInfo().store();
	}
}
