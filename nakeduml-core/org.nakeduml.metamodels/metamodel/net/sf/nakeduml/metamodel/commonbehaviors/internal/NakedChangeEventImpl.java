package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.Collection;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedChangeEventImpl extends NakedElementImpl implements INakedChangeEvent{
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
	@Override
	public INakedBehavior getOwningBehavior(){
		// The extractor will make sure the event is duplicated for each context
		return (INakedBehavior) getOwnerElement();
	}
	public void setChangeExpression(INakedValueSpecification changeExpression){
		this.changeExpression = changeExpression;
	}
	@Override
	public String getMetaClass(){
		return "changeEvent";
	}
}
