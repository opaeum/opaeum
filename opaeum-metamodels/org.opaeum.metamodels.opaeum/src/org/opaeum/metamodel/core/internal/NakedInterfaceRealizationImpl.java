package org.opaeum.metamodel.core.internal;

import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;

public class NakedInterfaceRealizationImpl extends NakedElementImpl implements INakedInterfaceRealization{
	private static final long serialVersionUID = 7528018002930390436L;
	INakedInterface contract;
	private int index;
	
	@Override
	public String getMetaClass(){
		return "interfaceRealization";
	}
	public INakedInterface getContract(){
		return contract;
	}
	public void setContract(INakedInterface contract){
		if(this.contract != null){
			contract.removeImplementingClassifier(getImplementingClassifier());
		}
		this.contract = contract;
		if(contract != null){
			contract.addImplementingClassifier(getImplementingClassifier());
		}
	}
	public INakedBehavioredClassifier getImplementingClassifier(){
		return (INakedBehavioredClassifier) getOwnerElement();
	}
	@Override
	public int getIndex(){
		return index;
	}
	@Override
	public void setIndex(int index){
		if(index==-1){
			System.out.println();
		}
		this.index = index;
	}
}
