package net.sf.nakeduml.metamodel.core;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import nl.klasse.octopus.model.IClass;

public interface INakedEntity extends IClass, INakedBehavioredClassifier, INakedComplexStructure, ICompositionParticipant {

	/**
	 * Returns a list of properties that define the uniqueness constraints on
	 * this structured type. A property is considered to be a uniqueness
	 * constraint when its other end is navigable and has qualifiers and a
	 * qualified multiplicity of one.
	 * 
	 */
	List<INakedProperty> getUniquenessConstraints();




	INakedBehavior getClassifierBehavior();


	void setClassifierBehavior(INakedBehavior behavior);
}