package org.opaeum.textmetamodel;

public class SourceFolderDefinition{
	private boolean overwriteFiles = true;
	private boolean cleanDirectories = true;
	private ProjectNameStrategy projectNameStrategy;
	private String sourceFolder;
	private String projectSuffix;
	public SourceFolderDefinition(ProjectNameStrategy pns,String projectSuffix,String sourceFolder){
		super();
		this.projectNameStrategy = pns;
		this.projectSuffix = projectSuffix;
		this.sourceFolder = sourceFolder;
	}
	public boolean isOneProjectPerWorkspace(){
		return projectNameStrategy != ProjectNameStrategy.MODEL_NAME_AND_SUFFIX;
	}
	public ProjectNameStrategy getProjectNameStrategy(){
		return projectNameStrategy;
	}
	public void dontCleanDirectories(){
		cleanDirectories = false;
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
	public String getSourceFolder(){
		return sourceFolder;
	}
	public String getProjectSuffix(){
		return projectSuffix;
	}
}
