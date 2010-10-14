package net.sf.nakeduml.metamodel.core;
public interface INakedGeneralization extends INakedElement {
	INakedPowerTypeInstance getPowerTypeLiteral();
	INakedClassifier getSpecific();
	INakedClassifier getGeneral();
	void setParentAndChild(INakedClassifier parent, INakedClassifier child);
	void setPowerTypeLiteral(INakedPowerTypeInstance powerTypeLiteral);
}