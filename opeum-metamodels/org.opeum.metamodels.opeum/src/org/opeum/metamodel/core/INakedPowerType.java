package org.opeum.metamodel.core;
import nl.klasse.octopus.model.IClassifier;
public interface INakedPowerType extends INakedEnumeration {
	INakedClassifier getRepresentedSupertype();
	void setRepresentedSupertype(INakedClassifier representedSupertype);
	INakedPowerTypeInstance getLiteralForSubtype(IClassifier element);
}