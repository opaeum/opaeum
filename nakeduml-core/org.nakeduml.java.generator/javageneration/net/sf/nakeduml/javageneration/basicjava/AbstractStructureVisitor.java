package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.components.INakedComponent;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.model.IOperation;

public abstract class AbstractStructureVisitor extends StereotypeAnnotator{
	public AbstractStructureVisitor(){
		super();
	}
	protected abstract void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(INakedComplexStructure umlOwner);
	@VisitAfter(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class,INakedSignal.class,INakedComponent.class
	})
	public void visitFeaturesOf(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			for(INakedProperty p:c.getEffectiveAttributes()){
				if(p.getOwner() == c || p.getOwner() instanceof INakedInterface){
					if(p.getAssociation() instanceof INakedAssociationClass){
						visitProperty(c, OJUtil.buildAssociationClassMap(p, getOclEngine().getOclLibrary()));
					}else{
						visitProperty(c, OJUtil.buildStructuralFeatureMap(p));
					}
				}
			}
			for(INakedOperation o:c.getEffectiveOperations()){
				if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
					visitOperation(o);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior umlOwner){
		if(BehaviorUtil.hasExecutionInstance(umlOwner)){
			visitFeaturesOf(umlOwner);
			visitComplexStructure(umlOwner);
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
				}
			}
		}
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
		if(o.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(o)){
			INakedMessageStructure umlOwner = o.getMessageStructure();
			visitComplexStructure(umlOwner);
			visitFeaturesOf(umlOwner);
		}
	}
	@VisitBefore(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class,INakedSignal.class,INakedComponent.class
	})
	public void visitClassifier(INakedClassifier umlOwner){
		visitComplexStructure((INakedComplexStructure) umlOwner);
	}
	public void visitTask(INakedEmbeddedTask node){
		INakedMessageStructure msg = node.getMessageStructure();
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
		visitComplexStructure(msg);
		visitFeaturesOf(msg);
	}
	public void visitCallAction(INakedCallAction node){
		if(node.getCalledElement().getContext() == null){
			// Contextless behaviors need to be attached to the process in an emulated compositional association to ensure transitive
			// persistence
			INakedComplexStructure umlOwner = node.getMessageStructure();
			visitComplexStructure(umlOwner);
			visitFeaturesOf(umlOwner);
		}else if((BehaviorUtil.mustBeStoredOnActivity(node))){
			// Their classes will be built elsewhere, so just visit the output pin as an artificial association
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getLibrary()));
		}
	}
}