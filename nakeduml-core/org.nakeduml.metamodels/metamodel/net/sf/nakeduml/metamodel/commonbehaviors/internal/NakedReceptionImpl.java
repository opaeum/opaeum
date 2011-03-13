package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedReceptionImpl extends NakedElementImpl implements INakedReception{
	INakedSignal signal;
	public INakedSignal getSignal(){
		return signal;
	}
	public void setSignal(INakedSignal signal){
		this.signal = signal;
	}
	@Override
	public String getMetaClass(){
		return "reception";
	}
}
