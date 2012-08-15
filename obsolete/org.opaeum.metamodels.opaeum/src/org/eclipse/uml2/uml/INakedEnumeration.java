package org.eclipse.uml2.uml;
import java.util.List;

import nl.klasse.octopus.model.IEnumerationType;
public interface INakedEnumeration extends INakedDataType, IEnumerationType {
	List<INakedEnumerationLiteral> getOwnedLiterals();
}