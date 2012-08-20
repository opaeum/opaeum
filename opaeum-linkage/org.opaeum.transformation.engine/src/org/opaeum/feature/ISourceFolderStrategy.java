package org.opaeum.feature;

import java.io.File;

public interface ISourceFolderStrategy{
	void defineSourceFolders(OpaeumConfig config);
	boolean isSingleProjectStrategy();
	File calculateOutputRoot(File projectRoot,String workspaceIdentifier);
}
