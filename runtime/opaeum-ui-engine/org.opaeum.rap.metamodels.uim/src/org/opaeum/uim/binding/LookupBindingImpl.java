package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.control.UimLookup;

public class LookupBindingImpl implements LookupBinding {
	private UimLookup lookup;
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
	
	public UimLookup getLookup() {
		return this.lookup;
	}
	
	public PropertyRef getNext() {
		return this.next;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setLookup(UimLookup lookup) {
		this.lookup=lookup;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}