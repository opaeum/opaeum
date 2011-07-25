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

@StepDependency(phase = LinkagePhase.class, after = { ProcessIdentifier.class,MappedTypeLinker.class }, before = {}, requires = { ProcessIdentifier.class,MappedTypeLinker.class })
public class CompositionEmulator extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp) {
		INakedProperty endToComposite = cp.getEndToComposite();
		if (endToComposite == null) {
			if (cp instanceof INakedBehavior) {
				INakedBehavior b = (INakedBehavior) cp;
				if (b.getContext() != null && BehaviorUtil.hasExecutionInstance(b)) {
					ArtificialProperty artificialProperty = new ArtificialProperty((INakedBehavior) cp, workspace.getOclEngine().getOclLibrary());
					b.getContext().addOwnedElement(artificialProperty);
					cp.setEndToComposite(artificialProperty.getOtherEnd());
				}
			} else if (cp.getNestingClassifier() != null) {
				// In case of composite structures, the composition may not have
				// been modeled as an association
				INakedProperty endFromComposite = null;
				for (INakedProperty p : (List<? extends INakedProperty>) cp.getNestingClassifier().getEffectiveAttributes()) {
					if (p.isComposite() && p.getNakedBaseType() == cp) {
						endFromComposite = p;
					}
				}
				if (endFromComposite != null) {
					ArtificialProperty artificialProperty = new ArtificialProperty(endFromComposite, workspace.getOclEngine().getOclLibrary());
					cp.setEndToComposite(artificialProperty);
				} else {
					ArtificialProperty artificialProperty = new ArtificialProperty(cp, workspace.getOclEngine().getOclLibrary());
					cp.getNestingClassifier().addOwnedElement(artificialProperty);
					cp.setEndToComposite(artificialProperty.getOtherEnd());
				}
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o) {
		if (BehaviorUtil.hasExecutionInstance(o)) {
			ICompositionParticipant b = o.getMessageStructure(workspace.getNakedUmlLibrary());
			ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary());
			b.setEndToComposite(artificialProperty.getOtherEnd());
			b.getEndToComposite().getNakedBaseType().addOwnedElement(artificialProperty);
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o) {
		ICompositionParticipant b = o.getMessageStructure(workspace.getNakedUmlLibrary());
		ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b, workspace.getOclEngine().getOclLibrary());
		b.setEndToComposite(artificialProperty.getOtherEnd());
		artificialProperty.getOtherEnd().getNakedBaseType().addOwnedElement(artificialProperty);
	}
}
