package net.sf.nakeduml.artefact;

import java.io.File;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.issuemanagement.IssueExtractor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class PrioritiesGenerationProcess {
	protected File workspaceRoot;
	protected INakedModelWorkspace nakedModelWorkspace;
	protected NakedUmlConfig config;

	public void initialize(INakedModelWorkspace workspace, File modelFile, NakedUmlConfig config) throws Exception {
		this.workspaceRoot = modelFile;
		this.nakedModelWorkspace = workspace;
		this.config = config;
	}

	public void execute() throws Exception {
		IssueExtractor ie = new IssueExtractor();
		TextWorkspace root = new TextWorkspace();
		ie.initialize(nakedModelWorkspace, config, root);
		ie.startVisiting(nakedModelWorkspace);
		TextFileGenerator generator = new TextFileGenerator();
		generator.initialize(config);
		generator.startVisiting(root);
	}
}
