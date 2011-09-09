package net.sf.nakeduml.validation.namegeneration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitSpec;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.EndToAssociationClass;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;

public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	Set<INakedElement> affectedElements = new HashSet<INakedElement>();
	protected NakedUmlConfig config;
	public Set<INakedElement> getAffectedElements(){
		return affectedElements;
	}
	public void initialize(NakedUmlConfig c){
		this.config=c;
		affectedElements.clear();
	}
	
	@Override
	public void visitRecursively(INakedElementOwner o){

		super.visitRecursively(o);
		if(o instanceof INakedOperation && BehaviorUtil.hasExecutionInstance((IParameterOwner) o)){
			visitRecursively(((INakedOperation) o).getMessageStructure());
		}else if(o instanceof INakedEmbeddedTask){
			visitRecursively(((INakedEmbeddedTask) o).getMessageStructure());
		}else if(o instanceof INakedCallAction && BehaviorUtil.hasMessageStructure((INakedAction) o)){
			visitRecursively(((INakedCallAction) o).getMessageStructure());
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
}