package org.opaeum.validation.composition;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ComplexStructure;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class CompositionValidator extends AbstractValidator{
	protected boolean isPersistent(Classifier c){
		if(c instanceof ComplexStructure){
			return ((ComplexStructure) c).isPersistent();
		}else{
			return false;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void class_Before(Class st){
		if(isPersistent(st)){
			if(st.hasComposite()){
				Property composite = st.getEndToComposite();
				if(!isPersistent((Classifier) composite.getType())){
					getErrorMap().putError(st, CompositionValidationRule.PERSISTENT_CONTAINS_PERSISTENT);
				}
				if(st instanceof Behavior || st instanceof Class){
					int composites = 0;
					for(Property f:st.getOwnedAttributes()){
						if(f instanceof Property){
							Property p = f;
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
