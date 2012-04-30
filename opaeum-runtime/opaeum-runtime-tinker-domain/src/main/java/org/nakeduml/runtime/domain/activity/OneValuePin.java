package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.List;


public abstract class OneValuePin<O> extends ValuePin<O, SingleObjectToken<O>> {

	public OneValuePin() {
		super();
	}

	protected abstract O getValue();

	@SuppressWarnings("unchecked")
	@Override
	public List<SingleObjectToken<O>> getInTokens() {
		return Arrays.<SingleObjectToken<O>>asList(new SingleObjectToken<O>(getName(), getValue()));
	}
	
	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValuePin //TODO");
		// sb.append(this.nodeStat.toString());
		return sb.toString();
	}

}
