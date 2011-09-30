package org.opeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IEnumerationType;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AttributeImplementor;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedProperty;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	AttributeImplementor.class
},after = {
	AttributeImplementor.class
})
public class MultiplicityChecking extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier in){
		if(!(in instanceof IEnumerationType)){
			OJPathName path = new ClassifierMap(in).javaTypePath();
			OJClassifier myOwner = javaModel.findIntfOrCls(path);
			if(myOwner != null){
				MultCheckCreator maker = new MultCheckCreator();
				maker.createCheckOper(myOwner);
				if(myOwner instanceof OJClass){
					for(INakedProperty attr:in.getOwnedAttributes()){
						maker.structuralfeature(attr);
					}
					maker.finishCheckOper();
				}
			}
		}
	}
}