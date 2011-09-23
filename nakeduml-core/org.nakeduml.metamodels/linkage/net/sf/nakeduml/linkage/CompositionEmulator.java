package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.core.internal.AssociationClassToEnd;
import net.sf.nakeduml.metamodel.core.internal.EndToAssociationClass;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;

@StepDependency(phase = LinkagePhase.class,after = {
		ProcessIdentifier.class,MappedTypeLinker.class,ParameterLinker.class
},before = {
	TypeResolver.class
},requires = {
		ProcessIdentifier.class,MappedTypeLinker.class,ParameterLinker.class
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
		if(ass.isClass()){
			if(ass.getEnd1().isNavigable() && ass.getPropertyToEnd1().getOtherEnd() == null){
				// add the implied property
				ass.getPropertyToEnd1().setOtherEnd(new EndToAssociationClass(ass.getEnd1()));
				ass.getPropertyToEnd1().getNakedBaseType().addOwnedElement(ass.getPropertyToEnd1().getOtherEnd());
			}else if(!ass.getEnd1().isNavigable() && ass.getPropertyToEnd1().getOtherEnd() != null){
				// must have changed - remove the implied property
				ass.getPropertyToEnd1().getNakedBaseType().removeOwnedElement(ass.getPropertyToEnd1().getOtherEnd(), true);
				ass.getPropertyToEnd1().setOtherEnd(null);
			}
			if(ass.getEnd2().isNavigable() && ass.getPropertyToEnd2().getOtherEnd() == null){
				// add the implied property
				ass.getPropertyToEnd2().setOtherEnd(new EndToAssociationClass(ass.getEnd2()));
				ass.getPropertyToEnd2().getNakedBaseType().addOwnedElement(ass.getPropertyToEnd2().getOtherEnd());
			}else if(!ass.getEnd2().isNavigable() && ass.getPropertyToEnd2().getOtherEnd() != null){
				// must have changed - remove the implied property
				ass.getPropertyToEnd2().getNakedBaseType().removeOwnedElement(ass.getPropertyToEnd2().getOtherEnd(), true);
				ass.getPropertyToEnd2().setOtherEnd(null);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitDataType(INakedStructuredDataType cp){
		for(INakedProperty p:cp.getOwnedAttributes()){
			if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
				p.setNavigable(false);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp){
		if(cp instanceof INakedAssociation){
			// do nothing
		}else{
			cp.removeObsoleteArtificialProperties();
			INakedProperty endToComposite = cp.getEndToComposite();
			if(endToComposite == null && !cp.getIsAbstract()){
				if(cp instanceof INakedBehavior){
					INakedBehavior b = (INakedBehavior) cp;
					if(b.getContext() != null && BehaviorUtil.hasExecutionInstance(b)){
						ArtificialProperty artificialProperty = new ArtificialProperty((INakedBehavior) cp);
						b.getContext().addOwnedElement(artificialProperty);
						b.addOwnedElement(artificialProperty.getOtherEnd());
						b.setEndToComposite(artificialProperty.getOtherEnd());
						addAffectedElement(artificialProperty);
						addAffectedElement(artificialProperty.getOtherEnd());
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
						addAffectedElement(artificialProperty);
						cp.setEndToComposite(artificialProperty);
					}else{
						ArtificialProperty artificialProperty = new ArtificialProperty(cp);
						cp.getNestingClassifier().addOwnedElement(artificialProperty);
						cp.setEndToComposite(artificialProperty.getOtherEnd());
						addAffectedElement(artificialProperty);
						addAffectedElement(artificialProperty.getOtherEnd());
					}
				}
				if(cp.getEndToComposite() != null){
					addAffectedElement(cp.getEndToComposite().getNakedBaseType());
					addAffectedElement(cp);
				}
			}else if(cp.getEndToComposite() != null){
				cp.removeObsoleteArtificialProperties();
				cp.getEndToComposite().getNakedBaseType().removeObsoleteArtificialProperties();
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			INakedMessageStructure b = o.getMessageStructure();
			if(b == null){
				o.initMessageStructure();
				workspace.putModelElement(o.getMessageStructure());
				b = o.getMessageStructure();
				if(o instanceof INakedResponsibility){
					// TODO define Responsibility interface
					// if(workspace.getNakedUmlLibrary().getTaskObject() != null){
					// ((EmulatedCompositionMessageStructure) b).addInterface(workspace.getNakedUmlLibrary().getTaskObject());
					// }
				}
				addAffectedElement(b);
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
			addAffectedElement(b.getEndToComposite().getNakedBaseType());
			addAffectedElement(b);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction o){
		if(BehaviorUtil.hasMessageStructure(o)){
			INakedMessageStructure b = o.getMessageStructure();
			if(b == null){
				o.initMessageStructure();
				workspace.putModelElement(o.getMessageStructure());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedTask o){
		INakedMessageStructure b = o.getMessageStructure();
		if(b == null){
			o.initMessageStructure();
			b = o.getMessageStructure();
			workspace.putModelElement(o.getMessageStructure());
			if(workspace.getNakedUmlLibrary().getTaskObject() != null){
				((EmulatedCompositionMessageStructure) b).addInterface(workspace.getNakedUmlLibrary().getTaskObject());
			}
			addAffectedElement(b);
		}else{
			((EmulatedCompositionMessageStructure) b).reinitialize();
		}
		setEndToComposite(b);
	}
}
