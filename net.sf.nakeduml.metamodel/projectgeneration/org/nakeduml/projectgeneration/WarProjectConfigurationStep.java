package org.nakeduml.projectgeneration;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.CharArrayTextSource.OutputRootId;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.pomgeneration.ProjectWarPomStep;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextSource;

@StepDependency(phase = ProjectGenerationPhase.class, requires = { ProjectWarPomStep.class, ProjectTestGenerationStep.class,
		TextFileGenerator.class }, before = { TextFileGenerator.class })
public class WarProjectGenerationStep extends AbstractProjectGenerationStep {

	@VisitBefore
	public void visitModel(INakedModel model) {
		createConfig("beans.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("faces-config.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("web.xml", CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, "WEB-INF");
		createConfig("arquillian.xml", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE);
		createConfig("log4j.properties", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE);
		createConfig("hornetq-jms.xml", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS);
		createConfig("jndi.properties", CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS);
		createDefaultHtmlPages("home.xhtml");
		createDefaultHtmlPages("index.html");
		createDefaultHtmlPages("template.xhtml");
	}

	private void createConfig(String name, OutputRootId outputRootId) {
		CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, outputRootId, name);
	}

	private void createDefaultHtmlPages(String name) {
		final CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, name);
	}


	private void createConfig(String name, Enum<?> outputRootId, String dir) {
		CharArrayWriter outputBuilder = copyResource(name);
		findOrCreateTextFile(outputBuilder, outputRootId, dir, name);
	}

	private CharArrayWriter copyResource(String name) {
		final InputStream inputStream = this.getClass().getResourceAsStream("/" + name);
		String string;
		final CharArrayWriter outputBuilder = new CharArrayWriter();
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
		return outputBuilder;
	}
}
