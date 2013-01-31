package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimDataTable;

public class TableBindingImpl implements TableBinding {
	private PropertyRef next;
	private UimDataTable table;
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
	
	public UimDataTable getTable() {
		return this.table;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setNext(PropertyRef next) {
		this.next=next;
	}
	
	public void setTable(UimDataTable table) {
		this.table=table;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}