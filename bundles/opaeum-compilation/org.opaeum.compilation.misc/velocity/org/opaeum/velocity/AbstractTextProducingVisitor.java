package org.opaeum.velocity;

import java.io.CharArrayWriter;
import java.util.ArrayList;
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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.CharArrayTextSource;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.PropertiesSource;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.util.SortedProperties;
import org.opaeum.visitor.TextFileGeneratingVisitor;

public class AbstractTextProducingVisitor extends TextFileGeneratingVisitor{
	protected VelocityEngine ve;
	protected Set<TextFile> textFiles;
	protected OJUtil ojUtil;
	@Override
	public void release(){
		super.release();
		textFiles = null;
	}
	@Override
	public void visitRecursively(Element o){
		if(EmfPackageUtil.isRootObject(o)){
			Package pkg = (Package) o;
			OJPathName utilPath = ojUtil.packagePathname(pkg).getCopy();
			utilPath.append("util");
			UtilityCreator.setUtilPathName(utilPath);
		}else if(o instanceof ModelWorkspace){
			OJPathName utilPath = new OJPathName(config.getMavenGroupId() + ".util");
			UtilityCreator.setUtilPathName(utilPath);
		}
		super.visitRecursively(o);
	}
	public void initialize(OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		this.ojUtil = ojUtil;
		this.workspace = workspace;
		this.config = config;
		super.textFiles = new HashSet<TextOutputNode>();
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
	protected void processTemplate(Element element,String templateResource,String destinationExpression,ISourceFolderIdentifier outputRootId){
		Map<String,Object> emptyMap = Collections.emptyMap();
		processTemplate(element, templateResource, destinationExpression, outputRootId, emptyMap);
	}
	protected void processTemplate(Element element,String templateResource,String destinationExpression,ISourceFolderIdentifier outputRootId,
			Map<String,Object> vars){
		VelocityContext context = new VelocityContext();
		for(Map.Entry<String,Object> var:vars.entrySet()){
			context.put(var.getKey(), var.getValue());
		}
		if(element.eClass() != null){
			context.put(NameConverter.decapitalize(element.eClass().getName()), element);
		}
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
			transformationContext.getLog().error(templateResource + " could not merge " + ((NamedElement) element).getName(), e);
			transformationContext.getLog().info(new String(contentWriter.toCharArray()));
		}
		fileNameWriter.close();
		List<String> path = Arrays.asList(new String(fileNameWriter.toCharArray()).split("[/]"));
		contentWriter.close();
		if(Boolean.TRUE.equals(context.get("shouldGenerate"))){
			createTextPath(outputRootId, path).setTextSource(new CharArrayTextSource(contentWriter));
		}
	}
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof ModelWorkspace){
			return new ArrayList<Element>(((ModelWorkspace) root).getGeneratingModelsOrProfiles());
		}else{
			return super.getChildren(root);
		}
	}
	public void findOrCreateTextFile(CharArrayWriter outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		createTextPath(outputRootId, Arrays.asList(names)).setTextSource(new CharArrayTextSource(outputBuilder));
	}
	public void findOrCreateTextFile(SortedProperties outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		createTextPath(outputRootId, Arrays.asList(names)).setTextSource(new PropertiesSource(outputBuilder));
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
}
