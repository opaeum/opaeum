package net.sf.nakeduml.filegeneration;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.textmetamodel.TextFileDirectory;
import net.sf.nakeduml.textmetamodel.TextFileNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class AbstractTextNodeVisitor extends VisitorAdapter<TextFileNode, TextWorkspace> implements TransformationStep {
	NakedUmlConfig config;

	public void initialize(NakedUmlConfig config2) {
		this.config = config2;
	}

	@Override
	public Collection<? extends TextFileNode> getChildren(TextFileNode root) {
		if (root instanceof TextWorkspace) {
			return ((TextWorkspace) root).getTextOutputRoots();
		} else if (root instanceof TextFileDirectory) {
			return ((TextFileDirectory) root).getChildren();
		} else {
			return Collections.emptySet();
		}
	}
}
