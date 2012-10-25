package org.opaeum.validation.composition;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class CompositionValidator extends AbstractValidator{
	protected boolean isPersistent(Classifier c){
		if(EmfClassifierUtil.isComplexStructure(c)){
			return EmfClassifierUtil.isPersistent(c);
		}else{
			return false;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void class_Before(Class st){
		if(isPersistent(st)){
			Property composite = workspace.getOpaeumLibrary().getEndToComposite(st);
			if(composite!=null){
				if(!isPersistent((Classifier) composite.getType())){
					getErrorMap().putError(st, CompositionValidationRule.PERSISTENT_CONTAINS_PERSISTENT);
				}
				if(EmfClassifierUtil.isCompositionParticipant(st) ){
					int composites = 0;
					for(Property f:workspace.getOpaeumLibrary().getEffectiveAttributes(st)){
						if(f instanceof Property){
							Property p = f;
							if(!EmfPropertyUtil.isDerived( p) && p.getOtherEnd() != null && p.getOtherEnd().isComposite() &&!EmfPropertyUtil.isDerived( p.getOtherEnd()) ){
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
