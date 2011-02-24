package org.nakeduml.projectgeneration;

import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.List;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;

public abstract class AbstractProjectGenerationStep extends AbstractJavaProducingVisitor implements TransformationStep {
	protected INakedModel currentModel;

	@Override
	public void visitRecursively(INakedElementOwner o) {
		super.visitRecursively(o);
		if (o instanceof INakedModel) {
			this.currentModel = (INakedModel) o;
		}
	}

	public abstract void visitModel(INakedModel model);

	protected void findOrCreateTextFile(final CharArrayWriter outputBuilder, Enum<?> outputRootId, String... names) {
		OutputRoot outputRoot = config.getOutputRoot(outputRootId);
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getName() : currentModel.getFileName();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		List<String> nameList = Arrays.asList(names);
		or.findOrCreateTextFile(nameList, new CharArrayTextSource(outputBuilder), outputRoot.overwriteFiles());
	}
}
