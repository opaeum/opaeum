package net.sf.nakeduml.metamodel.core;

import java.util.List;

import nl.klasse.octopus.model.IAssociation;

public interface INakedAssociation extends INakedClassifier, IAssociation {
	INakedProperty getEnd1();

	INakedProperty getEnd2();

	List<INakedProperty> getEnds();

	boolean isDerived();

	void setDerived(boolean t);

	boolean isClass();

	void setClass(boolean b);

	void setEnd(int i, INakedProperty np);
}