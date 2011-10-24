package org.opaeum.linkage;

import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.CallBehaviorMessageStructure;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.activities.internal.StructuredActivityNodeClassifier;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.bpm.internal.EmbeddedScreenFlowTaskMessageStructure;
import org.opaeum.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.AssociationClassToEnd;
import org.opaeum.metamodel.core.internal.EndToAssociationClass;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;

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
				// In case of composite structures, the composition may not have
				// been modeled as an association but as a part
				INakedProperty endFromComposite = null;
				if(cp.getNestingClassifier() != null){
					for(INakedProperty p:(List<? extends INakedProperty>) cp.getNestingClassifier().getEffectiveAttributes()){
						if(p.isComposite() && p.getNakedBaseType() == cp){
							endFromComposite = p;
						}
					}
				}
				if(cp instanceof INakedBehavior){
					INakedBehavior b = (INakedBehavior) cp;
					if(b.getContext() != null && BehaviorUtil.hasExecutionInstance(b)){
						if(endFromComposite != null){
							NonInverseArtificialProperty inverseArtificialProperty = new NonInverseArtificialProperty(endFromComposite, "contextObject");
							addAffectedElement(inverseArtificialProperty);
							cp.setEndToComposite(inverseArtificialProperty);
						}else{
							InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(b.getContext(), (INakedBehavior) cp);
							b.getContext().addOwnedElement(inverseArtificialProperty);
							b.addOwnedElement(inverseArtificialProperty.getOtherEnd());
							b.setEndToComposite(inverseArtificialProperty.getOtherEnd());
							addAffectedElement(inverseArtificialProperty);
							addAffectedElement(inverseArtificialProperty.getOtherEnd());
						}
					}
				}else if(cp.getNestingClassifier() != null){
					if(endFromComposite != null){
						NonInverseArtificialProperty inverseArtificialProperty = new NonInverseArtificialProperty(endFromComposite, "ownerObject");
						addAffectedElement(inverseArtificialProperty);
						cp.setEndToComposite(inverseArtificialProperty);
					}else{
						InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(cp.getNestingClassifier(), cp);
						cp.getNestingClassifier().addOwnedElement(inverseArtificialProperty);
						cp.setEndToComposite(inverseArtificialProperty.getOtherEnd());
						addAffectedElement(inverseArtificialProperty);
						addAffectedElement(inverseArtificialProperty.getOtherEnd());
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
			INakedMessageStructure b =  o.getMessageStructure();
			if(b == null){
				o.initMessageStructure();
				workspace.putModelElement(o.getMessageStructure());
				b = (OperationMessageStructureImpl) o.getMessageStructure();
				if(o instanceof INakedResponsibility){
					// TODO define Responsibility interface
					// if(workspace.getOpaeumLibrary().getTaskObject() != null){
					// ((EmulatedCompositionMessageStructure) b).addInterface(workspace.getOpaeumLibrary().getTaskObject());
					// }
				}
				addAffectedElement(b);
			}
			b.reinitialize();
			InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(o.getOwner(), b);
			o.getOwner().addOwnedElement(inverseArtificialProperty);
			b.setEndToComposite(inverseArtificialProperty.getOtherEnd());
			addAffectedElement(o.getOwner());
			addAffectedElement(b);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallBehaviorAction o){
		if(BehaviorUtil.hasMessageStructure(o)){
			INakedMessageStructure b = o.getMessageStructure();
			if(b == null){
				workspace.putModelElement(o.getMessageStructure());
				b = o.getMessageStructure();
			}
			b.reinitialize();
			if(b instanceof CallBehaviorMessageStructure){
				CallBehaviorMessageStructure msg = (CallBehaviorMessageStructure) b;
				INakedClassifier owner = o.getNearestStructuredElementAsClassifier();
				InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(owner, msg);
				msg.setEndToComposite(inverseArtificialProperty.getOtherEnd());
				owner.addOwnedElement(inverseArtificialProperty);
				addAffectedElement(owner);
				addAffectedElement(msg);
				if(b instanceof EmbeddedScreenFlowTaskMessageStructure){
					if(workspace.getOpaeumLibrary().getTaskObject() != null){
						((EmbeddedScreenFlowTaskMessageStructure) b).addInterface(workspace.getOpaeumLibrary().getTaskObject());
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitStructuredActivityNode(INakedStructuredActivityNode o){
		if(o.getActivity().getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			StructuredActivityNodeClassifier msg = (StructuredActivityNodeClassifier) o.getMessageStructure();
			if(msg == null){
				o.initMessageStructure();
				msg = (StructuredActivityNodeClassifier) o.getMessageStructure();
			}
			msg.reinitialize();
			INakedClassifier owner = o.getNearestStructuredElementAsClassifier();
			InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(owner, msg);
			msg.setEndToComposite(inverseArtificialProperty.getOtherEnd());
			owner.addOwnedElement(inverseArtificialProperty);
			addAffectedElement(owner);
			addAffectedElement(msg);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEmbeddedTask(INakedEmbeddedSingleScreenTask o){
		EmbeddedSingleScreenTaskMessageStructureImpl msg = (EmbeddedSingleScreenTaskMessageStructureImpl) o.getMessageStructure();
		if(msg == null){
			o.initMessageStructure();
			msg = (EmbeddedSingleScreenTaskMessageStructureImpl) o.getMessageStructure();
			workspace.putModelElement(o.getMessageStructure());
			if(workspace.getOpaeumLibrary().getTaskObject() != null){
				msg.addInterface(workspace.getOpaeumLibrary().getTaskObject());
			}
			addAffectedElement(msg);
		}
		msg.reinitialize();
		INakedClassifier owner = o.getNearestStructuredElementAsClassifier();
		InverseArtificialProperty inverseArtificialProperty = new InverseArtificialProperty(owner, msg);
		msg.setEndToComposite(inverseArtificialProperty.getOtherEnd());
		owner.addOwnedElement(inverseArtificialProperty);
		addAffectedElement(owner);
		addAffectedElement(msg);
	}
}
