package org.opaeum.runtime.remote;

import java.io.Serializable;

import org.opaeum.runtime.domain.IPersistentObject;

public abstract class Reference implements IReference{
	private static final long serialVersionUID = 7116069975027241285L;
	Long id;
	String name;
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public abstract Class<? extends IPersistentObject> getReferredClass();
}
