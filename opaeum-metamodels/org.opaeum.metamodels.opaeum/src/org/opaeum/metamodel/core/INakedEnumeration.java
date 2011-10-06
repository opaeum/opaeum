package org.opaeum.metamodel.core;
import java.util.List;

import nl.klasse.octopus.model.IEnumerationType;
public interface INakedEnumeration extends INakedDataType, IEnumerationType {
	List<INakedEnumerationLiteral> getOwnedLiterals();
}