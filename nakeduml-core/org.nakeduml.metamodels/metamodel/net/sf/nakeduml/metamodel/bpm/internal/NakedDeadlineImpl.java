package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.DeadlineKind;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;

public class NakedDeadlineImpl extends NakedTimeEventImpl implements INakedDeadline{
	private DeadlineKind kind;
	@Override
	public DeadlineKind getKind(){
		return this.kind;
	}
	public void setKind(DeadlineKind kind){
		this.kind = kind;
	}
	public INakedDefinedResponsibility getOrigin(){
		return (INakedDefinedResponsibility) getOwnerElement();
	}
	@Override
	public INakedBehavior getOwningBehavior(){
		if(getOrigin() instanceof INakedAction){
			return ((INakedAction) getOrigin()).getActivity();
		}else{
			return null;
		}
	}
}
