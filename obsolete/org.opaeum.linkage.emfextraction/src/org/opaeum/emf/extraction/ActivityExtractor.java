package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.internal.NakedActivityImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = FeatureExtractor.class,after = FeatureExtractor.class)
public class ActivityExtractor extends CommonBehaviorExtractor{
	@VisitBefore
	public void visitActivity(Activity esm, NakedActivityImpl nsm){
		if(esm.getContext() != null && esm.equals(esm.getContext().getClassifierBehavior())){
			INakedBehavioredClassifier ctx = (INakedBehavioredClassifier) getNakedPeer(esm.getContext());
			ctx.setClassifierBehavior(nsm);
		}
	}
}
