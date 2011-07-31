package net.sf.nakeduml.validation;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after = LinkagePhase.class)
public class ValidationPhase implements TransformationPhase<AbstractValidator,INakedElement>{
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractValidator> validators;
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	public Object[] execute(List<AbstractValidator> validators,TransformationContext context){
		this.validators=validators;
		for(AbstractValidator v:validators){
			v.initialize(modelWorkspace, config);
			v.startVisiting(modelWorkspace);
		}
		return new Object[]{};
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		for(INakedElement element:elements){
			for(AbstractValidator v:validators){
				v.initialize(modelWorkspace, config);
				v.visitRecursively((INakedElement) element);
			}
		}
		return elements;
	}
}
