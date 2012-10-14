package org.opaeum.metamodel.validation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.emf.workspace.EmfWorkspace;

public class ErrorMap{
	public ErrorMap(){
		super();
	}
	private Map<String,BrokenElement> errors = new HashMap<String,BrokenElement>();
	public Map<String,BrokenElement> getErrors(){
		return errors;
	}
	public Set<BrokenRule> findRelevantBrokenRules(EObject e){
		Set<BrokenRule> result = new HashSet<BrokenRule>();
		for(BrokenElement brokenElement:this.errors.values()){
			Collection<BrokenRule> values = brokenElement.getBrokenRules().values();
			for(BrokenRule brokenRule:values){
				Object[] parameters = brokenRule.getParameters();
				for(Object object:parameters){
					if(object == e){
						result.add(brokenRule);
					}
				}
			}
		}
		BrokenElement brokenElement = this.getErrors().get(EmfWorkspace.getId(e));
		if(brokenElement!=null){
			result.addAll(brokenElement.getBrokenRules().values());
		}
		return result;
	}
	public void putError(String id,IValidationRule rule,Object...objects){
		BrokenElement errorListFor = getErrorListFor(id);
		errorListFor.addMessage(rule, objects);
	}
	public void putError(EObject holder,IValidationRule rule,Object...objects){
		BrokenElement errorListFor = getErrorListFor(holder);
		errorListFor.addMessage(rule, objects);
	}
	private BrokenElement getErrorListFor(EObject holder){
		return getErrorListFor(EmfWorkspace.getId(holder));
	}
	protected BrokenElement getErrorListFor(String id){
		BrokenElement list = this.errors.get(id);
		if(list == null){
			list = new BrokenElement(id);
			this.errors.put(id, list);
		}
		return list;
	}
	public boolean hasBroken(IValidationRule rule,Object target){
		BrokenElement e = errors.get(target);
		if(e == null){
			return false;
		}else{
			return e.hasBroken(rule);
		}
	}
	public void clear(){
		this.errors.clear();
	}
}
