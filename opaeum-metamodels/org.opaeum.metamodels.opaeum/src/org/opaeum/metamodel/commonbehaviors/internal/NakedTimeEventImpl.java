package org.opaeum.metamodel.commonbehaviors.internal;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
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
