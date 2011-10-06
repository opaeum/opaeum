package org.opaeum.validation.composition;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

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
