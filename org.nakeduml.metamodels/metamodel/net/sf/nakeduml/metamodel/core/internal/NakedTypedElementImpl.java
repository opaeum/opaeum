package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;

public class NakedTypedElementImpl extends NakedModelElementImpl implements INakedTypedElement {
	private static final long serialVersionUID = -5574117047617953873L;
	private boolean ordered;
	private boolean unique;
	private INakedMultiplicity multiplicity;
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

	public boolean isOrdered() {
		return ordered;
	}

	public void setIsOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	public IClassifier getType() {
		return type;
	}

	public void setType(IClassifier type) {
		this.type = type;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setIsUnique(boolean unique) {
		this.unique = unique;
	}

	public INakedMultiplicity getNakedMultiplicity() {
		return multiplicity;
	}
	public IMultiplicityKind getMultiplicity() {
		return getNakedMultiplicity();
	}

	public void setMultiplicity(INakedMultiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	@Override
	public String getMetaClass() {
		return "typedElement";
	}
}
