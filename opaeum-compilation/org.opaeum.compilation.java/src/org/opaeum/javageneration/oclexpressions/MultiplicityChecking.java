package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IEnumerationType;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	AttributeImplementor.class
},after = {
	AttributeImplementor.class
})
public class MultiplicityChecking extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(Classifier in){
		if(!(in instanceof IEnumerationType)){
			OJPathName path = new ClassifierMap(in).javaTypePath();
			OJClassifier myOwner = javaModel.findClass(path);
			if(myOwner != null){
				MultCheckCreator maker = new MultCheckCreator();
				maker.createCheckOper(myOwner);
				if(myOwner instanceof OJClass){
					for(Property attr:in.getAttributes()){
						maker.structuralfeature(attr);
					}
					maker.finishCheckOper();
				}
			}
		}
	}
}