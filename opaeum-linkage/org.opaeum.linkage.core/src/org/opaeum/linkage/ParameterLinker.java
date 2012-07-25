package org.opaeum.linkage;

import java.util.List;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedParameter;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;

@StepDependency(phase = LinkagePhase.class,after = {
	MappedTypeLinker.class
},before = {},requires = {})
public class ParameterLinker extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedOperation o){
		o.recalculateParameterPositions();
	}
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedBehavior b){
		b.recalculateParameterPositions();
		if(b.getSpecification() != null){
			b.getSpecification().recalculateParameterPositions();
			if(b.getSpecification().getOwnedParameters().size() == b.getOwnedParameters().size()){
				List<? extends INakedParameter> fromParams = b.getSpecification().getOwnedParameters();
				List<? extends INakedParameter> toParams = b.getOwnedParameters();
				for(int i = 0;i < fromParams.size() && i < toParams.size();i++){
					INakedParameter to = toParams.get(i);
					INakedParameter from = fromParams.get(i);
					to.setLinkedParameter(from);
				}
			}else{
				getErrorMap().putError(b, BehaviorValidationRule.SPECIFICATION_PARAMETER_COUNT, b.getParameters().size(), b.getSpecification(),
						b.getSpecification().getParameters().size());
			}
		}
	}
}
