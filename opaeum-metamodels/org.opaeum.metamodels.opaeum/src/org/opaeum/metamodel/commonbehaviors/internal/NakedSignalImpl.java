package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.IState;

import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedSignal;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class NakedSignalImpl extends NakedClassifierImpl implements INakedSignal,IDataType{
	private static final long serialVersionUID = 5492485182705048910L;
	public static final String META_CLASS = "signal";
	private Integer listenerPoolSize;
	private boolean isNotification;
	@Override
	public Integer getListenerPoolSize(){
		return listenerPoolSize;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		// TODO Auto-generated method stub
		super.addStereotype(stereotype);
		if(stereotype.getName().equalsIgnoreCase(StereotypeNames.NOTIFICATION)){
			this.setNotification(true);
		}
		if(stereotype.hasValueForFeature("listenerPoolSize")){
			setListenerPoolSize(stereotype.getFirstValueFor("listenerPoolSize").intValue());
		}
	}
	public NakedSignalImpl(){
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public List<IState> getStates(){
		return Collections.emptyList();
	}
	public boolean isPersistent(){
		return false;
	}
	public void setListenerPoolSize(Integer listenerPoolSize){
		this.listenerPoolSize = listenerPoolSize;
	}
	public boolean isNotification(){
		return isNotification;
	}
	public void setNotification(boolean isNotification){
		this.isNotification = isNotification;
	}
}