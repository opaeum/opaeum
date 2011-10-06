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
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
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

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected abstract void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(INakedComplexStructure umlOwner);
	@VisitBefore(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class,INakedAssociation.class,INakedSignal.class,INakedComponent.class,INakedEnumeration.class,
			INakedBehavior.class
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
					if(umlOwner instanceof INakedActivity){
						INakedActivity a = (INakedActivity) umlOwner;
						visitVariables(a.getVariables());
						for(INakedActivityNode n:a.getActivityNodesRecursively()){
							if(n instanceof INakedOutputPin){
								visitOutputPin((INakedOutputPin) n);
							}else if(n instanceof INakedExpansionNode){
								visitExpansionNode((INakedExpansionNode) n);
							}else if(n instanceof INakedEmbeddedTask){
								visitTask((INakedEmbeddedTask) n);
							}else if(n instanceof INakedCallAction){
								visitCallAction((INakedCallAction) n);
							}else if(n instanceof INakedAcceptCallAction){
								visitAcceptCallAction((INakedAcceptCallAction) n);
							}
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
	private void visitAcceptCallAction(INakedAcceptCallAction node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			// Their classes will be built elsewhere, so just visit the relationship with the message structure as an artificial association
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public void visitAssociationClassProperty(INakedClassifier c,AssociationClassEndMap map){
	}
	private void visitVariables(Collection<INakedActivityVariable> vars){
		for(INakedActivityVariable var:vars){
			if(BehaviorUtil.mustBeStoredOnActivity(var)){
				// Variables contained by StructuredActivityNodes require
				// contextable variables which will be delegated to BPM engine
				visitProperty(var.getActivity(), OJUtil.buildStructuralFeatureMap(var.getActivity(), var));
			}
		}
	}
	private void visitOutputPin(INakedOutputPin node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node, true));
		}
	}
	private void visitExpansionNode(INakedExpansionNode node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
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
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
		visitFeaturesOf(msg);
	}
	protected void visitCallAction(INakedCallAction node){
		if(node.getCalledElement().getContext() == null && node.getMessageStructure() != null){
			// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
			// persistence
			INakedComplexStructure umlOwner = node.getMessageStructure();
			visitFeaturesOf(umlOwner);
		}else if((BehaviorUtil.mustBeStoredOnActivity(node))){
			// Their classes will be built elsewhere, so just visit the action as an artificial association with the message structure
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
	}
}