package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;

public class UimBindingImpl implements UimBinding {
	private PropertyRef next;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public String getLastPropertyUuid() {
		String result = null;
		
		return result;
	}
	
	public PropertyRef getNext() {
		return this.next;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}