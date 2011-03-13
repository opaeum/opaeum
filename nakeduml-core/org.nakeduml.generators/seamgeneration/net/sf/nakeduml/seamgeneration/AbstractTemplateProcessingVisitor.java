package net.sf.nakeduml.seamgeneration;

import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.textmetamodel.ResourceLoader;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionElement;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class AbstractTemplateProcessingVisitor extends UserInteractionElementVisitor  {
	protected VelocityEngine ve;

	@Override
	public void initialize(UserInteractionWorkspace workspace, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(workspace, config, textWorkspace);
		this.ve = new VelocityEngine();
		Properties velocityProperties = new Properties();
		velocityProperties.put("resource.loader", "class");
		velocityProperties.put("class.resource.loader.class", ResourceLoader.class.getName());
		try {
			this.ve.init(velocityProperties);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		}
	
	}

	protected void processTemplate(UserInteractionElement element, String templateResource, String destinationExpression, String outputRootName) {
		VelocityContext context = new VelocityContext();
		context.put("rootElement", element);
		context.put("config", this.config);
		context.put("model", this.workspace);
		context.put("workspace", this.workspace);
		context.put("stack1", new Stack());
		context.put("stack2", new Stack());
		context.put("stack3", new Stack());
		// generate
		CharArrayWriter contentWriter = new CharArrayWriter();
		CharArrayWriter fileNameWriter = new CharArrayWriter();
		try {
			Template template = this.ve.getTemplate(templateResource);
			template.merge(context, contentWriter);
			this.ve.evaluate(context, fileNameWriter, templateResource, destinationExpression);
		} catch (Throwable e) {
//			System.out.println(templateResource + " could not merge " + element.getMappingInfo().getQualifiedUmlName());
			System.out.println(new String(contentWriter.toCharArray()));
			e.printStackTrace();
		}
		fileNameWriter.close();
		List<String> path = Arrays.asList(new String(fileNameWriter.toCharArray()).split("[/]"));
		contentWriter.close();
		TextOutputRoot outputRoot=textWorkspace.findOrCreateTextOutputRoot(outputRootName);
		outputRoot.findOrCreateTextFile(path, new CharArrayTextSource(contentWriter));
	}
}
