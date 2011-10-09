package org.opaeum.validation.namegeneration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.visitor.NakedElementOwnerVisitor;

public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	Set<INakedElement> affectedElements = new HashSet<INakedElement>();
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
		affectedElements.clear();
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
			}else if(o instanceof INakedStructuredActivityNode){
				visitRecursively(((INakedStructuredActivityNode) o).getMessageStructure());
			}
		}
	}
	protected boolean shouldVisitRecursively(INakedElementOwner o){
		return !(o instanceof INakedRootObject && ((INakedRootObject) o).getStatus().isNamed());
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
}