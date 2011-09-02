package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;

public class NakedReceptionImpl extends NakedElementImpl implements INakedReception{
	private static final long serialVersionUID = -1057192220003496030L;
	INakedSignal signal;
	private Collection<INakedBehavior> methods=new HashSet<INakedBehavior>();
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
	@Override
	public Collection<INakedBehavior> getMethods(){
		return this.methods;
	}
	public void addMethod(INakedBehavior b){
		this.methods.add(b);
	}
	@Override
	public INakedClassifier getOwner(){
		return (INakedClassifier) getOwnerElement();
	}
}
