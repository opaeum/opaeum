package org.opaeum.linkage;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.CallBehaviorMessageStructure;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.activities.internal.StructuredActivityNodeClassifier;
import org.opaeum.metamodel.bpm.INakedBusinessComponent;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.bpm.internal.EmbeddedScreenFlowTaskMessageStructure;
import org.opaeum.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.AssociationClassToEnd;
import org.opaeum.metamodel.core.internal.EndToAssociationClass;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.core.internal.NakedAssociationImpl;
import org.opaeum.metamodel.core.internal.NakedInterfaceRealizationImpl;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.metamodel.core.internal.emulated.NakedBusinessCollaboration;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import org.opaeum.metamodel.usecases.INakedActor;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = LinkagePhase.class,after = {ProcessIdentifier.class,MappedTypeLinker.class,ParameterLinker.class},before = {TypeResolver.class},requires = {
		ProcessIdentifier.class,MappedTypeLinker.class,ParameterLinker.class})
public class CompositionEmulator extends AbstractModelElementLinker{
	SortedSet<ICompositionParticipant> rootClasses = new TreeSet<ICompositionParticipant>(new DefaultOpaeumComparator());
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
	// TODO maybe find a better place for this
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier c){
		if(!(c instanceof ICompositionParticipant)){
			c.reorderSequences();
		}
	}
	@Override
	protected boolean ignoreDeletedElements(){
		return false;
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParticipant(ICompositionParticipant cp){
		if(cp.isMarkedForDeletion()){
	}else{
			// TODO maybe find a better place for this
			cp.reorderSequences();
			if(cp instanceof INakedAssociation){
				// do nothing
			}else{
				cp.removeObsoleteArtificialProperties();
				INakedProperty endToComposite = cp.getEndToComposite();
				if(endToComposite == null && !cp.isAbstract()){
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
						if(b.isProcess() && getLibrary().getProcessObject() != null && !b.getInterfaces().contains(getLibrary().getProcessObject())
								&& !b.conformsTo(getLibrary().getAbstractRequest())){
							b.addOwnedElement(new NakedInterfaceRealizationImpl(getLibrary().getProcessObject()));
						}
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
						addAffectedElement(cp);
						if(cp.getEndToComposite().getNakedBaseType() != null){
							// Is null when being deleted
							addAffectedElement(cp.getEndToComposite().getNakedBaseType());
						}
					}
				}else if(cp.getEndToComposite() != null){
					cp.removeObsoleteArtificialProperties();
					if(cp.getEndToComposite().getNakedBaseType() != null){
						// Is null when being deleted
						cp.getEndToComposite().getNakedBaseType().removeObsoleteArtificialProperties();
					}
				}
			}
			if((cp instanceof INakedActor || cp instanceof INakedBusinessComponent) && !cp.isAbstract()){
				if(cp.getEndToComposite() == null || cp.getEndToComposite().getBaseType().equals(getLibrary().getBusinessCollaboration())){
					// Need to realize that abstract association to the real BUsinessCollabroation
					// TODO rethink this - maybe just always create an application root
					/* The problem is that during modelling the need for an artificial root may arise and then disappear again */
					if(workspace.getApplicationRoot() == null){
						rootClasses.add(cp);
					}else{
						addCompositionToApplicationRoot(workspace.getApplicationRoot(), cp);
					}
				}else if(cp.getEndToComposite() instanceof AbstractEmulatedProperty
						&& cp.getEndToComposite().getNakedBaseType() instanceof NakedBusinessCollaboration){
					INakedProperty artificialEndToComposite = cp.getEndToComposite();
					for(INakedProperty p:cp.getEffectiveAttributes()){
						if(p != artificialEndToComposite && p.getOtherEnd() != null && p.getOtherEnd().isComposite()
								&& !p.getBaseType().equals(getLibrary().getBusinessCollaboration())){
							addAffectedElement(artificialEndToComposite.getNakedBaseType());
							artificialEndToComposite.getNakedBaseType().removeOwnedElement(artificialEndToComposite.getOtherEnd(), true);
							cp.removeOwnedElement(artificialEndToComposite, true);
							cp.setEndToComposite(p);
						}
					}
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
				workspace.putModelElement(o.getMessageStructure());
				b = (OperationMessageStructureImpl) o.getMessageStructure();
				if(o instanceof INakedResponsibility){
					// TODO define Responsibility interface
					if(workspace.getOpaeumLibrary().getTaskObject() != null){
						((OperationMessageStructureImpl) b).addInterface(workspace.getOpaeumLibrary().getTaskObject());
					}
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
	@VisitBefore
	public void visitWorkspaceBefore(INakedModelWorkspace ws){
		rootClasses.clear();
	}
	@VisitAfter
	public void visitWorkspaceAfter(INakedModelWorkspace ws){
		if(rootClasses.size() > 1){
			SortedSet<INakedRootObject> rootObjects = new TreeSet<INakedRootObject>(new Comparator<INakedRootObject>(){
				@Override
				public int compare(INakedRootObject o1,INakedRootObject o2){
					return o1.getAllDependencies().size() - o2.getAllDependencies().size();
				}
			});
			outer:for(INakedRootObject ro:ws.getPrimaryModels()){
				for(ICompositionParticipant p:rootClasses){
					if(!ro.getAllDependencies().contains(p.getRootObject())){
						continue outer;
					}
				}
				rootObjects.add(ro);
			}
			if(rootObjects.isEmpty()){
				rootObjects.addAll(ws.getPrimaryModels());
			}
			String compositeName = "businessNetwork";
			NakedBusinessCollaboration bc = new NakedBusinessCollaboration(rootObjects.first());
			ws.putModelElement(bc);
			rootObjects.first().addOwnedElement(bc);
			if(getLibrary().getBusinessNetwork() != null){
				buildCompositionFrom(getLibrary().getBusinessNetwork(), bc, compositeName);
			}
			if(getLibrary().getBusinessCollaboration() != null){
				bc.addInterface(getLibrary().getBusinessCollaboration());
			}
			rootObjects.first().addOwnedElement(bc);
			ws.setApplicationRoot(bc);
			for(ICompositionParticipant p:rootClasses){
				addCompositionToApplicationRoot(bc, p);
			}
		}
	}
	private void addCompositionToApplicationRoot(INakedClassifier root,ICompositionParticipant p){
		NakedAssociationImpl assoc = buildCompositionFrom(root, p, "root");
		INakedProperty toChildren = assoc.getEnd2();
		INakedProperty toRoot = assoc.getEnd1();
		INakedInterface businessCollaboration = getLibrary().getBusinessCollaboration();
		if(p instanceof INakedBusinessComponent){
			if(getLibrary().getBusiness() != null && !p.getInterfaces().contains(getLibrary().getBusiness())){
				p.addOwnedElement(new NakedInterfaceRealizationImpl(getLibrary().getBusiness()));
				getLibrary().getBusiness().addImplementingClassifier((INakedBehavioredClassifier) p);
			}
			INakedProperty businessEnd = (INakedProperty) businessCollaboration.findAssociationEnd("business");
			toChildren.getSubsettedProperties().add(businessEnd);
			INakedProperty collaborationEnd = (INakedProperty) getLibrary().getBusiness().findAssociationEnd("businessCollaboration");
			toRoot.getSubsettedProperties().add(collaborationEnd);
		}else if(businessCollaboration != null){
			INakedProperty actorEnd = (INakedProperty) businessCollaboration.findAssociationEnd("businessActor");
			toChildren.getSubsettedProperties().add(actorEnd);
			INakedProperty collaborationEnd = (INakedProperty) getLibrary().getBusinessActor().findAssociationEnd("businessCollaboration");
			toRoot.getSubsettedProperties().add(collaborationEnd);
		}
		addAffectedElement(toRoot);
		addAffectedElement(toChildren);
	}
	private NakedAssociationImpl buildCompositionFrom(INakedClassifier root,ICompositionParticipant p,String compositeName){
		NakedAssociationImpl assoc = new NakedAssociationImpl();
		InverseArtificialProperty toChildren1 = new InverseArtificialProperty(root, p);
		NonInverseArtificialProperty toRoot1 = new NonInverseArtificialProperty(toChildren1, compositeName);
		toChildren1.setOtherEnd(toRoot1);
		assoc.setEnd(0, toRoot1);
		assoc.setEnd(1, toChildren1);
		root.addOwnedElement(toChildren1);
		p.addOwnedElement(toRoot1);
		p.setEndToComposite(toRoot1);
		return assoc;
	}
}
