package net.sf.nakeduml.metamodel.validation;

import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;

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
	public void putError(INakedElement holder,IValidationRule rule,Object...objects){
		BrokenElement errorListFor = getErrorListFor(holder);
		errorListFor.addMessage(rule, objects);
		// Events have difference containment structure:
		while(!(holder == null || holder instanceof INakedEvent)){
			holder = (INakedElement) holder.getOwnerElement();
		}
		if(holder instanceof INakedEvent){
			putError((INakedElement) holder.getOwnerElement(), rule, objects);
		}
	}
	private BrokenElement getErrorListFor(INakedElement holder){
		return getErrorListFor(holder.getId());
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
