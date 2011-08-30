package net.sf.nakeduml.metamodel.commonbehaviors.internal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
public class NakedTimeEventImpl extends AbstractTimeEventImpl implements INakedTimeEvent {
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
