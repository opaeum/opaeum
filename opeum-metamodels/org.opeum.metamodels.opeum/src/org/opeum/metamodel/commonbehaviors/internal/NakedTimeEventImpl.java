package org.opeum.metamodel.commonbehaviors.internal;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
public class NakedTimeEventImpl extends AbstractTimeEventImpl implements INakedTimeEvent {
	private static final long serialVersionUID = 8679314599756152781L;
	public NakedTimeEventImpl() {
		super();
	}
	@Override
	public String getMetaClass() {
		return "TimeEvent";
	}
	@Override
	public INakedBehavior getBehaviorContext(){
		INakedElementOwner o= getOwnerElement();
		while(!(o instanceof INakedBehavior)){
			o=((INakedElement) o).getOwnerElement();
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
