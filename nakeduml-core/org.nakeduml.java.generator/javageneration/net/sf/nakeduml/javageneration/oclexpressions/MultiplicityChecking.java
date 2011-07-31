package net.sf.nakeduml.javageneration.oclexpressions;

import java.util.Iterator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.creators.MultCheckCreator;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IEnumerationType;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJPathName;

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