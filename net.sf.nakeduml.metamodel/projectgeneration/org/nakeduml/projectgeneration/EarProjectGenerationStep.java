package org.nakeduml.projectgeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.pomgeneration.ProjectRootPomStep;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextSource;

import org.apache.commons.io.FileUtils;

@StepDependency(phase = ProjectGenerationPhase.class, requires = { TextFileGenerator.class, ProjectRootPomStep.class }, before = { ProjectRootPomStep.class,
		TextFileGenerator.class })
public class EarProjectGenerationStep extends AbstractProjectGenerationStep {

	private static final String PROJECT_RESOURCES = "project-resources";
	private static final String PROJECT_WEBAPP = "project-webapp";


	public void generate() {
		File root = new File(config.getNakedUmlProjectGenRoot() + "/" + config.getProjectName());
		if (root.exists()) {
			try {
				FileUtils.deleteDirectory(root);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		config.mapOutputRoot(JavaTextSource.NAKED_PROJECT_ROOT, root);
		config.mapOutputRoot(JavaTextSource.NAKED_PROJECT_EAR_ROOT, new File(root, config.getProjectName() + "-ear"));
		config.mapOutputRoot(JavaTextSource.NAKED_PROJECT_EJB_ROOT, new File(root, config.getProjectName() + "-ejb"));
		config.mapOutputRoot(JavaTextSource.NAKED_PROJECT_WAR_ROOT, new File(root, config.getProjectName() + "-war"));
		mapOutput("project-src", new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/main/java"));
		mapOutput(PROJECT_RESOURCES, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/main/resources"));
		mapOutput("project-test-src", new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/test/java"));
		mapOutput("project-test-resources", new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/test/resources"));
		mapOutput(JavaTextSource.GEN_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/main/generated-java"));
		mapOutput(PropertiesSource.GEN_RESOURCE, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/main/generated-resources"));
		mapOutput(JavaTextSource.TEST_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT), "/src/test/generated-java"));
		mapOutput(CharArrayTextSource.TEST_RESOURCE, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_EJB_ROOT),
				"/src/test/generated-resources"));
		mapOutput(PROJECT_WEBAPP, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_WAR_ROOT), "/src/main/webapp"));
		createConfig("beans.xml", PROJECT_RESOURCES, "META-INF");
		createWarBeansXml();
		createConfig("faces-config.xml", PROJECT_WEBAPP, "WEB-INF");
		createConfig("web.xml", PROJECT_WEBAPP, "WEB-INF");
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

	private void createDefaultHtmlPages(String name) {
		final InputStream inputStream = this.getClass().getResourceAsStream("/"+name);
		String string;
		final StringBuilder outputBuilder = new StringBuilder();
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			try {
				while (null != (string = reader.readLine())) {
					outputBuilder.append(string).append('\n');
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(PROJECT_WEBAPP);
		List<String> names = Arrays.asList(name);
		or.findOrCreateTextFile(names, new TextSource() {
			@Override
			public char[] toCharArray() {
				return outputBuilder.toString().toCharArray();
			}
			@Override
			public boolean hasContent() {
				return true;
			}
		});
	}

	private void createConfig(String name, String target, String dir) {
		final InputStream inputStream = this.getClass().getResourceAsStream("/"+name);
		String string;
		final StringBuilder outputBuilder = new StringBuilder();
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			try {
				while (null != (string = reader.readLine())) {
					outputBuilder.append(string).append('\n');
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(target);
		List<String> names = Arrays.asList(dir, name);
		or.findOrCreateTextFile(names, new TextSource() {
			@Override
			public char[] toCharArray() {
				return outputBuilder.toString().toCharArray();
			}
			@Override
			public boolean hasContent() {
				return true;
			}
		});
	}

	private void createWarBeansXml() {
		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(PROJECT_WEBAPP);
		List<String> names = Arrays.asList("WEB-INF", "beans.xml");
		or.findOrCreateTextFile(names, new TextSource() {
			@Override
			public char[] toCharArray() {
				return "".toCharArray();
			}

			@Override
			public boolean hasContent() {
				return true;
			}
		});
	}

	private void mapOutput(String name, File destination) {
		destination.mkdirs();
		config.mapOutputRoot(name, destination);
	}
}
