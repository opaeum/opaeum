package org.opaeum.jaxb;

import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
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
		org.opaeum.javageneration.bpm.activity.TaskAndResponsibilityImplementor.class,PersistentObjectImplementor.class,
		HibernateAnnotator.class})
public class JaxbImplementor extends AbstractStructureVisitor{
	@VisitAfter()
	public void visitInterface(Interface intf){
		if(ojUtil.hasOJClass(intf)){
			findJavaClass(intf).addToOperations(new OJAnnotatedOperation("fixJaxbRedefinitionBug"));
		}
	}
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
			if(c.getGenerals().size() > 0){
				OJAnnotatedField field = (OJAnnotatedField) owner.findField("deletedOn");
				if(field != null){
					JaxbAnnotator.addXmlTransient(field);
				}
			}
			// if(c.getName().equals("User")){
			// System.err.println();
			// }
			Set<Property> diattrs = getLibrary().getDirectlyImplementedAttributes(c);
			for(Property p:diattrs){
				if(!p.isStatic()){
					PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
					OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
					if(field != null){
						if(EmfPropertyUtil.isDerived(p) || p.isReadOnly()){
							JaxbAnnotator.addXmlTransient(field);
							continue;
						}else if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER) || map.getBaseType() instanceof Behavior){
							JaxbAnnotator.addXmlTransient(field);
							continue;
						}else if(EmfClassifierUtil.isPersistentComplexStructure(p.getType())){
							if(map.isInverse() || EmfClassifierUtil.isStructuredDataType(p.getType())){
								if(map.isOne()){
									OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlElement"));
									column.putAttribute("nillable", Boolean.TRUE);
									field.addAnnotationIfNew(column);
								}
							}else{
								JaxbAnnotator.addXmlTransient(field);
							}
							continue;
						}else if(c.getGenerals().size() >= 1){
							// Property att = getLibrary().findEffectiveAttribute(c.getGenerals().get(0), p.getName());
							// if(att != null && (EmfPropertyUtil.isDerived(att) || att.isReadOnly())){
							// JaxbAnnotator.addXmlTransient(field);
							// continue;
							// }
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
			// NB!! this code only really fixes a few specific cases for CM. Not to be trusted. Neither is entity pass-by-value
			OJAnnotatedOperation fixRedefinitionInJaxb = new OJAnnotatedOperation("fixJaxbRedefinitionBug");
			owner.addToOperations(fixRedefinitionInJaxb);
			if(c.getGenerals().size() > 0){
				fixRedefinitionInJaxb.getBody().addToStatements("super.fixJaxbRedefinitionBug()");
			}
			for(Property p:diattrs){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
				if(map.getProperty().isComposite() && EmfClassifierUtil.isCompositionParticipant(map.getBaseType() ) && !EmfPropertyUtil.isDerived(p) && !EmfAssociationUtil.isClass(p.getAssociation())){
					PropertyMap otherMap=null;
					if(map.getProperty().getOtherEnd()!=null && map.getProperty().getOtherEnd().isNavigable()){
						otherMap=ojUtil.buildStructuralFeatureMap(map.getProperty().getOtherEnd());
					}
					if(map.isOne()){
						OJIfStatement ifNoNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "().fixJaxbRedefinitionBug()");
						if(otherMap!=null){
							ifNoNull.getThenPart().addToStatements(map.getter() + "()." + otherMap.internalAdder()+"(this)");
						}
						fixRedefinitionInJaxb.getBody().addToStatements(ifNoNull);
					}else{
						OJForStatement ifNoNull = new OJForStatement(map.fieldname() ,map.javaBaseTypePath(), map.getter() + "()");
						ifNoNull.getBody().addToStatements(map.fieldname() + ".fixJaxbRedefinitionBug()");
						if(otherMap!=null){
							ifNoNull.getBody().addToStatements(map.fieldname() + "." + otherMap.internalAdder()+"(this)");
						}
						fixRedefinitionInJaxb.getBody().addToStatements(ifNoNull);
					}
				}
				if(map.isOne()){
					EList<Property> rdfs = p.getRedefinedProperties();
					if(rdfs.size() > 0){
						for(Property rdf:rdfs){
							if(p.getName().equals(rdf.getName()) && !EmfPropertyUtil.isDerived(p) && !EmfPropertyUtil.isDerived(rdf)){
								if(EmfPropertyUtil.getOwningClassifier(rdf) != c){
									// This results in jaxb only populating the superclass attribute
									PropertyMap rdfMap = ojUtil.buildStructuralFeatureMap(rdf);
									fixRedefinitionInJaxb.getBody().addToStatements(map.internalAdder() + "(super." + rdfMap.fieldname() + ")");
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(Behavior behavior){
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
