package org.opeum.feature;

import java.io.File;

public interface ISourceFolderStrategy{
	void defineSourceFolders(OpeumConfig config);
	boolean isSingleProjectStrategy();
	File calculateOutputRoot(File configFile,File projectRoot, String workspaceIdentifier);
}
