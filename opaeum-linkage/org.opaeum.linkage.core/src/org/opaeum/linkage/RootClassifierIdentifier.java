package org.opaeum.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;

@StepDependency(phase = LinkagePhase.class,after = {
	CompositionEmulator.class
},requires = {
	CompositionEmulator.class
})
public class RootClassifierIdentifier extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier cls){
		// TODO look at root collaborations too
		if(cls instanceof ICompositionParticipant){
			ICompositionParticipant cp = (ICompositionParticipant) cls;
			if(!cp.getIsAbstract() && cp.getEndToComposite() == null){
				for(INakedProperty p:cp.getEffectiveAttributes()){
					if(p.isComposite()){
						workspace.addRootClassifier(cp);
						break;
					}
				}
			}
		}
	}
}
