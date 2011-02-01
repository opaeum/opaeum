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
import net.sf.nakeduml.pomgeneration.ProjectWarPomStep;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextSource;

@StepDependency(phase = ProjectGenerationPhase.class, requires = { TextFileGenerator.class, ProjectWarPomStep.class }, before = { ProjectWarPomStep.class,
	TextFileGenerator.class })
public class WarProjectGenerationStep extends AbstractProjectGenerationStep {

	private static final String PROJECT_TEST_RESOURCES = "project-test-resources";
	private static final String PROJECT_TEST_SRC = "project-test-src";
	private static final String PROJECT_SRC = "project-src";
	private static final String PROJECT_RESOURCES = "project-resources";
	private static final String PROJECT_WEBAPP = "project-webapp";

	public void generate() {
		
		File root = createRootFolder();
		config.mapOutputRoot(JavaTextSource.NAKED_PROJECT_ROOT, root);
		
//		mapOutput(PROJECT_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/main/java"));
//		mapOutput(PROJECT_RESOURCES, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/main/resources"));
//		mapOutput(PROJECT_TEST_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/test/java"));
//		mapOutput(PROJECT_TEST_RESOURCES, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/test/resources"));
		mapOutput(JavaTextSource.GEN_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/main/generated-java"));
		mapOutput(PropertiesSource.GEN_RESOURCE, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/main/generated-resources"));
		mapOutput(JavaTextSource.TEST_SRC, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/test/generated-java"));
//		mapOutput(CharArrayTextSource.TEST_RESOURCE, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT),
//				"/src/test/generated-resources"));
//		mapOutput(PROJECT_WEBAPP, new File(config.getMappedDestination(JavaTextSource.NAKED_PROJECT_ROOT), "/src/main/webapp"));
//
//		createConfig("beans.xml", PROJECT_WEBAPP, "WEB-INF");
//		createConfig("faces-config.xml", PROJECT_WEBAPP, "WEB-INF");
//		createConfig("web.xml", PROJECT_WEBAPP, "WEB-INF");
//		createDefaultHtmlPages("home.xhtml");
//		createDefaultHtmlPages("index.html");
//		createDefaultHtmlPages("template.xhtml");
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
