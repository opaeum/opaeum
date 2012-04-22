package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;

public abstract class ValuePin<O, OUT extends ObjectToken<O>> extends InputPin<O, OUT> {

	public ValuePin() {
		super();
	}

	protected abstract O getValue();

	protected abstract BaseTinkerSoftDelete getContextObject();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValuePin //TODO");
		// sb.append(this.nodeStat.toString());
		return sb.toString();
	}

}
