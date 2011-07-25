package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;

@StepDependency(phase = LinkagePhase.class,after = {
	MappedTypeLinker.class
},before = {
	ObjectFlowLinker.class
},requires = {})
public class ParameterLinker extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedOperation b){
		b.recalculateParameterPositions();
	}
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedBehavior b){
		if(b.getSpecification() != null){
			List<? extends INakedParameter> fromParams = b.getSpecification().getOwnedParameters();
			List<? extends INakedParameter> toParams = b.getOwnedParameters();
			for(int i = 0;i < fromParams.size() && i < toParams.size();i++){
				INakedParameter to = toParams.get(i);
				INakedParameter from = fromParams.get(i);
				to.setLinkedParameter(from);
				to.setName(from.getName());
				to.getMappingInfo().setJavaName(from.getMappingInfo().getJavaName());
			}
			// TODO set types????
		}
		b.recalculateParameterPositions();
	}
}
