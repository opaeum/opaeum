package net.sf.nakeduml.feature;

public interface ISourceFolderStrategy{
	void defineSourceFolders(NakedUmlConfig config);
	boolean isSingleProjectStrategy();
}
