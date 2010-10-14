package net.sf.nakeduml.javageneration.userinteraction;

import java.util.Collections;

import net.sf.nakeduml.annotation.CompositionFromOwningObject;
import net.sf.nakeduml.annotation.CompositionToOwnedObject;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
//TODO delete?
public class CompositionTraversalAnnotator extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedProperty np){
		if(true)
			return;//
		// Datatypes should be reusable outside of context. At runtime, the parent
		// should be used as referencepoint for composition semantics
		if(np.isComposite() && np.getNakedBaseType() instanceof INakedEntity
				&& !(np.getNakedBaseType() instanceof INakedStructuredDataType)){
			StructuralFeatureMap map = new NakedStructuralFeatureMap(np);
			OJAnnotatedClass ojClass = findJavaClass(np.getOwner());
			OJAnnotatedOperation getter = (OJAnnotatedOperation) ojClass.findOperation(map.getter(), Collections.EMPTY_LIST);
			OJAnnotationValue an = new OJAnnotationValue(new OJPathName(CompositionToOwnedObject.class.getName()));
			getter.putAnnotation(an);
			OJAnnotatedClass otherClass = findJavaClass(np.getNakedBaseType());
			OJAnnotationValue an2 = new OJAnnotationValue(new OJPathName(CompositionFromOwningObject.class.getName()));
			an2.putAttribute(new OJAnnotationAttributeValue("attributeToChild", np.getMappingInfo().getJavaName().toString()));
			an2.putAttribute(new OJAnnotationAttributeValue("attributeToParent", np.getOtherEnd().getMappingInfo().getJavaName().toString()));
			an2.putAttribute(new OJAnnotationAttributeValue("parentType", ojClass.getPathName()));
			otherClass.putAnnotation(an2);
		}
	}
}
