package org.nakeduml.tinker.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;
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
import org.opaeum.emf.extraction.StereotypeApplicationExtractor;
import org.opaeum.emf.load.EmfWorkspaceLoader;
import org.opaeum.feature.DefaultTransformationLog;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.feature.WorkspaceMappingInfo;
import org.opaeum.filegeneration.TextFileDeleter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.generation.features.OclExpressionExecution;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextWorkspace;

public class GenerateTinkerTest {

	public static void main(String[] args) throws Exception {
		GenerateTinkerTest generateTest = new GenerateTinkerTest();
		generateTest.generate("tinker-test");
		generateTest.generateIntegrationCode();
	}

	TransformationProcess process = new TransformationProcess();
	WorkspaceMappingInfo mappingInfo;
	private String workspaceRoot;
	private ResourceSet resourceSet;
	private OpaeumConfig cfg;

	public GenerateTinkerTest() {
		String workspaceRoot = "/home/pieter/workspace-apaeum";
		this.resourceSet = EmfWorkspaceLoader.setupStandAloneAppForUML2();
		this.workspaceRoot = workspaceRoot;
		this.cfg = new OpaeumConfig(new File(workspaceRoot + "/nakeduml/opaeum-tests/opaeum-test-models/Models/tinker/tinker-test-nakeduml.properties"));
		this.cfg.setOutputRoot(new File(workspaceRoot + "/nakeduml/opaeum-tests/tinker"));
		process.initialize(cfg, getSteps());
	}

	public void generate(String modelName) throws Exception {
		long start = System.currentTimeMillis();
		File modelFile = new File(workspaceRoot + "/nakeduml/opaeum-tests/opaeum-test-models/Models/tinker/" + modelName + ".uml");
		process.replaceModel(EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg));
		process.execute(new DefaultTransformationLog());
		System.out.println(modelName + " took " + (System.currentTimeMillis() - start) + "ms");
	}

	private Set<Class<? extends ITransformationStep>> getSteps() {
		Set<Class<? extends ITransformationStep>> steps = new HashSet<Class<? extends ITransformationStep>>();
		steps.add(OclExpressionExecution.class);
		steps.add(StereotypeApplicationExtractor.class);
		steps.add(ExtendedCompositionSemantics.class);
		steps.add(JavaMetaInfoMapGenerator.class);
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
		// steps.add(TinkerAuditOrignalClassTransformation.class);
		steps.addAll(LinkagePhase.getAllSteps());
		return steps;
	}

	public void generateIntegrationCode() throws FileNotFoundException, IOException {
		INakedModelWorkspace workspace = process.findModel(INakedModelWorkspace.class);
		process.replaceModel(EmfWorkspaceLoader.loadDirectory(resourceSet, new File(workspaceRoot + "/nakeduml/opaeum-tests/opaeum-test-models/Models/tinker/"), cfg));
		workspace.clearGeneratingModelOrProfiles();
		for (INakedRootObject ro : workspace.getPrimaryRootObjects()) {
			if (ro instanceof INakedModel) {
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
//		this.cfg.getWorkspaceMappingInfo().store();
	}
}
