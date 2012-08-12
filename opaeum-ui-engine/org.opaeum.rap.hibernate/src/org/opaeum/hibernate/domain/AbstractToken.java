package org.opaeum.hibernate.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractToken{
	private boolean isActive;
	
	public boolean isActive(){
		return isActive;
	}

	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
}
