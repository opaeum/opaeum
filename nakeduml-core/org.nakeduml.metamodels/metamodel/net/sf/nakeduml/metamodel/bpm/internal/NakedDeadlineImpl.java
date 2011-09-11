package net.sf.nakeduml.metamodel.bpm.internal;

import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedDefinedResponsibility;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

import org.nakeduml.runtime.domain.DeadlineKind;

public class NakedDeadlineImpl extends AbstractTimeEventImpl implements INakedDeadline{
	private static final long serialVersionUID = -2226272827359217290L;
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
	@Override
	public INakedClassifier getContext(){
		if(getOrigin() instanceof INakedEmbeddedTask){
			return ((INakedEmbeddedTask) getOrigin()).getMessageStructure();
		}else if(getOrigin() instanceof INakedResponsibility){
			return ((INakedResponsibility) getOrigin()).getMessageStructure();
		}
		return null;
	}
}
