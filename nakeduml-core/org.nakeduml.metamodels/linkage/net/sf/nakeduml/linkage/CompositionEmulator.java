package net.sf.nakeduml.linkage;

import java.util.List;

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
				ArtificialProperty artificialProperty = new ArtificialProperty((INakedBehavior) b, workspace.getOclEngine().getOclLibrary());
				((INakedBehavior) b).getContext().addOwnedElement(artificialProperty);
				b.setEndToComposite(artificialProperty.getOtherEnd());
			}else if(b.getNestingClassifier() != null){
				//In case of composite structures, the composition may not have been modeled as an association
				INakedProperty endFromComposite = null;
				for(INakedProperty p:(List<? extends INakedProperty>) b.getNestingClassifier().getEffectiveAttributes()){
					if(p.isComposite() && p.getNakedBaseType() == b){
						endFromComposite = p;
					}
				}
				if(endFromComposite != null){
					ArtificialProperty artificialProperty = new ArtificialProperty(endFromComposite, workspace.getOclEngine().getOclLibrary());
					b.setEndToComposite(artificialProperty);
				}else{
					ArtificialProperty artificialProperty = new ArtificialProperty(b, workspace.getOclEngine().getOclLibrary());
					b.getNestingClassifier().addOwnedElement(artificialProperty);
					b.setEndToComposite(artificialProperty.getOtherEnd());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			ICompositionParticipant b = o.getMessageStructure(workspace.getNakedUmlLibrary());
			ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary());
			b.setEndToComposite(artificialProperty.getOtherEnd());
			b.getEndToComposite().getNakedBaseType().addOwnedElement(artificialProperty);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o){
		ICompositionParticipant b = o.getMessageStructure(workspace.getNakedUmlLibrary());
		ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary());
		b.setEndToComposite(artificialProperty.getOtherEnd());
		artificialProperty.getOtherEnd().getNakedBaseType().addOwnedElement(artificialProperty);
	}
}
