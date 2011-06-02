package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.bpm.DeadlineKind;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;

public class NakedDeadlineImpl extends AbstractTimeEventImpl implements INakedDeadline{
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
	public String getMetaClass(){
		return "deadline";
	}

}
