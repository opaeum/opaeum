package net.sf.nakeduml.metamodel.validation;

import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import nl.klasse.octopus.model.IModelElement;

public class ErrorMap {
	private boolean inCodeGeneratingModel=false;
	public ErrorMap() {
		super();
	}

	private Map<String, BrokenElement> errors = new HashMap<String, BrokenElement>();

	public Map<String, BrokenElement> getErrors() {
		return errors;
	}
	public void enterCodeGeneratingModel(){
		inCodeGeneratingModel=false;
	}
	public void exitCodeGeneratingModel(){
		inCodeGeneratingModel=false;
	}
	public void putError( INakedElement holder, IValidationRule rule, Object... objects) {
		if(holder instanceof INakedEvent || holder instanceof INakedValueSpecification){
			getErrorListFor((INakedElement) holder.getOwnerElement()).addMessage(rule, objects);
		}
		BrokenElement errorListFor = getErrorListFor(holder);
		errorListFor.addMessage(rule, objects);
	}

	private BrokenElement getErrorListFor(INakedElement holder) {
 		BrokenElement list = this.errors.get(holder.getId());
		if (list == null) {
			list = new BrokenElement();
			this.errors.put(holder.getId(), list);
		}
		return list;
	}



	public boolean hasBroken(IValidationRule rule, Object target) {
		BrokenElement e = errors.get(target);
		if (e == null) {
			return false;
		} else {
			return e.hasBroken(rule);
		}
	}

	public void clear() {
		this.errors.clear();
	}
}
