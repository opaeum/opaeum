package org.opaeum.jaxb;

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
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.bpm.EventHandlerImplementor;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.persistence.PersistentObjectImplementor;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,after = {HibernateAnnotator.class,EventHandlerImplementor.class,
		org.opaeum.javageneration.bpm.statemachine.StateMachineEventConsumptionImplementor.class,
		org.opaeum.javageneration.bpm.activity.TaskAndResponsibilityImplementor.class,PersistentObjectImplementor.class,HibernateAnnotator.class})
public class JaxbImplementor extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true)
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojClass,Classifier c){
		if(ojUtil.hasOJClass(c)){
			OJAnnotatedClass owner = findJavaClass(c);
			OJAnnotationValue accessor = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAccessorType"));
			accessor.addEnumValue(new OJEnumValue(new OJPathName("javax.xml.bind.annotation.XmlAccessType"), "FIELD"));
			owner.putAnnotation(accessor);
			addXmlRootElement(owner);
			OJAnnotatedField outgoingEvents = (OJAnnotatedField) owner.findField("outgoingEvents");
			if(outgoingEvents != null){
				JaxbAnnotator.addXmlTransient(outgoingEvents);
			}
			OJAnnotatedField cancelledEvents = (OJAnnotatedField) owner.findField("cancelledEvents");
			if(cancelledEvents != null){
				JaxbAnnotator.addXmlTransient(cancelledEvents);
			}
			OJAnnotatedField persistence = (OJAnnotatedField) owner.findField("persistence");
			if(persistence != null){
				JaxbAnnotator.addXmlTransient(persistence);
			}
			if(c.getGenerals().size()>0){
				OJAnnotatedField field = (OJAnnotatedField) owner.findField("deletedOn");
				if(field!=null){
					JaxbAnnotator.addXmlTransient(field);
				}
				
			}
			for(Property p:getLibrary().getDirectlyImplementedAttributes(c)){
				if(!p.isStatic()){
					PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
					OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
					if(field != null){
						if(EmfPropertyUtil.isDerived(p) || p.isReadOnly()){
							JaxbAnnotator.addXmlTransient(field);
							break;
						}else if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
							JaxbAnnotator.addXmlTransient(field);
							break;
						}else if(p.getType() instanceof Class && !map.isInverse()){
							JaxbAnnotator.addXmlTransient(field);
							break;
						}else if(c.getGenerals().size()>=1){
							Property att = getLibrary().findEffectiveAttribute(c.getGenerals().get(0),p.getName());
							if(att!=null && (EmfPropertyUtil.isDerived(att) || att.isReadOnly())){
								JaxbAnnotator.addXmlTransient(field);
								break;
							}
						}
						if(p.getType() instanceof Interface){
							addXmlAnyElement(owner, c, p);
						}else if(p.getType() instanceof DataType && EmfClassifierUtil.hasStrategy((DataType) p.getType(), JaxbStrategy.class)){
							EmfClassifierUtil.getStrategy((DataType) p.getType(), JaxbStrategy.class).annotatedField(
									(OJAnnotatedField) owner.findField(map.fieldname()));
						}
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
				Property ea = getLibrary().getEmulatedPropertyHolder(behavior.getContext()).getEmulatedAttribute(behavior);
				OJAnnotatedField oper = (OJAnnotatedField) ojContext.findField(ojUtil.buildStructuralFeatureMap(ea).fieldname());
				JaxbAnnotator.addXmlTransient(oper);
		}
	}
	private void addXmlAnyElement(OJAnnotatedClass clazz,Classifier c,Property p){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
		OJAnnotatedField oper = (OJAnnotatedField) clazz.findField(map.fieldname());
		oper.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlAnyElement")));
	}
	private void addXmlRootElement(OJAnnotatedClass owner){
		owner.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement")));
	}
	@Override
	protected void visitProperty(OJAnnotatedClass cls,Classifier owner,PropertyMap map){
	}
}
