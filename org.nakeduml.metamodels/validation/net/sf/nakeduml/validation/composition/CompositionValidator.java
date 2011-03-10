package net.sf.nakeduml.validation.composition;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.ValidationPhase;

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
