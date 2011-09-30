package org.opeum.metamodel.core;
public interface INakedPowerTypeInstance extends INakedEnumerationLiteral {
	INakedGeneralization getRepresentedGeneralization();
	void setRepresentedGeneralization(INakedGeneralization representedGeneralization);
}