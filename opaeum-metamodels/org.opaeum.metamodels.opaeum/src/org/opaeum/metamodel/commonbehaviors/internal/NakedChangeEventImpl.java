package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.Collection;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedValueSpecification;

public class NakedChangeEventImpl extends NakedEventImpl implements INakedChangeEvent{
	private static final long serialVersionUID = 562216620570901336L;
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
		if(this.changeExpression != changeExpression){
			removeOwnedElement(this.changeExpression, true);
			this.changeExpression = changeExpression;
			addOwnedElement(changeExpression);
			changeExpression.setOwnerElement(this);
		}
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
	@Override
	public INakedClassifier getContext(){
		return getBehaviorContext();
	}
}
