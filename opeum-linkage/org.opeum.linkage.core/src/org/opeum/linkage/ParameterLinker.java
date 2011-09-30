package org.opeum.linkage;

import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedParameter;

@StepDependency(phase = LinkagePhase.class,after = {
	MappedTypeLinker.class
},before = {
	ObjectFlowLinker.class
},requires = {})
public class ParameterLinker extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedOperation o){
		o.recalculateParameterPositions();
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
