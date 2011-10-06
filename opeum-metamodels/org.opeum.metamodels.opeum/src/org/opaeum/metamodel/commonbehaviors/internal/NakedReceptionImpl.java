package org.opeum.metamodel.commonbehaviors.internal;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedReception;
import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.internal.NakedElementImpl;

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
