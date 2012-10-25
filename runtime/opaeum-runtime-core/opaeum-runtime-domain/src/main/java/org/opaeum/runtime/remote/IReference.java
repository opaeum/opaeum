package org.opaeum.runtime.remote;

import java.io.Serializable;

import org.opaeum.runtime.domain.IPersistentObject;

public interface IReference extends Serializable{
	public Long getId();
	public void setId(Long id);
	public String getName();
	public void setName(String name);
	public abstract Class<? extends IPersistentObject> getReferredClass();
}
