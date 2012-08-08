package org.opaeum.visitor;

import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.opaeum.EmfElementVisitor;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class TextFileGeneratingEmfVisitor extends EmfElementVisitor{
	protected TextWorkspace textWorkspace;
	protected Set<TextOutputNode> textFiles;
	protected OpaeumConfig config;
	protected ModelWorkspace workspace;
	protected String getProjectName(SourceFolderDefinition outputRoot, Element p){
		String projectPrefix = null;
		switch(outputRoot.getProjectNameStrategy()){
		case QUALIFIED_WORKSPACE_NAME_AND_SUFFIX:
			projectPrefix = config.getMavenGroupId() + "." + workspace.getIdentifier();
			break;
		case SUFFIX_ONLY:
			projectPrefix = "";
			break;
		case MODEL_NAME_AND_SUFFIX:
			projectPrefix = getRootObjectIdentifier(p);
		case WORKSPACE_NAME_AND_SUFFIX:
			projectPrefix = workspace.getIdentifier();
			break;
		case QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER:
			projectPrefix = config.getMavenGroupId() + "." + workspace.getIdentifier();
			break;
		case WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER:
			projectPrefix = workspace.getIdentifier();
			break;
		}
		return projectPrefix + outputRoot.getProjectSuffix();
	}
	public Set<TextOutputNode> getTextFiles(){
		return textFiles;
	}
	protected TextFile createTextPath(ISourceFolderIdentifier id,List<String> names, Element e){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot,e);
		TextFile file = or.findOrCreateTextFile(names, outputRoot.overwriteFiles());
//		this.textFiles.add(file);
		return file;
	}
	protected synchronized SourceFolder getSourceFolder(SourceFolderDefinition outputRoot, Element p){
		TextProject textProject = textWorkspace.findOrCreateTextProject(getProjectName(outputRoot,p));
		String sourceFolder = outputRoot.getSourceFolder();
		if(outputRoot.prefixModelIdentifierToSourceFolder()){
			// force multiple source folders per model
			sourceFolder = getRootObjectIdentifier(p) + "/" + outputRoot.getSourceFolder();
		}
		SourceFolder or = textProject.findOrCreateSourceFolder(sourceFolder, outputRoot.cleanDirectories());
		return or;
	}
	private String getRootObjectIdentifier(Element p){
		return p.eResource().getURI().trimFileExtension().lastSegment();
	}
	public void release(){
		textFiles = null;
		textWorkspace = null;
		workspace = null;
	}
}
