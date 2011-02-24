package net.sf.nakeduml.pomgeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.textmetamodel.PropertiesSource;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;

public class AbstractMavenProjectProcess  {
	public static class MavenDirectories {
		public File srcFolder;
		public File testResourcesFolder;
		public File getTestJbossResourcesFolder;
		public File testFolder;
		public File srcResourceFolder;
		public File genSrcFolder;
		public File genTestResourcesFolder;
		public File genTestFolder;
		public File genSrcResourceFolder;
		public File webappFolder;
		public File warRoot;
	}

	protected static void transform(String outputRoot, String modelLocation, boolean initApp, Class<? extends TransformationStep>... features)
			throws Exception, IOException, FileNotFoundException {
		long start = System.currentTimeMillis();
		Model model = EmfWorkspaceLoader.loadModel(modelLocation);
		EcoreUtil.resolveAll(model);
		MavenDirectories mavenDirectories = new MavenDirectories();
		mavenDirectories.genSrcFolder = new File(outputRoot + "/src/main/generated-java");
		mavenDirectories.genSrcFolder.mkdirs();
		mavenDirectories.genSrcResourceFolder = new File(outputRoot + "/src/main/generated-resources");
		mavenDirectories.genSrcResourceFolder.mkdirs();
		mavenDirectories.genTestFolder = new File(outputRoot + "/src/test/generated-java");
		mavenDirectories.genTestFolder.mkdirs();
		mavenDirectories.genTestResourcesFolder = new File(outputRoot + "/src/test/generated-resources");
		mavenDirectories.genTestResourcesFolder.mkdirs();
		mavenDirectories.getTestJbossResourcesFolder = new File(outputRoot + "/src/test/generated-resource-jbossas");
		mavenDirectories.getTestJbossResourcesFolder.mkdirs();
		

		Properties props = new Properties();
		InputStreamReader inStream = EmfWorkspaceLoader.getInputStream(model, "properties");
		if (inStream != null) {
			props.load(inStream);
		}

		NakedUmlConfig cfg = new NakedUmlConfig(props, model.getName());
		transform(model, mavenDirectories, cfg, initApp, features);
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void transform(EmfWorkspace workspace, MavenDirectories mavenDirectories, NakedUmlConfig cfg, boolean initApp, Class<? extends TransformationStep>... features)
			throws IOException {
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC, false, "-domain", "src/main/generated-java");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC, false, "-domain", "src/test/generated-java");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE, false, "-domain", "src/test/generated-resources" );
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE, false, "-domain", "src/main/generated-resources" );
		OutputRoot jbossResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS, false, "-adapter", "src/test/jboss-resources" );
		jbossResources.dontCleanDirectories();
		jbossResources.dontOverwriteFiles();
		OutputRoot webAppRoot = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, true, "-webapp", "src/main/webapp");
		webAppRoot.dontCleanDirectories();
		webAppRoot.dontOverwriteFiles();
		
		TransformationProcess process = new TransformationProcess();
		HashSet<Class<? extends TransformationStep>> classes = new HashSet<Class<? extends TransformationStep>>(Arrays.asList(features));
		process.execute(cfg, workspace, classes);
		cfg.store(getOutputStream(workspace.getEntryModel(), "properties"));
		workspace.getMappingInfo().store();
	}

	public static OutputStreamWriter getOutputStream(Package model, String extension) throws IOException {
		OutputStream outStream = model.eResource().getResourceSet().getURIConverter()
				.createOutputStream(model.eResource().getURI().trimFileExtension().appendFileExtension(extension));
		return new OutputStreamWriter(outStream);
	}

}
