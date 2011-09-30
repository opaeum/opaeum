package org.opeum.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.core.INakedInstanceSpecification;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.NakedClassifierImpl;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.IState;

public class NakedSignalImpl extends NakedClassifierImpl implements INakedSignal,IDataType{
	private static final long serialVersionUID = 5492485182705048910L;
	public static final String META_CLASS = "signal";
	private Integer listenerPoolSize;
	@Override
	public Integer getListenerPoolSize(){
		return listenerPoolSize;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		// TODO Auto-generated method stub
		super.addStereotype(stereotype);
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
	public List<INakedProperty> getArgumentParameters(){
		List<INakedProperty> results = new ArrayList<INakedProperty>();
		if(hasSupertype()){
			results.addAll(((NakedSignalImpl) getSupertype()).getArgumentParameters());
		}
		results.addAll(getOwnedArgumentParamaters());
		return results;
	}
	private List<INakedProperty> getOwnedArgumentParamaters(){
		List<INakedProperty> list = new ArrayList<INakedProperty>(getOwnedAttributes());
		Collections.<INakedProperty>sort(list, new Comparator<INakedProperty>(){
			public int compare(INakedProperty o1,INakedProperty o2){
				int diff = o1.getOwnedAttributeIndex() - o2.getOwnedAttributeIndex();
				if(diff==0){
					return o1.getName().compareTo(o2.getName());
				}
				return diff;
			}
		});
		Iterator<INakedProperty> iter = list.iterator();
		while(iter.hasNext()){
			INakedProperty prop = iter.next();
			if(prop.isDerived() || prop.isReadOnly()){
				iter.remove();
			}
		}
		return list;
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
}