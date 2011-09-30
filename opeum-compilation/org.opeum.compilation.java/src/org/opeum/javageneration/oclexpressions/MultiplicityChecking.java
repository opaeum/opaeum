package org.opeum.javageneration.oclexpressions;

import java.util.Iterator;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AttributeImplementor;
import org.opeum.metamodel.core.INakedClassifier;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IEnumerationType;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJPathName;

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
					Iterator it = in.getAttributes().iterator();
					while(it.hasNext()){
						IAttribute attr = (IAttribute) it.next();
						maker.structuralfeature(attr);
					}
					it = in.getNavigations().iterator();
					while(it.hasNext()){
						IAssociationEnd attr = (IAssociationEnd) it.next();
						maker.structuralfeature(attr);
					}
					maker.finishCheckOper();
				}
			}
		}
	}
}