package org.nakeduml.environment.adaptor;

import java.io.Serializable;

public class PayloadToResend implements Serializable{

	private Serializable payload;
	private String destination;

	public PayloadToResend(String destination,Serializable payload){
		this.payload=payload;
		this.destination=destination;
	}

	public Serializable getPayload(){
		return payload;
	}

	public String getDestination(){
		return destination;
	}
}
