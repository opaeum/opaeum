package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;

public class NakedReceptionImpl extends NakedModelElementImpl implements INakedReception{
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
