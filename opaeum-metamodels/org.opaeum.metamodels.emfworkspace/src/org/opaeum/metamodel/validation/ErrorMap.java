package org.opaeum.metamodel.validation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;


public class ErrorMap{
	public ErrorMap(){
		super();
	}
	private Map<String,BrokenElement> errors = new HashMap<String,BrokenElement>();
	public Map<String,BrokenElement> getErrors(){
		return errors;
	}
	public void putError(String id,IValidationRule rule,Object...objects){
		BrokenElement errorListFor = getErrorListFor(id);
		errorListFor.addMessage(rule, objects);
	}
	public void putError(Element holder,IValidationRule rule,Object...objects){
		BrokenElement errorListFor = getErrorListFor(holder);
		errorListFor.addMessage(rule, objects);
	}
	private BrokenElement getErrorListFor(Element holder){
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
