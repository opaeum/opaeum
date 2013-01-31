package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimField;

public class FieldBindingImpl implements FieldBinding {
	private UimField field;
	private PropertyRef next;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public UimField getField() {
		return this.field;
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
	
	public void setField(UimField field) {
		this.field=field;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}