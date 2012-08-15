package org.opaeum.metamodel.commonbehaviors.internal;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedTimeEvent;
import org.eclipse.uml2.uml.INakedTrigger;
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
