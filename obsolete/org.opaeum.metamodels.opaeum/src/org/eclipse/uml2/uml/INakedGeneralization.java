package org.eclipse.uml2.uml;
public interface INakedGeneralization extends INakedElement {
	INakedPowerTypeInstance getPowerTypeLiteral();
	INakedClassifier getSpecific();
	INakedClassifier getGeneral();
	void setGeneral(INakedClassifier parent);
	void setPowerTypeLiteral(INakedPowerTypeInstance powerTypeLiteral);
}