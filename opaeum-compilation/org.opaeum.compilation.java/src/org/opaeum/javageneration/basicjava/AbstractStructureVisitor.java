package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Set;

import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedObservantElement;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.components.INakedComponent;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.emulated.NakedBusinessCollaboration;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.usecases.INakedActor;

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected abstract void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(INakedComplexStructure umlOwner);
	@VisitBefore(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class,INakedAssociation.class,INakedSignal.class,INakedComponent.class,INakedEnumeration.class,
			INakedBehavior.class,INakedActor.class,NakedBusinessCollaboration.class
	})
	public void visitFeaturesOf(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			if(c instanceof INakedComplexStructure){
				visitComplexStructure((INakedComplexStructure) c);
				if(c instanceof INakedBehavior){
					INakedBehavior umlOwner = (INakedBehavior) c;
					if(BehaviorUtil.hasExecutionInstance(umlOwner)){
						if(umlOwner.getSpecification() == null){
							for(INakedParameter parm:umlOwner.getOwnedParameters()){
								visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
							}
						}
					}
					if(umlOwner instanceof INakedStateMachine){
						visitObservations(umlOwner, (INakedObservantElement) umlOwner);
					}
					if(umlOwner instanceof INakedActivity){
						
						INakedActivity a = (INakedActivity) umlOwner;
						
						visitObservations(umlOwner, a);
						visitVariables(umlOwner, a.getVariables());
						if(BehaviorUtil.hasExecutionInstance(a)){
							visitActivityNodesRecursively(a, (ActivityNodeContainer) a);
						}
					}
				}
			}
			Set<INakedProperty> directlyImplementedAttributes = c.getDirectlyImplementedAttributes();
			for(INakedProperty p:directlyImplementedAttributes){
				if(p.isNavigable()){
					if(OJUtil.hasOJClass((INakedClassifier) p.getAssociation())){
						visitAssociationClassProperty(c, new AssociationClassEndMap(p));
					}else{
						visitProperty(c, OJUtil.buildStructuralFeatureMap(p));
					}
				}
			}
			for(INakedOperation o:c.getDirectlyImplementedOperations()){
				visitOperation(o);
			}
		}
	}
	protected void visitObservations(INakedClassifier umlOwner,INakedObservantElement a){
		Collection<INakedTimeObservation> timeObservations = a.getTimeObservations();
		Collection<INakedDurationObservation> durationObservations = a.getDurationObservations();
		for(INakedTimeObservation o:timeObservations){
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, o));
		}
		for(INakedDurationObservation o:durationObservations){
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, o));
		}
	}
	protected void visitActivityNodesRecursively(INakedClassifier owner,ActivityNodeContainer container){
		for(INakedActivityNode n:container.getActivityNodes()){
			if(n instanceof INakedAction){
				visitOutputPins((INakedAction) n);
			}
			if(n instanceof INakedExpansionRegion){
				visitExpansionNodes((INakedExpansionRegion) n);
			}
			if(n instanceof INakedEmbeddedTask){
				visitTask((INakedEmbeddedTask) n);
			}else if(n instanceof INakedCallAction){
				visitCallAction((INakedCallAction) n);
			}else if(n instanceof INakedAcceptCallAction){
				visitAcceptCallAction((INakedAcceptCallAction) n);
			}
			if(n instanceof INakedStructuredActivityNode){
				INakedMessageStructure msg = ((INakedStructuredActivityNode) n).getMessageStructure();
				visitFeaturesOf(msg);
				visitActivityNodesRecursively(msg, (ActivityNodeContainer) n);
				visitObservations(msg, (INakedObservantElement) n);
			}
		}
	}
	private void visitAcceptCallAction(INakedAcceptCallAction node){
		if(BehaviorUtil.hasMessageStructure(node)){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void visitAssociationClassProperty(INakedClassifier c,AssociationClassEndMap map){
	}
	private void visitVariables(INakedClassifier owner,Collection<INakedActivityVariable> vars){
		for(INakedActivityVariable var:vars){
			visitProperty(owner, OJUtil.buildStructuralFeatureMap(owner, var));
		}
	}
	private void visitOutputPins(INakedAction a){
		Collection<INakedOutputPin> nodes = a.getOutput();
		for(INakedOutputPin node:nodes){
			visitProperty(a.getNearestStructuredElementAsClassifier(), OJUtil.buildStructuralFeatureMap(a.getNearestStructuredElementAsClassifier(), node, true));
		}
	}
	private void visitExpansionNodes(INakedExpansionRegion region){
		for(INakedExpansionNode node:region.getOutputElement()){
			// NB output expansion nodes sit on the parent container
			visitProperty(region.getNearestStructuredElementAsClassifier(), OJUtil.buildStructuralFeatureMap(region.getNearestStructuredElementAsClassifier(), node,true));
		}
		for(INakedExpansionNode node:region.getInputElement()){
			// NB input expansion nodes sit on the expansion region class
			visitProperty(region.getMessageStructure(), OJUtil.buildStructuralFeatureMap(region.getMessageStructure(), node,false));
		}
	}
	private void visitOperation(INakedOperation o){
		if(BehaviorUtil.hasExecutionInstance(o)){
			INakedMessageStructure umlOwner = o.getMessageStructure();
			visitFeaturesOf(umlOwner);
		}
	}
	public void visitTask(INakedEmbeddedTask node){
		INakedMessageStructure msg = node.getMessageStructure();
		visitProperty(node.getNearestStructuredElementAsClassifier(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		visitFeaturesOf(msg);
	}
	protected void visitCallAction(INakedCallAction node){
		if(BehaviorUtil.hasMessageStructure(node)){
			if(node.getCalledElement().getContext() == null){
				// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
				// persistence
				INakedComplexStructure umlOwner = node.getMessageStructure();
				visitFeaturesOf(umlOwner);
			}else{
				// Their classes will be built elsewhere, so just visit the action as an artificial association with the message structure
				visitProperty(node.getNearestStructuredElementAsClassifier(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
			}
		}
	}
}