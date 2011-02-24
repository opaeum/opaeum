package net.sf.nakeduml.feature;

public class OutputRoot {
	boolean overwriteFiles=true;
	boolean cleanDirectories=true;
	boolean useEntryModelName;
	String sourceFolder;
	String projectSuffix;
	
	public OutputRoot(boolean useEntryModelName, String projectSuffix, String sourceFolder) {
		super();
		this.useEntryModelName = useEntryModelName;
		this.projectSuffix = projectSuffix;
		this.sourceFolder = sourceFolder;
	}
	public boolean useWorkspaceName(){
		return useEntryModelName;
	}
	public void dontCleanDirectories(){
		cleanDirectories=false;
	}
	public void dontOverwriteFiles(){
		overwriteFiles=false;
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
