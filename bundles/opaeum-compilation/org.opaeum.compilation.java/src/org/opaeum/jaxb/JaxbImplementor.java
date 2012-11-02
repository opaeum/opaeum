package org.opaeum.jaxb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
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
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.bpm.EventHandlerImplementor;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,after = {HibernateAnnotator.class,EventHandlerImplementor.class,
		org.opaeum.javageneration.bpm.statemachine.StateMachineEventConsumptionImplementor.class,
		org.opaeum.javageneration.bpm.activity.TaskAndResponsibilityImplementor.class})
public class JaxbImplementor extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true)
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojClass,Classifier c){
		if(ojUtil.hasOJClass(c)){
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
			for(Property p:getLibrary().getDirectlyImplementedAttributes(c)){
				if(!p.isStatic() || EmfPropertyUtil.isDerived(p)){
					PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
					if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
						OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), new ArrayList<OJPathName>());
						JaxbAnnotator.addXmlTransient(getter);
					}else if(p.getType() instanceof Class && !map.isInverse()){
						OJAnnotatedOperation o = (OJAnnotatedOperation) owner.findOperation(map.getter(), new ArrayList<OJPathName>());
						JaxbAnnotator.addXmlTransient(o);
					}else if(p.getType() instanceof Interface){
						addXmlAnyElement(owner, c, p);
					}else if(p.getType() instanceof DataType && EmfClassifierUtil.hasStrategy((DataType) p.getType(), JaxbStrategy.class)){
						List<OJPathName> emptyList = Collections.emptyList();
						EmfClassifierUtil.getStrategy((DataType) p.getType(), JaxbStrategy.class).annotatedMethod(
								(OJAnnotatedOperation) owner.findOperation(map.getter(), emptyList));
					}
				}
			}
		}
		return false;
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(Behavior behavior){
		if(behavior.getContext() != null && EmfBehaviorUtil.hasExecutionInstance(behavior)){
			OJAnnotatedClass ojContext = findJavaClass(behavior.getContext());
			if(EmfBehaviorUtil.isClassifierBehavior(behavior)){
				Property ea = getLibrary().getEmulatedPropertyHolder(behavior.getContext()).getEmulatedAttribute(behavior);
				String getter = ojUtil.buildStructuralFeatureMap(ea).getter();
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.findOperation(getter, new ArrayList<OJPathName>());
				JaxbAnnotator.addXmlTransient(oper);
				// OJAnnotatedOperation getCurrentState= (OJAnnotatedOperation) OJUtil.findOperation(ojContext, "getCurrentState");
				// getCurrentState.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlTransient")));
			}else{
				OJAnnotatedOperation oper = (OJAnnotatedOperation) ojContext.findOperation("get" + NameConverter.capitalize(behavior.getName()),
						new ArrayList<OJPathName>());
				JaxbAnnotator.addXmlTransient(oper);
			}
		}
	}
	private void addXmlAnyElement(OJAnnotatedClass clazz,Classifier c,Property p){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) clazz.findOperation(map.getter(), new ArrayList<OJPathName>());
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAnyElement")));
	}
	private void addXmlRootElement(OJAnnotatedClass owner){
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement")));
	}
	@Override
	protected void visitProperty(OJAnnotatedClass cls,Classifier owner,PropertyMap map){
	}
}
