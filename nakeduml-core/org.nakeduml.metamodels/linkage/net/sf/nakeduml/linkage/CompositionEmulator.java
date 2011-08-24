package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.core.internal.AssociationClassToEnd;
import net.sf.nakeduml.metamodel.core.internal.EndToAssociationClass;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;

@StepDependency(phase = LinkagePhase.class,after = {
		ProcessIdentifier.class,MappedTypeLinker.class
},before = {
	TypeResolver.class
},requires = {
		ProcessIdentifier.class,MappedTypeLinker.class
})
public class CompositionEmulator extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void visitAssociation(INakedAssociation ass){
		if(ass.getPropertyToEnd1() == null){
			ass.setPropertyToEnd1(new AssociationClassToEnd(ass.getEnd1()));
		}
		if(ass.getPropertyToEnd2() == null){
			ass.setPropertyToEnd2(new AssociationClassToEnd(ass.getEnd2()));
		}
		// TODO qualifiers
		if(ass instanceof INakedAssociationClass){
			if(ass.getEnd1().isNavigable() && ass.getPropertyToEnd1().getOtherEnd() == null){
				// add the implied property
				ass.getPropertyToEnd1().setOtherEnd(new EndToAssociationClass(ass.getEnd1()));
				ass.getPropertyToEnd1().getNakedBaseType().addOwnedElement(ass.getPropertyToEnd1().getOtherEnd());
			}else if(!ass.getEnd1().isNavigable() && ass.getPropertyToEnd1().getOtherEnd() != null){
				// must have changed - remove the implied property
				ass.getPropertyToEnd1().getNakedBaseType().removeOwnedElement(ass.getPropertyToEnd1().getOtherEnd());
				ass.getPropertyToEnd1().setOtherEnd(null);
			}
			if(ass.getEnd2().isNavigable() && ass.getPropertyToEnd2().getOtherEnd() == null){
				// add the implied property
				ass.getPropertyToEnd2().setOtherEnd(new EndToAssociationClass(ass.getEnd2()));
				ass.getPropertyToEnd2().getNakedBaseType().addOwnedElement(ass.getPropertyToEnd2().getOtherEnd());
			}else if(!ass.getEnd2().isNavigable() && ass.getPropertyToEnd2().getOtherEnd() != null){
				// must have changed - remove the implied property
				ass.getPropertyToEnd2().getNakedBaseType().removeOwnedElement(ass.getPropertyToEnd2().getOtherEnd());
				ass.getPropertyToEnd2().setOtherEnd(null);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp){
		if(cp instanceof INakedAssociationClass){
			// do nothing
		}else{
			INakedProperty endToComposite = cp.getEndToComposite();
			if(endToComposite == null){
				if(cp instanceof INakedBehavior){
					INakedBehavior b = (INakedBehavior) cp;
					if(b.getContext() != null && BehaviorUtil.hasExecutionInstance(b)){
						ArtificialProperty artificialProperty = new ArtificialProperty((INakedBehavior) cp);
						b.getContext().addOwnedElement(artificialProperty);
						cp.setEndToComposite(artificialProperty.getOtherEnd());
						getAffectedElements().add(artificialProperty);
						getAffectedElements().add(artificialProperty.getOtherEnd());
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
						getAffectedElements().add(artificialProperty);
						cp.setEndToComposite(artificialProperty);
					}else{
						ArtificialProperty artificialProperty = new ArtificialProperty(cp);
						cp.getNestingClassifier().addOwnedElement(artificialProperty);
						cp.setEndToComposite(artificialProperty.getOtherEnd());
						getAffectedElements().add(artificialProperty);
						getAffectedElements().add(artificialProperty.getOtherEnd());
					}
				}
				if(cp.getEndToComposite() != null){
					getAffectedElements().add(cp.getEndToComposite().getNakedBaseType());
					getAffectedElements().add(cp);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			INakedMessageStructure b = o.getMessageStructure();
			if(b == null){
				o.initMessageStructure();
				b = o.getMessageStructure();
				((EmulatedCompositionMessageStructure) b).addInterface(workspace.getNakedUmlLibrary().getTaskObject());
				getAffectedElements().add(b);
			}else{
				((EmulatedCompositionMessageStructure) b).reinitialize();
			}
			setEndToComposite(b);
		}
	}
	private void setEndToComposite(INakedMessageStructure b){
		if(b.getEndToComposite() == null){
			ArtificialProperty artificialProperty = new ArtificialProperty((EmulatedCompositionMessageStructure) b);
			b.setEndToComposite(artificialProperty.getOtherEnd());
			b.getEndToComposite().getNakedBaseType().addOwnedElement(artificialProperty);
			getAffectedElements().add(b.getEndToComposite().getNakedBaseType());
			getAffectedElements().add(b);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction o){
		if(BehaviorUtil.hasMessageStructure(o)){
			INakedMessageStructure b = o.getMessageStructure();
			if(b == null){
				o.initMessageStructure();
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o){
		INakedMessageStructure b = o.getMessageStructure();
		if(b == null){
			o.initMessageStructure();
			b = o.getMessageStructure();
			((EmulatedCompositionMessageStructure) b).addInterface(workspace.getNakedUmlLibrary().getTaskObject());
			getAffectedElements().add(b);
		}else{
			((EmulatedCompositionMessageStructure) b).reinitialize();
		}
		setEndToComposite(b);
	}
}
