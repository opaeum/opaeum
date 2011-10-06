package org.opeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.metamodel.actions.INakedSendObjectAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;

public class NakedSendObjectActionImpl extends NakedInvocationActionImpl implements INakedSendObjectAction{
	private static final long serialVersionUID = 110231012348710L;
	private INakedInputPin object;
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}
	@Override
	public INakedInputPin getRequest(){
		return this.object;
	}
	public void setRequest(INakedInputPin object){
		this.object = object;
	}
}
