package org.opaeum.feature;

import java.io.File;

public interface ISourceFolderStrategy{
	void defineSourceFolders(OpaeumConfig config);
	boolean isSingleProjectStrategy();
	File calculateOutputRoot(File configFile,File projectRoot, String workspaceIdentifier);
}
