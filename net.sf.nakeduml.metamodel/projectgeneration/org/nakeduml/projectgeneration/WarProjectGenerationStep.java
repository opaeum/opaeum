package org.nakeduml.projectgeneration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextSource;

@StepDependency(phase = ProjectGenerationPhase.class, requires = { TextFileGenerator.class }, before = { TextFileGenerator.class })
public class WarProjectGenerationStep extends AbstractProjectGenerationStep {

	public void generate() {
		createConfig("beans.xml", CharArrayTextSource.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("faces-config.xml", CharArrayTextSource.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("web.xml", CharArrayTextSource.WEBAPP_RESOURCE, "WEB-INF");
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

	private void createDefaultHtmlPages(String name) {
		final InputStream inputStream = this.getClass().getResourceAsStream("/" + name);
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

		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(CharArrayTextSource.WEBAPP_RESOURCE);
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
		final InputStream inputStream = this.getClass().getResourceAsStream("/" + name);
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

}
