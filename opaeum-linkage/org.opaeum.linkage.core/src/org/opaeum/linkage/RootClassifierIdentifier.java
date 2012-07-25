package org.opaeum.linkage;

import org.eclipse.uml2.uml.ICompositionParticipant;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;

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
			if(!cp.isAbstract() && cp.getEndToComposite() == null){
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
