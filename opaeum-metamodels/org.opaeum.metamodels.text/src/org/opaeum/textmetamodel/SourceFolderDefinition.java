package org.opaeum.textmetamodel;

public class SourceFolderDefinition{
	private boolean overwriteFiles = true;
	private boolean cleanDirectories = true;
	private ProjectNameStrategy projectNameStrategy;
	private String sourceFolderQualifier;
	private String projectQualifier;
	private String projectNameOverride;
	public SourceFolderDefinition(ProjectNameStrategy pns,String projectSuffix,String sourceFolder){
		super();
		this.projectNameStrategy = pns;
		this.projectQualifier = projectSuffix;
		this.sourceFolderQualifier = sourceFolder;
	}
	public void clearProjectNameOverride(){
		projectNameOverride = null;
	}
	public void overrideProjectName(String name){
		projectNameOverride = name;
	}
	public String generateProjectName(String workspaceIdentifier,String qualifiedWorkspaceIdentifier,String modelIdentifier){
		if(projectNameOverride == null){
			return projectNameStrategy.generateProjectName(projectQualifier, workspaceIdentifier, qualifiedWorkspaceIdentifier, modelIdentifier);
		}else{
			return projectNameOverride;
		}
	}
	public String generateSourceFolderName(String modelIdentifier){
		return projectNameStrategy.sourceFolderName(sourceFolderQualifier, modelIdentifier);
	}
	public boolean isOneProjectPerWorkspace(){
		return projectNameStrategy.isOneProjectPerWorkspace();
	}
	public void dontCleanDirectories(){
		cleanDirectories = false;
	}
	public boolean prefixModelIdentifierToSourceFolder(){
		return projectNameStrategy.definesSeparateSourceFoldersForModels();
	}
	public void dontCleanDirectoriesOrOverwriteFiles(){
		dontCleanDirectories();
		dontOverwriteFiles();
	}
	public void dontOverwriteFiles(){
		overwriteFiles = false;
	}
	public boolean overwriteFiles(){
		return overwriteFiles;
	}
	public boolean cleanDirectories(){
		return cleanDirectories;
	}
	public String getProjectQualifier(){
		return projectQualifier;
	}
}
