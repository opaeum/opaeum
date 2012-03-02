package org.opaeum.rap.runtime;

import java.util.ArrayList;
import java.util.List;

import com.google.gdata.data.contacts.ContactEntry;

public class SelectionForContact{
	private ContactEntry contact;
	private List<Boolean> selection;
	private List<Class<?>> businessActors;
	public SelectionForContact(){
		super();
	}
	public SelectionForContact(ContactEntry contactEntry,List<Class<?>> businessActors){
		this.contact = contactEntry;
		selection = new ArrayList<Boolean>();
		for(@SuppressWarnings("unused") Class<?> class1:businessActors){
			this.selection.add(Boolean.FALSE);
		}
		this.businessActors = businessActors;
	}
	public List<Class<?>> getSelectedClasses(){
		List<Class<?>> result = new ArrayList<Class<?>>();
		int i=0;
		for(Boolean s:this.selection){
			if(s){
				result.add(businessActors.get(i));
			}
			i++;
		}
		return result;
	}
	public List<Boolean> getSelection(){
		return selection;
	}
	public void setSelection(List<Boolean> selection){
		this.selection = selection;
	}
	public ContactEntry getContact(){
		return contact;
	}
	public void setContact(ContactEntry contact){
		this.contact = contact;
	}
}
