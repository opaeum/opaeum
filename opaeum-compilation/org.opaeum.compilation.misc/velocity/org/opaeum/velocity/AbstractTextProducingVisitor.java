package org.opaeum.velocity;

import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.CharArrayTextSource;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.PropertiesSource;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.util.SortedProperties;

public class AbstractTextProducingVisitor extends NakedElementOwnerVisitor{
	protected INakedModelWorkspace workspace;
	public Set<TextFile> getTextFiles(){
		return textFiles;
	}
	protected VelocityEngine ve;
	protected Set<TextFile> textFiles=new HashSet<TextFile>();
	protected OpaeumConfig config;
	protected TextWorkspace textWorkspace;
	protected INakedRootObject currentRootObject;
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(o instanceof INakedRootObject){
			INakedRootObject pkg = (INakedRootObject) o;
			this.currentRootObject = pkg;
			OJPathName utilPath = new OJPathName(pkg.getMappingInfo().getQualifiedJavaName() + ".util");
			UtilityCreator.setUtilPathName(utilPath);
		}else if(o instanceof INakedModelWorkspace){
			OJPathName utilPath = new OJPathName(config.getMavenGroupId() + ".util");
			UtilityCreator.setUtilPathName(utilPath);
		}
		super.visitRecursively(o);
	}
	public void initialize(OpaeumConfig config,TextWorkspace textWorkspace, INakedModelWorkspace workspace){
		this.workspace=workspace;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.ve = new VelocityEngine();
		Properties velocityProperties = new Properties();
		velocityProperties.put("resource.loader", "class");
		velocityProperties.put("class.resource.loader.class", ResourceLoader.class.getName());
		try{
			this.ve.init(velocityProperties);
		}catch(Exception e){
			if(e instanceof RuntimeException){
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		}
	}
	protected void processTemplate(INakedElement element,String templateResource,String destinationExpression,ISourceFolderIdentifier outputRootId){
		Map<String,Object> emptyMap = Collections.emptyMap();
		processTemplate(element, templateResource, destinationExpression, outputRootId, emptyMap);
	}
	protected void processTemplate(INakedElementOwner element,String templateResource,String destinationExpression,ISourceFolderIdentifier outputRootId,Map<String,Object> vars){
		VelocityContext context = new VelocityContext();
		for(Map.Entry<String,Object> var:vars.entrySet()){
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
		try{
			org.apache.velocity.Template template = this.ve.getTemplate(templateResource);
			template.merge(context, contentWriter);
			this.ve.evaluate(context, fileNameWriter, templateResource,/* logTag */destinationExpression);
		}catch(Throwable e){
			transformationContext.getLog().error(templateResource + " could not merge " + element.getName(), e);
			transformationContext.getLog().info(new String(contentWriter.toCharArray()));
		}
		fileNameWriter.close();
		List<String> path = Arrays.asList(new String(fileNameWriter.toCharArray()).split("[/]"));
		contentWriter.close();
		if(Boolean.TRUE.equals(context.get("shouldGenerate"))){
			SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(outputRootId);
			SourceFolder sourceFolder = getSourceFolder(outputRoot);
			sourceFolder.findOrCreateTextFile(path, new CharArrayTextSource(contentWriter), outputRoot.overwriteFiles());
		}
	}
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		}else{
			return super.getChildren(root);
		}
	}
	protected SourceFolder getSourceFolder(SourceFolderDefinition outputRoot){
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getIdentifier() : currentRootObject.getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	public void findOrCreateTextFile(CharArrayWriter outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		TextFile tf = sourceFolder.findOrCreateTextFile(Arrays.asList(names), new CharArrayTextSource(outputBuilder), outputRoot.overwriteFiles());
		textFiles.add(tf);
	}
	public void findOrCreateTextFile(SortedProperties outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		sourceFolder.findOrCreateTextFile(Arrays.asList(names), new PropertiesSource(outputBuilder), outputRoot.overwriteFiles());
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
}
