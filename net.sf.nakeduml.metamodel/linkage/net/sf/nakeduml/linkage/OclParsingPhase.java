package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

@PhaseDependency(after = { LinkagePhase.class, NameGenerationPhase.class })
public class OclParsingPhase implements TransformationPhase<NakedParsedOclStringResolver> {
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace workspace;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<NakedParsedOclStringResolver> features) {
		for (NakedParsedOclStringResolver p : features) {
			p.initialize(workspace, config);
			p.startVisiting(workspace);
		}
		return new Object[] { workspace };
	}
}
