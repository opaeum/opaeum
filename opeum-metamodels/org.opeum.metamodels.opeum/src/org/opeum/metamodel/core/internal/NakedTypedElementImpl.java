package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;

public class NakedTypedElementImpl extends NakedMultiplicityElement implements INakedTypedElement {
	private static final long serialVersionUID = -5574117047617953873L;
	private IClassifier type;
	private INakedClassifier baseType;

	public IClassifier getBaseType() {
		return getNakedBaseType();
	}

	public INakedClassifier getNakedBaseType() {
		return baseType;
	}

	public void setBaseType(INakedClassifier baseType) {
		this.baseType = baseType;
	}
	public IClassifier getType() {
		return type;
	}

	public void setType(IClassifier type) {
		this.type = type;
	}

	@Override
	public String getMetaClass() {
		return "typedElement";
	}
}
