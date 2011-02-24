package net.sf.nakeduml.javageneration;

import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.ResourceLoader;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class AbstractTextProducingVisitor extends AbstractJavaProducingVisitor {
	protected VelocityEngine ve;

	@Override
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		this.initialize(workspace, config, textWorkspace);
	}

	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config, TextWorkspace textWorkspace) {
		this.workspace = workspace;
		this.config = config;
		this.textWorkspace = textWorkspace;
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

	protected void processTemplate(INakedElement element, String templateResource, String destinationExpression, Enum<?> outputRootId) {
		Map<String, Object> emptyMap = Collections.emptyMap();
		processTemplate(element, templateResource, destinationExpression, outputRootId, emptyMap);
	}

	protected void processTemplate(INakedElement element, String templateResource, String destinationExpression, Enum<?> outputRootId,
			Map<String, Object> vars) {
		VelocityContext context = new VelocityContext();
		for (Map.Entry<String, Object> var : vars.entrySet()) {
			context.put(var.getKey(), var.getValue());
		}
		context.put(element.getMetaClass(), element);
		context.put("config", this.config);
		context.put("workspace", this.workspace);
		context.put("stack1", new Stack<Object>());
		context.put("stack2", new Stack<Object>());
		context.put("stack3", new Stack<Object>());
		// generate
		CharArrayWriter contentWriter = new CharArrayWriter();
		CharArrayWriter fileNameWriter = new CharArrayWriter();
		try {
			Template template = this.ve.getTemplate(templateResource);
			template.merge(context, contentWriter);
			this.ve.evaluate(context, fileNameWriter, templateResource,/* logTag */destinationExpression);
		} catch (Throwable e) {
			System.out.println(templateResource + " could not merge " + element.getPathName());
			System.out.println(new String(contentWriter.toCharArray()));
			e.printStackTrace();
		}
		fileNameWriter.close();
		List<String> path = Arrays.asList(new String(fileNameWriter.toCharArray()).split("[/]"));
		contentWriter.close();
		if (Boolean.TRUE.equals(context.get("shouldGenerate"))) {
			OutputRoot outputRoot = config.getOutputRoot(outputRootId);
			SourceFolder sourceFolder = getSourceFolder(outputRoot);
			sourceFolder.findOrCreateTextFile(path, new CharArrayTextSource(contentWriter), outputRoot.overwriteFiles());
		}
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		if (root instanceof INakedModelWorkspace) {
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		} else {
			return super.getChildren(root);
		}
	}
}
