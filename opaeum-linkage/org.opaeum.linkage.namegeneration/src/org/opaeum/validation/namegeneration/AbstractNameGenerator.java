package org.opeum.validation.namegeneration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.visit.VisitSpec;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedCallAction;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.bpm.INakedEmbeddedTask;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedInstanceSpecification;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.INakedValueSpecification;
import org.opeum.metamodel.core.IParameterOwner;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;

public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	Set<INakedElement> affectedElements = new HashSet<INakedElement>();
	protected OpeumConfig config;
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public Set<INakedElement> getAffectedElements(){
		return affectedElements;
	}
	public void initialize(OpeumConfig c){
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