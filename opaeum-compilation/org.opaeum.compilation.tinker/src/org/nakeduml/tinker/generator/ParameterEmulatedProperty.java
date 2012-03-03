package org.nakeduml.tinker.generator;

import java.util.Collection;

import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;

public class ParameterEmulatedProperty extends AbstractEmulatedProperty {

	private INakedParameter parameter;
	
	public ParameterEmulatedProperty(INakedClassifier owner, INakedParameter parameter) {
		super(owner, parameter);
		this.parameter = parameter;
		this.type = parameter.getType();
	}

	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd() {
		return null;
	}

	@Override
	public boolean isOrdered() {
		return parameter.isOrdered();
	}

	@Override
	public boolean isUnique() {
		return parameter.isUnique();
	}

	@Override
	public INakedMultiplicity getNakedMultiplicity() {
		return parameter.getNakedMultiplicity();
	}

	@Override
	public INakedClassifier getNakedBaseType() {
		return parameter.getNakedBaseType();
	}

}
