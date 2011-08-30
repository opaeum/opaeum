package org.nakeduml.runtime.environment.marshall;

import java.io.Serializable;


public class SerializableValue extends Value{
	private static final long serialVersionUID = 2404613268816695710L;
	Serializable value;
	public SerializableValue(Integer typeId, Serializable value){
		super(typeId);
		this.value = value;
	}
	public Serializable getValue(){
		return value;
	}

}