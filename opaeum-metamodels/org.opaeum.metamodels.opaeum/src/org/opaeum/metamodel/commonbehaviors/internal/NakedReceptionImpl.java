package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedReception;
import org.eclipse.uml2.uml.INakedSignal;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

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
