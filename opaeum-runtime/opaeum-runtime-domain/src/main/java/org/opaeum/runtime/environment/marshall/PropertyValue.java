package org.opaeum.runtime.environment.marshall;

import java.io.Serializable;

public class PropertyValue implements Serializable{
	private static final long serialVersionUID = -5852805763067486690L;
	private Long id;
	private Value value;
	public PropertyValue(Long id,Value value){
		this.id = id;
		this.value=value;
	}
	public Long getId(){
		return id;
	}
	public Value getValue(){
		return this.value;
	}
}