package org.opaeum.metamodel.activities.internal;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.ObjectNodeType;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedParameter;

public class NakedParameterNodeImpl extends NakedObjectNodeImpl implements INakedParameterNode {
	private static final long serialVersionUID = 9125417030702683972L;
	INakedParameter parameter;

	@Override
	public String getMetaClass() {
		return "parameterNode";
	}

	public INakedParameter getParameter() {
		return this.parameter;
	}

	public void setParameter(INakedParameter parameter) {
		this.parameter = parameter;
	}

	@Override
	public String getName() {
		if (parameter == null) {
			return super.getName();
		} else {
			return parameter.getName();
		}
	}

	@Override
	public ObjectNodeType getObjectNodeType() {
		return ObjectNodeType.PARAMETER_NODE;
	}

	@Override
	public INakedClassifier getNakedBaseType() {
		return this.parameter.getNakedBaseType();
	}

	@Override
	public INakedMultiplicity getNakedMultiplicity() {
		return this.parameter.getNakedMultiplicity();
	}

	@Override
	public IClassifier getType() {
		return this.parameter.getType();
	}

	@Override
	public boolean isOrdered() {
		return this.parameter.isOrdered();
	}

	@Override
	public boolean isUnique() {
		return this.parameter.isUnique();
	}

	public boolean isException() {
		return this.parameter.isException();
	}
}