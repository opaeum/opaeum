package net.sf.nakeduml.metamodel.core;

import java.util.List;

import nl.klasse.octopus.model.IAssociationClass;

/**
 * In NakedUML assocations and association class are the same thing. Different types of assocations, including assocation classes could
 * potentially be a class in NakedUML, e.g. Interface Assocations and Qualified Assocations
 * 
 * @author ampie
 * 
 */
public interface INakedAssociation extends INakedClassifier,IAssociationClass,ICompositionParticipant,INakedComplexStructure{
	INakedProperty getEnd1();
	INakedProperty getEnd2();
	List<INakedProperty> getEnds();
	boolean isDerived();
	void setDerived(boolean t);
	boolean isClass();
	void setClass(boolean b);
	void setEnd(int i,INakedProperty np);
	void setPropertyToEnd1(INakedProperty np);
	void setPropertyToEnd2(INakedProperty np);
	INakedProperty getPropertyToEnd1();
	INakedProperty getPropertyToEnd2();
	INakedProperty getPropertyToEnd(INakedProperty p);
}