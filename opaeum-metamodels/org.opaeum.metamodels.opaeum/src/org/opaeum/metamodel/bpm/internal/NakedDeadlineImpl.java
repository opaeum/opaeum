package org.opaeum.metamodel.bpm.internal;

import org.opaeum.metamodel.bpm.INakedDeadline;
import org.opaeum.metamodel.bpm.INakedDefinedResponsibility;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.bpm.INakedResponsibility;
import org.opaeum.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opeum.runtime.domain.DeadlineKind;

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
