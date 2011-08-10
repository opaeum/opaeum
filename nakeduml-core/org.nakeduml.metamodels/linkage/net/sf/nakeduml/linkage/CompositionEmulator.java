package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;

@StepDependency(phase = LinkagePhase.class,after = {
	ProcessIdentifier.class
},before = {
	TypeResolver.class
},requires = {
	ProcessIdentifier.class
})
public class CompositionEmulator extends AbstractModelElementLinker{
	@Override
	public void visitRecursively(INakedElementOwner o){
		System.out.println("AbstractModelElementLinker.visitRecursively()" + o.getName());
		super.visitRecursively(o);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp){
		INakedProperty endToComposite = cp.getEndToComposite();
		if(endToComposite == null){
			if(cp instanceof INakedBehavior){
				INakedBehavior b = (INakedBehavior) cp;
				if(b.getContext() != null && BehaviorUtil.hasExecutionInstance(b)){
					ArtificialProperty artificialProperty = new ArtificialProperty((INakedBehavior) cp);
					b.getContext().addOwnedElement(artificialProperty);
					cp.setEndToComposite(artificialProperty.getOtherEnd());
				}
			}else if(cp.getNestingClassifier() != null){
				// In case of composite structures, the composition may not have
				// been modeled as an association
				INakedProperty endFromComposite = null;
				for(INakedProperty p:(List<? extends INakedProperty>) cp.getNestingClassifier().getEffectiveAttributes()){
					if(p.isComposite() && p.getNakedBaseType() == cp){
						endFromComposite = p;
					}
				}
				if(endFromComposite != null){
					ArtificialProperty artificialProperty = new ArtificialProperty(endFromComposite);
					cp.setEndToComposite(artificialProperty);
				}else{
					ArtificialProperty artificialProperty = new ArtificialProperty(cp);
					cp.getNestingClassifier().addOwnedElement(artificialProperty);
					cp.setEndToComposite(artificialProperty.getOtherEnd());
				}
			}
			if(cp.getEndToComposite() != null){
				getAffectedElements().add(cp.getEndToComposite().getNakedBaseType());
				getAffectedElements().add(cp);
			}
		}else if(endToComposite instanceof ArtificialProperty || endToComposite.getOtherEnd() instanceof ArtificialProperty){
			getAffectedElements().add(endToComposite);
			getAffectedElements().add(endToComposite.getOtherEnd());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			o.initMessageStructure();
			ICompositionParticipant b = o.getMessageStructure();
			((MessageStructureImpl) b).addInterface(workspace.getNakedUmlLibrary().getTaskObject());
			ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b);
			b.setEndToComposite(artificialProperty.getOtherEnd());
			b.getEndToComposite().getNakedBaseType().addOwnedElement(artificialProperty);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction o){
		o.initMessageStructure();
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o){
		o.initMessageStructure();
		ICompositionParticipant b = o.getMessageStructure();
		ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b);
		b.setEndToComposite(artificialProperty.getOtherEnd());
		artificialProperty.getOtherEnd().getNakedBaseType().addOwnedElement(artificialProperty);
	}
}
