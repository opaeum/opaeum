package net.sf.nakeduml.metamodel.core;
import org.eclipse.uml2.uml.Classifier;
public interface INakedPowerType extends INakedEnumeration {
	INakedClassifier getRepresentedSupertype();
	void setRepresentedSupertype(INakedClassifier representedSupertype);
	INakedPowerTypeInstance getLiteralForSubtype(Classifier element);
}