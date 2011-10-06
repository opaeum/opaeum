package org.opaeum.metamodel.core;
public interface INakedGeneralization extends INakedElement {
	INakedPowerTypeInstance getPowerTypeLiteral();
	INakedClassifier getSpecific();
	INakedClassifier getGeneral();
	void setGeneral(INakedClassifier parent);
	void setPowerTypeLiteral(INakedPowerTypeInstance powerTypeLiteral);
}