package org.opeum.metamodel.validation;

import java.util.Date;

public class BrokenRule{
	private String elementId;
	private IValidationRule rule;
	private Object[] parameters;
	private Date timeBroken=new Date();
	public BrokenRule(String id,IValidationRule rule2,Object[] messageParameters){
		this.rule = rule2;
		this.elementId = id;
		this.parameters = messageParameters;
	}
	public IValidationRule getRule(){
		return rule;
	}
	public void setRule(IValidationRule rule){
		this.rule = rule;
	}
	public Object[] getParameters(){
		return parameters;
	}
	public void setParameters(Object[] parameters){
		this.parameters = parameters;
	}
	public Date getTimeBroken(){
		return timeBroken;
	}
	public void setTimeBroken(Date timeBroken){
		this.timeBroken = timeBroken;
	}
	public String getElementId(){
		return elementId;
	}
	public void setElementId(String elementId){
		this.elementId = elementId;
	}
}