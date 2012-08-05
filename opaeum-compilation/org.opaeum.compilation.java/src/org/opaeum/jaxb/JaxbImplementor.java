package org.opaeum.jaxb;

import java.util.ArrayList;
import java.util.Collections;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.simpleactions.EventGeneratorImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,after = {
	EventGeneratorImplementor.class
})
public class JaxbImplementor extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(Class  c){
		if(OJUtil.hasOJClass(c) ){
			OJAnnotatedClass owner = findJavaClass(c);
			addXmlRootElement(owner);
			OJOperation outgoingEvents = owner.getUniqueOperation("getOutgoingEvents");
			if(outgoingEvents != null){
				JaxbAnnotator.addXmlTransient((OJAnnotatedOperation) outgoingEvents);
			}
			OJOperation cancelledEvents = owner.getUniqueOperation("getCancelledEvents");
			if(cancelledEvents != null){
				JaxbAnnotator.addXmlTransient((OJAnnotatedOperation) cancelledEvents);
			}
			for(Property p:getLibrary().getDirectlyImplementedAttributes( c)){
				if(StereotypesHelper.hasStereotype(p.getType(),StereotypeNames.HELPER)){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
					OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), new ArrayList<Classifier>());
					JaxbAnnotator.addXmlTransient(getter);
				}else if(p.getType() instanceof Class && !EmfPropertyUtil.isInverse( p)){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
					OJAnnotatedOperation o = (OJAnnotatedOperation) owner.findOperation(map.getter(), Collections.EMPTY_LIST);
					JaxbAnnotator.addXmlTransient(o);
				}else if(p.getType() instanceof Interface){
					addXmlAnyElement(owner, c, p);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(Behavior behavior){
		if(behavior.getContext() != null && EmfBehaviorUtil.hasExecutionInstance(behavior)){
			OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
			if(EmfBehaviorUtil.isClassifierBehavior(behavior)){
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.getUniqueOperation("getClassifierBehavior");
				JaxbAnnotator.addXmlTransient(oper);
				// OJAnnotatedOperation getCurrentState= (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getCurrentState");
				// getCurrentState.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient")));
			}else{
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.getUniqueOperation("get" + NameConverter.capitalize(behavior.getName()));
				JaxbAnnotator.addXmlTransient(oper);
			}
		}
	}
	private void addXmlAnyElement(OJAnnotatedClass clazz,Class c,Property p){
		StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) clazz.findOperation(map.getter(), Collections.EMPTY_LIST);
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAnyElement")));
	}
	private void addXmlRootElement(OJAnnotatedClass owner){
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement")));
	}
}
