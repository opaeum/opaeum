package net.sf.nakeduml.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import net.sf.nakeduml.emf.load.UML2ModelLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.textmetamodel.PropertiesSource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;

public class AbstractMavenProjectProcess {
	public static class MavenDirectories {
		public File srcFolder;
		public File testResourcesFolder;
		public File testFolder;
		public File srcResourceFolder;
		public File jpaRoot;
	}

	protected static void transform(String outputRoot, String modelLocation, Class<? extends TransformationStep>... features)
			throws Exception, IOException, FileNotFoundException {
		long start = System.currentTimeMillis();
		Model model = UML2ModelLoader.loadModel(modelLocation);
		EcoreUtil.resolveAll(model);
		MavenDirectories mavenDirectories = new MavenDirectories();
		mavenDirectories.srcFolder = new File(outputRoot + "/src/main/generated-java");
		mavenDirectories.srcFolder.mkdirs();
		mavenDirectories.srcResourceFolder = new File(outputRoot + "/src/main/generated-resources");
		mavenDirectories.srcResourceFolder.mkdirs();
		mavenDirectories.testFolder = new File(outputRoot + "/src/test/generated-java");
		mavenDirectories.testFolder.mkdirs();
		mavenDirectories.testResourcesFolder = new File(outputRoot + "/src/test/generated-resources");
		mavenDirectories.testResourcesFolder.mkdirs();
		mavenDirectories.jpaRoot = new File(outputRoot);
		Properties props = new Properties();
		InputStreamReader inStream = getInputStream(model, "properties");
		if (inStream != null) {
			props.load(inStream);
		}

		NakedUmlConfig cfg = new NakedUmlConfig(props, model.getName());
		transform(model, mavenDirectories, cfg, features);
	}

	public static void transform(Model model, MavenDirectories mavenDirectories, NakedUmlConfig cfg,
			Class<? extends TransformationStep>... features) throws IOException {
		cfg.mapOutputRoot(JavaTextSource.GEN_SRC, mavenDirectories.srcFolder);
		cfg.mapOutputRoot(JavaTextSource.TEST_SRC, mavenDirectories.testFolder);
		cfg.mapOutputRoot(CharArrayTextSource.TEST_RESOURCE, mavenDirectories.testResourcesFolder);
		// TODO remove this
		cfg.mapOutputRoot(CharArrayTextSource.EJB_JAR_RESOURCE, mavenDirectories.testResourcesFolder);
		cfg.mapOutputRoot(PropertiesSource.GEN_RESOURCE, mavenDirectories.srcResourceFolder);
		cfg.mapOutputRoot(JavaTextSource.JPA_ROOT, mavenDirectories.jpaRoot);

		TransformationProcess process = new TransformationProcess();
		WorkspaceMappingInfoImpl mappingInfo = new WorkspaceMappingInfoImpl(getInputStream(model, "mappinginfo"));
		HashSet<Class<? extends TransformationStep>> classes = new HashSet<Class<? extends TransformationStep>>(Arrays.asList(features) );
		process.execute(cfg, new EmfWorkspace(model, mappingInfo),classes);
		cfg.store(getOutputStream(model, "properties"));
		mappingInfo.store(getOutputStream(model, "mappinginfo"));
	}

	public static OutputStreamWriter getOutputStream(Model model, String extension) throws IOException {
		OutputStream outStream = model.eResource().getResourceSet().getURIConverter()
				.createOutputStream(model.eResource().getURI().trimFileExtension().appendFileExtension(extension));
		return new OutputStreamWriter(outStream);
	}

	public static InputStreamReader getInputStream(Model model, String extension) {
		try {
			URIConverter uriConverter = model.eResource().getResourceSet().getURIConverter();
			URI uri = model.eResource().getURI().trimFileExtension().appendFileExtension(extension);
			if (uriConverter.exists(uri, null)) {
				InputStream inStream = uriConverter.createInputStream(uri);
				return new InputStreamReader(inStream);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
