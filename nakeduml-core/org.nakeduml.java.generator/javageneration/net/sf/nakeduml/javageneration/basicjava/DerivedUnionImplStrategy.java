package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJClass;

public interface DerivedUnionImplStrategy {

	void addSubsetToUnion(NakedStructuralFeatureMap map, OJClass c, INakedProperty derivedUnion);

}
