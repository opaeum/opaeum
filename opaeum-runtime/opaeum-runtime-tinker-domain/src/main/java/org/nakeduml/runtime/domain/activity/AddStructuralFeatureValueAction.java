package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AddStructuralFeatureValueAction<V, O> extends WriteStructuralFeatureAction<V, O> {

	public AddStructuralFeatureValueAction() {
		super();
	}

	public AddStructuralFeatureValueAction(boolean persist, String name) {
		super(persist, name);
	}

	public AddStructuralFeatureValueAction(Vertex vertex) {
		super(vertex);
	}

	protected abstract void writeStructuralFeature(O o, V v);

	@Override
	protected void execute() {
		super.execute();
		O object = getObject().getInTokens().iterator().next().getObject();
		V value = getValue().getInTokens().iterator().next().getObject();
		writeStructuralFeature(object, value);
		getResult().addIncomingToken(new SingleObjectToken<O>(getResult().getName(), object));
	}

}
