package org.opaeum.validation.namegeneration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.visitor.NakedElementOwnerVisitor;

public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	Set<INakedElement> affectedElements;
	protected OpaeumConfig config;
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public Set<INakedElement> getAffectedElements(){
		return affectedElements;
	}
	public void initialize(OpaeumConfig c){
		this.config = c;
		affectedElements = new HashSet<INakedElement>();
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(shouldVisitRecursively(o)){
			super.visitRecursively(o);
			if(o instanceof INakedOperation && BehaviorUtil.hasExecutionInstance((IParameterOwner) o)){
				visitRecursively(((INakedOperation) o).getMessageStructure());
			}else if(o instanceof INakedEmbeddedTask){
				visitRecursively(((INakedEmbeddedTask) o).getMessageStructure());
			}else if(o instanceof INakedCallAction && BehaviorUtil.hasMessageStructure((INakedAction) o)){
				visitRecursively(((INakedCallAction) o).getMessageStructure());
			}else if(o instanceof INakedStructuredActivityNode && ((INakedStructuredActivityNode) o).getMessageStructure() != null){
				visitRecursively(((INakedStructuredActivityNode) o).getMessageStructure());
			}
			// Some extra work required to ensure that a class and all its required elements have persistent names 
			//Primarily for derived elements
			if(o instanceof INakedProperty){
				INakedProperty p = (INakedProperty) o;
	
				if(p.getOtherEnd() != null && !hasName(p.getOtherEnd())){
					super.visitRecursively(p.getOtherEnd());
				}
				if(p.getAssociation() != null && !hasName( (INakedAssociation) p.getAssociation())){
					super.visitRecursively((INakedElement) p.getAssociation());
				}
			}else if(o instanceof INakedClassifier){
				INakedClassifier c = (INakedClassifier) o;
				for(INakedProperty p:c.getEffectiveAttributes()){
					if(!hasName(p) && p.getOwner()!=o && p.getRootObject()!=c.getRootObject()){
						visitRecursively(p);
					}
				}
				for(INakedOperation op:c.getEffectiveOperations()){
					if(!hasName(op) && op.getOwner()!=o && op.getRootObject()!=c.getRootObject()){
						visitRecursively(op);
					}
				}
				//TODO inherited states and actions?
				
			}

		}
	}
	//To prevent recursion
	protected abstract boolean hasName(INakedElement p);
	protected boolean shouldVisitRecursively(INakedElementOwner o){
		if(o instanceof INakedRootObject){
			return !(((INakedRootObject) o).getStatus().isNamed());
		}else{
			return true;
		}
	}
	protected static INakedValueSpecification getTaggedValue(INakedElement element,String...tags){
		try{
			Iterator<? extends INakedInstanceSpecification> iter = element.getStereotypes().iterator();
			while(iter.hasNext()){
				INakedInstanceSpecification is = iter.next();
				for(String tag:tags){
					if(is.hasValueForFeature(tag)){
						return is.getFirstValueFor(tag);
					}
				}
			}
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void maybeVisit(INakedElementOwner o,VisitSpec v){
		if(!(o instanceof INakedElement && ((INakedElement) o).isMarkedForDeletion())){
			super.maybeVisit(o, v);
		}
	}
	public void release(){
		super.release();
		this.affectedElements = null;
	}
}