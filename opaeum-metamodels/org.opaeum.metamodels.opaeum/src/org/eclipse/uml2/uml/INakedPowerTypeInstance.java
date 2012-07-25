package org.eclipse.uml2.uml;
public interface INakedPowerTypeInstance extends INakedEnumerationLiteral {
	INakedGeneralization getRepresentedGeneralization();
	void setRepresentedGeneralization(INakedGeneralization representedGeneralization);
}