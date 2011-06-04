package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;

@StepDependency(phase = LinkagePhase.class,after = {
	ProcessIdentifier.class
},before = {},requires = {
	ProcessIdentifier.class
})
public class CompositionEmulator extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant b){
		INakedProperty endToComposite = b.getEndToComposite();
		if(endToComposite == null){
			if(b instanceof INakedBehavior && ((INakedBehavior) b).getContext() != null){
				b.setEndToComposite(endToComposite = new ArtificialProperty((INakedBehavior) b, workspace.getOclEngine().getOclLibrary()));
				endToComposite.getNakedBaseType().addOwnedElement(endToComposite.getOtherEnd());
			}else if(b.getNestingClassifier() != null){
				b.setEndToComposite(endToComposite = new ArtificialProperty(b, workspace.getOclEngine().getOclLibrary()));
				endToComposite.getNakedBaseType().addOwnedElement(endToComposite.getOtherEnd());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			ArtificialProperty endToComposite;
			ICompositionParticipant b = o.getMessageStructure(workspace.getOclEngine().getOclLibrary());
			b.setEndToComposite(endToComposite = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary()));
			endToComposite.getNakedBaseType().addOwnedElement(endToComposite.getOtherEnd());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o){
		ArtificialProperty endToComposite;
		ICompositionParticipant b = o.getMessageStructure(workspace.getOclEngine().getOclLibrary());
		b.setEndToComposite(endToComposite = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary()));
		endToComposite.getNakedBaseType().addOwnedElement(endToComposite.getOtherEnd());
	}
}
