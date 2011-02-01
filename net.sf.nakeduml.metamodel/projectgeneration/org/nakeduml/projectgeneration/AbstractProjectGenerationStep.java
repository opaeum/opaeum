package org.nakeduml.projectgeneration;

import java.io.File;
import java.io.IOException;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.pomgeneration.AbstractMavenProjectProcess;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.apache.commons.io.FileUtils;

public abstract class AbstractProjectGenerationStep extends AbstractMavenProjectProcess implements TransformationStep {

	protected TextWorkspace textWorkspace;
	protected NakedUmlConfig config;

	public void initialize(NakedUmlConfig config, TextWorkspace textWorkspace) {
		this.textWorkspace = textWorkspace;
		this.config = config;
	}
	public abstract void generate();
	protected File createRootFolder() {
		File root = new File(config.getNakedUmlProjectGenRoot() + "/" + config.getNakedUmlProjectGenName());
		if (root.exists()) {
//			try {
//				FileUtils.deleteDirectory(root);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
		}
		return root;
	}
}
