package net.sf.nakeduml.feature;

import java.io.File;

public interface ISourceFolderStrategy{
	void defineSourceFolders(NakedUmlConfig config);
	boolean isSingleProjectStrategy();
	File calculateOutputRoot(File configFile,File projectRoot, String workspaceIdentifier);
}
