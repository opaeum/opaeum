package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.activity.interf.IClassifier;
import org.nakeduml.runtime.domain.activity.interf.IValuePin;

public abstract class ValuePin<O, OUT extends ObjectToken<O>> extends InputPin<O, OUT> implements IValuePin<O, OUT> {

	public ValuePin() {
		super();
	}
	
	@Override
	public abstract IClassifier getContext();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValuePin //TODO");
		// sb.append(this.nodeStat.toString());
		return sb.toString();
	}

}
