package org.opaeum.metamodel.core;
import nl.klasse.octopus.model.IEnumLiteral;
public interface INakedEnumerationLiteral extends INakedInstanceSpecification, IEnumLiteral {
	public Integer getOrdinal();
}