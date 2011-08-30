package org.nakeduml.runtime.environment.marshall;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PropertyValue implements Serializable{
	private static final long serialVersionUID = -5852805763067486690L;
	private Integer id;
	private Value value;
	public PropertyValue(Integer id,Value value){
		this.id = id;
		this.value=value;
	}
	public Integer getId(){
		return id;
	}
	public Value getValue(){
		return this.value;
	}
}