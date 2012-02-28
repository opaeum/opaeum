package org.nakeduml.runtime.domain.activity;


public abstract class ValuePin<O> extends InputPin<O> {

	public ValuePin() {
		super();
	}

	protected abstract O getValue();

}
