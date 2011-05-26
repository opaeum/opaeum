package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;

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
