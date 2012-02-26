package org.opaeum.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedInterface;

public class NakedInterfaceImpl extends NakedClassifierImpl implements INakedInterface{
	private Collection<INakedBehavioredClassifier> implementingClassifiers = new ArrayList<INakedBehavioredClassifier>();
	private static final long serialVersionUID = 1406494153933781228L;
	public static final String META_CLASS = "interface";
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public boolean getIsAbstract(){
		return true;
	}
	public void addImplementingClassifier(INakedBehavioredClassifier c){
		implementingClassifiers.add(c);
	}
	public Collection<INakedBehavioredClassifier> getImplementingClassifiers(){
		return implementingClassifiers;
	}
	public void removeImplementingClassifier(INakedBehavioredClassifier implementingClassifier){
		this.implementingClassifiers.remove(implementingClassifier);
	}
	@Override
	public boolean isPersistent(){
		return false;//TODO investigate why this is necessary
	}
	@Override
	public boolean isFact(){
		return false;
	}
	@Override
	public boolean hasReceptionFor(INakedSignal signal){
		for(INakedReception r:getEffectiveReceptions()){
			if(signal.equals(r.getSignal())){
				return true;
			}
		}
		return false;
	}
}
