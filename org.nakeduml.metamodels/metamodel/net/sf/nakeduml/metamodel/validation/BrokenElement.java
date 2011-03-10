package net.sf.nakeduml.metamodel.validation;
import java.util.HashMap;
import java.util.Map;
public class BrokenElement {
	private Map<IValidationRule, Object[]> brokenRules = new HashMap<IValidationRule, Object[]>();
	public BrokenElement() {
		super();
	}
	public void addMessage(IValidationRule rule, Object ... messageParameters) {
		brokenRules.put(rule, messageParameters);
	}
	public Object[] getMessageParameters(IValidationRule rule) {
		return brokenRules.get(rule);
	}
	public boolean hasBroken(IValidationRule rule) {
		return brokenRules.containsKey(rule);
	}
	public Map<IValidationRule, Object[]> getBrokenRules() {
		return brokenRules;
	}
}
