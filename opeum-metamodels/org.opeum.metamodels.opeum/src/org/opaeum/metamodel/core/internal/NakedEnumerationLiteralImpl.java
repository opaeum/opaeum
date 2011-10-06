package org.opeum.metamodel.core.internal;
import nl.klasse.octopus.model.IEnumLiteral;
import nl.klasse.octopus.model.IEnumerationType;

import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedEnumerationLiteral;
public class NakedEnumerationLiteralImpl extends NakedInstanceSpecificationImpl implements IEnumLiteral, INakedElement,
		INakedEnumerationLiteral {
	private static final long serialVersionUID = 6454193211164627516L;
	Integer ordinal;
	public Integer getOrdinal(){
		return ordinal;
	}
	public void setOrdinal(Integer ordinal){
		this.ordinal = ordinal;
	}
	public NakedEnumerationLiteralImpl() {
	}
	@Override
	public String getMetaClass() {
		return "EnumerationLiteral";
	}
	public IEnumerationType getEnumeration() {
		return (IEnumerationType) getNameSpace();
	}
}
