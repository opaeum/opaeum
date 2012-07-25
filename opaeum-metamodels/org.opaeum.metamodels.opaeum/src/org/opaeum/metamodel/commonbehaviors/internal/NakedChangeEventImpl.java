package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedChangeEvent;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedTrigger;
import org.eclipse.uml2.uml.INakedValueSpecification;

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
