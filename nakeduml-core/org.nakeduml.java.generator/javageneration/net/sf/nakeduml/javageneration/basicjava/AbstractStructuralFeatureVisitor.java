package net.sf.nakeduml.javageneration.basicjava;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

public abstract class AbstractStructuralFeatureVisitor extends StereotypeAnnotator{
	public AbstractStructuralFeatureVisitor(){
		super();
	}
	protected abstract void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap);
	protected abstract void visitComplexStructure(INakedComplexStructure umlOwner);
	@VisitAfter(matchSubclasses = true,match = {
			INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class
	})
	public void visitFeaturesOf(INakedClassifier entity){
		if(OJUtil.hasOJClass(entity)){
			for(INakedProperty p:entity.getEffectiveAttributes()){
				if(p.getOwner() instanceof INakedInterface || p.getOwner() == entity){
					if(p.getAssociation() instanceof INakedAssociationClass){
						visitProperty(entity, OJUtil.buildAssociationClassMap(p, getOclEngine().getOclLibrary()));
					}else{
						visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
					}
				}
			}
			if(entity instanceof ICompositionParticipant){
				ICompositionParticipant cp = (ICompositionParticipant) entity;
				boolean noConcreteEndToComposite = cp.getEndToComposite() == null || (!(cp instanceof INakedInterface) && cp.getEndToComposite().isDerived());
				if(noConcreteEndToComposite && cp.getNameSpace() instanceof INakedComplexStructure){
					visitProperty(entity, OJUtil.buildStructuralFeatureMapToOwningObject(cp, getOclEngine().getOclLibrary()));
					visitProperty((INakedClassifier) entity.getNameSpace(), OJUtil.buildStructuralFeatureMapFromOwningObject(cp, getOclEngine().getOclLibrary()));
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitVariable(INakedActivityVariable var){
		if(BehaviorUtil.hasExecutionInstance(var.getActivity()) && var.getOwnerElement() instanceof INakedActivity && BehaviorUtil.mustBeStoredOnActivity(var)){
			// Variables contained by StructuredActivityNodes require
			// contextable variables which will be delegated to BPM engine
			visitProperty(var.getActivity(), OJUtil.buildStructuralFeatureMap(var.getActivity(), var));
		}
	}
	@VisitBefore(matchSubclasses = true,match = {
		INakedOutputPin.class
	})
	public void visitOutputPin(INakedOutputPin node){
		if(BehaviorUtil.hasExecutionInstance(node.getActivity()) && BehaviorUtil.mustBeStoredOnActivity(node)){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node, true));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionNode(INakedExpansionNode node){
		if(BehaviorUtil.mustBeStoredOnActivity(node)){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}
	@VisitBefore()
	public void visitOperation(INakedOperation o){
		if(o.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(o)){
			OperationMessageStructureImpl umlOwner = new OperationMessageStructureImpl(o);
			visitComplexStructure(umlOwner);
			for(INakedParameter parm:o.getOwnedParameters()){
				visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
			}
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMapToContext(o, getOclEngine().getOclLibrary()));
			visitProperty(o.getContext(), OJUtil.buildStructuralFeatureMapFromContext(o, getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior umlOwner){
		if(BehaviorUtil.hasExecutionInstance(umlOwner)){
			visitFeaturesOf(umlOwner);
			if(umlOwner.getSpecification() == null){
				for(INakedParameter parm:umlOwner.getOwnedParameters()){
					visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
				}
			}
			visitProperty(umlOwner, OJUtil.buildStructuralFeatureMapToContext(umlOwner, getOclEngine().getOclLibrary()));
			visitProperty(umlOwner.getContext(), OJUtil.buildStructuralFeatureMapFromContext(umlOwner, getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore()
	public void visitScreenFlowTask(INakedEmbeddedScreenFlowTask node){
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore()
	public void visitEmbeddedSingleScreenTask(INakedEmbeddedSingleScreenTask node){
		INakedComplexStructure umlOwner = node.getMessageStructure();
		visitComplexStructure(umlOwner);
		List<? extends INakedProperty> ownedAttributes = umlOwner.getOwnedAttributes();
		for(INakedProperty p:ownedAttributes){
			visitProperty(umlOwner, new NakedStructuralFeatureMap(p));
		}
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node){
		if((BehaviorUtil.mustBeStoredOnActivity(node))){
			visitProperty(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}
}