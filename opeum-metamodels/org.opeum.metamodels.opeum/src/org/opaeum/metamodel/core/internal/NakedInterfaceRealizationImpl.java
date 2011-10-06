package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedInterfaceRealization;

public class NakedInterfaceRealizationImpl extends NakedElementImpl implements INakedInterfaceRealization{
	private static final long serialVersionUID = 7528018002930390436L;
	INakedInterface contract;
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
}
