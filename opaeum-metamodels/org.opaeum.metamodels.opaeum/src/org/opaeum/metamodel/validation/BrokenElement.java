package org.opaeum.metamodel.validation;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BrokenElement{
	String id;
	private Map<IValidationRule,BrokenRule> brokenRules = new HashMap<IValidationRule,BrokenRule>();
	public BrokenElement(String id){
		super();
		this.id = id;
	}
	public BrokenRule addMessage(IValidationRule rule,Object...messageParameters){
		BrokenRule value = new BrokenRule(id, rule, messageParameters);
		brokenRules.put(rule, value);
		return value;
	}
	public Object[] getMessageParameters(IValidationRule rule){
		return brokenRules.get(rule).getParameters();
	}
	public boolean hasBroken(IValidationRule rule){
		return brokenRules.containsKey(rule);
	}
	public Map<IValidationRule,BrokenRule> getBrokenRules(){
		return brokenRules;
	}
	public Set<BrokenRule> getRulesBrokenSince(Date date){
		Set<BrokenRule> result=new HashSet<BrokenRule>();
		for(BrokenRule brokenRule:brokenRules.values()){
			if(brokenRule.getTimeBroken().after(date)){
				result.add(brokenRule);
			}
		}
		return result;
	}
}
