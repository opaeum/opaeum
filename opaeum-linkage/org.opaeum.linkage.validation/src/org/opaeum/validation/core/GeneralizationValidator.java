package org.opaeum.validation.core;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class GeneralizationValidator extends AbstractValidator{
	// TODO find better place
	@VisitBefore
	public void visitReception(Reception r){
		if(r.getSignal() == null){
			getErrorMap().putError(r, CoreValidationRule.RECEPTION_REQUIRES_SIGNAL);
		}
	}
	@VisitBefore
	public void visitGeneralization(Generalization p){
		if(p.getSpecific() != null && p.getGeneral() != null){
			String specificMetaClass = EmfClassifierUtil.getMetaClass( p.getSpecific());
			String generalMetaClass = EmfClassifierUtil.getMetaClass(p.getGeneral());
			if(specificMetaClass.equals(generalMetaClass)){
				getErrorMap().putError(p.getSpecific(), CoreValidationRule.GENERALIZATION_ONLY_OF_SAME_METATYPE, specificMetaClass, p.getGeneral(),
						generalMetaClass,p);
			}else if(p.getSpecific() instanceof Behavior){
				Behavior s = (Behavior) p.getSpecific();
				Behavior g = (Behavior) p.getGeneral();
				BehavioredClassifier sContext = s.getContext();
				BehavioredClassifier gContext = g.getContext();
				if(sContext != null && gContext != null && !sContext.conformsTo(gContext)){
					getErrorMap().putError(s, CoreValidationRule.GENERALIZATION_CONTEXTS_CONFORMANCE, g, sContext, gContext,p);
				}else if(sContext == null && gContext != null){
					getErrorMap().putError(s, CoreValidationRule.GENERALIZATION_CONTEXTS_CONFORMANCE, g, "undefined", gContext,p);
				}
			}else if(EmfClassifierUtil.isCompositionParticipant(p.getSpecific())){
				Classifier s =  p.getSpecific();
				Classifier g =  p.getGeneral();
				if(getEndToComposite(s) != null && getEndToComposite(g) != null){
					Classifier sContext =  (Classifier) getEndToComposite(s).getType();
					Classifier gContext =   (Classifier) getEndToComposite(g).getType();
					if(sContext != null && gContext != null && !sContext.conformsTo(gContext)){
						getErrorMap().putError(s, CoreValidationRule.GENERALIZATION_COMPOSITION_CONFORMANCE, g, sContext, gContext,p);
					}
				}
			}
		}
	}
	public Property getEndToComposite(Classifier g){
		return workspace.getOpaeumLibrary().getEndToComposite(g);
	}
}
