package org.opaeum.metamodel.core.internal;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedTypedElement;

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
		if(baseType==null){
			
		}
		this.baseType = baseType;
	}
	public IClassifier getType() {
		return type;
	}

	public void setType(IClassifier type) {
		if(type==null){
			
		}
		this.type = type;
	}

	@Override
	public String getMetaClass() {
		return "typedElement";
	}
}
