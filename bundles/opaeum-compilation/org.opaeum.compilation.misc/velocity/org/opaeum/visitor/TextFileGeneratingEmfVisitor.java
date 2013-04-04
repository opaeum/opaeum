package org.opaeum.visitor;

import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.opaeum.EmfElementVisitor;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
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
	protected EmfWorkspace workspace;
	protected String getProjectName(SourceFolderDefinition outputRoot,Element p){
		return outputRoot.generateProjectName(workspace.getIdentifier(), config.getMavenGroupId(),
				getRootObjectIdentifier(p));
	}
	public Set<TextOutputNode> getTextFiles(){
		return textFiles;
	}
	protected TextFile createTextPath(ISourceFolderIdentifier id,List<String> names,Element e){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot, e);
		TextFile file = or.findOrCreateTextFile(names, outputRoot.overwriteFiles());
		// this.textFiles.add(file);
		return file;
	}
	protected synchronized SourceFolder getSourceFolder(SourceFolderDefinition outputRoot,Element p){
		TextProject textProject = textWorkspace.findOrCreateTextProject(getProjectName(outputRoot, p));
		String sourceFolder = outputRoot.generateSourceFolderName(getRootObjectIdentifier(p));
		SourceFolder or = textProject.findOrCreateSourceFolder(sourceFolder, outputRoot.cleanDirectories());
		return or;
	}
	private String getRootObjectIdentifier(Element p){
		return EmfPackageUtil.getIdentifier(EmfElementFinder.getRootObject(p));
	}
	public void release(){
		textFiles = null;
		textWorkspace = null;
		workspace = null;
	}
}
