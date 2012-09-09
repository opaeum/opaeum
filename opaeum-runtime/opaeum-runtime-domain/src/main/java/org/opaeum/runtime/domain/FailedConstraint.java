package org.opaeum.runtime.domain;

import java.io.Serializable;

public class FailedConstraint implements Serializable{
	private static final long serialVersionUID = -3701665429204023336L;
	private String constrainedElementName;
	private String message;
	public FailedConstraint(String constrainedElementName,String message){
		super();
		this.constrainedElementName = constrainedElementName;
		this.message = message;
	}
	public String getConstrainedElementName(){
		return constrainedElementName;
	}
	public void setConstrainedElementName(String constrainedElementName){
		this.constrainedElementName = constrainedElementName;
	}
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	
}

