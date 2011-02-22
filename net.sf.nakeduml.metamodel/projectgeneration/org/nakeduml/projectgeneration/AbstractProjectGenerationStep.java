package org.nakeduml.projectgeneration;

import java.io.File;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.pomgeneration.AbstractMavenProjectProcess;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public abstract class AbstractProjectGenerationStep extends AbstractJavaProducingVisitor implements TransformationStep {

	public abstract void visitModel(INakedModel model);
	protected File createRootFolder() {
		File root = new File(config.getNakedUmlProjectGenRoot() + "/" + config.getProjectName());
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
