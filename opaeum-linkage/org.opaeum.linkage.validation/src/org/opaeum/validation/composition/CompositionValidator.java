package org.opeum.validation.composition;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.validation.AbstractValidator;
import org.opeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class CompositionValidator extends AbstractValidator{
	protected boolean isPersistent(INakedClassifier c){
		if(c instanceof INakedComplexStructure){
			return ((INakedComplexStructure) c).isPersistent();
		}else{
			return false;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void class_Before(INakedEntity st){
		if(isPersistent(st)){
			if(st.hasComposite()){
				INakedProperty composite = st.getEndToComposite();
				if(!isPersistent(composite.getNakedBaseType())){
					getErrorMap().putError(st, CompositionValidationRule.PERSISTENT_CONTAINS_PERSISTENT);
				}
				if(st instanceof INakedBehavior || st instanceof INakedEntity){
					int composites = 0;
					for(INakedProperty f:st.getOwnedAttributes()){
						if(f instanceof INakedProperty){
							INakedProperty p = f;
							if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
								composites++;
							}
						}
					}
					if(composites > 1){
						getErrorMap().putError(st, CompositionValidationRule.ENTITIES_HAVE_ONE_COMPOSITE_ONLY, "");
					}
				}
			}
		}
	}
}
