package org.opaeum.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;

public abstract class NakedElementOwnerImpl implements INakedElementOwner{
	protected Map<String,INakedElement> ownedElements = new HashMap<String,INakedElement>();
	protected MappingInfo mappingInfo;
	private String name;
	public static Map<Class<?>,Long> counts = new HashMap<Class<?>,Long>();
	public NakedElementOwnerImpl(){
		super();
	}
	public void finalize(){
		counts.put(getClass(), getCount() - 1);
	}
	protected Long getCount(){
		Long long1 = counts.get(getClass());
		if(long1 == null){
			return 0l;
		}else if(long1 % 100 == 0){
			// System.out.println(getClass().getName() + " count :" + long1);
		}
		return long1;
	}
	public Collection<INakedElement> getOwnedElements(){
		TreeSet<INakedElement> result = new TreeSet<INakedElement>(new DefaultOpaeumComparator());
		result.addAll(ownedElements.values());
		return result;
	}
	public void addOwnedElement(INakedElement element){
		ownedElements.put(element.getId(), element);
		if(element != null){
			element.setOwnerElement(this);
		}
	}
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = new HashSet<INakedElement>();
		if(element != null){
			result.add(element);
			ownedElements.remove(element.getId());
			if(recursively){
				for(INakedElement child:new ArrayList<INakedElement>(element.getOwnedElements())){
					result.addAll(element.removeOwnedElement(child, recursively));
					child.markForDeletion();
				}
			}
		}
		return result;
	}
	public Collection<INakedElement> getAllDescendants(){
		Set<INakedElement> result = new HashSet<INakedElement>();
		addAll(result, getOwnedElements());
		return result;
	}
	private void addAll(Set<INakedElement> result,Collection<INakedElement> ownedElements2){
		result.addAll(ownedElements2);
		for(INakedElement e:ownedElements2){
			addAll(result, e.getOwnedElements());
		}
	}
	public INakedElement getOwnedElement(String id){
		return ownedElements.get(id);
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Returns a the UML meta class being wrapped,
	 * 
	 * @return
	 */
	public abstract String getMetaClass();
}