package org.nakeduml.tinker.generator;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.maps.AssociationClassEndMap;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;

@StepDependency(phase = JavaTransformationPhase.class, replaces = AttributeImplementor.class)
public class TinkerCodeGenerator extends AttributeImplementor {

	@Override
	protected OJAnnotatedOperation buildGetter(OJAnnotatedClass owner,
			NakedStructuralFeatureMap map, boolean b) {
		// TODO Auto-generated method stub
		return super.buildGetter(owner, map, b);
	}

	@Override
	protected void buildInternalAdder(OJAnnotatedClass owner,
			AssociationClassEndMap aMap) {
		// TODO Auto-generated method stub
		super.buildInternalAdder(owner, aMap);
	}

	@Override
	protected void buildInternalRemover(OJAnnotatedClass owner,
			AssociationClassEndMap aMap) {
		// TODO Auto-generated method stub
		super.buildInternalRemover(owner, aMap);
	}

	@Override
	protected void buildInternalRemover(OJAnnotatedClass owner,
			NakedStructuralFeatureMap map) {
		// TODO Auto-generated method stub
		super.buildInternalRemover(owner, map);
	}

	@Override
	protected void buildInternalAdder(OJAnnotatedClass owner,
			NakedStructuralFeatureMap map) {
		// TODO Auto-generated method stub
		super.buildInternalAdder(owner, map);
	}

}
