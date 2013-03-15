package org.opaeum.javageneration.hibernate;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AssociationClassAttributeImplementor;
import org.opaeum.javageneration.basicjava.AttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,replaces = AssociationClassAttributeImplementor.class)
public class HibernateAssociationClassAttributeImplementor extends AssociationClassAttributeImplementor{
	@Override
	protected void visitProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
		super.visitProperty(owner, umlOwner, map);
	}
	@Override
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		if(HibernateUtil.isInterfaceValue(owner, map) || HibernateUtil.isExternalValue(owner, map)){
			return HibernateUtil.getFromInterfaceValue(umlOwner, owner, map, HibernateUtil.isExternalValue(owner, map), getLibrary());
		}else{
			return super.buildGetter(umlOwner, owner, map, derived);
		}
	}
	@Override
	protected OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		if(HibernateUtil.isInterfaceValue(owner, map) || HibernateUtil.isExternalValue(owner, map)){
			return HibernateUtil.removeFromInterfaceValue(owner, map);
		}else{
			return super.buildInternalRemover(owner, map);
		}
	}
	@Override
	protected OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		if(HibernateUtil.isInterfaceValue(owner, map) || HibernateUtil.isExternalValue(owner, map)){
			return HibernateUtil.addToInterfaceValue(owner, map, HibernateUtil.isExternalValue(owner, map));
		}else{
			return super.buildInternalAdder(owner, map);
		}
	}
	@Override
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		if(HibernateUtil.isInterfaceValue(owner, map) || HibernateUtil.isExternalValue(owner, map)){
			return HibernateUtil.putField(owner, map, HibernateUtil.isExternalValue(owner, map));
		}else{
			return super.buildField(owner, map);
		}
	}
}
