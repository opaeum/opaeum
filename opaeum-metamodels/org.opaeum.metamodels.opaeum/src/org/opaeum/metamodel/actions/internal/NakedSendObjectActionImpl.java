package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedSendObjectAction;

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
		replacePin(this.object, object);
		this.object = object;
	}
}
