package net.sf.nakeduml.validation;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after = LinkagePhase.class)
public class ValidationPhase implements TransformationPhase<AbstractValidator,INakedElement> {
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	public Object[] execute(List<AbstractValidator> validators,TransformationContext context) {
		for (INakedPackage p : modelWorkspace.getGeneratingModelsOrProfiles()) {
			for (AbstractValidator v : validators) {
				v.initialize(modelWorkspace, config);
				v.startVisiting(p);
			}
		}
		return new Object[] {};
	}

	@Override
	public Object processSingleElement(List<AbstractValidator> validators,TransformationContext context,INakedElement element){
		for (INakedPackage p : modelWorkspace.getGeneratingModelsOrProfiles()) {
			for (AbstractValidator v : validators) {
				v.initialize(modelWorkspace, config);
				v.visitRecursively((INakedElement) element);
			}
		}
		return element;
		
	}

}
