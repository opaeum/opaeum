package org.nakeduml.environment;

import java.io.Serializable;

import org.hibernate.Session;

public class SerializableValue extends Value{
	private static final long serialVersionUID = 2404613268816695710L;
	Serializable value;
	public SerializableValue(){
	}
	public SerializableValue(Serializable value){
		this.value = value;
	}
	public Serializable getValue(){
		return value;
	}
	public void setValue(Serializable value){
		this.value = value;
	}
	@Override
	public Object getValue(Session session){
		return value;
	}
	@Override
	public Class<?> getValueClass(){
		return value.getClass();
	}
}