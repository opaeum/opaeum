package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IEnumerationType;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;

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