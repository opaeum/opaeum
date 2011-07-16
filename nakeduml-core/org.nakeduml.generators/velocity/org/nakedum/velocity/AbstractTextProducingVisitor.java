package org.nakedum.velocity;

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
import net.sf.nakeduml.feature.SortedProperties;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.nakeduml.java.metamodel.OJPathName;


public class AbstractTextProducingVisitor extends NakedElementOwnerVisitor {
	protected VelocityEngine ve;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	protected INakedRootObject currentRootObject;

	@Override
	public void visitRecursively(INakedElementOwner o) {
		if (o instanceof INakedRootObject) {
			INakedRootObject pkg = (INakedRootObject) o;
			this.currentRootObject = pkg;
			OJPathName utilPath = new OJPathName(pkg.getMappingInfo().getQualifiedJavaName() + ".util");
			UtilityCreator.setUtilPathName(utilPath);
		} else if (o instanceof INakedModelWorkspace) {
			OJPathName utilPath = new OJPathName(config.getMavenGroupId() + ".util");
			UtilityCreator.setUtilPathName(utilPath);
		}
		super.visitRecursively(o);
	}

	protected TransformationContext transformationContext;

	public void initialize(NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		this.config = config;
		this.transformationContext = context;
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

	protected void processTemplate(INakedElementOwner element, String templateResource, String destinationExpression, Enum<?> outputRootId,
			Map<String, Object> vars) {
		VelocityContext context = new VelocityContext();
		for (Map.Entry<String, Object> var : vars.entrySet()) {
			context.put(var.getKey(), var.getValue());
		}
		context.put(element.getMetaClass(), element);
		context.put("config", this.config);
		context.put("nakedWorkspace", this.workspace);
		context.put("stack1", new Stack<Object>());
		context.put("stack2", new Stack<Object>());
		context.put("stack3", new Stack<Object>());
		// generate
		CharArrayWriter contentWriter = new CharArrayWriter();
		CharArrayWriter fileNameWriter = new CharArrayWriter();
		try {
			org.apache.velocity.Template template = this.ve.getTemplate(templateResource);
			template.merge(context, contentWriter);
			this.ve.evaluate(context, fileNameWriter, templateResource,/* logTag */destinationExpression);
		} catch (Throwable e) {
			System.out.println(templateResource + " could not merge " + element.getName());
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

	protected SourceFolder getSourceFolder(OutputRoot outputRoot) {
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getIdentifier() : currentRootObject.getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	public void findOrCreateTextFile(CharArrayWriter outputBuilder, Enum<?> outputRootId, String... names) {
		OutputRoot outputRoot = config.getOutputRoot(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		sourceFolder.findOrCreateTextFile(Arrays.asList(names), new CharArrayTextSource(outputBuilder), outputRoot.overwriteFiles());
	}

	public void findOrCreateTextFile(SortedProperties outputBuilder, Enum<?> outputRootId, String... names) {
		OutputRoot outputRoot = config.getOutputRoot(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		sourceFolder.findOrCreateTextFile(Arrays.asList(names), new PropertiesSource(outputBuilder), outputRoot.overwriteFiles());
	}
}
