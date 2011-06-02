package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.Collection;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public class NakedChangeEventImpl extends NakedEventImpl implements INakedChangeEvent{
	private INakedValueSpecification changeExpression;
	@Override
	public INakedValueSpecification getChangeExpression(){
		return this.changeExpression;
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> ownedElements = super.getOwnedElements();
		if(changeExpression != null){
			ownedElements.add(changeExpression);
		}
		return ownedElements;
	}
	public void setChangeExpression(INakedValueSpecification changeExpression){
		this.changeExpression = changeExpression;
	}
	@Override
	public String getMetaClass(){
		return "changeEvent";
	}
	@Override
	public INakedBehavior getBehaviorContext(){
		INakedElementOwner o = getOwnerElement();
		while(!(o instanceof INakedBehavior)){
			o = ((INakedElement) o).getOwnerElement();
		}
		return (INakedBehavior) o;
	}
	@Override
	public INakedTrigger getOwningTrigger(){
		return (INakedTrigger) getOwnerElement();
	}
}
