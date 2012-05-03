package org.opaeum.metamodel.core.internal;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedTypedElement;

public class NakedTypedElementImpl extends NakedMultiplicityElement implements INakedTypedElement {
	private static final long serialVersionUID = -5574117047617953873L;
	private IClassifier type;
	private INakedClassifier baseType;
	private boolean isMeasure;
	private boolean isDimension;
	public boolean isMeasure(){
		return isMeasure;
	}
	public boolean isDimension(){
		return isDimension && getMultiplicity().isSingleObject();
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature(TagNames.ROLE_IN_CUBE)){
			INakedEnumerationLiteral l= (INakedEnumerationLiteral) stereotype.getFirstValueFor(TagNames.ROLE_IN_CUBE).getValue();
			if(l.getName().equalsIgnoreCase("measure")){
				isMeasure=true;
			}else if(l.getName().equalsIgnoreCase("dimension")){
				isDimension=true;
			}
				
		}
	}

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
		this.type = type;
	}

	@Override
	public String getMetaClass() {
		return "typedElement";
	}
}
