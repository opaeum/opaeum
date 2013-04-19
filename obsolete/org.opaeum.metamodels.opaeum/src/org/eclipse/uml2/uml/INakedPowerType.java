package org.eclipse.uml2.uml;
import nl.klasse.octopus.model.IClassifier;
public interface INakedPowerType extends INakedEnumeration {
	INakedClassifier getRepresentedSupertype();
	void setRepresentedSupertype(INakedClassifier representedSupertype);
	INakedPowerTypeInstance getLiteralForSubtype(IClassifier element);
}