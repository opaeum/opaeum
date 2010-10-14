package net.sf.nakeduml.jsf2generation;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.seamgeneration.UserInteractionElementVisitor;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.uigeneration.UserInteractionTransformationPhase;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

@PhaseDependency(after = UserInteractionTransformationPhase.class, requires = UserInteractionTransformationPhase.class, before = FileGenerationPhase.class)
public class Jsf2TransformationPhase implements TransformationPhase<UserInteractionElementVisitor> {
	@InputModel
	private TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	@InputModel
	private UserInteractionWorkspace uiWorkspace;
	public Object[] execute(List<UserInteractionElementVisitor> features) {
		for (UserInteractionElementVisitor d : features) {
			d.initialize(uiWorkspace, config, textWorkspace);
			d.startVisiting(uiWorkspace);
		}
		return new Object[] {};
	}
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}
}
