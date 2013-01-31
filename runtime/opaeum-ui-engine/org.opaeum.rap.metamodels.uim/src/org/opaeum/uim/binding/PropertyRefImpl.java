package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;

public class PropertyRefImpl implements PropertyRef {
	private UimBinding binding;
	private PropertyRef next;
	private PropertyRef previous;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UimBinding getBinding() {
		return this.binding;
	}
	
	public PropertyRef getNext() {
		return this.next;
	}
	
	public PropertyRef getPrevious() {
		return this.previous;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setBinding(UimBinding binding) {
		this.binding=binding;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setPrevious(PropertyRef previous) {
		this.previous=previous;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}