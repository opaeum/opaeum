package org.opaeum.runtime.strategy;

public class ValidationResult{
	public static final ValidationResult SUCCESSES = new ValidationResult();
	public boolean isSuccessFull(){
		return successFull;
	}
	public String[] getMessages(){
		return messages;
	}
	boolean successFull;
	String[] messages;
	public ValidationResult(String ... msg){
		this.messages = msg;
		successFull = false;
	}
	private ValidationResult(){
		successFull = true;
	}
}